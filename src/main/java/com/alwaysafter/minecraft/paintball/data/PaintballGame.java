package com.alwaysafter.minecraft.paintball.data;

import com.alwaysafter.minecraft.paintball.data.phase.PaintballRoundPhase;
import com.alwaysafter.minecraft.paintball.data.team.PaintballTeam;
import com.alwaysafter.minecraft.paintball.data.team.impl.CounterTerroristTeamImpl;
import com.alwaysafter.minecraft.paintball.data.team.impl.TerroristTeamImpl;
import com.alwaysafter.minecraft.paintball.data.user.PaintballUser;
import com.google.common.collect.ImmutableList;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

@RequiredArgsConstructor
@Data
public final class PaintballGame {

    private final int maximumRounds = 15;
    private final List<PaintballTeam> paintballTeams = ImmutableList.of(
            new CounterTerroristTeamImpl(),
            new TerroristTeamImpl()
    );

    private PaintballRoundPhase roundPhase = PaintballRoundPhase.WAITING;
    private long paintballRoundTime;
    private int round = 1;

    public int remainingRounds() {
        return this.maximumRounds - this.round;
    }

    public boolean hasAnyAlive() {
        return this.paintballTeams.stream().anyMatch(paintballTeam -> paintballTeam.getAliveUsers().size() >= 1);
    }

    public PaintballTeam getAliveTeam() {
        return this.paintballTeams.stream()
                .filter(paintballTeam -> paintballTeam.getAliveUsers().size() >= 1)
                .findFirst().orElse(null);
    }

    public void broadcast(String... messages) {
        for (final String message : messages) {
            Bukkit.broadcastMessage(message);
        }
    }


}
