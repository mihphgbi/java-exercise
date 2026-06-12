package ex2.interest_calculator;

import java.util.Scanner;

public class Interest {
    public static void main(String[] args) {
        double money = 1.0;
        int month = 1;
        double interestRate = 1.0;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter investment amount: ");
        money = scanner.nextDouble();

        System.out.print("Enter number of months: ");
        month = scanner.nextInt();

        System.out.print("Enter annual interest rate in percentage: ");
        interestRate = scanner.nextDouble();

        double totalInterest = 0;
        for (int i = 1; i <= month; i++) {
            totalInterest += money * (interestRate / 100) / 12 * month;
        }

        System.out.printf("Total interest: %.2f%n", totalInterest);
    }
}
