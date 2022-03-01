/**
 * @author Iacob Daria-Roxana
 * @since 2022
 */
package homework;
/**
 * The Room class defines a model of a room
 */
public abstract class Room {
    /**
     * member for storing the name of a Room
     */
    protected String name;
    /**
     * member for storing the maximum capacity of a Room
     */
    protected int capacity;

    /**
     * Parameterized constructor
     * @param name Room name
     * @param capacity Room capacity
     */
    public Room(String name, int capacity){
        this.setName(name);
        this.setCapacity(capacity);
    }

    /**
     *
     * @return returns the name of the room
     */
    public String getName() {
        return name;
    }

    /**
     * Method for setting a name to a room
     * @param name Room name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return returns the capacity of the room
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Method for setting a capacity to a room
     * @param capacity Room capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null || !(obj instanceof Room)){
            return false;
        }
        Room other = (Room) obj;
        return name.equals(other.name);
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
