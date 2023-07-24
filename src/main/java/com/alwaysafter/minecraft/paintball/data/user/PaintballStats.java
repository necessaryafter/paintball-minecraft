package com.alwaysafter.minecraft.paintball.data.user;

import lombok.Data;

@Data(staticConstructor = "of")
public final class PaintballStats {

    private int kills = 0;
    private int sequence = 0;

    private long lastKillMillis = -1L;

    public void incrementKills() {
        this.kills++;

        final long currentTimeMillis = System.currentTimeMillis();
        final long timeDiffInSeconds = Math.abs(lastKillMillis - currentTimeMillis) / 1000;

        if (timeDiffInSeconds < 10) {
            this.sequence++;
            return;
        }

        this.sequence = 0;
    }
}
