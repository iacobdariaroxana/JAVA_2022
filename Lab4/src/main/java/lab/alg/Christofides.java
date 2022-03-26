package lab.alg;

import lab.model.Intersection;
import lab.model.Street;
import org.jgrapht.Graph;
import org.jgrapht.alg.tour.ChristofidesThreeHalvesApproxMetricTSP;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.List;

public class Christofides extends AlgorithmRoute {
    private Graph<Integer, DefaultWeightedEdge> graph;
    private Intersection[] intersections;
    private Street[][] streets;
    private List<Integer> tourVertexList;
    private double tourCost;

    public Christofides(Graph<Integer, DefaultWeightedEdge> graph, Intersection[] intersections, Street[][] streets) {
        super(intersections, streets);
        this.graph = graph;
    }

    public void solve() {
        ChristofidesThreeHalvesApproxMetricTSP<Integer, DefaultWeightedEdge> alg = new ChristofidesThreeHalvesApproxMetricTSP<>();
        var tour = alg.getTour(graph);
        tourVertexList = alg.getTour(graph).getVertexList();
        tourCost = alg.getTour(graph).getWeight();
    }

    public List<Integer> getTourVertexList() {
        return tourVertexList;
    }

    public double getTourCost() {
        return tourCost;
    }

}
