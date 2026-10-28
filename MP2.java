package com.mycompany.mp2;

import java.util.Scanner;
import java.util.HashSet;
import java.util.HashMap;
public class MP2 {
   private static HashSet<String> courseCodes = new HashSet<>();
   private static HashSet<String> enrollmentPairs = new HashSet<>();
   private static HashMap<Integer, String> students = new HashMap<>();
   private static Scanner input = new Scanner(System.in);
   public static void main(String[] args) {
       int choice = -1;
       do {
           System.out.println("\n===== COURSE REGISTRATION SYSTEM =====");
           System.out.println("1. Add Student");
           System.out.println("2. Add Course");
           System.out.println("3. Enroll Student");
           System.out.println("4. List Student Courses");
           System.out.println("5. List All Courses");
           System.out.println("0. Exit");
           System.out.print("Enter choice: ");
           try {
               choice = Integer.parseInt(input.nextLine().trim());
           } catch (NumberFormatException e) {
               System.out.println("Invalid menu option!");
               choice = -1;
               continue;
           }
           switch (choice) {
               case 1:
                   addStudent();
                   break;
               case 2:
                   addCourse();
                   break;
               case 3:
                   enrollStudent();
                   break;
               case 4:
                   listStudentCourses();
                   break;
               case 5:
                   listCourses();
                   break;
               case 0:
                   System.out.println("Thank you for using the system!");
                   break;
               default:
                   System.out.println("Invalid menu option!");
           }
       } while (choice != 0);
   }
   private static Integer readID() {
       System.out.print("Enter Student ID: ");
       try {
           int id = Integer.parseInt(input.nextLine().trim());
           if (id <= 0) {
               System.out.println("Invalid Student ID!");
               return null;
           }
           return id;
       } catch (NumberFormatException e) {
           System.out.println("Invalid Student ID!");
           return null;
       }
   }
   private static String readCourseCode() {
       System.out.print("Enter Course Code: ");
       String code = input.nextLine().trim();
       if (code.isEmpty()) {
           System.out.println("Course code cannot be empty!");
           return null;
       }
       if (!code.equals(code.toUpperCase())) {
           System.out.println("Invalid course code! Use uppercase.");
           return null;
       }
       return code;
   }
   private static void addStudent() {
       Integer id = readID();
       if (id == null) {
           return;
       }
       if (students.containsKey(id)) {
           System.out.println("Duplicate Student ID!");
           return;
       }
       System.out.print("Enter Student Name: ");
       String name = input.nextLine().trim();
       if (name.isEmpty()) {
           System.out.println("Student name cannot be empty!");
           return;
       }
       students.put(id, name);
       System.out.println("Student added successfully!");
   }
   private static void addCourse() {
       String code = readCourseCode();
       if (code == null) {
           return;
       }
       if (courseCodes.add(code)) {
           System.out.println("Course added successfully!");
       } else {
           System.out.println("Duplicate course rejected!");
       }
   }
   private static void enrollStudent() {
       Integer id = readID();
       if (id == null) {
           return;
       }
       if (!students.containsKey(id)) {
           System.out.println("Student not found!");
           return;
       }
       String code = readCourseCode();
       if (code == null) {
           return;
       }
       if (!courseCodes.contains(code)) {
           System.out.println("Course not found!");
           return;
       }
       String pair = id + ":" + code;
       if (enrollmentPairs.add(pair)) {
           System.out.println("Enrollment added successfully!");
       } else {
           System.out.println("Duplicate enrollment rejected!");
       }
   }
   private static void listStudentCourses() {
       Integer id = readID();
       if (id == null) {
           return;
       }
       if (!students.containsKey(id)) {
           System.out.println("Student not found!");
           return;
       }
       System.out.println("\nStudent: " + students.get(id));
       System.out.println("Enrolled Courses:");
       boolean found = false;
       String prefix = id + ":";
       for (String pair : enrollmentPairs) {
           if (pair.startsWith(prefix)) {
               System.out.println("- " +
                       pair.substring(prefix.length()));
               found = true;
           }
       }
       if (!found) {
           System.out.println("No enrolled courses.");
       }
   }
   private static void listCourses() {
       if (courseCodes.isEmpty()) {
           System.out.println("No courses available.");
           return;
       }
       System.out.println("\nAvailable Courses:");
       for (String code : courseCodes) {
           System.out.println("- " + code);
       }
   }
}