package bonus;

import homework.Room;

public class Problem {
    private Event[] events;
    private Room[] rooms;
    private int numberOfEvents;
    private int numberOfRooms;

    Problem(int capacityOfEvents, int capacityOfRooms) {
        numberOfEvents = numberOfRooms = 0;
        events = new Event[capacityOfEvents];
        rooms = new Room[capacityOfRooms];
    }

    public void addEvent(Event e) {
        for (int i = 0; i < numberOfEvents; i++) {
            if (events[i].equals(e)) {
                System.err.println("Event: " + e.getName() + " already exists!");
                System.exit(-1);
            }
        }
        events[numberOfEvents++] = e;
    }

    public void addRoom(Room r) {
        for (int i = 0; i < numberOfRooms; i++) {
            if (rooms[i].equals(r)) {
                System.err.println("Room: " + r.getName() + " already exists!");
                System.exit(-1);
            }
        }
        rooms[numberOfRooms++] = r;
    }

    public int getNumberOfEvents() {
        return numberOfEvents;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    Event[] getEvents() {
        return events;
    }

    Room[] getRooms() {
        return rooms;
    }
}
