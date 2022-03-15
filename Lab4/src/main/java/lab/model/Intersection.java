package lab.model;

import java.util.Objects;

public class Intersection implements Comparable<Intersection> {
    private String name;

    public Intersection(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Intersection other) {
        if (other == null || other.getName() == null)
            throw new NullPointerException();
        return this.name.compareTo(other.getName());
    }

    @Override
    public String toString() {
        return "Intersection: " + name;
    }
}
