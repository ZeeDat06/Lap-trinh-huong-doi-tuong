import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GiaoDien extends JFrame {
    private QuanLySach quanLySach;
    private QuanLyKhachHang quanLyKhachHang;
    private QuanLyHoaDon quanLyHoaDon;
    private TimKiemThongKe timKiemThongKe;
    
    private JPanel mainContentPanel;
    private CardLayout cardLayout;
    
    private JTable tableSach;
    private JTable tableKhachHang;
    private JTable tableHoaDon;
    private DefaultTableModel modelSach;
    private DefaultTableModel modelKhachHang;
    private DefaultTableModel modelHoaDon;
    
    public GiaoDien() {
        this.quanLySach = new QuanLySach();
        this.quanLyKhachHang = new QuanLyKhachHang();
        this.quanLyHoaDon = new QuanLyHoaDon();
        this.timKiemThongKe = new TimKiemThongKe(quanLySach, quanLyHoaDon);
        
        loadDuLieu();
        initialize();
    }
    
    private void loadDuLieu() {
        List<Sach> sachList = QuanLyFile.docDuLieuSach("sach.dat");
        if (sachList != null) {
            for (Sach sach : sachList) {
                quanLySach.them(sach);
            }
        }
        
        List<KhachHang> khachHangList = QuanLyFile.docDuLieuKhachHang("khachhang.dat");
        if (khachHangList != null) {
            for (KhachHang kh : khachHangList) {
                quanLyKhachHang.them(kh);
            }
        }
        
        List<HoaDon> hoaDonList = QuanLyFile.docDuLieuHoaDon("hoadon.dat");
        if (hoaDonList != null) {
            for (HoaDon hd : hoaDonList) {
                quanLyHoaDon.them(hd);
            }
        }
    }
    
    private void saveDuLieu() {
        QuanLyFile.ghiDuLieuSach(quanLySach.layTatCa(), "sach.dat");
        QuanLyFile.ghiDuLieuKhachHang(quanLyKhachHang.layTatCa(), "khachhang.dat");
        QuanLyFile.ghiDuLieuHoaDon(quanLyHoaDon.layTatCa(), "hoadon.dat");
    }
    
    private void initialize() {
        setTitle("BookStore Manager - Phần Mềm Quản Lý Cửa Hàng Sách");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        
        taoGiaoDien();
        setVisible(true);
    }
    
    private void taoGiaoDien() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        taoPhanDau(mainPanel);
        taoThanhDieuHuong(mainPanel);
        taoNoiDungChinh(mainPanel);

        setContentPane(mainPanel);
    }
    
    private void taoPhanDau(JPanel mainPanel) {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(44, 62, 80));
        headerPanel.setPreferredSize(new Dimension(1200, 80));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        
        JLabel titleLabel = new JLabel("Quản lý cửa hàng sách");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);

        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        userPanel.setBackground(new Color(44, 62, 80));
        JLabel userText = new JLabel("Quản trị viên");
        userText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userText.setForeground(Color.WHITE);
        userPanel.add(userText);

        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(userPanel, BorderLayout.EAST);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
    }
    
    private void taoThanhDieuHuong(JPanel mainPanel) {
        JPanel navPanel = new JPanel();
        navPanel.setBackground(new Color(52, 73, 94));
        navPanel.setPreferredSize(new Dimension(200, 700));
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        
        JLabel navTitle = new JLabel("DANH MỤC");
        navTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        navTitle.setForeground(new Color(236, 240, 241));
        navTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        navTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        navPanel.add(navTitle);
        navPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        String[] menuItems = {
            "Trang chủ",
            "Quản lý Sách",
            "Quản lý Khách hàng",
            "Quản lý Hóa đơn",
            "Thống kê & Báo cáo",
            "Thoát"
        };
        
        for (String menuItem : menuItems) {
            JButton menuButton = taoNutMenu(menuItem);
            menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            menuButton.setMaximumSize(new Dimension(180, 45));
            navPanel.add(menuButton);
            navPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        }
        
        mainPanel.add(navPanel, BorderLayout.WEST);
    }
    
    private JButton taoNutMenu(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setBackground(new Color(52, 73, 94));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setHorizontalAlignment(SwingConstants.LEFT);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(44, 62, 80));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(52, 73, 94));
            }
        });

        button.addActionListener(e -> {
            switch (text) {
                case "Trang chủ":
                    hienThiTrangChu();
                    break;
                case "Quản lý Sách":
                    hienThiQuanLySach();
                    break;
                case "Quản lý Khách hàng":
                    hienThiQuanLyKhachHang();
                    break;
                case "Quản lý Hóa đơn":
                    hienThiQuanLyHoaDon();
                    break;
                case "Thống kê & Báo cáo":
                    hienThiThongKe();
                    break;
                case "Thoát":
                    saveDuLieu();
                    System.exit(0);
                    break;
            }
        });

        return button;
    }
    
    private void taoNoiDungChinh(JPanel mainPanel) {
        mainContentPanel = new JPanel(new CardLayout());
        mainContentPanel.setBackground(Color.WHITE);
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        taoTrangChu();
        taoQuanLySach();
        taoQuanLyKhachHang();
        taoQuanLyHoaDon();
        taoThongKe();
        
        mainPanel.add(mainContentPanel, BorderLayout.CENTER);

        cardLayout = (CardLayout) mainContentPanel.getLayout();
        hienThiTrangChu();
    }
    
    private void taoTrangChu() {
        JPanel dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("Tổng quan hệ thống");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        JPanel statsPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        statsPanel.setBackground(Color.WHITE);
        
        JPanel card1 = taoTheThongKe("", "Tổng sách", String.valueOf(quanLySach.layTatCa().size()), new Color(41, 128, 185));
        JPanel card2 = taoTheThongKe("", "Khách hàng", String.valueOf(quanLyKhachHang.layTatCa().size()), new Color(39, 174, 96));
        JPanel card3 = taoTheThongKe("", "Hóa đơn", String.valueOf(quanLyHoaDon.layTatCa().size()), new Color(243, 156, 18));
        
        double totalRevenue = quanLyHoaDon.layTatCa().stream()
                .mapToDouble(HoaDon::getTongTien)
                .sum();
        JPanel card4 = taoTheThongKe("", "Doanh thu", String.format("%,.0f VND", totalRevenue), new Color(231, 76, 60));
        
        statsPanel.add(card1);
        statsPanel.add(card2);
        statsPanel.add(card3);
        statsPanel.add(card4);
        
        JPanel recentPanel = new JPanel(new BorderLayout());
        recentPanel.setBackground(Color.WHITE);
        recentPanel.setBorder(BorderFactory.createTitledBorder("Hóa đơn gần đây"));
        
        String[] columnNames = {"Mã HĐ", "Khách hàng", "Ngày", "Tổng tiền"};
        DefaultTableModel recentModel = new DefaultTableModel(columnNames, 0);
        JTable recentTable = new JTable(recentModel);
        
        List<HoaDon> recentInvoices = quanLyHoaDon.layTatCa();
        if (recentInvoices.size() > 5) {
            recentInvoices = recentInvoices.subList(0, 5);
        }
        
        for (HoaDon hd : recentInvoices) {
            recentModel.addRow(new Object[]{
                hd.getMaHoaDon(),
                hd.getKhachHang().getHoTen(),
                new SimpleDateFormat("dd/MM/yyyy").format(hd.getNgayLap()),
                String.format("%,.0f VND", hd.getTongTien())
            });
        }
        
        JScrollPane scrollPane = new JScrollPane(recentTable);
        recentPanel.add(scrollPane, BorderLayout.CENTER);
        
        dashboardPanel.add(titleLabel, BorderLayout.NORTH);
        dashboardPanel.add(statsPanel, BorderLayout.CENTER);
        dashboardPanel.add(recentPanel, BorderLayout.SOUTH);
        
        mainContentPanel.add(dashboardPanel, "DASHBOARD");
    }
    
    private JPanel taoTheThongKe(String icon, String title, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(color);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color.darker(), 2),
            BorderFactory.createEmptyBorder(16, 16, 12, 16)
        ));
        card.setPreferredSize(new Dimension(220, 110));

        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.setBackground(color);
        textPanel.setBorder(BorderFactory.createEmptyBorder(2, 6, 2, 2));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        titleLabel.setForeground(Color.WHITE);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        valueLabel.setForeground(Color.WHITE);

        textPanel.add(titleLabel);
        textPanel.add(valueLabel);

        card.add(textPanel, BorderLayout.CENTER);

        return card;
    }
    
    private void taoQuanLySach() {
        JPanel bookPanel = new JPanel(new BorderLayout());
        bookPanel.setBackground(Color.WHITE);
        
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        JLabel titleLabel = new JLabel("Quản lý Sách");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton btnAdd = taoNutChinh("+ Thêm sách mới");
        JButton btnRefresh = taoNutPhu("Làm mới");
        
        btnAdd.addActionListener(e -> hienThiThemSachDialog());
        btnRefresh.addActionListener(e -> capNhatBangSach());
        
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnAdd);
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(buttonPanel, BorderLayout.EAST);
        
        String[] columnNames = {"Mã sách", "Tên sách", "Tác giả", "Thể loại", "Giá bán", "Tồn kho"};
        modelSach = new DefaultTableModel(columnNames, 0);
        
        tableSach = new JTable(modelSach);
        tableSach.setRowHeight(40);
        
        JScrollPane scrollPane = new JScrollPane(tableSach);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách sách"));
        
        bookPanel.add(headerPanel, BorderLayout.NORTH);
        bookPanel.add(scrollPane, BorderLayout.CENTER);
        
        mainContentPanel.add(bookPanel, "BOOKS");
        capNhatBangSach();
    }
    
    private void taoQuanLyKhachHang() {
        JPanel customerPanel = new JPanel(new BorderLayout());
        customerPanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("Quản lý Khách hàng");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        String[] columnNames = {"Mã KH", "Họ tên", "Điện thoại", "Email"};
        modelKhachHang = new DefaultTableModel(columnNames, 0);
        tableKhachHang = new JTable(modelKhachHang);
        
        JScrollPane scrollPane = new JScrollPane(tableKhachHang);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách khách hàng"));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAdd = taoNutChinh("+ Thêm khách hàng");
        JButton btnRefresh = taoNutPhu("Làm mới");
        
        btnAdd.addActionListener(e -> hienThiThemKhachHangDialog());
        btnRefresh.addActionListener(e -> capNhatBangKhachHang());
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnRefresh);
        
        customerPanel.add(titleLabel, BorderLayout.NORTH);
        customerPanel.add(scrollPane, BorderLayout.CENTER);
        customerPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        mainContentPanel.add(customerPanel, "CUSTOMERS");
        capNhatBangKhachHang();
    }
    
    private void taoQuanLyHoaDon() {
        JPanel invoicePanel = new JPanel(new BorderLayout());
        invoicePanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("Quản lý Hóa đơn");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        String[] columnNames = {"Mã HĐ", "Ngày lập", "Khách hàng", "Tổng tiền"};
        modelHoaDon = new DefaultTableModel(columnNames, 0);
        tableHoaDon = new JTable(modelHoaDon);
        
        JScrollPane scrollPane = new JScrollPane(tableHoaDon);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách hóa đơn"));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAdd = taoNutChinh("+ Tạo hóa đơn mới");
        JButton btnRefresh = taoNutPhu("Làm mới");
        
        btnAdd.addActionListener(e -> hienThiTaoHoaDonDialog());
        btnRefresh.addActionListener(e -> capNhatBangHoaDon());
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnRefresh);
        
        invoicePanel.add(titleLabel, BorderLayout.NORTH);
        invoicePanel.add(scrollPane, BorderLayout.CENTER);
        invoicePanel.add(buttonPanel, BorderLayout.SOUTH);
        
        mainContentPanel.add(invoicePanel, "INVOICES");
        capNhatBangHoaDon();
    }
    
    private void taoThongKe() {
        JPanel statsPanel = new JPanel(new BorderLayout());
        statsPanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("Thống kê & Báo cáo");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        JPanel chartPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        chartPanel.setBackground(Color.WHITE);
        
        chartPanel.add(taoTheBieuDo("Doanh thu theo tháng", "Biểu đồ doanh thu"));
        chartPanel.add(taoTheBieuDo("Sách bán chạy", "Top 5 sách bán chạy"));
        chartPanel.add(taoTheBieuDo("Khách hàng thân thiết", "Top khách hàng"));
        chartPanel.add(taoTheBieuDo("Tồn kho", "Cảnh báo tồn kho"));
        
        JPanel reportPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnMonthly = taoNutChinh("Báo cáo tháng");
        JButton btnCustom = taoNutPhu("Báo cáo tùy chỉnh");
        
        btnMonthly.addActionListener(e -> hienThiBaoCaoThang());
        btnCustom.addActionListener(e -> hienThiBaoCaoTuyChinh());
        
        reportPanel.add(btnMonthly);
        reportPanel.add(btnCustom);
        
        statsPanel.add(titleLabel, BorderLayout.NORTH);
        statsPanel.add(chartPanel, BorderLayout.CENTER);
        statsPanel.add(reportPanel, BorderLayout.SOUTH);
        
        mainContentPanel.add(statsPanel, "STATISTICS");
    }
    
    private JPanel taoTheBieuDo(String title, String content) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(226, 230, 234)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        
        JLabel contentLabel = new JLabel(content);
        contentLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        contentLabel.setForeground(Color.GRAY);
        
        card.add(titleLabel, BorderLayout.NORTH);
        card.add(contentLabel, BorderLayout.CENTER);
        
        return card;
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
    
    private void hienThiTrangChu() {
        cardLayout.show(mainContentPanel, "DASHBOARD");
    }
    
    private void hienThiQuanLySach() {
        capNhatBangSach();
        cardLayout.show(mainContentPanel, "BOOKS");
    }
    
    private void hienThiQuanLyKhachHang() {
        capNhatBangKhachHang();
        cardLayout.show(mainContentPanel, "CUSTOMERS");
    }
    
    private void hienThiQuanLyHoaDon() {
        capNhatBangHoaDon();
        cardLayout.show(mainContentPanel, "INVOICES");
    }
    
    private void hienThiThongKe() {
        cardLayout.show(mainContentPanel, "STATISTICS");
    }
    
    private void capNhatBangSach() {
        modelSach.setRowCount(0);
        for (Sach sach : quanLySach.layTatCa()) {
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
    
    private void capNhatBangKhachHang() {
        modelKhachHang.setRowCount(0);
        for (KhachHang kh : quanLyKhachHang.layTatCa()) {
            modelKhachHang.addRow(new Object[]{
                kh.getMaKhachHang(),
                kh.getHoTen(),
                kh.getDienThoai(),
                kh.getEmail()
            });
        }
    }
    
    private void capNhatBangHoaDon() {
        modelHoaDon.setRowCount(0);
        for (HoaDon hd : quanLyHoaDon.layTatCa()) {
            modelHoaDon.addRow(new Object[]{
                hd.getMaHoaDon(),
                new SimpleDateFormat("dd/MM/yyyy").format(hd.getNgayLap()),
                hd.getKhachHang().getHoTen(),
                String.format("%,.0f VND", hd.getTongTien())
            });
        }
    }
    
    private void hienThiThemSachDialog() {
        JDialog dialog = new JDialog(this, "Thêm sách mới", true);
        dialog.setSize(400, 500);
        dialog.setLocationRelativeTo(this);
        dialog.getContentPane().setBackground(Color.WHITE);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("Thông tin sách mới");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBackground(Color.WHITE);
        
        JLabel lblMaSach = new JLabel("Mã sách:");
        JTextField txtMaSach = new JTextField();
        
        JLabel lblTen = new JLabel("Tên sách:");
        JTextField txtTen = new JTextField();
        
        JLabel lblTacGia = new JLabel("Tác giả:");
        JTextField txtTacGia = new JTextField();
        
        JLabel lblTheLoai = new JLabel("Thể loại:");
        JTextField txtTheLoai = new JTextField();
        
        JLabel lblGia = new JLabel("Giá bán:");
        JTextField txtGia = new JTextField();
        
        JLabel lblSoLuong = new JLabel("Số lượng tồn:");
        JTextField txtSoLuong = new JTextField();
        
        formPanel.add(lblMaSach); formPanel.add(txtMaSach);
        formPanel.add(lblTen); formPanel.add(txtTen);
        formPanel.add(lblTacGia); formPanel.add(txtTacGia);
        formPanel.add(lblTheLoai); formPanel.add(txtTheLoai);
        formPanel.add(lblGia); formPanel.add(txtGia);
        formPanel.add(lblSoLuong); formPanel.add(txtSoLuong);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton btnSave = taoNutChinh("Lưu sách");
        JButton btnCancel = taoNutPhu("Hủy");
        
        btnSave.addActionListener(e -> {
            try {
                String maSach = txtMaSach.getText();
                String ten = txtTen.getText();
                String tacGia = txtTacGia.getText();
                String theLoai = txtTheLoai.getText();
                double giaBan = Double.parseDouble(txtGia.getText());
                int soLuongTon = Integer.parseInt(txtSoLuong.getText());
                
                if (maSach.isEmpty() || ten.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng nhập đầy đủ thông tin!");
                    return;
                }
                
                Sach sach = new Sach(maSach, ten, tacGia, theLoai, giaBan, soLuongTon);
                quanLySach.them(sach);
                capNhatBangSach();
                
                JOptionPane.showMessageDialog(dialog, "Thêm sách thành công!");
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Vui lòng nhập số hợp lệ cho giá và số lượng!");
            }
        });
        
        btnCancel.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(btnCancel);
        buttonPanel.add(btnSave);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    private void hienThiThemKhachHangDialog() {
        JDialog dialog = new JDialog(this, "Thêm khách hàng mới", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        dialog.getContentPane().setBackground(Color.WHITE);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("Thông tin khách hàng");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBackground(Color.WHITE);
        
        JLabel lblMaKH = new JLabel("Mã KH:");
        JTextField txtMaKH = new JTextField();
        
        JLabel lblHoTen = new JLabel("Họ tên:");
        JTextField txtHoTen = new JTextField();
        
        JLabel lblDienThoai = new JLabel("Điện thoại:");
        JTextField txtDienThoai = new JTextField();
        
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        
        formPanel.add(lblMaKH); formPanel.add(txtMaKH);
        formPanel.add(lblHoTen); formPanel.add(txtHoTen);
        formPanel.add(lblDienThoai); formPanel.add(txtDienThoai);
        formPanel.add(lblEmail); formPanel.add(txtEmail);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton btnSave = taoNutChinh("Lưu khách hàng");
        JButton btnCancel = taoNutPhu("Hủy");
        
        btnSave.addActionListener(e -> {
            String maKH = txtMaKH.getText();
            String hoTen = txtHoTen.getText();
            String dienThoai = txtDienThoai.getText();
            String email = txtEmail.getText();
            
            if (maKH.isEmpty() || hoTen.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }
            
            KhachHang kh = new KhachHang(maKH, hoTen, dienThoai, email);
            quanLyKhachHang.them(kh);
            capNhatBangKhachHang();
            
            JOptionPane.showMessageDialog(dialog, "Thêm khách hàng thành công!");
            dialog.dispose();
        });
        
        btnCancel.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(btnCancel);
        buttonPanel.add(btnSave);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    private void hienThiTaoHoaDonDialog() {
        JOptionPane.showMessageDialog(this, "Chức năng tạo hóa đơn đang được phát triển...");
    }
    
    private void hienThiBaoCaoThang() {
        JOptionPane.showMessageDialog(this, "Chức năng báo cáo tháng đang được phát triển...");
    }
    
    private void hienThiBaoCaoTuyChinh() {
        JOptionPane.showMessageDialog(this, "Chức năng báo cáo tùy chỉnh đang được phát triển...");
    }
}