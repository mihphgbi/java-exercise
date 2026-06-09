package wallet_system;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class WalletSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigDecimal currentBalance = new BigDecimal(2147000000);

        System.out.println("--- DEPOSIT TRANSACTION ---");
        System.out.print("Enter deposit amount (VND): ");
        BigDecimal depositAmount = scanner.nextBigDecimal();

        if (depositAmount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Deposit amount is negative");
        } else {
            BigDecimal newBalance = currentBalance.add(depositAmount);
            System.out.println("New balance: " + newBalance.stripTrailingZeros().toPlainString() + " VND");
        }

        System.out.println("\n--- SPENDING STATISTICS ---");
        BigDecimal totalSpent = BigDecimal.valueOf(0);
        long transactionCount = 0;
        if (transactionCount > 0 || totalSpent.compareTo(BigDecimal.ZERO) >= 0) {
            BigDecimal average = totalSpent.divide(BigDecimal.valueOf(transactionCount), 2, RoundingMode.HALF_UP).stripTrailingZeros();
            System.out.println("Average spending per transaction: " + average + " VND");
        } else {
            System.out.println("Invalid transaction count or total spending per transaction");

        }
    }
}
