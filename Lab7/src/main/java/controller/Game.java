package controller;

import com.github.javafaker.Faker;
import model.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Game {
    private final int numberOfPlayers;
    private final Bag bag = new Bag();
    private final Board board = new Board();
    private final Dictionary dictionary = new MockDictionary();
    private final List<Player> players = new ArrayList<>();
    private Thread timeThread;
    private Player winner;
    private int count = 0;
    private int whichPlayer = 0;
    private boolean interrupted;

    public Game(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        interrupted = false;
    }

    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }

    public void addTimeKeeper(TimeKeeper timeKeeper) {
        timeThread = new Thread(timeKeeper);
        timeThread.setPriority(Thread.MAX_PRIORITY);
    }

    public void play() {
        for (Player player : players) {
            Thread thread = new Thread(player);
            thread.start();
        }
        timeThread.start();
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
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                System.out.println(exception.getMessage());
            }
        }
        if (interrupted) {
            System.err.println("The game ended because it exceeded the time limit!");
        }
        int maximumScore = 0;
        for (Player player : players) {
            if (player.getScore() > maximumScore) {
                maximumScore = player.getScore();
                winner = player;
            }
        }

        int ranking = 1;
        System.out.println("Final ranking:\n" + ranking + ". " + winner.getName() + " score: " + winner.getScore() + " (winner 🎈️)");
        ranking++;
        players.sort(Comparator.comparingInt(Player::getScore).reversed());
        for (Player player : players) {
            if (player != winner) {
                System.out.println(ranking + ". " + player.getName() + " score: " + player.getScore());
                ranking++;
            }
        }
    }

    public void updateCount() {
        synchronized (this) {
            count++;
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getWhichPlayer() {
        return whichPlayer;
    }

    public void setWhichPlayer(int whichPlayer) {
        this.whichPlayer = whichPlayer;
    }

    public void switchToNextPlayer() {
        if (whichPlayer == (getNumberOfPlayers() - 1)) {
            whichPlayer = 0;
        } else {
            whichPlayer++;
        }
    }

    public Thread getTimeThread() {
        return timeThread;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void interruptGame() {
        interrupted = true;
    }

    public static void main(String[] args) {
        Faker faker = new Faker();
        System.out.println("Enter the number of players:");
        Scanner scanner = new Scanner(System.in);
        int numberOfPlayers = scanner.nextInt();
        System.out.println("Enter the duration of game (minutes):");
        int duration = scanner.nextInt();
        Game game = new Game(numberOfPlayers);
        TimeKeeper tk = new TimeKeeper(duration * 60, game);
        for (int i = 0; i < numberOfPlayers; i++)
            game.addPlayer(new Player(faker.funnyName().name(), i));

        game.addTimeKeeper(tk);
        game.play();
        game.findWinner();
    }


}
