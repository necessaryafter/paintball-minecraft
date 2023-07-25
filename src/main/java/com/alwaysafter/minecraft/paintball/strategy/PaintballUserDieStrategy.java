package com.alwaysafter.minecraft.paintball.strategy;

import com.alwaysafter.minecraft.paintball.data.PaintballGame;
import com.alwaysafter.minecraft.paintball.data.team.PaintballTeam;
import com.alwaysafter.minecraft.paintball.data.user.PaintballUser;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public final class PaintballUserDieStrategy {

    private final PaintballGame paintballGame;

    public void handle(PaintballUser user, PaintballUser killer) {
        final PaintballTeam paintballTeam = user.getTeam();
        paintballTeam.getAliveUsers().remove(user);

        this.paintballGame.broadcast(
                user.getDisplayName() + " §7died by " + killer.getDisplayName() + ". §8(AK-47)",
                "§7Remain " + paintballTeam.getAliveUsers().size() + " players in " + paintballTeam.getTeamName()
        );

        final Player playerExact = Bukkit.getPlayerExact(user.getPlayerName());
        if(playerExact == null) return;

        playerExact.setGameMode(GameMode.SPECTATOR);

        new PaintballRoundEndStrategy(paintballGame).advanceRound();
    }

}
