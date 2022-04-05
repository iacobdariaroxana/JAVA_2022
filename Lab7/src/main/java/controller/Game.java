package controller;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Bag bag = new Bag();
    private final Board board = new Board();
    private final Dictionary dictionary = new MockDictionary();
    private final List<Player> players = new ArrayList<>();
    private Player winner;
    private int count = 0;

    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }

    public void play() {
        for (Player player : players) {
            Thread thread = new Thread(player);
            thread.start();
        }
    }

    public Bag getBag() {
        return bag;
    }

    public Board getBoard() {
        return board;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void findWinner() {
        while (count != players.size()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int maximumScore = 0;
        for (Player player : players) {
            if (player.getScore() > maximumScore) {
                maximumScore = player.getScore();
                winner = player;
            }
        }
        System.out.println("Winner is " + winner.getName() + " (score: " + winner.getScore()  + ") 🎈🎉❤️");
    }

    public void updateCount() {
        synchronized(this){
            count++;
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.addPlayer(new Player("John"));
        game.addPlayer(new Player("Jennifer"));
        game.addPlayer(new Player("William"));
        game.play();
        game.findWinner();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
