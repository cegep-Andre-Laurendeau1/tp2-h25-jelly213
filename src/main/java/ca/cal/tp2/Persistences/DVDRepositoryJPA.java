package ca.cal.tp2.Persistences;

import ca.cal.tp2.Models.DVD;
import ca.cal.tp2.Persistences.Interface.DVDRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DVDRepositoryJPA implements DVDRepository {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaTownPU");

    @Override
    public void save(DVD dvd) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(dvd);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erreur lors de la sauvegarde du DVD", e);
        } finally {
            em.close();
        }
    }

    @Override
    public DVD findById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(DVD.class, id);
        }
    }
}