
package KIMLOI_CANHAN;

import java.util.*;

public class QuanLySuatChieu {
    private List<SuatChieu> dsSC = new ArrayList<>();

    public void them(SuatChieu sc) { dsSC.add(sc); }

    public SuatChieu tim(String ma) {
        for (SuatChieu s : dsSC)
            if (s.getMaSC().equalsIgnoreCase(ma)) return s;
        return null;
    }

    public List<SuatChieu> getDs() { return dsSC; }
}

