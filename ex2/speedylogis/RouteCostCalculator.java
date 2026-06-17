package ex2.speedylogis;

/**
 * HỆ THỐNG ĐIỀU PHỐI SPEEDYLOGIS (LEGACY CODE)
 * Vấn đề: Thiết kế vòng lặp tốn tài nguyên, thiếu điều kiện dừng sớm.
 */

class FuelConfig {
    public double getBasePrice() {
        return 22500.0; // Giá xăng cơ bản
    }
}

public class RouteCostCalculator {
    public static final double TAX_RATE = 1.1;

    // Hàm giả lập tính toán thuế rất nặng (mất thời gian CPU)
    private double calculateHeavyTaxRate() {
        double tax = 1.0;
        for (int i = 0; i < 1000; i++) {
            tax += Math.sin(i) * Math.cos(i);
        }
        return 1.1 + (tax * 0); // Trả về 1.1 (10% tax) nhưng cố tình làm tốn CPU
    }

    public double calculateRouteCost(double[] distances, double maxBudget) {
        double totalCost = 0;

        System.out.println("Bắt đầu tính toán lộ trình...");
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < distances.length; i++) {
            // LỖI 1: Khởi tạo đối tượng mới ở mỗi vòng lặp (Object Churn)
            FuelConfig config = new FuelConfig();

            // LỖI 2: Hàm tính thuế là hằng số cho cả lộ trình nhưng lại bị gọi lại liên tục (Loop Invariant)
            double currentTax = calculateHeavyTaxRate();

            double stepCost = distances[i] * config.getBasePrice() * currentTax;
            totalCost += stepCost;

            // LỖI 3: Không có Early Exit. Dù totalCost > maxBudget vẫn tiếp tục lặp mù quáng.
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Thời gian chạy (Legacy): " + (endTime - startTime) + " ms");

        return totalCost;
    }

    public double calculateRouteCostV2(double[] distances, double maxBudget) {
        double totalCost = 0;

        System.out.println("Bắt đầu tính toán lộ trình...");
        long startTime = System.currentTimeMillis();
        FuelConfig config = new FuelConfig();

        for (double distance : distances) {
            if (totalCost > maxBudget) {
                System.out.println("Chi phí vượt quá ngân sách tối đa. Dừng tính toán.");
                break;
            }

            double stepCost = distance * config.getBasePrice() * TAX_RATE;
            totalCost += stepCost;
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Thời gian chạy (Legacy): " + (endTime - startTime) + " ms");

        return totalCost;
    }
}
