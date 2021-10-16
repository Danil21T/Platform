package model;

public class Warrior {
    protected int x;
    protected int y;
    protected int run;
    protected int count = 0;
    protected int width = 70;
    protected int height = 70;
    protected boolean right = true;
    protected int cadre;
    protected int speedcadre;
    protected int inWar = 0;
    protected int countdamage = 0;
    protected int maxcountdamage;


    public Warrior() {
        maxcountdamage = 15;
        speedcadre = 7;
    }

    public void setParam(int x, int y, int run) {
        this.x = x;
        this.y = y;
        this.run = run;
        count = 0;
    }

    public void animRight() {
        cadre++;
        if (cadre == speedcadre + 1) {
            cadre = 0;
        }
        if (cadre == speedcadre) {
            if (count != run) {
                x++;
                count++;
            }
            if (count == run)
                right = false;
        }
    }

    public void animLeft() {
        cadre++;
        if (cadre == speedcadre + 1) {
            cadre = 0;
        }
        if (cadre == speedcadre) {
            if (count != 0) {
                x--;
                count--;
            }
            if (count == 0)
                right = true;
        }
    }

    public boolean isAttackX(int x) {
        return (x >= this.x  && x <= this.x + width);
    }

    public boolean isAttackY(int y) {
        return (y >= this.y && y <= this.y + height);
    }

    public boolean INFight(Player player) {
        if (inWar == 0) {


            if (isAttackX(player.getX() + player.getWidth() / 2)) {
                inWar = 1;

            }
            if (inWar == 1) {
                for (int i = 0; i < player.getHeight(); i++) {
                    if (isAttackY(player.getY() + i)) {
                        inWar = 2;
                        break;
                    }
                }
                if (inWar == 2) {
                    if (countdamage == 0) {
                        player.HealthDown();
                    }
                    countdamage++;
                    if (countdamage == maxcountdamage) {
                        countdamage = 0;
                    }
                    return true;
                }
            }

        }
        if (!isAttackX(player.getX())) inWar = 0;
        return false;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight(){
        return height;
    }
    public boolean getRight(){
        return right;
    }

}
