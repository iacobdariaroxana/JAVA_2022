package compulsory;

/**
 * The compulsory class
 */
public class Compulsory {
    /**
     * Main method of class
     * @param args for parsing command line arguments
     */
    public static void main(String[] args)
    {
        System.out.println("Events:");
        Event c1 = new Event();
        c1.setName("C1");
        c1.setNumberOfParticipants(100);
        c1.setStartTime(8);
        c1.setEndTime(10);
        System.out.println(c1);

        Event c2 = new Event("C2", 100, 10, 12);
        System.out.println(c2);

        Event l1 = new Event("L1");
        l1.setNumberOfParticipants(30);
        l1.setStartTime(8);
        l1.setEndTime(10);
        System.out.println(l1);

        Event l2 = new Event("L2", 30);
        l2.setStartTime(8);
        l2.setEndTime(10);
        System.out.println(l2);

        Event l3 = new Event("L3", 30, 10, 12);
        System.out.println(l3);

        System.out.println("Rooms:");
        Room r1 = new Room();
        r1.setName("401");
        r1.setCapacity(30);
        r1.setType(RoomType.COMPUTER_LAB);
        System.out.println(r1);

        Room r2 = new Room("403");
        r2.setCapacity(30);
        r2.setType(RoomType.COMPUTER_LAB);
        System.out.println(r2);

        Room r3 = new Room("405", 30);
        r3.setType(RoomType.COMPUTER_LAB);
        System.out.println(r3);

        Room r4 = new Room("309", 100, RoomType.LECTURE_HALL);
        System.out.println(r4);
    }

}
