package model;

import controller.Game;

import java.util.*;

public class Player implements Runnable {
    private String name;
    private Game game;
    private boolean running = true;
    private int score;
    private int turn;
    private Bag bag;
    private int previousWordLength;

    public Player(String name, int turn) {
        this.name = name;
        score = 0;
        this.setTurn(turn);
    }

    private void computeScore(List<Tile> extractedTiles, String word) {
        for (char c : word.toCharArray()) { //banana
            score += extractedTiles.stream().filter(tile -> tile.getLetter() == c).findFirst().get().getPoints() * word.length();
        }
    }

    private boolean verifyLetters(List<Tile> extractedTiles, String word) {
        Map<Character, Integer> availableLetters = new HashMap<>();
        for (Tile tile : extractedTiles) {
            if (availableLetters.containsKey(tile.getLetter()))
                availableLetters.put(tile.getLetter(), availableLetters.get(tile.getLetter()) + 1);
            else {
                availableLetters.put(tile.getLetter(), 1);
            }
        }

        String availableLettersString = availableLetters.keySet().toString();
        for (Character c : word.toCharArray()) {
            if (!availableLettersString.contains(c.toString())) {
                System.out.println("Letter " + c + " is not among extracted tiles!");
                return false;
            }
            if (availableLetters.containsKey(c)) {
                availableLetters.put(c, availableLetters.get(c) - 1);
                if (availableLetters.get(c) == 0) {
                    availableLetters.remove(c);
                }
            } else {
                System.out.println("You used letter " + c + " more times than allowed!");
                return false;
            }
        }
        return true;
    }

    private boolean submitWord() {
        synchronized (bag) {
            while (game.getWhichPlayer() != turn) {
                try {
                    bag.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (game.getTimeThread().isAlive()) {
                game.getTimeThread().interrupt();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException exception) {
                    System.err.println(exception.getMessage());
                }
            } else {
                game.switchToNextPlayer();
                bag.notifyAll();
                return false;
            }

            previousWordLength = 7;
            while (true) {
                List<Tile> extractedTiles = bag.extractTiles(previousWordLength);

                if (extractedTiles.isEmpty()) {
                    game.switchToNextPlayer();
                    bag.notifyAll();
                    return false;
                }

                System.out.print(name + ", your letters are: ");
                for (Tile tile : extractedTiles) System.out.print(tile.getLetter() + " ");
                System.out.println(". Please submit a word!");
                Scanner scanner = new Scanner(System.in);
                String word = scanner.nextLine();

                boolean correctLetters = verifyLetters(extractedTiles, word);

                if (correctLetters) {
                    if (game.getDictionary().isWord(word) && word.length() > 1) {
                        System.out.println("Word " + word + " was accepted!");
                        computeScore(extractedTiles, word);
                        game.getBoard().addWord(this, word);
                        previousWordLength = word.length();
                    } else {
                        System.out.println(word + " is not a valid word!");
                        break;
                    }
                } else {
                    break;
                }
            }
            game.switchToNextPlayer();
            bag.notifyAll();
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
        bag = game.getBag();
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

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
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
