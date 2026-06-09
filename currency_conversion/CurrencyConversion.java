package currency_conversion;

import java.math.BigDecimal;
import java.util.Scanner;

public class CurrencyConversion {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the rate to conversion: ");
        BigDecimal rate = input.nextBigDecimal();
        System.out.println("Please enter the USD of conversions: ");
        BigDecimal usd = input.nextBigDecimal();
        System.out.println("The VND is: " + usd.multiply(rate));

    }
}
