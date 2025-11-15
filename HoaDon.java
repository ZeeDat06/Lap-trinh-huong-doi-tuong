import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDon implements Serializable {
    private String maHoaDon;
    private Date ngayLap;
    private KhachHang khachHang;
    private List<ChiTietHoaDon> danhSachSachMua;
    private double tongTien;
    
    public HoaDon(String maHoaDon, Date ngayLap, KhachHang khachHang) {
        this.maHoaDon = maHoaDon;
        this.ngayLap = ngayLap;
        this.khachHang = khachHang;
        this.danhSachSachMua = new ArrayList<>();
        this.tongTien = 0;
    }
    
    public String getMaHoaDon() { return maHoaDon; }
    public void setMaHoaDon(String maHoaDon) { this.maHoaDon = maHoaDon; }
    
    public Date getNgayLap() { return ngayLap; }
    public void setNgayLap(Date ngayLap) { this.ngayLap = ngayLap; }
    
    public KhachHang getKhachHang() { return khachHang; }
    public void setKhachHang(KhachHang khachHang) { this.khachHang = khachHang; }
    
    public List<ChiTietHoaDon> getDanhSachSachMua() { return danhSachSachMua; }
    
    public double getTongTien() { 
        tongTien = 0;
        for (ChiTietHoaDon ct : danhSachSachMua) {
            tongTien += ct.getThanhTien();
        }
        return tongTien; 
    }
    
    public void themChiTiet(ChiTietHoaDon chiTiet) {
        danhSachSachMua.add(chiTiet);
    }
    
    public void xoaChiTiet(int index) {
        if (index >= 0 && index < danhSachSachMua.size()) {
            danhSachSachMua.remove(index);
        }
    }
    
    @Override
    public String toString() {
        return String.format("Ma HD: %s | Ngay: %s | Khach hang: %s | Tong tien: %.2f", maHoaDon, ngayLap, khachHang.getHoTen(), getTongTien());
    }
}