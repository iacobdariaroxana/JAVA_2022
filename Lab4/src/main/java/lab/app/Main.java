package lab.app;

import com.github.javafaker.Faker;
import lab.alg.TravellingSalesmanProblem;
import lab.model.City;
import lab.model.Intersection;
import lab.model.Street;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private Intersection[] intersections;
    private Street[] streets;
    List<Street> streetList = new LinkedList<>();
    Set<Intersection> intersectionSet = new HashSet<>();

    Main(int numberOfIntersections, int numberOfStreets) {
        intersections = IntStream.rangeClosed(0, numberOfIntersections).mapToObj(i -> new Intersection("v" + i)).toArray(Intersection[]::new);
        streets = new Street[numberOfStreets];
    }

    public void setExampleStreets() {
        for (int i = 0; i < 7; i++) {
            streets[i] = new Street("s" + i, 2);
        }
        for (int j = 7; j < 10; j++) {
            streets[j] = new Street("s" + j, 3);
        }
        for (int k = 10; k < 16; k++) {
            streets[k] = new Street("s" + k, 1);
        }
    }

    public void createListOfStreets() {
        streetList.addAll(Arrays.asList(streets));
        List<Street> sortedList = streetList.stream().sorted(Comparator.comparing(Street::getLength)).collect(Collectors.toList());
        System.out.println("Sorted street list:");
        displayList(sortedList);
    }

    public void displayList(List<?> list) {
        list.stream().forEach(System.out::println);
    }

    public void createSetOfIntersections() {
        intersectionSet.addAll(Arrays.asList(intersections));
        verifySetDuplicates(intersectionSet);
    }

    public void verifySetDuplicates(Set<Intersection> intersectionSet) {
        Map<String, Long> counting = intersectionSet.stream().collect(Collectors.groupingBy(Intersection::getName, Collectors.counting()));
        Optional<Long> opt = counting.values().stream().filter(value -> value > 1).findAny();
        if (opt.isEmpty()) {
            System.out.println("Intersections Set does not contain duplicates! ");
        } else {
            System.out.println("Intersections Set contains duplicates!");
        }
    }

    public void addIntersections(City city) {
        city.addIntersection(intersections[0], new Street[]{streets[0], streets[1], streets[2]});
        city.addIntersection(intersections[1], new Street[]{streets[0], streets[3], streets[8]});
        city.addIntersection(intersections[2], new Street[]{streets[1], streets[3], streets[4], streets[5], streets[10]});
        city.addIntersection(intersections[3], new Street[]{streets[2], streets[7], streets[10]});
        city.addIntersection(intersections[4], new Street[]{streets[6], streets[8], streets[11], streets[12]});
        city.addIntersection(intersections[5], new Street[]{streets[4], streets[7], streets[9], streets[11]});
        city.addIntersection(intersections[6], new Street[]{streets[5], streets[13], streets[15]});
        city.addIntersection(intersections[7], new Street[]{streets[12], streets[13], streets[14]});
        city.addIntersection(intersections[8], new Street[]{streets[6], streets[9], streets[14], streets[15]});
        city.setIntersectionsAdjacencyCost();
    }

    public List<Street> getStreets() {
        return this.streetList;
    }

    public void generateFakeNames() {
        Faker faker = new Faker();
        for (Intersection intersection : intersections) {
            intersection.setName(faker.funnyName().name());
        }
        for (Street street : streets) {
            street.setName(faker.address().firstName());
        }
    }

    public Set<Intersection> getIntersections() {
        return this.intersectionSet;
    }

    public void display() {
        Arrays.stream(intersections).forEach(System.out::println);
        Arrays.stream(streets).forEach(System.out::println);
    }

    public static void main(String[] args) {
        Main app = new Main(8, 16);
        app.setExampleStreets();
        app.createListOfStreets();
        System.out.println();
        app.createSetOfIntersections();
        app.generateFakeNames();

        City city = new City(app.getStreets(), app.getIntersections());
        app.addIntersections(city);
        System.out.println();

        app.display();
        System.out.println();

        city.displayStreetsWithSpecificProperties(2);
        System.out.println();

        city.installDataCables();
    }
}
