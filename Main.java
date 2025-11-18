import javax.swing.SwingUtilities;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                QuanLySach quanLySach = new QuanLySach();
                QuanLyKhachHang quanLyKhachHang = new QuanLyKhachHang();
                QuanLyHoaDon quanLyHoaDon = new QuanLyHoaDon();
                QuanLyNhanVien quanLyNhanVien = new QuanLyNhanVien();
                
                List<Sach> sachList = QuanLyFile.docDuLieuSach("sach.dat");
                if (sachList != null) {
                    for (Sach sach : sachList) {
                        quanLySach.them(sach);
                    }
                }
                
                List<KhachHang> khList = QuanLyFile.docDuLieuKhachHang("khachhang.dat");
                if (khList != null) {
                    for (KhachHang kh : khList) {
                        quanLyKhachHang.them(kh);
                    }
                }
                
                List<HoaDon> hdList = QuanLyFile.docDuLieuHoaDon("hoadon.dat");
                if (hdList != null) {
                    for (HoaDon hd : hdList) {
                        quanLyHoaDon.them(hd);
                    }
                }
                
                List<NhanVien> nvList = QuanLyFile.docDuLieuNhanVien("nhanvien.dat");
                if (nvList != null) {
                    for (NhanVien nv : nvList) {
                        quanLyNhanVien.them(nv);
                    }
                }
                
                LoginDialog loginDialog = new LoginDialog(null, quanLyNhanVien, quanLyKhachHang);
                loginDialog.setVisible(true);
                
                if (loginDialog.isLoginSuccessful()) {
                    if (loginDialog.getUserRole().equals("ADMIN")) {
                        new GiaoDien();
                    } else {
                        new KhachHangGiaoDien(quanLySach, quanLyKhachHang, quanLyHoaDon, 
                                              loginDialog.getMaKhachHang());
                    }
                }
            }
        });
    }
}