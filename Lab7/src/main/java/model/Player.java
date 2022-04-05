package model;

import controller.Game;

import java.util.List;

public class Player implements Runnable {
    private String name;
    private Game game;
    private boolean running = true;
    private int score;

    public Player(String name) {
        this.name = name;
        score = 0;
    }

    private void computeScore(List<Tile> extractedTiles) {
        for (Tile tile : extractedTiles) {
            score += tile.getPoints();
        }
    }

    private boolean submitWord() {
        List<Tile> extractedTiles = game.getBag().extractTiles(7);
        computeScore(extractedTiles);
        StringBuilder word = new StringBuilder();
        for (Tile tile : extractedTiles) {
            word.append(tile.getLetter());
        }

        if (extractedTiles.isEmpty()) return false;
        game.getBoard().addWord(this, word.toString());

        if (extractedTiles.size() < 7) {
            return false;
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void run() {
        while (running) {
            if (!submitWord()) {
                running = false;
            }
        }
        game.updateCount();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", game=" + game +
                ", running=" + running +
                ", score=" + score +
                '}';
    }
}
