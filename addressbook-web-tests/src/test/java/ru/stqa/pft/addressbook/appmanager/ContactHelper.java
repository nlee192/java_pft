package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

  private ApplicationManager manager;

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public ContactHelper(ApplicationManager manager) {
    super(manager.wd); // чтобы не менять базовый класс и чтобы туда передавать ссылку на драйвер как раньше
    this.manager = manager;
  }

  public void fillContactForm(ContactData newContactData, boolean creation) {
    type(By.name("firstname"),newContactData.getFirstname());
    type(By.name("lastname"), newContactData.getLastname());
    type(By.name("home"), newContactData.getPhonenumber());
    type(By.name("email"), newContactData.getEmail());
    type(By.name("address2"), newContactData.getAddress());

    if (creation) {
      isElementPresent(By.name("new_group"));
      if (newContactData.getGroup() != null) {
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(newContactData.getGroup());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitNewContact() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void initNewContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectContactById(int id) {
    wd.findElement(By.xpath("//input[@value='" + id + "']")).click();
  }

  public void initEditContact(int i) {
    wd.findElement(By.xpath("//a[@href='edit.php?id=" + i + "']")).click();
  }

  public void updateContact() {
    click(By.name("update"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("//*[@value='Delete']"));
  }

  public void acceptAlert() {
    wd.switchTo().alert().accept();
  }

  public void create(ContactData contact, boolean creation) {
    initNewContactCreation();
    fillContactForm(contact, creation);
    submitNewContact();
    contactsCache = null; //cleaning cache after modification in contacts lists
  }

  public void modify(ContactData contact) {
    initEditContact(contact.getId());
    fillContactForm(contact, false);
    updateContact();
    contactsCache = null; //cleaning cache after modification in contacts lists
    manager.goTo().homePage();
  }

  public void delete(int index) {
    selectContact(index);
    deleteSelectedContact();
    acceptAlert();
    contactsCache = null; //cleaning cache after modification in contacts lists
    manager.goTo().homePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    acceptAlert();
    contactsCache = null;
    manager.goTo().homePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

//  public List<ContactData> list() {
//    List<ContactData> contacts = new ArrayList<ContactData>();
//    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
//    for (WebElement element : elements) {
//      List<WebElement> cells = element.findElements(By.tagName("td"));
//      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
//      String lastname = cells.get(1).getText();
//      String firstname = cells.get(2).getText();
//      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
//    }
//    return contacts;
//  }

  private Contacts contactsCache = null;

  public Contacts all() {
    if (contactsCache != null) {
      return new Contacts(contactsCache); //returning a copy of contactsCache
    }
    contactsCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String address = cells.get(3).getText();
      String email = cells.get(4).getText();
      String phonenumber = cells.get(5).getText();

      contactsCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
      .withAddress(address).withEmail(email).withPhonenumber(phonenumber));
    }
    return contactsCache;
  }


}


