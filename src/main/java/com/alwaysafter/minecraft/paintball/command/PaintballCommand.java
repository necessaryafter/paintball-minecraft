package com.alwaysafter.minecraft.paintball.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.alwaysafter.minecraft.paintball.PaintballPlugin;
import com.alwaysafter.minecraft.paintball.controller.PaintballController;
import com.alwaysafter.minecraft.paintball.strategy.PaintballAddUserStrategy;
import com.alwaysafter.minecraft.paintball.task.PaintballPhaseTask;
import org.bukkit.entity.Player;

@CommandAlias("paintball|csgo|valorant")
public final class PaintballCommand extends BaseCommand {

    @Default
    public void handlePaintballCommand(Player player) {
        final PaintballPlugin paintballPlugin = PaintballPlugin.getInstance();
        if(!paintballPlugin.getPaintballController().hasGameRunning()) {
            player.sendMessage("§cNenhuma partida iniciada.");
            return;
        }

        final PaintballAddUserStrategy strategy = new PaintballAddUserStrategy(
                paintballPlugin.getPaintballController().getPaintballGame());

        if(paintballPlugin.getPaintballUserCache().exists(player.getName())) {
            player.sendMessage("§c nao vai rolar");
            return;
        }

        strategy.addPaintballUser(player);
        player.sendMessage("§anetrok wdakowdopawkd");
    }

    @Subcommand("start|iniciar")
    @CommandPermission("paintball.admin")
    public void handlePaintballStartCommand(Player player) {
        final PaintballPlugin paintballPlugin = PaintballPlugin.getInstance();
        final PaintballController paintballController = paintballPlugin.getPaintballController();

        if(paintballController.hasGameRunning()) {
           player.sendMessage("§cUma partida está em andamento.");
           return;
        }

        paintballController.startGame();
        player.sendMessage("§ePartida iniciando.");

        new PaintballPhaseTask().runTask(PaintballPlugin.getInstance());
    }


}
