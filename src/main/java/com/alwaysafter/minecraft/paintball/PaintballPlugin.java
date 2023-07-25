package com.alwaysafter.minecraft.paintball;

import co.aikar.commands.PaperCommandManager;
import com.alwaysafter.minecraft.paintball.cache.PaintballUserCache;
import com.alwaysafter.minecraft.paintball.command.PaintballCommand;
import com.alwaysafter.minecraft.paintball.controller.PaintballController;
import com.alwaysafter.minecraft.paintball.listener.PaintballDamageListener;
import com.alwaysafter.minecraft.paintball.listener.PlayerMoveListener;
import com.alwaysafter.minecraft.paintball.scoreboard.PaintballScoreboard;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;

@Getter
public final class PaintballPlugin extends JavaPlugin {

    private PaintballController paintballController;
    private PaintballUserCache paintballUserCache;
    private PaintballScoreboard paintballScoreboard;

    @Override
    public void onEnable() {
        this.paintballController = new PaintballController();
        this.paintballUserCache = new PaintballUserCache();
        this.paintballScoreboard = new PaintballScoreboard();

        final PaperCommandManager paperCommandManager = new PaperCommandManager(this);
        paperCommandManager.getLocales().setDefaultLocale(new Locale("pt", "BR"));
        paperCommandManager.registerCommand(new PaintballCommand());

        this.registerListeners(new PaintballDamageListener(paintballUserCache), new PlayerMoveListener());
    }

    public static PaintballPlugin getInstance() {
        return getPlugin(PaintballPlugin.class);
    }

    private void registerListeners(Listener... listeners) {
        for (final Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(
                    listener,
                    this
            );
        }
    }
}
