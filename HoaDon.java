import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDon implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String maHoaDon;
    private Date ngayLap;
    private KhachHang khachHang;
    private NhanVien nhanVienBan;
    private List<ChiTietHoaDon> danhSachSachMua;
    private double tongTienGoc;
    private double tienGiamGia;
    private double tongTien;
    
    public HoaDon(String maHoaDon, Date ngayLap, KhachHang khachHang) {
        this.maHoaDon = maHoaDon;
        this.ngayLap = ngayLap;
        this.khachHang = khachHang;
        this.danhSachSachMua = new ArrayList<>();
        this.nhanVienBan = null;
    }
    
    public HoaDon(String maHoaDon, Date ngayLap, KhachHang khachHang, NhanVien nhanVien) {
        this.maHoaDon = maHoaDon;
        this.ngayLap = ngayLap;
        this.khachHang = khachHang;
        this.nhanVienBan = nhanVien;
        this.danhSachSachMua = new ArrayList<>();
    }
    
    public double getTongTien() {
        tongTienGoc = 0;
        for (ChiTietHoaDon ct : danhSachSachMua) {
            tongTienGoc += ct.getThanhTien();
        }
        
        tienGiamGia = khachHang.tinhGiamGia(tongTienGoc);
        tongTien = tongTienGoc - tienGiamGia;
        
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
    
    public void hoanThanh() {
        khachHang.capNhatDiemTichLuy(getTongTien());
    }
    
    public String getMaHoaDon() { return maHoaDon; }
    public void setMaHoaDon(String maHoaDon) { this.maHoaDon = maHoaDon; }
    
    public Date getNgayLap() { return ngayLap; }
    public void setNgayLap(Date ngayLap) { this.ngayLap = ngayLap; }
    
    public KhachHang getKhachHang() { return khachHang; }
    public void setKhachHang(KhachHang khachHang) { this.khachHang = khachHang; }
    
    public NhanVien getNhanVienBan() { return nhanVienBan; }
    public void setNhanVienBan(NhanVien nhanVienBan) { this.nhanVienBan = nhanVienBan; }
    
    public List<ChiTietHoaDon> getDanhSachSachMua() { return danhSachSachMua; }
    
    public double getTongTienGoc() { return tongTienGoc; }
    
    public double getTienGiamGia() { return tienGiamGia; }
    
    @Override
    public String toString() {
        return String.format("Ma HD: %s | Ngay: %s | Khach hang: %s | Tong tien: %.2f", 
            maHoaDon, ngayLap, khachHang.getHoTen(), getTongTien());
    }
}