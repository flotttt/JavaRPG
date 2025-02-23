import java.util.ArrayList;
import java.util.HashMap;

public class Player extends Character {
    private static final int MAX_HEALTH = 100;
    private static final int EXPERIENCE = 0;
    private static final int SKILLS_NUMBER = 1;
    private int gold;
    private ArrayList<String> inventory;
    private String equippedItem;
    private HashMap<String, Integer> itemBonuses;

    public int attackSkillsNumber, defenseSkillsNumber;

    public Player(String name) {
        super(name, Player.MAX_HEALTH, Player.EXPERIENCE);
        this.attackSkillsNumber = 0;
        this.defenseSkillsNumber = Player.SKILLS_NUMBER;
        this.gold = 100;
        this.inventory = new ArrayList<>();
        this.equippedItem = "Aucun";

        // Définition des bonus d'équipement
        this.itemBonuses = new HashMap<>();
        this.itemBonuses.put("Épée en fer", 10);  // +10 dégâts
        this.itemBonuses.put("Bouclier en bois", 5);  // +5 défense
    }

    @Override
    public int attack() {
        int baseAttack = (int) (Math.random() * ((double) this.getExperience() / 4 + attackSkillsNumber * 3 + 3) +
                (double) this.getExperience() / 10 + attackSkillsNumber * 2 + defenseSkillsNumber + 1);

        // Ajout du bonus si un équipement est équipé
        int bonus = itemBonuses.getOrDefault(this.equippedItem, 0);
        return baseAttack + bonus;
    }

    @Override
    public int defend() {
        int baseDefense = (int) (Math.random() * ((double) this.getExperience() / 4 + defenseSkillsNumber * 3 + 3) +
                (double) this.getExperience() / 10 + defenseSkillsNumber * 2 + attackSkillsNumber + 1);

        // Ajout du bonus de défense si un bouclier est équipé
        int bonus = itemBonuses.getOrDefault(this.equippedItem, 0);
        return baseDefense + bonus;
    }

    public void rest() {
        int healAmount = 20;
        this.increaseHealth(healAmount);
        System.out.println(this.getName() + " se repose et récupère " + healAmount + " points de vie.");
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int amount) {
        this.gold += amount;
    }

    public boolean spendGold(int amount) {
        if (this.gold >= amount) {
            this.gold -= amount;
            return true;
        } else {
            System.out.println("Pas assez d'or !");
            return false;
        }
    }

    public void addItem(String item) {
        inventory.add(item);
        System.out.println(item + " ajouté à votre inventaire.");
    }

    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Votre inventaire est vide.");
        } else {
            System.out.println("Inventaire :");
            for (int i = 0; i < inventory.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + inventory.get(i));
            }
        }
    }

    public void equipItem(int itemIndex) {
        if (itemIndex < 1 || itemIndex > inventory.size()) {
            System.out.println("Choix invalide. Veuillez choisir un numéro correct.");
            return;
        }
        this.equippedItem = inventory.get(itemIndex - 1);
        System.out.println("Vous avez équipé : " + this.equippedItem);
    }

    public void showEquipment() {
        System.out.println("Équipement actuel : " + this.equippedItem);
    }
}