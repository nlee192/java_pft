package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String phonenumber;
  private final String email;
  private final String address;
  private String group;

  public ContactData(String firstname, String lastname, String phonenumber, String email, String address, String group) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.phonenumber = phonenumber;
    this.email = email;
    this.address = address;
    this.group = group;
  }

  public String getFirstname() {
    return firstname;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstname, lastname);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  public String getLastname() {
    return lastname;
  }

  public String getPhonenumber() {
    return phonenumber;
  }

  public String getEmail() {
    return email;
  }

  public String getAddress() {
    return address;
  }

  public String getGroup() {
    return group;
  }

}