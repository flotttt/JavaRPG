import java.util.ArrayList;
import java.util.HashMap;

public class Player extends Character {
    private static final int MAX_HEALTH = 100;
    private static final int EXPERIENCE = 0;
    private static final int SKILLS_NUMBER = 1;
    private static final double ESCAPE_CHANCE = 0.35; // 35% de chance de fuir

    private int gold = 50;
    private ArrayList<String> inventory;
    private String equippedItem;
    private HashMap<String, Integer> itemBonuses;

    public int attackSkillsNumber, defenseSkillsNumber;

    public Player(String name) {
        super(name, MAX_HEALTH, EXPERIENCE);
        this.attackSkillsNumber = 0;
        this.defenseSkillsNumber = SKILLS_NUMBER;
        this.gold = 100;
        this.inventory = new ArrayList<>();
        this.equippedItem = "Aucun";

        this.itemBonuses = new HashMap<>();
        this.itemBonuses.put("Épée en fer", 10);
        this.itemBonuses.put("Bouclier en bois", 5);
    }

    @Override
    public void attack(Character target) {
        if (!(target instanceof Enemy enemy)) {
            System.out.println("Impossible d'attaquer cette cible.");
            return;
        }

        int baseAttack = (int) (Math.random() * ((double) this.getExperience() / 4 + attackSkillsNumber * 3 + 3) +
                (double) this.getExperience() / 10 + attackSkillsNumber * 2 + defenseSkillsNumber + 1);

        int bonus = itemBonuses.getOrDefault(this.equippedItem, 0);
        int totalAttack = baseAttack + bonus;

        System.out.println(this.getName() + " attaque " + enemy.getName() + " et inflige " + totalAttack + " dégâts !");
        enemy.takeDamage(totalAttack);
    }

    @Override
    public int defend() {
        int baseDefense = (int) (Math.random() * ((double) this.getExperience() / 4 + defenseSkillsNumber * 3 + 3) +
                (double) this.getExperience() / 10 + defenseSkillsNumber * 2 + attackSkillsNumber + 1);

        int bonus = itemBonuses.getOrDefault(this.equippedItem, 0);
        return baseDefense + bonus;
    }


    public boolean fuir() {
        if (Math.random() < ESCAPE_CHANCE) {
            System.out.println(this.getName() + " s'échappe avec succès !");
            return true;
        } else {
            System.out.println(this.getName() + " tente de fuir mais échoue !");
            return false;
        }
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
        }
        System.out.println("Pas assez d'or !");
        return false;
    }

    public void addItem(String item) {
        inventory.add(item);
        System.out.println(item + " ajouté à votre inventaire.");
    }

    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Votre inventaire est vide.");
        } else {
            for (int i = 0; i < inventory.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + inventory.get(i));
            }
        }
    }

    public void equipItem(int itemIndex) {
        if (itemIndex < 1 || itemIndex > inventory.size()) {
            System.out.println("Choix invalide.");
            return;
        }

        String itemToEquip = inventory.get(itemIndex - 1);
        if (!itemBonuses.containsKey(itemToEquip)) {
            System.out.println("Cet objet ne peut pas être équipé.");
            return;
        }

        this.equippedItem = itemToEquip;
        System.out.println("Vous avez équipé : " + this.equippedItem);
    }

    public void takeDamage(int damage) {
        this.decreaseHealth(damage);
        System.out.println(this.getName() + " a perdu " + damage + " points de vie.");
    }

    public void gagnerExperience(int experience) {
        this.increaseExperience(experience);
        System.out.println(this.getName() + " gagne " + experience + " points d'expérience !");
    }
}