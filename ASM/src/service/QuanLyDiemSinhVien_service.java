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
import model.DiemSV;

public class QuanLyDiemSinhVien_service {

    List<DiemSV> list;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";

    public List<DiemSV> getAll() {
        list = new ArrayList<>();
        sql = "select top(3) GRADE.MASV,STUDENTS.HoTen,TiengAnh,TinHoc,GDTC,(TiengAnh+TinHoc+GDTC)/3 as DiemTB from GRADE inner join STUDENTS on GRADE.MASV=STUDENTS.MASV order by DiemTB desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DiemSV diem = new DiemSV(rs.getString(2), rs.getString(1), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
                list.add(diem);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int Update(String ma, DiemSV diem) {
        int kq = 0;
        sql = "update GRADE set TiengAnh=?,TinHoc=?,GDTC=? where MASV like?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, diem.getTiengAnh());
            ps.setInt(2, diem.getTinHoc());
            ps.setInt(3, diem.getGDTC());
            ps.setString(4, ma);
            kq = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return kq = 0;
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return kq;
    }

    public int deleteDiem(String ma) {
        int kq = 0;
        sql = "update GRADE set TiengAnh=null,TinHoc=null,GDTC=null where MASV like ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            kq = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            kq = 0;
        }
        return kq;

    }

    public DiemSV serchID(String ma) {

        List<DiemSV> list = new ArrayList<>();
        sql = "select GRADE.MASV,STUDENTS.HoTen,TiengAnh,TinHoc,GDTC,(TiengAnh+TinHoc+GDTC)/3 as DiemTB from GRADE inner join STUDENTS on GRADE.MASV=STUDENTS.MASV where GRADE.MASV like ? ";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                DiemSV diem = new DiemSV(rs.getString(2), rs.getString(1), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
                list.add(diem);
            }
            if (list.size() > 0) {
                return list.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public DiemSV getAll2(int index) {
        list = new ArrayList<>();
        sql = "select GRADE.MASV,STUDENTS.HoTen,TiengAnh,TinHoc,GDTC,(TiengAnh+TinHoc+GDTC)/3 as DiemTB from GRADE inner join STUDENTS on GRADE.MASV=STUDENTS.MASV order by DiemTB desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DiemSV diem = new DiemSV(rs.getString(2), rs.getString(1), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
                list.add(diem);
            }
            if (index == 4) {
                return list.get(list.size() - 1);
            } else if (index == 0) {
                return list.get(0);
            }else if (index >= list.size()-1) {
                return list.get(list.size() - 1);
            }else if (index < list.size() - 1) {
                return list.get(index);
            }  else {
                return null;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public DiemSV getAt(int index) {

        return list.get(index);
    }
}
