package model;

public class Bang {
    double dienTich;
    double phiMotMet;
    String tenBang;


    public Bang() {
    }

    public Bang(double dienTich, double phiMotMet) {
        this.dienTich = dienTich;
        this.phiMotMet = phiMotMet;

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

    public void setPhiMotMet(double phiMotMet) {
        this.phiMotMet = phiMotMet;
    }
    public String getNameBang(){
        return "Normal";
    }
    public double getChiPhi() {
        return phiMotMet * dienTich;
    }
}
