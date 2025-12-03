package KIMLOI_CANHAN;

public class PhimNguoiLon extends Phim {
    private int gioiHanTuoi;

    public PhimNguoiLon() {}

    public PhimNguoiLon(String tenPhim, int thoiLuong, String theLoai, int gioiHanTuoi) {
        super(tenPhim, thoiLuong, theLoai);
        this.gioiHanTuoi = gioiHanTuoi;
    }

    @Override
    public void hienThi() {
        super.hienThi();
        System.out.println("Giới hạn tuổi: " + gioiHanTuoi + "+");
    }

    @Override
    public String toString() {
        return "NL;" + tenPhim + ";" + thoiLuong + ";" + theLoai + ";" + gioiHanTuoi;
    }
}
