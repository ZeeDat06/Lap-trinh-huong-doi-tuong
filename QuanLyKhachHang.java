import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuanLyKhachHang implements IQuanLy<KhachHang> {
    private List<KhachHang> danhSachKhachHang;
    
    public QuanLyKhachHang() {
        this.danhSachKhachHang = new ArrayList<>();
    }
    
    @Override
    public void them(KhachHang khachHang) {
        danhSachKhachHang.add(khachHang);
    }
    
    @Override
    public void sua(KhachHang khachHang) {
        for (int i = 0; i < danhSachKhachHang.size(); i++) {
            if (danhSachKhachHang.get(i).getMaKhachHang().equals(khachHang.getMaKhachHang())) {
                danhSachKhachHang.set(i, khachHang);
                break;
            }
        }
    }
    
    @Override
    public void xoa(String maKhachHang) {
        danhSachKhachHang.removeIf(kh -> kh.getMaKhachHang().equals(maKhachHang));
    }
    
    @Override
    public KhachHang timKiemTheoMa(String maKhachHang) {
        return danhSachKhachHang.stream()
                .filter(kh -> kh.getMaKhachHang().equals(maKhachHang))
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public List<KhachHang> layTatCa() {
        return new ArrayList<>(danhSachKhachHang);
    }
    
    public List<KhachHang> timKiemTheoTen(String ten) {
        return danhSachKhachHang.stream()
                .filter(kh -> kh.getHoTen().toLowerCase().contains(ten.toLowerCase()))
                .collect(Collectors.toList());
    }
}