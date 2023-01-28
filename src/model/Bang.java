package model;

public class Bang {
    protected double dienTich;
    public static double phiMotMet = 30000;
    protected String tenBang;


    public Bang() {
    }

    public Bang(double dienTich) {
        this.dienTich = dienTich;
    }

    public double getDienTich() {
        return dienTich;
    }

    public void setDienTich(double dienTich) {
        this.dienTich = dienTich;
    }

    public double getPhiMotMet() {
        return phiMotMet;
    }

    public String getNameBang() {
        return "Normal";
    }

    public double getChiPhi() {
        return phiMotMet * dienTich;
    }

    public void setPhiMotMet(double phiMotMet) {
        this.phiMotMet = phiMotMet;
    }
}
