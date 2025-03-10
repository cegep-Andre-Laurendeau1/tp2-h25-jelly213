package ca.cal.tp2.Services;

import ca.cal.tp2.Models.*;
import ca.cal.tp2.Persistences.Interface.DocumentRepository;
import ca.cal.tp2.Persistences.Interface.EmpruntDetailRepository;
import ca.cal.tp2.Persistences.Interface.EmpruntRepository;

import java.text.SimpleDateFormat;
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
     * Génère une chaîne de caractères formatée contenant tous les emprunts d'un client
     * @param emprunteur Le client dont on veut afficher les emprunts
     * @return Une chaîne de caractères formatée
     */
    public String afficherEmpruntsClient(Emprunteur emprunteur) {
        if (emprunteur == null) {
            return "Client non spécifié";
        }

        List<Emprunt> emprunts = empruntRepository.findByEmprunteur(emprunteur);

        if (emprunts.isEmpty()) {
            return "Le client " + emprunteur.getName() + " n'a aucun emprunt en cours.";
        }

        StringBuilder sb = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        sb.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        sb.append("📚 LISTE DES EMPRUNTS DE ").append(emprunteur.getName().toUpperCase()).append(" 📚\n");
        sb.append("Email: ").append(emprunteur.getEmail()).append("\n");
        sb.append("Téléphone: ").append(emprunteur.getPhoneNumber()).append("\n");
        sb.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n\n");

        for (Emprunt emprunt : emprunts) {
            sb.append("EMPRUNT #").append(emprunt.getBorrowId()).append("\n");
            sb.append("Date d'emprunt: ").append(dateFormat.format(emprunt.getDateEmprunt())).append("\n");
            sb.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

            List<EmpruntDetail> details = empruntDetailRepository.findByEmprunt(emprunt);
            if (details.isEmpty()) {
                sb.append("Aucun document associé à cet emprunt.\n");
            } else {
                sb.append("DOCUMENTS EMPRUNTÉS:\n\n");

                for (EmpruntDetail detail : details) {
                    Document doc = detail.getDocument();

                    sb.append("- Titre: ").append(doc.getTitre()).append("\n");
                    sb.append("  Type: ").append(doc.getType()).append("\n");
                    sb.append("  Date de retour prévue: ").append(dateFormat.format(detail.getDateRetourPrevue())).append("\n");

                    // Calculer le nombre de jours restants
                    long diffTime = detail.getDateRetourPrevue().getTime() - new Date().getTime();
                    long diffDays = diffTime / (1000 * 60 * 60 * 24);

                    if (detail.getDateRetourActuelle() != null) {
                        sb.append("  Date de retour effective: ").append(dateFormat.format(detail.getDateRetourActuelle())).append("\n");

                        // Vérifier si retourné en retard
                        if (detail.getDateRetourActuelle().after(detail.getDateRetourPrevue())) {
                            sb.append("  ⚠️ RETOURNÉ EN RETARD ⚠️\n");
                        } else {
                            sb.append("  ✅ Retourné à temps\n");
                        }
                    } else if (diffDays < 0) {
                        sb.append("  ⚠️ EN RETARD DE ").append(Math.abs(diffDays)).append(" JOURS! ⚠️\n");
                        sb.append("  Amende potentielle: ").append(String.format("%.2f", detail.calculAmende())).append(" $\n");
                    } else {
                        sb.append("  À retourner dans: ").append(diffDays).append(" jours\n");
                    }

                    if (doc.getType().equals("Livre")) {
                        sb.append("  Auteur: ").append(((Livre)doc).getAuteur()).append("\n");
                    } else if (doc.getType().equals("CD")) {
                        sb.append("  Artiste: ").append(((CD)doc).getArtiste()).append("\n");
                    } else if (doc.getType().equals("DVD")) {
                        sb.append("  Réalisateur: ").append(((DVD)doc).getDirector()).append("\n");
                    }

                    sb.append("\n");
                }
            }

            sb.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n\n");
        }

        return sb.toString();
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
