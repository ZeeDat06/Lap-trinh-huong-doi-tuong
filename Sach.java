import java.io.Serializable;

public class Sach implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String maSach;
    private String ten;
    private String tacGia;
    private String nhaXuatBan;
    private String theLoai;
    private double giaBan;
    private int soLuongTon;
    private boolean dangKinhDoanh;
    
    public Sach(String maSach, String ten, String tacGia, String theLoai, double giaBan, int soLuongTon) {
        this.maSach = maSach;
        this.ten = ten;
        this.tacGia = tacGia;
        this.nhaXuatBan = "";
        this.theLoai = theLoai;
        this.giaBan = giaBan;
        this.soLuongTon = soLuongTon;
        this.dangKinhDoanh = true;
    }
    
    public boolean giamSoLuongTon(int soLuong) {
        if (soLuong <= 0) {
            return false;
        }
        if (soLuongTon >= soLuong) {
            soLuongTon -= soLuong;
            return true;
        }
        return false;
    }
    
    public void tangSoLuongTon(int soLuong) {
        if (soLuong > 0) {
            this.soLuongTon += soLuong;
        }
    }
    
    public boolean sapHetHang() {
        return soLuongTon < 10;
    }
    
    public void ngungKinhDoanh() {
        this.dangKinhDoanh = false;
    }
    
    public String getMaSach() { return maSach; }
    public void setMaSach(String maSach) { this.maSach = maSach; }
    
    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }
    
    public String getTacGia() { return tacGia; }
    public void setTacGia(String tacGia) { this.tacGia = tacGia; }
    
    public String getNhaXuatBan() { return nhaXuatBan; }
    public void setNhaXuatBan(String nhaXuatBan) { this.nhaXuatBan = nhaXuatBan; }
    
    public String getTheLoai() { return theLoai; }
    public void setTheLoai(String theLoai) { this.theLoai = theLoai; }
    
    public double getGiaBan() { return giaBan; }
    public void setGiaBan(double giaBan) { this.giaBan = giaBan; }
    
    public int getSoLuongTon() { return soLuongTon; }
    public void setSoLuongTon(int soLuongTon) { 
        if (soLuongTon >= 0) {
            this.soLuongTon = soLuongTon; 
        }
    }
    
    public boolean isDangKinhDoanh() { return dangKinhDoanh; }
    public void setDangKinhDoanh(boolean dangKinhDoanh) { this.dangKinhDoanh = dangKinhDoanh; }
    
    @Override
    public String toString() {
        return String.format("Ma: %s | Ten: %s | Tac gia: %s | The loai: %s | Gia: %.2f | Ton: %d", 
            maSach, ten, tacGia, theLoai, giaBan, soLuongTon);
    }
}