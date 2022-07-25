/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ManageCourse;

public class ManageCourseDAO extends DBContext{
    public List<ManageCourse> getAll() {
        String sql = "select * from Manage_Course";
        List<ManageCourse> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ManageCourse mc = new ManageCourse();
                mc.setCid(rs.getString(1));
                mc.setUid(rs.getInt(2));
                mc.setFeature(rs.getString(3));
                list.add(mc);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }   
    
    public void insertManageCourseByAdmin(ManageCourse s){
        //insert into Manage_Course values(cid, uid, View only)
        String sql="insert into Manage_Course values(?, ?, 'View only')";
        try{
             PreparedStatement st=connection.prepareStatement(sql);
             st.setString(1, s.getCid());
             st.setInt(2, s.getUid());
             st.executeUpdate();//1 or 0
        }catch(SQLException e){
            System.out.println(e);
        }
    } 
    
    public static void main(String[] args) {
        ManageCourseDAO mcd = new ManageCourseDAO();
        List<ManageCourse> ls = mcd.getAll();
        for (ManageCourse mc : ls) {
            System.out.println(mc.getCid());
        }
    }
}
