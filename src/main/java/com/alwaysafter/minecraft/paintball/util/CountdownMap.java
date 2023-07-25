package com.alwaysafter.minecraft.paintball.util;

import com.google.common.collect.Maps;
import lombok.Data;
import lombok.NonNull;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Data(staticConstructor = "of")
public final class CountdownMap<T> {

    private final TimeUnit timeUnit;
    private final Map<T, Instant> instantMap = Maps.newHashMap();
    private T var;

    public void insert(@NonNull T var, long delay) {
        instantMap.put(var, Instant.now().plusMillis(timeUnit.toMillis(delay)));
    }

    public void clearAll() {
        instantMap.clear();
    }

    public void remove(@NonNull T var) {
        instantMap.remove(var);
    }

    public long getRemainingTime(@NonNull T var) {
        final Instant instant = instantMap.get(var);
        if (instant == null) {
            return 0L;
        }

        return isInCountdown(var) ? (instant.toEpochMilli() - System.currentTimeMillis()) : 0L;
    }

    public boolean isInCountdown(@NonNull T var) {
        final Instant instant = instantMap.get(var);
        return instant != null && instant.isAfter(Instant.now());
    }
}
