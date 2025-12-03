package KIMLOI_CANHAN;

public class Ghe {
    private String soGhe;      // ví dụ "A1"
    private String loaiGhe;    // "Thuong" hoặc "VIP"

    public Ghe() {}

    public Ghe(String soGhe, String loaiGhe) {
        this.soGhe = soGhe;
        setLoaiGhe(loaiGhe);
    }

    public String getSoGhe() { return soGhe; }
    public String getLoaiGhe() { return loaiGhe; }

    public void setSoGhe(String soGhe) { this.soGhe = soGhe; }
    public void setLoaiGhe(String loaiGhe) {
        if (loaiGhe == null) throw new IllegalArgumentException("Loại ghế không được null");
        String t = loaiGhe.trim().toLowerCase();
        if (t.equals("vip")) this.loaiGhe = "VIP";
        else if (t.equals("thuong") || t.equals("thường")) this.loaiGhe = "Thuong";
        else throw new IllegalArgumentException("Loại ghế chỉ nhận: Thuong hoặc VIP");
    }

    @Override
    public String toString() {
        return soGhe + ":" + loaiGhe; // dùng cho lưu file
    }

    public static Ghe parse(String token) {
        // token dạng "A1:VIP" or "A2:Thuong"
        String[] a = token.split(":");
        if (a.length != 2) return null;
        return new Ghe(a[0], a[1]);
    }
}

