package Test;

import model.Player;
import model.Warrior;
import org.junit.Assert;
import org.junit.Test;

public class WarriorTest {

    @Test
    public void INFight() {
        Player p = new Player("");
        Warrior w = new Warrior();
        p.setParam(1, 1);
        w.setParam(1, 1, 20);

        Assert.assertTrue(w.INFight(p));
    }

    @Test
    public void INFight_NON() {
        Player p = new Player("");
        Warrior w = new Warrior();
        p.setParam(1, 1);
        w.setParam(100, 100, 20);

        Assert.assertFalse(w.INFight(p));
    }
}