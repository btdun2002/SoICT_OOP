package model;

public class BangTamGiac extends Bang {
    public BangTamGiac(int dienTich, int phiMotMet) {
        super(dienTich, phiMotMet);
    }

    @Override
    public double getChiPhi() {
        return super.getDienTich()*super.getPhiMotMet()+5000000;
    }
}
