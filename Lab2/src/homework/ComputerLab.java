/**
 * @author Iacob Daria-Roxana
 * @since 2022
 */
package homework;

/**
 * Class for defining a computer laboratory
 */
public class ComputerLab extends Room{
    private String computersOperatingSystem;

    /**
     * Parameterized Constructor of class ComputerLab
     * @param name ComputerLab name
     * @param capacity the maximum number of students who can entry into ComputerLab
     * @param operatingSystem what Operating System are using the computers from the lab
     */
    ComputerLab(String name, int capacity, String operatingSystem){
        super(name, capacity);
        this.computersOperatingSystem = operatingSystem;
    }

    @Override
    public String toString() {
        return "ComputerLab{" +
                "name='" + name + '\'' +
                ", capacity= " + capacity +
                " computersOperatingSystem='" + computersOperatingSystem + '\'' +
                '}';
    }
}
