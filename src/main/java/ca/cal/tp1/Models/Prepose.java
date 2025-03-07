package ca.cal.tp1.Models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("Prepose")
public class Prepose extends Utilisateur {
   public Prepose(String nom, String prenom){
         super(nom, prenom);
   }
}