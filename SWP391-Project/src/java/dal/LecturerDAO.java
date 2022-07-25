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
import model.Lecturer;

/**
 *
 * @author Admin
 */
public class LecturerDAO extends DBContext {

    public List<Lecturer> getAll() {
        String sql = "select * from Lecturer";
        List<Lecturer> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Lecturer(rs.getInt("lecturer_id"), rs.getString("lecturer_name")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Lecturer getLectByLectID(int lid) {
        String sql = "select * from Lecturer where lecturer_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, lid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Lecturer l = new Lecturer();
                l.setLid(rs.getInt("lecturer_id"));
                l.setLname(rs.getString("lecturer_name"));
                return l;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Lecturer getLectByLectName(String name) {
        String sql = "select * from Lecturer where lecturer_name = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Lecturer l = new Lecturer();
                l.setLid(rs.getInt("lecturer_id"));
                l.setLname(rs.getString("lecturer_name"));
                return l;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void insertLec(String sname) {
        //insert Lecturer values ('abc')
        String sql = "insert Lecturer values (?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, sname);
            st.executeUpdate();//1 or 0
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
    }
}
