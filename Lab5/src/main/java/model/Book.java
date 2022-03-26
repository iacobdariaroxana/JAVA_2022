package model;

public class Book extends Item {
    public Book(){

    }
    public Book(String id, String title, String location, LocationType type) {
        super(id, title, location, type);
    }
}
