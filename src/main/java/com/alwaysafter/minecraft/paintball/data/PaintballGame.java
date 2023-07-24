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

    private PaintballRoundPhase roundPhase = null;
    private long paintballRoundTime;
    private int round = 0;

    public int remainingRounds() {
        return this.maximumRounds - this.round;
    }

    public boolean hasDiedAll() {
        return this.paintballTeams.stream()
                .noneMatch(paintballTeam -> paintballTeam.getAliveUsers().size() < 5);
    }

    public PaintballTeam getAliveTeam() {
        return this.paintballTeams.stream()
                .filter(paintballTeam -> paintballTeam.getAliveUsers().size() < 5)
                .findFirst().orElse(null);
    }

    public void broadcast(String... message) {
        for (final PaintballTeam paintballTeam : this.paintballTeams) {
            for (final PaintballUser paintballUser : paintballTeam.getPaintballUsers()) {
                final Player playerExact = Bukkit.getPlayerExact(paintballUser.getPlayerName());
                if(playerExact == null) return;

                playerExact.sendMessage(message);
            }
        }
    }


}
