package com.alwaysafter.minecraft.paintball.strategy;


import com.alwaysafter.minecraft.paintball.PaintballPlugin;
import com.alwaysafter.minecraft.paintball.data.PaintballGame;
import com.alwaysafter.minecraft.paintball.data.phase.PaintballRoundPhase;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

@RequiredArgsConstructor
public final class PaintballRoundStartStrategy {

    private final PaintballGame paintballGame;

    public void startRound() {
        this.getCountingRunnable().runTaskTimerAsynchronously(
                PaintballPlugin.getInstance(),
                20, 20
        );
    }

    private BukkitRunnable getCountingRunnable() {
        return new BukkitRunnable() {

            private int seconds = 20;

            @Override
            public void run() {
                this.seconds--;

                switch (seconds) {
                    case 10: {
                        paintballGame.broadcast(
                                "",
                                "§a Round §l" + paintballGame.getRound() + "§a starting in §l" + seconds + "§a seconds!",
                                "§7 The team that wins 8 rounds will win the match! May the best team win.",
                                "",
                                "§8 Your objetive is to eliminate all",
                                "§8 players from opposing team.",
                                ""
                        );

                        break;
                    }

                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5: {
                        paintballGame.broadcast("§7Game starting in " + seconds + "seconds...");
                        break;
                    }

                    case 0: {
                        paintballGame.broadcast("§7The game has been started! May the best team win!");
                        break;
                    }

                    case -1: {
                        this.cancel();

                        paintballGame.setPaintballRoundTime(System.currentTimeMillis());
                        paintballGame.setRoundPhase(PaintballRoundPhase.STARTED);
                        break;
                    }
                }
            }
        };
    }

}
