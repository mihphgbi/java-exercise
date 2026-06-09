package ex1.mega_cine_app;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketPricingServiceTest {
    private final TicketPricingService ticketPricingService = new TicketPricingService();

    @ParameterizedTest(name = "{0}")
    @MethodSource("ticketPricingCases")
    void testMethodCalculatePriceRefactor(String testCaseId, int age, String dayOfWeek, boolean isMember, String expectedPrice) {
        assertEquals(
                0,
                ticketPricingService.calculatePriceRefactor(age, dayOfWeek, isMember).compareTo(new BigDecimal(expectedPrice))
        );
    }

    private static Stream<Arguments> ticketPricingCases() {
        return Stream.of(
                Arguments.of("TC-TPS-001", 1, "Monday", false, "70000"),
                Arguments.of("TC-TPS-002", 1, "Monday", true, "60000"),
                Arguments.of("TC-TPS-003", 1, "Friday", false, "70000"),
                Arguments.of("TC-TPS-004", 12, "Friday", false, "70000"),
                Arguments.of("TC-TPS-005", 12, "Friday", true, "60000"),
                Arguments.of("TC-TPS-006", 13, "Friday", false, "100000"),
                Arguments.of("TC-TPS-007", 13, "Friday", true, "90000"),
                Arguments.of("TC-TPS-008", 59, "Friday", false, "100000"),
                Arguments.of("TC-TPS-009", 59, "Friday", true, "90000"),
                Arguments.of("TC-TPS-010", 60, "Friday", false, "60000"),
                Arguments.of("TC-TPS-011", 60, "Friday", true, "60000"),
                Arguments.of("TC-TPS-012", 120, "Friday", false, "60000"),
                Arguments.of("TC-TPS-013", 12, "Tuesday", false, "50000"),
                Arguments.of("TC-TPS-014", 12, "Tuesday", true, "50000"),
                Arguments.of("TC-TPS-015", 30, "Tuesday", false, "50000"),
                Arguments.of("TC-TPS-016", 30, "Tuesday", true, "50000"),
                Arguments.of("TC-TPS-017", 60, "Tuesday", false, "50000"),
                Arguments.of("TC-TPS-018", 60, "Tuesday", true, "50000"),
                Arguments.of("TC-TPS-019", 30, "tuesday", false, "50000"),
                Arguments.of("TC-TPS-020", 30, "TuEsDaY", true, "50000"),
                Arguments.of("TC-TPS-021", 30, "Saturday", false, "120000"),
                Arguments.of("TC-TPS-022", 30, "Saturday", true, "108000"),
                Arguments.of("TC-TPS-023", 30, "Sunday", false, "120000"),
                Arguments.of("TC-TPS-024", 30, "Sunday", true, "108000"),
                Arguments.of("TC-TPS-025", 30, "saturday", true, "108000"),
                Arguments.of("TC-TPS-026", 30, "SuNdAy", false, "120000"),
                Arguments.of("TC-TPS-027", 10, "Saturday", false, "70000"),
                Arguments.of("TC-TPS-028", 10, "Sunday", true, "60000"),
                Arguments.of("TC-TPS-029", 70, "Saturday", false, "60000"),
                Arguments.of("TC-TPS-030", 70, "Sunday", true, "60000"),
                Arguments.of("TC-TPS-031", 30, "Monday", true, "90000"),
                Arguments.of("TC-TPS-032", 30, "Monday", false, "100000"),
                Arguments.of("TC-TPS-033", 30, "Wednesday", true, "90000"),
                Arguments.of("TC-TPS-034", 30, "Thursday", false, "100000"),
                Arguments.of("TC-TPS-035", 30, "Friday", true, "90000"),
                Arguments.of("TC-TPS-036", 0, "Monday", false, "-1"),
                Arguments.of("TC-TPS-037", -1, "Monday", true, "-1"),
                Arguments.of("TC-TPS-038", -100, "Tuesday", false, "-1"),
                Arguments.of("TC-TPS-040", 30, "", false, "-1"),
                Arguments.of("TC-TPS-041", 10, "", true, "-1"),
                Arguments.of("TC-TPS-042", 70, "", false, "-1"),
                Arguments.of("TC-TPS-043", 30, " ", false, "100000"),
                Arguments.of("TC-TPS-044", 30, " ", true, "90000"),
                Arguments.of("TC-TPS-045", 30, " Tuesday ", false, "100000"),
                Arguments.of("TC-TPS-046", 30, " Saturday ", true, "90000"),
                Arguments.of("TC-TPS-047", 30, "Holiday", false, "100000"),
                Arguments.of("TC-TPS-048", 30, "Holiday", true, "90000"),
                Arguments.of("TC-TPS-049", 10, "Holiday", true, "60000"),
                Arguments.of("TC-TPS-050", 70, "Holiday", true, "60000"),
                Arguments.of("TC-TPS-051", Integer.MAX_VALUE, "Monday", false, "60000"),
                Arguments.of("TC-TPS-052", Integer.MIN_VALUE, "Monday", false, "-1"),
                Arguments.of("TC-TPS-053", Integer.MAX_VALUE, "Tuesday", true, "50000"),
                Arguments.of("TC-TPS-054", 30, "Saturday", true, "108000"),
                Arguments.of("TC-TPS-055", 10, "Monday", true, "60000")
        );
    }

}
