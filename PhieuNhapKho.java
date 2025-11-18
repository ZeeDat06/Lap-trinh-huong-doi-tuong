import java.io.Serializable;
import java.util.Date;

public class PhieuNhapKho implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String maPhieu;
    private Date ngayNhap;
    private NhanVien nguoiNhap;
    private String nhaCungCap;
    private Sach sach;
    private int soLuongNhap;
    private double giaNhap;
    private double tongTien;
    
    public PhieuNhapKho() {
    }
    
    public PhieuNhapKho(String maPhieu, Date ngayNhap, NhanVien nguoiNhap, 
                        String nhaCungCap, Sach sach, int soLuongNhap, double giaNhap) {
        this.maPhieu = maPhieu;
        this.ngayNhap = ngayNhap;
        this.nguoiNhap = nguoiNhap;
        this.nhaCungCap = nhaCungCap;
        this.sach = sach;
        this.soLuongNhap = soLuongNhap;
        this.giaNhap = giaNhap;
        this.tongTien = soLuongNhap * giaNhap;
    }
    
    public void nhapKho() {
        sach.tangSoLuongTon(soLuongNhap);
    }
    
    public String getMaPhieu() {
        return maPhieu;
    }
    
    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }
    
    public Date getNgayNhap() {
        return ngayNhap;
    }
    
    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
    
    public NhanVien getNguoiNhap() {
        return nguoiNhap;
    }
    
    public void setNguoiNhap(NhanVien nguoiNhap) {
        this.nguoiNhap = nguoiNhap;
    }
    
    public String getNhaCungCap() {
        return nhaCungCap;
    }
    
    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }
    
    public Sach getSach() {
        return sach;
    }
    
    public void setSach(Sach sach) {
        this.sach = sach;
    }
    
    public int getSoLuongNhap() {
        return soLuongNhap;
    }
    
    public void setSoLuongNhap(int soLuongNhap) {
        this.soLuongNhap = soLuongNhap;
        this.tongTien = soLuongNhap * giaNhap;
    }
    
    public double getGiaNhap() {
        return giaNhap;
    }
    
    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
        this.tongTien = soLuongNhap * giaNhap;
    }
    
    public double getTongTien() {
        return tongTien;
    }
}
