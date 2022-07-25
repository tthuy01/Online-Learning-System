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
import model.LessonResult;

/**
 *
 * @author ADMIN
 */
public class LessonResultDAO extends DBContext {

    public List<LessonResult> getListLessonByUid(int uid, String cid) {
        String sql = "select lr.* from Lesson_Result lr\n"
                + "inner join Lesson l on lr.lesson_id = l.lesson_id\n"
                + "where user_id = ? and l.course_id = ? order by l.lesson_id,l.section_id";
        List<LessonResult> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, uid);
            st.setString(2, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                LessonResult lr = new LessonResult();
                lr.setLid(rs.getInt(1));
                lr.setUid(rs.getInt(2));
                lr.setLstatus(rs.getBoolean(3));
                list.add(lr);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public void updateLessonResult(int lid, int uid) {
        String sql = "update Lesson_Result set lesson_status = 1 where lesson_id = ? and user_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, lid);
            st.setInt(2, uid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void insertLessonResult(int lid, int uid) {
        String sql = "insert into Lesson_Result(lesson_id,[user_id],lesson_status) values (?,?,0)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, lid);
            st.setInt(2, uid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteLessonResult(int lid, int uid) {
        String sql = "delete Lesson_Result where lesson_id= ? and user_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, lid);
            st.setInt(2, uid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
