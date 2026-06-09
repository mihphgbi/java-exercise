package ex1.leap_year_calculator;

import java.util.Scanner;

public class LeapYearCalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int year;
        System.out.print("Enter the year: ");
        year = input.nextInt();
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            System.out.println("The year is a leap year");
        }else {
            System.out.println("The year is not a leap year");
        }
    }
}
