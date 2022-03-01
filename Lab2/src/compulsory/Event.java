package compulsory;

public class Event {
    /**
     * name of event
     */
    private String name;
    /**
     * how many participants the event will have
     */
    private int numberOfParticipants;
    /**
     * at what time the event starts
     */
    private int startTime;
    /**
     * at what time the event ends
     */
    private int endTime;

    /**
     * Non parameterized constructor
     */
    public Event()
    {

    }

    /**
     * Constructor
     * @param name name of event
     */
    public Event(String name){
        this.name = name;
    }

    /**
     * Constructor
     * @param name name of event
     * @param numberOfParticipants the number of participants
     */
    public Event(String name, int numberOfParticipants){
        this(name);
        this.numberOfParticipants = numberOfParticipants;
    }

    /**
     * Constructor
     * @param name name of event
     * @param numberOfParticipants the number of participants
     * @param startTime at what time the event starts
     * @param endTime at what time the event ends
     */
    public Event(String name, int numberOfParticipants, int startTime, int endTime){
        this(name, numberOfParticipants);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Method for getting name of an event
     * @return returns the name of event
     */
    public String getName() {
        return name;
    }

    /**
     * Sets a name to an event
     * @param name name of event
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method for getting start time of an event
     * @return returns the start time of event
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Method for setting start time of an event
     * @param startTime at what time the event starts
     */
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", numberOfParticipants=" + numberOfParticipants +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Event)){
            return false;
        }
        Event other = (Event) obj;
        return name.equals(other.name);
    }
}
