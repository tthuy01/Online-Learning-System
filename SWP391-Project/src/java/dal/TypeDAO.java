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
import model.Type;

/**
 *
 * @author ADMIN
 */
public class TypeDAO extends DBContext{
    
    public List<Type> getAll(){
        String sql = "select * from Type";
        List<Type> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Type t = new Type();
                t.setTid(rs.getInt(1));
                t.setTname(rs.getString(2));
                list.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public Type getTypeById(int tid){
        String sql = "select * from Type where type_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, tid);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                return new Type(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
