package ca.cal.tp2.Services.DTO;

import ca.cal.tp2.Models.Document;

public record DocumentDTO(long id, String titre , String auteur, String editeur, int aneePublication, boolean disponible, int dureeMaxEmprunt, int nombreExemplaires, String type, int nombrePages, int dureeDVD, int dureeCD) {
    public static DocumentDTO toDTO(Document document) {
        return new DocumentDTO(
                document.getId(),
                document.getTitre(),
                document.getAuteur(),
                document.getEditeur(),
                document.getAnneePublication(),
                document.isDisponible(),
                document.getDureeMaxEmprunt(),
                document.getNombreExemplaires(),
                document.getType(), document.getNombrePages(), document.getDureeDVD(), document.getDureeCD());
    }
}
