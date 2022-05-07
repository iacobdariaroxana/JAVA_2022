package repositories;

import entities.AbstractEntity;
import singleton.MyEntityManagerFactory;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractRepository<T extends AbstractEntity, ID extends Serializable> {
    private EntityManager em;
    private String className;

    public AbstractRepository(String name) {
        this.em = MyEntityManagerFactory.getEntityManager();
        this.className = name;
    }

    public T findById(ID id) {
        String query = this.className + ".findById";
        return (T) em.createNamedQuery(query)
                .setParameter(1, id)
                .getSingleResult();
    }

    public List<T> findByName(String name) {
        String query = this.className + ".findByName";
        return  em.createNamedQuery(query)
                .setParameter(1, name)
                .getResultList();
    }

    public void create(T obj) {
        System.out.println("Inserting into database ... " +  obj.toString());
        persist(obj);
    }

    public void persist(T entity) {
        try {
            beginTransaction();
            em.persist(entity);
            commit();
            return;
        } catch (Exception e) {
            handleException(e);
            rollback();
        }
    }

    private void rollback() {
        em.getTransaction().rollback();
    }

    private void commit() {
        em.getTransaction().commit();
    }

    private void beginTransaction() {
        em = MyEntityManagerFactory.getEntityManager();
        em.getTransaction().begin();
    }

    private void handleException(Exception e) {
        System.err.println(e.getMessage());
    }
}

