package com.alwaysafter.minecraft.paintball.strategy;

import com.alwaysafter.minecraft.paintball.data.gun.PaintballGun;
import com.alwaysafter.minecraft.paintball.data.user.PaintballUser;
import com.alwaysafter.minecraft.paintball.util.BukkitCountdownTimer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public final class PaintballGunReloadStrategy {

    public void handle(PaintballUser user) {
        final Player playerExact = Bukkit.getPlayerExact(user.getPlayerName());
        if(playerExact == null) return;

        final PaintballGun paintballGun = user.getGun();
        final BukkitCountdownTimer countdownTimer = BukkitCountdownTimer.of(
                paintballGun.getReloadTime(),
                value -> playerExact.sendTitle("§7Reloading in", "§7" + value + " seconds"),
                () -> paintballGun.setClipAmmo(paintballGun.getMaximumAmmo())
        );

        playerExact.addPotionEffect(PotionEffectType.SLOW.createEffect(paintballGun.getReloadTime(), 6));
    }

}
