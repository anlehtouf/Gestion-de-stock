import java.util.Scanner;

public class GestionStockApp {
    // Nombre maximum de produits
    private static final int MAX_PRODUITS = 100;

    // Tableaux pour stocker les détails des produits
    private static int[] codesProduits = new int[MAX_PRODUITS]; // Codes uniques des produits
    private static String[] nomsProduits = new String[MAX_PRODUITS]; // Noms des produits
    private static int[] quantites = new int[MAX_PRODUITS]; // Quantités en stock
    private static double[] prix = new double[MAX_PRODUITS]; // Prix unitaires des produits
    private static int nombreProduits = 0; // Nombre actuel de produits dans l'inventaire

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        // Boucle principale du programme
        do {
            afficherMenu(); // Affiche le menu des options
            System.out.print("Choisissez une option : ");
            choix = scanner.nextInt(); // Lecture du choix de l'utilisateur
            scanner.nextLine(); // Consommer la nouvelle ligne

            // Exécution de l'action choisie
            switch (choix) {
                case 1:
                    ajouterProduit(scanner); // Ajouter un produit
                    break;
                case 2:
                    modifierProduit(scanner); // Modifier un produit existant
                    break;
                case 3:
                    supprimerProduit(scanner); // Supprimer un produit
                    break;
                case 4:
                    afficherProduits(); // Afficher tous les produits
                    break;
                case 5:
                    rechercherProduit(scanner); // Rechercher un produit par nom
                    break;
                case 6:
                    calculerValeurStock(); // Calculer la valeur totale du stock
                    break;
                case 7:
                    System.out.println("Fermeture de l'application. Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix != 7); // Répéter jusqu'à ce que l'utilisateur choisisse de quitter

        scanner.close(); // Fermeture du scanner
    }

    // Afficher le menu principal
    private static void afficherMenu() {
        System.out.println("\n=== Menu de Gestion de Stock ===");
        System.out.println("1. Ajouter un produit");
        System.out.println("2. Modifier un produit");
        System.out.println("3. Supprimer un produit");
        System.out.println("4. Afficher tous les produits");
        System.out.println("5. Rechercher un produit");
        System.out.println("6. Calculer la valeur totale du stock");
        System.out.println("7. Quitter");
    }

    // Ajouter un produit à l'inventaire
    private static void ajouterProduit(Scanner scanner) {
        // Vérifier si l'inventaire est plein
        if (nombreProduits >= MAX_PRODUITS) {
            System.out.println("L'inventaire est plein. Impossible d'ajouter plus de produits.");
            return;
        }

        // Lecture des informations du produit
        System.out.print("Entrez le code du produit : ");
        int code = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        System.out.print("Entrez le nom du produit : ");
        String nom = scanner.nextLine();

        System.out.print("Entrez la quantité : ");
        int quantite = scanner.nextInt();

        System.out.print("Entrez le prix : ");
        double prixUnitaire = scanner.nextDouble();

        // Stockage des informations dans les tableaux
        codesProduits[nombreProduits] = code;
        nomsProduits[nombreProduits] = nom;
        quantites[nombreProduits] = quantite;
        prix[nombreProduits] = prixUnitaire;

        nombreProduits++; // Incrémenter le compteur de produits
        System.out.println("Produit ajouté avec succès !");
    }

    // Modifier un produit existant
    private static void modifierProduit(Scanner scanner) {
        System.out.print("Entrez le code du produit à modifier : ");
        int code = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        // Trouver le produit par son code
        int index = trouverProduitParCode(code);
        if (index == -1) {
            System.out.println("Produit non trouvé.");
            return;
        }

        // Lecture des nouvelles informations
        System.out.print("Entrez le nouveau nom : ");
        String nouveauNom = scanner.nextLine();

        System.out.print("Entrez la nouvelle quantité : ");
        int nouvelleQuantite = scanner.nextInt();

        System.out.print("Entrez le nouveau prix : ");
        double nouveauPrix = scanner.nextDouble();

        // Mise à jour des informations dans les tableaux
        nomsProduits[index] = nouveauNom;
        quantites[index] = nouvelleQuantite;
        prix[index] = nouveauPrix;

        System.out.println("Produit modifié avec succès !");
    }

    // Supprimer un produit
    private static void supprimerProduit(Scanner scanner) {
        System.out.print("Entrez le code du produit à supprimer : ");
        int code = scanner.nextInt();

        // Trouver le produit par son code
        int index = trouverProduitParCode(code);
        if (index == -1) {
            System.out.println("Produit non trouvé.");
            return;
        }

        // Déplacer les produits suivants pour remplir le vide
        for (int i = index; i < nombreProduits - 1; i++) {
            codesProduits[i] = codesProduits[i + 1];
            nomsProduits[i] = nomsProduits[i + 1];
            quantites[i] = quantites[i + 1];
            prix[i] = prix[i + 1];
        }

        nombreProduits--; // Réduire le compteur de produits
        System.out.println("Produit supprimé avec succès !");
    }

    // Afficher tous les produits
    private static void afficherProduits() {
        if (nombreProduits == 0) {
            System.out.println("Aucun produit en stock.");
            return;
        }

        System.out.println("\n=== Liste des Produits ===");
        for (int i = 0; i < nombreProduits; i++) {
            System.out.printf("Code : %d, Nom : %s, Quantité : %d, Prix : %.2f%n",
                    codesProduits[i], nomsProduits[i], quantites[i], prix[i]);
        }
    }

    // Rechercher un produit par son nom
    private static void rechercherProduit(Scanner scanner) {
        System.out.print("Entrez le nom du produit à rechercher : ");
        String nom = scanner.nextLine();

        System.out.println("\n=== Résultats de Recherche ===");
        boolean trouve = false;

        // Parcourir les produits pour trouver des correspondances
        for (int i = 0; i < nombreProduits; i++) {
            if (nomsProduits[i].equalsIgnoreCase(nom)) {
                System.out.printf("Code : %d, Nom : %s, Quantité : %d, Prix : %.2f%n",
                        codesProduits[i], nomsProduits[i], quantites[i], prix[i]);
                trouve = true;
            }
        }

        if (!trouve) {
            System.out.println("Aucun produit trouvé avec ce nom.");
        }
    }

    // Calculer la valeur totale du stock
    private static void calculerValeurStock() {
        double valeurTotale = 0;

        // Ajouter les valeurs de chaque produit
        for (int i = 0; i < nombreProduits; i++) {
            valeurTotale += quantites[i] * prix[i];
        }

        System.out.printf("Valeur totale du stock : %.2f%n", valeurTotale);
    }

    // Trouver un produit par son code
    private static int trouverProduitParCode(int code) {
        for (int i = 0; i < nombreProduits; i++) {
            if (codesProduits[i] == code) {
                return i; // Retourne l'indice du produit trouvé
            }
        }
        return -1; // Retourne -1 si le produit n'est pas trouvé
    }
}
