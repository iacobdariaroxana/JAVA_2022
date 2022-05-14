package repositories;

import entities.Friend;
import network.User;

public class FriendsRepository extends AbstractRepository<Friend, Integer> {
    public FriendsRepository(String name){
        super(name);
    }
}
