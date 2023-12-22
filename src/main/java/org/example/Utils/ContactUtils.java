package org.example.Utils;

import java.util.UUID;
import java.util.regex.Pattern;
import org.example.Contact;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ContactUtils {

  @Value("${regex.phone}")
  String phoneRegex;

  @Value("${regex.email}")
  String emailRegex;

  public Contact getContactInStringToCommandLine(String line) {
    String[] contact = line.split(";");
    return new Contact(contact[0], contact[1], contact[2]);
  }

  public boolean isContact(String line) {
    String[] contact = line.split(";");
    return contact.length == 3 && isPhoneFromContact(contact[1]) && isEmailFromContact(contact[2]);
  }

  public boolean isPhoneFromContact(String line) {
    Pattern pattern = Pattern.compile(phoneRegex);
    return pattern.matcher(line).matches();
  }

  public boolean isEmailFromContact(String line) {
    Pattern pattern = Pattern.compile(emailRegex);
    return pattern.matcher(line).matches();
  }

  public String generateFileNameToSave() {
    return UUID.randomUUID()
        .toString()
        .replaceAll("-", "")
        .toLowerCase();
  }
}
