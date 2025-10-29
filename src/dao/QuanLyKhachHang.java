package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.KhachHang;

public class QuanLyKhachHang implements IQuanLy<KhachHang> {
    private List<KhachHang> danhSachKhachHang;
    private static final String FILE_PATH = "data/khachhang.dat";
    
    public QuanLyKhachHang() {
        danhSachKhachHang = new ArrayList<>();
        docDuLieu();
    }
    
    @Override
    public void them(KhachHang khachHang) {
        danhSachKhachHang.add(khachHang);
        luuDuLieu();
    }
    
    @Override
    public void sua(KhachHang khachHang) {
        for (int i = 0; i < danhSachKhachHang.size(); i++) {
            if (danhSachKhachHang.get(i).getMaKhachHang().equals(khachHang.getMaKhachHang())) {
                danhSachKhachHang.set(i, khachHang);
                luuDuLieu();
                return;
            }
        }
    }
    
    @Override
    public void xoa(String maKhachHang) {
        danhSachKhachHang.removeIf(kh -> kh.getMaKhachHang().equals(maKhachHang));
        luuDuLieu();
    }
    
    @Override
    public KhachHang timTheoMa(String maKhachHang) {
        for (KhachHang kh : danhSachKhachHang) {
            if (kh.getMaKhachHang().equals(maKhachHang)) {
                return kh;
            }
        }
        return null;
    }
    
    @Override
    public List<KhachHang> layTatCa() {
        return new ArrayList<>(danhSachKhachHang);
    }
    
    public List<KhachHang> timTheoTen(String hoTen) {
        return danhSachKhachHang.stream()
                .filter(kh -> kh.getHoTen().toLowerCase().contains(hoTen.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    public List<KhachHang> timTheoDienThoai(String dienThoai) {
        return danhSachKhachHang.stream()
                .filter(kh -> kh.getDienThoai().contains(dienThoai))
                .collect(Collectors.toList());
    }
    
    private void luuDuLieu() {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(danhSachKhachHang);
        } catch (IOException e) {
            System.err.println("Loi luu du lieu khach hang: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    private void docDuLieu() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                danhSachKhachHang = (List<KhachHang>) ois.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Loi doc du lieu khach hang: " + e.getMessage());
                    danhSachKhachHang = new ArrayList<>();
                }
            } else {
                danhSachKhachHang = new ArrayList<>();
            }
        }
    }
