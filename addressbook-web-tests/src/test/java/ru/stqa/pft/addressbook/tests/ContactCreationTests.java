package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[]{new ContactData().withFirstname("firstName1").withLastname("lastName1")});
    list.add(new Object[]{new ContactData().withFirstname("firstName2").withLastname("lastName2")});
    list.add(new Object[]{new ContactData().withFirstname("firstName3").withLastname("lastName3")});
    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testNewContact(ContactData contact) throws Exception {
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/boat.jpg");
    app.contact().create(contact, true);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size()+1));
    Contacts after = app.contact().all();
//    int max = 0;
//    for (ContactData c : after) {
//      if (c.getId() > max) {
//        max = c.getId();
//      }
//    }
//    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());

//    before.add(contact);
//    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
//    before.sort(byId);
//    after.sort(byId);
//    assertEquals(before, after);
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testBadNewContact() throws Exception {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Natasha'").withLastname("Lee");
    app.contact().create(contact, true);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }


//  public void testCurrentDir() {
//    File currentDir = new File(".");
//    System.out.println(currentDir.getAbsolutePath()); //  Checking the working directory for the tests
//    File photo = new File("src/test/resources/boat.jpg"); // adding the path to the file should be done
  // after the working directory
//    System.out.println(photo.getAbsolutePath());
//    System.out.println(photo.exists()); //checking if the file exists
//  }
}
