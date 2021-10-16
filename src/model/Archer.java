package model;

public class Archer extends Warrior {
    private int xw = 0;
    private int yw = 0;
    private int widthw;
    private int heightw;
    public Archer() {
        super();
        maxcountdamage = 15;
    }
    public void setParam(int x, int y, int run){
        this.x = x;
        this.y = y + 20;
        right = false;
        count = run;
        this.run = run;
        width = 30;
        height = 15;
        widthw = 50;
        heightw = 60;
        xw = x - width;
        yw = y;
        speedcadre = 5;
    }
    public void animRight(){
        x = x + run;
        right = false;
        count = run;
    }
    public int getXw(){
        return xw;
    }
    public int getYw(){
        return yw;
    }
    public int getWidthw(){
        return widthw;
    }
    public int getHeightw(){
        return heightw;
    }


}
