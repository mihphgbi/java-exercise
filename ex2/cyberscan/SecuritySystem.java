package ex2.cyberscan;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.function.LongSupplier;

public class SecuritySystem {
    private static final int TEST_DATA_SIZE = 1_000_000;
    private static final int ADMIN_INDEX = 9;
    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private static final OperatingSystemMXBean OPERATING_SYSTEM =
            (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    public static void main(String[] args) throws IOException {
        writeBenchmarkReport();
        System.out.println("Benchmark report saved to benchmark_report.txt");
    }

    public static SecurityData generateSecurityData() {
        Random random = new Random();
        String[] users = new String[TEST_DATA_SIZE];
        int[] loginAttempts = new int[TEST_DATA_SIZE];
        boolean[] isLocked = new boolean[TEST_DATA_SIZE];

        for (int i = 0; i < users.length; i++) {
            users[i] = "USER_" + (i + 1);
            loginAttempts[i] = random.nextInt(10) + 1;
            isLocked[i] = random.nextBoolean();
        }
        users[ADMIN_INDEX] = "ADMIN_01";
        loginAttempts[ADMIN_INDEX] = 4;
        isLocked[ADMIN_INDEX] = false;

        return new SecurityData(users, loginAttempts, isLocked);
    }

    public static void writeBenchmarkReport() throws IOException {
        SecurityData securityData = generateSecurityData();
        SecurityScanner securityScanner = new SecurityScanner();

        BenchmarkResult oldVersionResult = measureScan(() ->
                securityScanner.logHackedUserAndFirstAdmin(
                        securityData.users,
                        securityData.loginAttempts,
                        securityData.isLocked
                )
        );
        BenchmarkResult newVersionResult = measureScan(() ->
                securityScanner.logHackedUserAndFirstAdminVer2(
                        securityData.users,
                        securityData.loginAttempts,
                        securityData.isLocked
                )
        );

        double improvement = oldVersionResult.timeNs == 0
                ? 0
                : ((double) (oldVersionResult.timeNs - newVersionResult.timeNs) / oldVersionResult.timeNs) * 100;
        double cpuUsageImprovement = oldVersionResult.cpuUsage == 0
                ? 0
                : ((oldVersionResult.cpuUsage - newVersionResult.cpuUsage) / oldVersionResult.cpuUsage) * 100;

        String report = """
                Security Scanner Benchmark Report
                ================================
                Test data size: %d users
                Admin test position: %d
                
                Old version time: %d ns
                New version time: %d ns
                Improvement: %.2f%%
                Old version CPU Usage: %.2f%%
                New version CPU Usage: %.2f%%
                Improvement CPU Usage: %.2f%%
                """.formatted(
                TEST_DATA_SIZE,
                ADMIN_INDEX + 1,
                oldVersionResult.timeNs,
                newVersionResult.timeNs,
                improvement,
                oldVersionResult.cpuUsage,
                newVersionResult.cpuUsage,
                cpuUsageImprovement
        );

        Files.writeString(Path.of("src", "ex2", "cyberscan", "benchmark_report.txt"), report);
    }

    private static BenchmarkResult measureScan(LongSupplier scanTask) {
        long startCpuTime = OPERATING_SYSTEM.getProcessCpuTime();
        long measuredTime = scanTask.getAsLong();
        long endCpuTime = OPERATING_SYSTEM.getProcessCpuTime();

        long elapsedCpuTime = endCpuTime - startCpuTime;
        double cpuUsage = measuredTime == 0
                ? 0
                : ((double) elapsedCpuTime / measuredTime) * 100 / AVAILABLE_PROCESSORS;

        return new BenchmarkResult(measuredTime, cpuUsage);
    }

    private static class BenchmarkResult {
        private final long timeNs;
        private final double cpuUsage;

        private BenchmarkResult(long timeNs, double cpuUsage) {
            this.timeNs = timeNs;
            this.cpuUsage = cpuUsage;
        }
    }

    public static class SecurityData {
        public final String[] users;
        public final int[] loginAttempts;
        public final boolean[] isLocked;

        public SecurityData(String[] users, int[] loginAttempts, boolean[] isLocked) {
            this.users = users;
            this.loginAttempts = loginAttempts;
            this.isLocked = isLocked;
        }
    }
}
