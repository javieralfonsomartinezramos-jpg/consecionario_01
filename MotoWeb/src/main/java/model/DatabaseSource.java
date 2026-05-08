
package model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;

public class DatabaseSource implements DataSource {

    private final EntityManagerFactory emf;

    public DatabaseSource() {
        emf = Persistence.createEntityManagerFactory("app.polsl.MotoCatWeb");
    }
    
    @Override
    public void persistObject(Object object) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();     
        } catch (PersistenceException e) {            
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
      

    
    
    
}

