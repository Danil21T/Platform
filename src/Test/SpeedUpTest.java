package Test;

import model.Player;
import model.SpeedUp;
import org.junit.Assert;
import org.junit.Test;


public class SpeedUpTest {

    @Test
    public void INBonus() {
        Player p = new Player("");
        SpeedUp w = new SpeedUp();
        p.setParam(1,1);
        w.setParam(1,1);

        Assert.assertTrue(w.INBonus(p));
    }

    @Test
    public void INBonus_NON() {
        Player p = new Player("");
        SpeedUp w = new SpeedUp();
        p.setParam(1,1);
        w.setParam(50,50);

        Assert.assertFalse(w.INBonus(p));
    }


}