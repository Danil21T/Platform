package View;

import IO.WriteRecords;
import model.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.IOException;
import java.net.URL;


public class Game extends Canvas implements Runnable {

    private static Player player;
    private static Image playeri;
    private static Warrior warrior;
    private static Image warriori;
    private static plarform plarform;
    private static Image plarformi;
    private static SpeedUp speedUp;
    private static Image speedupi;
    private static Heal heal;
    private static Image heali;
    private static Ship ship;
    private static Ship ship2;
    private static Ship ship3;
    private static Image shipi;
    private static Archer archer;
    private static Archer archer2;
    private static Image archeri;
    private static Image arrowi;
    private static Finish finish;
    private static Image finishi;

    private String way = "src/Record.txt";

    Font BigFont = new Font("Yu Gothic Light", Font.PLAIN, 25);
    private JFrame f;

    public Game(JFrame f, String name) {
        this.f = f;
        player = getPlayer(name);
    }

    private boolean running;

    private boolean left;
    private boolean right;
    private boolean up = false;

    public void start() {
        running = true;
        new Thread(this).start();
    }

    public void run() {

        init();

        int count = 0;
        while (running) {

            if (!player.getFly()) {
                if (count == 0) {
                    player.setSY();
                }
                if (up) {
                    player.setStopFly(false);
                    count++;
                }
                if (!up && count != 0) {
                    count = 0;
                }
            }
            render();
            update();
            if (player.getHealth() == 0) {
                running = false;
            }
            if (player.getLvl() == 4)
                running = false;
        }
        f.getContentPane().removeAll();
        f.setBounds((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - 500,
                (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - 300,
                1000, 600);
        if (player.getLvl() == 4) {
            FinishDialog d = new FinishDialog(f, player);
            WriteRecords w = new WriteRecords(player, way);
            f.revalidate();
            f.repaint();
        } else {
            LooseDialog d = new LooseDialog(f, player);
            f.revalidate();
            f.repaint();
        }
    }

    public void init() {
        warrior = getWarrior();
        plarform = getPlatform();
        speedUp = getSpeedUp();
        heal = getHeal();
        ship = getShip();
        ship2 = getShip();
        ship3 = getShip();
        archer = getArcher();
        archer2 = getArcher();
        finish = getFinish();
        addKeyListener(new Action());
    }

    public void update() {
        int count = 0;
        if (left) {
            player.LeftAnim();
        }
        if (right) {
            player.RightAnim(getWidth());
        }
        if (!up) {
            player.setStopFly(true);
        }
        if (player.getStopFly()) {
            player.DownAnim(getHeight());
            count++;
        }

        if (count == 0) {
            if ((up)) {
                player.UpAnim();
            }
        }

    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2);
            requestFocus();
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Image i = new ImageIcon("assets\\back.jpg").getImage();
        g.drawImage(i, 0, 0, getWidth(), getHeight(), null);
        g.setFont(BigFont);
        g.setColor(Color.RED);
        g.drawString("Health: " + player.getHealth(), 20, 25);
        g.drawString("Score: " + player.getScore(), 20, 55);
        if (player.getLvl() == 1) {
            DrawLvl1((Graphics2D) g);
        }
        if (player.getLvl() == 2) {
            DrawLvl2((Graphics2D) g);
        }
        if (player.getLvl() == 3) {
            DrawLvl3((Graphics2D) g);
        }

        drawPlayer((Graphics2D) g, player);
        g.dispose();
        bs.show();
    }

    public void DrawLvl1(Graphics2D g) {
        if (!player.getInLvl()) {
            player.setParam(20, 499);
            finish.setParam(1200, 330);
            warrior.setParam(330, 400, 100);
            archer.setParam(1010, 440, 200);
            player.setInLvl(true);
        }

        plarform.setParam(0, 500 + player.getHeight(), 300);
        drawPlatform(g, plarform);
        if (plarform.INPlatform(player)) {
            player.setFallen(false);
        }
        ship.setParam(200, 470 + player.getHeight());
        drawShip(g, ship);
        ship.INFight(player);
        plarform.setParam(300, getHeight(), 25);
        if (plarform.INPlatform(player)) {
            player.setFallen(true);
        }

        plarform.setParam(330, 470, 150);
        drawPlatform(g, plarform);
        if (plarform.INPlatform(player)) {
            player.setFallen(false);
        }
        drawWarrior(g, warrior);
        warrior.INFight(player);
        plarform.setParam(480, getHeight(), 25);
        if (plarform.INPlatform(player)) {
            player.setFallen(true);
        }

        plarform.setParam(505, 600, 250);
        drawPlatform(g, plarform);
        if (plarform.INPlatform(player)) {
            player.setFallen(false);
        }
        heal.setParam(615, 470);
        drawHeal(g, heal);
        heal.INBonus(player);
        plarform.setParam(730, getHeight(), 25);
        if (plarform.INPlatform(player)) {
            player.setFallen(true);
        }

        plarform.setParam(760, 500, 250);
        drawPlatform(g, plarform);
        if (plarform.INPlatform(player)) {
            player.setFallen(false);
        }
        drawArcher(g, archer);
        archer.INFight(player);
        plarform.setParam(1010, getHeight(), 25);
        if (plarform.INPlatform(player)) {
            player.setFallen(true);
        }

        plarform.setParam(1040, 400, 500);
        drawPlatform(g, plarform);
        if (plarform.INPlatform(player)) {
            player.setFallen(false);
        }
        drawFinish(g, finish);
        if (finish.INBonus(player)) {
            finish.dr();
            player.setInLvl(false);
        }
    }

    public void DrawLvl2(Graphics2D g) {
        if (!player.getInLvl()) {
            player.setParam(20, 399);
            player.setFallen(false);
            heal.dr();
            finish.setParam(1200, 370);
            warrior.setParam(310, 200, 350);
            archer.setParam(990, 310, 250);
            player.setInLvl(true);
        }

        plarform.setParam(0, 400 + player.getHeight(), 100);
        drawPlatform(g, plarform);
        if (plarform.INPlatform(player)) {
            player.setFallen(false);
        }
        speedUp.setParam(70, 370 + player.getHeight());
        drawSpeedUp(g, speedUp);
        speedUp.INBonus(player);
        plarform.setParam(100, getHeight(), 30);
        if (plarform.INPlatform(player)) {
            player.setFallen(true);
        }

        plarform.setParam(130, 350, 150);
        drawPlatform(g, plarform);
        if (plarform.INPlatform(player)) {
            player.setFallen(false);
        }
        ship.setParam(180, 320);
        drawShip(g, ship);
        if(ship.INFight(player)){
            player.SppedStart();
        }
        plarform.setParam(280, getHeight(), 30);
        if (plarform.INPlatform(player)) {
            player.setFallen(true);
        }

        plarform.setParam(310, 270, 400);
        drawPlatform(g, plarform);
        if (plarform.INPlatform(player)) {
            player.setFallen(false);
        }
        drawWarrior(g, warrior);
        if(warrior.INFight(player)){
            player.SppedStart();
        }
        plarform.setParam(710, getHeight(), 30);
        if (plarform.INPlatform(player)) {
            player.setFallen(true);
        }

        plarform.setParam(740, 370, 250);
        drawPlatform(g, plarform);
        if (plarform.INPlatform(player)) {
            player.setFallen(false);
        }
        drawArcher(g, archer);
        if(archer.INFight(player)){
            player.SppedStart();
        }
        plarform.setParam(990, getHeight(), 30);
        if (plarform.INPlatform(player)) {
            player.setFallen(true);
        }

        plarform.setParam(1020, 400, 50);
        drawPlatform(g, plarform);
        if (plarform.INPlatform(player)) {
            player.setFallen(false);
        }
        heal.setParam(1030, 340);
        drawHeal(g, heal);
        heal.INBonus(player);
        plarform.setParam(1070, getHeight(), 1);
        if (plarform.INPlatform(player)) {
            player.setFallen(true);
        }

        plarform.setParam(1072, 440, 500);
        drawPlatform(g, plarform);
        if (plarform.INPlatform(player)) {
            player.setFallen(false);
        }
        drawFinish(g, finish);
        if (finish.INBonus(player)) {
            finish.dr();
            player.setInLvl(false);
        }

    }

    public void DrawLvl3(Graphics2D g) {
        if (!player.getInLvl()) {
            player.setParam(20, 149);
            player.setFallen(false);
            player.SppedStart();
            heal.dr();
            finish.setParam(1090, 370);
            warrior.setParam(310, 200, 350);
            archer.setParam(400, 150 + player.getHeight() - 60, 300);
            archer2.setParam(1010, 370, 300);
            player.setInLvl(true);
        }

        plarform.setParam(0, 150 + player.getHeight(), 400);
        drawPlatform(g, plarform);
        if (plarform.INPlatform(player)) {
            player.setFallen(false);
        }
        drawArcher(g, archer);
        archer.INFight(player);
        ship.setParam(180, 150 + player.getHeight() - 30);
        drawShip(g, ship);
        ship.INFight(player);
        plarform.setParam(400, getHeight(), 30);
        if (plarform.INPlatform(player)) {
            player.setFallen(true);
        }

        plarform.setParam(430, 360, 150);
        drawPlatform(g, plarform);
        if (plarform.INPlatform(player)) {
            player.setFallen(false);
        }
        ship2.setParam(500, 330);
        drawShip(g, ship2);
        ship3.setParam(530, 330);
        drawShip(g, ship3);
        ship2.INFight(player);
        ship3.INFight(player);
        plarform.setParam(580, getHeight(), 30);
        if (plarform.INPlatform(player)) {
            player.setFallen(true);
        }

        plarform.setParam(610, 430, 400);
        drawPlatform(g, plarform);
        if (plarform.INPlatform(player)) {
            player.setFallen(false);
        }
        drawArcher(g, archer2);
        archer2.INFight(player);
        plarform.setParam(1010, getHeight(), 30);
        if (plarform.INPlatform(player)) {
            player.setFallen(true);
        }


        plarform.setParam(1072, 440, 500);
        drawPlatform(g, plarform);
        if (plarform.INPlatform(player)) {
            player.setFallen(false);
        }
        drawFinish(g, finish);
        if (finish.INBonus(player)) {
            finish.dr();
            player.setInLvl(false);
        }
    }

    public Warrior getWarrior() {
        BufferedImage sourceImage = null;
        URL url = this.getClass().getClassLoader().getResource("swordman.png");
        try {
            sourceImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        warriori = Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
        Warrior w = new Warrior();
        return w;
    }

    public Archer getArcher() {
        BufferedImage sourceImage1 = null;
        BufferedImage sourceImage2 = null;
        URL url1 = this.getClass().getClassLoader().getResource("archer.png");
        URL url2 = this.getClass().getClassLoader().getResource("arrow.png");
        try {
            sourceImage1 = ImageIO.read(url1);
            sourceImage2 = ImageIO.read(url2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        archeri = Toolkit.getDefaultToolkit().createImage(sourceImage1.getSource());
        arrowi = Toolkit.getDefaultToolkit().createImage(sourceImage2.getSource());
        Archer w = new Archer();
        return w;
    }

    public Ship getShip() {
        BufferedImage sourceImage = null;
        URL url = this.getClass().getClassLoader().getResource("ship.png");
        try {
            sourceImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        shipi = Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
        Ship s = new Ship();
        return s;
    }

    public plarform getPlatform() {
        BufferedImage sourceImage = null;
        URL url = this.getClass().getClassLoader().getResource("platform.png");
        try {
            sourceImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        plarformi = Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
        plarform p = new plarform();
        return p;
    }

    public Player getPlayer(String name) {
        BufferedImage sourceImage = null;
        URL url = this.getClass().getClassLoader().getResource("pirate_.png");
        try {
            sourceImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        playeri = Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
        Player i = new Player(name);
        return i;
    }

    public Finish getFinish() {
        BufferedImage sourceImage = null;
        URL url = this.getClass().getClassLoader().getResource("finish.png");
        try {
            sourceImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finishi = Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
        Finish s = new Finish();
        return s;
    }

    public SpeedUp getSpeedUp() {
        BufferedImage sourceImage = null;
        URL url = this.getClass().getClassLoader().getResource("speed.png");
        try {
            sourceImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        speedupi = Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
        SpeedUp s = new SpeedUp();
        return s;
    }

    public Heal getHeal() {
        BufferedImage sourceImage = null;
        URL url = this.getClass().getClassLoader().getResource("health.png");
        try {
            sourceImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        heali = Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
        Heal s = new Heal();
        return s;
    }

    public void drawPlayer(Graphics2D g, Player p) {

        p.ScorecountUp();
        if (p.getScorecount() == 100) {
            p.ScorecountDown();
            p.ScoreUp();
        }
        g.drawImage(playeri, p.getX(), p.getY(), p.getWidth(), p.getHeight(), null);

    }

    public void drawWarrior(Graphics2D g, Warrior w) {
        if (w.getRight())
            w.animRight();
        if (!w.getRight())
            w.animLeft();

        g.drawImage(warriori, w.getX(), w.getY(), w.getWidth(), w.getHeight(), null);
    }

    public void drawArcher(Graphics2D g, Archer a) {

        if (a.getRight())
            a.animRight();
        if (!a.getRight()) {
            a.animLeft();
        }

        g.drawImage(arrowi, a.getX(), a.getY(), a.getWidth(), a.getHeight(), null);
        g.drawImage(archeri, a.getXw(), a.getYw(), a.getWidthw(), a.getHeightw(), null);
    }

    public void drawShip(Graphics2D g, Ship s) {

        g.drawImage(shipi, s.getX(), s.getY(), s.getWidth(), s.getHeight(), null);

    }

    public void drawPlatform(Graphics2D g, plarform p) {
        g.drawImage(plarformi, p.getX(), p.getY(), p.getWidth(), p.getHeight(), null);

    }

    public void drawSpeedUp(Graphics2D g, SpeedUp s) {
        if (s.getDr())
            g.drawImage(speedupi, s.getX(), s.getY(), s.getWidth(), s.getHeight(), null);
    }

    public void drawHeal(Graphics2D g, Heal h) {
        if (h.getDr())
            g.drawImage(heali, h.getX(), h.getY(), h.getWidth(), h.getHeight(), null);
    }

    public void drawFinish(Graphics2D g, Finish f) {
        if (f.getDr())
            g.drawImage(finishi, f.getX(), f.getY(), f.getWidth(), f.getHeight(), null);
    }


    public class Action extends KeyAdapter {
        public void keyTyped(KeyEvent e) {

        }

        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                left = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                right = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                up = true;
            }
        }

        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                left = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                right = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                up = false;
            }
        }
    }


}
