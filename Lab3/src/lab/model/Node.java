package lab.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public abstract class Node implements Comparable<Node> {
    private String name;
    private String hardwareAddress;
    private String location;
    private Map<Node, Integer> cost = new HashMap<>();

    protected Node(String name, String location) {
        this.setName(name);
        this.setLocation(location);
    }

    protected Node(String name, String hardwareAddress, String location) {
        this.setName(name);
        this.setHardwareAddress(hardwareAddress);
        this.setLocation(location);
    }

    @Override
    public int compareTo(Node other) {
        if (other == null || other.getName() == null)
            throw new NullPointerException();
        return this.name.compareTo(other.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node))
            return false;
        Node node = (Node) o;
        return this.name.equals(node.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getHardwareAddress(), getLocation());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHardwareAddress() {
        return hardwareAddress;
    }

    public void setHardwareAddress(String hardwareAddress) {
        this.hardwareAddress = hardwareAddress;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCost(Node node, int value) {
        cost.put(node, value);
    }

    public Map<Node,Integer> getCostsMap(){
        return cost;
    }
    public String getCosts() {
        StringBuilder str = new StringBuilder();
        for (Node node : cost.keySet()) {
            str.append(this.getLocation()).append("--").append(node.getLocation()).append(" ").append(cost.get(node));
            str.append("\n");
        }
        return str.toString();
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", hardwareAddress='" + hardwareAddress + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
