package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");
        hello("user");
        hello("Natasha");

        Square s = new Square(5);
        System.out.println("Ploschad kvadrata so storonoy " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(3, 4);
        System.out.println("Ploschad pryamougolnika so storonami " + r.a + " i " + r.b + " = " + r.area());


    }
    public static void hello (String somebody) {

        System.out.println("Hello, " + somebody + "!");
    }



}
