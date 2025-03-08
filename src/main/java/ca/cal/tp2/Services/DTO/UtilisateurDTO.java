package ca.cal.tp2.Services.DTO;

import ca.cal.tp2.Models.Amende;
import ca.cal.tp2.Models.Emprunt;
import ca.cal.tp2.Models.Utilisateur;

import java.util.List;

public record UtilisateurDTO(long id, String prenom, String nom, String role, List<Emprunt> emprunts, List<Amende> amendes) {
    public static UtilisateurDTO toDTO(Utilisateur utilisateur) {
        return new UtilisateurDTO(utilisateur.getId(), utilisateur.getPrenom(), utilisateur.getNom(), utilisateur.getRole(), utilisateur.getEmprunts(), utilisateur.getAmendes());
    }
}

