package com.alwaysafter.minecraft.paintball;

import com.alwaysafter.minecraft.paintball.data.team.PaintballTeam;
import com.alwaysafter.minecraft.paintball.data.team.impl.CounterTerroristTeamImpl;
import com.alwaysafter.minecraft.paintball.data.team.type.PaintballTeamType;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.concurrent.ThreadLocalRandom;

public final class PaintballConstants {

    public static final String GUN_NBT_TAG_KEY = "paintball-gun";
    public static final String GUN_PROJECTILE_METADATA_KEY = "paintball-projectile";

    public static final int MAXIMUM_PAINTBALL_ROUNDS = 15;
    public static final int MAXIMUM_MEMBERS_PER_TEAM = 5;
    public static final int MINIMUM_MEMBERS_TO_START = 2;

    public static final Location COUNTER_TERRORIST_LOCATION = new Location(
            Bukkit.getWorld("world"),
            -123, 4,  -221
    );

    public static final Location TERRORIST_LOCATION = new Location(
            Bukkit.getWorld("world"),
            -154, 12,  -140
    );

    public static Location getLocationByTeam(PaintballTeam paintballTeam) {
        final Location location = paintballTeam.getTeamType() == PaintballTeamType.TERRORIST ?
                TERRORIST_LOCATION : COUNTER_TERRORIST_LOCATION;

        final ThreadLocalRandom current = ThreadLocalRandom.current();
        final double x = current.nextDouble(2);
        final double z = current.nextDouble(2);

        return location.clone().add(x, 0.5, z);
    }

}
