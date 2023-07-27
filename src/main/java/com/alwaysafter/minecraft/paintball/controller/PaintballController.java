package com.alwaysafter.minecraft.paintball.controller;


import com.alwaysafter.minecraft.paintball.data.PaintballGame;
import com.alwaysafter.minecraft.paintball.data.phase.PaintballRoundPhase;
import lombok.Getter;

@Getter
public final class PaintballController {

    private PaintballGame paintballGame = null;

    public void startGame() {
        if(this.paintballGame != null) return;

        this.paintballGame = new PaintballGame();
        this.paintballGame.setRoundPhase(PaintballRoundPhase.WAITING);
    }

    public void endGame() {
        if(this.paintballGame == null) return;

        this.paintballGame = null;
    }

    public boolean hasGameRunning() {
        return this.paintballGame != null;
    }

    public boolean hasGameStarted() {
        return this.paintballGame.getRoundPhase() == PaintballRoundPhase.STARTED;
    }

}
