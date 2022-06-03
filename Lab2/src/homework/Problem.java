package homework;

import compulsory.Event;

/**
 * The Problem class defines an instance of the problem
 */
public class Problem {
    private Event[] events;
    private Room[] rooms;
    private int currentNumberOfEvents;
    private int currentNumberOfRooms;

    /**
     * Parameterized constructor
     *
     * @param capacityOfEvents the number of events
     * @param capacityOfRooms  the number of rooms
     */
    public Problem(int capacityOfEvents, int capacityOfRooms) {
        currentNumberOfEvents = currentNumberOfRooms = 0;
        events = new Event[capacityOfEvents];
        rooms = new Room[capacityOfRooms];
    }

    /**
     * A method for adding an event to the problem
     *
     * @param e the Event to be inserted
     */
    public void addEvent(Event e) {
        for (int i = 0; i < currentNumberOfEvents; i++) {
            if (events[i].equals(e)) {
                System.err.println("Event: " + e.getName() + " already exists!");
                System.exit(-1);
            }
        }
        events[currentNumberOfEvents++] = e;
    }

    /**
     * A method for adding a room to the problem
     *
     * @param r the Room to be inserted
     */
    public void addRoom(Room r) {
        for (int i = 0; i < currentNumberOfRooms; i++) {
            if (rooms[i].equals(r)) {
                System.err.println("Room: " + r.getName() + " already exists!");
                System.exit(-1);
            }
        }
        rooms[currentNumberOfRooms++] = r;
    }

    /**
     * A method for getting the events array
     *
     * @return returns a reference to the events[] array
     */
    public Event[] getEvents() {
        return events;
    }

    /**
     * A method for getting the rooms array
     *
     * @return returns a reference to the rooms[] array
     */
    public Room[] getRooms() {
        return rooms;
    }
}

