package com.alwaysafter.minecraft.paintball.listener;

import com.alwaysafter.minecraft.paintball.PaintballConstants;
import com.alwaysafter.minecraft.paintball.PaintballPlugin;
import com.alwaysafter.minecraft.paintball.cache.PaintballUserCache;
import com.alwaysafter.minecraft.paintball.data.gun.PaintballGun;
import com.alwaysafter.minecraft.paintball.data.user.PaintballUser;
import com.alwaysafter.minecraft.paintball.util.ItemBuilder;
import lombok.RequiredArgsConstructor;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

@RequiredArgsConstructor
public final class PaintballDamageListener implements Listener {

    private final PaintballUserCache paintballUserCache;

    @EventHandler(priority = EventPriority.HIGHEST)
    private void handleInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final ItemStack item = event.getItem();

        final PaintballUser paintballUser = this.paintballUserCache.getPaintballUser(player.getName());
        if(paintballUser == null) return;

        final ItemBuilder itemBuilder = ItemBuilder.of(item);
        final NBTTagCompound nbtTagCompound = itemBuilder.getCompound();

        if(!nbtTagCompound.hasKey(PaintballConstants.GUN_NBT_TAG_KEY)) {
            return;
        }

        final PaintballGun paintballGun = paintballUser.getGun();
        final Snowball snowball = player.getWorld().spawn(player.getLocation(), Snowball.class);

        snowball.setMetadata("damage", new FixedMetadataValue(PaintballPlugin.getInstance(), paintballGun.getDamage()));
    }

}
