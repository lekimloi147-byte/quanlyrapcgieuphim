package KIMLOI_CANHAN;

public class Phim {
    protected String tenPhim;
    protected int thoiLuong;
    protected String theLoai;

    public Phim() {}

    public Phim(String tenPhim, int thoiLuong, String theLoai) {
        this.tenPhim = tenPhim;
        this.thoiLuong = thoiLuong;
        this.theLoai = theLoai;
    }

    public String getTenPhim() { return tenPhim; }
    public int getThoiLuong() { return thoiLuong; }
    public String getTheLoai() { return theLoai; }

    public void setTenPhim(String tenPhim) { this.tenPhim = tenPhim; }
    public void setThoiLuong(int thoiLuong) { this.thoiLuong = thoiLuong; }
    public void setTheLoai(String theLoai) { this.theLoai = theLoai; }

    public void hienThi() {
        System.out.println("Tên phim: " + tenPhim + " | Thể loại: " + theLoai + " | Thời lượng: " + thoiLuong);
    }

    @Override
    public String toString() {
        return "PHIM;" + tenPhim + ";" + thoiLuong + ";" + theLoai;
    }
}
