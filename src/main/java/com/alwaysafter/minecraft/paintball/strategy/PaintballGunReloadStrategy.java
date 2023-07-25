package com.alwaysafter.minecraft.paintball.strategy;

import com.alwaysafter.minecraft.paintball.PaintballPlugin;
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
        playerExact.addPotionEffect(PotionEffectType.SLOW.createEffect(paintballGun.getReloadTime(), 6));

        final BukkitCountdownTimer countdownTimer = BukkitCountdownTimer.of(
                paintballGun.getReloadTime(),
                value -> playerExact.sendTitle("§fReloading in", "§7" + value + " seconds§f."),
                () -> {
                    playerExact.sendTitle("§aGun reloaded!", "§7Have fun shooting1");
                    paintballGun.setClipAmmo(paintballGun.getMaximumAmmo());
                }
        );

        playerExact.removePotionEffect(PotionEffectType.SLOW);
        countdownTimer.runTaskTimerAsynchronously(PaintballPlugin.getInstance(), 20, 20);
    }

}
