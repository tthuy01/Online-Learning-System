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
import model.Lesson;

/**
 *
 * @author ADMIN
 */
public class LessonDAO extends DBContext {

    public List<Lesson> getListLessonByCID(String cid) {
        String sql = "select * from Lesson where course_id = ? and lesson_status = 1 order by section_id";
        List<Lesson> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson();
                l.setLid(rs.getInt(1));
                l.setLname(rs.getString(2));
                l.setLvideo(rs.getString(3));
                l.setCid(rs.getString(4));
                l.setSid(rs.getInt(5));
                l.setTid(rs.getInt(6));
                l.setLdesc(rs.getString(7));
                list.add(l);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<Lesson> getListLessonBySID(int sid) {
        String sql = "select * from Lesson where section_id = ? and lesson_status = 1";
        List<Lesson> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, sid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson();
                l.setLid(rs.getInt(1));
                l.setLname(rs.getString(2));
                l.setLvideo(rs.getString(3));
                l.setCid(rs.getString(4));
                l.setSid(rs.getInt(5));
                l.setTid(rs.getInt(6));
                l.setLdesc(rs.getString(7));
                list.add(l);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<Lesson> getListLessonByCIdSID(String cid, int sid) {
        String sql = "select * from Lesson where course_id = ? and section_id = ? and lesson_status = 1";
        List<Lesson> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);
            st.setInt(2, sid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson();
                l.setLid(rs.getInt(1));
                l.setLname(rs.getString(2));
                l.setLvideo(rs.getString(3));
                l.setCid(rs.getString(4));
                l.setSid(rs.getInt(5));
                l.setTid(rs.getInt(6));
                l.setLdesc(rs.getString(7));
                list.add(l);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public Lesson getLessonByID(int lid) {
        String sql = "select * from Lesson where lesson_id = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, lid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Lesson(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Lesson getLessonNext(int sid, int lid, String cid, int next) {
        String sql = null;
        switch (next) {
            case 1:
                sql = "select top 1 * from Lesson where section_id = ? and course_id = ? and lesson_id  > ?  and lesson_status = 1";
                break;
            case -1:
                sql = "select top 1 * from Lesson where section_id = ? and course_id = ?  and lesson_id  < ? and lesson_status = 1\n"
                        + "order by lesson_id desc";
                break;
            case 2:
                sql = "select top 1 * from Lesson where section_id > ? and course_id = ? and lesson_status = 1";
                break;
            case -2:
                sql = "select top 1 * from Lesson where section_id < ? and course_id = ? and lesson_status = 1\n"
                        + "order by section_id desc,lesson_id desc";
            default:
                break;
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, sid);
            st.setString(2, cid);
            if (next == 1 || next == -1) {
                st.setInt(3, lid);
            }
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Lesson(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public void updateLesson(Lesson l) {
        String sql = "update Lesson set lesson_name = ?, lesson_video = ? ,course_id = ? ,section_id= ? ,type_id = ? ,lesson_desc = ? \n"
                + "where lesson_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, l.getLname());
            st.setString(2, l.getLvideo());
            st.setString(3, l.getCid());
            st.setInt(4, l.getSid());
            st.setInt(5, l.getTid());
            st.setString(6, l.getLdesc());
            st.setInt(7, l.getLid());           
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int getNumOfLesson() {
        String sql = "select count(*) from Lesson";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public void insertLesson(Lesson l) {
        String sql = "insert into Lesson(lesson_name,lesson_video,course_id,section_id,type_id,lesson_desc,lesson_status) values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, l.getLname());
            st.setString(2, l.getLvideo());
            st.setString(3, l.getCid());
            st.setInt(4, l.getSid());
            st.setInt(5, l.getTid());
            st.setString(6, l.getLdesc());
            st.setInt(7, 1);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    // Dao iter 4 ---------------admin/expert role ------------------------------------------------------------------------------------------------------
    
    public List<Lesson> getListLessonByCidAR(String cid, int sid, int status){
        List<Lesson> list = new ArrayList<>();
        String sql = "select * from Lesson where course_id = ?";
        if (sid != 0) {
            sql += " and section_id = " + sid;
        }
        if (status != 2) {
            sql += " and lesson_status = " + status;
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Lesson l = new Lesson();
                l.setLid(rs.getInt(1));
                l.setLname(rs.getString(2));
                l.setLvideo(rs.getString(3));
                l.setCid(rs.getString(4));
                l.setSid(rs.getInt(5));
                l.setTid(rs.getInt(6));
                l.setLdesc(rs.getString(7));
                l.setLstatus(rs.getBoolean(8));
                list.add(l);
            }
        } catch (SQLException e) {
        }
        return list;
    }
    public void updateLessonAR(Lesson l) {
        String sql = "update Lesson set lesson_name = ?, lesson_video = ? ,course_id = ? ,section_id= ? ,type_id = ? ,lesson_desc = ? , lesson_status = ?\n"
                + "where lesson_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, l.getLname());
            st.setString(2, l.getLvideo());
            st.setString(3, l.getCid());
            st.setInt(4, l.getSid());
            st.setInt(5, l.getTid());
            st.setString(6, l.getLdesc());
            st.setBoolean(7, l.isLstatus());
            st.setInt(8, l.getLid());           
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    // Dao iter 4 ---------------admin/expert role ------------------------------------------------------------------------------------------------------

}
