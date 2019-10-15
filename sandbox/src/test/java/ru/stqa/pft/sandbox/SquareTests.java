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
        assert 0 < distance(p1,p2);
        assert 1000 > distance(p1,p2);
    }

    private double distance(Point p1, Point p2)
    {
        return Math.sqrt(Math.pow((p2.getX() - p1.getX()), 2) + Math.pow((p2.getY() - p1.getY()), 2));
    }
}
