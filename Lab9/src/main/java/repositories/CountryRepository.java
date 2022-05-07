package repositories;

import entities.Country;

public class CountryRepository extends AbstractRepository<Country, Integer> {
    public CountryRepository(String name){
        super(name);
    }
}
