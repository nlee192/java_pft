package ru.stqa.pft.addressbook.model;

public class NewContactData {
  private final String firstname;
  private final String lastname;
  private final String phonenumber;
  private final String email;
  private final String address;

  public NewContactData(String firstname, String lastname, String phonenumber, String email, String address) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.phonenumber = phonenumber;
    this.email = email;
    this.address = address;
  }

  public String getFirstname() {
    return firstname;
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
}
