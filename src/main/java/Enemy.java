public class Enemy extends Character {
    private int playerExperience;

    public Enemy(String name, int playerExperience) {
        super(name, (int) (Math.random() * playerExperience + (double) playerExperience / 3 + 5),
                (int) (Math.random() * ((double) playerExperience / 4 + 2) + 1));
        this.playerExperience = playerExperience;
    }

    public void takeDamage(int damage) {
        this.decreaseHealth(damage);
        System.out.println(this.getName() + " a perdu " + damage + " points de vie.");
    }

    @Override
    public void attack(Character target) {
        if (!(target instanceof Player player)) {
            System.out.println("L'ennemi ne peut attaquer que les joueurs.");
            return;
        }

        int baseAttack = (int) (Math.random() * ((double) this.getExperience() / 4 + 1) +
                (double) playerExperience / 4 + 3);

        System.out.println(this.getName() + " attaque " + player.getName() + " et inflige " + baseAttack + " dégâts !");
        player.takeDamage(baseAttack);
    }

    @Override
    public int defend() {
        return (int) (Math.random() * ((double) playerExperience / 4 + 1) + (double) this.getExperience() / 4 + 3);
    }

    public int getGoldReward() {
        return (int) (Math.random() * 10 + 5);
    }
}