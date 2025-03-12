package edu.ufl.ancha.client;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class RegistrationClient {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter L to list enrollment information for a student, E to enroll in a course, Q to Quit");
            String choice = sc.nextLine().toUpperCase();

            if (choice.equals("L")) {
                System.out.print("Student Id: ");
                String stuid = sc.nextLine();
                System.out.print("Semester Id (Fyy for Fall, Syy for Spring, Uyy for Summer): ");
                String semid = sc.nextLine();

                List<String> enrolledCourses = ListEnrolled(stuid, semid);
                enrolledCourses.forEach(System.out::println);
            }
            else if (choice.equals("E")) {
                System.out.print("Course Id: ");
                String crsid = sc.nextLine();
                System.out.print("Semester Id (Fyy for Fall, Syy for Spring, Uyy for Summer): ");
                String semid = sc.nextLine();

                List<String> availableSections = ListAvailable(crsid, semid);
                availableSections.forEach(System.out::println);

                System.out.println("These are the available sections for " + crsid + " during semester " + semid +
                        ". If you wish to enroll press E again otherwise press C to cancel.");
                String confirm = sc.nextLine().toUpperCase();

                if (confirm.equals("E")) {
                    System.out.print("Student Id: ");
                    String stuid = sc.nextLine();
                    System.out.print("Section Id: ");
                    String secid = sc.nextLine();

                    System.out.println(Enroll(stuid, crsid, secid, semid));
                }
            }
            else if (choice.equals("Q")) {
                System.out.println("Exiting...");
                break;
            }
        }
        sc.close();


    }
    public static List<String> ListEnrolled(String stuid, String semid) {

        List<String> enrolledList = new LinkedList<>();
        enrolledList.add("ISM6236, " + semid + ",1,MW 9-10");
        enrolledList.add("ISM6485, " + semid + ",2,MW 3-4");
        return enrolledList;
    }

    public static List<String> ListAvailable(String crsid, String semid) {
        List<String> availableList = new LinkedList<>();
        // 1,MW 1-2,10 => Section 1, meets MW 1-2, 10 seats left
        // 2,MW 3-4,12 => Section 2, meets MW 3-4, 12 seats left
        availableList.add("1,MW 1-2,10");
        availableList.add("2,MW 3-4,12");
        return availableList;
    }

    public static String Enroll(String stuid, String crsid, String secid, String semid) {

        return "Student " + stuid + " added to section " + secid + " of "
                + crsid + " for semester " + semid + ".";
    }
}
