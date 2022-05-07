package app;

import entities.City;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;
import repositories.AbstractRepository;
import repositories.CityRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChocoSolverDemo {
    public static void getCities(char letter, int minPopulation, int maxPopulation) {
        Model model = new Model("cities problem");

        AbstractRepository<City, Integer> data = new CityRepository("City");
        List<City> cities = data.findByName(letter + "%");

        int[] ids = new int[cities.size()];
        int[] populations = new int[cities.size()];

        Set<Integer> countriesIds = new HashSet<>(cities.size());

        int count = 0;
        for (City city : cities) {
            if (!countriesIds.contains(city.getCountry().getId())) {
                countriesIds.add(city.getCountry().getId());
                ids[count] = city.getId();
                populations[count++] = city.getPopulation();
            }
        }

        IntVar[] populationsChoco = model.intVarArray(1, populations);
        model.sum(populationsChoco, "<", maxPopulation).post();
        model.sum(populationsChoco, ">", minPopulation).post();
        //model.allDifferent(populationsChoco).post();

        while (model.getSolver().solve()) {
            System.out.println("Solution " + Arrays.toString(populationsChoco));
        }
        int[] solution = Arrays.stream(populations).toArray();

//        cities.stream().filter(city -> {
//            city.getPopulation()})

        System.out.println(cities);
    }
}
