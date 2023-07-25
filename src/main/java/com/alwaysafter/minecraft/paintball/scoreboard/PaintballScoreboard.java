package com.alwaysafter.minecraft.paintball.scoreboard;

import com.alwaysafter.minecraft.paintball.PaintballPlugin;
import com.alwaysafter.minecraft.paintball.data.PaintballGame;
import com.alwaysafter.minecraft.paintball.data.user.PaintballUser;
import com.google.common.collect.Maps;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Map;

public final class PaintballScoreboard {

    private final Map<String, FastBoard> boardMap = Maps.newHashMap();

    public PaintballScoreboard() {
        final BukkitScheduler bukkitScheduler = Bukkit.getScheduler();
        final PaintballPlugin paintballPlugin = PaintballPlugin.getInstance();
        bukkitScheduler.runTaskTimerAsynchronously(paintballPlugin, () -> {

            for (final PaintballUser paintballUser : paintballPlugin.getPaintballUserCache().values()) {
                final Player player = Bukkit.getPlayer(paintballUser.getPlayerName());
                if(player == null) continue;

                this.handle(player, paintballUser);
            }

        }, 20, 20);
    }

    public void handle(Player player, PaintballUser user) {
        final PaintballPlugin paintballPlugin = PaintballPlugin.getInstance();
        final FastBoard fastBoard = this.boardMap.computeIfAbsent(player.getName(), $ -> new FastBoard(player));

        final PaintballGame paintballGame = paintballPlugin.getPaintballController().getPaintballGame();
        final int alivePlayers = paintballGame
                .getPaintballTeams()
                .stream().mapToInt(value -> value.getAliveUsers().size())
                .sum();

        fastBoard.updateTitle("§9§lPAINTBALL!");
        fastBoard.updateLines(
                " §8 Round " + paintballGame.getRound() + "/8",
                "",
                "§f Your team",
                "§7 " + user.getTeam().getTeamName(),
                "",
                "§f Your ammo",
                "§7 " + user.getGun().getClipAmmo() + "/" + user.getGun().getMaximumAmmo(),
                "",
                "§f Players alive",
                "§7 " + alivePlayers,
                "",
                "§epaintball.com.br"
        );
    }

}
