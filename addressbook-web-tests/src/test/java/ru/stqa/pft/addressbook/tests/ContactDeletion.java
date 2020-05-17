package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

public class ContactDeletion extends TestBase {

  @Test
  public void testContactDeletion() throws Exception{
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new NewContactData("Natasha", "Lee",
              "(123)1234567", "na@le.com", "123 Terra ln, Richmond, TX", "2"), true);
      app.getNavigationHelper().returnHomePage();
    }
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().acceptAlert();
    app.getNavigationHelper().returnHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);



  }
}
