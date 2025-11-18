import java.util.ArrayList;
import java.util.List;

public class KhoiTaoDuLieu {
    public static void main(String[] args) {
        taoNhanVienMacDinh();
        System.out.println("Đã tạo dữ liệu mẫu thành công!");
    }
    
    private static void taoNhanVienMacDinh() {
        List<NhanVien> danhSachNV = new ArrayList<>();
        
        NhanVien admin = new NhanVien(
            "NV001", 
            "Quản Trị Viên", 
            "0123456789", 
            "Hà Nội", 
            "admin@bookstore.com",
            "Quản lý",
            15000000,
            "admin",
            "admin123"
        );
        
        NhanVien nv1 = new NhanVien(
            "NV002",
            "Nguyễn Văn A",
            "0987654321",
            "Hà Nội",
            "nguyenvana@bookstore.com",
            "Nhân viên bán hàng",
            8000000,
            "nvana",
            "123456"
        );
        
        danhSachNV.add(admin);
        danhSachNV.add(nv1);
        
        QuanLyFile.ghiDuLieuNhanVien(danhSachNV, "nhanvien.dat");
    }
}
