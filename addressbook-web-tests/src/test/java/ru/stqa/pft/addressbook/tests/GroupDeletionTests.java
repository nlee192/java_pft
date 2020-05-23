package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.group().gotoGroupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("new"));
      app.group().gotoGroupPage();
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    app.group().gotoGroupPage();
    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Groups after = app.group().all();
    Assert.assertEquals(after.size(), before.size() - 1);

//    before.remove(deletedGroup);
    assertThat(after, equalTo(before.without(deletedGroup)));
//    Assert.assertEquals(before, after);
  }
}
