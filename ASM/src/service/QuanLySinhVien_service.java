/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author Admin
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.SinhVien;
public class QuanLySinhVien_service {
    List<SinhVien> listSV;
    Connection con=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    String sql=null;
    
    public List<SinhVien> getAll(){
        listSV=new ArrayList<>();
        sql="select MASV,HoTen,Email,SoDT,GioiTinh,DiaChi,Hinh from STUDENTS";
        try {
            con=DBConnect.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                SinhVien sv= new SinhVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7));
                listSV.add(sv);
            }
            return listSV;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
      finally{
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }  
    }
    public int saveSV(SinhVien sv){
        int kq=0;
       
        try {
           
            sql="insert into STUDENTS(MASV,HoTen,Email,SoDT,GioiTinh,DiaChi) values(?,?,?,?,?,?)";
            con=DBConnect.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, sv.getMasv());
            ps.setString(2, sv.getHoten());
            ps.setString(3, sv.getEmail());
            ps.setString(4, sv.getSoDT());
            ps.setInt(5, sv.getGioiTinh());
            ps.setString(6, sv.getDiaChi());
            kq=ps.executeUpdate();
            ps.close();
            String sql2="insert into GRADE(MASV) values (?)";
            ps=con.prepareStatement(sql2);
            ps.setString(1, sv.getMasv());
            ps.executeUpdate();
            ps.close();
               
        } catch (Exception e) {
            e.printStackTrace();
            kq=0;
        }
        return kq;
    }
    
//    public boolean checkma(String ma){
//        try {
//            sql="select MASV,HoTen,Email,SoDT,GioiTinh,DiaChi,Hinh  from STUDENTS ";
//            con=DBConnect.getConnection();
//            ps=con.prepareStatement(sql);
//            return ps.executeQuery().next();
//        
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    
//    }
    public int  deleteSV(String ma){
        int kq=0;
  
        try {
        sql="delete from GRADE where MASV=?"; 
        con=DBConnect.getConnection();
        ps=con.prepareStatement(sql);
        ps.setString(1, ma);
        kq=ps.executeUpdate();
        ps.close();
        
        sql="delete from STUDENTS where MASV=?";
        ps=con.prepareStatement(sql);
        ps.setString(1, ma);
        kq=ps.executeUpdate();
        ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            return kq=0;
        }
        return kq;
    }
    public int update(SinhVien sv,String ma){
        int kq=0;
        sql="update STUDENTS set HoTen=?,Email=?,SoDT=?,GioiTinh=?,DiaChi=? where MASV like ?";
        try {
            con=DBConnect.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, sv.getHoten());
            ps.setString(2, sv.getEmail());
            ps.setString(3, sv.getSoDT());
            ps.setInt(4, sv.getGioiTinh());
            ps.setString(5, sv.getDiaChi());
            ps.setString(6, ma);
            kq=ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return kq=0;
        }
        return kq;
    }
    
    public SinhVien getAt(int index){
        return listSV.get(index);
    }
}
