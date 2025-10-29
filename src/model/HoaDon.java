package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HoaDon implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String maHoaDon;
    private LocalDate ngayLap;
    private KhachHang khachHang;
    private List<ChiTietHoaDon> danhSachChiTiet;
    
    public HoaDon() {
        this.danhSachChiTiet = new ArrayList<>();
    }
    
    public HoaDon(String maHoaDon, LocalDate ngayLap, KhachHang khachHang) {
        this.maHoaDon = maHoaDon;
        this.ngayLap = ngayLap;
        this.khachHang = khachHang;
        this.danhSachChiTiet = new ArrayList<>();
    }
    
    public String getMaHoaDon() {
        return maHoaDon;
    }
    
    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }
    
    public LocalDate getNgayLap() {
        return ngayLap;
    }
    
    public void setNgayLap(LocalDate ngayLap) {
        this.ngayLap = ngayLap;
    }
    
    public KhachHang getKhachHang() {
        return khachHang;
    }
    
    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }
    
    public List<ChiTietHoaDon> getDanhSachChiTiet() {
        return danhSachChiTiet;
    }
    
    public void setDanhSachChiTiet(List<ChiTietHoaDon> danhSachChiTiet) {
        this.danhSachChiTiet = danhSachChiTiet;
    }
    
    public void themChiTiet(ChiTietHoaDon chiTiet) {
        this.danhSachChiTiet.add(chiTiet);
    }
    
    public double tinhTongTien() {
        double tongTien = 0;
        for (ChiTietHoaDon ct : danhSachChiTiet) {
            tongTien += ct.tinhThanhTien();
        }
        return tongTien;
    }
    
    @Override
    public String toString() {
        return "HoaDon{" +
                "maHoaDon='" + maHoaDon + '\'' +
                ", ngayLap=" + ngayLap +
                ", khachHang=" + khachHang.getHoTen() +
                ", tongTien=" + tinhTongTien() +
                '}';
    }
}
