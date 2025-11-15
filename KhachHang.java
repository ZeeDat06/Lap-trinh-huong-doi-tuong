import java.io.Serializable;

public class KhachHang implements Serializable {
    private String maKhachHang;
    private String hoTen;
    private String dienThoai;
    private String email;
    
    public KhachHang(String maKhachHang, String hoTen, String dienThoai, String email) {
        this.maKhachHang = maKhachHang;
        this.hoTen = hoTen;
        this.dienThoai = dienThoai;
        this.email = email;
    }
    
    public String getMaKhachHang() { return maKhachHang; }
    public void setMaKhachHang(String maKhachHang) { this.maKhachHang = maKhachHang; }
    
    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
    
    public String getDienThoai() { return dienThoai; }
    public void setDienThoai(String dienThoai) { this.dienThoai = dienThoai; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    @Override
    public String toString() {
        return String.format("Ma: %s | Ho ten: %s | Dien thoai: %s | Email: %s", maKhachHang, hoTen, dienThoai, email);
    }
}