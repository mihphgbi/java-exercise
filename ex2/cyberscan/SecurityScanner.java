package ex2.cyberscan;

/**
 * HỆ THỐNG QUÉT AN NINH - CYBERSCAN (LEGACY CODE)
 * Vấn đề: If-else lồng sâu (Arrow Code) và vòng lặp dư thừa.
 */
public class SecurityScanner {
    public long logHackedUserAndFirstAdmin(String[] users, int[] loginAttempts, boolean[] isLocked) {
        System.out.println("--- BẮT ĐẦU QUÉT HỆ THỐNG ---");
        long startTime = System.nanoTime();

        // MỤC TIÊU 1: Báo động user không bị khóa, không bị ban, nhưng đăng nhập sai > 3 lần.
        // MỤC TIÊU 2: Tìm thấy ADMIN đầu tiên đang online để gửi báo cáo.

        for (int i = 0; i < users.length; i++) {

            // LỖI 1: Tối kỵ trong Clean Code - If lồng nhau 4 tầng (Arrow Anti-pattern)
            if (users[i] != null) {
                if (!users[i].startsWith("BANNED")) {
                    if (isLocked[i] == false) {
                        if (loginAttempts[i] > 3) {
                            System.out.println("Cảnh báo: " + users[i] + " sai mật khẩu " + loginAttempts[i] + " lần.");
                        }
                    }
                }
            }

            // LỖI 2: Dư thừa vòng lặp.
            // Nếu users[i] là null, mã vẫn cố gắng xuống đây kiểm tra tiếp thay vì bỏ qua vòng lặp.
            // Nếu đã tìm thấy ADMIN, hệ thống vẫn tiếp tục quét các user phía sau thay vì dừng lại.
            if (users[i] != null) {
                if (users[i].startsWith("ADMIN")) {
                    System.out.println("Đã tìm thấy Admin hệ thống: " + users[i]);
                    // THIẾU LỆNH ĐIỀU HƯỚNG VÒNG LẶP Ở ĐÂY
                }
            }
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Thời gian quét: " + totalTime + " nano giây.");
        return totalTime;
    }

    public long logHackedUserAndFirstAdminVer2(String[] users, int[] failedLoginAttempts, boolean[] isLocked) {
        long startTime = System.nanoTime();

        for (int i = 0; i < users.length; i++) {
            if (users[i] == null || isLocked[i] || users[i].startsWith("BANNED") ||(!isLocked[i] && failedLoginAttempts[i] <= 3)) {
                continue; // Ignore these users

            }
            if (users[i].startsWith("ADMIN")) {
                System.out.println("First system admin is : " + users[i]);
                break;
            }
            System.out.println("Alert: " + users[i] + " enter wrong password " + failedLoginAttempts[i] + " times.");
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime + " nano seconds.");
        return totalTime;
    }

    public void logAllHackedUserAndFirstAdmin(String[] users, int[] failedLoginAttempts, boolean[] isLocked) {
        System.out.println("--- BEGIN SCANNING SYSTEM ---");
        long startTime = System.nanoTime();
        String firstAdmin = getFirstOnlineAdmin(users, isLocked);
        System.out.println("First system admin is : " + firstAdmin);

        for (int i = 0; i < users.length; i++) {
            if (users[i] == null || isLocked[i] || users[i].startsWith("BANNED") || users[i].startsWith("ADMIN") || (!isLocked[i] && failedLoginAttempts[i] <= 3)) {
                continue; // Ignore these users

            }
            System.out.println("Alert: " + users[i] + " enter wrong password " + failedLoginAttempts[i] + " times.");
        }

        long endTime = System.nanoTime();
        System.out.println("Total time: " + (endTime - startTime) + " nano seconds.");
        System.out.println("--- END SCANNING ---");
    }

    public String getFirstOnlineAdmin(String[] users, boolean[] isLocked) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null && users[i].startsWith("ADMIN") && !isLocked[i]) {
                return users[i];
            }
        }
        return null;
    }

}
