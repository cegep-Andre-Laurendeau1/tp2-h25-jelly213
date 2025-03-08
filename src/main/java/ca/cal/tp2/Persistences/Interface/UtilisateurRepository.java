package ca.cal.tp2.Persistences.Interface;

import ca.cal.tp2.Models.Utilisateur;

public interface UtilisateurRepository {
    Utilisateur getByEmprunteurByName(String prenom, String nom);
}
