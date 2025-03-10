package ca.cal.tp2.Persistences;

import ca.cal.tp2.Models.Document;
import ca.cal.tp2.Models.Emprunt;
import ca.cal.tp2.Models.EmpruntDetail;
import ca.cal.tp2.Persistences.Interface.EmpruntDetailRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;

public class EmpruntDetailRepositoryJPA implements EmpruntDetailRepository {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaTownPU");

    @Override
    public void save(EmpruntDetail empruntDetail) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (empruntDetail.getLineItemId() == 0) {
                em.persist(empruntDetail);
            } else {
                em.merge(empruntDetail);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erreur lors de la sauvegarde du détail d'emprunt", e);
        } finally {
            em.close();
        }
    }

    @Override
    public EmpruntDetail findById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(EmpruntDetail.class, id);
        }
    }

    @Override
    public List<EmpruntDetail> findByEmprunt(Emprunt emprunt) {
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createQuery(
                    "SELECT ed FROM EmpruntDetail ed WHERE ed.emprunt = :emprunt"
            );
            query.setParameter("emprunt", emprunt);
            return query.getResultList();
        }
    }

    @Override
    public List<EmpruntDetail> findByDocument(Document document) {
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createQuery(
                    "SELECT ed FROM EmpruntDetail ed WHERE ed.document = :document"
            );
            query.setParameter("document", document);
            return query.getResultList();
        }
    }
}