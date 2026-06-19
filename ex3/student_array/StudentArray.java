package ex3.student_array;

import java.util.Scanner;

public class StudentArray {
    public static void main(String[] args) {
        String[] students = {"Christian", "Michael", "Camila", "Sienna", "Tanya", "Connor", "Zachariah", "Mallory", "Zoe", "Emily"};
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a name's students: ");
        String name = input.nextLine();

        boolean isFound = false;
        for (int i = 1; i < students.length; i++) {
            if (students[i].equals(name)) {
                System.out.println("Position of the students in the list " + name + " is: " + i);
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            System.out.println("Not found" + name + " in the list.");
        }
    }
}
