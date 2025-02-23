/**
 * Classe abstraite représentant un personnage dans le jeu RPG.
 * Cette classe définit les attributs et méthodes communs à tous les personnages,
 * tels que la santé, l'expérience, et les actions de combat.
 */
public abstract class Character {
    /** Constantes pour les modes de combat */
    private final String BATTLE_MODE = "1";
    private final String ESCAPE_MODE = "2";

    /**
     * Obtient le mode de combat (attaque).
     * @return La chaîne représentant le mode de combat "1".
     */
    public String getBATTLE_MODE() {
        return BATTLE_MODE;
    }

    /**
     * Obtient le mode de fuite.
     * @return La chaîne représentant le mode de fuite "2".
     */
    public String getESCAPE_MODE() {
        return ESCAPE_MODE;
    }

    /** Nom du personnage */
    private String name;
    /** Points de vie actuels du personnage */
    private int health;
    /** Points de vie maximum du personnage */
    private int maxHealth;
    /** Expérience du personnage */
    private int experience;

    /**
     * Constructeur de la classe Character.
     * @param name Nom du personnage.
     * @param maxHealth Points de vie maximum.
     * @param experience Expérience initiale.
     */
    public Character(String name, int maxHealth, int experience) {
        this.name = name;
        this.health = this.maxHealth = maxHealth;
        this.experience = experience;
    }

    /** @return Le nom du personnage */
    public String getName() {
        return name;
    }

    /** @param name Définit le nom du personnage */
    public void setName(String name) {
        this.name = name;
    }

    /** @return La santé actuelle du personnage */
    public int getHealth() {
        return health;
    }

    /** @param health Définit la santé actuelle du personnage */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Diminue la santé du personnage.
     * @param points Nombre de points à enlever.
     */
    public void decreaseHealth(int points) {
        this.health -= points;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    /**
     * Augmente la santé du personnage.
     * @param points Nombre de points à ajouter.
     */
    public void increaseHealth(int points) {
        this.health += points;
        if (this.health > this.maxHealth) {
            this.health = this.maxHealth;
        }
    }

    /** @return La santé maximale du personnage */
    public int getMaxHealth() {
        return maxHealth;
    }

    /** @param maxHealth Définit la santé maximale du personnage */
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    /** @return L'expérience du personnage */
    public int getExperience() {
        return experience;
    }

    /** @param experience Définit l'expérience du personnage */
    public void setExperience(int experience) {
        this.experience = experience;
    }

    /**
     * Diminue l'expérience du personnage.
     * @param points Nombre de points à enlever.
     */
    public void decreaseExperience(int points) {
        this.experience -= points;
        if (this.experience < 0) {
            this.experience = 0;
        }
    }

    /**
     * Augmente l'expérience du personnage.
     * @param points Nombre de points à ajouter.
     */
    public void increaseExperience(int points) {
        this.experience += points;
    }

    /**
     * Vérifie si le personnage est mort.
     * @return `true` si le personnage n'a plus de points de vie, sinon `false`.
     */
    public boolean isDead() {
        return this.health <= 0;
    }

    /**
     * Gère le combat entre deux personnages.
     * @param character Le personnage adverse.
     * @param mode      Le mode de combat ("1" pour attaquer, "2" pour fuir).
     * @return `true` si le combat continue, `false` s'il est terminé.
     */
    public boolean fight(Character character, String mode) {
        int dealtDamage;
        int takenDamage;
        boolean isOngoing = true;

        switch (mode) {
            case BATTLE_MODE:
                dealtDamage = Math.max(0, this.attack() - character.defend());
                takenDamage = Math.max(0, character.attack() - this.defend());

                this.decreaseHealth(takenDamage);
                character.decreaseHealth(dealtDamage);

                System.out.println(this.getName() + " attaque " + character.getName() + " !");
                System.out.println(character.getName() + " subit " + dealtDamage + " dégâts.");
                System.out.println(this.getName() + " subit " + takenDamage + " dégâts.");

                if (this.isDead()) {
                    System.out.println(this.getName() + " a été vaincu !");
                    isOngoing = false;
                } else if (character.isDead()) {
                    this.increaseExperience(character.getExperience());
                    System.out.println(character.getName() + " a été vaincu !");
                    System.out.println(this.getName() + " gagne " + character.getExperience() + " XP !");
                    isOngoing = false;
                }
                break;

            case ESCAPE_MODE:
                if (Math.random() < 0.35) {
                    System.out.println(this.getName() + " s'échappe avec succès !");
                    isOngoing = false;
                } else {
                    takenDamage = character.attack();
                    this.decreaseHealth(takenDamage);
                    System.out.println(this.getName() + " n'a pas réussi à fuir et subit " + takenDamage + " dégâts !");

                    if (this.isDead()) {
                        System.out.println(this.getName() + " a été vaincu en tentant de fuir !");
                        isOngoing = false;
                    }
                }
                break;

            default:
                throw new IllegalArgumentException("Mode de combat invalide : " + mode);
        }

        return isOngoing;
    }

    /** @return Le montant des dégâts infligés lors d'une attaque. */
    public abstract int attack();

    /** @return Le montant des dégâts bloqués lors d'une défense. */
    public abstract int defend();

    /**
     * Retourne une représentation textuelle des statistiques du personnage.
     * @return Une chaîne de caractères décrivant le personnage.
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