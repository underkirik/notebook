package org.example.Utils;

import org.example.Contact;

public class ContactUtils {

    public static Contact getContactInStringToCommandLine(String line) {
        String[] contact = line.split(";");
        if (contact.length > 3) {
            System.out.println("Current contact contains an error \n" +
                    "please clear or to correct contact's file \n" +
                    "error contact: " + line);
        }
        return new Contact(contact[0], contact[1], contact[2]);
    }

}
