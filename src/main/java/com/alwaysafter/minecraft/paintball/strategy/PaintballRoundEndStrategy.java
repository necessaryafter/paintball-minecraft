package com.alwaysafter.minecraft.paintball.strategy;

import com.alwaysafter.minecraft.paintball.PaintballConstants;
import com.alwaysafter.minecraft.paintball.PaintballPlugin;
import com.alwaysafter.minecraft.paintball.data.PaintballGame;
import com.alwaysafter.minecraft.paintball.data.phase.PaintballRoundPhase;
import com.alwaysafter.minecraft.paintball.data.team.PaintballTeam;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

@RequiredArgsConstructor
public final class PaintballRoundEndStrategy {

    private final PaintballGame paintballGame;

    public void advanceRound() {
        if(!this.paintballGame.hasAnyAlive()) {
            return;
        }

        final PaintballTeam aliveTeam = this.paintballGame.getAliveTeam();
        this.paintballGame.setRound(this.paintballGame.getRound() + 1);

        aliveTeam.setWonRounds(aliveTeam.getWonRounds() + 1);

        if(aliveTeam.getWonRounds() >= 8 || paintballGame.getRound() >= PaintballConstants.MAXIMUM_PAINTBALL_ROUNDS) {
            new PaintballGameEndStrategy(PaintballPlugin.getInstance().getPaintballController(), aliveTeam)
                    .handleGameEnd();
            return;
        }


        new PaintballRoundStartStrategy(paintballGame).startRound();
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

                    for (final Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        final PlayerInventory inventory = onlinePlayer.getInventory();

                        inventory.clear();
                        inventory.setArmorContents(null);
                    }

                    player.setGameMode(GameMode.SURVIVAL);
                    player.teleport(PaintballConstants.getLocationByTeam(paintballTeam));

                }));
    }

}
