package ca.cal.tp2.Services;

import ca.cal.tp2.Models.Document;
import ca.cal.tp2.Models.Emprunt;
import ca.cal.tp2.Models.EmpruntDetail;
import ca.cal.tp2.Models.Emprunteur;
import ca.cal.tp2.Persistences.Interface.DocumentRepository;
import ca.cal.tp2.Persistences.Interface.EmpruntDetailRepository;
import ca.cal.tp2.Persistences.Interface.EmpruntRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EmpruntService {
    private final EmpruntRepository empruntRepository;
    private final EmpruntDetailRepository empruntDetailRepository;
    private final DocumentRepository documentRepository;

    public EmpruntService(EmpruntRepository empruntRepository,
                          EmpruntDetailRepository empruntDetailRepository,
                          DocumentRepository documentRepository) {
        this.empruntRepository = empruntRepository;
        this.empruntDetailRepository = empruntDetailRepository;
        this.documentRepository = documentRepository;
    }

    /**
     * Emprunte un document si celui-ci est disponible
     * @param emprunteur L'emprunteur qui souhaite emprunter le document
     * @param document Le document à emprunter
     * @return L'objet EmpruntDetail créé ou null si l'emprunt a échoué
     */
    public EmpruntDetail emprunterDocument(Emprunteur emprunteur, Document document) {
        // Vérifier que l'emprunteur et le document sont valides
        if (emprunteur == null || document == null) {
            throw new IllegalArgumentException("L'emprunteur et le document ne peuvent pas être null");
        }

        // Vérifier la disponibilité du document
        if (!document.verifierDisponibilite()) {
            return null; // Aucun exemplaire disponible
        }

        // Créer un nouvel emprunt
        Date dateEmprunt = new Date();
        Emprunt emprunt = new Emprunt(emprunteur, dateEmprunt);
        emprunt.setStatus("En cours");
        empruntRepository.save(emprunt);

        // Calculer la date de retour prévue selon le type de document
        Date dateRetourPrevue = calculerDateRetourPrevue(document);

        // Créer le détail d'emprunt pour ce document
        EmpruntDetail empruntDetail = new EmpruntDetail(emprunt, document, dateRetourPrevue);
        empruntDetailRepository.save(empruntDetail);

        // Mettre à jour le nombre d'exemplaires disponibles
        document.setNombreExemplaires(document.getNombreExemplaires() - 1);
        documentRepository.save(document);

        return empruntDetail;
    }

    /**
     * Calcule la date de retour prévue selon le type de document
     * @param document Le document emprunté
     * @return La date de retour prévue
     */
    private Date calculerDateRetourPrevue(Document document) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        // Déterminer la durée d'emprunt selon le type de document
        String typeDocument = document.getType();
        if ("Livre".equals(typeDocument)) {
            // 3 semaines pour les livres
            cal.add(Calendar.WEEK_OF_YEAR, 3);
        } else if ("CD".equals(typeDocument)) {
            // 2 semaines pour les CD
            cal.add(Calendar.WEEK_OF_YEAR, 2);
        } else if ("DVD".equals(typeDocument)) {
            // 1 semaine pour les DVD
            cal.add(Calendar.WEEK_OF_YEAR, 1);
        } else {
            // Par défaut, 2 semaines
            cal.add(Calendar.WEEK_OF_YEAR, 2);
        }

        return cal.getTime();
    }

    /**
     * Retourne la liste de tous les emprunts d'un emprunteur
     * @param emprunteur L'emprunteur dont on veut voir les emprunts
     * @return La liste des emprunts
     */
    public List<Emprunt> getEmpruntsByEmprunteur(Emprunteur emprunteur) {
        return empruntRepository.findByEmprunteur(emprunteur);
    }

    /**
     * Retourne la liste des détails d'emprunt pour un emprunt donné
     * @param emprunt L'emprunt dont on veut voir les détails
     * @return La liste des détails d'emprunt
     */
    public List<EmpruntDetail> getEmpruntDetailsByEmprunt(Emprunt emprunt) {
        return empruntDetailRepository.findByEmprunt(emprunt);
    }

    /**
     * Retourne un document et met à jour le nombre d'exemplaires disponibles
     * @param empruntDetail Le détail d'emprunt concernant le document à retourner
     * @return true si le retour est réussi, false sinon
     */
    public boolean retournerDocument(EmpruntDetail empruntDetail) {
        if (empruntDetail == null) {
            return false;
        }

        // Mettre à jour la date de retour réelle
        empruntDetail.setDateRetourActuelle(new Date());
        empruntDetail.setStatus("Retourné");
        empruntDetailRepository.save(empruntDetail);

        // Mettre à jour le nombre d'exemplaires disponibles
        Document document = empruntDetail.getDocument();
        document.setNombreExemplaires(document.getNombreExemplaires() + 1);
        documentRepository.save(document);

        return true;
    }

    /**
     * Formate l'affichage d'un emprunt avec ses détails
     * @param emprunt L'emprunt à afficher
     * @return Une chaîne formatée contenant les informations de l'emprunt
     */
    public String formaterEmprunt(Emprunt emprunt) {
        if (emprunt == null) {
            return "Emprunt non trouvé";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Emprunt #").append(emprunt.getBorrowId()).append("\n");
        sb.append("Date d'emprunt: ").append(formaterDate(emprunt.getDateEmprunt())).append("\n");
        sb.append("Statut: ").append(emprunt.getStatus()).append("\n");
        sb.append("Emprunteur: ").append(emprunt.getEmprunteur().getName()).append("\n");
        sb.append("\nDocuments empruntés:\n");

        List<EmpruntDetail> details = empruntDetailRepository.findByEmprunt(emprunt);
        for (EmpruntDetail detail : details) {
            Document doc = detail.getDocument();
            sb.append("- ").append(doc.getTitre()).append(" (").append(doc.getType()).append(")\n");
            sb.append("  Date de retour prévue: ").append(formaterDate(detail.getDateRetourPrevue())).append("\n");

            if (detail.getDateRetourActuelle() != null) {
                sb.append("  Date de retour réelle: ").append(formaterDate(detail.getDateRetourActuelle())).append("\n");
            } else {
                sb.append("  Statut: ").append(detail.getStatus()).append("\n");
                if (detail.isEnRetard()) {
                    sb.append("  ⚠️ EN RETARD! Amende potentielle: ").append(String.format("%.2f", detail.calculAmende())).append(" $\n");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Formate une date selon le format spécifié
     * @param date La date à formater
     * @return La date formatée sous forme de chaîne
     */
    private String formaterDate(Date date) {
        if (date == null) {
            return "N/A";
        }
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
}
