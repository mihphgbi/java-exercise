package ex2.prime;

import java.util.Scanner;

public class PrimeV2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of prime numbers to print:");
        int quantity = input.nextInt();

        int count = 0;
        int number = 2;

        while (count < quantity) {
            boolean isPrime = true;

            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                System.out.println(number);
                count++;
            }

            number++;
        }
    }
}
