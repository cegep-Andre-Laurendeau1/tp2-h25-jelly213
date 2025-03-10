package ca.cal.tp2.Services;

import ca.cal.tp2.Models.Document;
import ca.cal.tp2.Persistences.DocumentRepositoryJPA;
import ca.cal.tp2.Services.DTO.DocumentDTO;

import java.util.List;
import java.util.stream.Collectors;

public class DocumentService {
    private final DocumentRepositoryJPA documentRepository;
    public DocumentService (
        DocumentRepositoryJPA documentRepository
    ) {
        this.documentRepository = documentRepository;
    }

    // Recherche par titre (partielle)
    public List<DocumentDTO> getDocumentsByPartialTitle(String keyword) {
        List<Document> documents = documentRepository.getDocumentsByPartialTitle(keyword);
        return documents.stream().map(DocumentDTO::toDTO).collect(Collectors.toList());
    }

    // Recherche par auteur
    public List<DocumentDTO> getDocumentsByAuthor(String author) {
        List<Document> documents = documentRepository.getDocumentsByAuthor(author);
        return documents.stream().map(DocumentDTO::toDTO).collect(Collectors.toList());
    }

    // Recherche par année
    public List<DocumentDTO> getDocumentsByYear(int year) {
        List<Document> documents = documentRepository.getDocumentsByYear(year);
        return documents.stream().map(DocumentDTO::toDTO).collect(Collectors.toList());
    }

    // Formatage de la liste de documents
    public String formatDocumentList(List<DocumentDTO> documents) {
        if (documents.isEmpty()) {
            return "Aucun livre trouvé.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("📚 Liste des Livres Trouvés 📚\n");
        sb.append("--------------------------------------------------\n");

        for (DocumentDTO doc : documents) {
            sb.append("📖 Titre : ").append(doc.titre()).append("\n");
            sb.append("✍️ Auteur : ").append(doc.auteur()).append("\n");
            sb.append("🏢 Éditeur : ").append(doc.editeur()).append("\n");
            sb.append("📅 Année : ").append(doc.aneePublication()).append("\n");
            sb.append("📌 Disponible : ").append(doc.disponible() ? "Oui" : "Non").append("\n");
            sb.append("📑 Nombre d'exemplaires : ").append(doc.nombreExemplaires()).append("\n");
            sb.append("--------------------------------------------------\n");
        }

        return sb.toString();
    }

    // 🔍 Recherche par titre avec affichage formaté
    public String getDocumentsByPartialTitleFormatted(String keyword) {
        List<DocumentDTO> documents = getDocumentsByPartialTitle(keyword);
        return formatDocumentList(documents);
    }

    // 🔍 Recherche par auteur avec affichage formaté
    public String getDocumentsByAuthorFormatted(String author) {
        List<DocumentDTO> documents = getDocumentsByAuthor(author);
        return formatDocumentList(documents);
    }

    // 🔍 Recherche par année avec affichage formaté
    public String getDocumentsByYearFormatted(int year) {
        List<DocumentDTO> documents = getDocumentsByYear(year);
        return formatDocumentList(documents);
    }

}
