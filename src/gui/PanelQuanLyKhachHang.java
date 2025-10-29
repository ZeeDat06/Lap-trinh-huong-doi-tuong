package gui;

import dao.QuanLyKhachHang;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.KhachHang;

public class PanelQuanLyKhachHang extends JPanel {
    private QuanLyKhachHang quanLyKhachHang;
    private JTable tableKhachHang;
    private DefaultTableModel tableModel;
    
    private JTextField txtMaKhachHang, txtHoTen, txtDienThoai, txtEmail, txtTimKiem;
    private JButton btnThem, btnSua, btnXoa, btnLamMoi, btnTimKiem;
    
    public PanelQuanLyKhachHang(QuanLyKhachHang quanLyKhachHang) {
        this.quanLyKhachHang = quanLyKhachHang;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        add(taoPanelNhapLieu(), BorderLayout.NORTH);
        add(taoPanelBang(), BorderLayout.CENTER);
        
        hienThiDanhSach();
    }
    
    private JPanel taoPanelNhapLieu() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Thong Tin Khach Hang"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Mã khách hàng
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Ma Khach Hang:"), gbc);
        gbc.gridx = 1;
        txtMaKhachHang = new JTextField(15);
        panel.add(txtMaKhachHang, gbc);
        
        // Họ tên
        gbc.gridx = 2;
        panel.add(new JLabel("Ho Ten:"), gbc);
        gbc.gridx = 3;
        txtHoTen = new JTextField(15);
        panel.add(txtHoTen, gbc);
        
        // Điện thoại
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Dien Thoai:"), gbc);
        gbc.gridx = 1;
        txtDienThoai = new JTextField(15);
        panel.add(txtDienThoai, gbc);
        
        // Email
        gbc.gridx = 2;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 3;
        txtEmail = new JTextField(15);
        panel.add(txtEmail, gbc);
        
        // Panel tìm kiếm
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Tim Kiem:"), gbc);
        gbc.gridx = 1;
        txtTimKiem = new JTextField(15);
        panel.add(txtTimKiem, gbc);
        gbc.gridx = 2;
        btnTimKiem = new JButton("Tim Theo Ten");
        btnTimKiem.addActionListener(e -> timKiem());
        panel.add(btnTimKiem, gbc);
        
        // Các nút chức năng
        JPanel panelButtons = new JPanel(new FlowLayout());
        btnThem = new JButton("Them");
        btnSua = new JButton("Sua");
        btnXoa = new JButton("Xoa");
        btnLamMoi = new JButton("Lam Moi");
        
        btnThem.addActionListener(e -> themKhachHang());
        btnSua.addActionListener(e -> suaKhachHang());
        btnXoa.addActionListener(e -> xoaKhachHang());
        btnLamMoi.addActionListener(e -> lamMoi());
        
        panelButtons.add(btnThem);
        panelButtons.add(btnSua);
        panelButtons.add(btnXoa);
        panelButtons.add(btnLamMoi);
        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 4;
        panel.add(panelButtons, gbc);
        
        return panel;
    }
    
    private JScrollPane taoPanelBang() {
        String[] columnNames = {"Ma Khach Hang", "Ho Ten", "Dien Thoai", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableKhachHang = new JTable(tableModel);
        tableKhachHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableKhachHang.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                hienThiChiTiet();
            }
        });
        
        return new JScrollPane(tableKhachHang);
    }
    
    private void hienThiDanhSach() {
        tableModel.setRowCount(0);
        List<KhachHang> danhSach = quanLyKhachHang.layTatCa();
        for (KhachHang kh : danhSach) {
            Object[] row = {
                kh.getMaKhachHang(),
                kh.getHoTen(),
                kh.getDienThoai(),
                kh.getEmail()
            };
            tableModel.addRow(row);
        }
    }
    
    private void hienThiChiTiet() {
        int selectedRow = tableKhachHang.getSelectedRow();
        if (selectedRow >= 0) {
            txtMaKhachHang.setText(tableModel.getValueAt(selectedRow, 0).toString());
            txtHoTen.setText(tableModel.getValueAt(selectedRow, 1).toString());
            txtDienThoai.setText(tableModel.getValueAt(selectedRow, 2).toString());
            txtEmail.setText(tableModel.getValueAt(selectedRow, 3).toString());
        }
    }
    
    private void themKhachHang() {
        String maKhachHang = txtMaKhachHang.getText().trim();
        if (maKhachHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long nhap ma khach hang!");
            return;
        }
        
        if (quanLyKhachHang.timTheoMa(maKhachHang) != null) {
            JOptionPane.showMessageDialog(this, "Ma khach hang da ton tai!");
            return;
        }
        
        KhachHang kh = new KhachHang(
            maKhachHang,
            txtHoTen.getText().trim(),
            txtDienThoai.getText().trim(),
            txtEmail.getText().trim()
        );
        
        quanLyKhachHang.them(kh);
        hienThiDanhSach();
        lamMoi();
        JOptionPane.showMessageDialog(this, "Them khach hang thanh cong!");
    }
    
    private void suaKhachHang() {
        String maKhachHang = txtMaKhachHang.getText().trim();
        if (maKhachHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long chon khach hang de sua!");
            return;
        }
        
        KhachHang kh = new KhachHang(
            maKhachHang,
            txtHoTen.getText().trim(),
            txtDienThoai.getText().trim(),
            txtEmail.getText().trim()
        );
        
        quanLyKhachHang.sua(kh);
        hienThiDanhSach();
        JOptionPane.showMessageDialog(this, "Sua khach hang thanh cong!");
    }
    
    private void xoaKhachHang() {
        String maKhachHang = txtMaKhachHang.getText().trim();
        if (maKhachHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long chon khach hang de xoa!");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Ban co chac chan muon xoa khach hang nay?", 
            "Xac nhan", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            quanLyKhachHang.xoa(maKhachHang);
            hienThiDanhSach();
            lamMoi();
            JOptionPane.showMessageDialog(this, "Xoa khach hang thanh cong!");
        }
    }
    
    private void timKiem() {
        String tuKhoa = txtTimKiem.getText().trim();
        if (tuKhoa.isEmpty()) {
            hienThiDanhSach();
            return;
        }
        
        tableModel.setRowCount(0);
        List<KhachHang> ketQua = quanLyKhachHang.timTheoTen(tuKhoa);
        for (KhachHang kh : ketQua) {
            Object[] row = {
                kh.getMaKhachHang(),
                kh.getHoTen(),
                kh.getDienThoai(),
                kh.getEmail()
            };
            tableModel.addRow(row);
        }
    }
    
    private void lamMoi() {
        txtMaKhachHang.setText("");
        txtHoTen.setText("");
        txtDienThoai.setText("");
        txtEmail.setText("");
        txtTimKiem.setText("");
        tableKhachHang.clearSelection();
        hienThiDanhSach();
    }
}
