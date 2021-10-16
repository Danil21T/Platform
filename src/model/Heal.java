package model;

public class Heal extends SpeedUp {

    public Heal() {
        super();
    }

    protected void Bonus(Player player) {
        player.HealthUp();
    }
}
