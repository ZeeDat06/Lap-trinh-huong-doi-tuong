import java.awt.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class KhachHangGiaoDien extends JFrame {
    private QuanLySach quanLySach;
    private QuanLyKhachHang quanLyKhachHang;
    private QuanLyHoaDon quanLyHoaDon;
    private KhachHang khachHang;
    
    private JTable tableSach;
    private DefaultTableModel modelSach;
    private JLabel lblWelcome;
    private JLabel lblDiemTichLuy;
    
    public KhachHangGiaoDien(QuanLySach quanLySach, QuanLyKhachHang quanLyKhachHang, 
                             QuanLyHoaDon quanLyHoaDon, String maKhachHang) {
        this.quanLySach = quanLySach;
        this.quanLyKhachHang = quanLyKhachHang;
        this.quanLyHoaDon = quanLyHoaDon;
        this.khachHang = quanLyKhachHang.timKiemTheoMa(maKhachHang);
        
        initialize();
    }
    
    private void initialize() {
        setTitle("Cửa hàng sách - Khách hàng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        taoGiaoDien();
        setVisible(true);
    }
    
    private void taoGiaoDien() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        
        taoPhanDau(mainPanel);
        taoNoiDungChinh(mainPanel);
        
        setContentPane(mainPanel);
    }
    
    private void taoPhanDau(JPanel mainPanel) {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(44, 62, 80));
        headerPanel.setPreferredSize(new Dimension(1000, 100));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        JLabel titleLabel = new JLabel("Cửa hàng sách");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        
        JPanel userPanel = new JPanel(new GridLayout(3, 1));
        userPanel.setBackground(new Color(44, 62, 80));
        
        lblWelcome = new JLabel("Xin chào: " + khachHang.getHoTen());
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblWelcome.setForeground(Color.WHITE);
        lblWelcome.setHorizontalAlignment(SwingConstants.RIGHT);
        
        lblDiemTichLuy = new JLabel(String.format("Điểm tích lũy: %d điểm", khachHang.getDiemTichLuy()));
        lblDiemTichLuy.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDiemTichLuy.setForeground(new Color(241, 196, 15));
        lblDiemTichLuy.setHorizontalAlignment(SwingConstants.RIGHT);
        
        String vipStatus = khachHang.isVIP() ? "⭐ VIP - Giảm 10%" : 
                          (khachHang.getDiemTichLuy() >= 50 ? "Giảm 5%" : "Khách hàng thường");
        JLabel lblVIP = new JLabel(vipStatus);
        lblVIP.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblVIP.setForeground(khachHang.isVIP() ? new Color(241, 196, 15) : Color.LIGHT_GRAY);
        lblVIP.setHorizontalAlignment(SwingConstants.RIGHT);
        
        userPanel.add(lblWelcome);
        userPanel.add(lblDiemTichLuy);
        userPanel.add(lblVIP);
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(userPanel, BorderLayout.EAST);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
    }
    
    private void taoNoiDungChinh(JPanel mainPanel) {
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        
        JLabel titleLabel = new JLabel("Danh sách sách");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setBackground(Color.WHITE);
        
        JLabel lblTimKiem = new JLabel("Tìm kiếm:");
        JTextField txtTimKiem = new JTextField(20);
        JComboBox<String> cboTimKiem = new JComboBox<>(new String[]{"Tên sách", "Tác giả", "Thể loại"});
        JButton btnTimKiem = taoNutPhu("Tìm");
        JButton btnReset = taoNutPhu("Làm mới");
        
        btnTimKiem.addActionListener(e -> {
            String tuKhoa = txtTimKiem.getText().trim().toLowerCase();
            String tieuChi = cboTimKiem.getSelectedItem().toString();
            timKiemSach(tuKhoa, tieuChi);
        });
        
        btnReset.addActionListener(e -> {
            txtTimKiem.setText("");
            capNhatBangSach();
        });
        
        searchPanel.add(lblTimKiem);
        searchPanel.add(txtTimKiem);
        searchPanel.add(cboTimKiem);
        searchPanel.add(btnTimKiem);
        searchPanel.add(btnReset);
        
        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(searchPanel, BorderLayout.EAST);
        
        String[] columnNames = {"Mã sách", "Tên sách", "Tác giả", "Thể loại", "Giá bán", "Tồn kho"};
        modelSach = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableSach = new JTable(modelSach);
        tableSach.setRowHeight(45);
        tableSach.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tableSach.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tableSach.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(tableSach);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199)));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        JButton btnMuaSach = taoNutChinh("🛒 Mua sách");
        JButton btnXemGioHang = taoNutChinh("Xem giỏ hàng");
        JButton btnLichSuMua = taoNutPhu("Lịch sử mua hàng");
        JButton btnDangXuat = new JButton("Đăng xuất");
        btnDangXuat.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnDangXuat.setBackground(new Color(231, 76, 60));
        btnDangXuat.setForeground(Color.WHITE);
        btnDangXuat.setFocusPainted(false);
        btnDangXuat.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        btnDangXuat.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnMuaSach.addActionListener(e -> hienThiMuaSachDialog());
        btnXemGioHang.addActionListener(e -> hienThiGioHangDialog());
        btnLichSuMua.addActionListener(e -> hienThiLichSuMuaHang());
        btnDangXuat.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Bạn có chắc chắn muốn đăng xuất?", 
                "Xác nhận", 
                JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                SwingUtilities.invokeLater(() -> {
                    new Main();
                });
            }
        });
        
        buttonPanel.add(btnLichSuMua);
        buttonPanel.add(btnXemGioHang);
        buttonPanel.add(btnMuaSach);
        buttonPanel.add(btnDangXuat);
        
        contentPanel.add(topPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        capNhatBangSach();
    }
    
    private void capNhatBangSach() {
        modelSach.setRowCount(0);
        for (Sach sach : quanLySach.layTatCa()) {
            if (sach.getSoLuongTon() > 0) {
                modelSach.addRow(new Object[]{
                    sach.getMaSach(),
                    sach.getTen(),
                    sach.getTacGia(),
                    sach.getTheLoai(),
                    String.format("%,.0f VND", sach.getGiaBan()),
                    sach.getSoLuongTon()
                });
            }
        }
    }
    
    private void timKiemSach(String tuKhoa, String tieuChi) {
        modelSach.setRowCount(0);
        if (tuKhoa.isEmpty()) {
            capNhatBangSach();
            return;
        }
        
        for (Sach sach : quanLySach.layTatCa()) {
            if (sach.getSoLuongTon() <= 0) continue;
            
            boolean khopTuKhoa = false;
            switch (tieuChi) {
                case "Tên sách":
                    khopTuKhoa = sach.getTen().toLowerCase().contains(tuKhoa);
                    break;
                case "Tác giả":
                    khopTuKhoa = sach.getTacGia().toLowerCase().contains(tuKhoa);
                    break;
                case "Thể loại":
                    khopTuKhoa = sach.getTheLoai().toLowerCase().contains(tuKhoa);
                    break;
            }
            
            if (khopTuKhoa) {
                modelSach.addRow(new Object[]{
                    sach.getMaSach(),
                    sach.getTen(),
                    sach.getTacGia(),
                    sach.getTheLoai(),
                    String.format("%,.0f VND", sach.getGiaBan()),
                    sach.getSoLuongTon()
                });
            }
        }
    }
    
    private DefaultTableModel gioHangModel = new DefaultTableModel(
        new String[]{"Mã sách", "Tên sách", "Số lượng", "Đơn giá", "Thành tiền"}, 0);
    
    private void hienThiMuaSachDialog() {
        int selectedRow = tableSach.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sách cần mua!");
            return;
        }
        
        String maSach = modelSach.getValueAt(selectedRow, 0).toString();
        Sach sach = quanLySach.timKiemTheoMa(maSach);
        
        if (sach == null || sach.getSoLuongTon() <= 0) {
            JOptionPane.showMessageDialog(this, "Sách đã hết hàng!");
            return;
        }
        
        String soLuongStr = JOptionPane.showInputDialog(this, 
            "Nhập số lượng muốn mua (Tồn kho: " + sach.getSoLuongTon() + "):", 
            "Mua sách", 
            JOptionPane.QUESTION_MESSAGE);
        
        if (soLuongStr == null || soLuongStr.trim().isEmpty()) {
            return;
        }
        
        try {
            int soLuong = Integer.parseInt(soLuongStr.trim());
            
            if (soLuong <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0!");
                return;
            }
            
            if (soLuong > sach.getSoLuongTon()) {
                JOptionPane.showMessageDialog(this, "Số lượng vượt quá tồn kho!");
                return;
            }
            
            boolean daTonTai = false;
            for (int i = 0; i < gioHangModel.getRowCount(); i++) {
                if (gioHangModel.getValueAt(i, 0).toString().equals(maSach)) {
                    int soLuongCu = Integer.parseInt(gioHangModel.getValueAt(i, 2).toString());
                    int soLuongMoi = soLuongCu + soLuong;
                    
                    if (soLuongMoi > sach.getSoLuongTon()) {
                        JOptionPane.showMessageDialog(this, "Tổng số lượng vượt quá tồn kho!");
                        return;
                    }
                    
                    gioHangModel.setValueAt(soLuongMoi, i, 2);
                    gioHangModel.setValueAt(soLuongMoi * sach.getGiaBan(), i, 4);
                    daTonTai = true;
                    break;
                }
            }
            
            if (!daTonTai) {
                gioHangModel.addRow(new Object[]{
                    sach.getMaSach(),
                    sach.getTen(),
                    soLuong,
                    sach.getGiaBan(),
                    soLuong * sach.getGiaBan()
                });
            }
            
            JOptionPane.showMessageDialog(this, "Đã thêm vào giỏ hàng!");
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ!");
        }
    }
    
    private void hienThiGioHangDialog() {
        if (gioHangModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Giỏ hàng trống!");
            return;
        }
        
        JDialog dialog = new JDialog(this, "Giỏ hàng của bạn", true);
        dialog.setSize(700, 500);
        dialog.setLocationRelativeTo(this);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JTable gioHangTable = new JTable(gioHangModel);
        gioHangTable.setRowHeight(40);
        JScrollPane scroll = new JScrollPane(gioHangTable);
        
        double tongTien = 0;
        for (int i = 0; i < gioHangModel.getRowCount(); i++) {
            tongTien += Double.parseDouble(gioHangModel.getValueAt(i, 4).toString());
        }
        
        double giamGia = khachHang.tinhGiamGia(tongTien);
        double thanhToan = tongTien - giamGia;
        
        JPanel thongTinPanel = new JPanel(new GridLayout(4, 2, 10, 5));
        thongTinPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        thongTinPanel.add(new JLabel("Tổng tiền:"));
        thongTinPanel.add(new JLabel(String.format("%,.0f VND", tongTien)));
        
        thongTinPanel.add(new JLabel("Giảm giá:"));
        JLabel lblGiamGia = new JLabel(String.format("-%,.0f VND", giamGia));
        lblGiamGia.setForeground(new Color(39, 174, 96));
        thongTinPanel.add(lblGiamGia);
        
        thongTinPanel.add(new JLabel("Thanh toán:"));
        JLabel lblThanhToan = new JLabel(String.format("%,.0f VND", thanhToan));
        lblThanhToan.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblThanhToan.setForeground(new Color(231, 76, 60));
        thongTinPanel.add(lblThanhToan);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnXoa = taoNutPhu("Xóa sách");
        JButton btnThanhToan = taoNutChinh("Thanh toán");
        JButton btnDong = taoNutPhu("Đóng");
        
        btnXoa.addActionListener(e -> {
            int selectedRow = gioHangTable.getSelectedRow();
            if (selectedRow >= 0) {
                gioHangModel.removeRow(selectedRow);
                dialog.dispose();
                if (gioHangModel.getRowCount() > 0) {
                    hienThiGioHangDialog();
                }
            }
        });
        
        btnThanhToan.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(dialog, 
                "Xác nhận thanh toán: " + String.format("%,.0f VND", thanhToan) + "?",
                "Thanh toán",
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                thanhToanGioHang();
                dialog.dispose();
            }
        });
        
        btnDong.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnDong);
        buttonPanel.add(btnThanhToan);
        
        mainPanel.add(scroll, BorderLayout.CENTER);
        mainPanel.add(thongTinPanel, BorderLayout.SOUTH);
        mainPanel.add(buttonPanel, BorderLayout.PAGE_END);
        
        dialog.add(mainPanel);
        dialog.setVisible(true);
    }
    
    private void thanhToanGioHang() {
        HoaDon hoaDon = new HoaDon("HD" + System.currentTimeMillis(), new java.util.Date(), khachHang);
        
        boolean thanhCong = true;
        for (int i = 0; i < gioHangModel.getRowCount(); i++) {
            String maSach = gioHangModel.getValueAt(i, 0).toString();
            int soLuong = Integer.parseInt(gioHangModel.getValueAt(i, 2).toString());
            double donGia = Double.parseDouble(gioHangModel.getValueAt(i, 3).toString());
            
            Sach sach = quanLySach.timKiemTheoMa(maSach);
            if (sach != null && sach.getSoLuongTon() >= soLuong) {
                ChiTietHoaDon chiTiet = new ChiTietHoaDon(sach, soLuong, donGia);
                hoaDon.themChiTiet(chiTiet);
                
                sach.setSoLuongTon(sach.getSoLuongTon() - soLuong);
                quanLySach.sua(sach);
            } else {
                thanhCong = false;
                JOptionPane.showMessageDialog(this, 
                    "Sách " + sach.getTen() + " không đủ số lượng!");
                break;
            }
        }
        
        if (thanhCong) {
            hoaDon.hoanThanh();
            khachHang.capNhatDiemTichLuy(hoaDon.getTongTien());
            quanLyKhachHang.sua(khachHang);
            quanLyHoaDon.them(hoaDon);
            
            saveDuLieu();
            
            gioHangModel.setRowCount(0);
            capNhatBangSach();
            
            lblDiemTichLuy.setText(String.format("Điểm tích lũy: %d điểm", khachHang.getDiemTichLuy()));
            
            JOptionPane.showMessageDialog(this, 
                String.format("Thanh toán thành công!\nTổng tiền: %,.0f VND\nĐiểm tích lũy mới: %d điểm", 
                    hoaDon.getTongTien(), khachHang.getDiemTichLuy()),
                "Thành công",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void hienThiLichSuMuaHang() {
        JDialog dialog = new JDialog(this, "Lịch sử mua hàng", true);
        dialog.setSize(800, 500);
        dialog.setLocationRelativeTo(this);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        String[] columns = {"Mã HĐ", "Ngày mua", "Tổng tiền", "Giảm giá", "Thanh toán"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        for (HoaDon hd : quanLyHoaDon.layTatCa()) {
            if (hd.getKhachHang().getMaKhachHang().equals(khachHang.getMaKhachHang())) {
                model.addRow(new Object[]{
                    hd.getMaHoaDon(),
                    new SimpleDateFormat("dd/MM/yyyy HH:mm").format(hd.getNgayLap()),
                    String.format("%,.0f VND", hd.getTongTienGoc()),
                    String.format("%,.0f VND", hd.getTienGiamGia()),
                    String.format("%,.0f VND", hd.getTongTien())
                });
            }
        }
        
        JTable table = new JTable(model);
        table.setRowHeight(40);
        JScrollPane scroll = new JScrollPane(table);
        
        JButton btnDong = taoNutPhu("Đóng");
        btnDong.addActionListener(e -> dialog.dispose());
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(btnDong);
        
        mainPanel.add(scroll, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.add(mainPanel);
        dialog.setVisible(true);
    }
    
    private void saveDuLieu() {
        QuanLyFile.ghiDuLieuSach(quanLySach.layTatCa(), "sach.dat");
        QuanLyFile.ghiDuLieuKhachHang(quanLyKhachHang.layTatCa(), "khachhang.dat");
        QuanLyFile.ghiDuLieuHoaDon(quanLyHoaDon.layTatCa(), "hoadon.dat");
    }
    
    private JButton taoNutChinh(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBackground(new Color(41, 128, 185));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
    
    private JButton taoNutPhu(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setBackground(new Color(236, 240, 241));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}
