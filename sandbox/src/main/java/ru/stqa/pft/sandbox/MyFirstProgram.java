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

        //Формула AB = корень(AC^2 + BC^2), т.е. получается у нас 2 точки с (x1,y1) и (x2,y2)
        //Вариант 1

        Point point = new Point(3,6);
        System.out.println("Расстояние от точки " + "=" + " " + Math.sqrt(point.distance() + point.distance()));

        //Вариант 2

        PointStart pointStart = new PointStart(2,3);
        PointEnd pointEnd = new PointEnd(3,4);

        System.out.println("Расстояние от точки " + pointStart.x1 + " " + pointEnd.y1 + " и " + pointStart.x2 + " " + pointEnd.y2 + " = " + Math.sqrt((pointStart.x2x1() + pointEnd.y2y1())));

    }

    public static void hello(String somebody)
    {
        System.out.println("Hello, " + somebody + "!");
    }
}