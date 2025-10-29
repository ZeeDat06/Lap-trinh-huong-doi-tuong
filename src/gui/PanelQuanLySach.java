package gui;

import dao.QuanLySach;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.Sach;

public class PanelQuanLySach extends JPanel {
    private final QuanLySach quanLySach;
    private JTable tableSach;
    private DefaultTableModel tableModel;
    
    private JTextField txtMaSach, txtTenSach, txtTacGia, txtTheLoai, txtGiaBan, txtSoLuongTon;
    private JButton btnThem, btnSua, btnXoa, btnLamMoi;
    private JComboBox<String> cboSapXep;
    
    public PanelQuanLySach(QuanLySach quanLySach) {
        this.quanLySach = quanLySach;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel nhập liệu
        add(taoPanelNhapLieu(), BorderLayout.NORTH);
        
        // Table hiển thị danh sách
        add(taoPanelBang(), BorderLayout.CENTER);
        
        // Load dữ liệu
        hienThiDanhSach();
    }
    
    private JPanel taoPanelNhapLieu() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Thong Tin Sach"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Mã sách
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Ma Sach:"), gbc);
        gbc.gridx = 1;
        txtMaSach = new JTextField(15);
        panel.add(txtMaSach, gbc);
        
        // Tên sách
        gbc.gridx = 2;
        panel.add(new JLabel("Ten Sach:"), gbc);
        gbc.gridx = 3;
        txtTenSach = new JTextField(15);
        panel.add(txtTenSach, gbc);
        
        // Tác giả
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Tac Gia:"), gbc);
        gbc.gridx = 1;
        txtTacGia = new JTextField(15);
        panel.add(txtTacGia, gbc);
        
        // Thể loại
        gbc.gridx = 2;
        panel.add(new JLabel("The Loai:"), gbc);
        gbc.gridx = 3;
        txtTheLoai = new JTextField(15);
        panel.add(txtTheLoai, gbc);
        
        // Giá bán
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Gia Ban:"), gbc);
        gbc.gridx = 1;
        txtGiaBan = new JTextField(15);
        panel.add(txtGiaBan, gbc);
        
        // Số lượng tồn
        gbc.gridx = 2;
        panel.add(new JLabel("So Luong Ton:"), gbc);
        gbc.gridx = 3;
        txtSoLuongTon = new JTextField(15);
        panel.add(txtSoLuongTon, gbc);
        
        // Các nút chức năng
        JPanel panelButtons = new JPanel(new FlowLayout());
        btnThem = new JButton("Them");
        btnSua = new JButton("Sua");
        btnXoa = new JButton("Xoa");
        btnLamMoi = new JButton("Lam Moi");
        
        btnThem.addActionListener(e -> themSach());
        btnSua.addActionListener(e -> suaSach());
        btnXoa.addActionListener(e -> xoaSach());
        btnLamMoi.addActionListener(e -> lamMoi());
        
        panelButtons.add(btnThem);
        panelButtons.add(btnSua);
        panelButtons.add(btnXoa);
        panelButtons.add(btnLamMoi);
        
        // Sắp xếp
        panelButtons.add(new JLabel("   Sap Xep:"));
        cboSapXep = new JComboBox<>(new String[]{"Theo Ma Sach", "Theo Ten Sach", "Theo Gia Ban"});
        cboSapXep.addActionListener(e -> sapXepDanhSach());
        panelButtons.add(cboSapXep);
        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 4;
        panel.add(panelButtons, gbc);
        
        return panel;
    }
    
    private JScrollPane taoPanelBang() {
        String[] columnNames = {"Ma Sach", "Ten Sach", "Tac Gia", "The Loai", "Gia Ban", "So Luong Ton"};
        tableModel = new DefaultTableModel(columnNames, 0) {
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
        
        tableSach = new JTable(tableModel);
        tableSach.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableSach.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                hienThiChiTiet();
            }
        });
        
        // Format cột giá bán
        DefaultTableCellRenderer priceRenderer = new DefaultTableCellRenderer() {
            private DecimalFormat formatter = new DecimalFormat("#,###.00");
            
            @Override
            public void setValue(Object value) {
                if (value instanceof Double) {
                    setText(formatter.format(value) + " VND");
                } else {
                    super.setValue(value);
                }
            }
        };
        priceRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tableSach.getColumnModel().getColumn(4).setCellRenderer(priceRenderer);
        
        return new JScrollPane(tableSach);
    }
    
    private void hienThiDanhSach() {
        tableModel.setRowCount(0);
        List<Sach> danhSach = quanLySach.layTatCa();
        
        // Sắp xếp mặc định theo mã sách
        Collections.sort(danhSach, new Comparator<Sach>() {
            @Override
            public int compare(Sach s1, Sach s2) {
                return s1.getMaSach().compareTo(s2.getMaSach());
            }
        });
        
        for (Sach s : danhSach) {
            Object[] row = {
                s.getMaSach(),
                s.getTenSach(),
                s.getTacGia(),
                s.getTheLoai(),
                s.getGiaBan(),
                s.getSoLuongTon()
            };
            tableModel.addRow(row);
        }
    }
    
    private void sapXepDanhSach() {
        tableModel.setRowCount(0);
        List<Sach> danhSach = quanLySach.layTatCa();
        
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
        
        for (Sach s : danhSach) {
            Object[] row = {
                s.getMaSach(),
                s.getTenSach(),
                s.getTacGia(),
                s.getTheLoai(),
                s.getGiaBan(),
                s.getSoLuongTon()
            };
            tableModel.addRow(row);
        }
    }
    
    private void hienThiChiTiet() {
        int selectedRow = tableSach.getSelectedRow();
        if (selectedRow >= 0) {
            txtMaSach.setText(tableModel.getValueAt(selectedRow, 0).toString());
            txtTenSach.setText(tableModel.getValueAt(selectedRow, 1).toString());
            txtTacGia.setText(tableModel.getValueAt(selectedRow, 2).toString());
            txtTheLoai.setText(tableModel.getValueAt(selectedRow, 3).toString());
            txtGiaBan.setText(tableModel.getValueAt(selectedRow, 4).toString());
            txtSoLuongTon.setText(tableModel.getValueAt(selectedRow, 5).toString());
        }
    }
    
    private void themSach() {
        try {
            String maSach = txtMaSach.getText().trim();
            if (maSach.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui long nhap ma sach!");
                return;
            }
            
            if (quanLySach.timTheoMa(maSach) != null) {
                JOptionPane.showMessageDialog(this, "Ma sach da ton tai!");
                return;
            }
            
            Sach sach = new Sach(
                maSach,
                txtTenSach.getText().trim(),
                txtTacGia.getText().trim(),
                txtTheLoai.getText().trim(),
                Double.parseDouble(txtGiaBan.getText().trim()),
                Integer.parseInt(txtSoLuongTon.getText().trim())
            );
            
            quanLySach.them(sach);
            hienThiDanhSach();
            lamMoi();
            JOptionPane.showMessageDialog(this, "Them sach thanh cong!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Gia ban va so luong phai la so!");
        }
    }
    
    private void suaSach() {
        try {
            String maSach = txtMaSach.getText().trim();
            if (maSach.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui long chon sach de sua!");
                return;
            }
            
            Sach sach = new Sach(
                maSach,
                txtTenSach.getText().trim(),
                txtTacGia.getText().trim(),
                txtTheLoai.getText().trim(),
                Double.parseDouble(txtGiaBan.getText().trim()),
                Integer.parseInt(txtSoLuongTon.getText().trim())
            );
            
            quanLySach.sua(sach);
            hienThiDanhSach();
            JOptionPane.showMessageDialog(this, "Sua sach thanh cong!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Gia ban va so luong phai la so!");
        }
    }
    
    private void xoaSach() {
        String maSach = txtMaSach.getText().trim();
        if (maSach.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long chon sach de xoa!");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Ban co chac chan muon xoa sach nay?", 
            "Xac nhan", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            quanLySach.xoa(maSach);
            hienThiDanhSach();
            lamMoi();
            JOptionPane.showMessageDialog(this, "Xoa sach thanh cong!");
        }
    }
    
    private void lamMoi() {
        txtMaSach.setText("");
        txtTenSach.setText("");
        txtTacGia.setText("");
        txtTheLoai.setText("");
        txtGiaBan.setText("");
        txtSoLuongTon.setText("");
        tableSach.clearSelection();
    }
}
