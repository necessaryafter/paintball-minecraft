package com.alwaysafter.minecraft.paintball.strategy;

import com.alwaysafter.minecraft.paintball.PaintballPlugin;
import com.alwaysafter.minecraft.paintball.data.PaintballGame;
import com.alwaysafter.minecraft.paintball.data.team.PaintballTeam;
import com.alwaysafter.minecraft.paintball.data.user.PaintballUser;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import java.util.Comparator;

@RequiredArgsConstructor
public final class PaintballAddUserStrategy {

    private final PaintballGame paintballGame;

    public void addPaintballUser(@NonNull Player player) {
        final PaintballTeam team = this.paintballGame.getPaintballTeams()
                .stream()
                .min(Comparator.comparing(paintballTeam -> paintballTeam.getPaintballUsers().size()))
                .orElse(null);

        if(team == null) return;

        this.addPaintballUser(player, team);
    }

    public void addPaintballUser(@NonNull Player player, @NonNull PaintballTeam paintballTeam) {
        final PaintballUser paintballUser = PaintballUser.of(player, paintballTeam);

        PaintballPlugin.getInstance()
                .getPaintballUserCache()
                .putPaintballUser(paintballUser);

        paintballTeam.getPaintballUsers().add(paintballUser);

        PaintballPlugin.getInstance()
                .getPaintballScoreboard()
                .handle(player, paintballUser);
    }

}
