package dao;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.HoaDon;

public class QuanLyHoaDon implements IQuanLy<HoaDon> {
    private List<HoaDon> danhSachHoaDon;
    private static final String FILE_PATH = "data/hoadon.dat";
    
    public QuanLyHoaDon() {
        danhSachHoaDon = new ArrayList<>();
        docDuLieu();
    }
    
    @Override
    public void them(HoaDon hoaDon) {
        danhSachHoaDon.add(hoaDon);
        luuDuLieu();
    }
    
    @Override
    public void sua(HoaDon hoaDon) {
        for (int i = 0; i < danhSachHoaDon.size(); i++) {
            if (danhSachHoaDon.get(i).getMaHoaDon().equals(hoaDon.getMaHoaDon())) {
                danhSachHoaDon.set(i, hoaDon);
                luuDuLieu();
                return;
            }
        }
    }
    
    @Override
    public void xoa(String maHoaDon) {
        danhSachHoaDon.removeIf(hd -> hd.getMaHoaDon().equals(maHoaDon));
        luuDuLieu();
    }
    
    @Override
    public HoaDon timTheoMa(String maHoaDon) {
        for (HoaDon hd : danhSachHoaDon) {
            if (hd.getMaHoaDon().equals(maHoaDon)) {
                return hd;
            }
        }
        return null;
    }
    
    @Override
    public List<HoaDon> layTatCa() {
        return new ArrayList<>(danhSachHoaDon);
    }
    
    public double tinhDoanhThuTheoThang(int thang, int nam) {
        return danhSachHoaDon.stream()
                .filter(hd -> hd.getNgayLap().getMonthValue() == thang && 
                             hd.getNgayLap().getYear() == nam)
                .mapToDouble(HoaDon::tinhTongTien)
                .sum();
    }
    
    public double tinhDoanhThuTheoKhoangThoiGian(LocalDate tuNgay, LocalDate denNgay) {
        return danhSachHoaDon.stream()
                .filter(hd -> !hd.getNgayLap().isBefore(tuNgay) && 
                             !hd.getNgayLap().isAfter(denNgay))
                .mapToDouble(HoaDon::tinhTongTien)
                .sum();
    }
    
    public List<HoaDon> layHoaDonTheoKhachHang(String maKhachHang) {
        return danhSachHoaDon.stream()
                .filter(hd -> hd.getKhachHang().getMaKhachHang().equals(maKhachHang))
                .collect(Collectors.toList());
    }
    
    private void luuDuLieu() {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();
            
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(danhSachHoaDon);
            }
        } catch (IOException e) {
            System.err.println("Loi luu du lieu hoa don: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    private void docDuLieu() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    danhSachHoaDon = (List<HoaDon>) ois.readObject();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Loi doc du lieu hoa don: " + e.getMessage());
            danhSachHoaDon = new ArrayList<>();
        }
    }
}
