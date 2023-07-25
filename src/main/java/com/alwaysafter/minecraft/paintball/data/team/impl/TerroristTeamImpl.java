package com.alwaysafter.minecraft.paintball.data.team.impl;

import com.alwaysafter.minecraft.paintball.data.gun.PaintballGun;
import com.alwaysafter.minecraft.paintball.data.gun.impl.AK47PaintballGunImpl;
import com.alwaysafter.minecraft.paintball.data.team.PaintballTeam;
import com.alwaysafter.minecraft.paintball.data.team.type.PaintballTeamType;
import com.alwaysafter.minecraft.paintball.data.user.PaintballUser;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;

import java.util.List;

public final class TerroristTeamImpl implements PaintballTeam {

    private final List<PaintballUser> paintballUsers = Lists.newArrayList(),
            diedUsers = Lists.newArrayList();
    private int wonRounds = 0;

    @Override
    public String getTeamName() {
        return "Terrorist";
    }

    @Override
    public PaintballTeamType getTeamType() {
        return PaintballTeamType.TERRORIST;
    }

    @Override
    public ChatColor getTeamColor() {
        return ChatColor.RED;
    }

    @Override
    public int getWonRounds() {
        return wonRounds;
    }

    @Override
    public PaintballGun getDefaultGun() {
        return new AK47PaintballGunImpl();
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
