package day_of_month;

import java.util.Scanner;

public class DayOfMonth {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Which month that you want to count days? ");
        int month = sc.nextInt();
        String dateOfMonth;
        switch (month) {
            case 2:
                dateOfMonth = "28 or 29";
                break;
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                dateOfMonth = "31";
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                dateOfMonth = "30";
                break;
            default:
                dateOfMonth = "";
        }
        if (!dateOfMonth.equals("")) {
            System.out.printf("Days of Month: %s\n", dateOfMonth);
        } else {
            System.out.println("Invalid input!");
        }
    }
}
