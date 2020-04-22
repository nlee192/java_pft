package HomeworkTests;

import Homework1.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test

  public void testDistance () {
    Point p1 = new Point(2, 4);
    Point p2 = new Point(5, 8);
    Assert.assertEquals(Point.distance(p1, p2),5);

  }
}
