package lab.model;

import lab.alg.Prim;

import java.util.*;

public class City {
    private List<Street> streetList;
    private Set<Intersection> intersectionSet;
    private Map<Intersection, List<Street>> cityMap = new HashMap<>();
    private int[][] intersectionsAdjacency;
    private Map<Intersection, Integer> intersectionNodeNumber = new HashMap<>();
    private int counter = 0;

    public City(List<Street> streetList, Set<Intersection> intersectionSet) {
        this.streetList = streetList;
        this.intersectionSet = intersectionSet;
        intersectionsAdjacency = new int[intersectionSet.size()][intersectionSet.size()];
        Arrays.stream(intersectionsAdjacency).forEach(row -> Arrays.fill(row, 0));
    }

    public void addIntersection(Intersection node, Street[] incidentStreets) {
        cityMap.put(node, Arrays.asList(incidentStreets));
        intersectionNodeNumber.put(node, counter++);
    }

    public void showIntersections() {
        for (var key : cityMap.keySet()) {
            System.out.print(key.getName() + " : ");
            for (var street : cityMap.get(key)) {
                System.out.print(street.getName() + " ");
            }
            System.out.println();
        }
    }

    public void displayStreetsWithSpecificProperties(int length) {
        System.out.println("Streets with the required properties are:");
        streetList.stream().filter(s -> s.getLength() > length).filter(s ->
                (cityMap.get(s.getFirstIntersection()).size() + cityMap.get(s.getFirstIntersection()).size() - 2) > 2).forEach(System.out::println);
    }

    public int[][] getIntersectionsAdjacencyCost() {
        return intersectionsAdjacency;
    }

    public void setIntersectionsAdjacencyCost() {
        for (Street street : streetList) {
            List<Intersection> streetEdges = intersectionSet.stream().filter(i -> cityMap.get(i).contains(street)).toList();
            if (streetEdges.size() == 2) {
                int u = intersectionNodeNumber.get(streetEdges.get(0));
                int v = intersectionNodeNumber.get(streetEdges.get(1));
                street.setFirstIntersection(streetEdges.get(0));
                street.setSecondIntersection(streetEdges.get(1));
                street.setFirstNode(u);
                street.setSecondNode(v);
                intersectionsAdjacency[u][v] = intersectionsAdjacency[v][u] = street.getLength();
            }
        }
    }

    public void showCityMap() {
        cityMap.entrySet().forEach(System.out::println);
    }

    public void installDataCables() {
        Prim prim = new Prim(this.getIntersectionsAdjacencyCost());
        int[][] streetNodes = prim.findMinimumSpanningTree();
        System.out.println("The data cables will be installed on the following streets:");
        for (int[] row : streetNodes) {
            streetList.stream().filter(street -> (street.getFirstNode() == row[0] && street.getSecondNode() == row[1]) || (street.getFirstNode() == row[1] && street.getSecondNode() == row[0])).forEach(System.out::println);
        }
        System.out.println("MST cost = " + prim.getMSTCost());
    }

}
