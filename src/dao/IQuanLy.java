package dao;

import java.util.List;

public interface IQuanLy<T> {
    void them(T item);
    void sua(T item);
    void xoa(String ma);
    T timTheoMa(String ma);
    List<T> layTatCa();
}
