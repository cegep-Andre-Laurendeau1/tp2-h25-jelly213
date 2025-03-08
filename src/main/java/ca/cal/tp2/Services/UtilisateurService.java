package ca.cal.tp2.Services;

import ca.cal.tp2.Models.Utilisateur;
import ca.cal.tp2.Persistences.UtilisateurRepositoryJPA;
import ca.cal.tp2.Services.DTO.UtilisateurDTO;

public class UtilisateurService {
    private final UtilisateurRepositoryJPA utilisateurRepository;

    public UtilisateurService(
        UtilisateurRepositoryJPA utilisateurRepository
    ) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public UtilisateurDTO getUtilisateurEmprunteurByName(String prenom, String nom) {
        Utilisateur utilisateur = utilisateurRepository.getByEmprunteurByName(prenom, nom);
        return UtilisateurDTO.toDTO(utilisateur);
    }
}
