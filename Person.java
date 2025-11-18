import java.io.Serializable;

public abstract class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String maNguoiDung;
    private String hoTen;
    private String soDienThoai;
    private String diaChi;
    private String email;
    
    public Person() {
    }
    
    public Person(String maNguoiDung, String hoTen, String soDienThoai, String diaChi, String email) {
        this.maNguoiDung = maNguoiDung;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.email = email;
    }
    
    public abstract String getLoaiNguoiDung();
    
    public String getMaNguoiDung() {
        return maNguoiDung;
    }
    
    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }
    
    public String getHoTen() {
        return hoTen;
    }
    
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    
    public String getSoDienThoai() {
        return soDienThoai;
    }
    
    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
    
    public String getDiaChi() {
        return diaChi;
    }
    
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String hienThiThongTin() {
        return String.format("Mã: %s | Họ tên: %s | SĐT: %s | Địa chỉ: %s | Email: %s", 
            maNguoiDung, hoTen, soDienThoai, diaChi, email);
    }
}
