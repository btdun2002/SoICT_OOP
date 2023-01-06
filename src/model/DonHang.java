package model;

public class DonHang {
    String ten;
    String diaChi;
    Bang bang;

    public DonHang(String ten, String diaChi, Bang bang) {
        this.ten = ten;
        this.diaChi = diaChi;
        this.bang = bang;
    }

    public DonHang() {}

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Bang getBang() {
        return bang;
    }

    public void setBang(Bang bang) {
        this.bang = bang;
    }
}
