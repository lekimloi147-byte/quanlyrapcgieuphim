package KIMLOI_CANHAN;

public class PhimTreEm extends Phim {
    private String danhMuc; // mô tả ngắn

    public PhimTreEm() { super(); }

    public PhimTreEm(String tenPhim, int thoiLuong, String theLoai, String danhMuc) {
        super(tenPhim, thoiLuong, theLoai);
        this.danhMuc = danhMuc;
    }

    public String getDanhMuc() { return danhMuc; }
    public void setDanhMuc(String danhMuc) { this.danhMuc = danhMuc; }

    @Override
    public void hienThiThongTin() {
        super.hienThiThongTin();
        System.out.println("Danh mục trẻ em: " + danhMuc);
    }

    @Override
    public String toString() {
        // TE;ten;thoiLuong;theLoai;danhMuc
        return "TE;" + tenPhim + ";" + thoiLuong + ";" + theLoai + ";" + danhMuc;
    }
}
