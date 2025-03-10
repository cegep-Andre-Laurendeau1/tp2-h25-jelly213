package ca.cal.tp2.Persistences.Interface;

import ca.cal.tp2.Models.CD;

public interface CDRepository {
    void save(CD cd);
    CD findById(Long id);
}