// GameManager.java (Gestion du jeu)
import java.util.Scanner;
import java.util.ArrayList;

public class GameManager {
    private Scanner scanner = new Scanner(System.in);
    private Player player;
    private ShopManager shopManager;

    public void startGame() {
        initializeGame();
        gameLoop();
    }

    private void initializeGame() {
        player = new Player("Joueur");
        shopManager = new ShopManager();
        System.out.println("Bienvenue dans le RPG !");
    }

    private void gameLoop() {
        boolean jeuEnCours = true;
        while (jeuEnCours) {
            System.out.println("\nMenu Principal :");
            System.out.println("[1] Combattre un ennemi");
            System.out.println("[2] Se reposer");
            System.out.println("[3] Acheter un objet");
            System.out.println("[4] Gérer l'inventaire");
            System.out.println("[0] Quitter");

            System.out.print(">>> ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    Enemy enemy = new Enemy("Ennemi", player.getExperience());
                    BattleManager battle = new BattleManager(player, enemy, scanner);
                    battle.startBattle();
                    break;
                case "2":
                    player.rest();
                    break;
                case "3":
                    shopManager.acheterObjet(player);
                    break;
                case "4":
                    InventoryManager.gererInventaire(player);
                    break;
                case "0":
                    System.out.println("Merci d'avoir joué !");
                    jeuEnCours = false;
                    break;
                default:
                    System.out.println("Choix invalide !");
                    break;
            }
        }
    }
}
