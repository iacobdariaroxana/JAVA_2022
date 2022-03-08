package bonus;

import java.time.LocalTime;

public class Event {
    private String name;
    private int numberOfParticipants;
    private LocalTime startTime;
    private LocalTime endTime;
    private String type;

    Event(String name, int numberOfParticipants, int startTimeHour, int startTimeMinute, int endTimeHour, int endTimeMinute) {
        this.name = name;
        if (name.charAt(0) == 'C')
            setType("course");
        else
            setType("lab");
        this.numberOfParticipants = numberOfParticipants;
        this.startTime = LocalTime.of(startTimeHour, startTimeMinute);
        this.endTime = LocalTime.of(endTimeHour, endTimeMinute);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof compulsory.Event)) {
            return false;
        }
        compulsory.Event other = (compulsory.Event) obj;
        return name.equals(other.getName());
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
