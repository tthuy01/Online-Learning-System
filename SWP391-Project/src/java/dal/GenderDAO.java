/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;
import model.Gender;

public class GenderDAO extends DBContext {

    public List<Gender> getAll() {
        String sql = "Select * from Gender";
        List<Gender> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Gender(rs.getInt("gender_id"), rs.getString("gender_name")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    //Iteration 3: Thuy
    public Gender getGenderByGenId(int gid) {
        String sql = "select * from Gender where gender_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, gid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Gender(rs.getInt("gender_id"), rs.getString("gender_name"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        GenderDAO gd = new GenderDAO();
        System.out.println(gd.getAll().get(1).getGname());
    }
}
