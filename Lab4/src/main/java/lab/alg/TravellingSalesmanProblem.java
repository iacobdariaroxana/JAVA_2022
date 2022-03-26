package lab.alg;

import lab.model.Intersection;
import lab.model.Street;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TravellingSalesmanProblem extends AlgorithmRoute {
    private int numberOfNodes;
    int[][] adjacencyCost;
    private List<Integer> adjacencyLists[];
    private List<Integer> tourVertexList = new ArrayList<>();
    private Intersection[] intersections;
    private Street[][] streets;
    private int routeCost = 0;

    public TravellingSalesmanProblem(int[][] adjacencyCost, Intersection[] intersections, Street[][] streets) {
        super(intersections, streets);
        this.adjacencyCost = adjacencyCost;
        numberOfNodes = adjacencyCost.length;
        adjacencyLists = new List[numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            adjacencyLists[i] = new LinkedList<>();
        }
        this.intersections = intersections;
        this.streets = streets;
    }

    public void solve() {
        computeMST();
        dfs(0);
    }

    public void computeMST() {
        Prim prim = new Prim(adjacencyCost);
        int[][] result = prim.findMinimumSpanningTree();
//        System.out.println("MST cost = " + prim.getMSTCost());
        setAdjacencyList(result);
    }

    public void setAdjacencyList(int[][] mstEdges) {
        for (int[] row : mstEdges) {
            adjacencyLists[row[0]].add(row[1]);
            adjacencyLists[row[1]].add(row[0]);
        }
//        showAdjacencyLists(adjacencyLists);
    }

    public void showAdjacencyLists(List<Integer>[] adjacencyLists) {
        System.out.println();
        Arrays.stream(adjacencyLists).forEach(System.out::println);
    }

    public void recursiveDFS(int node, boolean[] visited) {
        visited[node] = true;
        tourVertexList.add(node);
        for (Integer neighbor : adjacencyLists[node]) {
            if (!visited[neighbor]) {
                recursiveDFS(neighbor, visited);
            }
        }
    }

    public void dfs(int startingNode) {
        boolean[] visited = new boolean[numberOfNodes];
        recursiveDFS(startingNode, visited);
    }

    public void showRoute() {
        System.out.print("Route: ");
        for (Integer node : tourVertexList) {
            System.out.print(node + " ");
        }
        System.out.println(tourVertexList.get(0));
    }

    public void computeCost() {
        int i = 0;
        for (; i < numberOfNodes - 1; i++) {
            routeCost += adjacencyCost[tourVertexList.get(i)][tourVertexList.get(i + 1)];
        }
        routeCost += adjacencyCost[tourVertexList.get(i)][tourVertexList.get(0)];
        System.out.println("Metric route cost: " + routeCost);
    }

    public List<Integer> getTourVertexList() {
        return tourVertexList;
    }

}
