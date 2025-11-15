import java.io.Serializable;

public class Sach implements Serializable {
    private String maSach;
    private String ten;
    private String tacGia;
    private String theLoai;
    private double giaBan;
    private int soLuongTon;
    
    public Sach(String maSach, String ten, String tacGia, String theLoai, double giaBan, int soLuongTon) {
        this.maSach = maSach;
        this.ten = ten;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
        this.giaBan = giaBan;
        this.soLuongTon = soLuongTon;
    }
    
    public String getMaSach() { return maSach; }
    public void setMaSach(String maSach) { this.maSach = maSach; }
    
    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }
    
    public String getTacGia() { return tacGia; }
    public void setTacGia(String tacGia) { this.tacGia = tacGia; }
    
    public String getTheLoai() { return theLoai; }
    public void setTheLoai(String theLoai) { this.theLoai = theLoai; }
    
    public double getGiaBan() { return giaBan; }
    public void setGiaBan(double giaBan) { this.giaBan = giaBan; }
    
    public int getSoLuongTon() { return soLuongTon; }
    public void setSoLuongTon(int soLuongTon) { this.soLuongTon = soLuongTon; }
    
    @Override
    public String toString() {
        return String.format("Ma: %s | Ten: %s | Tac gia: %s | The loai: %s | Gia: %.2f | Ton: %d", maSach, ten, tacGia, theLoai, giaBan, soLuongTon);
    }
}