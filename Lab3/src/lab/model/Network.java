package lab.model;

import java.util.ArrayList;
import java.util.List;

public class Network {
    private List<Node> nodes;
    private List<Node> identifiableNodes = new ArrayList<>();

    public Network() {
        nodes = new ArrayList<>();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public int compareByAddress(Node n1, Node n2) {
        String ipAddress1 = "";
        String ipAddress2 = "";
        if (n1 instanceof Computer)
            ipAddress1 = ((Computer) n1).getIpAddress();
        else
            ipAddress1 = ((Router) n1).getIpAddress();

        if (n2 instanceof Computer)
            ipAddress2 = ((Computer) n2).getIpAddress();
        else
            ipAddress2 = ((Router) n2).getIpAddress();
        return ipAddress1.compareTo(ipAddress2);
//        return ((Identifiable) n1).getIpAddress().compareTo(((Identifiable) n2).getIpAddress());
    }

    public void setIdentifiableNodes() {
        for (Node node : nodes) {
            if (node instanceof Identifiable) {
                identifiableNodes.add(node);
            }
        }
        identifiableNodes.sort(this::compareByAddress);
    }

    public void displayIdentifiableNodes() {
        System.out.println("Nodes that are identifiable are:");
        for (Node node : identifiableNodes) {
            System.out.println(node);
        }
        System.out.println();
    }

    public List<Node> getIdentifiableNodes() {
        return identifiableNodes;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Node node : nodes) {
            s.append(node);
            s.append("\n");
        }
        s.append("From-To Cost\n");
        for (Node node : nodes) {
            s.append(node.getCosts());
        }
        return s.toString();
    }
}
