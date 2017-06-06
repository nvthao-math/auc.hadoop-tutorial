/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.process.info;

import com.vng.process.execution.Executor;
import static com.vng.process.execution.Executor.readData;
import static com.vng.process.execution.Executor.timeToString;
import java.util.Date;
import java.util.List;

/**
 *
 * @author thaonv
 */
public class Process04 extends Executor {

    private static final String PATH_DATA = "/home/cpu10869-local/sandbox/process-monitor/data/input.txt";
    private static final String RESULT = "/home/cpu10869-local/sandbox/process-monitor/data/result.txt";
    private static final String LOG_DATA = "/home/cpu10869-local/sandbox/process-monitor/process_time.log";

    @Override
    public void execute() {
        try {
            long t1 = System.currentTimeMillis();
            List<String> data = readData(PATH_DATA);
            for (String val : data) {
                if (Integer.parseInt(val) < 400000) {
                    long x = getFactorial(Integer.parseInt(val));
                    System.out.println(Integer.parseInt(val));
                    Executor.writeData(Long.toString(x) + getString(), RESULT);
                }
            }
            long duration = System.currentTimeMillis() - t1;
            String time = timeToString(new Date());
            Executor.writeData(time + "\t" + "PROCESS-01" + "\t" + "time: " + Long.toString(duration) + " (ms)", LOG_DATA);
            System.out.println("Time: " + duration + " (ms)");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private long getFactorial(Integer x) {
        long result = 1l;
        for (int i = 1; i <= x; i++) {
            result *= i;
        }
        return result;
    }

    private String getString() {
        String str = "Miền Bắc và Trung nắng nóng gay gắt nhất từ đầu mùa\n"
                + "Từ ngày 2 đến 4/6, nền nhiệt một số nơi Bắc Bộ có thể lên đến 40 độ C, còn miền Trung khoảng 41-42 độ C.\n"
                + "Trung tâm dự báo khí tượng thủy văn Trung ương cho biết, do vùng thấp nóng phía tây kết hợp hiệu ứng gió phơn hoạt động mạnh nên nắng nóng xảy ra khắp miền Bắc và miền Trung từ Thanh Hóa đến Phú Yên. Lúc 10h, nhiệt độ hai khu vực đồng loạt tăng lên 32-34 độ C, tại thành phố Hà Tĩnh và Quảng Ngãi cao 35 độ C. Mức nhiệt cao nhất hôm nay ở miền Bắc dự báo 36-37 độ C, còn miền Trung 36-38 độ C.\n"
                + "\n"
                + "Từ ngày 2 đến 4/6 mới là thời điểm nóng nhất ở miền Bắc và miền Trung. Trong đó Bắc Bộ tăng lên 37-38 độ C, một số nơi như Mường La (Sơn La), Mường Lay (Điện Biên), Mường Tè hay Hòa Bình trên 40 độ C.\n"
                + "\n"
                + "Riêng Hà Nội hôm nay nhiệt độ cao nhất có thể là 37 độ C và tiếp tục lên 38-39 độ C trong các ngày tiếp theo. Đây sẽ là mức nhiệt cao nhất từ đầu hè.\n"
                + "\n"
                + "mien-bac-va-trung-nang-nong-gay-gat-nhat-tu-dau-mua\n"
                + "Miền Bắc sẽ trải qua đợt nắng nóng kéo dài 3 ngày. Ảnh: Ngọc Thành.\n"
                + "Với miền Trung, từ ngày mai nền nhiệt lên 37-39 độ C; ở Tương Dương (Nghệ An), Hương Khê, Đông Hà (Quảng Trị), Nam Đông (Thừa Thiên Huế) tới 41-42 độ C.\n"
                + "\n"
                + "Các chuyên gia nhận định đây sẽ là đợt nắng nóng diện rộng ở miền Bắc và miền Trung gay gắt nhất từ đầu mùa hè. Trước đó, đợt nắng nóng ngày 1-4/5 miền Trung phổ biến 37-38 độ C, cao nhất Con Cuông 41 độ C vào ngày 2/5.\n"
                + "\n"
                + "Đợt nắng nóng dự báo sẽ kéo dài đến hết 5/6 ở miền Bắc và 6/6 ở miền Trung.\n"
                + "\n"
                + "Hà Trung";
        return str;
    }

}
