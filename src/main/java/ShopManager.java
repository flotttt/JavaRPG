import java.util.Scanner;

public class ShopManager {
    private Scanner scanner = new Scanner(System.in);
    private Shop shop;

    public ShopManager() {
        this.shop = new Shop();
    }

    public void acheterObjet(Player player) {
        boolean shopping = true;
        while (shopping) {
            shop.showItems(player);
            System.out.println("Entrez le numéro de l'objet que vous voulez acheter (0 pour quitter) : ");

            try {
                int itemNumber = scanner.nextInt();
                scanner.nextLine();

                if (itemNumber == 0) {
                    shopping = false;
                } else {
                    String itemName = shop.buyItem(player, itemNumber);
                    if (itemName != null) {
                        player.addItem(itemName);
                    }
                }
            } catch (Exception e) {
                System.out.println("Veuillez entrer un numéro valide !");
                scanner.nextLine();
            }
        }
    }
}
