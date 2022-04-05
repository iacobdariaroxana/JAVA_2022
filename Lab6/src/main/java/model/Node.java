package model;

import java.io.Serializable;
import java.util.*;

public class Node implements Serializable {
    private int x;
    private int y;
    private Set<Node> neighbors;

    public Node() {

    }

    public Node(int x, int y) {
        this.setX(x);
        this.setY(y);
        setNeighbors(new HashSet<>());
    }

    public void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Set<Node> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Set<Node> neighbors) {
        this.neighbors = neighbors;
    }
}
