package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModification extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Natasha").withLastname("Lee"), true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactModification() {

    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
//    int index = before.size() - 1;
    ContactData contact = new ContactData().
            withId(modifiedContact.getId()).withFirstname("black").
            withLastname("bird");
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();

//    before.remove(modifiedContact);
//    before.add(contact);
//    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
//    before.sort(byId);
//    after.sort(byId);
    assertThat(after, equalTo(before.withAdded(contact).without(modifiedContact)));

  }


}
