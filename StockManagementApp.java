import java.util.Scanner;

public class StockManagementApp {
    private static final int MAX_PRODUITS = 100;

    // Tableaux pour stocker les détails des produits
    private static int[] codesProduits = new int[MAX_PRODUITS];
    private static String[] nomsProduits = new String[MAX_PRODUITS];
    private static int[] quantites = new int[MAX_PRODUITS];
    private static double[] prix = new double[MAX_PRODUITS];
    private static int nombreProduits = 0; // Nombre de produits en stock

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            afficherMenu();
            System.out.print("Choisissez une option : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    ajouterProduit(scanner);
                    break;
                case 2:
                    modifierProduit(scanner);
                    break;
                case 3:
                    supprimerProduit(scanner);
                    break;
                case 4:
                    afficherProduits();
                    break;
                case 5:
                    rechercherProduit(scanner);
                    break;
                case 6:
                    calculerValeurStock();
                    break;
                case 7:
                    System.out.println("Fermeture de l'application. Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix != 7);

        scanner.close();
    }

    // Afficher le menu
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

    // Ajouter un produit
    private static void ajouterProduit(Scanner scanner) {
        if (nombreProduits >= MAX_PRODUITS) {
            System.out.println("L'inventaire est plein. Impossible d'ajouter plus de produits.");
            return;
        }

        System.out.print("Entrez le code du produit : ");
        int code = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        System.out.print("Entrez le nom du produit : ");
        String nom = scanner.nextLine();

        System.out.print("Entrez la quantité : ");
        int quantite = scanner.nextInt();

        System.out.print("Entrez le prix : ");
        double prixUnitaire = scanner.nextDouble();

        codesProduits[nombreProduits] = code;
        nomsProduits[nombreProduits] = nom;
        quantites[nombreProduits] = quantite;
        prix[nombreProduits] = prixUnitaire;

        nombreProduits++;
        System.out.println("Produit ajouté avec succès !");
    }

    // Modifier un produit
    private static void modifierProduit(Scanner scanner) {
        System.out.print("Entrez le code du produit à modifier : ");
        int code = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        int index = trouverProduitParCode(code);
        if (index == -1) {
            System.out.println("Produit non trouvé.");
            return;
        }

        System.out.print("Entrez le nouveau nom : ");
        String nouveauNom = scanner.nextLine();

        System.out.print("Entrez la nouvelle quantité : ");
        int nouvelleQuantite = scanner.nextInt();

        System.out.print("Entrez le nouveau prix : ");
        double nouveauPrix = scanner.nextDouble();

        nomsProduits[index] = nouveauNom;
        quantites[index] = nouvelleQuantite;
        prix[index] = nouveauPrix;

        System.out.println("Produit modifié avec succès !");
    }

    // Supprimer un produit
    private static void supprimerProduit(Scanner scanner) {
        System.out.print("Entrez le code du produit à supprimer : ");
        int code = scanner.nextInt();

        int index = trouverProduitParCode(code);
        if (index == -1) {
            System.out.println("Produit non trouvé.");
            return;
        }

        for (int i = index; i < nombreProduits - 1; i++) {
            codesProduits[i] = codesProduits[i + 1];
            nomsProduits[i] = nomsProduits[i + 1];
            quantites[i] = quantites[i + 1];
            prix[i] = prix[i + 1];
        }

        nombreProduits--;
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

        for (int i = 0; i < nombreProduits; i++) {
            valeurTotale += quantites[i] * prix[i];
        }

        System.out.printf("Valeur totale du stock : %.2f%n", valeurTotale);
    }

    // Méthode pour trouver un produit par son code
    private static int trouverProduitParCode(int code) {
        for (int i = 0; i < nombreProduits; i++) {
            if (codesProduits[i] == code) {
                return i;
            }
        }
        return -1;
    }
}
