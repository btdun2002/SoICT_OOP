package model;

public class BangTron extends Bang {
    public static double phiTang = 500000;
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
   }
