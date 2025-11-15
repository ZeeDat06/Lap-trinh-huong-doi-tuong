import java.io.Serializable;

public class ChiTietHoaDon implements Serializable {
    private Sach sach;
    private int soLuong;
    private double donGia;
    private double thanhTien;
    
    public ChiTietHoaDon(Sach sach, int soLuong, double donGia) {
        this.sach = sach;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = soLuong * donGia;
    }
    
    public Sach getSach() { return sach; }
    public void setSach(Sach sach) { this.sach = sach; }
    
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { 
        this.soLuong = soLuong; 
        this.thanhTien = this.soLuong * this.donGia;
    }
    
    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { 
        this.donGia = donGia; 
        this.thanhTien = this.soLuong * this.donGia;
    }
    
    public double getThanhTien() { return thanhTien; }
    
    @Override
    public String toString() {
        return String.format("Sach: %s | So luong: %d | Don gia: %.2f | Thanh tien: %.2f", sach.getTen(), soLuong, donGia, thanhTien);
    }
}