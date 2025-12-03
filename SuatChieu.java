package KIMLOI_CANHAN;

public class SuatChieu {
    private String maSC;
    private Phim phim;
    private PhongChieu phong;
    private String thoiGian;

    public SuatChieu(String maSC, Phim phim, PhongChieu phong, String thoiGian) {
        this.maSC = maSC;
        this.phim = phim;
        this.phong = phong;
        this.thoiGian = thoiGian;
    }

    public String getMaSC() { return maSC; }
    public Phim getPhim() { return phim; }
    public PhongChieu getPhong() { return phong; }

    @Override
    public String toString() {
        return maSC + "|" + phim.getTenPhim() + "|" + phong.getMaPhong() + "|" + thoiGian;
    }
}
