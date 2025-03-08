package ca.cal.tp2;

import ca.cal.tp2.Persistences.EmprunteurRepositoryJPA;
import ca.cal.tp2.Persistences.UtilisateurRepositoryJPA;
import ca.cal.tp2.Services.EmprunteurService;
import ca.cal.tp2.Services.UtilisateurService;
import ca.cal.tp2.Utils.TcpServer;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws InterruptedException, SQLException {

        TcpServer.startTcpServer();

        // Création d'un emprunteur
        EmprunteurService emprunteurService = new EmprunteurService(new EmprunteurRepositoryJPA());

        emprunteurService.createEmprunteur("John", "Doe", null, null);

        // Recherche d'un emprunteur
        UtilisateurService utilisateurService = new UtilisateurService(new UtilisateurRepositoryJPA());

       System.out.println(utilisateurService.getUtilisateurEmprunteurByName("John", "Doe"));

        Thread.currentThread().join();
    }
}
