package homework;
import compulsory.Event;

/**
 * The main class of the problem
 */
public class Main {
    /**
     * main method of the class
     * @param args for parsing command line arguments
     */
    public static void main(String[] args){
        Problem p = new Problem(7, 4);
        Event e1 = new Event("C1", 100, 8, 10);
        Event e2 = new Event("C2", 100, 10, 12);
        Event e3 = new Event("L1", 30, 8, 10);
        Event e4 = new Event("L2", 30, 8, 10);
        Event e5 = new Event("L3", 30, 10, 12);
        Event e6 = new Event("C3", 100, 14, 16);
        Event e7 = new Event("C4", 100, 12, 14);
        p.addEvent(e1);
        p.addEvent(e2);
        p.addEvent(e3);
        p.addEvent(e4);
        p.addEvent(e5);
        p.addEvent(e6);
        p.addEvent(e7);

        Room r1 = new ComputerLab("401", 30, "Windows 10");
        Room r2 = new ComputerLab("403", 30, "Ubuntu");
        Room r3 = new ComputerLab("405", 30, "Windows 11");
        Room r4 = new LectureHall("309", 100, true);
        p.addRoom(r1);
        p.addRoom(r2);
        p.addRoom(r3);
        p.addRoom(r4);

        Algorithm greedy = new GreedyAlgorithm(p);
        Solution sol = greedy.solve(); // solve() method returns a reference to an object which describes a possible solution to the problem
        System.out.println(sol); // automatically calls toString() method
        System.out.println("Number of used rooms: " + sol.computeUsedRooms());

    }
}
