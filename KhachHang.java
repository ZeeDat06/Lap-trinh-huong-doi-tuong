import java.io.Serializable;

public class KhachHang extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String maKhachHang;
    private String tenDangNhap;
    private String matKhau;
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
    
    public KhachHang(String maKhachHang, String tenDangNhap, String matKhau, String hoTen, String dienThoai, String email) {
        super(maKhachHang, hoTen, dienThoai, "", email);
        this.maKhachHang = maKhachHang;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.diemTichLuy = 0;
        this.tongChiTieu = 0;
    }
    
    @Override
    public String getLoaiNguoiDung() {
        return isVIP() ? "Khách hàng VIP" : "Khách hàng";
    }
    
    public double tinhGiamGia(double tongTien) {
        if (isVIP()) {
            return tongTien * 0.10;
        } else if (diemTichLuy >= 50) {
            return tongTien * 0.05;
        }
        return 0;
    }
    
    public boolean isVIP() {
        return tongChiTieu >= 10000000;
    }
    
    public void capNhatDiemTichLuy(double soTienMua) {
        this.diemTichLuy += (int)(soTienMua / 100000);
        this.tongChiTieu += soTienMua;
    }
    
    public String getMaKhachHang() {
        return maKhachHang;
    }
    
    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }
    
    public String getTenDangNhap() {
        return tenDangNhap;
    }
    
    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }
    
    public String getMatKhau() {
        return matKhau;
    }
    
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
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