package org.example;

import org.example.Utils.ContactUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CommandLine {

    @Autowired
    private Notebook notebook;

    public static void helpCommand() {
        System.out.println("--------------------------------------------------------------------\n" +
                "show - enter command by view contact's list in memory cash\n" +
                "save - enter command by save contacts in memory list to file, Example file name - _contacts\n" +
                "add - enter command and in next line enter contact in format: full name;phone number;email\n" +
                "delete - enter command and in next line enter email to delete contacts in contact's list\n" +
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter contact in format - full name;phone number;email:");
        String contact = scanner.nextLine();
        notebook.add(ContactUtils.getContactInStringToCommandLine(contact));
        System.out.println("Successfully add contact in memory cash");
    }

    public void deleteCommand(String email) {
        if (email == null || email.isEmpty()) {
            System.out.println("Error when deleting contacts in memory cash by email.\n" +
                    "Please enter existing email in contacts");
        }
        notebook.delete(email);
        System.out.println("Successfully delete contact in memory cash");
    }
}
