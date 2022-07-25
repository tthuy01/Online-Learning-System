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
import model.Level;

/**
 *
 * @author Admin
 */
public class LevelDAO extends DBContext {

    public List<Level> getAll() {
        String sql = "Select * from Level";
        List<Level> list=new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                list.add(new Level(rs.getInt("level_id"), rs.getString("level_name")));
            }
        } catch (Exception e) {
        }
        return list;
    }
    public static void main(String[] args) {
        LevelDAO ld=new LevelDAO();
        System.out.println(ld.getAll().get(1).getLname());
    }
}
