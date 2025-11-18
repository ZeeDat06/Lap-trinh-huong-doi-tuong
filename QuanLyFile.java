import java.io.*;
import java.util.List;

/**
 * Lớp quản lý đọc/ghi file - Lưu trữ dữ liệu bền vững
 */
public class QuanLyFile {
    
    public static void ghiDuLieuSach(List<Sach> danhSachSach, String tenFile) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tenFile))) {
            oos.writeObject(danhSachSach);
        } catch (IOException e) {
            System.out.println("Loi khi ghi du lieu sach: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public static List<Sach> docDuLieuSach(String tenFile) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tenFile))) {
            return (List<Sach>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Loi khi doc du lieu sach: " + e.getMessage());
            return null;
        }
    }
    
    public static void ghiDuLieuKhachHang(List<KhachHang> danhSachKhachHang, String tenFile) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tenFile))) {
            oos.writeObject(danhSachKhachHang);
        } catch (IOException e) {
            System.out.println("Loi khi ghi du lieu khach hang: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public static List<KhachHang> docDuLieuKhachHang(String tenFile) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tenFile))) {
            return (List<KhachHang>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Loi khi doc du lieu khach hang: " + e.getMessage());
            return null;
        }
    }
    
    public static void ghiDuLieuHoaDon(List<HoaDon> danhSachHoaDon, String tenFile) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tenFile))) {
            oos.writeObject(danhSachHoaDon);
        } catch (IOException e) {
            System.out.println("Loi khi ghi du lieu hoa don: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public static List<HoaDon> docDuLieuHoaDon(String tenFile) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tenFile))) {
            return (List<HoaDon>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Loi khi doc du lieu hoa don: " + e.getMessage());
            return null;
        }
    }
    
    // Thêm các phương thức cho NhanVien và PhieuNhapKho
    public static void ghiDuLieuNhanVien(List<NhanVien> danhSach, String tenFile) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tenFile))) {
            oos.writeObject(danhSach);
        } catch (IOException e) {
            System.out.println("Loi khi ghi du lieu nhan vien: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public static List<NhanVien> docDuLieuNhanVien(String tenFile) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tenFile))) {
            return (List<NhanVien>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Loi khi doc du lieu nhan vien: " + e.getMessage());
            return null;
        }
    }
    
    public static void ghiDuLieuPhieuNhap(List<PhieuNhapKho> danhSach, String tenFile) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tenFile))) {
            oos.writeObject(danhSach);
        } catch (IOException e) {
            System.out.println("Loi khi ghi du lieu phieu nhap: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public static List<PhieuNhapKho> docDuLieuPhieuNhap(String tenFile) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tenFile))) {
            return (List<PhieuNhapKho>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Loi khi doc du lieu phieu nhap: " + e.getMessage());
            return null;
        }
    }
}