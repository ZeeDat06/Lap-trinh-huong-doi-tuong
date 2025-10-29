package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Sach;

public class QuanLySach implements IQuanLy<Sach> {
    private List<Sach> danhSachSach;
    private static final String FILE_PATH = "data/sach.dat";
    
    public QuanLySach() {
        danhSachSach = new ArrayList<>();
        docDuLieu();
    }
    
    @Override
    public void them(Sach sach) {
        danhSachSach.add(sach);
        luuDuLieu();
    }
    
    @Override
    public void sua(Sach sach) {
        for (int i = 0; i < danhSachSach.size(); i++) {
            if (danhSachSach.get(i).getMaSach().equals(sach.getMaSach())) {
                danhSachSach.set(i, sach);
                luuDuLieu();
                return;
            }
        }
    }
    
    @Override
    public void xoa(String maSach) {
        danhSachSach.removeIf(s -> s.getMaSach().equals(maSach));
        luuDuLieu();
    }
    
    @Override
    public Sach timTheoMa(String maSach) {
        for (Sach s : danhSachSach) {
            if (s.getMaSach().equals(maSach)) {
                return s;
            }
        }
        return null;
    }
    
    @Override
    public List<Sach> layTatCa() {
        return new ArrayList<>(danhSachSach);
    }
    
    public List<Sach> timTheoTen(String tenSach) {
        return danhSachSach.stream()
                .filter(s -> s.getTenSach().toLowerCase().contains(tenSach.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    public List<Sach> timTheoTheLoai(String theLoai) {
        return danhSachSach.stream()
                .filter(s -> s.getTheLoai().equalsIgnoreCase(theLoai))
                .collect(Collectors.toList());
    }
    
    public void capNhatSoLuong(String maSach, int soLuongMua) {
        Sach sach = timTheoMa(maSach);
        if (sach != null) {
            sach.setSoLuongTon(sach.getSoLuongTon() - soLuongMua);
            luuDuLieu();
        }
    }
    
    // Lưu dữ liệu ra file Binary
    private void luuDuLieu() {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();
            
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(danhSachSach);
            oos.close();
        } catch (IOException e) {
            System.err.println("Loi luu du lieu sach: " + e.getMessage());
        }
    }
    
    // Đọc dữ liệu từ file Binary
    @SuppressWarnings("unchecked")
    private void docDuLieu() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                danhSachSach = (List<Sach>) ois.readObject();
                ois.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Loi doc du lieu sach: " + e.getMessage());
            danhSachSach = new ArrayList<>();
        }
    }
}
