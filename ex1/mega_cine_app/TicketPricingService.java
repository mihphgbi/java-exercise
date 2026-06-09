package ex1.mega_cine_app;

import java.math.BigDecimal;

/**
 * HỆ THỐNG BÁN VÉ MEGACINE (LEGACY CODE)
 * Vấn đề: Arrow Anti-Pattern, Nested if-else quá sâu, khó bảo trì.
 */
public class TicketPricingService {
    private static final int CHILDREN_CODE = 1;
    private static final int ADULT_CODE = 2;
    private static final int SENIOR_CODE = 3;
    private static final BigDecimal BASE_PRICE = BigDecimal.valueOf(100000);

    public double calculatePrice(int age, String dayOfWeek, boolean isMember) {
        double basePrice = 100000;
        double finalPrice = 0;

        // Vấn đề 1: Validate đầu vào ôm trọn toàn bộ logic bên trong
        if (age > 0) {
            if (dayOfWeek != null && !dayOfWeek.isEmpty()) {
                // Vấn đề 2: Logic ngày thứ 3 bị tách rời thiếu nhất quán
                if (dayOfWeek.equalsIgnoreCase("Tuesday")) {
                    finalPrice = basePrice * 0.5; // Happy Day 50%
                } else {
                    // Vấn đề 3: Lồng ghép sâu cho độ tuổi và thành viên
                    if (age <= 12) {
                        if (isMember) {
                            finalPrice = (basePrice * 0.7) - 10000; // Trẻ em + VIP
                        } else {
                            finalPrice = basePrice * 0.7; // Trẻ em
                        }
                    } else if (age >= 60) {
                        finalPrice = basePrice * 0.6; // Người cao tuổi
                    } else {
                        // Người lớn bình thường
                        if (dayOfWeek.equalsIgnoreCase("Saturday") || dayOfWeek.equalsIgnoreCase("Sunday")) {
                            if (isMember) {
                                finalPrice = (basePrice * 1.2) * 0.9; // Cuối tuần + VIP được giảm 10%
                            } else {
                                finalPrice = basePrice * 1.2; // Cuối tuần tăng giá
                            }
                        } else {
                            if (isMember) {
                                finalPrice = basePrice * 0.9; // Ngày thường + VIP
                            } else {
                                finalPrice = basePrice;
                            }
                        }
                    }
                }
            } else {
                return -1; // Lỗi ngày
            }
        } else {
            return -1; // Lỗi tuổi
        }

        return finalPrice;
    }

    public BigDecimal calculatePriceRefactor(int age, String dayOfWeek, boolean isMember) {
        if (age <= 0 || dayOfWeek == null || dayOfWeek.isEmpty()) {
            return BigDecimal.valueOf(-1);
        }

        if (dayOfWeek.equalsIgnoreCase("Tuesday")) {
            return BASE_PRICE.multiply(BigDecimal.valueOf(0.5)); // Happy Day 50
        }

        int customerGroup = getCustomerGroup(age);
        return switch (customerGroup) {
            case CHILDREN_CODE -> isMember
                    ? BASE_PRICE.multiply(new BigDecimal("0.7")).subtract(new BigDecimal("10000"))
                    : BASE_PRICE.multiply(new BigDecimal("0.7"));
            case ADULT_CODE -> {
                BigDecimal adultPrice = isWeekend(dayOfWeek) ? BASE_PRICE.multiply(new BigDecimal("1.2")) : BASE_PRICE;
                if (isMember) {
                    yield adultPrice.multiply(new BigDecimal("0.9"));
                }
                yield adultPrice;
            }
            case SENIOR_CODE -> BASE_PRICE.multiply(new BigDecimal("0.6"));
            default -> throw new IllegalStateException("Unexpected customer group: " + customerGroup);
        };
    }

    public static int getCustomerGroup(int age) {
        if (age <= 12) return CHILDREN_CODE;
        if (age < 60) return ADULT_CODE;
        return SENIOR_CODE;
    }

    public static boolean isWeekend(String dayOfWeek) {
        return dayOfWeek.equalsIgnoreCase("Saturday") || dayOfWeek.equalsIgnoreCase("Sunday");
    }

}
