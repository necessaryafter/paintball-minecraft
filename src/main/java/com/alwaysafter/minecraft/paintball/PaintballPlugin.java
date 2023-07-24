package com.alwaysafter.minecraft.paintball;

import com.alwaysafter.minecraft.paintball.cache.PaintballUserCache;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class PaintballPlugin extends JavaPlugin {

    private PaintballUserCache paintballUserCache;

    @Override
    public void onEnable() {
        this.paintballUserCache = new PaintballUserCache();
    }

    public static PaintballPlugin getInstance() {
        return getPlugin(PaintballPlugin.class);
    }
}
