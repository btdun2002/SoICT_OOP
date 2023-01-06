package model;

public class Bang {
    int dienTich;
    int phiMotMet;

    public Bang() {
    }

    public Bang(int dienTich, int phiMotMet) {
        this.dienTich = dienTich;
        this.phiMotMet = phiMotMet;
    }

    public int getDienTich() {
        return dienTich;
    }

    public void setDienTich(int dienTich) {
        this.dienTich = dienTich;
    }

    public int getPhiMotMet() {
        return phiMotMet;
    }

    public void setPhiMotMet(int phiMotMet) {
        this.phiMotMet = phiMotMet;
    }

    public int getPhi() {
        return phiMotMet * dienTich;
    }
}
