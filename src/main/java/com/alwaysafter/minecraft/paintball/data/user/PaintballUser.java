package com.alwaysafter.minecraft.paintball.data.user;

import com.alwaysafter.minecraft.paintball.data.gun.PaintballGun;
import com.alwaysafter.minecraft.paintball.data.team.PaintballTeam;
import lombok.Data;
import lombok.NonNull;
import org.bukkit.entity.Player;

@Data
public final class PaintballUser {

    private final String playerName;
    private final PaintballStats stats;

    private final PaintballTeam team;
    private final PaintballGun gun;

    public static PaintballUser of(@NonNull Player player, @NonNull PaintballTeam paintballTeam) {
        return new PaintballUser(
                player.getName(), PaintballStats.of(), paintballTeam, paintballTeam.getDefaultGun());
    }

    public String getDisplayName() {
        return team.getTeamColor() + playerName;
    }

}
