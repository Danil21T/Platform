package model;

public class Finish extends SpeedUp {

    public Finish() {
        super();
        height = 70;
        width = 70;
    }
    protected void Bonus(Player player) {
        player.LvlUp();
    }
}
