package repositories;

import entities.Continent;

public class ContinentRepository extends AbstractRepository<Continent, Integer> {
    public ContinentRepository(String name){
        super(name);
    }
}
