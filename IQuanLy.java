import java.util.List;

public interface IQuanLy<T> {
    void them(T obj);
    void sua(T obj);
    void xoa(String ma);
    T timKiemTheoMa(String ma);
    List<T> layTatCa();
}