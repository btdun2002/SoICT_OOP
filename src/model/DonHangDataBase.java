package model;

public class DonHangDataBase {
    String ten;
    String diaChi;

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

    public DonHangDataBase(String ten, String diaChi, String thoiGianThem, Double dienTich, Double chiPhi1m2, Double tongPhi, String tenBang) {
        this.ten = ten;
        this.diaChi = diaChi;
        ThoiGianThem = thoiGianThem;
        this.dienTich = dienTich;
        ChiPhi1m2 = chiPhi1m2;
        TongPhi = tongPhi;
        this.tenBang = tenBang;
    }
}
