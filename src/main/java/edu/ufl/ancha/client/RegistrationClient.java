package edu.ufl.ancha.client;

import edu.ufl.ancha.registrationdblib.RegDBlib;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class RegistrationClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RegDBlib dbLib = new RegDBlib();

        while (true) {
            System.out.println("\nEnter L to list enrollment information for a student, E to enroll in a course, Q to Quit:");
            String input = scanner.nextLine().toUpperCase();

            switch (input) {
                case "L":
                    System.out.print("Enter Student ID: ");
                    String stuid = scanner.nextLine();
                    System.out.print("Enter Semester ID: ");
                    String semid = scanner.nextLine();

                    System.out.println("Enrollment information for student " + stuid + " in semester " + semid + ":");
                    dbLib.listEnrolled(stuid, semid).forEach(System.out::println);
                    break;

                case "E":
                    System.out.print("Enter Course ID: ");
                    String crsid = scanner.nextLine();
                    System.out.print("Enter Semester ID: ");
                    semid = scanner.nextLine();

                    System.out.printf("These are the available sections for %s during semester %s:%n", crsid, semid);
                    dbLib.listAvailable(crsid, semid).forEach(System.out::println);

                    System.out.println("If you wish to enroll press E again otherwise press C to cancel:");
                    String confirm = scanner.nextLine().trim().toUpperCase();

                    if ("E".equals(confirm)) {
                        System.out.print("Enter Student ID: ");
                        stuid = scanner.nextLine();
                        System.out.print("Enter Section ID: ");
                        String secid = scanner.nextLine();

                        System.out.println(dbLib.enroll(stuid, crsid, secid, semid));
                    } else if ("C".equals(confirm)) {
                        System.out.println("Enrollment cancelled.");
                    } else {
                        System.out.println("Invalid input. Returning to main menu.");
                    }
                    break;

                case "Q":
                    System.out.println("Exiting from the system...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }


}
