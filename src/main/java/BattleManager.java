import java.util.Scanner;

public class BattleManager {
    private Player player;
    private Enemy enemy;
    private Scanner scanner;

    public BattleManager(Player player, Enemy enemy, Scanner scanner) {
        this.player = player;
        this.enemy = enemy;
        this.scanner = scanner;
    }


    public boolean startBattle() {
        System.out.println("\nDébut du combat !");

        while (!player.isDead() && !enemy.isDead()) {
            afficherEtat();
            afficherOptionsCombat();

            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    player.attack(enemy);
                    if (!enemy.isDead()) {
                        enemy.attack(player);
                    }
                    break;
                case "2":
                    if (player.fuir()) {
                        System.out.println("Vous avez réussi à fuir !");
                        return false;
                    } else {
                        System.out.println("La fuite a échoué ! L'ennemi attaque.");
                        enemy.attack(player);
                    }
                    break;
                case "3":
                    utiliserObjet();
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
                    break;
            }


            if (player.isDead()) {
                System.out.println(" Vous avez été vaincu !");
                return false;
            } else if (enemy.isDead()) {
                int goldEarned = enemy.getGoldReward();
                player.addGold(goldEarned);
                player.gagnerExperience(enemy.getExperience());
                System.out.println("Vous avez vaincu l'ennemi !");
                System.out.println("Vous gagnez " + goldEarned + " pièces d'or !");
                System.out.println("Vous avez gagné " + enemy.getExperience() + " points d'expérience !");
                return false;
            }
        }

        return true;
    }

    private void afficherEtat() {
        System.out.println("\n État des combattants :");
        System.out.println(player);
        System.out.println(enemy);
    }

    private void afficherOptionsCombat() {
        System.out.println("\n Actions disponibles :");
        System.out.println("[1] Attaquer");
        System.out.println("[2] Fuir");
        System.out.println("[3] Utiliser un objet");
        System.out.print(">>> ");
    }

    private void utiliserObjet() {
        System.out.println("\n Inventaire du joueur :");
        player.showInventory();
        System.out.println("[0] Retour");

        System.out.print("Entrez le numéro de l'objet à utiliser : ");
        try {
            int choixObjet = scanner.nextInt();
            scanner.nextLine();
            if (choixObjet != 0) {
                player.equipItem(choixObjet);
            }
        } catch (Exception e) {
            System.out.println(" Entrée invalide !");
            scanner.nextLine();
        }
    }
}