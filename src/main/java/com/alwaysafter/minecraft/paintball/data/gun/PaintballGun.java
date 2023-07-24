package com.alwaysafter.minecraft.paintball.data.gun;


import org.bukkit.inventory.ItemStack;

public interface PaintballGun {

    String getGunName();

    int getMaximumAmmo();

    int getClipAmmo();

    int getReloadTime();

    double getDamage();

    ItemStack getItemStack();

    void setClipAmmo(int clipAmmo);

}
