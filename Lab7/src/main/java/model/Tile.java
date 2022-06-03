package model;

import java.util.Objects;

public class Tile {
    private char letter;
    private int points;

    @Test
    public Tile(){

    }

    public Tile(char letter, int points){
        this.setLetter(letter);
        this.setPoints(points);
    }

    public char getLetter() {
        return letter;
    }

    @Test
    public void setLetter(char letter) {
        this.letter = letter;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return letter == tile.letter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter);
    }

    @Override
    public String toString() {
        return "Tile{" +
                "letter=" + letter +
                ", points=" + points +
                '}';
    }
}
