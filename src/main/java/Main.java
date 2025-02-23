import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Level topLevel = new Level(
                "L'ombre du passé",
                "Il était une fois un village perdu dans les montagnes, où une légende ancienne hantait encore les esprits...",
                "Ainsi, le mystère de l’ombre du passé fut levé, laissant derrière lui un village enfin en paix."
        );

        ArrayList<Level> levels = new ArrayList<>();
        levels.add(new Level("Les murmures de la forêt", "Alors que Léa s’aventurait seule...", "Les esprits disparurent..."));
        levels.add(new Level("Le dernier train pour Minuit", "Paul monta à bord du train...", "Le train s’effaça..."));
        levels.add(new Level("Le secret du vieux phare", "Par une nuit brumeuse...", "Un trésor inestimable fut révélé."));

        Story story = new Story(topLevel, levels);
        System.out.println("Nombre de niveaux : " + story.getLevelsNumber());

        while (!story.isLastLevel()) {
            System.out.println("Niveau " + story.getCurrentLevelNumber() + " : " + story.getCurrentLevel().getTitle());
            story.nextLevel();
        }

        Player player = new Player("Joueur");
        Enemy enemy = new Enemy("Ennemi", player.getExperience());

        Shop shop = new Shop();

        ArrayList<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(1, "Combattre", "1"));
        menuItems.add(new MenuItem(2, "Utiliser un élixir", "useElixir", false));
        menuItems.add(new MenuItem(3, "Prendre la fuite", "2"));
        menuItems.add(new MenuItem(4, "Se reposer", "rest"));
        menuItems.add(new MenuItem(5, "Acheter un objet", "purchase"));
        menuItems.add(new MenuItem(6, "Gérer l'inventaire", "inventory")); // Fusionné

        Menu fightMenu = new Menu("Choisir une action", menuItems);

        boolean combatEnCours = true;
        while (combatEnCours) {
            System.out.println(player);
            System.out.println(enemy);

            String choix = fightMenu.exec(scanner);

            switch (choix) {
                case "1":
                    combatEnCours = player.fight(enemy, player.getBATTLE_MODE());
                    break;
                case "2":
                    combatEnCours = player.fight(enemy, player.getESCAPE_MODE());
                    break;
                case "rest":
                    player.rest();
                    break;
                case "purchase":
                    boolean shopping = true;
                    while (shopping) {
                        shop.showItems();
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
                    break;
                case "inventory":
                    boolean managingInventory = true;
                    while (managingInventory) {
                        System.out.println("\n📜 Gestion de l'inventaire :");
                        player.showInventory();
                        System.out.println("[0] Retour\n[1] Équiper un objet");

                        try {
                            int invChoice = scanner.nextInt();
                            scanner.nextLine();

                            if (invChoice == 0) {
                                managingInventory = false;
                            } else if (invChoice == 1) {
                                player.showInventory();
                                System.out.println("Entrez le numéro de l'objet que vous voulez équiper (0 pour annuler) : ");
                                int equipChoice = scanner.nextInt();
                                scanner.nextLine();

                                if (equipChoice != 0) {
                                    player.equipItem(equipChoice);
                                }
                            } else {
                                System.out.println("Choix invalide.");
                            }
                        } catch (Exception e) {
                            System.out.println("Veuillez entrer un numéro valide !");
                            scanner.nextLine();
                        }
                    }
                    break;
                default:
                    System.out.println("Choix invalide.");
                    break;
            }

            if (player.isDead()) {
                System.out.println("Vous avez été vaincu !");
                combatEnCours = false;
            } else if (enemy.isDead()) {
                System.out.println("Vous avez vaincu l'ennemi !");
                combatEnCours = false;
            }
        }
    }
}