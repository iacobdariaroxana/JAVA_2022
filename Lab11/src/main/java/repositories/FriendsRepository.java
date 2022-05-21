package repositories;

import entities.Friend;
import entities.User;

import javax.persistence.NoResultException;

public class FriendsRepository extends AbstractRepository<Friend, Integer> {
    public FriendsRepository(String name) {
        super(name);
    }

    public void updateByUsername1(String username, String replacedUsername) {
        beginTransaction();
        String query = this.getClassName() + ".updateByUsername1";
        em.createNamedQuery(query).setParameter(1, username).
                setParameter(2, replacedUsername).
                executeUpdate();
        commit();
    }

    public void updateByUsername2(String username, String replacedUsername) {
        beginTransaction();
        String query = this.getClassName() + ".updateByUsername2";
        em.createNamedQuery(query).setParameter(1, username).
                setParameter(2, replacedUsername).
                executeUpdate();
        commit();
    }

    public Friend findRelationship(String user1, String user2){
        String query = this.getClassName() + ".findRelationship";
        Friend friend = null;
        try{
            friend = (Friend) em.createNamedQuery(query)
                    .setParameter(1, user1).
                    setParameter(2, user2)
                    .getSingleResult();
            return friend;
        }
        catch(NoResultException exception){
            return null;
        }
    }
}
