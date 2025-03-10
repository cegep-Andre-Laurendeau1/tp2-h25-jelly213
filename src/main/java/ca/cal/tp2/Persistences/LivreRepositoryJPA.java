package ca.cal.tp2.Persistences;

import ca.cal.tp2.Persistences.Interface.LivreRepository;
import ca.cal.tp2.Models.Livre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class LivreRepositoryJPA implements LivreRepository {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaTownPU");

    @Override
    public void save(Livre livre) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(livre);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erreur lors de la sauvegarde du livre", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Livre findById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Livre.class, id);
        }
    }
}