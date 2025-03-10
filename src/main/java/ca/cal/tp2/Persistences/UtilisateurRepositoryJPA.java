package ca.cal.tp2.Persistences;

import ca.cal.tp2.Models.Emprunteur;
import ca.cal.tp2.Models.Utilisateur;
import ca.cal.tp2.Persistences.Interface.UtilisateurRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class UtilisateurRepositoryJPA implements UtilisateurRepository {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaTownPU");

    @Override
    public Utilisateur getByEmprunteurByName(String prenom, String nom) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT u FROM Utilisateur u WHERE u.nom = :nom AND u.prenom = :prenom AND TYPE(u) = :role");
            query.setParameter("nom", nom);
            query.setParameter("prenom", prenom);
            query.setParameter("role", Emprunteur.class); // Si c'est un Emprunteur

            Utilisateur utilisateur = (Utilisateur) query.getSingleResult();
            // Si c'est un Emprunteur, forcer le chargement des relations
            if (utilisateur instanceof Emprunteur emprunteur) {
                emprunteur.getEmprunts().size(); // Force le chargement des emprunts
                emprunteur.getAmendes().size();  // Force le chargement des amendes
            }
            return utilisateur;
        } finally {
            em.close(); // Assure que l'EntityManager est bien fermé
        }
    }
}