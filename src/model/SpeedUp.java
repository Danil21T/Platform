package model;

public class SpeedUp {
    protected int x ;
    protected int y ;
    protected int width = 30;
    protected int height = 30;
    protected boolean dr = true;
    protected int inBonus = 0;

    public SpeedUp(){
    }
    public void setParam(int x, int y){
        this.x = x;
        this.y = y;
    }
    public boolean inSpeedX(int x){
        return (x >= this.x  && x <= this.x ) ;
    }
    public boolean inSpeedY(int y){
        return (y >= this.y && y <= this.y +30);
    }
    public boolean INBonus(Player player){
        if(collect()) {
            if (inBonus == 0) {

                for (int i = 0; i < (player.getWidth()); i++) {
                    if (inSpeedX(player.getX() + i)) {
                        inBonus = 1;
                        break;
                    }
                }
                if (inBonus == 1) {
                    for (int i = 0; i < player.getHeight(); i++) {
                        if (inSpeedY(player.getY() + i)) {
                            inBonus = 2;
                            break;
                        }
                    }
                    if (inBonus == 2) {
                        dr = false;
                        Bonus(player);
                        return true;
                    }
                }

            }
        }
        if (!inSpeedX(player.getX())) inBonus = 0;
        return false;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public boolean getDr(){
        return dr;
    }
    public void dr(){
        dr = true;
    }
    protected void Bonus(Player player){
        player.SpeedUp();
    }
    public boolean collect(){
        return dr;
    }
}
