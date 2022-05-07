package app;

import entities.City;
import org.chocosolver.solver.Model;
import repositories.AbstractRepository;
import repositories.CityRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChocoSolverDemo {
    public static List<City> getCities(char letter, int minPopulation, int maxPopulation) {
        Model model = new Model("cities problem");

        AbstractRepository<City, Integer> data = new CityRepository("City");
        List<City> cities = data.findByName(letter + "%");
        int[] ids = new int[cities.size()];
        Set<Integer> countriesIds = new HashSet<>();
        int count = 0;
        for (City city : cities) {
            if (!countriesIds.contains(city.getCountry().getId())) {
                ids[count++] = city.getId();
                countriesIds.add(city.getCountry().getId());
            }
        }


//        SetVar correspondingIds = model.setVar("correspondingIds", ids);
//        IntVar population = model.intVar("population", minPopulation, maxPopulation, true);
        return null;
    }
}
