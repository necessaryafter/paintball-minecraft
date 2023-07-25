package com.alwaysafter.minecraft.paintball.listener;

import com.alwaysafter.minecraft.paintball.PaintballConstants;
import com.alwaysafter.minecraft.paintball.PaintballPlugin;
import com.alwaysafter.minecraft.paintball.cache.PaintballUserCache;
import com.alwaysafter.minecraft.paintball.controller.PaintballController;
import com.alwaysafter.minecraft.paintball.data.user.PaintballUser;
import com.alwaysafter.minecraft.paintball.strategy.PaintballGunDamageStrategy;
import com.alwaysafter.minecraft.paintball.strategy.PaintballGunShootStrategy;
import com.alwaysafter.minecraft.paintball.util.CountdownMap;
import com.alwaysafter.minecraft.paintball.util.ItemBuilder;
import lombok.RequiredArgsConstructor;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public final class PaintballDamageListener implements Listener {

    private final CountdownMap<String> countdownMap = CountdownMap.of(TimeUnit.MILLISECONDS);
    private final PaintballUserCache paintballUserCache;

    @EventHandler(priority = EventPriority.HIGHEST)
    private void handleInteract(PlayerInteractEvent event) {
        if(!event.hasItem()) return;

        final Player player = event.getPlayer();
        final ItemStack item = event.getItem();

        final PaintballUser paintballUser = this.paintballUserCache.getPaintballUser(player.getName());
        if(paintballUser == null) return;

        final ItemBuilder itemBuilder = ItemBuilder.of(item);
        final NBTTagCompound nbtTagCompound = itemBuilder.nbt();

        if(!nbtTagCompound.hasKey(PaintballConstants.GUN_NBT_TAG_KEY)) {
            return;
        }

        event.setCancelled(true);
        if (countdownMap.isInCountdown(player.getName())) {
            return;
        }

        countdownMap.insert(player.getName(), 500);
        new PaintballGunShootStrategy().shoot(paintballUser);
    }

    @EventHandler
    private void handleSnowballProjectile(EntityDamageByEntityEvent event) {
        if(!(event.getDamager() instanceof Snowball) || !(event.getEntity() instanceof Player)) return;

        final Snowball snowball = (Snowball) event.getDamager();
        final Player player = (Player) event.getEntity();

        event.setCancelled(true);

        final PaintballController paintballController = PaintballPlugin.getInstance().getPaintballController();
        if(!paintballController.hasGameStarted()) {
            return;
        }

        new PaintballGunDamageStrategy(paintballController.getPaintballGame())
                .handle(paintballUserCache.getPaintballUser(player.getName()), snowball);
    }

}
