package Test;

import model.Player;
import model.plarform;
import org.junit.Assert;
import org.junit.Test;

public class plarformTest {

    @Test
    public void INPlatform() {
        Player p1 = new Player("");
        p1.setParam(30,400);
        plarform plat1 = new plarform();
        plat1.setParam(0,500,100);

        Assert.assertTrue(plat1.INPlatform(p1));
    }

    @Test
    public void INPlatform_NON() {
        Player p2 = new Player("");
        p2.setParam(0,0);
        plarform plat2 = new plarform();
        plat2.setParam(400,400,10);

        Assert.assertFalse(plat2.INPlatform(p2));
    }
}