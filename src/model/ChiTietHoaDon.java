package model;

import java.io.Serializable;

public class ChiTietHoaDon implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Sach sach;
    private int soLuong;
    private double donGia;
    
    public ChiTietHoaDon() {
    }
    
    public ChiTietHoaDon(Sach sach, int soLuong, double donGia) {
        this.sach = sach;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }
    
    public Sach getSach() {
        return sach;
    }
    
    public void setSach(Sach sach) {
        this.sach = sach;
    }
    
    public int getSoLuong() {
        return soLuong;
    }
    
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    public double getDonGia() {
        return donGia;
    }
    
    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
    
    public double tinhThanhTien() {
        return soLuong * donGia;
    }
    
    @Override
    public String toString() {
        return "ChiTietHoaDon{" +
                "sach=" + sach.getTenSach() +
                ", soLuong=" + soLuong +
                ", donGia=" + donGia +
                ", thanhTien=" + tinhThanhTien() +
                '}';
    }
}
