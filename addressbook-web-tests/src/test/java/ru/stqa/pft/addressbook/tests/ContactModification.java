package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

public class ContactModification extends TestBase {

  @Test
  public void testContactModification() {
    app.getContactHelper().initEditContact();
    app.getContactHelper().fillContactForm(new NewContactData("black",
            "bird", "(123)1234567", "b@b.com",
            "123 street", null), false);
    app.getContactHelper().updateContact();
    app.getNavigationHelper().returnHomePage();
  }
}
