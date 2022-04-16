package controller;

import javax.swing.plaf.TableHeaderUI;

public class TimeKeeper implements Runnable {
    private Game game;
    private int duration;


    public TimeKeeper(int duration, Game game) {
        this.duration = duration;
        this.game = game;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        while ((System.currentTimeMillis() - start) < duration * 1000L) {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                System.err.println("Game remaining time: " + (duration - (System.currentTimeMillis() - start)/1000) + " seconds!");
            }
        }
        game.interruptGame();
    }
}
