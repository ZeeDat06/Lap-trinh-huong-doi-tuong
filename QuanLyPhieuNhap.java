import java.util.ArrayList;
import java.util.List;

/**
 * Lớp Quản lý Phiếu nhập kho
 */
public class QuanLyPhieuNhap implements IQuanLy<PhieuNhapKho> {
    private List<PhieuNhapKho> danhSach;
    
    public QuanLyPhieuNhap() {
        this.danhSach = new ArrayList<>();
    }
    
    @Override
    public void them(PhieuNhapKho phieu) {
        danhSach.add(phieu);
        // Tự động cập nhật kho
        phieu.nhapKho();
    }
    
    @Override
    public void sua(PhieuNhapKho phieu) {
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).getMaPhieu().equals(phieu.getMaPhieu())) {
                danhSach.set(i, phieu);
                break;
            }
        }
    }
    
    @Override
    public void xoa(String ma) {
        danhSach.removeIf(p -> p.getMaPhieu().equals(ma));
    }
    
    @Override
    public PhieuNhapKho timKiemTheoMa(String ma) {
        for (PhieuNhapKho p : danhSach) {
            if (p.getMaPhieu().equals(ma)) {
                return p;
            }
        }
        return null;
    }
    
    @Override
    public List<PhieuNhapKho> layTatCa() {
        return new ArrayList<>(danhSach);
    }
}
