package model;

public class Player {

    private int width = 32;
    private int height = 69;

    private int cadre = 0;
    private int speed = 1;
    private int x ;
    private int y ;
    private final int jump = 150;
    private int sy;
    private int MaxJamp;
    private boolean fly = false;
    private boolean stopfly;
    private boolean fallen = false;

    private String name;//состояние игры
    private int score = 0;
    private int health = 3;
    private int scorecount = 0;
    private int lvl = 1;
    private boolean inLvl = false;

    public Player(String name) {
        this.name = name;
    }

    public void setParam(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void RightAnim(int Edge){
        cadre++;
        if(cadre == 2){
            cadre = 0;
        }
        if(cadre == 0) {
            if (x < Edge + width) {
                x = x + speed;
            }
        }
    }
    public void LeftAnim(){
        cadre++;
        if(cadre == 2){
            cadre = 0;
        }
        if(cadre == 0) {
            if (x > 0) {
                x = x - speed;
            }
        }
    }
    public void UpAnim(){
        if(!fallen ) {
            MaxJamp = sy - jump;
        }
        if (y >= MaxJamp ) {
            fly = true;
            y --;
        }
        if(y <= MaxJamp){
            stopfly = true;
        }
    }
    public void DownAnim(int Edge) {

        if (fallen) {
            y++;
        }
       else if(y != sy && stopfly)
           y++;
        if (y == sy) {
            fly = false;
            MaxJamp = 0;
        }
        if (y == Edge - height) {
            health = 0;
        }

    }

    public void SpeedUp(){
        speed++;
    }
    public void HealthUp(){
        if(health<5){
            health++;
        }
    }
    public void HealthDown(){
        health--;
    }
    public void LvlUp(){
        lvl++;
    }
    public void SppedStart(){
        speed = 1;
    }
    public int getLvl(){
        return lvl;
    }
    public boolean getInLvl(){
        return inLvl;
    }
    public int getHealth(){
        return health;
    }
    public boolean getFly(){
        return fly;
    }
    public boolean getStopFly(){
        return stopfly;
    }
    public int getScore(){
        return score;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public String getName() {
        return name;
    }
    public void setFallen(boolean f){
        fallen = f;
    }

    public void setInLvl(boolean l){
        inLvl = l;
    }
    public void setStopFly(boolean stopfly){
        if(!fallen) {
            this.stopfly = stopfly;
        }
    }
    public void setSY(){
        sy = y;
    }
    public void setSY(int sy){
        this.sy = sy;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public void ScorecountUp(){
        scorecount++;
    }
    public int getScorecount(){
        return scorecount;
    }
    public void ScorecountDown(){
        scorecount = 0;
    }
    public void ScoreUp(){
        score++;
    }
}
