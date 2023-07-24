package com.alwaysafter.minecraft.paintball;

import com.alwaysafter.minecraft.paintball.data.team.PaintballTeam;
import com.alwaysafter.minecraft.paintball.data.team.impl.CounterTerroristTeamImpl;
import com.alwaysafter.minecraft.paintball.data.team.type.PaintballTeamType;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.concurrent.ThreadLocalRandom;

public final class PaintballConstants {

    public static final String GUN_NBT_TAG_KEY = "paintball-gun";

    public static final int MAXIMUM_MEMBERS_PER_TEAM = 5;
    public static final int MINIMUM_MEMBERS_TO_START = 2;

    public static final Location COUNTER_TERRORIST_LOCATION = new Location(
            Bukkit.getWorld("world"),
            0, 5,  0
    );

    public static final Location TERRORIST_LOCATION = new Location(
            Bukkit.getWorld("world"),
            10, 5,  10
    );

    public static Location getLocationByTeam(PaintballTeam paintballTeam) {
        final Location location = paintballTeam.getTeamType() == PaintballTeamType.TERRORIST ?
                TERRORIST_LOCATION : COUNTER_TERRORIST_LOCATION;

        final ThreadLocalRandom current = ThreadLocalRandom.current();
        final double x = current.nextDouble(5);
        final double z = current.nextDouble(5);

        return location.clone().add(x, 0.5, z);
    }

}
