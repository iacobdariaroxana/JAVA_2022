package bonus;

import java.util.Random;

import homework.*;

public class Main {
    private static Random rand = new Random();
    private static final int MAX = 400;

    public void generateRandomEvents(int numberOfEvents, Problem p1, homework.Problem p2) {
        int number = 0;
        for (int i = 0; i < numberOfEvents; i++) {
            int startTimeHour = rand.nextInt(8, 18);
            int endTimeHour = startTimeHour + 2;
            int numberOfParticipants;
            boolean course = rand.nextBoolean();
            StringBuilder name = new StringBuilder();
            if (course) {
                numberOfParticipants = 100;
                name.append("C");
            } else {
                numberOfParticipants = 30;
                name.append("L");
            }
            name.append(number++);
            p1.addEvent(new Event(name.toString(), numberOfParticipants, startTimeHour, 0, endTimeHour, 0));
            p2.addEvent(new compulsory.Event(name.toString(), numberOfParticipants, startTimeHour, endTimeHour));
        }
    }

    public void generateRandomRooms(int numberOfRooms, Problem p1, homework.Problem p2) {
        int roomNumber = 0;
        for (int i = 0; i < numberOfRooms; i++) {
            String name = String.valueOf(roomNumber);
            roomNumber++;
            boolean computerLab = rand.nextBoolean();
            if (computerLab) {
                p1.addRoom(new ComputerLab(name, 30, ""));
                p2.addRoom(new ComputerLab(name, 30, ""));
            } else {
                p1.addRoom(new LectureHall(name, 100, true));
                p2.addRoom(new LectureHall(name, 100, true));
            }
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        int numberOfEvents = rand.nextInt(3, 1000);
        int numberOfRooms = rand.nextInt(1000);
        Problem p1 = new Problem(numberOfEvents, numberOfRooms);
        homework.Problem p2 = new homework.Problem(numberOfEvents, numberOfRooms);
        m.generateRandomEvents(numberOfEvents, p1, p2);
        m.generateRandomRooms(numberOfRooms, p1, p2);

        long start = System.currentTimeMillis();
        DSatur d = new DSatur(p1);
        d.solve();
        //d.showSolution();
        System.out.println("(DSatur) Number of used rooms: " + d.computeUsedColors());
        long end = System.currentTimeMillis();
        //System.out.println("DSatur running time: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        GreedyAlgorithm greedy = new GreedyAlgorithm(p2);
        Solution sol = greedy.solve();
        //System.out.println(sol); // automatically calls toString() method
        System.out.println("(Greedy) Number of used rooms: " + sol.computeUsedRooms());
        end = System.currentTimeMillis();
        //System.out.println("Greedy running time: " + (end - start) + " ms");
    }
}
