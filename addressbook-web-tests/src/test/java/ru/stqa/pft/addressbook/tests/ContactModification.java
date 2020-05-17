package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactModification extends TestBase {

  @Test
  public void testContactModification() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Natasha", "Lee",
              "(123)1234567", "na@le.com", "123 Terra ln, Richmond, TX", "2"), true);
      app.getNavigationHelper().returnHomePage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initEditContact(before.size() - 1);
    app.getContactHelper().fillContactForm(new ContactData("black",
            "bird", "(123)1234567", "b@b.com",
            "123 street", null), false);
    app.getContactHelper().updateContact();
    app.getNavigationHelper().returnHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
  }
}
