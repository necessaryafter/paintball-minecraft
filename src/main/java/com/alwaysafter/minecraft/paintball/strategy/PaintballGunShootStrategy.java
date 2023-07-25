package com.alwaysafter.minecraft.paintball.strategy;

import com.alwaysafter.minecraft.paintball.PaintballConstants;
import com.alwaysafter.minecraft.paintball.PaintballPlugin;
import com.alwaysafter.minecraft.paintball.data.gun.PaintballGun;
import com.alwaysafter.minecraft.paintball.data.user.PaintballUser;
import com.alwaysafter.minecraft.paintball.util.CountdownMap;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.concurrent.TimeUnit;

public final class PaintballGunShootStrategy {

    private final CountdownMap<String> countdownMap = CountdownMap.of(TimeUnit.SECONDS);

    public void shoot(PaintballUser paintballUser) {
        final Player playerExact = Bukkit.getPlayerExact(paintballUser.getPlayerName());
        if(playerExact == null) return;

        final PaintballGun paintballGun = paintballUser.getGun();
        if(paintballGun.getClipAmmo() == 0) {
            playerExact.sendMessage("§7You are out of ammo! Reloading your weapon...");

            if(!countdownMap.isInCountdown(playerExact.getName())) {
                new PaintballGunReloadStrategy().handle(paintballUser);
            }

            countdownMap.insert(playerExact.getName(), 3);
            return;
        }

        final Snowball snowball = playerExact.launchProjectile(Snowball.class);
        snowball.setVelocity(playerExact.getEyeLocation()
                .getDirection()
                .add(playerExact.getVelocity())
                .multiply(2));

        snowball.setMetadata(PaintballConstants.GUN_PROJECTILE_METADATA_KEY,
                new FixedMetadataValue(PaintballPlugin.getInstance(), paintballGun.getDamage()));

        paintballGun.setClipAmmo(paintballGun.getClipAmmo() - 1);
        playerExact.playSound(playerExact.getLocation(), Sound.NOTE_BASS_DRUM, 1f, 1f);
    }

}
