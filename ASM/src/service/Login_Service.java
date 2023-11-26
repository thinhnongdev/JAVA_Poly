/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.Login;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Login_Service {

    List<Login> list=new ArrayList<>();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public List<Login> dangNhap(String user, String passWord) {
        sql = "select username,password,role from USERS where username like ? and password like ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, user);
            ps.setObject(2, passWord);
            
            rs = ps.executeQuery();
            while (rs.next()) {
                Login login= new Login(rs.getString(1), rs.getString(2), rs.getString(3));
                list.add(login);
            }
            return list;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        
    }

}
