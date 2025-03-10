package ca.cal.tp2.Persistences.Interface;

import ca.cal.tp2.Models.Livre;

public interface LivreRepository {
    void save(Livre livre);
    Livre findById(Long id);
}