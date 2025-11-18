import java.awt.*;
import javax.swing.*;

public class LoginDialog extends JDialog {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JRadioButton rdoAdmin;
    private JRadioButton rdoCustomer;
    private boolean loginSuccessful = false;
    private String userRole = "";
    private String loggedInUser = "";
    private String maKhachHang = "";
    
    private QuanLyNhanVien quanLyNhanVien;
    private QuanLyKhachHang quanLyKhachHang;
    
    public LoginDialog(Frame parent, QuanLyNhanVien quanLyNhanVien, QuanLyKhachHang quanLyKhachHang) {
        super(parent, "Đăng nhập - Hệ thống quản lý cửa hàng sách", true);
        this.quanLyNhanVien = quanLyNhanVien;
        this.quanLyKhachHang = quanLyKhachHang;
        
        initComponents();
        setSize(500, 350);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(41, 128, 185));
        headerPanel.setPreferredSize(new Dimension(400, 80));
        headerPanel.setLayout(new BorderLayout());
        
        JLabel titleLabel = new JLabel("ĐĂNG NHẬP HỆ THỐNG", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(25, 0, 25, 0));
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 5, 8, 5);
        
        JLabel lblRole = new JLabel("Vai trò:");
        lblRole.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        formPanel.add(lblRole, gbc);
        
        JPanel rolePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rolePanel.setBackground(Color.WHITE);
        rdoAdmin = new JRadioButton("Quản trị viên");
        rdoCustomer = new JRadioButton("Khách hàng");
        rdoAdmin.setBackground(Color.WHITE);
        rdoCustomer.setBackground(Color.WHITE);
        rdoAdmin.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        rdoCustomer.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        ButtonGroup roleGroup = new ButtonGroup();
        roleGroup.add(rdoAdmin);
        roleGroup.add(rdoCustomer);
        rdoAdmin.setSelected(true);
        
        rolePanel.add(rdoAdmin);
        rolePanel.add(rdoCustomer);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(rolePanel, gbc);
        
        JLabel lblUsername = new JLabel("Tên đăng nhập:");
        lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        formPanel.add(lblUsername, gbc);
        
        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsername.setPreferredSize(new Dimension(200, 35));
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(txtUsername, gbc);
        
        JLabel lblPassword = new JLabel("Mật khẩu:");
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        formPanel.add(lblPassword, gbc);
        
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPassword.setPreferredSize(new Dimension(200, 35));
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(txtPassword, gbc);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        
        JButton btnLogin = new JButton("Đăng nhập");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setBackground(new Color(39, 174, 96));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setPreferredSize(new Dimension(130, 40));
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton btnRegister = new JButton("Đăng ký");
        btnRegister.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnRegister.setBackground(new Color(52, 152, 219));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFocusPainted(false);
        btnRegister.setPreferredSize(new Dimension(130, 40));
        btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton btnCancel = new JButton("Thoát");
        btnCancel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnCancel.setBackground(new Color(231, 76, 60));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFocusPainted(false);
        btnCancel.setPreferredSize(new Dimension(130, 40));
        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnLogin.addActionListener(e -> handleLogin());
        btnRegister.addActionListener(e -> handleRegister());
        btnCancel.addActionListener(e -> {
            loginSuccessful = false;
            dispose();
            System.exit(0);
        });
        
        txtPassword.addActionListener(e -> handleLogin());
        
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnRegister);
        buttonPanel.add(btnCancel);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Vui lòng nhập đầy đủ thông tin!", 
                "Lỗi đăng nhập", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (rdoAdmin.isSelected()) {
            NhanVien nhanVien = quanLyNhanVien.dangNhap(username, password);
            if (nhanVien != null) {
                loginSuccessful = true;
                userRole = "ADMIN";
                loggedInUser = nhanVien.getHoTen();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Tên đăng nhập hoặc mật khẩu không đúng!", 
                    "Lỗi đăng nhập", 
                    JOptionPane.ERROR_MESSAGE);
                txtPassword.setText("");
                txtUsername.requestFocus();
            }
        } else {
            KhachHang khachHang = null;
            for (KhachHang kh : quanLyKhachHang.layTatCa()) {
                if (kh.getTenDangNhap() != null && kh.getTenDangNhap().equals(username)) {
                    khachHang = kh;
                    break;
                }
            }
            
            if (khachHang != null && khachHang.getMatKhau() != null && khachHang.getMatKhau().equals(password)) {
                loginSuccessful = true;
                userRole = "CUSTOMER";
                loggedInUser = khachHang.getHoTen();
                maKhachHang = khachHang.getMaKhachHang();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Tên đăng nhập hoặc mật khẩu không đúng!\nVui lòng đăng ký tài khoản mới nếu chưa có.", 
                    "Lỗi đăng nhập", 
                    JOptionPane.ERROR_MESSAGE);
                txtPassword.setText("");
                txtUsername.requestFocus();
            }
        }
    }
    
    private void handleRegister() {
        if (rdoAdmin.isSelected()) {
            JOptionPane.showMessageDialog(this,
                "Tài khoản quản trị viên chỉ được tạo bởi quản trị hệ thống!\nVui lòng liên hệ quản trị viên.",
                "Thông báo",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        JDialog registerDialog = new JDialog(this, "Đăng ký tài khoản khách hàng", true);
        registerDialog.setSize(500, 500);
        registerDialog.setLocationRelativeTo(this);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(52, 152, 219));
        headerPanel.setPreferredSize(new Dimension(400, 60));
        
        JLabel titleLabel = new JLabel("ĐĂNG KÝ TÀI KHOẢN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        headerPanel.add(titleLabel);
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 5, 8, 5);
        
        JTextField txtTenDangNhap = new JTextField();
        JPasswordField txtMatKhauReg = new JPasswordField();
        JPasswordField txtXacNhanMatKhau = new JPasswordField();
        JTextField txtHoTen = new JTextField();
        JTextField txtSDT = new JTextField();
        JTextField txtEmail = new JTextField();
        
        txtTenDangNhap.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtMatKhauReg.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtXacNhanMatKhau.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSDT.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        String[] labels = {"Tên đăng nhập:", "Mật khẩu:", "Xác nhận mật khẩu:", "Họ tên:", "Số điện thoại:", "Email:"};
        JComponent[] fields = {txtTenDangNhap, txtMatKhauReg, txtXacNhanMatKhau, txtHoTen, txtSDT, txtEmail};
        
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0.3;
            formPanel.add(label, gbc);
            
            fields[i].setPreferredSize(new Dimension(250, 35));
            gbc.gridx = 1;
            gbc.weightx = 0.7;
            formPanel.add(fields[i], gbc);
        }
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        
        JButton btnConfirm = new JButton("Đăng ký");
        btnConfirm.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnConfirm.setBackground(new Color(39, 174, 96));
        btnConfirm.setForeground(Color.WHITE);
        btnConfirm.setFocusPainted(false);
        btnConfirm.setPreferredSize(new Dimension(120, 40));
        btnConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton btnCancelReg = new JButton("Hủy");
        btnCancelReg.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnCancelReg.setBackground(new Color(149, 165, 166));
        btnCancelReg.setForeground(Color.WHITE);
        btnCancelReg.setFocusPainted(false);
        btnCancelReg.setPreferredSize(new Dimension(120, 40));
        btnCancelReg.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnConfirm.addActionListener(e -> {
            String tenDangNhap = txtTenDangNhap.getText().trim();
            String matKhau = new String(txtMatKhauReg.getPassword());
            String xacNhanMatKhau = new String(txtXacNhanMatKhau.getPassword());
            String hoTen = txtHoTen.getText().trim();
            String sdt = txtSDT.getText().trim();
            String email = txtEmail.getText().trim();
            
            if (tenDangNhap.isEmpty() || matKhau.isEmpty() || hoTen.isEmpty() || sdt.isEmpty()) {
                JOptionPane.showMessageDialog(registerDialog,
                    "Vui lòng nhập đầy đủ thông tin bắt buộc!",
                    "Lỗi",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (!matKhau.equals(xacNhanMatKhau)) {
                JOptionPane.showMessageDialog(registerDialog,
                    "Mật khẩu xác nhận không khớp!",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
                txtMatKhauReg.setText("");
                txtXacNhanMatKhau.setText("");
                return;
            }
            
            for (KhachHang kh : quanLyKhachHang.layTatCa()) {
                if (kh.getTenDangNhap() != null && kh.getTenDangNhap().equals(tenDangNhap)) {
                    JOptionPane.showMessageDialog(registerDialog,
                        "Tên đăng nhập đã tồn tại! Vui lòng chọn tên khác.",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            String maKH = taoMaKhachHangTuDong();
            
            KhachHang khachHangMoi = new KhachHang(maKH, tenDangNhap, matKhau, hoTen, sdt, email);
            quanLyKhachHang.them(khachHangMoi);
            QuanLyFile.ghiDuLieuKhachHang(quanLyKhachHang.layTatCa(), "khachhang.dat");
            
            JOptionPane.showMessageDialog(registerDialog,
                "Đăng ký thành công!\nTên đăng nhập: " + tenDangNhap + "\nMã khách hàng: " + maKH,
                "Thành công",
                JOptionPane.INFORMATION_MESSAGE);
            
            registerDialog.dispose();
            txtUsername.setText(tenDangNhap);
            rdoCustomer.setSelected(true);
            txtPassword.requestFocus();
        });
        
        btnCancelReg.addActionListener(e -> registerDialog.dispose());
        
        buttonPanel.add(btnConfirm);
        buttonPanel.add(btnCancelReg);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        registerDialog.add(mainPanel);
        registerDialog.setVisible(true);
    }
    
    private String taoMaKhachHangTuDong() {
        int maxNumber = 0;
        for (KhachHang kh : quanLyKhachHang.layTatCa()) {
            String maKH = kh.getMaKhachHang();
            if (maKH.startsWith("KH")) {
                try {
                    int number = Integer.parseInt(maKH.substring(2));
                    if (number > maxNumber) {
                        maxNumber = number;
                    }
                } catch (NumberFormatException e) {
                }
            }
        }
        return String.format("KH%03d", maxNumber + 1);
    }
    
    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }
    
    public String getUserRole() {
        return userRole;
    }
    
    public String getLoggedInUser() {
        return loggedInUser;
    }
    
    public String getMaKhachHang() {
        return maKhachHang;
    }
    
    public String getUsername() {
        return txtUsername.getText().trim();
    }
}
