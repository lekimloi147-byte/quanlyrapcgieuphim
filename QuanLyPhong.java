package KIMLOI_CANHAN;

import java.io.*;
import java.util.*;

public class QuanLyPhong implements IRW {
    private List<PhongChieu> dsPhong = new ArrayList<>();

    // FILE mặc định cho phòng
    public static final String FILE_PHONG = "Phong.txt";

    // CRUD: add
    public void themPhong(PhongChieu p) {
        if (findByMa(p.getMaPhong()) != null) {
            System.out.println("Mã phòng đã tồn tại: " + p.getMaPhong());
            return;
        }
        dsPhong.add(p);
        System.out.println("Đã thêm phòng: " + p.getMaPhong());
    }

    // read all
    public void hienThiTatCa() {
        if (dsPhong.isEmpty()) { System.out.println("Chưa có phòng nào."); return; }
        for (PhongChieu p : dsPhong) { p.hienThi(); System.out.println("----------------"); }
    }

    // find
    public PhongChieu findByMa(String ma) {
        for (PhongChieu p : dsPhong) if (p.getMaPhong().equalsIgnoreCase(ma)) return p;
        return null;
    }

    // update: cho phép đổi loại phòng (kiểm tra) và thêm/xoá ghế
    public void capNhatPhong(String maPhong, String loaiPhong) {
        PhongChieu p = findByMa(maPhong);
        if (p == null) { System.out.println("Không tìm thấy phòng."); return; }
        try {
            p.setLoaiPhong(loaiPhong);
            System.out.println("Cập nhật loại phòng thành công.");
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    public void xoaPhong(String maPhong) {
        PhongChieu p = findByMa(maPhong);
        if (p == null) { System.out.println("Không tìm thấy phòng."); return; }
        dsPhong.remove(p);
        System.out.println("Đã xoá phòng " + maPhong);
    }

    // thêm ghế cho phòng
    public void themGheChoPhong(String maPhong, Ghe g) {
        PhongChieu p = findByMa(maPhong);
        if (p == null) { System.out.println("Không tìm thấy phòng."); return; }
        p.themGhe(g);
        System.out.println("Đã thêm ghế " + g.getSoGhe() + " vào phòng " + maPhong);
    }

    // ----------------- ĐỌC / GHI FILE (IRW) -----------------
    @Override
    public void writeData(String fileName) {
        // nếu fileName null -> dùng FILE_PHONG
        String fn = (fileName == null || fileName.isEmpty()) ? FILE_PHONG : fileName;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fn))) {
            for (PhongChieu p : dsPhong) {
                bw.write(p.toString());
                bw.newLine();
            }
            System.out.println("Ghi file phòng thành công -> " + fn);
        } catch (IOException e) {
            System.out.println("Lỗi ghi file phòng: " + e.getMessage());
        }
    }

    @Override
    public void readData(String fileName) {
        String fn = (fileName == null || fileName.isEmpty()) ? FILE_PHONG : fileName;
        dsPhong.clear();
        File f = new File(fn);
        if (!f.exists()) {
            System.out.println("File phòng không tồn tại: " + fn);
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(fn))) {
            String line;
            while ((line = br.readLine()) != null) {
                PhongChieu p = PhongChieu.parse(line);
                if (p != null) dsPhong.add(p);
            }
            System.out.println("Đọc file phòng thành công -> " + fn);
        } catch (IOException e) {
            System.out.println("Lỗi đọc file phòng: " + e.getMessage());
        }
    }

    // helper
    public List<PhongChieu> getDsPhong() { return dsPhong; }
}

