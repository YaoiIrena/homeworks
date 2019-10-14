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

        Point p1 = new Point();
        Point p2 = new Point();
        p1.p1 = 3;
        p2.p2 = 6;
        System.out.println(distance(p1, p2));

        //Формула AB = корень(AC^2 + BC^2), т.е. получается у нас 2 точки с (x1,y1) и (x2,y2)
        PointStart x1 = new PointStart();
        PointStart x2 = new PointStart();
        PointEnd y1 = new PointEnd();
        PointEnd y2 = new PointEnd();
        x1.x1 = 2;
        x2.x2 = 3;
        y1.y1 = 3;
        y2.y2 = 4;

        System.out.println("Расстояние от точки " + x1.x1 + " " + y1.y1 + " и " + x2.x2 + " " + y2.y2 + " = " + Math.sqrt((x2x1(x1, x2) + y2y1(y1, y2))));

    }
    public static double distance(Point p1, Point p2)
    {
        return Math.sqrt((p2.p2 + p1.p1));
    }

    public static void hello(String somebody)
    {
        System.out.println("Hello, " + somebody + "!");
    }

    public static double x2x1(PointStart x1, PointStart x2)
    {
        return Math.pow((x2.x2 - x1.x1), 2);
    }

    public static double y2y1(PointEnd y1, PointEnd y2)
    {
        return Math.pow((y2.y2 - y1.y1), 2);
    }
}