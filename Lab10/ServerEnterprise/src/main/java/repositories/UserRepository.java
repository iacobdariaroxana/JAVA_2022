package repositories;

import entities.User;

public class UserRepository extends AbstractRepository<User, Integer> {
    public UserRepository(String name){
        super(name);
    }
}