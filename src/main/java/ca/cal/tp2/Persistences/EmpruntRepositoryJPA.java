package ca.cal.tp2.Persistences;

import ca.cal.tp2.Models.Emprunt;
import ca.cal.tp2.Models.Emprunteur;
import ca.cal.tp2.Persistences.Interface.EmpruntRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class EmpruntRepositoryJPA implements EmpruntRepository {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaTownPU");

    @Override
    public void save(Emprunt emprunt) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (emprunt.getBorrowId() == 0) {
                em.persist(emprunt);
            } else {
                em.merge(emprunt);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erreur lors de la sauvegarde de l'emprunt", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Emprunt findById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Emprunt.class, id);
        }
    }

    @Override
    public List<Emprunt> findByEmprunteur(Emprunteur emprunteur) {
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createQuery(
                    "SELECT e FROM Emprunt e WHERE e.emprunteur = :emprunteur"
            );
            query.setParameter("emprunteur", emprunteur);
            return query.getResultList();
        }
    }
}
