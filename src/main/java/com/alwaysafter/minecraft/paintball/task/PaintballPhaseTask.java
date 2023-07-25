package com.alwaysafter.minecraft.paintball.task;

import com.alwaysafter.minecraft.paintball.PaintballConstants;
import com.alwaysafter.minecraft.paintball.PaintballPlugin;
import com.alwaysafter.minecraft.paintball.controller.PaintballController;
import com.alwaysafter.minecraft.paintball.data.PaintballGame;
import com.alwaysafter.minecraft.paintball.data.phase.PaintballRoundPhase;
import com.alwaysafter.minecraft.paintball.strategy.PaintballRoundStartStrategy;
import com.alwaysafter.minecraft.paintball.util.BukkitCountdownTimer;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public final class PaintballPhaseTask extends BukkitRunnable {

    @Override
    public void run() {
        final PaintballPlugin paintballPlugin = PaintballPlugin.getInstance();
        final PaintballController paintballController = paintballPlugin.getPaintballController();

        if(!paintballController.hasGameRunning()) {
            System.out.println("parou aq fdp");
            this.cancel();
            return;

        }
        final PaintballGame paintballGame = paintballController.getPaintballGame();
        System.out.println("AI XERECA");

        /*final int totalPlayers = paintballGame.getPaintballTeams().stream()
                .mapToInt(paintballTeam -> paintballTeam.getAliveUsers().size())
                .sum();

        if(totalPlayers < PaintballConstants.MINIMUM_MEMBERS_TO_START) {
            return;
        }*
         */

        BukkitCountdownTimer.of(
                10,
                value -> Bukkit.broadcastMessage("§c ahjdiawjdoiawdj inciiando em " + value),
                () -> {
                    paintballGame.getPaintballTeams().forEach(paintballTeam -> paintballTeam.getPaintballUsers().stream()
                            .map(user -> Bukkit.getPlayerExact(user.getPlayerName()))
                            .forEach(player -> {
                                paintballGame.setRoundPhase(PaintballRoundPhase.COUNTING);
                                player.teleport(PaintballConstants.getLocationByTeam(paintballTeam));
                            }));

                    new PaintballRoundStartStrategy(paintballGame).startRound();
                    cancel();
                }).runTaskTimerAsynchronously(paintballPlugin, 20L, 20L);
    }
}
