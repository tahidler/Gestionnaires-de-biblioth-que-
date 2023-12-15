package Bibliotheque;

//Classe Utilisateur pour représenter les utilisateurs
class Utilisateur {
private String nom;
private String numeroIdentification;

public Utilisateur(String nom, String numeroIdentification) {
    this.nom = nom;
    this.numeroIdentification = numeroIdentification;
}

public String getNom() {
    return nom;
}

public String getNumeroIdentification() {
    return numeroIdentification;
}

}