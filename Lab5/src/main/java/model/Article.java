package model;

public class Article extends Item{
    public Article(){

    }
    public Article(String id, String title, String location, LocationType type) {
        super(id, title, location, type);
    }
}
