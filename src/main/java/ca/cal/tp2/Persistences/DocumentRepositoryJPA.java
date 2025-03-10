package ca.cal.tp2.Persistences;

import ca.cal.tp2.Models.Document;
import ca.cal.tp2.Persistences.Interface.DocumentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class DocumentRepositoryJPA implements DocumentRepository {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaTownPU");

    @Override
    public List<Document> getDocumentsByPartialTitle(String keyword) {
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createQuery(
                    "SELECT d FROM Document d WHERE d.titre LIKE :keyword AND TYPE(d) = Livre"
            );
            query.setParameter("keyword", "%" + keyword + "%");
            return query.getResultList();
        }
    }

    // Recherche par auteur
    @Override
    public List<Document> getDocumentsByAuthor(String author) {
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createQuery(
                    "SELECT d FROM Document d WHERE d.auteur = :author AND TYPE(d) = Livre"
            );
            query.setParameter("author", author);
            return query.getResultList();
        }
    }

    // Recherche par année
    @Override
    public List<Document> getDocumentsByYear(int year) {
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createQuery(
                    "SELECT d FROM Document d WHERE d.anneePublication = :year AND TYPE(d) = Livre"
            );
            query.setParameter("year", year);
            return query.getResultList();
        }
    }
}

