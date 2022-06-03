package lab.alg;

import lab.model.Vertex;

import java.util.*;

public class Dijkstra {
    private double[] totalProbability;
    private Set<Integer> addedVertices;
    private List<Integer> route = new ArrayList<>();
    private PriorityQueue<Vertex> priority;
    private int numberOfVertices;
    private List<List<Vertex>> adjacency;
    private int source;
    private int destination;
    private double safestRouteCost;

    public Dijkstra(int numberOfVertices, List<List<Vertex>> adjacency, int source, int destination) {
        this.numberOfVertices = numberOfVertices;
        this.adjacency = adjacency;
        this.source = source;
        this.destination = destination;
        totalProbability = new double[numberOfVertices];
        addedVertices = new HashSet<>();
        priority = new PriorityQueue<>(numberOfVertices);
    }

    public void solve() {
        for (int i = 0; i < numberOfVertices; i++) {
            totalProbability[i] = Double.MAX_VALUE;
        }
        priority.add(new Vertex(source, 0));
        totalProbability[source] = 0;
        while (addedVertices.size() != numberOfVertices) {
            if (priority.isEmpty()) return;
            int u = priority.remove().getIndex();
            if (u == destination) {
                safestRouteCost = totalProbability[destination];
                return;
            }
            if (addedVertices.contains(u)) continue;
            addedVertices.add(u);
            route.add(u);
            updateNeighbors(u);
        }
    }

    public void updateNeighbors(int u) {
        double edgeProbability;
        double newProbability;
        for (int i = 0; i < adjacency.get(u).size(); i++) {
            Vertex v = adjacency.get(u).get(i);
            if (!addedVertices.contains(v.getIndex())) {
                edgeProbability = v.getProbability();
                newProbability = totalProbability[u] + edgeProbability;
                if (newProbability < totalProbability[v.getIndex()]) {
                    totalProbability[v.getIndex()] = newProbability;
                }
                priority.add(new Vertex(v.getIndex(), totalProbability[v.getIndex()]));
            }
        }
    }

    public double getSafestRouteProbability() {
        return Math.pow(10, -safestRouteCost);
    }

    public void showRoute() {
        System.out.println("Source : " + source);
        System.out.println("Destination " + destination);
        System.out.print("Route : ");
        for (int node : route) {
            System.out.print("Node " + node + " -> ");
        }
        System.out.println("Node " + destination);
    }
}
