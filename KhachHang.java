import java.io.Serializable;

/**
 * Lớp KhachHang kế thừa từ Person
 * Thể hiện tính Kế thừa (Inheritance) và Đa hình (Polymorphism)
 */
public class KhachHang extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String maKhachHang;
    private int diemTichLuy;
    private double tongChiTieu;
    
    public KhachHang() {
        super();
    }
    
    public KhachHang(String maKhachHang, String hoTen, String dienThoai, String email) {
        super(maKhachHang, hoTen, dienThoai, "", email);
        this.maKhachHang = maKhachHang;
        this.diemTichLuy = 0;
        this.tongChiTieu = 0;
    }
    
    @Override
    public String getLoaiNguoiDung() {
        return isVIP() ? "Khách hàng VIP" : "Khách hàng";
    }
    
    // Phương thức đa hình - có thể bị ghi đè trong lớp con
    public double tinhGiamGia(double tongTien) {
        // Khách hàng thường: giảm giá theo điểm tích lũy
        if (isVIP()) {
            return tongTien * 0.10; // VIP giảm 10%
        } else if (diemTichLuy >= 50) {
            return tongTien * 0.05; // Có 50 điểm trở lên giảm 5%
        }
        return 0;
    }
    
    // Kiểm tra có phải VIP không (chi tiêu >= 10 triệu)
    public boolean isVIP() {
        return tongChiTieu >= 10000000;
    }
    
    // Cập nhật điểm tích lũy khi mua hàng
    public void capNhatDiemTichLuy(double soTienMua) {
        this.diemTichLuy += (int)(soTienMua / 100000); // 100k = 1 điểm
        this.tongChiTieu += soTienMua;
    }
    
    // Getters and Setters
    public String getMaKhachHang() {
        return maKhachHang;
    }
    
    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }
    
    public String getDienThoai() {
        return getSoDienThoai();
    }
    
    public void setDienThoai(String dienThoai) {
        setSoDienThoai(dienThoai);
    }
    
    public int getDiemTichLuy() {
        return diemTichLuy;
    }
    
    public void setDiemTichLuy(int diemTichLuy) {
        this.diemTichLuy = diemTichLuy;
    }
    
    public double getTongChiTieu() {
        return tongChiTieu;
    }
    
    public void setTongChiTieu(double tongChiTieu) {
        this.tongChiTieu = tongChiTieu;
    }
    
    @Override
    public String toString() {
        return String.format("Ma: %s | Ho ten: %s | Dien thoai: %s | Email: %s | Diem: %d | Tong chi tieu: %.0f", 
            maKhachHang, getHoTen(), getDienThoai(), getEmail(), diemTichLuy, tongChiTieu);
    }
}