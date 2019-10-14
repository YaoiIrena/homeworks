package ru.stqa.pft.sandbox;

public class PointStart {
    public double x1;
    public double x2;

    public PointStart(double x1, double x2)
    {
        this.x1 = x1;
        this.x2 = x2;
    }

    public double x2x1() {
        return Math.pow((this.x2 - this.x1), 2);
    }
}
