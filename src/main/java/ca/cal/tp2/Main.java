package ca.cal.tp2;

import ca.cal.tp2.Models.Emprunteur;
import ca.cal.tp2.Persistences.EmprunteurRepositoryJPA;
import ca.cal.tp2.Services.EmprunteurService;
import ca.cal.tp2.Utils.TcpServer;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        TcpServer.startTcpServer();
        // Initialisation des services
        EmprunteurService emprunteurService = new EmprunteurService(new EmprunteurRepositoryJPA());

        // Démonstration de la fonctionnalité d'enregistrement d'un nouvel emprunteur
        System.out.println("===== ENREGISTREMENT D'UN NOUVEL EMPRUNTEUR =====");

        try {
            // Création d'un nouvel emprunteur
            emprunteurService.creerNouvelEmprunteur("John Doe", "john.doe@example.com", "514-123-4567");
            System.out.println("Emprunteur créé avec succès.");

            // Récupération et affichage des détails de l'emprunteur
            Emprunteur emprunteur = emprunteurService.getEmprunteurByName("John Doe");
            System.out.println("Détails de l'emprunteur récupéré:");
            System.out.println("  Nom: " + emprunteur.getName());
            System.out.println("  Email: " + emprunteur.getEmail());
            System.out.println("  Téléphone: " + emprunteur.getPhoneNumber());
            System.out.println("  Rôle: " + emprunteur.getRole());

            // Tentative de création d'un emprunteur avec un nom existant (doit échouer)
            emprunteurService.creerNouvelEmprunteur("John Doe", "autre.email@example.com", "514-987-6543");
        } catch (IllegalStateException e) {
            System.out.println("Erreur attendue: " + e.getMessage());
        }

        // Création d'un nouvel emprunteur avec un nom différent
        try {
            emprunteurService.creerNouvelEmprunteur("Jane Smith", "jane.smith@example.com", "514-555-9876");
            System.out.println("Deuxième emprunteur créé avec succès.");
        } catch (Exception e) {
            System.out.println("Erreur inattendue: " + e.getMessage());
        }
    }
}