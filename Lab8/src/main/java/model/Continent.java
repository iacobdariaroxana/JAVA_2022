package model;

public class Continent {
    private int id;
    private String name;
    private long area;
    private long population;

    public Continent(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Continent(int id, String name, long area, long population) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.population = population;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getArea() {
        return area;
    }

    public void setArea(long area) {
        this.area = area;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "Continent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", area=" + area +
                ", population=" + population +
                '}';
    }
}
