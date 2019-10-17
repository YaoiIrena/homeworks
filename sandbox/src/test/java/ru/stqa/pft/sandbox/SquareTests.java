package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTests
{
    @Test
    public void testArea()
    {
        Square s = new Square(5);
        Assert.assertEquals(s.area(), 25.0);

        Point p1 = new Point(1,5);
        Point p2 = new Point(3,4);
        Point p3 = new Point(0,0);
        assert p3 != p1 & p3 != p2;
        assert 0 < p1.distance(p2);
        assert 1000 > p1.distance(p2);
    }

}
