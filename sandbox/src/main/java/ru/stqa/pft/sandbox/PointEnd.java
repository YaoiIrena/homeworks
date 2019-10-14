package ru.stqa.pft.sandbox;

public class PointEnd
{
    public double y1;
    public double y2;

    public PointEnd(double y1, double y2)
    {
        this.y1 = y1;
        this.y2 = y2;
    }

    public double y2y1()
    {
        return Math.pow((this.y2 - this.y1), 2);
    }
}
