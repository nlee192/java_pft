package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

public class ContactModification extends TestBase {

  @Test
  public void testContactModification() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new NewContactData("Natasha", "Lee",
              "(123)1234567", "na@le.com", "123 Terra ln, Richmond, TX", "2"), true);
      app.getNavigationHelper().returnHomePage();
    }
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().initEditContact();
    app.getContactHelper().fillContactForm(new NewContactData("black",
            "bird", "(123)1234567", "b@b.com",
            "123 street", null), false);
    app.getContactHelper().updateContact();
    app.getNavigationHelper().returnHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  }
}
