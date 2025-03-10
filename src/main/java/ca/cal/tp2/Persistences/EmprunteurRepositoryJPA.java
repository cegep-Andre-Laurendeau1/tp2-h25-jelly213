package ca.cal.tp2.Persistences;

import ca.cal.tp2.Models.Emprunteur;
import ca.cal.tp2.Persistences.Interface.EmprunteurRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class EmprunteurRepositoryJPA implements EmprunteurRepository {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaTownPU");

    @Override
    public void save(Emprunteur emprunteur) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(emprunteur);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erreur lors de la sauvegarde de l'emprunteur", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Emprunteur findById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Emprunteur.class, id);
        }
    }

    @Override
    public Emprunteur findByName(String name) {
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createQuery("SELECT e FROM Emprunteur e WHERE e.name = :name");
            query.setParameter("name", name);
            return (Emprunteur) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}