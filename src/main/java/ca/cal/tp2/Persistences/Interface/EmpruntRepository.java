package ca.cal.tp2.Persistences.Interface;

import ca.cal.tp2.Models.Emprunt;
import ca.cal.tp2.Models.Emprunteur;

import java.util.List;

public interface EmpruntRepository {
    void save(Emprunt emprunt);
    Emprunt findById(Long id);
    List<Emprunt> findByEmprunteur(Emprunteur emprunteur);
}
