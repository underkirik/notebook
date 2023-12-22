package org.example;

import java.util.Scanner;
import org.example.config.AppConfig;
import org.example.config.DefaultAppConfig;
import org.example.config.InitAppConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    context.getBean(Notebook.class).init();
    CommandLine commandLine = context.getBean(CommandLine.class);
    Scanner scanner = commandLine.getScanner();
    System.out.println("Welcome to contacts application\n" +
        "Write your command to use contacts");
    while (true) {
      System.out.print("Your command: ");
      String command = scanner.nextLine();
      switch (command) {
        case ("help"):
          commandLine.helpCommand();
          break;
        case ("show"):
          commandLine.showCommand();
          break;
        case ("save"):
          commandLine.saveCommand();
          break;
        case ("add"):
          commandLine.addCommand();
          break;
        case ("delete"):
          System.out.println("Enter email to delete in contacts");
          commandLine.deleteCommand();
          break;
        case ("exit"):
          scanner.close();
          return;
        default:
          System.out.println("This command not found, please enter existing command\n" +
              "If you don't know commands, enter: help");
      }
    }
  }
}