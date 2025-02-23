import java.util.Scanner;

public class InventoryManager {
    private static Scanner scanner = new Scanner(System.in);

    public static void gererInventaire(Player player) {
        boolean managingInventory = true;
        while (managingInventory) {
            System.out.println("\nGestion de l'inventaire :");
            player.showInventory();
            System.out.println("[0] Retour\n[1] Équiper un objet");

            try {
                int invChoice = scanner.nextInt();
                scanner.nextLine();

                if (invChoice == 0) {
                    managingInventory = false;
                } else if (invChoice == 1) {
                    player.showInventory();
                    System.out.println("Entrez le numéro de l'objet à équiper (0 pour annuler) : ");
                    int equipChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (equipChoice != 0) {
                        player.equipItem(equipChoice);
                    }
                } else {
                    System.out.println("Choix invalide");
                }
            } catch (Exception e) {
                System.out.println("Veuillez entrer un numéro valide");
                scanner.nextLine();
            }
        }
    }
}
