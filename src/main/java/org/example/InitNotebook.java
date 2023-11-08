package org.example;

import org.example.Utils.ContactUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class InitNotebook implements Notebook {

    @Value("${app.save_path}")
    private String pathToSave;

    @Value("${app.init-contact-file-name}")
    private String fileNameToLoad;

    private Set<Contact> contacts;

    private File contactsFile;

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
                contacts.add(ContactUtils.getContactInStringToCommandLine(data));
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
                    bufferedWriter.write(contact.getContactString());
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
                .filter(contact -> contact.getEmail().equals(email))
                .collect(Collectors.toSet()));

    }
}
