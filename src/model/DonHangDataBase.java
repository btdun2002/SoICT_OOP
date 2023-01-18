package model;

public class DonHangDataBase {
    String ten;
    String diaChi;
    int ID;

    public String getTen() {
        return ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getThoiGianThem() {
        return ThoiGianThem;
    }

    public Double getDienTich() {
        return dienTich;
    }

    public Double getChiPhi1m2() {
        return ChiPhi1m2;
    }

    public Double getTongPhi() {
        return TongPhi;
    }

    public String getTenBang() {
        return tenBang;
    }

    String ThoiGianThem;
    Double dienTich;
    Double ChiPhi1m2;
    Double TongPhi;
    String tenBang;

    public DonHangDataBase(int ID, String ten, String diaChi, String thoiGianThem, Double dienTich, Double chiPhi1m2, Double tongPhi, String tenBang) {
        this.ID = ID;
        this.ten = ten;
        this.diaChi = diaChi;
        ThoiGianThem = thoiGianThem;
        this.dienTich = dienTich;
        ChiPhi1m2 = chiPhi1m2;
        TongPhi = tongPhi;
        this.tenBang = tenBang;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setThoiGianThem(String thoiGianThem) {
        ThoiGianThem = thoiGianThem;
    }

    public void setDienTich(Double dienTich) {
        this.dienTich = dienTich;
    }

    public void setChiPhi1m2(Double chiPhi1m2) {
        ChiPhi1m2 = chiPhi1m2;
    }

    public void setTongPhi(Double tongPhi) {
        TongPhi = tongPhi;
    }

    public void setTenBang(String tenBang) {
        this.tenBang = tenBang;
    }
}
