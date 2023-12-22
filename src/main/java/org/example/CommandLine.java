package org.example;

import java.util.Scanner;
import lombok.Getter;
import org.example.Utils.ContactUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CommandLine {

  private final Notebook notebook;
  private final Scanner scanner;
  private final ContactUtils contactUtils;

  @Autowired
  public CommandLine(Notebook notebook, ContactUtils contactUtils) {
    this.notebook = notebook;
    this.scanner = new Scanner(System.in);
    this.contactUtils = contactUtils;
  }

  public void helpCommand() {
    System.out.println("--------------------------------------------------------------------\n" +
        "show - enter command by view contact's list in memory cash\n" +
        "save - enter command by save contacts in memory list to file, Example file name - _contacts\n"
        +
        "add - enter command and in next line enter contact in format: full name;phone number;email\n"
        +
        "delete - enter command and in next line enter email to delete contacts in contact's list\n"
        +
        "exit - enter command by exit in this application\n" +
        "--------------------------------------------------------------------");
  }

  public void showCommand() {
    System.out.println("Contacts in memory cash:");
    notebook.show();
  }

  public void saveCommand() {
    notebook.save();
    System.out.println("Successfully save contacts in file");
  }

  public void addCommand() {
    System.out.println("Enter contact in format - full name;phone number;email:");
    String contact = scanner.nextLine();
    if (contact.equals("back")) {
      System.out.println("Back to entered command");
    } else if (!contactUtils.isContact(contact)) {
      System.out.println(
          "Entered string is not contact, please enter contact in format - full name;phone number;email:");
    } else {
      notebook.add(contactUtils.getContactInStringToCommandLine(contact));
      System.out.println("Successfully add contact in memory cash");
    }
  }

  public void deleteCommand() {
    String email = scanner.nextLine();
    if (email.equals("back")) {
      System.out.println("Back to entered command");
    } else if (email.isEmpty() || !notebook.isExistInNotebook(email)) {
      System.out.println("Error when deleting contacts in memory cash by email.\n" +
          "Please enter existing email in contacts");
    } else {
      notebook.delete(email);
      System.out.println("Successfully delete contact in memory cash");
    }
  }
}
