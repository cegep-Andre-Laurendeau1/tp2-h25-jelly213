package ca.cal.tp2.Persistences;

import ca.cal.tp2.Models.DVD;
import ca.cal.tp2.Persistences.Interface.DVDRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DVDRepositoryJPA implements DVDRepository {
    @Override
    public void save(DVD dvd) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaTownPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(dvd);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
