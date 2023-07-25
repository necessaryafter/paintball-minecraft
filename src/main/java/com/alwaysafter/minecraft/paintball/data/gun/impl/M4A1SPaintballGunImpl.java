package com.alwaysafter.minecraft.paintball.data.gun.impl;

import com.alwaysafter.minecraft.paintball.PaintballConstants;
import com.alwaysafter.minecraft.paintball.data.gun.PaintballGun;
import com.alwaysafter.minecraft.paintball.util.ItemBuilder;
import lombok.RequiredArgsConstructor;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public final class M4A1SPaintballGunImpl implements PaintballGun {

    private int clipAmmo = 30;

    @Override
    public String getGunName() {
        return "M4A1";
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
        return ItemBuilder.of(Material.DIAMOND_HOE)
                .displayName(getGunName())
                .lore(
                        "§7Damage: §f" + getDamage(),
                        "§7Max Ammo: §f" + getMaximumAmmo(),
                        "",
                        "§7This gun is originally from",
                        "§7Counter Terrorist Team."
                )
                .nbt(PaintballConstants.GUN_NBT_TAG_KEY, getGunName())
                .build();
    }

    @Override
    public void setClipAmmo(final int clipAmmo) {
        this.clipAmmo = clipAmmo;
    }
}
