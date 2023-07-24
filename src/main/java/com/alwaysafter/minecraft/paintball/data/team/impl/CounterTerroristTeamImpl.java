package com.alwaysafter.minecraft.paintball.data.team.impl;

import com.alwaysafter.minecraft.paintball.data.gun.PaintballGun;
import com.alwaysafter.minecraft.paintball.data.gun.impl.M4A1SPaintballGunImpl;
import com.alwaysafter.minecraft.paintball.data.team.PaintballTeam;
import com.alwaysafter.minecraft.paintball.data.team.type.PaintballTeamType;
import com.alwaysafter.minecraft.paintball.data.user.PaintballUser;
import com.google.common.collect.Lists;
import org.bukkit.ChatColor;

import java.util.List;

public final class CounterTerroristTeamImpl implements PaintballTeam {

    private final List<PaintballUser> paintballUsers = Lists.newArrayList(),
            diedUsers = Lists.newArrayList();
    private int wonRounds = 0;

    @Override
    public String getTeamName() {
        return "Counter Terrorist";
    }

    @Override
    public PaintballTeamType getTeamType() {
        return PaintballTeamType.COUNTER_TERRORIST;
    }

    @Override
    public ChatColor getTeamColor() {
        return ChatColor.BLUE;
    }

    @Override
    public int getWonRounds() {
        return wonRounds;
    }

    @Override
    public PaintballGun getDefaultGun() {
        return new M4A1SPaintballGunImpl();
    }

    @Override
    public List<PaintballUser> getPaintballUsers() {
        return paintballUsers;
    }

    @Override
    public List<PaintballUser> getAliveUsers() {
        return diedUsers;
    }

    @Override
    public void setWonRounds(final int rounds) {
        this.wonRounds = rounds;
    }
}
