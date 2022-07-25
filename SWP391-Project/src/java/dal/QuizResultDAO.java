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
import model.QuizResult;

/**
 *
 * @author ADMIN
 */
public class QuizResultDAO extends DBContext {

    public List<QuizResult> getListStatusQuizResultByUid(int uid) {
        String sql = "select qr.quiz_result_id ,qr.quiz_id,qr.user_id,qr.quiz_status,qr.quiz_grade,qr.quiz_start,qr.quiz_end\n"
                + "from Quiz_Result qr inner join\n"
                + "(select max(quiz_result_id) as quiz_result_id from Quiz_Result group by quiz_id, user_id) as qrm \n"
                + "on qr.quiz_result_id = qrm.quiz_result_id \n"
                + "where user_id = ?";
        List<QuizResult> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, uid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                QuizResult qr = new QuizResult();
                qr.setQrid(rs.getInt(1));
                qr.setQid(rs.getInt(2));
                qr.setUid(rs.getInt(3));
                qr.setQstatus(rs.getBoolean(4));
                qr.setQgrade(rs.getDouble(5));
                qr.setQstart(rs.getString(6));
                qr.setQend(rs.getString(7));
                list.add(qr);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
//    public QuizResult getQuizResByQuizResId(int qrid) {
//        QuizResult qr = new QuizResult();
//        String sql = "select * from Quiz_Result where quiz_result_id = ?";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setInt(1, qrid);
//            ResultSet rs = st.executeQuery();
//            if (rs.next()) {
//                qr.setQrid(rs.getInt("quiz_result_id"));
//                qr.setQid(rs.getInt("quiz_id"));
//                qr.setUid(rs.getInt("user_id"));
//                qr.setQstatus(rs.getBoolean("quiz_status"));
//                qr.setQgrade(rs.getInt("quiz_grade"));
//                qr.setQstart(rs.getString("quiz_start"));
//                qr.setQend(rs.getString("quiz_end"));
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return qr;
//    }
    
//    public String getQuizname(int qiz) {
//        String sql = "select q.quiz_name from Quiz_Result qr\n"
//                + "join Quiz q on qr.quiz_id=q.quiz_id\n"
//                + "where qr.quiz_result_id = ?";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setInt(1, qiz);
//            ResultSet rs = st.executeQuery();
//            if(rs.next()){
//                return rs.getString(1);
//            }
//        } catch (SQLException e) {
//        }
//        return null;
//    }
    
    public List<QuizResult> getListQRByQid(int uid, int qid)
    {
        List<QuizResult> list = new ArrayList<>();
        String sql = "select * from Quiz_Result\n" +
                        "where user_id = ? and quiz_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, uid);
            st.setInt(2, qid);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                QuizResult qr = new QuizResult();
                qr.setQrid(rs.getInt("quiz_result_id"));
                qr.setQid(rs.getInt("quiz_id"));
                qr.setUid(rs.getInt("user_id"));
                qr.setQstatus(rs.getBoolean("quiz_status"));
                qr.setQgrade(rs.getInt("quiz_grade"));
                qr.setQstart(rs.getString("quiz_start"));
                qr.setQend(rs.getString("quiz_end"));
                list.add(qr);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public double getHighestGradeByQid(int qid){
        double g = 0;
        String sql="select max(quiz_grade) from Quiz_Result where quiz_id=?";      
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, qid);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                g = rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return  g;
    }
    
    public int numberDoingQuiz(int qid)
    {
        int n = 0;
        String sql = "select count(*) from Quiz_Result qr \n" +
                        "join Quiz q on qr.quiz_id = q.quiz_id\n" +
                        "where q.quiz_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, qid);
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                n = rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return n;
    }

//    public List<QuizResult> getListQuizResByQuizResId(int qid) {
//        List<QuizResult> list = new ArrayList<>();
//        String sql = "select * from Quiz_Result where quiz_id = ?";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setInt(1, qid);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                QuizResult qr = new QuizResult();
//                qr.setQrid(rs.getInt("quiz_result_id"));
//                qr.setQid(rs.getInt("quiz_id"));
//                qr.setUid(rs.getInt("user_id"));
//                qr.setQstatus(rs.getBoolean("quiz_status"));
//                qr.setQgrade(rs.getInt("quiz_grade"));
//                qr.setQstart(rs.getString("quiz_start"));
//                qr.setQend(rs.getString("quiz_end"));
//                list.add(qr);
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return list;
//    }

     public QuizResult getQuizResultByQIdUid(int qid, int uid) {
        String sql = "select top 1 * from Quiz_Result where quiz_id =? and user_id =? order by quiz_grade desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, qid);
            st.setInt(2, uid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new QuizResult(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getBoolean(4), rs.getDouble(5), rs.getString(6), rs.getString(7));
            }
        } catch (SQLException e) {
        }
        return null;
    }


    public static void main(String[] args) {
        QuizResultDAO lrd = new QuizResultDAO();
    }

}
