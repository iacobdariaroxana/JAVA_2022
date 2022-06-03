package lab.model;

import java.util.Comparator;

public class Vertex implements Comparable<Vertex> {
    private int index;
    private double probability;

    public Vertex() {
    }

    public Vertex(int index, double probability) {
        this.setIndex(index);
        this.setProbability(probability);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    @Override
    public int compareTo(Vertex v) {
        return Double.compare(this.getProbability(), v.getProbability());
    }
}
