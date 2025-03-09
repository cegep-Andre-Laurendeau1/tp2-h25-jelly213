package ca.cal.tp2.Persistences;

import ca.cal.tp2.Models.Livre;
import ca.cal.tp2.Persistences.Interface.LivreRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class LivreRepositoryJPA implements LivreRepository {

    @Override
    public void save(Livre livre) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaTownPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(livre);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
