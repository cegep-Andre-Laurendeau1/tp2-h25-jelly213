package ca.cal.tp2.Persistences;

import ca.cal.tp2.Persistences.Interface.DocumentRepository;
import ca.cal.tp2.Models.Document;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class DocumentRepositoryJPA implements DocumentRepository {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaTownPU");

    // Méthode pour sauvegarder un document
    @Override
    public void save(Document document) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(document);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erreur lors de la sauvegarde du document", e);
        } finally {
            em.close();
        }
    }

    // Méthode pour mettre à jour un document
    @Override
    public Document findById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Document.class, id);
        }
    }

    // Méthode pour trouver un document par son titre
    @Override
    public Document findByTitre(String titre) {
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createQuery("SELECT d FROM Document d WHERE d.titre = :titre");
            query.setParameter("titre", titre);
            return (Document) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}