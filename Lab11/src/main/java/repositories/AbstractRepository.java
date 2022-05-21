package repositories;

import entities.AbstractEntity;
import entities.User;
import singleton.MyEntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractRepository<T extends AbstractEntity, ID extends Serializable> {
    protected EntityManager em;
    private String className;

    public AbstractRepository(String name) {
        this.em = MyEntityManagerFactory.getEntityManager();
        this.className = name;
    }

    public T findById(ID id) {
        String query = this.className + ".findById";
        User user = null;
        try{
            user = (User) em.createNamedQuery(query)
                    .setParameter(1, id)
                    .getSingleResult();
            return (T) user;
        }
        catch(NoResultException exception){
            return null;
        }
    }

    public List<T> findByName(String name) {
        String query = this.className + ".findByName";
        return  em.createNamedQuery(query)
                .setParameter(1, name)
                .getResultList();
    }

    public List<T> findAll() {
        String query = this.className + ".findAll";
        return  em.createNamedQuery(query)
                .getResultList();
    }

    public List<T> getMessages(String name) {
        String query = this.className + ".getMessages";
        return  em.createNamedQuery(query)
                .setParameter(1, name)
                .getResultList();
    }

    public void updateMessages(String name, String message) {
        beginTransaction();
        String query = this.className + ".updateMessages";
        em.createNamedQuery(query)
            .setParameter(1, message)
            .setParameter(2, name);
        commit();
    }


    public void create(T obj) {
        System.out.println("Inserting into database ... " +  obj.toString());
        persist(obj);
    }

    public void delete(ID id){
        beginTransaction();
        String query = this.className + ".delete";
        em.createNamedQuery(query)
                .setParameter(1, id).executeUpdate();
        commit();
    }

    public void deleteByUsername(String username){
        beginTransaction();
        String query = this.className + ".deleteByUsername";
        em.createNamedQuery(query).setParameter(1, username).executeUpdate();
        commit();
    }

    public void updateUsername(String username, String replacedUsername){
        beginTransaction();
        String query = this.className + ".updateByUsername";
        em.createNamedQuery(query).setParameter(1, username).
                setParameter(2, replacedUsername).
                executeUpdate();
        commit();
    }
    public void persist(T entity) {
        try {
            beginTransaction();
            em.persist(entity);
            commit();
        } catch (Exception e) {
            handleException(e);
            rollback();
        }
    }

    private void rollback() {
        em.getTransaction().rollback();
    }

    protected void commit() {
        em.getTransaction().commit();
    }

    protected void beginTransaction() {
        em = MyEntityManagerFactory.getEntityManager();
        em.getTransaction().begin();
    }

    public String getClassName() {
        return className;
    }

    private void handleException(Exception e) {
        System.err.println(e.getMessage());
    }
}

