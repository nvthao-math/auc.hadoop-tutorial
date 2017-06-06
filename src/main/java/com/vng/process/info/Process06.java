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
public class Process06 extends Executor {

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
                + "Hà Trung"
                + "Đề xuất chất vấn về thực trạng khám chữa bệnh\n"
                + "Các đại biểu Quốc hội nhận được phiếu xin ý kiến nội dung chất vấn tại kỳ họp lần này, trong đó có nhóm vấn đề về lĩnh vực y tế.\n"
                + "Quốc hội sẽ đề nghị Thủ tướng trả lời chất vấn  /  Quốc hội sẽ tăng tính tranh luận, đối thoại\n"
                + "Để chuẩn bị cho hoạt động chất vấn tại kỳ họp đang diễn ra, Tổng thư ký Quốc hội Nguyễn Hạnh Phúc đã gửi phiếu xin ý kiến các đại biểu Quốc hội về dự kiến 5 nhóm vấn đề. \n"
                + "\n"
                + "Đầu tiên là giải pháp đột phá và lộ trình thực hiện có hiệu quả tái cơ cấu ngành nông nghiệp trong thời gian tới; biện pháp nâng cao chất lượng, hiệu quả và sức cạnh tranh của sản phẩm, gắn sản xuất với tiêu thụ, hướng đến xây dựng nền nông nghiệp hàng hoá...\n"
                + "\n"
                + "Nhóm vấn đề tiếp theo là giải pháp cụ thể huy động các nguồn lực trong xã hội cho đầu tư phát triển; việc phân bổ, thông báo kế hoạch vốn đầu tư từ ngân sách Nhà nước; tăng cường quản lý, kiểm soát, tránh lãng phí trong đầu tư công; trách nhiệm của bộ, ngành trong việc thực hiện các dự án trọng điểm quốc gia.\n"
                + "\n"
                + "Thứ ba là nhóm vấn đề về thực trạng, giải pháp nâng cao chất lượng, hiệu quả khám chữa bệnh; giá thuốc và quản lý nhà nước về giá thuốc, cung ứng thuốc tại các cơ sở y tế...\n"
                + "\n"
                + "Nếu nhóm vấn đề này được lựa chọn, đây sẽ là lần đầu tiên Bộ trưởng Y tế Nguyễn Thị Kim Tiến đăng đàn trên nghị trường của Quốc hội khoá 14.\n"
                + "\n"
                + "de-xuat-chat-van-ve-thuc-trang-kham-chua-benh\n"
                + "Bộ trưởng Y tế Nguyễn Thị Kim Tiến.\n"
                + "Nhóm vấn đề thứ 4 là việc quản lý, cấp phép các hoạt động văn hoá, nghệ thuật; khai thác, sử dụng các công trình văn hoá, thể thao; tổ chức lễ hội...\n"
                + "\n"
                + "Cuối cùng là công tác phòng chống tội phạm, bảo đảm an ninh, trật tự an toàn xã hội; giải phá nâng cao hiệu quả phòng, chống tội phạm hình sự, nhất là tội phạm giết người, cố ý gây thương tích, hiếp dâm, xâm hại tình dục trẻ em, chống người thi hành công vụ.\n"
                + "\n"
                + "Ngoài ra, phiếu xin ý kiến nêu trên được thiết kế với một dòng để trống cho nhóm vấn đề và Bộ trưởng/Trưởng ngành cụ thể mà đại biểu muốn đề xuất thêm. \n"
                + "\n"
                + "Tại kỳ họp giữa năm này, lần đầu tiên Quốc hội dành 3 ngày làm việc để tổ chức hoạt động chất vấn (ngày 13, 14 và 15/6). Trong phiên họp Ủy ban Thường vụ Quốc hội ngày 17/5, Chủ tịch Quốc hội cho hay sẽ đề nghị Thủ tướng cùng trả lời chất vấn với các Phó thủ tướng."
                + "Kiếm 1 triệu USD trong 3 tháng nhờ bán hàng online\n"
                + "Thương mại điện tử đã giúp Trevor Chapman chuyển từ công việc ngốn 12 giờ mỗi ngày sang chỉ 1 giờ làm việc mỗi tuần, với doanh thu hằng tháng 350.000 USD.\n"
                + "10 cách giúp bạn tiêu tiền thông minh nhất / Kiếm hơn 200 triệu USD từ đồng hồ bình dân\n"
                + "13 năm trước, Trevor Chapman (Mỹ) là một sinh viên, phải vay tiền đóng học phí, và làm thêm việc bán thuốc diệt côn trùng tận nhà. Nhiều năm sau đó, anh bắt đầu kinh doanh riêng, mở công ty lắp đặt pin mặt trời. Việc kinh doanh khá phát đạt, nhanh chóng mở rộng ra 3 bang. Nhưng chỉ 2 năm sau, anh lại bắt đầu băn khoăn.\n"
                + "\n"
                + "\"Khi ấy, tôi tự nói với bản thân: Mình 32 tuổi rồi, liệu mình có sẵn sàng chờ đến khi bọn trẻ lớn hẳn rồi mới được tận hưởng cuộc sống như đã mơ ước hay không?\", Chapman nghĩ lại.\n"
                + "\n"
                + "Rồi anh chợt nhớ một câu nói của huyền thoại đầu tư Warren Buffett: \"Nếu không tìm được cách kiếm tiền kể cả trong lúc ngủ, anh sẽ phải làm việc cho đến lúc chết\".\n"
                + "\n"
                + "Và anh đã tìm ra cách tốt nhất để có thu nhập bị động, đó là thương mại điện tử. Đây là ngành công nghiệp khổng lồ, với doanh số bán lẻ toàn cầu năm 2015 là 2.300 tỷ USD.\n"
                + "\n"
                + "kiem-1-trieu-usd-trong-3-thang-nho-ban-hang-online\n"
                + "Chapman muốn có nhiều thời gian hơn cho gia đình. Ảnh: Trevor Chapman\n"
                + "Tuy nhiên, trước khi thực sự bỏ công việc hiện tại, anh muốn thử xem mình có thể sống bằng việc này hay không. \"Anh phải làm mọi thứ, nhưng không được bỏ công việc lúc bấy giờ\", Chapman cho biết.\n"
                + "\n"
                + "Thế là mỗi đêm, anh dành vài giờ cho dự án này. Chi phí ban đầu cũng rất nhỏ. Anh mua một tên miền giá 2,99 USD một năm, lập tài khoản bán hàng trên Shopify với giá dùng thử 14 USD. Đắt đỏ nhất là ngân sách quảng cáo trên Facebook với 100 USD mỗi ngày. LDSman.com chính thức hoạt động từ ngày 11/11/2016.\n"
                + "\n"
                + "Ngày đầu tiên, Chapman đã lỗ. Nguyên nhân là anh bán sai sản phẩm. \"Tôi bán các tác phẩm nghệ thuật. Nhưng rồi sau đó, tôi nhận ra chỉ rao bán thôi là không đủ để thu hút người dùng\".\n"
                + "\n"
                + "Vì thế, Chapman nhớ lại bài học trước đây. Khi bán sản phẩm tận nhà, nó đó phải đủ thu hút để mọi người mời anh vào trong. \"Bán online cũng vậy. Để khiến ai đó rời khỏi bảng tin họ đang đọc, bạn phải chào mời thứ gì đó thực sự thú vị\", anh nói.\n"
                + "\n"
                + "Thế là Chapman bỏ bán tác phẩm nghệ thuật và chuyển sang ghế bơm hơi - mặt hàng đang rất được ưa chuộng. Chapman tìm nguồn hàng từ các hãng sản xuất Trung Quốc trên Alibaba và Aliexpress, mua với giá 4,99 USD và bán lại với giá 59,99 USD.  \n"
                + "\n"
                + "Để tránh chi phí và rủi ro của việc trữ hàng, anh thỏa thuận với các nhà cung cấp Trung Quốc) để họ chuyển trực tiếp từ kho tại nước này sang cho khách hàng ở Mỹ. \"Đây là cách tốt nhất để thử nghiệm xem một sản phẩm có thể bán được hay không\", anh nói. Bên cạnh đó, nhờ một thỏa thuận có tên ePacket giữa bưu chính Mỹ và các nước khác nhằm khuyến khích thương mại điện tử, phí chuyển hàng từ Trung Quốc tới Mỹ còn rẻ hơn, dù thời gian chậm hơn một chút.\n"
                + "\n"
                + "Ví dụ, một chiếc ống kính zoom cho iPhone chuyển từ Thượng Hải sang có giá 2,29 USD - rẻ hơn khoảng 5 USD so với phí giao hàng nội địa tại Mỹ.\n"
                + "\n"
                + "\"Thế là ngày thứ 2, tôi kiếm được tiền. Các ngày sau đó cũng vậy\", anh nhớ lại. Chỉ trong 2 tuần, anh kiếm được 10.000 USD đầu tiên. Website của anh còn bán nhiều sản phẩm độc đáo từ Trung Quốc, như kem đánh răng có than hoạt tính hay đồ chơi Spinner.\n"
                + "\n"
                + "kiem-1-trieu-usd-trong-3-thang-nho-ban-hang-online-1\n"
                + "Kho hàng rộng hơn 800 m2 của Chapman. Ảnh: CNBC\n"
                + "Số tiền này cho phép Chapman thuê ngoài nhân viên chăm sóc khách hàng tại Philippines, với 700 USD một người mỗi tháng. Con số này khá thấp so với tiêu chuẩn tại Mỹ, nhưng cao hơn nhiều thu nhập bình quân 400 USD một tháng tại Philippines. Anh cũng tăng ngân sách quảng cáo trên Facebook.\n"
                + "\n"
                + "Gần hai tháng sau, công việc của anh gặp sự cố. Nhà cung cấp ghế bơm hơi tại Trung Quốc lại tráo sản phẩm này bằng loại khác rẻ hơn. Khi khách hàng phàn nàn, anh đã phải đổi 1.500 chiếc. Dù vậy, kinh nghiệm từ việc này đã giúp anh đạt biên lợi nhuận trước thuế tới 48% về sau.\n"
                + "\n"
                + "Cũng như các doanh nhân khác, Chapman rất biết tận dụng cơ hội. Anh mua một nhà kho rộng hơn 800m2 và tuyển 5 nhân viên làm việc toàn thời gian. Việc này cho phép LDSman đưa việc kinh doanh lên một tầm cao mới. Và với việc có nhà kho, có nhân viên, Chapman chỉ mất 1 giờ mỗi tuần để làm việc và cập nhật quảng cáo Facebook. Thế là anh bỏ việc bán pin năng lượng mặt trời.\n"
                + "\n"
                + "Chỉ trong 3 tháng, Chapman đã chuyển từ công việc ngốn 12 giờ mỗi ngày sang chỉ 1 giờ làm việc mỗi tuần. Khi doanh thu công ty chạm mốc 1 triệu USD vào ngày thứ 92 hoạt động, anh rút ngắn thời gian xuống còn nửa giờ.\n"
                + "\n"
                + "Một quỹ đầu tư có tên Clarke Capital sau đó đã tìm đến anh và hỏi mua công ty. Tuy nhiên, Chapman đã từ chối lời chào mua trị giá 3 triệu USD đó, để được tự do làm những gì mình muốn.\n"
                + "\n"
                + "Hiện doanh thu hằng tháng của LDSman vào khoảng 350.000 USD một tháng. Chapman cho biết anh vừa chạm mốc doanh thu 2 triệu USD trong tháng thứ 6 hoạt động. Anh cũng dạy một khoá thương mại điện tử online nữa.\n"
                + "\n"
                + "Chapman gần đây còn cùng anh rể mở một công ty chuyển phát, tận dụng khoảng trống trên các chuyến bay chở hàng hóa để đưa hàng từ châu Á sang Mỹ. Khách hàng mới nhất của họ chính là Amazon. Họ kỳ vọng có thể thu về 10 triệu USD trong năm đầu hoạt động. Và dĩ nhiên, phần lớn nhân viên cũng là thuê ngoài.\n"
                + "\n"
                + "Quá trình kinh doanh đã giúp Chapman nhận ra thương mại điện tử chính là cơ hội tốt nhất cho bất kỳ ai muốn kiểm soát thời gian và kiếm tiền. Kể cả khi đó chỉ là người không có kinh nghiệm như anh.\n"
                + "\n"
                + "\"Hè này, tôi và gia đình sẽ du lịch 3 tháng\", Chapman cho biết. Đây là điều anh luôn muốn, nhưng chưa bao giờ làm được.\n";
        return str;
    }

}
