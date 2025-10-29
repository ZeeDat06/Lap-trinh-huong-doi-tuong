package gui;

import dao.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.*;

public class PanelTimKiemThongKe extends JPanel {
    private QuanLySach quanLySach;
    private QuanLyHoaDon quanLyHoaDon;
    
    private JTextField txtTimKiemSach, txtThang, txtNam;
    private JComboBox<String> cboTheLoai, cboSapXep;
    private JButton btnTimTheoTen, btnTimTheoTheLoai, btnThongKeThang, btnThongKeKhoang;
    
    private JTable tableSach;
    private DefaultTableModel tableModelSach;
    private JTextArea txtKetQuaThongKe;
    
    public PanelTimKiemThongKe(QuanLySach quanLySach, QuanLyHoaDon quanLyHoaDon) {
        this.quanLySach = quanLySach;
        this.quanLyHoaDon = quanLyHoaDon;
        
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        add(taoPanelTimKiem(), BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        centerPanel.add(taoPanelKetQuaTimKiem());
        centerPanel.add(taoPanelThongKe());
        
        add(centerPanel, BorderLayout.CENTER);
    }
    
    private JPanel taoPanelTimKiem() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Tim Kiem Sach"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Tìm theo tên
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Tim Theo Ten:"), gbc);
        gbc.gridx = 1;
        txtTimKiemSach = new JTextField(20);
        panel.add(txtTimKiemSach, gbc);
        gbc.gridx = 2;
        btnTimTheoTen = new JButton("Tim Kiem");
        btnTimTheoTen.addActionListener(e -> timKiemTheoTen());
        panel.add(btnTimTheoTen, gbc);
        
        // Tìm theo thể loại
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Tim Theo The Loai:"), gbc);
        gbc.gridx = 1;
        cboTheLoai = new JComboBox<>();
        capNhatDanhSachTheLoai();
        panel.add(cboTheLoai, gbc);
        gbc.gridx = 2;
        btnTimTheoTheLoai = new JButton("Tim Kiem");
        btnTimTheoTheLoai.addActionListener(e -> timKiemTheoTheLoai());
        panel.add(btnTimTheoTheLoai, gbc);
        
        return panel;
    }
    
    private JPanel taoPanelKetQuaTimKiem() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Ket Qua Tim Kiem"));
        
        String[] columnNames = {"Ma Sach", "Ten Sach", "Tac Gia", "The Loai", "Gia Ban", "So Luong Ton"};
        tableModelSach = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 4) return Double.class; // Gia Ban
                if (columnIndex == 5) return Integer.class; // So Luong Ton
                return String.class;
            }
        };
        
        tableSach = new JTable(tableModelSach);
        
        // Format cột giá bán
        DefaultTableCellRenderer priceRenderer = new DefaultTableCellRenderer() {
            private DecimalFormat formatter = new DecimalFormat("#,###.00");
            
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof Double) {
                    value = formatter.format(value) + " VND";
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        priceRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tableSach.getColumnModel().getColumn(4).setCellRenderer(priceRenderer);
        
        JScrollPane scrollPane = new JScrollPane(tableSach);
        
        // Panel sắp xếp
        JPanel sortPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sortPanel.add(new JLabel("Sap Xep:"));
        cboSapXep = new JComboBox<>(new String[]{"Theo Ma Sach", "Theo Ten Sach", "Theo Gia Ban"});
        cboSapXep.addActionListener(e -> sapXepKetQua());
        sortPanel.add(cboSapXep);
        
        panel.add(sortPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel taoPanelThongKe() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Thong Ke Doanh Thu"));
        
        // Panel nhập liệu thống kê
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        inputPanel.add(new JLabel("Thang:"));
        txtThang = new JTextField(5);
        inputPanel.add(txtThang);
        
        inputPanel.add(new JLabel("Nam:"));
        txtNam = new JTextField(5);
        txtNam.setText(String.valueOf(LocalDate.now().getYear()));
        inputPanel.add(txtNam);
        
        btnThongKeThang = new JButton("Thong Ke Theo Thang");
        btnThongKeThang.addActionListener(e -> thongKeTheoThang());
        inputPanel.add(btnThongKeThang);
        
        btnThongKeKhoang = new JButton("Thong Ke Tuy Chinh");
        btnThongKeKhoang.addActionListener(e -> thongKeKhoangThoiGian());
        inputPanel.add(btnThongKeKhoang);
        
        panel.add(inputPanel, BorderLayout.NORTH);
        
        // Kết quả thống kê
        txtKetQuaThongKe = new JTextArea(5, 40);
        txtKetQuaThongKe.setEditable(false);
        txtKetQuaThongKe.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtKetQuaThongKe);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void capNhatDanhSachTheLoai() {
        cboTheLoai.removeAllItems();
        cboTheLoai.addItem("-- Chon The Loai --");
        
        List<Sach> danhSach = quanLySach.layTatCa();
        java.util.Set<String> theLoaiSet = new java.util.HashSet<>();
        
        for (Sach s : danhSach) {
            if (s.getTheLoai() != null && !s.getTheLoai().trim().isEmpty()) {
                theLoaiSet.add(s.getTheLoai());
            }
        }
        
        for (String theLoai : theLoaiSet) {
            cboTheLoai.addItem(theLoai);
        }
    }
    
    private void timKiemTheoTen() {
        String tuKhoa = txtTimKiemSach.getText().trim();
        if (tuKhoa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long nhap tu khoa tim kiem!");
            return;
        }
        
        tableModelSach.setRowCount(0);
        List<Sach> ketQua = quanLySach.timTheoTen(tuKhoa);
        
        if (ketQua.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Khong tim thay sach nao!");
        } else {
            for (Sach s : ketQua) {
                Object[] row = {
                    s.getMaSach(),
                    s.getTenSach(),
                    s.getTacGia(),
                    s.getTheLoai(),
                    s.getGiaBan(),
                    s.getSoLuongTon()
                };
                tableModelSach.addRow(row);
            }
        }
    }
    
    private void timKiemTheoTheLoai() {
        if (cboTheLoai.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Vui long chon the loai!");
            return;
        }
        
        String theLoai = (String) cboTheLoai.getSelectedItem();
        tableModelSach.setRowCount(0);
        List<Sach> ketQua = quanLySach.timTheoTheLoai(theLoai);
        
        if (ketQua.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Khong tim thay sach nao!");
        } else {
            for (Sach s : ketQua) {
                Object[] row = {
                    s.getMaSach(),
                    s.getTenSach(),
                    s.getTacGia(),
                    s.getTheLoai(),
                    s.getGiaBan(),
                    s.getSoLuongTon()
                };
                tableModelSach.addRow(row);
            }
        }
    }
    
    private void sapXepKetQua() {
        // Lấy dữ liệu hiện tại từ bảng
        List<Sach> danhSach = new java.util.ArrayList<>();
        for (int i = 0; i < tableModelSach.getRowCount(); i++) {
            String maSach = tableModelSach.getValueAt(i, 0).toString();
            Sach sach = quanLySach.timTheoMa(maSach);
            if (sach != null) {
                danhSach.add(sach);
            }
        }
        
        if (danhSach.isEmpty()) {
            return;
        }
        
        int selectedIndex = cboSapXep.getSelectedIndex();
        
        switch (selectedIndex) {
            case 0: // Theo Ma Sach
                Collections.sort(danhSach, new Comparator<Sach>() {
                    @Override
                    public int compare(Sach s1, Sach s2) {
                        return s1.getMaSach().compareTo(s2.getMaSach());
                    }
                });
                break;
                
            case 1: // Theo Ten Sach
                Collections.sort(danhSach, new Comparator<Sach>() {
                    @Override
                    public int compare(Sach s1, Sach s2) {
                        return s1.getTenSach().compareToIgnoreCase(s2.getTenSach());
                    }
                });
                break;
                
            case 2: // Theo Gia Ban
                Collections.sort(danhSach, new Comparator<Sach>() {
                    @Override
                    public int compare(Sach s1, Sach s2) {
                        return Double.compare(s1.getGiaBan(), s2.getGiaBan());
                    }
                });
                break;
        }
        
        // Cập nhật lại bảng
        tableModelSach.setRowCount(0);
        for (Sach s : danhSach) {
            Object[] row = {
                s.getMaSach(),
                s.getTenSach(),
                s.getTacGia(),
                s.getTheLoai(),
                s.getGiaBan(),
                s.getSoLuongTon()
            };
            tableModelSach.addRow(row);
        }
    }
    
    private void thongKeTheoThang() {
        try {
            int thang = Integer.parseInt(txtThang.getText().trim());
            int nam = Integer.parseInt(txtNam.getText().trim());
            
            if (thang < 1 || thang > 12) {
                JOptionPane.showMessageDialog(this, "Thang phai tu 1 den 12!");
                return;
            }
            
            double doanhThu = quanLyHoaDon.tinhDoanhThuTheoThang(thang, nam);
            
            StringBuilder sb = new StringBuilder();
            sb.append("========== THONG KE DOANH THU ==========\n");
            sb.append("Thang: ").append(thang).append("/").append(nam).append("\n");
            sb.append("Tong doanh thu: ").append(String.format("%,.0f", doanhThu)).append(" VND\n");
            sb.append("=========================================");
            
            txtKetQuaThongKe.setText(sb.toString());
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Thang va nam phai la so!");
        }
    }
    
    private void thongKeKhoangThoiGian() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        
        JTextField txtNgayBatDau = new JTextField();
        JTextField txtThangBatDau = new JTextField();
        JTextField txtNamBatDau = new JTextField(String.valueOf(LocalDate.now().getYear()));
        JTextField txtNgayKetThuc = new JTextField();
        JTextField txtThangKetThuc = new JTextField();
        JTextField txtNamKetThuc = new JTextField(String.valueOf(LocalDate.now().getYear()));
        
        panel.add(new JLabel("Ngay Bat Dau (dd):"));
        panel.add(txtNgayBatDau);
        panel.add(new JLabel("Thang Bat Dau (mm):"));
        panel.add(txtThangBatDau);
        panel.add(new JLabel("Nam Bat Dau (yyyy):"));
        panel.add(txtNamBatDau);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        
        JPanel panel2 = new JPanel(new GridLayout(3, 2, 5, 5));
        panel2.add(new JLabel("Ngay Ket Thuc (dd):"));
        panel2.add(txtNgayKetThuc);
        panel2.add(new JLabel("Thang Ket Thuc (mm):"));
        panel2.add(txtThangKetThuc);
        panel2.add(new JLabel("Nam Ket Thuc (yyyy):"));
        panel2.add(txtNamKetThuc);
        
        JPanel mainPanel = new JPanel(new GridLayout(2, 1));
        mainPanel.add(panel);
        mainPanel.add(panel2);
        
        int result = JOptionPane.showConfirmDialog(this, mainPanel, 
            "Nhap Khoang Thoi Gian", JOptionPane.OK_CANCEL_OPTION);
            
        if (result == JOptionPane.OK_OPTION) {
            try {
                int ngayBD = Integer.parseInt(txtNgayBatDau.getText().trim());
                int thangBD = Integer.parseInt(txtThangBatDau.getText().trim());
                int namBD = Integer.parseInt(txtNamBatDau.getText().trim());
                
                int ngayKT = Integer.parseInt(txtNgayKetThuc.getText().trim());
                int thangKT = Integer.parseInt(txtThangKetThuc.getText().trim());
                int namKT = Integer.parseInt(txtNamKetThuc.getText().trim());
                
                LocalDate tuNgay = LocalDate.of(namBD, thangBD, ngayBD);
                LocalDate denNgay = LocalDate.of(namKT, thangKT, ngayKT);
                
                if (tuNgay.isAfter(denNgay)) {
                    JOptionPane.showMessageDialog(this, "Ngay bat dau phai truoc ngay ket thuc!");
                    return;
                }
                
                double doanhThu = quanLyHoaDon.tinhDoanhThuTheoKhoangThoiGian(tuNgay, denNgay);
                
                StringBuilder sb = new StringBuilder();
                sb.append("========== THONG KE DOANH THU ==========\n");
                sb.append("Tu ngay: ").append(tuNgay).append("\n");
                sb.append("Den ngay: ").append(denNgay).append("\n");
                sb.append("Tong doanh thu: ").append(String.format("%,.0f", doanhThu)).append(" VND\n");
                sb.append("=========================================");
                
                txtKetQuaThongKe.setText(sb.toString());
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Du lieu khong hop le! Vui long kiem tra lai.");
            }
        }
    }
}
