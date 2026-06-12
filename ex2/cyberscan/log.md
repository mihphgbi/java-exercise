- Write an simple method to generate data for testing, which creates 3 arrays: `users`, `loginAttempts`, and `isLocked`. In `user`, put value "ADMIN_01" at index 9, random value 1-10 for each index `loginAttempts`, and random value `isLocked`  
- Help me create an method to write benchmark report to file 'benchmark_report.txt' with the format: 
"Security Scanner Benchmark Report
  ================================
  Test data size: %d users
  Admin test position: %d

Old version time: %d ns
New version time: %d ns
Improvement: %.2f%%
Old version CPU Usage: [cpu_usage]%
New version CPU Usage: [cpu_usage]%
Improvement CPU Usage: %.2f%%"

- Improve hàm writeBenchmarkReport để giá trị chạy thức tế ở terminal và giá trị ở báo cáo phải đồng bộ nhau