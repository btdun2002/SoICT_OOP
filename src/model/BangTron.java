package model;

public class BangTron extends Bang {

    public BangTron(double dienTich, double phiMotMet) {
        super(dienTich, phiMotMet);
    }

    @Override
    public double getChiPhi() {
        return super.getDienTich()*super.getPhiMotMet()+5000000;
    }

    @Override
    public String getNameBang() {
        return "Circle";
    }
}
