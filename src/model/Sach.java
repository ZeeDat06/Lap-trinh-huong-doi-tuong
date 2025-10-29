package model;

import java.io.Serializable;

public class Sach implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String maSach;
    private String tenSach;
    private String tacGia;
    private String theLoai;
    private double giaBan;
    private int soLuongTon;
    
    public Sach() {
    }
    
    public Sach(String maSach, String tenSach, String tacGia, String theLoai, double giaBan, int soLuongTon) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
        this.giaBan = giaBan;
        this.soLuongTon = soLuongTon;
    }
    
    // Getters and Setters
    public String getMaSach() {
        return maSach;
    }
    
    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }
    
    public String getTenSach() {
        return tenSach;
    }
    
    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }
    
    public String getTacGia() {
        return tacGia;
    }
    
    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }
    
    public String getTheLoai() {
        return theLoai;
    }
    
    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }
    
    public double getGiaBan() {
        return giaBan;
    }
    
    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }
    
    public int getSoLuongTon() {
        return soLuongTon;
    }
    
    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }
    
    @Override
    public String toString() {
        return "Sach{" +
                "maSach='" + maSach + '\'' +
                ", tenSach='" + tenSach + '\'' +
                ", tacGia='" + tacGia + '\'' +
                ", theLoai='" + theLoai + '\'' +
                ", giaBan=" + giaBan +
                ", soLuongTon=" + soLuongTon +
                '}';
    }
}
