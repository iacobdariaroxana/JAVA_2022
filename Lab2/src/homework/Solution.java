package homework;

import compulsory.Event;

/**
 * The Solution class describes a possible way of assigning rooms to events in an optimal way
 */
public class Solution {
    private Room[] assignment; // assignment[i] = what room received the event with index i
    private Event[] events;

    /**
     * Parameterized constructor
     * @param assignment reference to an array of objects of type Room
     * @param events reference to an array of objects of type Event
     */
    public Solution(Room[] assignment, Event[] events){
        this.assignment = assignment;
        this.events = events;
    }

    /**
     * A method which calculates the number of used rooms
     * @return returns the number of different rooms used for the assigning problem
     */
    public int computeUsedRooms(){
        int count = 1;
        for(int i = 1; i < assignment.length; i++){
            boolean flag = false;
            for( int j = 0; j < i && !flag; j++){
                if(assignment[j].equals(assignment[i])){
                    flag = true;
                }
            }
            if(!flag){
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sol = new StringBuilder();
        for(int i = 0; i < assignment.length; i++){
            sol.append("\n").append(events[i].getName()).append(" -> ").append(assignment[i].getName());
        }
        return "Solution:" + sol;
    }
}
