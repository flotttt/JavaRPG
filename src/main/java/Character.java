/**
 * Classe abstraite représentant un personnage dans le jeu RPG.
 * Cette classe définit les attributs et méthodes communs à tous les personnages,
 * tels que la santé, l'expérience et les actions de combat.
 */
public abstract class Character {
    /** Mode de combat : attaque */
    private final String BATTLE_MODE = "1";
    /** Mode de combat : fuite */
    private final String ESCAPE_MODE = "2";

    /** Nom du personnage */
    private String name;
    /** Points de vie actuels du personnage */
    private int health;
    /** Points de vie maximum du personnage */
    private int maxHealth;
    /** Expérience accumulée par le personnage */
    private int experience;

    /**
     * Constructeur de la classe Character.
     * Initialise le personnage avec un nom, une santé maximale et une expérience initiale.
     *
     * @param name       Nom du personnage.
     * @param maxHealth  Points de vie maximum.
     * @param experience Expérience initiale.
     */
    public Character(String name, int maxHealth, int experience) {
        this.name = name;
        this.health = this.maxHealth = maxHealth;
        this.experience = experience;
    }

    /**
     * Obtient le nom du personnage.
     *
     * @return Le nom du personnage.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtient la santé actuelle du personnage.
     *
     * @return Le nombre de points de vie actuels.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Obtient l'expérience du personnage.
     *
     * @return Le nombre de points d'expérience accumulés.
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Vérifie si le personnage est mort.
     *
     * @return {@code true} si la santé est inférieure ou égale à 0, sinon {@code false}.
     */
    public boolean isDead() {
        return this.health <= 0;
    }

    /**
     * Diminue la santé du personnage.
     *
     * @param points Nombre de points de vie à retirer.
     */
    public void decreaseHealth(int points) {
        this.health -= points;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    /**
     * Augmente la santé du personnage, sans dépasser la limite maximale.
     *
     * @param points Nombre de points de vie à ajouter.
     */
    public void increaseHealth(int points) {
        this.health += points;
        if (this.health > this.maxHealth) {
            this.health = this.maxHealth;
        }
    }

    /**
     * Augmente l'expérience du personnage.
     *
     * @param points Nombre de points d'expérience à ajouter.
     */
    public void increaseExperience(int points) {
        this.experience += points;
    }

    /**
     * Gère le combat entre deux personnages.
     *
     * @param character L'adversaire du combat.
     * @param mode      Le mode de combat : "1" pour attaquer, "2" pour tenter de fuir.
     * @return {@code true} si le combat continue, {@code false} s'il est terminé.
     */
    public boolean fight(Character character, String mode) {
        switch (mode) {
            case BATTLE_MODE:
                this.attack(character);
                if (!character.isDead()) {
                    character.attack(this);
                }
                break;

            case ESCAPE_MODE:
                if (Math.random() < 0.35) {
                    System.out.println(this.name + " s'échappe avec succès !");
                    return false;
                } else {
                    System.out.println(this.name + " n'a pas réussi à fuir !");
                    character.attack(this);
                }
                break;
        }
        return !this.isDead() && !character.isDead();
    }

    /**
     * Effectue une attaque sur un adversaire.
     * Cette méthode doit être implémentée dans les classes dérivées.
     *
     * @param target L'adversaire qui reçoit l'attaque.
     */
    public abstract void attack(Character target);

    /**
     * Définit la capacité du personnage à se défendre contre une attaque.
     * Cette méthode doit être implémentée dans les classes dérivées.
     *
     * @return Le montant des dégâts bloqués lors d'une attaque.
     */
    public abstract int defend();

    /**
     * Retourne une représentation textuelle des statistiques du personnage.
     *
     * @return Une chaîne de caractères affichant les HP, XP et le nom du personnage.
     */
    @Override
    public String toString() {
        return String.format("""
                ****************************************
                ***** %s
                ****************************************
                * HP : %d / %d
                * XP : %d
                ****************************************
                """, this.name, this.health, this.maxHealth, this.experience);
    }
}