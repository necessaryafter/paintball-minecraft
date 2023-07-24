package com.alwaysafter.minecraft.paintball.strategy;

import com.alwaysafter.minecraft.paintball.PaintballConstants;
import com.alwaysafter.minecraft.paintball.data.PaintballGame;
import com.alwaysafter.minecraft.paintball.data.phase.PaintballRoundPhase;
import com.alwaysafter.minecraft.paintball.data.team.PaintballTeam;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;

@RequiredArgsConstructor
public final class PaintballRoundEndStrategy {

    private final PaintballGame paintballGame;

    public void advanceRound() {
        if(!this.paintballGame.hasDiedAll()) return;

        final PaintballTeam aliveTeam = this.paintballGame.getAliveTeam();
        this.paintballGame.setRound(this.paintballGame.getRound() + 1);

        aliveTeam.setWonRounds(aliveTeam.getWonRounds() + 1);

        this.paintballGame.getPaintballTeams().forEach(paintballTeam -> paintballTeam.getPaintballUsers().stream()
                .map(user -> Bukkit.getPlayerExact(user.getPlayerName()))
                .forEach(player -> {
                    paintballTeam.getAliveUsers().clear();

                    final String teamName = aliveTeam.getTeamName();
                    final String teamNameFormatted = String.format("%s§l %s", aliveTeam.getTeamColor(), teamName);

                    player.sendTitle(
                            teamNameFormatted,
                            "§7won the round! §8(" + aliveTeam.getWonRounds() + "/8)"
                    );

                    player.sendMessage(new String[] {
                            "",
                            "   " + teamNameFormatted,
                            "§7 " + teamName + " won this round!",
                            "",
                            "§7§o Teleporting others players to",
                            "§7§o team's default base.",
                            ""
                    });

                    this.paintballGame.setRoundPhase(PaintballRoundPhase.COUNTING);
                    player.teleport(PaintballConstants.getLocationByTeam(paintballTeam));
                }));
    }

}
