package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private String name;
    private List<Item> items = new ArrayList<>();

    public Catalog() {

    }

    public Catalog(String name) {
        this.setName(name);
    }

    public void add(Item item) {
        if (findById(item.getId()) == null) {
            getItems().add(item);
        }
    }

    public Item findById(String id) {
        return getItems().stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "name='" + getName() + '\'' +
                ", items=" + getItems() +
                '}';
    }
}
