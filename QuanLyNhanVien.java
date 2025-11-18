import java.util.ArrayList;
import java.util.List;

public class QuanLyNhanVien implements IQuanLy<NhanVien> {
    private List<NhanVien> danhSach;
    
    public QuanLyNhanVien() {
        this.danhSach = new ArrayList<>();
    }
    
    @Override
    public void them(NhanVien nv) {
        danhSach.add(nv);
    }
    
    @Override
    public void sua(NhanVien nv) {
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).getMaNhanVien().equals(nv.getMaNhanVien())) {
                danhSach.set(i, nv);
                break;
            }
        }
    }
    
    @Override
    public void xoa(String ma) {
        danhSach.removeIf(nv -> nv.getMaNhanVien().equals(ma));
    }
    
    @Override
    public NhanVien timKiemTheoMa(String ma) {
        for (NhanVien nv : danhSach) {
            if (nv.getMaNhanVien().equals(ma)) {
                return nv;
            }
        }
        return null;
    }
    
    @Override
    public List<NhanVien> layTatCa() {
        return new ArrayList<>(danhSach);
    }
    
    public NhanVien timKiemTheoTaiKhoan(String taiKhoan) {
        for (NhanVien nv : danhSach) {
            if (nv.getTaiKhoan().equals(taiKhoan)) {
                return nv;
            }
        }
        return null;
    }
    
    public NhanVien dangNhap(String taiKhoan, String matKhau) {
        NhanVien nv = timKiemTheoTaiKhoan(taiKhoan);
        if (nv != null && nv.getMatKhau().equals(matKhau)) {
            return nv;
        }
        return null;
    }
}
