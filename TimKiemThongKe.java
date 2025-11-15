import java.util.Date;
import java.util.List;

public class TimKiemThongKe {
    private QuanLySach quanLySach;
    private QuanLyHoaDon quanLyHoaDon;

    public TimKiemThongKe(QuanLySach quanLySach, QuanLyHoaDon quanLyHoaDon) {
        this.quanLySach = quanLySach;
        this.quanLyHoaDon = quanLyHoaDon;
    }
    
    public List<Sach> timSachTheoTen(String ten) {
        return quanLySach.timKiemTheoTen(ten);
    }
    
    public List<Sach> timSachTheoTheLoai(String theLoai) {
        return quanLySach.timKiemTheoTheLoai(theLoai);
    }
    
    public double tinhDoanhThuTheoThang(int thang, int nam) {
        List<HoaDon> hoaDonThang = quanLyHoaDon.timKiemTheoThang(thang, nam);
        return hoaDonThang.stream()
                .mapToDouble(HoaDon::getTongTien)
                .sum();
    }
    
    public double tinhDoanhThuTheoKhoangThoiGian(Date tuNgay, Date denNgay) {
        List<HoaDon> hoaDonKhoang = quanLyHoaDon.timKiemTheoKhoangThoiGian(tuNgay, denNgay);
        return hoaDonKhoang.stream()
                .mapToDouble(HoaDon::getTongTien)
                .sum();
    }
}