package model;

public class Ship extends Warrior {
    public Ship(){
        super();
        width = 30;
        height = 30;
        maxcountdamage = 20;
    }
    public void setParam(int x, int y){
        this.x = x;
        this.y = y;
    }
}
