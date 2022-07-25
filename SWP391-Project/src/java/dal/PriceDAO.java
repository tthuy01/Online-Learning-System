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
import model.Price;

public class PriceDAO extends DBContext {
    public List<Price> getAll() {
        String sql = "Select * from Price_Package";
        List<Price> list=new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                list.add(new Price(rs.getInt("package_id"), rs.getString("package_name"),
                rs.getInt("duration"), rs.getBoolean("pack_status")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    } 
    
    //Iteration 4: Thuy
    public List<Price> getAllPricePackage() {
        String sql = "Select * from Price_Package";
        List<Price> list=new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                list.add(new Price(rs.getInt("package_id"), rs.getString("package_name"),
                rs.getInt("duration"), rs.getBoolean("pack_status"), rs.getFloat("multiple"), rs.getString("description")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void InsertNewPricePackage(Price p) {
        String sql = "insert into Price_Package values(?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, p.getPackname());
            st.setInt(2, p.getDuration());
            st.setBoolean(3, p.isPackstatus());
            st.setFloat(4, p.getMultiple());
            st.setString(5, p.getDescription());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void UpdatePricePackage(Price p)
    {
        String sql = "update Price_Package set package_name = ?, duration = ?, pack_status = ?, "
                        + "multiple = ?, description = ? where package_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, p.getPackname());
            st.setInt(2, p.getDuration());
            st.setBoolean(3, p.isPackstatus());
            st.setFloat(4, p.getMultiple());
            st.setString(5, p.getDescription());
            st.setInt(6, p.getPackid());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public Price GetPricepackageByPId(int pid)
    {
        Price p = new Price();
        String sql = "select * from Price_Package where package_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, pid);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                p.setPackid(pid);
                p.setPackname(rs.getString("package_name"));
                p.setDuration(rs.getInt("duration"));
                p.setPackstatus(rs.getBoolean("pack_status"));
                p.setMultiple(rs.getFloat("multiple"));
                p.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return p;
    }
    
    public static void main(String[] args) {
        PriceDAO pd = new PriceDAO();
        System.out.println(pd.getAll().get(0).getPackname());
    }
}
