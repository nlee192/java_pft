package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String[] args) {
    String [] langs = {"Java", "C#", "Python", "PHP"};

    for (String l : langs) {
      System.out.println("I want to learn " + l);
    }

    List<String> languages = new ArrayList<String>();
    languages.add("Java");
    languages.add("C#");
    languages.add("Python");

    for (String l : languages) {
      System.out.println("I want to learn " + l);
    }

    List<String> languages2 = Arrays.asList("Java", "C#", "Python", "PHP");
    for (String l : languages2) {
      System.out.println("I want to learn " + l);
    }

    //if you want to have elements of different type (not just String or numbers) - remove <> and set variable as Object
    List languages3 = Arrays.asList("Java", "C#", "Python", "PHP");
    for (Object l : languages3) {
      System.out.println("I want to learn " + l);
    }
  }
}
