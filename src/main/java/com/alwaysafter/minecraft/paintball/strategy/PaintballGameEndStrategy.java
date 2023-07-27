package com.alwaysafter.minecraft.paintball.strategy;

import com.alwaysafter.minecraft.paintball.controller.PaintballController;
import com.alwaysafter.minecraft.paintball.data.PaintballGame;
import com.alwaysafter.minecraft.paintball.data.team.PaintballTeam;
import com.alwaysafter.minecraft.paintball.data.team.type.PaintballTeamType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public final class PaintballGameEndStrategy {

    private final PaintballController paintballController;

    private final PaintballTeam winnerPaintballTeam;

    public void handleGameEnd() {
        if(!paintballController.hasGameRunning()) return;

        final String teamDisplayName = winnerPaintballTeam.getTeamColor() + "§l" + winnerPaintballTeam.getTeamName();
        final PaintballTeam loserPaintballTeam = getOtherTeam(winnerPaintballTeam);

        for (final Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.sendMessage(new String[] {
                    "",
                    " §f§lCongratulations, " + teamDisplayName + "!",
                    " §7" + winnerPaintballTeam.getTeamName() + "§f won the game",
                    " §7after a series of victories!",
                    "",
                    " §8Match Result: §f" + getScoreboardByTeam(winnerPaintballTeam) + " x " + getScoreboardByTeam(loserPaintballTeam),
                    ""
            });
        }

        this.paintballController.endGame();
    }

    private PaintballTeam getOtherTeam(PaintballTeam paintballTeam) {
        final PaintballGame paintballGame = paintballController.getPaintballGame();
        return paintballTeam.getTeamType() == PaintballTeamType.TERRORIST ?
                paintballGame.getPaintballTeam(PaintballTeamType.COUNTER_TERRORIST) :
                paintballGame.getPaintballTeam(PaintballTeamType.TERRORIST);
    }

    private String getScoreboardByTeam(@NonNull PaintballTeam paintballTeam) {
        final String shortName = paintballTeam.getShortName();
        final int wonRounds = paintballTeam.getWonRounds();

        return shortName + " " + wonRounds;
    }



}
