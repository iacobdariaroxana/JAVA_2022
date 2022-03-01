package compulsory;

public class Room {
    private String name; // name of a room
    private int capacity; // how many people it can store
    private RoomType type; // what type of room it is( lab or lecture hall)

    public Room(){

    }

    public Room(String name){
        this.name = name;
    }

    public Room(String name, int capacity){
        this(name);
        this.capacity = capacity;
    }

    public Room(String name, int capacity, RoomType type){
        this(name, capacity);
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", type=" + type +
                '}';
    }
}
