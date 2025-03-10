package ca.cal.tp2.Persistences;

import ca.cal.tp2.Models.CD;
import ca.cal.tp2.Persistences.Interface.CDRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CDRepositoryJPA implements CDRepository {

    @Override
    public void save(CD cd) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaTownPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(cd);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

}
