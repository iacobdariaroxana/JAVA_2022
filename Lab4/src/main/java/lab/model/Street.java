package lab.model;

import java.util.Objects;

public class Street implements Comparable<Street> {
    private String name;
    private int length;
    private Intersection firstIntersection;
    private Intersection secondIntersection;
    private int firstNode;
    private int secondNode;

    public Street(String name, int length) {
        this.setName(name);
        this.setLength(length);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public int compareTo(Street other) {
        if (other == null || other.getName() == null)
            throw new NullPointerException();
        return this.name.compareTo(other.getName());
    }

    @Override
    public String toString() {
        return "Street: " + name + ", cost: " + length;
    }

    public int getFirstNode() {
        return firstNode;
    }

    public void setFirstNode(int firstNode) {
        this.firstNode = firstNode;
    }

    public int getSecondNode() {
        return secondNode;
    }

    public void setSecondNode(int secondNode) {
        this.secondNode = secondNode;
    }

    public Intersection getFirstIntersection() {
        return firstIntersection;
    }

    public void setFirstIntersection(Intersection firstIntersection) {
        this.firstIntersection = firstIntersection;
    }

    public Intersection getSecondIntersection() {
        return secondIntersection;
    }

    public void setSecondIntersection(Intersection secondIntersection) {
        this.secondIntersection = secondIntersection;
    }
}
