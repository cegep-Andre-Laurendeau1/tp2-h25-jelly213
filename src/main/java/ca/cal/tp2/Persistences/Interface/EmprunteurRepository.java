package ca.cal.tp2.Persistences.Interface;

import ca.cal.tp2.Models.Emprunteur;

public interface EmprunteurRepository {
    void save(Emprunteur emprunteur);
    Emprunteur findById(Long id);
    Emprunteur findByName(String name);
}