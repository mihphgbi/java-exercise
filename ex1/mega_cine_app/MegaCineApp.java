package ex1.mega_cine_app;

import java.math.BigDecimal;
import java.util.Scanner;

public class MegaCineApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Mega Cine App!");
        System.out.println("Please enter the day: ");
        String date = scanner.next();
        System.out.println("Please enter the age of customer: ");
        int ageOfCustomer = scanner.nextInt();
        System.out.println("Are you member of cinema?: ");
        boolean isMember = scanner.nextBoolean();

        TicketPricingService ticketPricingService = new TicketPricingService();
        BigDecimal totalPrice = ticketPricingService.calculatePriceRefactor(ageOfCustomer,date,isMember);
        if (totalPrice.compareTo(new BigDecimal(-1))==0) {
            System.out.println("Invalid input! Please check your age and day of week.");
        } else {
            System.out.println("Your price is: " + totalPrice);
        }
    }
}
