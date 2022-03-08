package lab.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Network {
    private List<Node> nodes;

    public Network() {
        nodes = new ArrayList<>();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }


    public int compareByAddress(Node n1, Node n2) {
        return n1.getHardwareAddress().compareTo(n2.getHardwareAddress());
    }

    public void displayIdentifiableNodes() {
        List<Node> items = new ArrayList<>();
        for (Node node : nodes) {
            if (node instanceof Identifiable) {
                items.add(node);
            }
        }
        items.sort(this::compareByAddress);
        for(Node node:items){
            System.out.println(node);
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Node node : nodes) {
            s.append(node);
            s.append("\n");
        }
        return s.toString();
    }
}
