package lab.alg;

import lab.model.Intersection;
import lab.model.Street;

import java.util.List;

public class AlgorithmRoute {
    private Intersection[] intersections;
    private Street[][] streets;
    public AlgorithmRoute(Intersection[] intersections, Street[][] streets){
        this.intersections = intersections;
        this.streets = streets;
    }
    public void showFullRoute(List<Integer> tourVertexList) {
        for (int i = 0; i < tourVertexList.size() - 1; i++) {
            if (i % 3 == 0 && i != 0) System.out.println();
            System.out.print("Intersection: " + intersections[tourVertexList.get(i)].getName() + " -> Street: " + streets[tourVertexList.get(i)][tourVertexList.get(i + 1)].getName() + " -> ");
        }
        System.out.println("Intersection: " + intersections[tourVertexList.get(0)].getName());
    }
}
