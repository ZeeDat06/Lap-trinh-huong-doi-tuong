import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class QuanLyHoaDon implements IQuanLy<HoaDon> {
    private List<HoaDon> danhSachHoaDon;
    
    public QuanLyHoaDon() {
        this.danhSachHoaDon = new ArrayList<>();
    }
    
    @Override
    public void them(HoaDon hoaDon) {
        danhSachHoaDon.add(hoaDon);
    }
    
    @Override
    public void sua(HoaDon hoaDon) {
        for (int i = 0; i < danhSachHoaDon.size(); i++) {
            if (danhSachHoaDon.get(i).getMaHoaDon().equals(hoaDon.getMaHoaDon())) {
                danhSachHoaDon.set(i, hoaDon);
                break;
            }
        }
    }
    
    @Override
    public void xoa(String maHoaDon) {
        danhSachHoaDon.removeIf(hd -> hd.getMaHoaDon().equals(maHoaDon));
    }
    
    @Override
    public HoaDon timKiemTheoMa(String maHoaDon) {
        return danhSachHoaDon.stream()
                .filter(hd -> hd.getMaHoaDon().equals(maHoaDon))
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public List<HoaDon> layTatCa() {
        return new ArrayList<>(danhSachHoaDon);
    }
    
    public List<HoaDon> timKiemTheoThang(int thang, int nam) {
        return danhSachHoaDon.stream()
                .filter(hd -> {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(hd.getNgayLap());
                    return cal.get(Calendar.MONTH) + 1 == thang && cal.get(Calendar.YEAR) == nam;
                })
                .collect(Collectors.toList());
    }
    
    public List<HoaDon> timKiemTheoKhoangThoiGian(Date tuNgay, Date denNgay) {
        return danhSachHoaDon.stream()
                .filter(hd -> {
                    Date ngayLap = hd.getNgayLap();
                    return (ngayLap.equals(tuNgay) || ngayLap.after(tuNgay)) && (ngayLap.equals(denNgay) || ngayLap.before(denNgay));
                })
                .collect(Collectors.toList());
    }
}