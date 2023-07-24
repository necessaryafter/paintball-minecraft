package com.alwaysafter.minecraft.paintball.cache;

import com.alwaysafter.minecraft.paintball.data.user.PaintballUser;
import com.google.common.collect.Maps;
import lombok.NonNull;

import java.util.Map;

public final class PaintballUserCache {

    private final Map<String, PaintballUser> paintballUserMap = Maps.newHashMap();

    public PaintballUser getPaintballUser(@NonNull String playerName) {
        return this.paintballUserMap.get(playerName);
    }

    public void putPaintballUser(@NonNull PaintballUser user) {
        this.paintballUserMap.putIfAbsent(user.getPlayerName(), user);
    }

    public void removePaintballUser(@NonNull PaintballUser user) {
        this.paintballUserMap.remove(user.getPlayerName());
    }

}
