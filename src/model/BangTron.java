package model;

public class BangTron extends Bang {
    protected double phiTang = 500000;
    public BangTron(double dienTich) {
        super(dienTich);
    }

    @Override
    public double getChiPhi() {
        return super.getDienTich()*super.getPhiMotMet()+phiTang;
    }

    @Override
    public String getNameBang() {
        return "Circle";
    }

    public double getPhiTang() {
        return phiTang;
    }

    public void setPhiTang(double phiTang) {
        this.phiTang = phiTang;
    }
}
