package bonus;

import homework.Room;

import java.util.*;

public class DSatur {
    class Node {
        private int size;
        private int subgraphDegree;
        private String color;
        private List<Integer> neighbors;
        private Set<String> adjacentColors;

        Node() {
            color = "";
            neighbors = new LinkedList<>();
            adjacentColors = new HashSet<>();
        }
    }

    private Room[] rooms;
    private Event[] events;
    private Node[] nodes;
    private final int numberOfColors;
    private List<Integer> notColored;
    private Set<String> usedColors;

    DSatur(Problem p) {
        rooms = p.getRooms();
        numberOfColors = rooms.length;
        nodes = new Node[p.getNumberOfEvents()];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node();
        }
        events = p.getEvents();
        addEdges(events);
        notColored = new ArrayList<>();
        for (int i = 0; i < nodes.length; i++) {
            notColored.add(i);
        }
        usedColors = new HashSet<>();
    }

    public void addEdges(Event[] events) {
        for (int i = 0; i < events.length - 1; i++) {
            for (int j = i + 1; j < events.length; j++) {
                if (!notOverlaps(events[i], events[j]) && events[i].getType().equals(events[j].getType())) {
                    nodes[i].neighbors.add(j);
                    nodes[j].neighbors.add(i);
                }
            }
        }
    }

    public boolean notOverlaps(Event e1, Event e2) {
        if (e1.getStartTime().isBefore(e2.getStartTime())) {
            if (e1.getEndTime().equals(e2.getStartTime())) return true;
            else return e1.getEndTime().isBefore(e2.getStartTime());
        } else {
            if (e2.getEndTime().equals(e1.getStartTime())) return true;
            else return e2.getEndTime().isBefore(e1.getStartTime());
        }
    }

    public void initialize() {
        for (int i = 0; i < nodes.length; i++) {
            nodes[i].subgraphDegree = getNumberOfNeighbors(i);
            nodes[i].size = events[i].getNumberOfParticipants();
        }
    }

    public int getNumberOfNeighbors(int v) {
        return nodes[v].neighbors.size();
    }

    public int getSaturationDegree(Node node) {
        return node.adjacentColors.size();
    }

    public int getMaximumSaturationDegreeNode() {
        int maximum = -1;
        int next = notColored.get(0);
        for (int i = 0; i < notColored.size(); i++) {
            int nodeIndex = notColored.get(i);
            int currentSaturationDegree = getSaturationDegree(nodes[nodeIndex]);
            if (currentSaturationDegree > maximum) {
                maximum = currentSaturationDegree;
                next = nodeIndex;
            } else if (currentSaturationDegree == maximum && nodes[nodeIndex].subgraphDegree >= nodes[next].subgraphDegree) {
                next = nodeIndex;
            }
        }
        return next;
    }

    public void colorNode(int index, String color) {
        usedColors.add(color);
        nodes[index].color = color;
        for (Integer neighbor : nodes[index].neighbors) {
            nodes[neighbor].adjacentColors.add(color);
            nodes[neighbor].neighbors.remove((Integer) index);
            nodes[neighbor].subgraphDegree--;
        }
        notColored.remove((Integer) index);
    }

    public boolean findColor(int next) {
        for (int i = 0; i < numberOfColors; i++) {
            if (!nodes[next].adjacentColors.contains(rooms[i].getName()) && nodes[next].size <= rooms[i].getCapacity()) {
                colorNode(next, rooms[i].getName());
                return true;
            }
        }
        return false;
    }

    public void solve() {
        initialize();
        findColor(0);
        while (!notColored.isEmpty()) {
            int next = getMaximumSaturationDegreeNode();
            if (!findColor(next)) {
                System.err.println("(DSatur) The Room Assignment Problem has no solution!");
                System.exit(-1);
            }
        }
    }

    public void showSolution() {
        System.out.println("DSatur solution:");
        for (int i = 0; i < nodes.length; i++) {
            System.out.println(events[i].getName() + " -> " + nodes[i].color);
        }
    }

    public void displayNeighbors() {
        for (int i = 0; i < nodes.length; i++) {
            System.out.print("Neighbors of node " + i + " are:");
            for (Integer j : nodes[i].neighbors) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public int computeUsedColors() {
        return usedColors.size();
    }
}
