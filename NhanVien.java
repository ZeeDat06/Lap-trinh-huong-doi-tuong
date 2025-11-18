import java.io.Serializable;

public class NhanVien extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String maNhanVien;
    private String chucVu;
    private double luong;
    private String taiKhoan;
    private String matKhau;
    
    public NhanVien() {
        super();
    }
    
    public NhanVien(String maNhanVien, String hoTen, String soDienThoai, String diaChi, 
                    String email, String chucVu, double luong, String taiKhoan, String matKhau) {
        super(maNhanVien, hoTen, soDienThoai, diaChi, email);
        this.maNhanVien = maNhanVien;
        this.chucVu = chucVu;
        this.luong = luong;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
    }
    
    @Override
    public String getLoaiNguoiDung() {
        return "Nhân viên";
    }
    
    public boolean coQuyenQuanLy() {
        return "Quản lý".equalsIgnoreCase(chucVu);
    }
    
    public String getMaNhanVien() {
        return maNhanVien;
    }
    
    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }
    
    public String getChucVu() {
        return chucVu;
    }
    
    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
    
    public double getLuong() {
        return luong;
    }
    
    public void setLuong(double luong) {
        this.luong = luong;
    }
    
    public String getTaiKhoan() {
        return taiKhoan;
    }
    
    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }
    
    public String getMatKhau() {
        return matKhau;
    }
    
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
