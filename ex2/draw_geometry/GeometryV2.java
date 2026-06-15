package ex2.draw_geometry;

import java.util.Scanner;

public class GeometryV2 {
    public static void main(String[] args) {
        int choice = -1;
        Scanner scanner = new Scanner(System.in);
        while (choice != 4) {
            System.out.println("Menu");
            System.out.println("1. Print the rectangle");
            System.out.println("2. Print the square triangle (The corner is square at 4 different angles: top-left, top-right, botton-left, botton-right)");
            System.out.println("3. Print isosceles triangle");
            System.out.println("4. Exit");
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    for (int i = 6; i > 0; i--) {
                        System.out.println("*".repeat(i));
                    }
                    break;
                case 2:
                    System.out.println("The corner is square at top-left");
                    for (int i = 6; i >= 0; i--) {
                        for (int j = 0; j < i ; j++) {
                            System.out.print("*");
                        }
                        System.out.println();
                    }
                    System.out.println("The corner is square at top-right");
                    for (int i = 6; i > 0; i--) {
                        for (int j = 0; j < 6 - i; j++) {
                            System.out.print(" ");
                        }
                        for (int j = 0; j < i; j++) {
                            System.out.print("*");
                        }
                        System.out.println();
                    }
                    System.out.println("The corner is square at bottom-left");
                    for (int i = 0; i < 6; i++) {
                        for (int j = 0; j < i ; j++) {
                            System.out.print("*");
                        }
                        System.out.println();
                    }
                    System.out.println("The corner is square at bottom-right");
                    for (int i = 1; i <= 6; i++) {
                        for (int j = 0; j < 6 - i; j++) {
                            System.out.print(" ");
                        }
                        for (int j = 0; j < i; j++) {
                            System.out.print("*");
                        }
                        System.out.println();
                    }

                    break;
                case 3:
                    for (int i = 1; i <= 6; i++) {
                        for (int j = 0; j < 6 - i; j++) {
                            System.out.print(" ");
                        }
                        for (int j = 0; j < (i * 2) - 1; j++) {
                            System.out.print("*");
                        }
                        System.out.println();
                    }
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("No choice!");

            }
        }
    }
}
