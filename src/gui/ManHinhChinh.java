package gui;

import dao.*;
import javax.swing.*;

public class ManHinhChinh extends JFrame {
    private QuanLySach quanLySach;
    private QuanLyKhachHang quanLyKhachHang;
    private QuanLyHoaDon quanLyHoaDon;
    
    private JTabbedPane tabbedPane;
    
    public ManHinhChinh() {
        quanLySach = new QuanLySach();
        quanLyKhachHang = new QuanLyKhachHang();
        quanLyHoaDon = new QuanLyHoaDon();
        
        setTitle("Quan Ly Cua Hang Sach");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        taoMenuBar();
        
        tabbedPane = new JTabbedPane();
        
        tabbedPane.addTab("Quan Ly Sach", new PanelQuanLySach(quanLySach));
        tabbedPane.addTab("Quan Ly Khach Hang", new PanelQuanLyKhachHang(quanLyKhachHang));
        tabbedPane.addTab("Quan Ly Hoa Don", new PanelQuanLyHoaDon(quanLyHoaDon, quanLySach, quanLyKhachHang));
        tabbedPane.addTab("Tim Kiem & Thong Ke", new PanelTimKiemThongKe(quanLySach, quanLyHoaDon));
        
        add(tabbedPane);
        
        setVisible(true);
    }
    
    private void taoMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu menuFile = new JMenu("File");
        JMenuItem itemThoat = new JMenuItem("Thoat");
        itemThoat.addActionListener(e -> System.exit(0));
        menuFile.add(itemThoat);
        
        JMenu menuGioiThieu = new JMenu("Gioi Thieu");
        JMenuItem itemThongTin = new JMenuItem("Thong Tin");
        itemThongTin.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "Phan Mem Quan Ly Cua Hang Sach\nPhien ban 1.0\nLap trinh huong doi tuong",
                    "Thong Tin",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        menuGioiThieu.add(itemThongTin);
        
        menuBar.add(menuFile);
        menuBar.add(menuGioiThieu);
        
        setJMenuBar(menuBar);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new ManHinhChinh();
        });
    }
}
