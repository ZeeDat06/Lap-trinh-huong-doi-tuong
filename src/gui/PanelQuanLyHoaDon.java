package gui;

import dao.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.*;

public class PanelQuanLyHoaDon extends JPanel {
    private QuanLyHoaDon quanLyHoaDon;
    private QuanLySach quanLySach;
    private QuanLyKhachHang quanLyKhachHang;
    
    private JTable tableHoaDon;
    private DefaultTableModel tableModelHoaDon;
    private JTable tableChiTiet;
    private DefaultTableModel tableModelChiTiet;
    
    private JTextField txtMaHoaDon, txtTongTien;
    private JComboBox<String> cboKhachHang;
    private JButton btnTaoMoi, btnThemSach, btnXoaChiTiet, btnLuuHoaDon, btnXoaHoaDon, btnCapNhatKH;
    
    private HoaDon hoaDonHienTai;
    
    public PanelQuanLyHoaDon(QuanLyHoaDon quanLyHoaDon, QuanLySach quanLySach, QuanLyKhachHang quanLyKhachHang) {
        this.quanLyHoaDon = quanLyHoaDon;
        this.quanLySach = quanLySach;
        this.quanLyKhachHang = quanLyKhachHang;
        
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(taoPanelThongTinHoaDon(), BorderLayout.NORTH);
        topPanel.add(taoPanelChiTiet(), BorderLayout.CENTER);
        
        add(topPanel, BorderLayout.NORTH);
        add(taoPanelDanhSachHoaDon(), BorderLayout.CENTER);
        
        hienThiDanhSachHoaDon();
    }
    
    private JPanel taoPanelThongTinHoaDon() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Thong Tin Hoa Don"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Mã hóa đơn
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Ma Hoa Don:"), gbc);
        gbc.gridx = 1;
        txtMaHoaDon = new JTextField(15);
        txtMaHoaDon.setEditable(false);
        panel.add(txtMaHoaDon, gbc);
        
        // Khách hàng
        gbc.gridx = 2;
        panel.add(new JLabel("Khach Hang:"), gbc);
        gbc.gridx = 3;
        
        JPanel khachHangPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        cboKhachHang = new JComboBox<>();
        cboKhachHang.setPreferredSize(new Dimension(200, 25));
        capNhatDanhSachKhachHang();
        
        btnCapNhatKH = new JButton("Tai Lai");
        btnCapNhatKH.setToolTipText("Tai lai danh sach khach hang");
        btnCapNhatKH.addActionListener(e -> {
            capNhatDanhSachKhachHang();
            JOptionPane.showMessageDialog(this, "Da cap nhat danh sach khach hang!");
        });
        
        khachHangPanel.add(cboKhachHang);
        khachHangPanel.add(btnCapNhatKH);
        
        panel.add(khachHangPanel, gbc);
        
        // Tổng tiền
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Tong Tien:"), gbc);
        gbc.gridx = 1;
        txtTongTien = new JTextField(15);
        txtTongTien.setEditable(false);
        panel.add(txtTongTien, gbc);
        
        // Các nút chức năng
        JPanel panelButtons = new JPanel(new FlowLayout());
        btnTaoMoi = new JButton("Tao Hoa Don Moi");
        btnLuuHoaDon = new JButton("Luu Hoa Don");
        btnXoaHoaDon = new JButton("Xoa Hoa Don");
        
        btnTaoMoi.addActionListener(e -> taoHoaDonMoi());
        btnLuuHoaDon.addActionListener(e -> luuHoaDon());
        btnXoaHoaDon.addActionListener(e -> xoaHoaDon());
        
        panelButtons.add(btnTaoMoi);
        panelButtons.add(btnLuuHoaDon);
        panelButtons.add(btnXoaHoaDon);
        
        gbc.gridx = 2; gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(panelButtons, gbc);
        
        return panel;
    }
    
    private JPanel taoPanelChiTiet() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Chi Tiet Hoa Don"));
        
        String[] columnNames = {"Ten Sach", "So Luong", "Don Gia", "Thanh Tien"};
        tableModelChiTiet = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 1) return Integer.class; // So Luong
                if (columnIndex == 2 || columnIndex == 3) return Double.class; // Don Gia, Thanh Tien
                return String.class;
            }
        };
        
        tableChiTiet = new JTable(tableModelChiTiet);
        
        // Format cột tiền
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
        tableChiTiet.getColumnModel().getColumn(2).setCellRenderer(priceRenderer); // Don Gia
        tableChiTiet.getColumnModel().getColumn(3).setCellRenderer(priceRenderer); // Thanh Tien
        
        JScrollPane scrollPane = new JScrollPane(tableChiTiet);
        scrollPane.setPreferredSize(new Dimension(0, 150));
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnThemSach = new JButton("Them Sach");
        btnXoaChiTiet = new JButton("Xoa Chi Tiet");
        
        btnThemSach.addActionListener(e -> themSachVaoHoaDon());
        btnXoaChiTiet.addActionListener(e -> xoaChiTietHoaDon());
        
        buttonPanel.add(btnThemSach);
        buttonPanel.add(btnXoaChiTiet);
        
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JScrollPane taoPanelDanhSachHoaDon() {
        String[] columnNames = {"Ma Hoa Don", "Ngay Lap", "Khach Hang", "Tong Tien"};
        tableModelHoaDon = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 3) return Double.class; // Tong Tien
                return String.class;
            }
        };
        
        tableHoaDon = new JTable(tableModelHoaDon);
        tableHoaDon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableHoaDon.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                hienThiChiTietHoaDon();
            }
        });
        
        // Format cột tổng tiền
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
        tableHoaDon.getColumnModel().getColumn(3).setCellRenderer(priceRenderer);
        
        JScrollPane scrollPane = new JScrollPane(tableHoaDon);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Danh Sach Hoa Don"));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return scrollPane;
    }
    
    private void capNhatDanhSachKhachHang() {
        cboKhachHang.removeAllItems();
        List<KhachHang> danhSach = quanLyKhachHang.layTatCa();
        
        if (danhSach.isEmpty()) {
            cboKhachHang.addItem("-- Chua co khach hang --");
            cboKhachHang.setEnabled(false);
        } else {
            cboKhachHang.setEnabled(true);
            for (KhachHang kh : danhSach) {
                cboKhachHang.addItem(kh.getMaKhachHang() + " - " + kh.getHoTen());
            }
        }
    }
    
    private void taoHoaDonMoi() {
        if (cboKhachHang.getItemCount() == 0 || !cboKhachHang.isEnabled()) {
            JOptionPane.showMessageDialog(this, "Chua co khach hang trong he thong!\nVui long them khach hang truoc!");
            return;
        }
        
        String maHoaDon = "HD" + System.currentTimeMillis();
        txtMaHoaDon.setText(maHoaDon);
        
        String selectedKH = (String) cboKhachHang.getSelectedItem();
        if (selectedKH == null || selectedKH.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long chon khach hang!");
            return;
        }
        
        String maKH = selectedKH.split(" - ")[0];
        KhachHang khachHang = quanLyKhachHang.timTheoMa(maKH);
        
        if (khachHang == null) {
            JOptionPane.showMessageDialog(this, "Khach hang khong ton tai!");
            return;
        }
        
        hoaDonHienTai = new HoaDon(maHoaDon, LocalDate.now(), khachHang);
        
        tableModelChiTiet.setRowCount(0);
        txtTongTien.setText("0.00 VND");
        
        // Thêm listener để có thể thay đổi khách hàng trước khi lưu
        cboKhachHang.addActionListener(e -> {
            if (hoaDonHienTai != null && cboKhachHang.getSelectedItem() != null) {
                String selectedKHNew = (String) cboKhachHang.getSelectedItem();
                if (!selectedKHNew.equals("-- Chua co khach hang --")) {
                    String maKHNew = selectedKHNew.split(" - ")[0];
                    KhachHang khNew = quanLyKhachHang.timTheoMa(maKHNew);
                    if (khNew != null) {
                        hoaDonHienTai.setKhachHang(khNew);
                    }
                }
            }
        });
        
        JOptionPane.showMessageDialog(this, "Da tao hoa don moi!\nBan co the chon lai khach hang neu can thiet.");
    }
    
    private void themSachVaoHoaDon() {
        if (hoaDonHienTai == null) {
            JOptionPane.showMessageDialog(this, "Vui long tao hoa don truoc!");
            return;
        }
        
        List<Sach> danhSachSach = quanLySach.layTatCa();
        if (danhSachSach.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chua co sach trong kho!");
            return;
        }
        
        // Mở dialog chọn sách
        Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(this);
        DialogChonSach dialog = new DialogChonSach(parentFrame, quanLySach);
        dialog.setVisible(true);
        
        // Kiểm tra nếu đã chọn sách
        if (dialog.isDaChon()) {
            Sach sach = dialog.getSachDaChon();
            int soLuong = dialog.getSoLuongChon();
            
            // Kiểm tra xem sách đã có trong hóa đơn chưa
            boolean daTonTai = false;
            for (ChiTietHoaDon ct : hoaDonHienTai.getDanhSachChiTiet()) {
                if (ct.getSach().getMaSach().equals(sach.getMaSach())) {
                    // Nếu đã có, tăng số lượng
                    ct.setSoLuong(ct.getSoLuong() + soLuong);
                    daTonTai = true;
                    break;
                }
            }
            
            // Nếu chưa có, thêm mới
            if (!daTonTai) {
                ChiTietHoaDon chiTiet = new ChiTietHoaDon(sach, soLuong, sach.getGiaBan());
                hoaDonHienTai.themChiTiet(chiTiet);
            }
            
            hienThiChiTietHoaDonHienTai();
            capNhatTongTien();
            
            // Thông báo thêm thành công
            int soLuongConLai = sach.getSoLuongTon() - soLuong;
            JOptionPane.showMessageDialog(this, 
                "Da them sach vao hoa don!\n" +
                "Sach: " + sach.getTenSach() + "\n" +
                "So luong: " + soLuong + "\n" +
                "So luong ton hien tai: " + sach.getSoLuongTon() + "\n" +
                "So luong ton sau khi luu: " + soLuongConLai,
                "Thong bao",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void xoaChiTietHoaDon() {
        int selectedRow = tableChiTiet.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui long chon chi tiet de xoa!");
            return;
        }
        
        if (hoaDonHienTai != null) {
            hoaDonHienTai.getDanhSachChiTiet().remove(selectedRow);
            hienThiChiTietHoaDonHienTai();
            capNhatTongTien();
        }
    }
    
    private void luuHoaDon() {
        if (hoaDonHienTai == null) {
            JOptionPane.showMessageDialog(this, "Chua co hoa don de luu!");
            return;
        }
        
        if (hoaDonHienTai.getDanhSachChiTiet().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hoa don phai co it nhat 1 sach!");
            return;
        }
        
        // Hiển thị thông tin trước khi lưu
        StringBuilder thongTin = new StringBuilder();
        thongTin.append("Thong tin hoa don:\n");
        thongTin.append("Ma hoa don: ").append(hoaDonHienTai.getMaHoaDon()).append("\n");
        thongTin.append("Khach hang: ").append(hoaDonHienTai.getKhachHang().getHoTen()).append("\n");
        thongTin.append("So luong sach: ").append(hoaDonHienTai.getDanhSachChiTiet().size()).append("\n");
        thongTin.append("Tong tien: ").append(String.format("%,.0f", hoaDonHienTai.tinhTongTien())).append(" VND\n\n");
        thongTin.append("So luong ton se duoc cap nhat sau khi luu.");
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            thongTin.toString(), 
            "Xac nhan luu hoa don", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        
        // Cập nhật số lượng tồn kho
        for (ChiTietHoaDon ct : hoaDonHienTai.getDanhSachChiTiet()) {
            quanLySach.capNhatSoLuong(ct.getSach().getMaSach(), ct.getSoLuong());
        }
        
        quanLyHoaDon.them(hoaDonHienTai);
        hienThiDanhSachHoaDon();
        
        hoaDonHienTai = null;
        txtMaHoaDon.setText("");
        txtTongTien.setText("");
        tableModelChiTiet.setRowCount(0);
        
        JOptionPane.showMessageDialog(this, 
            "Luu hoa don thanh cong!\nSo luong ton kho da duoc cap nhat.",
            "Thanh cong",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void xoaHoaDon() {
        int selectedRow = tableHoaDon.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui long chon hoa don de xoa!");
            return;
        }
        
        String maHoaDon = tableModelHoaDon.getValueAt(selectedRow, 0).toString();
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Ban co chac chan muon xoa hoa don nay?", 
            "Xac nhan", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            quanLyHoaDon.xoa(maHoaDon);
            hienThiDanhSachHoaDon();
            JOptionPane.showMessageDialog(this, "Xoa hoa don thanh cong!");
        }
    }
    
    private void hienThiDanhSachHoaDon() {
        tableModelHoaDon.setRowCount(0);
        List<HoaDon> danhSach = quanLyHoaDon.layTatCa();
        for (HoaDon hd : danhSach) {
            Object[] row = {
                hd.getMaHoaDon(),
                hd.getNgayLap(),
                hd.getKhachHang().getHoTen(),
                hd.tinhTongTien()
            };
            tableModelHoaDon.addRow(row);
        }
    }
    
    private void hienThiChiTietHoaDon() {
        int selectedRow = tableHoaDon.getSelectedRow();
        if (selectedRow >= 0) {
            String maHoaDon = tableModelHoaDon.getValueAt(selectedRow, 0).toString();
            HoaDon hoaDon = quanLyHoaDon.timTheoMa(maHoaDon);
            
            if (hoaDon != null) {
                DecimalFormat formatter = new DecimalFormat("#,###.00");
                txtMaHoaDon.setText(hoaDon.getMaHoaDon());
                txtTongTien.setText(formatter.format(hoaDon.tinhTongTien()) + " VND");
                
                tableModelChiTiet.setRowCount(0);
                for (ChiTietHoaDon ct : hoaDon.getDanhSachChiTiet()) {
                    Object[] row = {
                        ct.getSach().getTenSach(),
                        ct.getSoLuong(),
                        ct.getDonGia(),
                        ct.tinhThanhTien()
                    };
                    tableModelChiTiet.addRow(row);
                }
            }
        }
    }
    
    private void hienThiChiTietHoaDonHienTai() {
        if (hoaDonHienTai != null) {
            tableModelChiTiet.setRowCount(0);
            for (ChiTietHoaDon ct : hoaDonHienTai.getDanhSachChiTiet()) {
                Object[] row = {
                    ct.getSach().getTenSach(),
                    ct.getSoLuong(),
                    ct.getDonGia(),
                    ct.tinhThanhTien()
                };
                tableModelChiTiet.addRow(row);
            }
        }
    }
    
    private void capNhatTongTien() {
        if (hoaDonHienTai != null) {
            DecimalFormat formatter = new DecimalFormat("#,###.00");
            txtTongTien.setText(formatter.format(hoaDonHienTai.tinhTongTien()) + " VND");
        }
    }
}
