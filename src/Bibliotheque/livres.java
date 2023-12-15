package Bibliotheque;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

class Livre {
private String titre;
private String auteur;
private String ISBN;
private boolean estEmprunte;

public Livre(String titre, String auteur, String ISBN) {
    this.titre = titre;
    this.auteur = auteur;
    this.ISBN = ISBN;
    this.estEmprunte = false;
}

// Getters et setters pour les attributs

public String getTitre() {
    return titre;
}

public String getAuteur() {
    return auteur;
}

public String getISBN() {
    return ISBN;
}

public boolean estEmprunte() {
    return estEmprunte;
}

public void emprunter() {
    estEmprunte = true;
}

public void retourner() {
    estEmprunte = false;
}

// Méthode pour afficher les détails du livre
public void afficherLivre() {
    System.out.println("Titre : " + titre);
    System.out.println("Auteur : " + auteur);
    System.out.println("ISBN : " + ISBN);
    System.out.println("Statut d'emprunt : " + (estEmprunte ? "Emprunté" : "Disponible"));
}


}

