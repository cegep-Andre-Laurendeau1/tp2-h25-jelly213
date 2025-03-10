package ca.cal.tp2.Persistences.Interface;

import ca.cal.tp2.Models.Document;
import ca.cal.tp2.Models.Emprunt;
import ca.cal.tp2.Models.EmpruntDetail;

import java.util.List;

public interface EmpruntDetailRepository {
    void save(EmpruntDetail empruntDetail);
    EmpruntDetail findById(Long id);
    List<EmpruntDetail> findByEmprunt(Emprunt emprunt);
    List<EmpruntDetail> findByDocument(Document document);
}