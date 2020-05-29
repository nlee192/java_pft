package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

//    assertThat(contact.getHomephone(), equalTo(cleaned(contactInfoFromEditForm.getHomephone())));
//    assertThat(contact.getMobilephone(), equalTo(cleaned(contactInfoFromEditForm.getMobilephone())));
//    assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));

    assertThat(contact.getAllPhones(), equalTo(mergedPhones(contactInfoFromEditForm)));
  }

  private String mergedPhones(ContactData contact) {
    return Arrays.asList(contact.getHomephone(),contact.getMobilephone(),contact.getWorkPhone())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }
  //Arrays.asList - creates a collection from the phones.
  //In order to filter out null elements we turn the array into a stream.
  //Filter(anonymous function accepts String (s) as a parameter (because it's a stream made of an array of strings)
  // we need to keep -> elements not equal to null string ! s.equals("")
  //.map - implement some function to all stream element and return a stream of results from this function
  //.map(as parameter you can name the function, the function has to be static)
  //Collect - to merge the not null strings together, .joining - merges all stream elements into one big string
  //"\n" is the string to be inserted between the elements being merged

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "")
            .replaceAll("[-()]", "");
  }
}
