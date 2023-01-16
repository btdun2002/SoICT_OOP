package model;

public class BangTamGiac extends Bang {
    public BangTamGiac(double dienTich, double phiMotMet) {
        super(dienTich, phiMotMet);
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
