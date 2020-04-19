package ru.stqa.pft.sandbox;

public class Point {

  public double x;
  public double y;

  static Point p1 = new Point(2, 4);
  static Point p2 = new Point(5, 8);

  public Point (double x, double y) {
    this.x=x;
    this.y=y;

  }

    public static double distance(double x, double y) {
    double d = Math.sqrt((p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y));
    System.out.println(d);

  }

}
