package KIMLOI_CANHAN;

import java.io.*;
import java.util.*;

public class QuanLyPhim implements IRW {
    private List<Phim> dsPhim = new ArrayList<>();
    public static final String FILE_PHIM = "Phim.txt";

    public void themPhim(Phim p) {
        dsPhim.add(p);
        System.out.println("Đã thêm phim: " + p.getTenPhim());
    }

    public void hienThiTatCa() {
        if (dsPhim.isEmpty()) { System.out.println("Chưa có phim."); return; }
        for (int i = 0; i < dsPhim.size(); i++) {
            System.out.print((i+1) + ". ");
            dsPhim.get(i).hienThiThongTin();
            System.out.println("--------------");
        }
    }

    public Phim timPhimTheoTen(String ten) {
        for (Phim p : dsPhim) if (p.getTenPhim().equalsIgnoreCase(ten)) return p;
        return null;
    }

    public boolean xoaPhimTheoTen(String ten) {
        Phim p = timPhimTheoTen(ten);
        if (p == null) return false;
        dsPhim.remove(p);
        return true;
    }

    public void capNhatPhim(String ten, String tenMoi, int thoiLuongMoi, String theLoaiMoi) {
        Phim p = timPhimTheoTen(ten);
        if (p == null) { System.out.println("Không tìm thấy phim."); return; }
        p.setTenPhim(tenMoi);
        p.setThoiLuong(thoiLuongMoi);
        p.setTheLoai(theLoaiMoi);
        System.out.println("Cập nhật phim thành công.");
    }

    // ---------------- IRW ----------------
    @Override
    public void writeData(String fileName) {
        String fn = (fileName == null || fileName.isEmpty()) ? FILE_PHIM : fileName;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fn))) {
            for (Phim p : dsPhim) {
                bw.write(p.toString());
                bw.newLine();
            }
            System.out.println("Ghi file phim thành công -> " + fn);
        } catch (IOException e) {
            System.out.println("Lỗi ghi file phim: " + e.getMessage());
        }
    }

    @Override
    public void readData(String fileName) {
        String fn = (fileName == null || fileName.isEmpty()) ? FILE_PHIM : fileName;
        dsPhim.clear();
        File f = new File(fn);
        if (!f.exists()) {
            System.out.println("File phim không tồn tại: " + fn);
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(fn))) {
            String line;
            while ((line = br.readLine()) != null) {
                // định dạng: NL;ten;thoi;theLoai;gioiHan  hoặc TE;ten;thoi;theLoai;danhMuc
                String[] a = line.split(";", -1);
                if (a.length < 5) continue;
                if (a[0].equalsIgnoreCase("NL")) {
                    PhimNguoiLon nl = new PhimNguoiLon(a[1], Integer.parseInt(a[2]), a[3], Integer.parseInt(a[4]));
                    dsPhim.add(nl);
                } else if (a[0].equalsIgnoreCase("TE")) {
                    PhimTreEm te = new PhimTreEm(a[1], Integer.parseInt(a[2]), a[3], a[4]);
                    dsPhim.add(te);
                } else {
                    // fallback: generic phim
                    Phim p = new Phim(a[1], Integer.parseInt(a[2]), a[3]);
                    dsPhim.add(p);
                }
            }
            System.out.println("Đọc file phim thành công -> " + fn);
        } catch (IOException e) {
            System.out.println("Lỗi đọc file phim: " + e.getMessage());
        }
    }

    // helper
    public List<Phim> getDsPhim() { return dsPhim; }
}

