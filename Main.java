package KIMLOI_CANHAN;

import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    private static QuanLyPhim qlPhim = new QuanLyPhim();
    private static QuanLyPhong qlPhong = new QuanLyPhong();
    private static QuanLySuatChieu qlSC = new QuanLySuatChieu();
    private static QuanLyVe qlVe = new QuanLyVe();

    public static void main(String[] args) {

        boolean running = true;

        while (running) {
            System.out.println("\n========= QUAN LY RAP CHIEU PHIM =========");
            System.out.println("1. quan ly phim");
            System.out.println("2. quan ly phong chieu");
            System.out.println("3. quan ly suat chieu");
            System.out.println("4. quan ly ve");
            System.out.println("5. ghi toan bo du lieu file");
            System.out.println("6. doc toan bo du lieu file");
            System.out.println("0. thoat chuong trinh");
            System.out.print("Chon: ");

            String ch = sc.nextLine().trim();

            switch (ch) {
                case "1": menuPhim(); break;
                case "2": menuPhong(); break;
                case "3": menuSuatChieu(); break;
                case "4": menuVe(); break;
                case "5": writeAll(); break;
                case "6": readAll(); break;
                case "0": running = false; break;
                default: System.out.println("❌ Lua chon khong hop le!");
            }
        }

        System.out.println("---- KET THUC CHUONG TRINH ----");
    }


   
    private static void menuPhim() {
        while (true) {
            System.out.println("\n----- QUAN LY PHIM -----");
            System.out.println("1. them phim nguoi lon");
            System.out.println("2. them phim tre em");
            System.out.println("3. hien thi tat ca phim");
            System.out.println("4. tim phim theo ten");
            System.out.println("5. xoa phim theo ten");
            System.out.println("0. quay lai");
            System.out.print("Chon: ");

            String c = sc.nextLine();

            switch (c) {
                case "1": themPhimNguoiLon(); break;
                case "2": themPhimTreEm(); break;
                case "3": 
                    for (Phim p : qlPhim.getDs()) {
                        p.hienThi();
                        System.out.println("----------------");
                    }
                    break;
                case "4": 
                    System.out.print("Nhap ten can tim: ");
                    Phim f = qlPhim.tim(sc.nextLine());
                    if (f != null) f.hienThi();
                    else System.out.println("❌ Khong tim thay phim!");
                    break;
                case "5":
                    System.out.print("Nhap ten can xoa: ");
                    Phim px = qlPhim.tim(sc.nextLine());
                    if (px != null) {
                        qlPhim.getDs().remove(px);
                        System.out.println("✔ Da xoa!");
                    } else System.out.println("❌ Khong tim thay!");
                    break;
                case "0": return;
                default: System.out.println("Sai lua chon!");
            }
        }
    }

    private static void themPhimNguoiLon() {
        System.out.print("Tên phim: "); String ten = sc.nextLine();
        System.out.print("Thời lượng: "); int tl = Integer.parseInt(sc.nextLine());
        System.out.print("Thể loại: "); String tlr = sc.nextLine();
        System.out.print("Giới hạn tuổi: "); int gh = Integer.parseInt(sc.nextLine());

        qlPhim.themPhim(new PhimNguoiLon(ten, tl, tlr, gh));
    }

    private static void themPhimTreEm() {
        System.out.print("Tên phim: "); String ten = sc.nextLine();
        System.out.print("Thời lượng: "); int tl = Integer.parseInt(sc.nextLine());
        System.out.print("Thể loại: "); String tlr = sc.nextLine();
        System.out.print("Danh mục tre em: "); String dm = sc.nextLine();

        qlPhim.themPhim(new PhimTreEm(ten, tl, tlr, dm));
    }



    // ===========================================================
    // MENU PHÒNG CHIẾU
    // ===========================================================
    private static void menuPhong() {
        while (true) {
            System.out.println("\n----- QUAN LY PHONG CHIEU -----");
            System.out.println("1. them phong");
            System.out.println("2. hien thi tat ca phong");
            System.out.println("3. tim phong theo ma");
            System.out.println("4. them ghe vao phong");
            System.out.println("0. quay lai");
            System.out.print("Chon: ");

            String c = sc.nextLine();

            switch (c) {
                case "1": themPhong(); break;
                case "2": showPhong(); break;
                case "3": timPhong(); break;
                case "4": themGhePhong(); break;
                case "0": return;
                default: System.out.println("Sai lua chon!");
            }
        }
    }

    private static void themPhong() {
        try {
            System.out.print("Ma phong: "); 
            String ma = sc.nextLine();
            System.out.print("Loai phong (2D/3D/IMAX): ");
            String loai = sc.nextLine();

            PhongChieu p = new PhongChieu(ma, loai);
            qlPhong.themPhong(p);
        } catch (Exception e) {
            System.out.println("❌ LOi: " + e.getMessage());
        }
    }

    private static void showPhong() {
        for (PhongChieu p : qlPhong.getDsPhong()) {
            System.out.println(p);
            for (Ghe g : p.getDsGhe()) {
                System.out.println("  - " + g);
            }
            System.out.println("-------------------");
        }
    }

    private static void timPhong() {
        System.out.print("Nhap ma: ");
        PhongChieu p = qlPhong.findByMa(sc.nextLine());
        if (p != null) {
            System.out.println(p);
            p.getDsGhe().forEach(g -> System.out.println("  - " + g));
        } else System.out.println("❌ Khong tim thay!");
    }

    private static void themGhePhong() {
        System.out.print("Ma phong: "); String mp = sc.nextLine();
        PhongChieu p = qlPhong.findByMa(mp);

        if (p == null) { System.out.println("❌ Khong tim thay phong!"); return; }

        try {
            System.out.print("So ghe: "); String sg = sc.nextLine();
            System.out.print("Loai ghe (Thuong/VIP): "); String lg = sc.nextLine();
            p.themGhe(new Ghe(sg, lg));
            System.out.println("✔ them ghe thanh cong!");

        } catch (Exception e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
        }
    }



    
    private static void menuSuatChieu() {
        while (true) {
            System.out.println("\n----- QUAN LY SUAT CHIEU-----");
            System.out.println("1. them suat chieu");
            System.out.println("2. hien thi tat ca suat chieu");
            System.out.println("0. quay lai");
            System.out.print("Chon: ");

            switch (sc.nextLine()) {
                case "1": themSC(); break;
                case "2": 
                    for (SuatChieu s : qlSC.getDs())
                        System.out.println(s);
                    break;
                case "0": return;
                default: System.out.println("Sai lua chon!");
            }
        }
    }

    private static void themSC() {
        System.out.print("Ma suat chieu: "); 
        String ma = sc.nextLine();

        System.out.print("TTen phim: ");
        Phim p = qlPhim.tim(sc.nextLine());
        if (p == null) { System.out.println("❌ Khong tim thay phim!"); return; }

        System.out.print("Ma phong: ");
        PhongChieu pc = qlPhong.findByMa(sc.nextLine());
        if (pc == null) { System.out.println("❌ Khong tim thay phong!"); return; }

        System.out.print("Thoi gian (dd/mm hh:mm): ");
        String tg = sc.nextLine();

        SuatChieu scTemp = new SuatChieu(ma, p, pc, tg);

        qlSC.them(scTemp);
        System.out.println("✔ them suat chieu thanh cong!");
    }



    
    private static void menuVe() {
        while (true) {
            System.out.println("\n----- QUAN LY VE -----");
            System.out.println("1. them ve");
            System.out.println("2. hien thi tat ca ve");
            System.out.println("0. quay lai");
            System.out.print("Chon: ");

            String c = sc.nextLine();

            switch (c) {
                case "1": themVe(); break;
                case "2": qlVe.hienThiTatCa(); break;
                case "0": return;
                default: System.out.println("Sai lua chon!");
            }
        }
    }

    private static void themVe() {
        System.out.print("Mã vé: ");
        String mv = sc.nextLine();

        System.out.print("Ma suat chieu: ");
        SuatChieu scTemp = qlSC.tim(sc.nextLine());
        if (scTemp == null) { System.out.println("❌ Khong tim thay suat chieu"); return; }

        System.out.print("So ghe: "); 
        String sg = sc.nextLine();

        System.out.print("Loai ghe (Thuong/VIP): "); 
        String lg = sc.nextLine();

        System.out.print("Gia ve: ");
        double gia = Double.parseDouble(sc.nextLine());

        Ghe g = new Ghe(sg, lg);
        Ve v = new Ve(mv, scTemp, g, gia);

        qlVe.themVe(v);
        System.out.println("✔ them ve thanh cong!");
    }




    
    private static void writeAll() {
        qlPhim.writeData(null);
        qlPhong.writeData(null);
        qlVe.writeData(null);
        System.out.println("✔ Ghi toan bo file thanh cong!");
    }

    private static void readAll() {
        qlPhim.readData(null);
        qlPhong.readData(null);
        qlVe.readData(null);
        System.out.println("✔ Doc toan bo file thanh cong!");
    }
}
