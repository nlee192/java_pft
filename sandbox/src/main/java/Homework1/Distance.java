package Homework1;

public class Distance {
  public static void main(String[] args) {
    Point p1 = new Point();
    Point p2 = new Point();

    p1.x = 2;
    p1.y = 4;

    p2.x = 5;
    p2.y = 8;


    System.out.println("The distance is " + Point.distance(p1, p2));

  }
}
