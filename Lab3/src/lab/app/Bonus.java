package lab.app;

import lab.alg.Dijkstra;
import lab.model.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bonus {
    private static Random rand = new Random();

    public static void main(String[] args) {
//        int numberOfVertices = rand.nextInt(2, 100);
        int numberOfVertices = 15;
        int source = rand.nextInt(0, numberOfVertices);
        int destination = rand.nextInt(0, numberOfVertices);
        List<List<Vertex>> adjacency = new ArrayList<>();
        for (int i = 0; i < numberOfVertices; i++) {
            List<Vertex> item = new ArrayList<>();
            adjacency.add(item);
        }
        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = 0; j < numberOfVertices; j++) {
                double probability = 1 - rand.nextDouble(0, 1); // probability of success = 1 - probability of failure
                probability = -Math.log(probability);
//                System.out.println("P:" + probability);
                adjacency.get(i).add(new Vertex(j, probability));
                adjacency.get(j).add(new Vertex(i, probability));
            }
        }
        Dijkstra alg = new Dijkstra(numberOfVertices, adjacency, source, destination);
        alg.solve();
        System.out.println("Safest route probability: " + alg.getSafestRouteProbability());
        alg.showRoute();
    }
}
