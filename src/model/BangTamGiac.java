package model;

public class BangTamGiac extends Bang {
    public BangTamGiac(double dienTich, double phiMotMet) {
        super(dienTich, phiMotMet);
        super.tenBang="Circle";
    }

    @Override
    public double getChiPhi() {
        return super.getDienTich()*super.getPhiMotMet()+500000;
    }

    @Override
    public String getNameBang() {
        return "Triangle";
    }
}
