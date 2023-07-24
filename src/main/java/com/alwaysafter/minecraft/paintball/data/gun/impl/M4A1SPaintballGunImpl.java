package com.alwaysafter.minecraft.paintball.data.gun.impl;

import com.alwaysafter.minecraft.paintball.PaintballConstants;
import com.alwaysafter.minecraft.paintball.data.gun.PaintballGun;
import com.alwaysafter.minecraft.paintball.util.ItemBuilder;
import lombok.RequiredArgsConstructor;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public final class M4A1SPaintballGunImpl implements PaintballGun {

    private int clipAmmo = 0;

    @Override
    public String getGunName() {
        return "M4A1 (Silent)";
    }

    @Override
    public int getMaximumAmmo() {
        return 30;
    }

    @Override
    public int getClipAmmo() {
        return clipAmmo;
    }

    @Override
    public int getReloadTime() {
        return 3;
    }

    @Override
    public double getDamage() {
        return 5;
    }

    @Override
    public ItemStack getItemStack() {
        final ItemBuilder itemBuilder = ItemBuilder.of(Material.DIAMOND_HOE)
                .displayName(getGunName())
                .lore(
                        "§7Damage: §f" + getDamage(),
                        "§7Max Ammo: §f" + getMaximumAmmo(),
                        "",
                        "§7This gun is originally from",
                        "§7Counter Terrorist Team."
                );

        final NBTTagCompound compound = itemBuilder.getCompound();
        compound.setBoolean(PaintballConstants.GUN_NBT_TAG_KEY, true);

        return itemBuilder.buildWithNbt();
    }

    @Override
    public void setClipAmmo(final int clipAmmo) {
        this.clipAmmo = clipAmmo;
    }
}
