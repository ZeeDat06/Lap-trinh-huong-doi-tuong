package gui;

import dao.QuanLySach;
import model.Sach;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DialogChonSach extends JDialog {
    private QuanLySach quanLySach;
    private JTable tableSach;
    private DefaultTableModel tableModel;
    private JTextField txtTimKiem, txtSoLuong;
    private JComboBox<String> cboSapXep;
    private JButton btnChon, btnHuy;
    
    private Sach sachDaChon;
    private int soLuongChon;
    private boolean daChon = false;
    
    public DialogChonSach(Frame parent, QuanLySach quanLySach) {
        super(parent, "Chon Sach", true);
        this.quanLySach = quanLySach;
        
        setSize(900, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));
        
        // Panel tìm kiếm và sắp xếp
        add(taoPanelTimKiem(), BorderLayout.NORTH);
        
        // Bảng hiển thị sách
        add(taoPanelBang(), BorderLayout.CENTER);
        
        // Panel nhập số lượng và nút
        add(taoPanelBottom(), BorderLayout.SOUTH);
        
        // Load dữ liệu
        hienThiDanhSach();
    }
    
    private JPanel taoPanelTimKiem() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Tim Kiem & Sap Xep"));
        
        panel.add(new JLabel("Tim Kiem:"));
        txtTimKiem = new JTextField(20);
        panel.add(txtTimKiem);
        
        JButton btnTimKiem = new JButton("Tim");
        btnTimKiem.addActionListener(e -> timKiemSach());
        panel.add(btnTimKiem);
        
        JButton btnHienThiTatCa = new JButton("Hien Thi Tat Ca");
        btnHienThiTatCa.addActionListener(e -> hienThiDanhSach());
        panel.add(btnHienThiTatCa);
        
        panel.add(new JLabel("   Sap Xep:"));
        cboSapXep = new JComboBox<>(new String[]{"Theo Ma Sach", "Theo Ten Sach", "Theo Gia Ban"});
        cboSapXep.addActionListener(e -> sapXepDanhSach());
        panel.add(cboSapXep);
        
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
        
        // Format cột giá bán
        DefaultTableCellRenderer priceRenderer = new DefaultTableCellRenderer() {
            private final DecimalFormat formatter = new DecimalFormat("#,###.00");
            
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
        
        // Double click để chọn
        tableSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    chonSach();
                }
            }
        });
        
        return new JScrollPane(tableSach);
    }
    
    private JPanel taoPanelBottom() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        panel.add(new JLabel("So Luong:"));
        txtSoLuong = new JTextField(10);
        txtSoLuong.setText("1");
        panel.add(txtSoLuong);
        
        btnChon = new JButton("Chon Sach");
        btnChon.addActionListener(e -> chonSach());
        panel.add(btnChon);
        
        btnHuy = new JButton("Huy");
        btnHuy.addActionListener(e -> {
            daChon = false;
            dispose();
        });
        panel.add(btnHuy);
        
        return panel;
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
        
        txtTimKiem.setText("");
    }
    
    private void timKiemSach() {
        String tuKhoa = txtTimKiem.getText().trim();
        if (tuKhoa.isEmpty()) {
            hienThiDanhSach();
            return;
        }
        
        tableModel.setRowCount(0);
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
                tableModel.addRow(row);
            }
        }
    }
    
    private void sapXepDanhSach() {
        // Lấy dữ liệu hiện tại từ bảng
        List<Sach> danhSach = new java.util.ArrayList<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String maSach = tableModel.getValueAt(i, 0).toString();
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
        tableModel.setRowCount(0);
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
    
    private void chonSach() {
        int selectedRow = tableSach.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui long chon sach!");
            return;
        }
        
        try {
            soLuongChon = Integer.parseInt(txtSoLuong.getText().trim());
            if (soLuongChon <= 0) {
                JOptionPane.showMessageDialog(this, "So luong phai lon hon 0!");
                return;
            }
            
            String maSach = tableModel.getValueAt(selectedRow, 0).toString();
            sachDaChon = quanLySach.timTheoMa(maSach);
            
            if (sachDaChon == null) {
                JOptionPane.showMessageDialog(this, "Khong tim thay sach!");
                return;
            }
            
            if (soLuongChon > sachDaChon.getSoLuongTon()) {
                JOptionPane.showMessageDialog(this, 
                    "So luong ton khong du!\nChi con: " + sachDaChon.getSoLuongTon() + " quyen");
                return;
            }
            
            daChon = true;
            dispose();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "So luong phai la so nguyen!");
        }
    }
    
    public boolean isDaChon() {
        return daChon;
    }
    
    public Sach getSachDaChon() {
        return sachDaChon;
    }
    
    public int getSoLuongChon() {
        return soLuongChon;
    }
}
