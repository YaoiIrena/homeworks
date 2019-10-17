package ru.stqa.pft.sandbox;

public class MyFirstProgram {
    public static void main(String[] args)
    {
        hello("Welt");
        hello("Hwo");

        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(4, 6);
        System.out.println("Площадь прямоугольника a и b " + r.a + " и " + r.b + " = " + r.area());

        Point p1 = new Point(1,1);
        Point p2 = new Point(2,4);
        System.out.println("Расстояние точки = " + p1.distance(p2));

    }
    public static double distance(Point p1, Point p2)
    {
        return Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));
    }

    public static void hello(String somebody)
    {
        System.out.println("Hello, " + somebody + "!");
    }
}