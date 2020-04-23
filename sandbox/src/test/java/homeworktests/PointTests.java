package homeworktests;

import Homework1.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test

  public void testDistance () {
    Point p1 = new Point(2, 4);
    Point p2 = new Point(5, 8);
    Assert.assertEquals(p1.distance(p2),5);

  }

  @Test
  public void testDistance2 () {
    Point p1 = new Point (0, 0);
    Point p2 = new Point(0, 3);
    assert p1.distance(p2) == 3;
  }
}
