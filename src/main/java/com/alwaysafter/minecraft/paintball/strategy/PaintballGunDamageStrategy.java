package com.alwaysafter.minecraft.paintball.strategy;

import com.alwaysafter.minecraft.paintball.PaintballConstants;
import com.alwaysafter.minecraft.paintball.PaintballPlugin;
import com.alwaysafter.minecraft.paintball.cache.PaintballUserCache;
import com.alwaysafter.minecraft.paintball.data.PaintballGame;
import com.alwaysafter.minecraft.paintball.data.user.PaintballUser;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;

@RequiredArgsConstructor
public final class PaintballGunDamageStrategy {

    private final PaintballGame paintballGame;

    public void handle(PaintballUser paintballUser, Snowball snowball) {
        final Player shooter = (Player) snowball.getShooter();
        final double damage = (double) snowball.getMetadata(PaintballConstants.GUN_PROJECTILE_METADATA_KEY).get(0).value();

        final Player player = Bukkit.getPlayerExact(paintballUser.getPlayerName());
        if(player == null) return;

        final double health = player.getHealth() - damage;
        if(health <= 0) {
            final PaintballUserCache paintballUserCache = PaintballPlugin.getInstance().getPaintballUserCache();
            final PaintballUser shooterUser = paintballUserCache.getPaintballUser(shooter.getName());

            new PaintballUserDieStrategy(paintballGame).handle(paintballUser, shooterUser);
            return;
        }

        player.damage(damage);
        player.getWorld().playEffect(player.getLocation(), Effect.STEP_SOUND, Material.REDSTONE_WIRE);
        paintballUser.getStats().incrementKills();
    }

}
