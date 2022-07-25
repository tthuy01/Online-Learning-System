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
import model.Section;

/**
 *
 * @author ADMIN
 */
public class SectionDAO extends DBContext {

    public List<Section> getAll() {
        String sql = "select * from Section where section_status = 1";
        List<Section> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Section s = new Section();
                s.setSid(rs.getInt(1));
                s.setCid(rs.getString(2));
                s.setSname(rs.getString(3));
                s.setTid(rs.getInt(4));
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Section> getListSectionByCourseID(String cid) {
        String sql = "select * from Section where course_id = ? and section_status = 1";
        List<Section> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Section s = new Section();
                s.setSid(rs.getInt(1));
                s.setSname(rs.getString(3));
                s.setCid(rs.getString(2));
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<Section> getListSectionByCourseIDAR(String cid) {
        String sql = "select * from Section where course_id = ?";
        List<Section> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Section s = new Section();
                s.setSid(rs.getInt(1));
                s.setSname(rs.getString(3));
                s.setCid(rs.getString(2));
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public Section getSectionById(int sid) {
        String sql = "select * from Section where section_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, sid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Section(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void updateSection(Section s) {
        String sql = "update Section set course_id = ? , section_name = ? , type_id = ? where section_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, s.getCid());
            st.setString(2, s.getSname());
            st.setInt(3, s.getTid());
            st.setInt(4, s.getSid());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void insertSection(Section s) {
        String sql = "insert into Section(course_id,section_name,type_id,section_status) values (?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, s.getCid());
            st.setString(2, s.getSname());
            st.setInt(3, s.getTid());
            st.setInt(4, 1);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    // Dao iter 4 ---------------admin/expert role ------------------------------------------------------------------------------------------------------
    public List<Section> getListSectionByCidAR(String cid, int sid, int status) {
        String sql = "select * from Section where course_id = ?";
        if (sid != 0) {
            sql += " and section_id = " + sid;
        }
        if (status != 2) {
            sql += " and section_status = " + status;
        }
        List<Section> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Section s = new Section();
                s.setSid(rs.getInt(1));
                s.setCid(rs.getString(2));
                s.setSname(rs.getString(3));
                s.setTid(rs.getInt(4));
                s.setSstatus(rs.getBoolean(5));
                list.add(s);
            }
        } catch (SQLException e) {
        }
        return list;
    }
    public void updateSectionAR(Section s) {
        String sql = "update Section set course_id = ? , section_name = ? , type_id = ? , section_status= ? where section_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, s.getCid());
            st.setString(2, s.getSname());
            st.setInt(3, s.getTid());
            st.setBoolean(4, s.isSstatus());
            st.setInt(5, s.getSid());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Dao iter 4 ---------------admin/expert role ------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        SectionDAO sd = new SectionDAO();
        List<Section> list = sd.getListSectionByCourseID("CP");
        System.out.println(list);
    }

}
