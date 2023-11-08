package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DefaultNotebook implements Notebook {

    @Value("${app.save_path}")
    private String pathToSave;

    private Set<Contact> contacts;

    private File fileToSave;

    @Override
    public void init() {
        if (contacts == null) {
            contacts = new HashSet<>();
        }
    }

    @Override
    public void save() {
        try {
            if (fileToSave == null) {
                fileToSave = new File(pathToSave + contacts.hashCode() + "_contacts");
            }
            if (!fileToSave.exists()) {
                fileToSave.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(fileToSave.getAbsolutePath());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            contacts.forEach(contact -> {
                try {
                    bufferedWriter.write(contact.getContactString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
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
        if (contacts.isEmpty()){
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
