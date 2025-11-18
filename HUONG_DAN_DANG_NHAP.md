# Hệ thống đăng nhập - Hướng dẫn sử dụng

## Tổng quan
Hệ thống quản lý cửa hàng sách đã được cập nhật với tính năng đăng nhập phân quyền:
- **Quản trị viên (Admin)**: Có quyền thêm, sửa, xóa tất cả dữ liệu
- **Khách hàng**: Chỉ có quyền xem và mua sách

## Tài khoản mẫu

### Tài khoản Admin (Quản trị viên)
1. **Tài khoản chính**
   - Username: `admin`
   - Password: `admin123`
   - Tên: Quản Trị Viên

2. **Tài khoản phụ**
   - Username: `nvana`
   - Password: `123456`
   - Tên: Nguyễn Văn A

### Tài khoản Khách hàng
- Đăng nhập bằng Mã khách hàng (không cần mật khẩu)
- Mã khách hàng có sẵn trong file `khachhang.dat`

## Cách sử dụng

### 1. Khởi động chương trình
```bash
java Main
```

### 2. Đăng nhập
- **Bước 1**: Chọn loại tài khoản (Admin hoặc Khách hàng)
- **Bước 2**: Nhập thông tin đăng nhập
  - Admin: Username + Password
  - Khách hàng: Chỉ cần Mã khách hàng
- **Bước 3**: Click "Đăng nhập"

### 3. Tính năng theo vai trò

#### Quản trị viên
- Quản lý sách: Thêm, sửa, xóa, tìm kiếm
- Quản lý khách hàng: Thêm, sửa, xóa, xem lịch sử
- Quản lý hóa đơn: Tạo mới, xem, thống kê
- Quản lý nhân viên: Thêm, sửa, xóa
- Quản lý phiếu nhập kho
- Xem thống kê báo cáo

#### Khách hàng
- Xem danh sách sách
- Tìm kiếm sách (theo tên, tác giả, thể loại)
- Thêm sách vào giỏ hàng
- Thanh toán đơn hàng
- Xem lịch sử mua hàng
- Xem điểm tích lũy và ưu đãi

## Tính năng nổi bật

### Cho khách hàng
1. **Giỏ hàng thông minh**
   - Thêm nhiều sách cùng lúc
   - Điều chỉnh số lượng
   - Xóa sách không muốn mua
   - Xem tổng tiền trước khi thanh toán

2. **Hệ thống giảm giá tự động**
   - Khách VIP (chi tiêu ≥ 10,000,000 VND): Giảm 10%
   - Khách tích lũy ≥ 50 điểm: Giảm 5%
   - Tích điểm: 100,000 VND = 1 điểm

3. **Giao diện thân thiện**
   - Hiển thị rõ ràng thông tin khách hàng
   - Cập nhật điểm tích lũy realtime
   - Màu sắc phân biệt trạng thái VIP

### Bảo mật
- Mã hóa mật khẩu Admin
- Phân quyền rõ ràng
- Không cho phép khách hàng truy cập chức năng quản trị

## Cấu trúc file mới

### LoginDialog.java
- Giao diện đăng nhập
- Xác thực tài khoản
- Phân quyền người dùng

### KhachHangGiaoDien.java
- Giao diện dành riêng cho khách hàng
- Chức năng mua sắm
- Quản lý giỏ hàng

### Main.java (đã cập nhật)
- Load dữ liệu từ file
- Hiển thị màn hình đăng nhập
- Điều hướng theo vai trò

### KhoiTaoDuLieu.java
- Tạo tài khoản admin mặc định
- Tiện ích khởi tạo dữ liệu

## Lưu ý
- Chạy `KhoiTaoDuLieu` một lần để tạo tài khoản admin
- File dữ liệu được lưu ở thư mục gốc dự án
- Tất cả thay đổi được lưu tự động vào file .dat
