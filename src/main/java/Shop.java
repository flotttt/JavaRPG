import java.util.ArrayList;
import java.util.HashMap;

public class Shop {
    private ArrayList<String> itemNames;
    private HashMap<String, Integer> itemPrices;

    public Shop() {
        itemNames = new ArrayList<>();
        itemPrices = new HashMap<>();

        itemNames.add("Potion de soin");
        itemPrices.put("Potion de soin", 20);

        itemNames.add("Épée en fer");
        itemPrices.put("Épée en fer", 50);

        itemNames.add("Bouclier en bois");
        itemPrices.put("Bouclier en bois", 40);
    }

    public void showItems(Player player) {
        System.out.println("Bienvenue dans la boutique !");
        System.out.println("Vous avez " + player.getGold() + " pièces d'or.");
        for (int i = 0; i < itemNames.size(); i++) {
            System.out.println((i + 1) + " - " + itemNames.get(i) + " - " + itemPrices.get(itemNames.get(i)) + " pièces d'or");
        }
    }

    public String buyItem(Player player, int itemNumber) {
        if (itemNumber < 1 || itemNumber > itemNames.size()) {
            System.out.println("Choix invalide.");
            return null;
        }

        String selectedItem = itemNames.get(itemNumber - 1);
        int price = itemPrices.get(selectedItem);

        if (player.spendGold(price)) {
            System.out.println("Vous avez acheté : " + selectedItem);
            player.addItem(selectedItem);
            return selectedItem;
        } else {
            System.out.println("Pas assez d'or !");
            return null;
        }
    }
}