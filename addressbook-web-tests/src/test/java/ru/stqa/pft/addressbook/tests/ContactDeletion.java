package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletion extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Natasha").withLastname("Lee"), true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactDeletion() throws Exception{

    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
//    int index = before.size() - 1;
    app.contact().delete(deletedContact);
    assertThat(app.contact().count(), equalTo(before.size()-1));
    Contacts after = app.contact().all();

//    before.remove(deletedContact);
//    Assert.assertEquals(before, after);
    assertThat(after, equalTo(before.without(deletedContact)));
    }




}

