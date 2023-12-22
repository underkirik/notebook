package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import org.example.Utils.ContactUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class InitNotebook implements Notebook {

  @Value("${app.save-path}")
  private String pathToSave;

  @Value("${app.init-contact-file-name}")
  private String fileNameToLoad;

  private Set<Contact> contacts;

  private File contactsFile;

  @Autowired
  private ContactUtils contactUtils;

  @Override
  public void init() {
    contactsFile = new File(pathToSave + fileNameToLoad);
    if (contacts == null) {
      contacts = new HashSet<>();
    }
    try {
      Scanner scanner = new Scanner(contactsFile);
      while (scanner.hasNextLine()) {
        String data = scanner.nextLine();
        contacts.add(contactUtils.getContactInStringToCommandLine(data));
      }
      scanner.close();
      System.out.println("Successfully notebook initialization");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.out.println("File not found to default path\n" +
          "Default path: " + pathToSave + "\n" +
          "Default file name" + fileNameToLoad);
    }
  }

  @Override
  public void save() {
    try {
      FileWriter fileWriter = new FileWriter(contactsFile.getAbsolutePath());
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      contacts.forEach(contact -> {
        try {
          bufferedWriter.write(contact.getContactString() + "\n");
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      });
      bufferedWriter.close();
      fileWriter.close();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error in save contacts in file, this contacts");
      contacts.forEach(System.out::println);
    }
  }

  @Override
  public void add(Contact contact) {
    contacts.add(contact);
  }

  @Override
  public void show() {
    if (contacts.isEmpty()) {
      System.out.println("Contacts in memory cash is empty");
      return;
    }
    contacts.forEach(contact -> System.out.println(contact.getContactString()));
  }

  @Override
  public void delete(String email) {
    contacts.removeAll(contacts.stream()
        .filter(contact -> contact.getEmail().equalsIgnoreCase(email))
        .collect(Collectors.toSet()));
  }

  @Override
  public boolean isExistInNotebook(String email) {
    return contacts.stream()
        .map(Contact::getEmail)
        .anyMatch(contactEmail -> contactEmail.equalsIgnoreCase(email));
  }
}
