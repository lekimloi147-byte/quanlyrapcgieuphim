package KIMLOI_CANHAN;

import java.io.*;
import java.util.*;

public class QuanLyVe implements IRW {
    private List<Ve> dsVe = new ArrayList<>();
    public static final String FILE_VE = "Ve.txt";

    // thêm vé
    public void themVe(Ve v) {
        dsVe.add(v);
        System.out.println("Đã thêm vé: " + v.getMaVe());
    }

    public void hienThiTatCa() {
        if (dsVe.isEmpty()) {
            System.out.println("Chưa có vé nào.");
            return;
        }
        for (Ve v : dsVe) {
            v.hienThi();
            System.out.println("----------------");
        }
    }

    public Ve timVeTheoMa(String ma) {
        for (Ve v : dsVe)
            if (v.getMaVe().equalsIgnoreCase(ma))
                return v;
        return null;
    }

    public boolean xoaVeTheoMa(String ma) {
        Ve v = timVeTheoMa(ma);
        if (v == null) return false;
        dsVe.remove(v);
        return true;
    }

    public void capNhatGiaVe(String ma, double gia) {
        Ve v = timVeTheoMa(ma);
        if (v == null) {
            System.out.println("Không tìm thấy vé.");
            return;
        }
        v.setGia(gia);
        System.out.println("Cập nhật giá vé thành công.");
    }

    // ---------------- IRW ----------------
    @Override
    public void writeData(String fileName) {
        String fn = (fileName == null || fileName.isEmpty()) ? FILE_VE : fileName;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fn))) {
            for (Ve v : dsVe) {
                bw.write(v.toString());
                bw.newLine();
            }
            System.out.println("Ghi file vé thành công -> " + fn);
        } catch (IOException e) {
            System.out.println("Lỗi ghi file vé: " + e.getMessage());
        }
    }

    @Override
    public void readData(String fileName) {
        String fn = (fileName == null || fileName.isEmpty()) ? FILE_VE : fileName;

        dsVe.clear();
        File f = new File(fn);
        if (!f.exists()) {
            System.out.println("File vé không tồn tại: " + fn);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fn))) {
            String line;
            while ((line = br.readLine()) != null) {
                // maVe|maSC|A1:VIP|gia
                String[] a = line.split("\\|");

                if (a.length < 4) continue;

                // parse ghế
                Ghe g = Ghe.parse(a[2]);

                // parse suất không đủ để tạo object => chỉ lưu mã
                SuatChieu sc = new SuatChieu(a[1], null, null, "UNKNOWN");

                Ve v = new Ve(a[0], sc, g, Double.parseDouble(a[3]));

                dsVe.add(v);
            }
            System.out.println("Đọc file vé thành công -> " + fn);

        } catch (IOException e) {
            System.out.println("Lỗi đọc file vé: " + e.getMessage());
        }
    }

    public List<Ve> getDsVe() { return dsVe; }
}

