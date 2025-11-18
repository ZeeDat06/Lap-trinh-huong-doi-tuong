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
    private JPanel statsPanel;
    private JPanel statsChartPanel;
    
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
    
    private void capNhatTrangTongQuan() {
        // Xóa panel cũ và tạo lại panel tổng quan (chỉ cập nhật dữ liệu, không chuyển hướng)
        for (Component comp : mainContentPanel.getComponents()) {
            if (comp instanceof JPanel) {
                String name = ((JPanel) comp).getName();
                if ("DASHBOARD".equals(name)) {
                    mainContentPanel.remove(comp);
                    break;
                }
            }
        }
        taoTrangChu();
        // Không gọi cardLayout.show() để người dùng vẫn ở lại trang hiện tại
    }
    
    private void taoTrangChu() {
        JPanel dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.setBackground(Color.WHITE);
        dashboardPanel.setName("DASHBOARD");
        
        JLabel titleLabel = new JLabel("Tổng quan hệ thống");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        JPanel dashboardStatsPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        dashboardStatsPanel.setBackground(Color.WHITE);
        
        JPanel card1 = taoTheThongKe("", "Tổng sách", String.valueOf(quanLySach.layTatCa().size()), new Color(41, 128, 185));
        JPanel card2 = taoTheThongKe("", "Khách hàng", String.valueOf(quanLyKhachHang.layTatCa().size()), new Color(39, 174, 96));
        JPanel card3 = taoTheThongKe("", "Hóa đơn", String.valueOf(quanLyHoaDon.layTatCa().size()), new Color(243, 156, 18));
        
        double totalRevenue = quanLyHoaDon.layTatCa().stream()
                .mapToDouble(HoaDon::getTongTien)
                .sum();
        JPanel card4 = taoTheThongKe("", "Doanh thu", String.format("%,.0f VND", totalRevenue), new Color(231, 76, 60));
        
        dashboardStatsPanel.add(card1);
        dashboardStatsPanel.add(card2);
        dashboardStatsPanel.add(card3);
        dashboardStatsPanel.add(card4);
        
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
        dashboardPanel.add(dashboardStatsPanel, BorderLayout.CENTER);
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
        JButton btnEdit = taoNutChinh("Sửa sách");
        JButton btnDelete = taoNutPhu("Xóa sách");
        JButton btnRefresh = taoNutPhu("Làm mới");
        
        btnAdd.addActionListener(e -> hienThiThemSachDialog());
        btnEdit.addActionListener(e -> hienThiSuaSachDialog());
        btnDelete.addActionListener(e -> xoaSach());
        btnRefresh.addActionListener(e -> capNhatBangSach());
        
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnAdd);
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(buttonPanel, BorderLayout.EAST);
        
        // Panel tìm kiếm và sắp xếp
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        JLabel lblTimKiem = new JLabel("Tìm kiếm:");
        JTextField txtTimKiem = new JTextField(20);
        JComboBox<String> cboTimKiem = new JComboBox<>(new String[]{"Tên sách", "Tác giả", "Thể loại"});
        JButton btnTimKiem = taoNutPhu("Tìm");
        
        JLabel lblSapXep = new JLabel("  Sắp xếp:");
        JComboBox<String> cboSapXep = new JComboBox<>(new String[]{"Mã sách", "Tên sách", "Giá bán", "Tồn kho"});
        JButton btnSapXep = taoNutPhu("Sắp xếp");
        
        btnTimKiem.addActionListener(e -> {
            String tuKhoa = txtTimKiem.getText().trim().toLowerCase();
            String tieuChi = cboTimKiem.getSelectedItem().toString();
            timKiemSach(tuKhoa, tieuChi);
        });
        
        btnSapXep.addActionListener(e -> {
            String tieuChi = cboSapXep.getSelectedItem().toString();
            sapXepSach(tieuChi);
        });
        
        searchPanel.add(lblTimKiem);
        searchPanel.add(txtTimKiem);
        searchPanel.add(cboTimKiem);
        searchPanel.add(btnTimKiem);
        searchPanel.add(lblSapXep);
        searchPanel.add(cboSapXep);
        searchPanel.add(btnSapXep);
        
        String[] columnNames = {"Mã sách", "Tên sách", "Tác giả", "Thể loại", "Giá bán", "Tồn kho"};
        modelSach = new DefaultTableModel(columnNames, 0);
        
        tableSach = new JTable(modelSach);
        tableSach.setRowHeight(40);
        
        JScrollPane scrollPane = new JScrollPane(tableSach);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách sách"));
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.SOUTH);
        
        bookPanel.add(topPanel, BorderLayout.NORTH);
        bookPanel.add(scrollPane, BorderLayout.CENTER);
        
        mainContentPanel.add(bookPanel, "BOOKS");
        capNhatBangSach();
    }
    
    private void taoQuanLyKhachHang() {
        JPanel customerPanel = new JPanel(new BorderLayout());
        customerPanel.setBackground(Color.WHITE);
        
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        JLabel titleLabel = new JLabel("Quản lý Khách hàng");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        JButton btnAdd = taoNutChinh("+ Thêm khách hàng");
        JButton btnEdit = taoNutChinh("Sửa khách hàng");
        JButton btnDelete = taoNutPhu("Xóa khách hàng");
        JButton btnRefresh = taoNutPhu("Làm mới");
        
        btnAdd.addActionListener(e -> hienThiThemKhachHangDialog());
        btnEdit.addActionListener(e -> hienThiSuaKhachHangDialog());
        btnDelete.addActionListener(e -> xoaKhachHang());
        btnRefresh.addActionListener(e -> capNhatBangKhachHang());
        
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnAdd);
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(buttonPanel, BorderLayout.EAST);
        
        // Panel tìm kiếm và sắp xếp
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        JLabel lblTimKiem = new JLabel("Tìm kiếm:");
        JTextField txtTimKiem = new JTextField(20);
        JComboBox<String> cboTimKiem = new JComboBox<>(new String[]{"Họ tên", "Điện thoại", "Email"});
        JButton btnTimKiem = taoNutPhu("Tìm");
        
        JLabel lblSapXep = new JLabel("  Sắp xếp:");
        JComboBox<String> cboSapXep = new JComboBox<>(new String[]{"Mã KH", "Họ tên"});
        JButton btnSapXep = taoNutPhu("Sắp xếp");
        
        btnTimKiem.addActionListener(e -> {
            String tuKhoa = txtTimKiem.getText().trim().toLowerCase();
            String tieuChi = cboTimKiem.getSelectedItem().toString();
            timKiemKhachHang(tuKhoa, tieuChi);
        });
        
        btnSapXep.addActionListener(e -> {
            String tieuChi = cboSapXep.getSelectedItem().toString();
            sapXepKhachHang(tieuChi);
        });
        
        searchPanel.add(lblTimKiem);
        searchPanel.add(txtTimKiem);
        searchPanel.add(cboTimKiem);
        searchPanel.add(btnTimKiem);
        searchPanel.add(lblSapXep);
        searchPanel.add(cboSapXep);
        searchPanel.add(btnSapXep);
        
        String[] columnNames = {"Mã KH", "Họ tên", "Điện thoại", "Email"};
        modelKhachHang = new DefaultTableModel(columnNames, 0);
        tableKhachHang = new JTable(modelKhachHang);
        tableKhachHang.setRowHeight(40);
        
        JScrollPane scrollPane = new JScrollPane(tableKhachHang);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách khách hàng"));
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.SOUTH);
        
        customerPanel.add(topPanel, BorderLayout.NORTH);
        customerPanel.add(scrollPane, BorderLayout.CENTER);
        
        mainContentPanel.add(customerPanel, "CUSTOMERS");
        capNhatBangKhachHang();
    }
    
    private void taoQuanLyHoaDon() {
        JPanel invoicePanel = new JPanel(new BorderLayout());
        invoicePanel.setBackground(Color.WHITE);
        
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        JLabel titleLabel = new JLabel("Quản lý Hóa đơn");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        JButton btnAdd = taoNutChinh("+ Tạo hóa đơn mới");
        JButton btnEdit = taoNutChinh("Sửa hóa đơn");
        JButton btnDelete = taoNutPhu("Xóa hóa đơn");
        JButton btnRefresh = taoNutPhu("Làm mới");
        
        btnAdd.addActionListener(e -> hienThiTaoHoaDonDialog());
        btnEdit.addActionListener(e -> hienThiSuaHoaDonDialog());
        btnDelete.addActionListener(e -> xoaHoaDon());
        btnRefresh.addActionListener(e -> capNhatBangHoaDon());
        
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnAdd);
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(buttonPanel, BorderLayout.EAST);
        
        // Panel tìm kiếm và sắp xếp
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        JLabel lblTimKiem = new JLabel("Tìm kiếm:");
        JTextField txtTimKiem = new JTextField(20);
        JComboBox<String> cboTimKiem = new JComboBox<>(new String[]{"Mã hóa đơn", "Khách hàng"});
        JButton btnTimKiem = taoNutPhu("Tìm");
        
        JLabel lblSapXep = new JLabel("  Sắp xếp:");
        JComboBox<String> cboSapXep = new JComboBox<>(new String[]{"Mã HĐ", "Ngày lập", "Tổng tiền"});
        JButton btnSapXep = taoNutPhu("Sắp xếp");
        
        btnTimKiem.addActionListener(e -> {
            String tuKhoa = txtTimKiem.getText().trim().toLowerCase();
            String tieuChi = cboTimKiem.getSelectedItem().toString();
            timKiemHoaDon(tuKhoa, tieuChi);
        });
        
        btnSapXep.addActionListener(e -> {
            String tieuChi = cboSapXep.getSelectedItem().toString();
            sapXepHoaDon(tieuChi);
        });
        
        searchPanel.add(lblTimKiem);
        searchPanel.add(txtTimKiem);
        searchPanel.add(cboTimKiem);
        searchPanel.add(btnTimKiem);
        searchPanel.add(lblSapXep);
        searchPanel.add(cboSapXep);
        searchPanel.add(btnSapXep);
        
        String[] columnNames = {"Mã HĐ", "Khách hàng", "Ngày lập", "Tổng tiền"};
        modelHoaDon = new DefaultTableModel(columnNames, 0);
        tableHoaDon = new JTable(modelHoaDon);
        tableHoaDon.setRowHeight(40);
        
        tableHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int row = tableHoaDon.getSelectedRow();
                    if (row >= 0) {
                        String maHD = modelHoaDon.getValueAt(row, 0).toString();
                        hienThiChiTietHoaDon(maHD);
                    }
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(tableHoaDon);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách hóa đơn"));
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.SOUTH);
        
        invoicePanel.add(topPanel, BorderLayout.NORTH);
        invoicePanel.add(scrollPane, BorderLayout.CENTER);
        
        mainContentPanel.add(invoicePanel, "INVOICES");
        capNhatBangHoaDon();
    }
    
    private void taoThongKe() {
        statsPanel = new JPanel(new BorderLayout());
        statsPanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("Thống kê & Báo cáo");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        statsChartPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        statsChartPanel.setBackground(Color.WHITE);
        
        // Tính doanh thu tháng hiện tại
        java.util.Calendar cal = java.util.Calendar.getInstance();
        int thangHienTai = cal.get(java.util.Calendar.MONTH) + 1;
        int namHienTai = cal.get(java.util.Calendar.YEAR);
        double doanhThuThang = timKiemThongKe.tinhDoanhThuTheoThang(thangHienTai, namHienTai);
        
        // Đếm sách sắp hết hàng (số lượng < 10)
        long sachSapHet = quanLySach.layTatCa().stream().filter(s -> s.getSoLuongTon() < 10).count();
        
        // Đếm khách hàng VIP
        long soKHVIP = quanLyKhachHang.layTatCa().stream().filter(kh -> kh.isVIP()).count();
        
        statsChartPanel.add(taoTheBieuDo("Doanh thu tháng " + thangHienTai + "/" + namHienTai, 
            String.format("%,.0f VND", doanhThuThang)));
        statsChartPanel.add(taoTheBieuDo("Tổng số sách", 
            quanLySach.layTatCa().size() + " đầu sách"));
        statsChartPanel.add(taoTheBieuDo("Khách hàng VIP", 
            soKHVIP + " / " + quanLyKhachHang.layTatCa().size() + " khách hàng"));
        statsChartPanel.add(taoTheBieuDo("Cảnh báo tồn kho", 
            sachSapHet + " sách sắp hết hàng"));
        
        JPanel reportPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnMonthly = taoNutChinh("Báo cáo tháng");
        JButton btnCustom = taoNutPhu("Báo cáo tùy chỉnh");
        
        btnMonthly.addActionListener(e -> hienThiBaoCaoThang());
        btnCustom.addActionListener(e -> hienThiBaoCaoTuyChinh());
        
        reportPanel.add(btnMonthly);
        reportPanel.add(btnCustom);
        
        statsPanel.add(titleLabel, BorderLayout.NORTH);
        statsPanel.add(statsChartPanel, BorderLayout.CENTER);
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
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        JLabel contentLabel = new JLabel("<html><div style='text-align: center;'><span style='font-size: 20px; font-weight: bold; color: #2980b9;'>" + content + "</span></div></html>");
        contentLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        contentLabel.setHorizontalAlignment(JLabel.CENTER);
        
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
        capNhatThongKe();
        cardLayout.show(mainContentPanel, "STATISTICS");
    }
    
    private void capNhatThongKe() {
        // Xóa các thẻ cũ
        statsChartPanel.removeAll();
        
        // Tính doanh thu tháng hiện tại
        java.util.Calendar cal = java.util.Calendar.getInstance();
        int thangHienTai = cal.get(java.util.Calendar.MONTH) + 1;
        int namHienTai = cal.get(java.util.Calendar.YEAR);
        double doanhThuThang = timKiemThongKe.tinhDoanhThuTheoThang(thangHienTai, namHienTai);
        
        // Đếm sách sắp hết hàng (số lượng < 10)
        long sachSapHet = quanLySach.layTatCa().stream().filter(s -> s.getSoLuongTon() < 10).count();
        
        // Đếm khách hàng VIP
        long soKHVIP = quanLyKhachHang.layTatCa().stream().filter(kh -> kh.isVIP()).count();
        
        // Thêm các thẻ mới với dữ liệu cập nhật
        statsChartPanel.add(taoTheBieuDo("Doanh thu tháng " + thangHienTai + "/" + namHienTai, 
            String.format("%,.0f VND", doanhThuThang)));
        statsChartPanel.add(taoTheBieuDo("Tổng số sách", 
            quanLySach.layTatCa().size() + " đầu sách"));
        statsChartPanel.add(taoTheBieuDo("Khách hàng VIP", 
            soKHVIP + " / " + quanLyKhachHang.layTatCa().size() + " khách hàng"));
        statsChartPanel.add(taoTheBieuDo("Cảnh báo tồn kho", 
            sachSapHet + " sách sắp hết hàng"));
        
        // Cập nhật giao diện
        statsChartPanel.revalidate();
        statsChartPanel.repaint();
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
                hd.getKhachHang().getHoTen(),
                new SimpleDateFormat("dd/MM/yyyy").format(hd.getNgayLap()),
                String.format("%,.0f VND", hd.getTongTien())
            });
        }
    }
    
    // Phương thức tìm kiếm sách
    private void timKiemSach(String tuKhoa, String tieuChi) {
        modelSach.setRowCount(0);
        if (tuKhoa.isEmpty()) {
            capNhatBangSach();
            return;
        }
        
        for (Sach sach : quanLySach.layTatCa()) {
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
    
    // Phương thức sắp xếp sách
    private void sapXepSach(String tieuChi) {
        java.util.List<Sach> danhSach = new java.util.ArrayList<>(quanLySach.layTatCa());
        
        switch (tieuChi) {
            case "Mã sách":
                danhSach.sort((s1, s2) -> s1.getMaSach().compareTo(s2.getMaSach()));
                break;
            case "Tên sách":
                danhSach.sort((s1, s2) -> s1.getTen().compareTo(s2.getTen()));
                break;
            case "Giá bán":
                danhSach.sort((s1, s2) -> Double.compare(s1.getGiaBan(), s2.getGiaBan()));
                break;
            case "Tồn kho":
                danhSach.sort((s1, s2) -> Integer.compare(s1.getSoLuongTon(), s2.getSoLuongTon()));
                break;
        }
        
        modelSach.setRowCount(0);
        for (Sach sach : danhSach) {
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
    
    // Phương thức tìm kiếm khách hàng
    private void timKiemKhachHang(String tuKhoa, String tieuChi) {
        modelKhachHang.setRowCount(0);
        if (tuKhoa.isEmpty()) {
            capNhatBangKhachHang();
            return;
        }
        
        for (KhachHang kh : quanLyKhachHang.layTatCa()) {
            boolean khopTuKhoa = false;
            switch (tieuChi) {
                case "Họ tên":
                    khopTuKhoa = kh.getHoTen().toLowerCase().contains(tuKhoa);
                    break;
                case "Điện thoại":
                    khopTuKhoa = kh.getDienThoai().toLowerCase().contains(tuKhoa);
                    break;
                case "Email":
                    khopTuKhoa = kh.getEmail().toLowerCase().contains(tuKhoa);
                    break;
            }
            
            if (khopTuKhoa) {
                modelKhachHang.addRow(new Object[]{
                    kh.getMaKhachHang(),
                    kh.getHoTen(),
                    kh.getDienThoai(),
                    kh.getEmail()
                });
            }
        }
    }
    
    // Phương thức sắp xếp khách hàng
    private void sapXepKhachHang(String tieuChi) {
        java.util.List<KhachHang> danhSach = new java.util.ArrayList<>(quanLyKhachHang.layTatCa());
        
        switch (tieuChi) {
            case "Mã KH":
                danhSach.sort((k1, k2) -> k1.getMaKhachHang().compareTo(k2.getMaKhachHang()));
                break;
            case "Họ tên":
                danhSach.sort((k1, k2) -> k1.getHoTen().compareTo(k2.getHoTen()));
                break;
        }
        
        modelKhachHang.setRowCount(0);
        for (KhachHang kh : danhSach) {
            modelKhachHang.addRow(new Object[]{
                kh.getMaKhachHang(),
                kh.getHoTen(),
                kh.getDienThoai(),
                kh.getEmail()
            });
        }
    }
    
    // Phương thức tìm kiếm hóa đơn
    private void timKiemHoaDon(String tuKhoa, String tieuChi) {
        modelHoaDon.setRowCount(0);
        if (tuKhoa.isEmpty()) {
            capNhatBangHoaDon();
            return;
        }
        
        for (HoaDon hd : quanLyHoaDon.layTatCa()) {
            boolean khopTuKhoa = false;
            switch (tieuChi) {
                case "Mã hóa đơn":
                    khopTuKhoa = hd.getMaHoaDon().toLowerCase().contains(tuKhoa);
                    break;
                case "Khách hàng":
                    khopTuKhoa = hd.getKhachHang().getHoTen().toLowerCase().contains(tuKhoa);
                    break;
            }
            
            if (khopTuKhoa) {
                modelHoaDon.addRow(new Object[]{
                    hd.getMaHoaDon(),
                    hd.getKhachHang().getHoTen(),
                    new SimpleDateFormat("dd/MM/yyyy").format(hd.getNgayLap()),
                    String.format("%,.0f VND", hd.getTongTien())
                });
            }
        }
    }
    
    // Phương thức sắp xếp hóa đơn
    private void sapXepHoaDon(String tieuChi) {
        java.util.List<HoaDon> danhSach = new java.util.ArrayList<>(quanLyHoaDon.layTatCa());
        
        switch (tieuChi) {
            case "Mã HĐ":
                danhSach.sort((h1, h2) -> h1.getMaHoaDon().compareTo(h2.getMaHoaDon()));
                break;
            case "Ngày lập":
                danhSach.sort((h1, h2) -> h1.getNgayLap().compareTo(h2.getNgayLap()));
                break;
            case "Tổng tiền":
                danhSach.sort((h1, h2) -> Double.compare(h1.getTongTien(), h2.getTongTien()));
                break;
        }
        
        modelHoaDon.setRowCount(0);
        for (HoaDon hd : danhSach) {
            modelHoaDon.addRow(new Object[]{
                hd.getMaHoaDon(),
                hd.getKhachHang().getHoTen(),
                new SimpleDateFormat("dd/MM/yyyy").format(hd.getNgayLap()),
                String.format("%,.0f VND", hd.getTongTien())
            });
        }
    }
    
    private void xoaSach() {
        int selectedRow = tableSach.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sách cần xóa!");
            return;
        }
        
        String maSach = modelSach.getValueAt(selectedRow, 0).toString();
        String tenSach = modelSach.getValueAt(selectedRow, 1).toString();
        
        // Kiểm tra xem sách có trong hóa đơn nào không
        boolean coDuLieuLienQuan = false;
        for (HoaDon hd : quanLyHoaDon.layTatCa()) {
            for (ChiTietHoaDon ct : hd.getDanhSachSachMua()) {
                if (ct.getSach().getMaSach().equals(maSach)) {
                    coDuLieuLienQuan = true;
                    break;
                }
            }
            if (coDuLieuLienQuan) break;
        }
        
        if (coDuLieuLienQuan) {
            JOptionPane.showMessageDialog(this, 
                "Không thể xóa sách này vì đã có trong hóa đơn!\nVui lòng xóa các hóa đơn liên quan trước.",
                "Cảnh báo", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc chắn muốn xóa sách:\n" + tenSach + " (" + maSach + ")?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            quanLySach.xoa(maSach);
            saveDuLieu();
            capNhatBangSach();
            capNhatTrangTongQuan();
            JOptionPane.showMessageDialog(this, "Xóa sách thành công!");
        }
    }
    
    private void hienThiSuaSachDialog() {
        int selectedRow = tableSach.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sách cần sửa!");
            return;
        }
        
        String maSach = modelSach.getValueAt(selectedRow, 0).toString();
        Sach sach = quanLySach.timKiemTheoMa(maSach);
        
        if (sach == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sách!");
            return;
        }
        
        JDialog dialog = new JDialog(this, "Sửa thông tin sách", true);
        dialog.setSize(400, 500);
        dialog.setLocationRelativeTo(this);
        dialog.getContentPane().setBackground(Color.WHITE);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("Sửa thông tin sách");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBackground(Color.WHITE);
        
        JLabel lblMaSach = new JLabel("Mã sách:");
        JTextField txtMaSach = new JTextField(sach.getMaSach());
        txtMaSach.setEditable(false);
        
        JLabel lblTen = new JLabel("Tên sách:");
        JTextField txtTen = new JTextField(sach.getTen());
        
        JLabel lblTacGia = new JLabel("Tác giả:");
        JTextField txtTacGia = new JTextField(sach.getTacGia());
        
        JLabel lblTheLoai = new JLabel("Thể loại:");
        JTextField txtTheLoai = new JTextField(sach.getTheLoai());
        
        JLabel lblGia = new JLabel("Giá bán:");
        JTextField txtGia = new JTextField(String.valueOf(sach.getGiaBan()));
        
        JLabel lblSoLuong = new JLabel("Số lượng tồn:");
        JTextField txtSoLuong = new JTextField(String.valueOf(sach.getSoLuongTon()));
        
        formPanel.add(lblMaSach); formPanel.add(txtMaSach);
        formPanel.add(lblTen); formPanel.add(txtTen);
        formPanel.add(lblTacGia); formPanel.add(txtTacGia);
        formPanel.add(lblTheLoai); formPanel.add(txtTheLoai);
        formPanel.add(lblGia); formPanel.add(txtGia);
        formPanel.add(lblSoLuong); formPanel.add(txtSoLuong);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton btnSave = taoNutChinh("Cập nhật");
        JButton btnCancel = taoNutPhu("Hủy");
        
        btnSave.addActionListener(e -> {
            try {
                String ten = txtTen.getText();
                String tacGia = txtTacGia.getText();
                String theLoai = txtTheLoai.getText();
                double giaBan = Double.parseDouble(txtGia.getText());
                int soLuongTon = Integer.parseInt(txtSoLuong.getText());
                
                if (ten.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng nhập tên sách!");
                    return;
                }
                
                sach.setTen(ten);
                sach.setTacGia(tacGia);
                sach.setTheLoai(theLoai);
                sach.setGiaBan(giaBan);
                sach.setSoLuongTon(soLuongTon);
                
                quanLySach.sua(sach);
                saveDuLieu();
                capNhatBangSach();
                capNhatTrangTongQuan();
                
                JOptionPane.showMessageDialog(dialog, "Cập nhật sách thành công!");
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
                
                // Kiểm tra mã sách đã tồn tại chưa
                if (quanLySach.timKiemTheoMa(maSach) != null) {
                    JOptionPane.showMessageDialog(dialog, "Mã sách đã tồn tại! Vui lòng nhập mã khác.");
                    return;
                }
                
                Sach sach = new Sach(maSach, ten, tacGia, theLoai, giaBan, soLuongTon);
                quanLySach.them(sach);
                saveDuLieu();
                capNhatBangSach();
                capNhatTrangTongQuan();
                
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
    
    private void xoaKhachHang() {
        int selectedRow = tableKhachHang.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần xóa!");
            return;
        }
        
        String maKH = modelKhachHang.getValueAt(selectedRow, 0).toString();
        String tenKH = modelKhachHang.getValueAt(selectedRow, 1).toString();
        
        // Kiểm tra xem khách hàng có hóa đơn nào không
        boolean coHoaDon = false;
        for (HoaDon hd : quanLyHoaDon.layTatCa()) {
            if (hd.getKhachHang().getMaKhachHang().equals(maKH)) {
                coHoaDon = true;
                break;
            }
        }
        
        if (coHoaDon) {
            JOptionPane.showMessageDialog(this, 
                "Không thể xóa khách hàng này vì đã có hóa đơn!\nVui lòng xóa các hóa đơn của khách hàng trước.",
                "Cảnh báo", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc chắn muốn xóa khách hàng:\n" + tenKH + " (" + maKH + ")?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            quanLyKhachHang.xoa(maKH);
            saveDuLieu();
            capNhatBangKhachHang();
            capNhatTrangTongQuan();
            JOptionPane.showMessageDialog(this, "Xóa khách hàng thành công!");
        }
    }
    
    private void hienThiSuaKhachHangDialog() {
        int selectedRow = tableKhachHang.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần sửa!");
            return;
        }
        
        String maKH = modelKhachHang.getValueAt(selectedRow, 0).toString();
        KhachHang kh = quanLyKhachHang.timKiemTheoMa(maKH);
        
        if (kh == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng!");
            return;
        }
        
        JDialog dialog = new JDialog(this, "Sửa thông tin khách hàng", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        dialog.getContentPane().setBackground(Color.WHITE);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("Sửa thông tin khách hàng");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBackground(Color.WHITE);
        
        JLabel lblMaKH = new JLabel("Mã KH:");
        JTextField txtMaKH = new JTextField(kh.getMaKhachHang());
        txtMaKH.setEditable(false);
        
        JLabel lblHoTen = new JLabel("Họ tên:");
        JTextField txtHoTen = new JTextField(kh.getHoTen());
        
        JLabel lblDienThoai = new JLabel("Điện thoại:");
        JTextField txtDienThoai = new JTextField(kh.getDienThoai());
        
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField(kh.getEmail());
        
        formPanel.add(lblMaKH); formPanel.add(txtMaKH);
        formPanel.add(lblHoTen); formPanel.add(txtHoTen);
        formPanel.add(lblDienThoai); formPanel.add(txtDienThoai);
        formPanel.add(lblEmail); formPanel.add(txtEmail);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton btnSave = taoNutChinh("Cập nhật");
        JButton btnCancel = taoNutPhu("Hủy");
        
        btnSave.addActionListener(e -> {
            String hoTen = txtHoTen.getText();
            String dienThoai = txtDienThoai.getText();
            String email = txtEmail.getText();
            
            if (hoTen.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Vui lòng nhập họ tên!");
                return;
            }
            
            kh.setHoTen(hoTen);
            kh.setDienThoai(dienThoai);
            kh.setEmail(email);
            
            quanLyKhachHang.sua(kh);
            saveDuLieu();
            capNhatBangKhachHang();
            capNhatTrangTongQuan();
            
            JOptionPane.showMessageDialog(dialog, "Cập nhật khách hàng thành công!");
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
            
            // Kiểm tra mã khách hàng đã tồn tại chưa
            if (quanLyKhachHang.timKiemTheoMa(maKH) != null) {
                JOptionPane.showMessageDialog(dialog, "Mã khách hàng đã tồn tại! Vui lòng nhập mã khác.");
                return;
            }
            
            KhachHang kh = new KhachHang(maKH, hoTen, dienThoai, email);
            quanLyKhachHang.them(kh);
            saveDuLieu();
            capNhatBangKhachHang();
            capNhatTrangTongQuan();
            
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
        JDialog dialog = new JDialog(this, "Tạo hóa đơn mới", true);
        dialog.setSize(900, 600);
        dialog.setLocationRelativeTo(this);
        dialog.getContentPane().setBackground(Color.WHITE);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        
        // Header
        JLabel titleLabel = new JLabel("Thông tin hóa đơn");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        
        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBackground(Color.WHITE);
        
        JLabel lblMaHD = new JLabel("Mã hóa đơn:");
        JTextField txtMaHD = new JTextField("HD" + System.currentTimeMillis());
        txtMaHD.setEditable(false);
        
        JLabel lblNgay = new JLabel("Ngày lập:");
        JTextField txtNgay = new JTextField(new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date()));
        txtNgay.setEditable(false);
        
        JLabel lblKhachHang = new JLabel("Khách hàng:");
        JComboBox<String> cboKhachHang = new JComboBox<>();
        for (KhachHang kh : quanLyKhachHang.layTatCa()) {
            cboKhachHang.addItem(kh.getMaKhachHang() + " - " + kh.getHoTen());
        }
        
        formPanel.add(lblMaHD); formPanel.add(txtMaHD);
        formPanel.add(lblNgay); formPanel.add(txtNgay);
        formPanel.add(lblKhachHang); formPanel.add(cboKhachHang);
        
        // Giỏ hàng
        JPanel cartPanel = new JPanel(new BorderLayout(5, 5));
        cartPanel.setBackground(Color.WHITE);
        cartPanel.setBorder(BorderFactory.createTitledBorder("Giỏ hàng"));
        
        String[] cartColumns = {"Mã sách", "Tên sách", "Số lượng", "Đơn giá", "Thành tiền"};
        DefaultTableModel cartModel = new DefaultTableModel(cartColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2; // Chỉ cho phép sửa số lượng
            }
        };
        JTable cartTable = new JTable(cartModel);
        JScrollPane cartScroll = new JScrollPane(cartTable);
        
        JPanel cartButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cartButtonPanel.setBackground(Color.WHITE);
        JButton btnThemSach = taoNutChinh("+ Thêm sách");
        JButton btnXoaSach = taoNutPhu("- Xóa sách");
        
        cartButtonPanel.add(btnThemSach);
        cartButtonPanel.add(btnXoaSach);
        
        cartPanel.add(cartScroll, BorderLayout.CENTER);
        cartPanel.add(cartButtonPanel, BorderLayout.SOUTH);
        
        // Tổng tiền
        JPanel tongTienPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        tongTienPanel.setBackground(Color.WHITE);
        JLabel lblTongTien = new JLabel("Tổng tiền: 0 VND");
        lblTongTien.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTongTien.setForeground(new Color(231, 76, 60));
        tongTienPanel.add(lblTongTien);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        JButton btnLuu = taoNutChinh("Lưu hóa đơn");
        JButton btnHuy = taoNutPhu("Hủy");
        
        // Xử lý thêm sách
        btnThemSach.addActionListener(e -> {
            JDialog sachDialog = new JDialog(dialog, "Chọn sách", true);
            sachDialog.setSize(600, 400);
            sachDialog.setLocationRelativeTo(dialog);
            
            JPanel sachPanel = new JPanel(new BorderLayout(10, 10));
            sachPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            
            String[] sachCols = {"Mã", "Tên", "Giá", "Tồn kho"};
            DefaultTableModel sachModel = new DefaultTableModel(sachCols, 0);
            for (Sach s : quanLySach.layTatCa()) {
                sachModel.addRow(new Object[]{s.getMaSach(), s.getTen(), 
                    String.format("%,.0f", s.getGiaBan()), s.getSoLuongTon()});
            }
            
            JTable sachTable = new JTable(sachModel);
            JScrollPane sachScroll = new JScrollPane(sachTable);
            
            JPanel inputPanel = new JPanel(new FlowLayout());
            JLabel lblSoLuong = new JLabel("Số lượng:");
            JTextField txtSoLuong = new JTextField("1", 5);
            JButton btnChon = taoNutChinh("Chọn");
            inputPanel.add(lblSoLuong);
            inputPanel.add(txtSoLuong);
            inputPanel.add(btnChon);
            
            btnChon.addActionListener(ev -> {
                int selectedRow = sachTable.getSelectedRow();
                if (selectedRow >= 0) {
                    try {
                        String maSach = sachModel.getValueAt(selectedRow, 0).toString();
                        Sach sach = quanLySach.timKiemTheoMa(maSach);
                        int soLuong = Integer.parseInt(txtSoLuong.getText());
                        
                        if (soLuong <= 0) {
                            JOptionPane.showMessageDialog(sachDialog, "Số lượng phải lớn hơn 0!");
                            return;
                        }
                        
                        if (soLuong > sach.getSoLuongTon()) {
                            JOptionPane.showMessageDialog(sachDialog, "Không đủ hàng trong kho!");
                            return;
                        }
                        
                        double thanhTien = sach.getGiaBan() * soLuong;
                        cartModel.addRow(new Object[]{
                            sach.getMaSach(),
                            sach.getTen(),
                            soLuong,
                            String.format("%,.0f", sach.getGiaBan()),
                            String.format("%,.0f", thanhTien)
                        });
                        
                        capNhatTongTien(cartModel, lblTongTien);
                        sachDialog.dispose();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(sachDialog, "Số lượng không hợp lệ!");
                    }
                } else {
                    JOptionPane.showMessageDialog(sachDialog, "Vui lòng chọn sách!");
                }
            });
            
            sachPanel.add(sachScroll, BorderLayout.CENTER);
            sachPanel.add(inputPanel, BorderLayout.SOUTH);
            sachDialog.add(sachPanel);
            sachDialog.setVisible(true);
        });
        
        // Xử lý xóa sách
        btnXoaSach.addActionListener(e -> {
            int selectedRow = cartTable.getSelectedRow();
            if (selectedRow >= 0) {
                cartModel.removeRow(selectedRow);
                capNhatTongTien(cartModel, lblTongTien);
            } else {
                JOptionPane.showMessageDialog(dialog, "Vui lòng chọn sách cần xóa!");
            }
        });
        
        // Cập nhật tổng tiền khi sửa số lượng
        cartModel.addTableModelListener(e -> {
            if (e.getColumn() == 2) { // Cột số lượng
                int row = e.getFirstRow();
                try {
                    int soLuong = Integer.parseInt(cartModel.getValueAt(row, 2).toString());
                    String donGiaStr = cartModel.getValueAt(row, 3).toString().replace(",", "").replace(" VND", "");
                    double donGia = Double.parseDouble(donGiaStr);
                    double thanhTien = soLuong * donGia;
                    cartModel.setValueAt(String.format("%,.0f", thanhTien), row, 4);
                    capNhatTongTien(cartModel, lblTongTien);
                } catch (Exception ex) {
                    // Ignore invalid input
                }
            }
        });
        
        // Xử lý lưu
        btnLuu.addActionListener(e -> {
            if (cboKhachHang.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(dialog, "Vui lòng chọn khách hàng!");
                return;
            }
            
            if (cartModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(dialog, "Vui lòng thêm sách vào giỏ hàng!");
                return;
            }
            
            // Kiểm tra mã hóa đơn đã tồn tại chưa
            if (quanLyHoaDon.timKiemTheoMa(txtMaHD.getText()) != null) {
                JOptionPane.showMessageDialog(dialog, "Mã hóa đơn đã tồn tại! Vui lòng nhập mã khác.");
                return;
            }
            
            String maKH = cboKhachHang.getSelectedItem().toString().split(" - ")[0];
            KhachHang khachHang = quanLyKhachHang.timKiemTheoMa(maKH);
            
            HoaDon hoaDon = new HoaDon(txtMaHD.getText(), new java.util.Date(), khachHang);
            
            for (int i = 0; i < cartModel.getRowCount(); i++) {
                String maSach = cartModel.getValueAt(i, 0).toString();
                Sach sach = quanLySach.timKiemTheoMa(maSach);
                int soLuong = Integer.parseInt(cartModel.getValueAt(i, 2).toString());
                double donGia = Double.parseDouble(cartModel.getValueAt(i, 3).toString().replace(",", ""));
                
                ChiTietHoaDon chiTiet = new ChiTietHoaDon(sach, soLuong, donGia);
                hoaDon.themChiTiet(chiTiet);
                
                // Giảm tồn kho
                sach.setSoLuongTon(sach.getSoLuongTon() - soLuong);
                quanLySach.sua(sach);
            }
            
            quanLyHoaDon.them(hoaDon);
            saveDuLieu();
            capNhatBangHoaDon();
            capNhatBangSach();
            capNhatTrangTongQuan();
            
            JOptionPane.showMessageDialog(dialog, "Tạo hóa đơn thành công!");
            dialog.dispose();
        });
        
        btnHuy.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(btnHuy);
        buttonPanel.add(btnLuu);
        
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBackground(Color.WHITE);
        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(formPanel, BorderLayout.CENTER);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(cartPanel, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(tongTienPanel, BorderLayout.NORTH);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        dialog.add(mainPanel);
        dialog.setVisible(true);
    }
    
    private void capNhatTongTien(DefaultTableModel model, JLabel lblTongTien) {
        double tong = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            String thanhTienStr = model.getValueAt(i, 4).toString().replace(",", "");
            tong += Double.parseDouble(thanhTienStr);
        }
        lblTongTien.setText(String.format("Tổng tiền: %,.0f VND", tong));
    }
    
    private void xoaHoaDon() {
        int selectedRow = tableHoaDon.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn cần xóa!");
            return;
        }
        
        String maHD = modelHoaDon.getValueAt(selectedRow, 0).toString();
        HoaDon hoaDon = quanLyHoaDon.timKiemTheoMa(maHD);
        
        if (hoaDon == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn!");
            return;
        }
        
        String thongTinHD = String.format("Mã: %s\nKhách hàng: %s\nTổng tiền: %,.0f VND",
            hoaDon.getMaHoaDon(),
            hoaDon.getKhachHang().getHoTen(),
            hoaDon.getTongTien());
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc chắn muốn xóa hóa đơn?\n\n" + thongTinHD + "\n\nSố lượng sách sẽ được hoàn trả về kho.",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            // Hoàn trả số lượng sách về kho
            for (ChiTietHoaDon ct : hoaDon.getDanhSachSachMua()) {
                Sach sach = quanLySach.timKiemTheoMa(ct.getSach().getMaSach());
                if (sach != null) {
                    sach.setSoLuongTon(sach.getSoLuongTon() + ct.getSoLuong());
                    quanLySach.sua(sach);
                }
            }
            
            quanLyHoaDon.xoa(maHD);
            saveDuLieu();
            capNhatBangHoaDon();
            capNhatBangSach();
            capNhatTrangTongQuan();
            JOptionPane.showMessageDialog(this, "Xóa hóa đơn thành công!\nĐã hoàn trả sách về kho.");
        }
    }
    
    private void hienThiSuaHoaDonDialog() {
        int selectedRow = tableHoaDon.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn cần sửa!");
            return;
        }
        
        String maHD = modelHoaDon.getValueAt(selectedRow, 0).toString();
        HoaDon hoaDon = quanLyHoaDon.timKiemTheoMa(maHD);
        
        if (hoaDon == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn!");
            return;
        }
        
        JDialog dialog = new JDialog(this, "Sửa hóa đơn: " + maHD, true);
        dialog.setSize(900, 600);
        dialog.setLocationRelativeTo(this);
        dialog.getContentPane().setBackground(Color.WHITE);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("Sửa thông tin hóa đơn");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBackground(Color.WHITE);
        
        JLabel lblMaHD = new JLabel("Mã hóa đơn:");
        JTextField txtMaHD = new JTextField(hoaDon.getMaHoaDon());
        txtMaHD.setEditable(false);
        
        JLabel lblNgay = new JLabel("Ngày lập:");
        JTextField txtNgay = new JTextField(new java.text.SimpleDateFormat("dd/MM/yyyy").format(hoaDon.getNgayLap()));
        txtNgay.setEditable(false);
        
        JLabel lblKhachHang = new JLabel("Khách hàng:");
        JComboBox<String> cboKhachHang = new JComboBox<>();
        int selectedKHIndex = 0;
        int index = 0;
        for (KhachHang kh : quanLyKhachHang.layTatCa()) {
            cboKhachHang.addItem(kh.getMaKhachHang() + " - " + kh.getHoTen());
            if (kh.getMaKhachHang().equals(hoaDon.getKhachHang().getMaKhachHang())) {
                selectedKHIndex = index;
            }
            index++;
        }
        cboKhachHang.setSelectedIndex(selectedKHIndex);
        
        formPanel.add(lblMaHD); formPanel.add(txtMaHD);
        formPanel.add(lblNgay); formPanel.add(txtNgay);
        formPanel.add(lblKhachHang); formPanel.add(cboKhachHang);
        
        JPanel cartPanel = new JPanel(new BorderLayout(5, 5));
        cartPanel.setBackground(Color.WHITE);
        cartPanel.setBorder(BorderFactory.createTitledBorder("Giỏ hàng"));
        
        String[] cartColumns = {"Mã sách", "Tên sách", "Số lượng", "Đơn giá", "Thành tiền"};
        DefaultTableModel cartModel = new DefaultTableModel(cartColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };
        
        // Load dữ liệu hiện tại vào bảng
        java.util.Map<String, Integer> soLuongCu = new java.util.HashMap<>();
        for (ChiTietHoaDon ct : hoaDon.getDanhSachSachMua()) {
            soLuongCu.put(ct.getSach().getMaSach(), ct.getSoLuong());
            cartModel.addRow(new Object[]{
                ct.getSach().getMaSach(),
                ct.getSach().getTen(),
                ct.getSoLuong(),
                String.format("%,.0f", ct.getDonGia()),
                String.format("%,.0f", ct.getThanhTien())
            });
        }
        
        JTable cartTable = new JTable(cartModel);
        JScrollPane cartScroll = new JScrollPane(cartTable);
        
        JPanel cartButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cartButtonPanel.setBackground(Color.WHITE);
        JButton btnThemSach = taoNutChinh("+ Thêm sách");
        JButton btnXoaSach = taoNutPhu("- Xóa sách");
        
        cartButtonPanel.add(btnThemSach);
        cartButtonPanel.add(btnXoaSach);
        
        cartPanel.add(cartScroll, BorderLayout.CENTER);
        cartPanel.add(cartButtonPanel, BorderLayout.SOUTH);
        
        JPanel tongTienPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        tongTienPanel.setBackground(Color.WHITE);
        JLabel lblTongTien = new JLabel("Tổng tiền: 0 VND");
        lblTongTien.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTongTien.setForeground(new Color(231, 76, 60));
        tongTienPanel.add(lblTongTien);
        
        capNhatTongTien(cartModel, lblTongTien);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        JButton btnLuu = taoNutChinh("Cập nhật hóa đơn");
        JButton btnHuy = taoNutPhu("Hủy");
        
        btnThemSach.addActionListener(e -> {
            JDialog sachDialog = new JDialog(dialog, "Chọn sách", true);
            sachDialog.setSize(600, 400);
            sachDialog.setLocationRelativeTo(dialog);
            
            JPanel sachPanel = new JPanel(new BorderLayout(10, 10));
            sachPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            
            String[] sachCols = {"Mã", "Tên", "Giá", "Tồn kho"};
            DefaultTableModel sachModel = new DefaultTableModel(sachCols, 0);
            for (Sach s : quanLySach.layTatCa()) {
                sachModel.addRow(new Object[]{s.getMaSach(), s.getTen(), 
                    String.format("%,.0f", s.getGiaBan()), s.getSoLuongTon()});
            }
            
            JTable sachTable = new JTable(sachModel);
            JScrollPane sachScroll = new JScrollPane(sachTable);
            
            JPanel inputPanel = new JPanel(new FlowLayout());
            JLabel lblSoLuong = new JLabel("Số lượng:");
            JTextField txtSoLuong = new JTextField("1", 5);
            JButton btnChon = taoNutChinh("Chọn");
            inputPanel.add(lblSoLuong);
            inputPanel.add(txtSoLuong);
            inputPanel.add(btnChon);
            
            btnChon.addActionListener(ev -> {
                int selectedRowSach = sachTable.getSelectedRow();
                if (selectedRowSach >= 0) {
                    try {
                        String maSach = sachModel.getValueAt(selectedRowSach, 0).toString();
                        Sach sach = quanLySach.timKiemTheoMa(maSach);
                        int soLuong = Integer.parseInt(txtSoLuong.getText());
                        
                        if (soLuong <= 0) {
                            JOptionPane.showMessageDialog(sachDialog, "Số lượng phải lớn hơn 0!");
                            return;
                        }
                        
                        int tonKhoHienTai = sach.getSoLuongTon();
                        int soLuongDaMua = soLuongCu.getOrDefault(maSach, 0);
                        if (soLuong > tonKhoHienTai + soLuongDaMua) {
                            JOptionPane.showMessageDialog(sachDialog, "Không đủ hàng trong kho!");
                            return;
                        }
                        
                        double thanhTien = sach.getGiaBan() * soLuong;
                        cartModel.addRow(new Object[]{
                            sach.getMaSach(),
                            sach.getTen(),
                            soLuong,
                            String.format("%,.0f", sach.getGiaBan()),
                            String.format("%,.0f", thanhTien)
                        });
                        
                        capNhatTongTien(cartModel, lblTongTien);
                        sachDialog.dispose();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(sachDialog, "Số lượng không hợp lệ!");
                    }
                } else {
                    JOptionPane.showMessageDialog(sachDialog, "Vui lòng chọn sách!");
                }
            });
            
            sachPanel.add(sachScroll, BorderLayout.CENTER);
            sachPanel.add(inputPanel, BorderLayout.SOUTH);
            sachDialog.add(sachPanel);
            sachDialog.setVisible(true);
        });
        
        btnXoaSach.addActionListener(e -> {
            int selectedRowCart = cartTable.getSelectedRow();
            if (selectedRowCart >= 0) {
                cartModel.removeRow(selectedRowCart);
                capNhatTongTien(cartModel, lblTongTien);
            } else {
                JOptionPane.showMessageDialog(dialog, "Vui lòng chọn sách cần xóa!");
            }
        });
        
        cartModel.addTableModelListener(e -> {
            if (e.getColumn() == 2) {
                int row = e.getFirstRow();
                try {
                    int soLuong = Integer.parseInt(cartModel.getValueAt(row, 2).toString());
                    String donGiaStr = cartModel.getValueAt(row, 3).toString().replace(",", "");
                    double donGia = Double.parseDouble(donGiaStr);
                    double thanhTien = soLuong * donGia;
                    cartModel.setValueAt(String.format("%,.0f", thanhTien), row, 4);
                    capNhatTongTien(cartModel, lblTongTien);
                } catch (Exception ex) {
                    // Ignore
                }
            }
        });
        
        btnLuu.addActionListener(e -> {
            if (cboKhachHang.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(dialog, "Vui lòng chọn khách hàng!");
                return;
            }
            
            if (cartModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(dialog, "Vui lòng thêm sách vào giỏ hàng!");
                return;
            }
            
            // Hoàn trả tồn kho cũ
            for (ChiTietHoaDon ct : hoaDon.getDanhSachSachMua()) {
                Sach sachCu = quanLySach.timKiemTheoMa(ct.getSach().getMaSach());
                sachCu.setSoLuongTon(sachCu.getSoLuongTon() + ct.getSoLuong());
                quanLySach.sua(sachCu);
            }
            
            String maKH = cboKhachHang.getSelectedItem().toString().split(" - ")[0];
            KhachHang khachHang = quanLyKhachHang.timKiemTheoMa(maKH);
            
            hoaDon.setKhachHang(khachHang);
            hoaDon.getDanhSachSachMua().clear();
            
            for (int i = 0; i < cartModel.getRowCount(); i++) {
                String maSach = cartModel.getValueAt(i, 0).toString();
                Sach sach = quanLySach.timKiemTheoMa(maSach);
                int soLuong = Integer.parseInt(cartModel.getValueAt(i, 2).toString());
                double donGia = Double.parseDouble(cartModel.getValueAt(i, 3).toString().replace(",", ""));
                
                ChiTietHoaDon chiTiet = new ChiTietHoaDon(sach, soLuong, donGia);
                hoaDon.themChiTiet(chiTiet);
                
                sach.setSoLuongTon(sach.getSoLuongTon() - soLuong);
                quanLySach.sua(sach);
            }
            
            quanLyHoaDon.sua(hoaDon);
            saveDuLieu();
            capNhatBangHoaDon();
            capNhatBangSach();
            capNhatTrangTongQuan();
            
            JOptionPane.showMessageDialog(dialog, "Cập nhật hóa đơn thành công!");
            dialog.dispose();
        });
        
        btnHuy.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(btnHuy);
        buttonPanel.add(btnLuu);
        
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBackground(Color.WHITE);
        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(formPanel, BorderLayout.CENTER);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(cartPanel, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(tongTienPanel, BorderLayout.NORTH);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        dialog.add(mainPanel);
        dialog.setVisible(true);
    }
    
    private void hienThiBaoCaoThang() {
        JDialog dialog = new JDialog(this, "Báo cáo tháng", true);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel lblThang = new JLabel("Tháng:");
        JComboBox<Integer> cboThang = new JComboBox<>();
        for (int i = 1; i <= 12; i++) cboThang.addItem(i);
        cboThang.setSelectedItem(java.util.Calendar.getInstance().get(java.util.Calendar.MONTH) + 1);
        
        JLabel lblNam = new JLabel("Năm:");
        JComboBox<Integer> cboNam = new JComboBox<>();
        int namHienTai = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        for (int i = namHienTai - 5; i <= namHienTai; i++) cboNam.addItem(i);
        cboNam.setSelectedItem(namHienTai);
        
        JButton btnXem = taoNutChinh("Xem báo cáo");
        
        inputPanel.add(lblThang);
        inputPanel.add(cboThang);
        inputPanel.add(lblNam);
        inputPanel.add(cboNam);
        inputPanel.add(btnXem);
        
        JTextArea txtBaoCao = new JTextArea();
        txtBaoCao.setEditable(false);
        txtBaoCao.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(txtBaoCao);
        
        btnXem.addActionListener(e -> {
            int thang = (int) cboThang.getSelectedItem();
            int nam = (int) cboNam.getSelectedItem();
            
            List<HoaDon> hoaDonThang = quanLyHoaDon.timKiemTheoThang(thang, nam);
            double doanhThu = timKiemThongKe.tinhDoanhThuTheoThang(thang, nam);
            
            StringBuilder sb = new StringBuilder();
            sb.append("=== BÁO CÁO THÁNG ").append(thang).append("/").append(nam).append(" ===").append("\n\n");
            sb.append("Tổng số hóa đơn: ").append(hoaDonThang.size()).append("\n");
            sb.append(String.format("Tổng doanh thu: %,.0f VND\n\n", doanhThu));
            
            sb.append("DANH SÁCH HÓA ĐƠN:\n");
            sb.append(String.format("%-15s %-20s %-15s %-20s\n", "Mã HD", "Khách hàng", "Ngày", "Tổng tiền"));
            sb.append("-".repeat(70)).append("\n");
            
            for (HoaDon hd : hoaDonThang) {
                sb.append(String.format("%-15s %-20s %-15s %,-20.0f\n",
                    hd.getMaHoaDon(),
                    hd.getKhachHang().getHoTen(),
                    new java.text.SimpleDateFormat("dd/MM/yyyy").format(hd.getNgayLap()),
                    hd.getTongTien()
                ));
            }
            
            txtBaoCao.setText(sb.toString());
        });
        
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    private void hienThiBaoCaoTuyChinh() {
        JDialog dialog = new JDialog(this, "Thống kê chi tiết", true);
        dialog.setSize(700, 500);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Tab Sách bán chạy
        JPanel sachPanel = new JPanel(new BorderLayout());
        String[] sachCols = {"Mã sách", "Tên sách", "Số lượng bán", "Doanh thu"};
        DefaultTableModel sachModel = new DefaultTableModel(sachCols, 0);
        JTable sachTable = new JTable(sachModel);
        
        java.util.Map<String, Integer> soLuongBan = new java.util.HashMap<>();
        java.util.Map<String, Double> doanhThuSach = new java.util.HashMap<>();
        
        for (HoaDon hd : quanLyHoaDon.layTatCa()) {
            for (ChiTietHoaDon ct : hd.getDanhSachSachMua()) {
                String maSach = ct.getSach().getMaSach();
                soLuongBan.put(maSach, soLuongBan.getOrDefault(maSach, 0) + ct.getSoLuong());
                doanhThuSach.put(maSach, doanhThuSach.getOrDefault(maSach, 0.0) + ct.getThanhTien());
            }
        }
        
        java.util.List<java.util.Map.Entry<String, Integer>> sortedSach = new java.util.ArrayList<>(soLuongBan.entrySet());
        sortedSach.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        for (java.util.Map.Entry<String, Integer> entry : sortedSach) {
            Sach sach = quanLySach.timKiemTheoMa(entry.getKey());
            if (sach != null) {
                sachModel.addRow(new Object[]{
                    sach.getMaSach(),
                    sach.getTen(),
                    entry.getValue(),
                    String.format("%,.0f VND", doanhThuSach.get(entry.getKey()))
                });
            }
        }
        
        sachPanel.add(new JScrollPane(sachTable), BorderLayout.CENTER);
        tabbedPane.addTab("Sách bán chạy", sachPanel);
        
        // Tab Khách hàng thân thiết
        JPanel khPanel = new JPanel(new BorderLayout());
        String[] khCols = {"Mã KH", "Tên khách hàng", "Số đơn hàng", "Tổng chi tiêu"};
        DefaultTableModel khModel = new DefaultTableModel(khCols, 0);
        JTable khTable = new JTable(khModel);
        
        java.util.Map<String, Integer> soDonHang = new java.util.HashMap<>();
        java.util.Map<String, Double> tongChiTieu = new java.util.HashMap<>();
        
        for (HoaDon hd : quanLyHoaDon.layTatCa()) {
            String maKH = hd.getKhachHang().getMaKhachHang();
            soDonHang.put(maKH, soDonHang.getOrDefault(maKH, 0) + 1);
            tongChiTieu.put(maKH, tongChiTieu.getOrDefault(maKH, 0.0) + hd.getTongTien());
        }
        
        java.util.List<java.util.Map.Entry<String, Double>> sortedKH = new java.util.ArrayList<>(tongChiTieu.entrySet());
        sortedKH.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        for (java.util.Map.Entry<String, Double> entry : sortedKH) {
            KhachHang kh = quanLyKhachHang.timKiemTheoMa(entry.getKey());
            if (kh != null) {
                khModel.addRow(new Object[]{
                    kh.getMaKhachHang(),
                    kh.getHoTen(),
                    soDonHang.get(entry.getKey()),
                    String.format("%,.0f VND", entry.getValue())
                });
            }
        }
        
        khPanel.add(new JScrollPane(khTable), BorderLayout.CENTER);
        tabbedPane.addTab("Khách hàng thân thiết", khPanel);
        
        // Tab Tổng quan
        JPanel tongQuanPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        tongQuanPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        int tongSoHoaDon = quanLyHoaDon.layTatCa().size();
        double tongDoanhThu = quanLyHoaDon.layTatCa().stream().mapToDouble(HoaDon::getTongTien).sum();
        double trungBinhHoaDon = tongSoHoaDon > 0 ? tongDoanhThu / tongSoHoaDon : 0;
        
        tongQuanPanel.add(new JLabel("Tổng số hóa đơn:"));
        tongQuanPanel.add(new JLabel(String.valueOf(tongSoHoaDon)));
        
        tongQuanPanel.add(new JLabel("Tổng doanh thu:"));
        tongQuanPanel.add(new JLabel(String.format("%,.0f VND", tongDoanhThu)));
        
        tongQuanPanel.add(new JLabel("Trung bình/hóa đơn:"));
        tongQuanPanel.add(new JLabel(String.format("%,.0f VND", trungBinhHoaDon)));
        
        tongQuanPanel.add(new JLabel("Tổng số sách:"));
        tongQuanPanel.add(new JLabel(String.valueOf(quanLySach.layTatCa().size())));
        
        tongQuanPanel.add(new JLabel("Tổng khách hàng:"));
        tongQuanPanel.add(new JLabel(String.valueOf(quanLyKhachHang.layTatCa().size())));
        
        tabbedPane.addTab("Tổng quan", tongQuanPanel);
        
        panel.add(tabbedPane, BorderLayout.CENTER);
        
        JButton btnDong = taoNutPhu("Đóng");
        btnDong.addActionListener(e -> dialog.dispose());
        
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.add(btnDong);
        panel.add(btnPanel, BorderLayout.SOUTH);
        
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    private void hienThiChiTietHoaDon(String maHoaDon) {
        HoaDon hoaDon = quanLyHoaDon.timKiemTheoMa(maHoaDon);
        if (hoaDon == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn!");
            return;
        }
        
        JDialog dialog = new JDialog(this, "Chi tiết hóa đơn: " + maHoaDon, true);
        dialog.setSize(700, 500);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);
        
        // Thông tin hóa đơn
        JPanel infoPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin hóa đơn"));
        
        infoPanel.add(new JLabel("Mã hóa đơn:"));
        infoPanel.add(new JLabel(hoaDon.getMaHoaDon()));
        
        infoPanel.add(new JLabel("Ngày lập:"));
        infoPanel.add(new JLabel(new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(hoaDon.getNgayLap())));
        
        infoPanel.add(new JLabel("Khách hàng:"));
        infoPanel.add(new JLabel(hoaDon.getKhachHang().getHoTen()));
        
        infoPanel.add(new JLabel("Điện thoại:"));
        infoPanel.add(new JLabel(hoaDon.getKhachHang().getDienThoai()));
        
        // Danh sách sách
        String[] columns = {"STT", "Mã sách", "Tên sách", "Số lượng", "Đơn giá", "Thành tiền"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        
        int stt = 1;
        for (ChiTietHoaDon ct : hoaDon.getDanhSachSachMua()) {
            model.addRow(new Object[]{
                stt++,
                ct.getSach().getMaSach(),
                ct.getSach().getTen(),
                ct.getSoLuong(),
                String.format("%,.0f", ct.getDonGia()),
                String.format("%,.0f", ct.getThanhTien())
            });
        }
        
        JTable table = new JTable(model);
        table.setEnabled(false);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder("Danh sách sách"));
        
        // Tổng tiền
        JPanel tongPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        tongPanel.setBackground(Color.WHITE);
        JLabel lblTong = new JLabel(String.format("TỔNG TIỀN: %,.0f VND", hoaDon.getTongTien()));
        lblTong.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTong.setForeground(new Color(231, 76, 60));
        tongPanel.add(lblTong);
        
        JButton btnDong = taoNutPhu("Đóng");
        btnDong.addActionListener(e -> dialog.dispose());
        
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setBackground(Color.WHITE);
        btnPanel.add(btnDong);
        
        panel.add(infoPanel, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(tongPanel, BorderLayout.NORTH);
        bottomPanel.add(btnPanel, BorderLayout.SOUTH);
        panel.add(bottomPanel, BorderLayout.SOUTH);
        
        dialog.add(panel);
        dialog.setVisible(true);
    }
}