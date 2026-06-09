package ex1.number_to_words;

import java.util.Scanner;

public class NumberToWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number to convert: ");
        int number = scanner.nextInt();
        System.out.println(convert(number));
    }

    private static String convert(int number) {
        if (number < 0 || number > 999) {
            return "out of ability";
        }

        if (number < 10) {
            return convertOneDigit(number);
        }

        if (number < 20) {
            return convertTeen(number);
        }

        if (number < 100) {
            return convertTwoDigits(number);
        }

        int hundreds = number / 100;
        int remainder = number % 100;
        String words = convertOneDigit(hundreds) + " hundred";

        if (remainder > 0) {
            words += " and " + convert(remainder);
        }

        return words;
    }

    private static String convertTwoDigits(int number) {
        int tens = number / 10;
        int ones = number % 10;

        if (ones == 0) {
            return convertTens(tens);
        }

        return convertTens(tens) + " " + convertOneDigit(ones);
    }

    private static String convertOneDigit(int number) {
        switch (number) {
            case 0:
                return "zero";
            case 1:
                return "one";
            case 2:
                return "two";
            case 3:
                return "three";
            case 4:
                return "four";
            case 5:
                return "five";
            case 6:
                return "six";
            case 7:
                return "seven";
            case 8:
                return "eight";
            case 9:
                return "nine";
            default:
                return "out of ability";
        }
    }

    private static String convertTeen(int number) {
        switch (number) {
            case 10:
                return "ten";
            case 11:
                return "eleven";
            case 12:
                return "twelve";
            case 13:
                return "thirteen";
            case 14:
                return "fourteen";
            case 15:
                return "fifteen";
            case 16:
                return "sixteen";
            case 17:
                return "seventeen";
            case 18:
                return "eighteen";
            case 19:
                return "nineteen";
            default:
                return "out of ability";
        }
    }

    private static String convertTens(int number) {
        switch (number) {
            case 2:
                return "twenty";
            case 3:
                return "thirty";
            case 4:
                return "forty";
            case 5:
                return "fifty";
            case 6:
                return "sixty";
            case 7:
                return "seventy";
            case 8:
                return "eighty";
            case 9:
                return "ninety";
            default:
                return "out of ability";
        }
    }
}
