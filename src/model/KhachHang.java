package model;

import java.io.Serializable;

public class KhachHang implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String maKhachHang;
    private String hoTen;
    private String dienThoai;
    private String email;
    
    public KhachHang() {
    }
    
    public KhachHang(String maKhachHang, String hoTen, String dienThoai, String email) {
        this.maKhachHang = maKhachHang;
        this.hoTen = hoTen;
        this.dienThoai = dienThoai;
        this.email = email;
    }
    
    // Getters and Setters
    public String getMaKhachHang() {
        return maKhachHang;
    }
    
    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }
    
    public String getHoTen() {
        return hoTen;
    }
    
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    
    public String getDienThoai() {
        return dienThoai;
    }
    
    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "KhachHang{" +
                "maKhachHang='" + maKhachHang + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", dienThoai='" + dienThoai + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
