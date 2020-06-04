package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]>validGroups() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new GroupData().withName("test1").withHeader("header 1").withFooter("footer 1")});
    list.add(new Object[] {new GroupData().withName("test2").withHeader("header 2").withFooter("footer 2")});
    list.add(new Object[] {new GroupData().withName("test3").withHeader("header 3").withFooter("footer 3")});
    return list.iterator();
  }

  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) throws Exception {
    app.group().gotoGroupPage();
    Groups before = app.group().all();
    app.group().create(group);
    app.group().gotoGroupPage();
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();

//    int max = 0;
//    for (GroupData g : after) {
//      if (g.getId() > max) {
//        max = g.getId();
//      }
//    }

//    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
//    before.add(group);
//    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
//    before.sort(byId);
//    after.sort(byId);
//    Assert.assertEquals(before, after);
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadGroupCreation() throws Exception {
    app.group().gotoGroupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("new'");
    app.group().create(group);
    app.group().gotoGroupPage();
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before));
  }

}
