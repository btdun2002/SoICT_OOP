package model;

public class BangTron extends Bang {

    public BangTron(int dienTich, int phiMotMet) {
        super(dienTich, phiMotMet);
    }

    @Override
    public double getChiPhi() {
        return super.getDienTich()*super.getPhiMotMet()+5000000;
    }
}
