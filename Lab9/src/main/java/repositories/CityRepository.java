package repositories;

import entities.City;

public class CityRepository extends AbstractRepository<City, Integer> {
    public CityRepository(String name){
        super(name);
    }
}