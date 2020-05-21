package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.group().gotoGroupPage();
    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData().withName("new");
    app.group().create(group);
    app.group().gotoGroupPage();
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() + 1);

//    int max = 0;
//    for (GroupData g : after) {
//      if (g.getId() > max) {
//        max = g.getId();
//      }
//    }

    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(group);
//    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
//    before.sort(byId);
//    after.sort(byId);
    Assert.assertEquals(before, after);


  }


}
