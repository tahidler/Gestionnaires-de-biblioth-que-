package Bibliotheque;


import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public class bibliothequeApp {
	
	private static List<Livre> bibliotheque = new ArrayList<>();
	private static List<Utilisateur> utilisateurs = new ArrayList<>();
	private static List<Emprunt> emprunts = new ArrayList<>();
	

	
	public static void main(String[] args) {
	    initialiserDonnees();

	    Scanner scanner = new Scanner(System.in);
	    int choix;

	    do {
	        afficherMenu();
	        choix = scanner.nextInt();
	        scanner.nextLine(); // Pour consommer la nouvelle ligne

	        switch (choix) {
	            case 1:
	                ajouterLivre(scanner);
	                break;
	            case 2:
	                rechercherLivre(scanner);
	                break;
	            case 3:
	                emprunterLivre(scanner);
	                break;
	            case 4:
	                retournerLivre(scanner);
	                break;
	            case 5:
	                afficherEmprunts();
	                break;
	            case 6:
	                afficherLivres();
	                break;
	            case 7:
	                System.out.println("Au revoir !");
	                break;
	            default:
	                System.out.println("Choix invalide. Veuillez réessayer.");
	        }

	    } while (choix != 7);
	}

	private static void afficherMenu() {
	    System.out.println("Menu :");
	    System.out.println("1. Ajouter un livre");
	    System.out.println("2. Rechercher un livre");
	    System.out.println("3. Emprunter un livre");
	    System.out.println("4. Retourner un livre");
	    System.out.println("5. Afficher les emprunts");
	    System.out.println("6. Afficher les livres disponibles");
	    System.out.println("7. Quitter");
	    System.out.print("Entrez votre choix : ");
	}

	private static void ajouterLivre(Scanner scanner) {
	    System.out.print("Titre du livre : ");
	    String titre = scanner.nextLine();
	    System.out.print("Auteur du livre : ");
	    String auteur = scanner.nextLine();
	    System.out.print("ISBN du livre : ");
	    String isbn = scanner.nextLine();

	    Livre livre = new Livre(titre, auteur, isbn);
	    bibliotheque.add(livre);
	    System.out.println("Livre ajouté avec succès !");
	}

	private static void rechercherLivre(Scanner scanner) {
	    System.out.print("Rechercher par (1. Titre, 2. Auteur) : ");
	    int choixRecherche = scanner.nextInt();
	    scanner.nextLine(); // Pour consommer la nouvelle ligne

	    System.out.print("Entrez le terme de recherche : ");
	    String termeRecherche = scanner.nextLine();

	    switch (choixRecherche) {
	        case 1:
	            rechercherParTitre(termeRecherche);
	            break;
	        case 2:
	            rechercherParAuteur(termeRecherche);
	            break;
	        default:
	            System.out.println("Choix de recherche invalide.");
	    }
	}

	private static void rechercherParTitre(String titre) {
	    for (Livre livre : bibliotheque) {
	        if (livre.getTitre().equalsIgnoreCase(titre)) {
	            System.out.println("Livre trouvé :");
	            livre.afficherLivre();
	            return;
	        }
	    }
	    System.out.println("Aucun livre trouvé avec ce titre.");
	}

	private static void rechercherParAuteur(String auteur) {
	    for (Livre livre : bibliotheque) {
	        if (livre.getAuteur().equalsIgnoreCase(auteur)) {
	            System.out.println("Livre trouvé :");
	            livre.afficherLivre();
	            return;
	        }
	    }
	    System.out.println("Aucun livre trouvé avec cet auteur.");
	}

	private static void emprunterLivre(Scanner scanner) {
	    System.out.print("Entrez le nom de l'utilisateur : ");
	    String nomUtilisateur = scanner.nextLine();

	    Utilisateur utilisateur = trouverUtilisateurParNom(nomUtilisateur);

	    if (utilisateur == null) {
	        System.out.println("Utilisateur non trouvé.");
	        return;
	    }

	    System.out.print("Entrez le titre du livre à emprunter : ");
	    String titreLivre = scanner.nextLine();

	    Livre livre = trouverLivreParTitre(titreLivre);

	    if (livre == null) {
	        System.out.println("Livre non trouvé.");
	        return;
	    }

	    if (livre.estEmprunte()) {
	        System.out.println("Ce livre est déjà emprunté.");
	    } else {
	        Date dateEmprunt = new Date();
	        Date dateEcheance = new Date(dateEmprunt.getTime() + (7 * 24 * 60 * 60 * 1000)); // Emprunt de 7 jours

	        Emprunt emprunt = new Emprunt(utilisateur, livre, dateEmprunt, dateEcheance);
	        emprunts.add(emprunt);
	        livre.emprunter();
	        System.out.println("Livre emprunté avec succès !");
	    }
	}

	private static void retournerLivre(Scanner scanner) {
	    System.out.print("Entrez le nom de l'utilisateur : ");
	    String nomUtilisateur = scanner.nextLine();

	    Utilisateur utilisateur = trouverUtilisateurParNom(nomUtilisateur);

	    if (utilisateur == null) {
	        System.out.println("Utilisateur non trouvé.");
	        return;
	    }

	    System.out.print("Entrez le titre du livre à retourner : ");
	    String titreLivre = scanner.nextLine();

	    Livre livre = trouverLivreParTitre(titreLivre);

	    if (livre == null) {
	        System.out.println("Livre non trouvé.");
	        return;
	    }

	    boolean empruntTrouve = false;

	    for (Emprunt emprunt : emprunts) {
	        if (emprunt.getUtilisateur() == utilisateur && emprunt.getLivre() == livre) {
	            emprunts.remove(emprunt);
	            livre.retourner();
	            System.out.println("Livre retourné avec succès !");
	            empruntTrouve = true;
	            break;
	        }
	    }

	    if (!empruntTrouve) {
	        System.out.println("Cet utilisateur n'a pas emprunté ce livre.");
	    }
	}

	private static Utilisateur trouverUtilisateurParNom(String nom) {
	    for (Utilisateur utilisateur : utilisateurs) {
	        if (utilisateur.getNom().equalsIgnoreCase(nom)) {
	            return utilisateur;
	        }
	    }
	    return null;
	}

	private static Livre trouverLivreParTitre(String titre) {
	    for (Livre livre : bibliotheque) {
	        if (livre.getTitre().equalsIgnoreCase(titre)) {
	            return livre;
	        }
	    }
	    return null;
	}

	private static void afficherEmprunts() {
	    System.out.println("Liste des emprunts :");
	    for (Emprunt emprunt : emprunts) {
	        emprunt.afficherEmprunt();
	    }
	}

	private static void afficherLivres() {
	    System.out.println("Liste des livres disponibles :");
	    for (Livre livre : bibliotheque) {
	        if (!livre.estEmprunte()) {
	            livre.afficherLivre();
	        }
	    }
	}

	private static void initialiserDonnees() {
	    // Exemple : Ajouter des utilisateurs
	    utilisateurs.add(new Utilisateur("Utilisateur 1", "ID001"));
	    utilisateurs.add(new Utilisateur("Utilisateur 2", "ID002"));
	    utilisateurs.add(new Utilisateur("Utilisateur 3", "ID003"));
	    utilisateurs.add(new Utilisateur("Utilisateur 4", "ID004"));

	    // Exemple : Ajouter des livres
	    bibliotheque.add(new Livre("Livre 1 Psychologie cognitive", "Auteur   Smith, J. (2019)", " 978-1234567890."));
	    
	    bibliotheque.add(new Livre("Livre 2  Sociologie moderne. McGraw-Hill", "Auteur    Johnson, A. (2020)", "978-9876543210."));
	    
	    bibliotheque.add(new Livre("Livre 3 Le cerveau humain : Une approche complète (2e édition). Wiley", "Auteur   Brown, M. R. (2018)", " 978-5432109876"));
	    
	    bibliotheque.add(new Livre("Livre 4  Économie internationale. Routledge.", "Auteur   Garcia, L. (2021)", "978-0123456789."));
	}
	
	



	// Méthode pour ajouter un livre à la bibliothèque
	private static void ajouterLivre(Livre livre) {
	    bibliotheque.add(livre);
	}

	// Méthode pour rechercher un livre par titre
	private static Livre rechercherLivreParTitre(String titre) {
	    for (Livre livre : bibliotheque) {
	        if (livre.getTitre().equalsIgnoreCase(titre)) {
	            return livre;
	        }
	    }
	    return null;
	}

	// Méthode pour emprunter un livre
	private static void emprunterLivre(Utilisateur utilisateur, Livre livre, Date dateEmprunt, Date dateEcheance) {
	    Emprunt emprunt = new Emprunt(utilisateur, livre, dateEmprunt, dateEcheance);
	    emprunts.add(emprunt);
	    livre.emprunter();
	}

	// Méthode pour retourner un livre
	private static void retournerLivre(Utilisateur utilisateur, Livre livre) {
	    for (Emprunt emprunt : emprunts) {
	        if (emprunt.getUtilisateur() == utilisateur && emprunt.getLivre() == livre) {
	            emprunts.remove(emprunt);
	            livre.retourner();
	            break; // Sortir de la boucle dès que l'emprunt est trouvé
	        }
	    }
	}

}