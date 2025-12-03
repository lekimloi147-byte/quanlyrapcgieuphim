package KIMLOI_CANHAN;

import java.util.ArrayList;
import java.util.List;

public class PhongChieu {
    private String maPhong;
    private String loaiPhong; // chỉ 2D, 3D, IMAX
    private List<Ghe> dsGhe = new ArrayList<>();

    public PhongChieu() {}

    public PhongChieu(String maPhong, String loaiPhong) {
        this.maPhong = maPhong;
        setLoaiPhong(loaiPhong);
    }

    public String getMaPhong() { return maPhong; }
    public String getLoaiPhong() { return loaiPhong; }
    public List<Ghe> getDsGhe() { return dsGhe; }

    public void setMaPhong(String maPhong) { this.maPhong = maPhong; }

    public void setLoaiPhong(String loaiPhong) {
        if (loaiPhong == null) throw new IllegalArgumentException("Loại phòng không được null");
        String t = loaiPhong.trim().toUpperCase();
        if (t.equals("2D") || t.equals("3D") || t.equals("IMAX")) this.loaiPhong = t;
        else throw new IllegalArgumentException("Loại phòng phải là 2D, 3D hoặc IMAX");
    }

    public void themGhe(Ghe g) {
        dsGhe.add(g);
    }

    public void hienThi() {
        System.out.println("Phòng: " + maPhong + " | Loại: " + loaiPhong + " | Số ghế: " + dsGhe.size());
        for (Ghe g : dsGhe) System.out.println("  - " + g);
    }

    /**
     * Lưu phong theo 1 dòng:
     * maPhong|loaiPhong|A1:VIP,A2:Thuong,A3:VIP
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(maPhong).append("|").append(loaiPhong).append("|");
        for (int i = 0; i < dsGhe.size(); i++) {
            sb.append(dsGhe.get(i).toString());
            if (i < dsGhe.size() - 1) sb.append(",");
        }
        return sb.toString();
    }

    public static PhongChieu parse(String line) {
        // line: ma|loai|g1:gloai,g2:gloai
        String[] parts = line.split("\\|", -1);
        if (parts.length < 3) return null;
        PhongChieu p = new PhongChieu(parts[0], parts[1]);
        if (!parts[2].isEmpty()) {
            String[] gheTokens = parts[2].split(",");
            for (String t : gheTokens) {
                Ghe g = Ghe.parse(t);
                if (g != null) p.themGhe(g);
            }
        }
        return p;
    }
}
