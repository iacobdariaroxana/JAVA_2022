package lab.alg;

import lab.model.Node;

import java.util.*;

public class FloydWarshall {
    static final int INFINITY;

    static {
        INFINITY = Integer.MAX_VALUE;
    }

    private Map<Node, Integer> nodesIndex = new HashMap<>();
    private int[][] costMatrix;
    private int numberOfNodes;
    private int[][] shortestTimes;

    public FloydWarshall(List<Node> nodes) {
        int counter = 0;
        for (Node node : nodes) {
            nodesIndex.put(node, counter++);
        }
        numberOfNodes = nodes.size();
        costMatrix = new int[numberOfNodes][numberOfNodes];
        shortestTimes = new int[numberOfNodes][numberOfNodes];
        setCostMatrix();
    }

    public void setCostMatrix() {
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                if (i == j)
                    costMatrix[i][j] = 0;
                else
                    costMatrix[i][j] = INFINITY;
            }
        }
        for (Node node : nodesIndex.keySet()) {
            Map<Node, Integer> temporaryMap = node.getCostsMap();
            for (Node neighbor : temporaryMap.keySet()) {
                // returns index of neighbor
                costMatrix[nodesIndex.get(node)][nodesIndex.get(neighbor)] = temporaryMap.get(neighbor);
            }
        }
    }

    public void findAllShortestTimes() {
        for (int i = 0; i < numberOfNodes; i++) {
            System.arraycopy(costMatrix[i], 0, shortestTimes[i], 0, numberOfNodes);
        }
        for (int k = 0; k < numberOfNodes; k++) {
            for (int i = 0; i < numberOfNodes; i++) {
                for (int j = 0; j < numberOfNodes; j++) {
                    if (shortestTimes[i][k] != INFINITY && shortestTimes[k][j] != INFINITY && shortestTimes[i][k] + shortestTimes[k][j] < shortestTimes[i][j]) {
                        shortestTimes[i][j] = shortestTimes[i][k] + shortestTimes[k][j];
                    }
                }
            }
        }
    }

    public void getAllShortestTimes(List<Node> identifiableNodes) {
        for (int i = 0, n = identifiableNodes.size(); i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int firstNodePosition = nodesIndex.get(identifiableNodes.get(i));
                int secondNodePosition = nodesIndex.get(identifiableNodes.get(j));
                System.out.println("The shortest time from identifiable node " + identifiableNodes.get(i).getLocation() + " to " + identifiableNodes.get(j).getLocation() + " is " + shortestTimes[firstNodePosition][secondNodePosition]);

            }
        }
    }

    public void displayCostMatrix() {
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                if (costMatrix[i][j] != INFINITY) System.out.print(costMatrix[i][j] + " ");
                else System.out.print("INF ");
            }
            System.out.println();
        }
    }
}
