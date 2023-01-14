package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Controller {
    String url = "jdbc:mysql://localhost:3306/oop";
    String username= "root";
    String password = "";
    public void themBangThuong(){
        Scanner kb = new Scanner(System.in);
        DonHang a = new DonHang();
        Bang b = new Bang();
        System.out.println("Nhap vao Ho ten khach hang:");
        a.setTen(kb.nextLine());
        System.out.println("Nhap vao dia chi khach hang:");
        a.setDiaChi(kb.nextLine());
        System.out.println("Nhap vao dien tich: ");
        b.setDienTich(kb.nextDouble());
        System.out.println("Nhap vao phi lap dat 1m2: ");
        b.setPhiMotMet(kb.nextDouble());
        kb.nextLine();
        System.out.println("Nhap vao thoi gian dat hang:(Format: yyyy-mm-dd)");
        a.setThoiGianThem(kb.nextLine());
        a.setBang(b);
        try (Connection conn = DriverManager.getConnection(url,username,password)){
            String Insert = "INSERT INTO `congtyquangcao`(`CustomerName`, `Address`, `ThoiGianThem`, `LoaiBang`, `ChiPhi`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(Insert);
            ps.setString(1,a.getTen());
            ps.setString(2,a.getDiaChi());
            ps.setString(3,a.getThoiGianThem());
            ps.setString(4,"Bang Thuong");
            ps.setDouble(5,a.getBang().getChiPhi());
            ps.execute();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
