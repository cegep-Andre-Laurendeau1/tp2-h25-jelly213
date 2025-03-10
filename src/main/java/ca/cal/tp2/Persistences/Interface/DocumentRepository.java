package ca.cal.tp2.Persistences.Interface;

import ca.cal.tp2.Models.Document;

import java.util.List;

public interface DocumentRepository {
    List<Document> getDocumentsByPartialTitle(String keyword);

    List<Document> getDocumentsByAuthor(String author);

    List<Document> getDocumentsByYear(int year);
}
