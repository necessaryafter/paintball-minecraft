package com.alwaysafter.minecraft.paintball.listener;

import com.alwaysafter.minecraft.paintball.PaintballPlugin;
import com.alwaysafter.minecraft.paintball.controller.PaintballController;
import com.alwaysafter.minecraft.paintball.data.PaintballGame;
import com.alwaysafter.minecraft.paintball.data.phase.PaintballRoundPhase;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    @EventHandler
    private void handlePlayerMovement(PlayerMoveEvent event) {
        if(compareLocation(event.getFrom(), event.getTo())) return;

        final PaintballController paintballController = PaintballPlugin.getInstance().getPaintballController();
        final PaintballGame paintballGame = paintballController.getPaintballGame();

        if(paintballGame.getRoundPhase() == PaintballRoundPhase.COUNTING) {

            event.setTo(event.getFrom());
            event.setCancelled(true);
        }
    }

    public boolean compareLocation(Location first, Location second) {
        return first.getWorld().getName().equalsIgnoreCase(second.getWorld().getName())
                && first.getBlockX() == second.getBlockX()
                && first.getBlockY() == second.getBlockY()
                && first.getBlockZ() == second.getBlockZ();
    }


}
