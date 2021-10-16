package model;

public class plarform {
    protected int width;
    protected int height = 10;
    protected int x;
    protected int y;
    protected int inPlat = 0;
    public plarform(){
    }
    public void setParam(int x, int y, int width){
        this.width = width;
        this.x = x;
        this.y = y;
    }
    public int getY(){
        return y;
    }
    public int getX(){
        return x;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public boolean inPlatformX(int x){
        return (x >= this.x && x <= this.x + width) ;
    }
    public boolean inPlatformY(int y){
        return y <= this.y ;
    }
    public boolean INPlatform(Player player) {

        if (inPlat == 0) {

            for (int i = 5; i < (player.getWidth()); i++) {
                if (inPlatformX(player.getX() + i)) {
                    inPlat = 1;
                    break;
                }
            }

            if (inPlat == 1 && inPlatformY(player.getY()+player.getHeight()-10)) {
                player.setSY(getY() - player.getHeight());
                return true;

            }
        }
        if (!inPlatformX(player.getX())) {
            inPlat = 0;
        }
        return false;
    }
}
