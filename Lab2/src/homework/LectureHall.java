/**
 * @author Iacob Daria-Roxana
 * @since 2022
 */
package homework;

/**
 * Class for defining a lecture hall
 */
public class LectureHall extends Room {
    /**
     * a member of the class which tells if the lecture hall has a video projector or not
     */
    private boolean videoProjector;
    /**
     * Parameterized constructor
     * @param name name of lecture hall
     * @param capacity the maximum numbers of students who can entry into the lecture hall
     * @param videoProjector true if the lecture hall has a video projector, otherwise false
     */
    LectureHall(String name, int capacity, boolean videoProjector){
        super(name, capacity);
        this.videoProjector = videoProjector;
    }

    @Override
    public String toString() {
        return "LectureHall{" +
                "name='" + name + '\'' +
                ", capacity= " + capacity +
                " videoProjector=" + videoProjector +
                '}';
    }
}
