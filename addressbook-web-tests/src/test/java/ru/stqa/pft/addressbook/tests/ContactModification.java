package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModification extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Natasha").withLastname("Lee"), true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactModification() {

    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
//    int index = before.size() - 1;
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("black").
            withLastname("bird").withPhonenumber("(123)1234567").withEmail("b@b.com").
            withAddress("123 street");
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);
//    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
//    before.sort(byId);
//    after.sort(byId);
    Assert.assertEquals(before, after);

  }


}
