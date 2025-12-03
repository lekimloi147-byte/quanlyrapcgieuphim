package KIMLOI_CANHAN;

public class Ve {
    private String maVe;
    private SuatChieu suatChieu;
    private Ghe ghe;
    private double gia;

    public Ve() {}

    public Ve(String maVe, SuatChieu suatChieu, Ghe ghe, double gia) {
        this.maVe = maVe;
        this.suatChieu = suatChieu;
        this.ghe = ghe;
        this.gia = gia;
    }

    public String getMaVe() { return maVe; }
    public SuatChieu getSuatChieu() { return suatChieu; }
    public Ghe getGhe() { return ghe; }
    public double getGia() { return gia; }

    public void setMaVe(String maVe) { this.maVe = maVe; }
    public void setSuatChieu(SuatChieu suatChieu) { this.suatChieu = suatChieu; }
    public void setGhe(Ghe ghe) { this.ghe = ghe; }
    public void setGia(double gia) { this.gia = gia; }

    public void hienThi() {
        System.out.println("Mã vé: " + maVe);
        System.out.println("Suất chiếu: " + suatChieu);
        System.out.println("Ghế: " + ghe);
        System.out.println("Giá: " + gia);
    }

    @Override
    public String toString() {
        // Định dạng lưu file:
        // maVe|maSC|soGhe:loaiGhe|gia
        return maVe + "|" + suatChieu.getMaSC() + "|" + ghe.toString() + "|" + gia;
    }
}

