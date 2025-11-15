import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuanLySach implements IQuanLy<Sach> {
    private List<Sach> danhSachSach;
    
    public QuanLySach() {
        this.danhSachSach = new ArrayList<>();
    }
    
    @Override
    public void them(Sach sach) {
        danhSachSach.add(sach);
    }
    
    @Override
    public void sua(Sach sach) {
        for (int i = 0; i < danhSachSach.size(); i++) {
            if (danhSachSach.get(i).getMaSach().equals(sach.getMaSach())) {
                danhSachSach.set(i, sach);
                break;
            }
        }
    }
    
    @Override
    public void xoa(String maSach) {
        danhSachSach.removeIf(sach -> sach.getMaSach().equals(maSach));
    }
    
    @Override
    public Sach timKiemTheoMa(String maSach) {
        return danhSachSach.stream()
                .filter(sach -> sach.getMaSach().equals(maSach))
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public List<Sach> layTatCa() {
        return new ArrayList<>(danhSachSach);
    }
    
    public List<Sach> timKiemTheoTen(String ten) {
        return danhSachSach.stream()
                .filter(sach -> sach.getTen().toLowerCase().contains(ten.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    public List<Sach> timKiemTheoTheLoai(String theLoai) {
        return danhSachSach.stream()
                .filter(sach -> sach.getTheLoai().toLowerCase().contains(theLoai.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    public boolean kiemTraSoLuong(String maSach, int soLuongMua) {
        Sach sach = timKiemTheoMa(maSach);
        return sach != null && sach.getSoLuongTon() >= soLuongMua;
    }
    
    public void capNhatSoLuong(String maSach, int soLuongBan) {
        Sach sach = timKiemTheoMa(maSach);
        if (sach != null) {
            sach.setSoLuongTon(sach.getSoLuongTon() - soLuongBan);
        }
    }
}