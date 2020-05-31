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
  private int id;

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
    attach(By.name("photo"), newContactData.getPhoto());
//    type(By.name("home"), newContactData.getPhonenumber());
//    type(By.name("email"), newContactData.getEmail());
//    type(By.name("address"), newContactData.getAddress());

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

  public ContactData infoFromEditForm(ContactData contact) {
    initEditContact(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email1 = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address)
            .withEmail1(email1).withEmail2(email2).withEmail3(email3);
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

  public int count() {
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
    List<WebElement> rows = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
//      String[] phones  = cells.get(5).getText().split("\n");
      String allPhones = cells.get(5).getText();


      contactsCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
      .withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));
    }
    return contactsCache;
  }


}


