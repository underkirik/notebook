package org.example;

import org.example.config.DefaultAppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DefaultAppConfig.class);
        context.getBean(ProfileWorker.class).doWork();
        CommandLine commandLine = context.getBean(CommandLine.class);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to contacts application\n" +
                "Write your command to use contacts");
        while (true) {
            System.out.print("Your command: ");
            String command = scanner.nextLine();
            switch (command) {
                case ("help"):
                    CommandLine.helpCommand();
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
                    String email = scanner.nextLine();
                    commandLine.deleteCommand(email);
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