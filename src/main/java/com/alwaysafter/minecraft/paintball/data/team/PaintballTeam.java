package com.alwaysafter.minecraft.paintball.data.team;

import com.alwaysafter.minecraft.paintball.data.gun.PaintballGun;
import com.alwaysafter.minecraft.paintball.data.team.type.PaintballTeamType;
import com.alwaysafter.minecraft.paintball.data.user.PaintballUser;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.List;

public interface PaintballTeam {

    String getTeamName();

    String getShortName();

    PaintballTeamType getTeamType();

    ChatColor getTeamColor();

    PaintballGun getDefaultGun();

    List<PaintballUser> getPaintballUsers();

    List<PaintballUser> getAliveUsers();

    int getWonRounds();

    void setWonRounds(int rounds);

    default void broadcast(String... messages) {
        this.getPaintballUsers().stream()
                .map(user -> Bukkit.getPlayerExact(user.getPlayerName()))
                .forEach(player -> player.sendMessage(messages));
    }

}
