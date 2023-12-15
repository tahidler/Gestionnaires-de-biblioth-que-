package Bibliotheque;

import java.util.Date;

//Classe Emprunt pour représenter les emprunts
class Emprunt {
private Utilisateur utilisateur;
private Livre livre;
private Date dateEmprunt;
private Date dateEcheance;



public Emprunt(Utilisateur utilisateur, Livre livre, Date dateEmprunt, Date dateEcheance) {
    this.utilisateur = utilisateur;
    this.livre = livre;
    this.dateEmprunt = dateEmprunt;
    this.dateEcheance = dateEcheance;
}

public Utilisateur getUtilisateur() {
    return utilisateur;
}

public Livre getLivre() {
    return livre;
}

public Date getDateEmprunt() {
    return dateEmprunt;
}

public Date getDateEcheance() {
    return dateEcheance;
}

// Méthode pour afficher les détails de l'emprunt
public void afficherEmprunt() {
    System.out.println("Utilisateur : " + utilisateur.getNom()); // Assurez-vous que votre classe Utilisateur a une méthode getNom() appropriée
    System.out.println("Livre emprunté : " + livre.getTitre());
    System.out.println("Date d'emprunt : " + dateEmprunt);
    System.out.println("Date d'échéance : " + dateEcheance);
    // Ajoutez d'autres détails de l'emprunt ici si nécessaire
}
}
