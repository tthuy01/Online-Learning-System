/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.TimeCourse;

/**
 *
 * @author Administrator
 */
public class TimeCourseDAO extends DBContext {

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public void insertTimeCourse(TimeCourse tc) {
        //insert Time_Course values ('cid', uid, dateStr, dateSto)
        String sql = "insert Time_Course values (?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, tc.getCid());
            st.setInt(2, tc.getUid());
            st.setString(3, getCurrentTimeStamp());
            st.setString(4, tc.getCourse_stop());
            st.executeUpdate();//1 or 0
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
