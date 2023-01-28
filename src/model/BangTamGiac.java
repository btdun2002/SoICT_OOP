package model;

public class BangTamGiac extends Bang {
    public static double phiTang = 500000;

    public BangTamGiac(double dienTich) {
        super(dienTich);
        super.tenBang = "Circle";
    }

    @Override
    public double getChiPhi() {
        return super.getDienTich() * super.getPhiMotMet() + phiTang;
    }

    @Override
    public String getNameBang() {
        return "Triangle";
    }

}
