package ca.cal.tp2.Persistences;

import ca.cal.tp2.Models.CD;
import ca.cal.tp2.Persistences.Interface.CDRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CDRepositoryJPA implements CDRepository {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaTownPU");

    @Override
    public void save(CD cd) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cd);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erreur lors de la sauvegarde du CD", e);
        } finally {
            em.close();
        }
    }

    @Override
    public CD findById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(CD.class, id);
        }
    }
}