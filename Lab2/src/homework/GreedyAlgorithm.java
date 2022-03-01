/**
 * @author Iacob Daria-Roxana
 * @since 2022
 */
package homework;

import compulsory.Event;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Class SortByCapacity implements Comparator interface for defining the compare method of two object of type Room
 */
class SortByCapacity implements Comparator<Room>{
    @Override
    public int compare(Room o1, Room o2) {
        return o1.getCapacity()- o2.getCapacity();
    }
}

/**
 * The GreedyAlgorithm class provides methods for finding a solution of the problem
 */
public class GreedyAlgorithm extends Algorithm{
    private Event[] events;
    private Room[] rooms;
    private Room[] assignment; // assignment[i] = what room receives the event with index i
    private int[][] hours; // hours[i] stores an array with startTime hour for the events that will take place in rooms[i]

    /**
     * Parameterized Constructor
     * @param p an instance of the problem
     */
    GreedyAlgorithm(Problem p){
        this.events = p.getEvents();
        this.rooms = p.getRooms();
        assignment = new Room[events.length];
        hours = new int[rooms.length][]; // hours[i] keeps the start time of the hours that will take place in rooms[i]
        for(int i = 0; i < rooms.length; i++){
            hours[i] = new int[0]; // initially rooms are not allocated
        }
    }

    /**
     * Checks if a room is available in an given interval
     * @param index the index of the room
     * @param startTime at what time the event should start
     * @param endTime at what time the event ends
     * @return returns true if rooms[index] is available for hosting the event, otherwise false
     */
    public boolean isRoomAvailable(int index, int startTime, int endTime){
        if(hours[index].length == 0) // no events were added to this room before
            return true;
        else{
            if((startTime+2) <= hours[index][0]) // if the event can be placed at the beginning of the array
                return true;
            for(int j = 0; j < hours[index].length-1; j++){
                if((hours[index][j]+2) <= startTime && endTime <= hours[index][j+1]) // verify if the event can be inserted between other two existing events( keeping into account that hours[index] is sorted
                    return true;
            }
            return (hours[index][hours[index].length-1]+2) <= startTime; // verify if the current is bigger than the endTime of the last event that will take place in rooms[index]
        }
    }

    /**
     * Adds a specific interval to a room to mark it at occupied in that period of time
     * @param index the index of the room in which an event should be inserted
     * @param startTime the start time of the event which will take place in rooms[index]
     */
    public void addInterval(int index, int startTime){
        int[] temporary = new int[hours[index].length+1];
        System.arraycopy(hours[index], 0, temporary, 0, hours[index].length);
        hours[index] = temporary;
        hours[index][hours[index].length-1] = startTime;
        Arrays.sort(hours[index]);
    }

    /**
     * A method for finding a room in which a specific event to take place
     * @param event the event who needs a room in which to take place
     * @param eventNumber the position of the event in the events[] array
     * @return returns true if an available room for hosting the event was found, otherwise returns false
     */
    public boolean findRoom(Event event, int eventNumber){
        for(int i = 0; i < rooms.length; i++){
            if((event.getNumberOfParticipants() <= rooms[i].getCapacity()) && isRoomAvailable(i, event.getStartTime(), event.getEndTime())){
                assignment[eventNumber] = rooms[i];
                addInterval(i, event.getStartTime());
                return true;
            }
        }
        return false;
    }

    /**
     * The method who offers a solution for the problem
     * @return returns an instance on Solution class in case it exists
     */
    public Solution solve(){
        Arrays.sort(rooms, new SortByCapacity());
        for(int i = 0; i < events.length; i++){
            if(!findRoom(events[i], i)){
                System.err.println("The Room Assignment Problem has no solution!");
                System.exit(-1);
            }
        }
        return new Solution(assignment, events);
    }
}
