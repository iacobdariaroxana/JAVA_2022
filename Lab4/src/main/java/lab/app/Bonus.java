package lab.app;

import com.github.javafaker.Faker;
import lab.alg.Christofides;
import lab.model.Intersection;
import lab.model.Street;
import lab.alg.TravellingSalesmanProblem;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.Arrays;
import java.util.Random;

public class Bonus {
    private int[][] adjacencyCost;
    private int numberOfIntersections;
    private static Random random = new Random();
    private Intersection[] intersections;
    private Street[][] streets;
    private Graph<Integer, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

    Bonus(int numberOfIntersections) {
        adjacencyCost = new int[numberOfIntersections][numberOfIntersections];
        this.numberOfIntersections = numberOfIntersections;
        intersections = new Intersection[numberOfIntersections];
        streets = new Street[numberOfIntersections][numberOfIntersections];
    }

    public void generateRandomValue(int x, int y) {
        if (x != y) {
            adjacencyCost[x][y] = adjacencyCost[y][x] = random.nextInt(1, 10);
        } else {
            adjacencyCost[x][y] = 0;
        }
    }

    public boolean verifyTriangle(int x, int y, int z) {
        return x > (y + z) || y > (x + z) || z > (x + y);
    }

    public void verifyAdjacencyCost() {
        for (int i = 0; i < numberOfIntersections - 2; i++) {
            for (int j = i + 1; j < numberOfIntersections - 1; j++) {
                for (int k = j + 1; k < numberOfIntersections; k++) {
//                    System.out.println(i + " " + j + " " + k);
                    if (verifyTriangle(adjacencyCost[i][j], adjacencyCost[i][k], adjacencyCost[j][k])) {
                        do {
                            adjacencyCost[i][j] = adjacencyCost[j][i] = random.nextInt(1, 10);
                            adjacencyCost[i][k] = adjacencyCost[k][i] = random.nextInt(1, 10);
                            adjacencyCost[j][k] = adjacencyCost[k][j] = random.nextInt(1, 10);
                        } while (verifyTriangle(adjacencyCost[i][j], adjacencyCost[i][k], adjacencyCost[j][k]));
                    }
                }
            }
        }
    }

    public void generateInitialAdjacencyCost() {
        for (int i = 0; i < numberOfIntersections; i++) {
            for (int j = 0; j < numberOfIntersections; j++) {
                generateRandomValue(i, j);
            }
        }
    }

    public void generateRandomProblem() {
        generateInitialAdjacencyCost();
        verifyAdjacencyCost();
        Faker faker = new Faker();
        for (int i = 0; i < numberOfIntersections; i++) {
            intersections[i] = new Intersection(faker.funnyName().name());
        }
        for (int i = 0; i < numberOfIntersections; i++) {
            for (int j = i + 1; j < numberOfIntersections; j++) {
                streets[i][j] = streets[j][i] = new Street(faker.address().streetName(), adjacencyCost[i][j]);
            }
        }
    }

    public void showMatrix() {
        System.out.println("Adjacency cost:");
        for (int[] row : adjacencyCost)
            System.out.println(Arrays.toString(row));
    }

    public int[][] getAdjacencyCost() {
        return adjacencyCost;
    }

    public Intersection[] getIntersections() {
        return intersections;
    }

    public Street[][] getStreets() {
        return streets;
    }

    public Graph<Integer, DefaultWeightedEdge> getGraph() {
        return graph;
    }

    public void createGraph() {
        for (int i = 0; i < numberOfIntersections; i++) {
            graph.addVertex(i);
        }
        for (int i = 0; i < numberOfIntersections; i++) {
            for (int j = i + 1; j < numberOfIntersections; j++) {
                DefaultWeightedEdge edge = new DefaultWeightedEdge();
                graph.addEdge(i, j, edge);
                graph.setEdgeWeight(edge, adjacencyCost[i][j]);
            }
        }
    }

    public static void main(String[] args) {
        int numberOfIntersections = random.nextInt(5, 15);
        Bonus bonus = new Bonus(numberOfIntersections);
        bonus.generateRandomProblem();
        TravellingSalesmanProblem tsp = new TravellingSalesmanProblem(bonus.getAdjacencyCost(), bonus.getIntersections(), bonus.getStreets());
        System.out.println("Metric TSP Solution Route:");
        tsp.solve();
        tsp.showFullRoute(tsp.getTourVertexList());
        tsp.computeCost();

        System.out.println();
        bonus.createGraph();
        Christofides c = new Christofides(bonus.getGraph(), bonus.getIntersections(), bonus.getStreets());
        c.solve();
        System.out.println("Christofides Solution Route:");
        c.showFullRoute(c.getTourVertexList());
        System.out.println("Christofides route cost: " + c.getTourCost());
    }
}
