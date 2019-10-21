package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
    @Test
    public void testPoint()
    {
        Point p1 = new Point(1,5);
        Point p2 = new Point(3,4);
        Point p3 = new Point(0,0);
        assert p3 != p1 & p3 != p2;
        assert 0 < p1.distance(p2);
        assert 1000 > p1.distance(p2);
        Assert.assertEquals(Math.round((p1.distance(p2)*100)/100), 2);
        Assert.assertEquals(p2.distance(p3), 5.0);
    }
}
