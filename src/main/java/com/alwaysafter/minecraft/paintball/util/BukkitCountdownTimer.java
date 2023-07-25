package com.alwaysafter.minecraft.paintball.util;

import lombok.AllArgsConstructor;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.function.IntConsumer;

@AllArgsConstructor(staticName = "of")
public final class BukkitCountdownTimer extends BukkitRunnable {

    private int counter;
    private final IntConsumer consumer;
    private final Runnable completionAction;

    @Override
    public void run() {
        if(--counter <= 0) {
            this.completionAction.run();
            this.cancel();

            return;
        }

        this.consumer.accept(counter);
    }
}
