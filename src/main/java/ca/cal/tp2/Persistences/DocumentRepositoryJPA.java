package ca.cal.tp2.Persistences;

import ca.cal.tp2.Persistences.Interface.DocumentRepository;
import ca.cal.tp2.Models.Document;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

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

    @Override
    public List<Document> findByTitreContaining(String keyword) {
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createQuery(
                    "SELECT d FROM Document d WHERE LOWER(d.titre) LIKE LOWER(:keyword)"
            );
            query.setParameter("keyword", "%" + keyword + "%");
            return query.getResultList();
        }
    }

    @Override
    public List<Document> findByAuteur(String auteur) {
        try (EntityManager em = emf.createEntityManager()) {
            // Requête pour trouver les livres par auteur
            Query queryLivres = em.createQuery(
                    "SELECT l FROM Livre l WHERE l.auteur = :auteur"
            );
            queryLivres.setParameter("auteur", auteur);
            List<Document> livres = queryLivres.getResultList();

            // Requête pour trouver les CDs par artiste
            Query queryCDs = em.createQuery(
                    "SELECT c FROM CD c WHERE c.artiste = :artiste"
            );
            queryCDs.setParameter("artiste", auteur);
            List<Document> cds = queryCDs.getResultList();

            // Requête pour trouver les DVDs par réalisateur
            Query queryDVDs = em.createQuery(
                    "SELECT d FROM DVD d WHERE d.director = :director"
            );
            queryDVDs.setParameter("director", auteur);
            List<Document> dvds = queryDVDs.getResultList();

            // Combiner les résultats
            livres.addAll(cds);
            livres.addAll(dvds);
            return livres;
        }
    }

    @Override
    public List<Document> findByAnneePublication(int annee) {
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createQuery(
                    "SELECT d FROM Document d WHERE d.anneePublication = :annee"
            );
            query.setParameter("annee", annee);
            return query.getResultList();
        }
    }

    @Override
    public List<Document> findByArtisteContaining(String artiste) {
        try (EntityManager em = emf.createEntityManager()) {
            // Requête pour trouver les CDs par artiste
            Query queryCDs = em.createQuery(
                    "SELECT c FROM CD c WHERE LOWER(c.artiste) LIKE LOWER(:artiste)"
            );
            queryCDs.setParameter("artiste", "%" + artiste + "%");
            List<Document> cds = queryCDs.getResultList();

            // Requête pour trouver les DVDs par réalisateur
            Query queryDVDs = em.createQuery(
                    "SELECT d FROM DVD d WHERE LOWER(d.director) LIKE LOWER(:director)"
            );
            queryDVDs.setParameter("director", "%" + artiste + "%");
            List<Document> dvds = queryDVDs.getResultList();

            // Combiner les résultats
            cds.addAll(dvds);
            return cds;
        }
    }
}