/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Quiz;
import model.QuizList;
import model.QuizMix;
import model.QuizResult;

/**
 *
 * @author ADMIN
 */
public class QuizDAO extends DBContext {

    public List<Quiz> getAll() {
        String sql = "select * from Quiz";
        List<Quiz> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Quiz q = new Quiz();
                q.setQid(rs.getInt(1));
                q.setQname(rs.getString(2));
                q.setQdesc(rs.getString(3));
                q.setCid(rs.getString(4));
                q.setTid(rs.getInt(5));
                q.setSid(rs.getInt(6));
                list.add(q);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<Quiz> getListQuizByCID(String cid) {
        String sql = "select * from Quiz where course_id like ? and quiz_status = 1 order by section_id";
        List<Quiz> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Quiz q = new Quiz();
                q.setQid(rs.getInt(1));
                q.setQname(rs.getString(2));
                q.setQdesc(rs.getString(3));
                q.setCid(rs.getString(4));
                q.setTid(rs.getInt(5));
                q.setSid(rs.getInt(6));
                list.add(q);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<Quiz> getListQuizBySID(int sid) {
        String sql = "select * from Quiz where section_id = ?";
        List<Quiz> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, sid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Quiz q = new Quiz();
                q.setQid(rs.getInt(1));
                q.setQname(rs.getString(2));
                q.setQdesc(rs.getString(3));
                q.setCid(rs.getString(4));
                q.setTid(rs.getInt(5));
                q.setSid(rs.getInt(6));
                list.add(q);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public Quiz getQuizById(String qid) {
        String sql = "select * from Quiz where quiz_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(qid));
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Quiz q = new Quiz();
                q.setQid(rs.getInt(1));
                q.setQname(rs.getString(2));
                q.setQdesc(rs.getString(3));
                q.setCid(rs.getString(4));
                q.setTid(rs.getInt(5));
                q.setSid(rs.getInt(6));
                return q;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public void insertQuizResult(QuizResult qr) {
        String sql = "INSERT INTO [dbo].[Quiz_Result] ([quiz_id],[user_id],[quiz_status],[quiz_grade],[quiz_start],[quiz_end]) \n"
                + "VALUES(?, ?, ?, ?, CAST(? AS DateTime), CAST(? AS DateTime))";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, qr.getQid());
            st.setInt(2, qr.getUid());
            st.setBoolean(3, qr.isQstatus());
            st.setDouble(4, qr.getQgrade());
            st.setString(5, qr.getQstart());
            st.setString(6, qr.getQend());
            st.executeUpdate();
        } catch (Exception e) {
        }

    }

    public int countQuizResult() {
        String sql = "select count(*) from Quiz_Result";
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

    public QuizMix getInfoBy2Id(String qid, String qrid) {
        String sql = "select q.*, \n"
                + "c.course_name as[course_name],\n"
                + "qr.quiz_result_id, qr.quiz_status, qr.quiz_grade, qr.quiz_start, qr.quiz_end\n"
                + "from Quiz q right join Course c on q.course_id = c.course_id left join Quiz_Result qr on q.quiz_id = qr.quiz_id\n"
                + "where q.quiz_id = ? and qr.quiz_result_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(qid));
            st.setInt(2, Integer.parseInt(qrid));
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                QuizMix qm = new QuizMix();
                qm.setCid(rs.getString("course_id"));
                qm.setCname(rs.getString("course_name"));
                qm.setQid(rs.getInt("quiz_id"));
                qm.setQname(rs.getString("quiz_name"));
                qm.setQdesc(rs.getString("quiz_desc"));
                qm.setQstatus(rs.getBoolean("quiz_status"));
                qm.setQgrade(rs.getDouble("quiz_grade"));
                qm.setQstart(rs.getString("quiz_start"));
                qm.setQend(rs.getString("quiz_end"));
                qm.setQrid(rs.getInt("quiz_result_id"));
                return qm;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public String getTimeById(String qrid) {
        String sql = "select quiz_end - quiz_start as Time, quiz_start, quiz_end from Quiz_Result where quiz_result_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(qrid));
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString("Time") + "|" + rs.getString("quiz_start") + "|" + rs.getString("quiz_end");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public Quiz getQuizNext(int sid) {
        String sql = "select top 1 * from Quiz where section_id = ? and quiz_status = 1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, sid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Quiz(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

//    nam iteration 3-----------------------------------------------------------------------------------------------------
    public List<QuizList> getQuizList(int quizid, String quizName, String[] subId, String[] qStatus, String qSort, int page) {

        List<QuizList> list = new ArrayList<>();
        String sql = "select distinct q.quiz_id, q.quiz_name, cast(quiz_desc as varchar(max)) as qdesc, q.course_id, q.type_id, q.section_id, \n"
                + "qr.quiz_id as status, s.sub_id, s.sub_name, c.course_name, \n"
                + "(select section_name from Section where q.section_id = section_id) as sname,\n"
                + "c.course_status, \n"
                + "(select count(*) from Question where q.quiz_id = quiz_id) as quesnum,\n"
                + "cast((select count(*)*1.0/(select count(*) from Quiz_Result where q.quiz_id = quiz_id having count(*) > 0) from Quiz_Result where quiz_grade >= 5 and q.quiz_id = quiz_id)*100 as numeric(36,2)) as passrate\n"
                + "from Quiz q\n"
                + "join Course c on q.course_id = c.course_id \n"
                + "left join Quiz_Result qr on qr.quiz_id = q.quiz_id\n"
                + "join Subject s on c.sub_id = s.sub_id \n"
                + "where 1=1 ";

        if (quizid != 0) {
            sql += " and q.quiz_id = " + quizid;
        }
        if (quizName != null) {
            sql += " and q.quiz_name like '%" + quizName + "%'";
        }
        if (subId != null) {
//            sql += " and s.sub_id in (";
//            for (int i = 0; i < subId.length; i++) {
//                sql += subId[i];
//                if (i != subId.length - 1) {
//                    sql += ", ";
//                }
//            }
            sql += " and c.course_id in (";
            for (int i = 0; i < subId.length; i++) {
                sql += "'" + subId[i] + "'";
                if (i != subId.length - 1) {
                    sql += ", ";
                }
            }
            sql += ")";
        }
        if (qStatus != null) {
            if (qStatus.length == 1) {
                sql += qStatus[0].equals("0") ? " and qr.quiz_id is null " : " and qr.quiz_id is not null ";
            } else {
                sql += " and (qr.quiz_id is null or qr.quiz_id is not null) ";
            }
        }
        if (qSort != null && qSort.length() > 0) {
            sql += " and qr.quiz_id is not null";
        }
        if (page != -1) {
            sql += " order by ";
            if (qSort != null) {
                sql += "passrate ";
                sql += qSort.equals("0") ? "," : "desc,";
            }
            sql += "q.quiz_id offset ? rows fetch next 5 rows only";
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            if (page != -1) {
                st.setInt(1, (page - 1) * 5);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                QuizList q = new QuizList();
                q.setQid(rs.getInt("quiz_id"));
                q.setQname(rs.getString("quiz_name"));
                q.setQdesc(rs.getString("qdesc"));
                q.setCid(rs.getString("course_id"));
                q.setTid(rs.getInt("type_id"));
                q.setSid(rs.getInt("section_id"));
                q.setQexist(rs.getInt("status"));
//                q.setQstatus(rs.getBoolean("quiz_status"));
//                q.setUid(rs.getInt("user_id"));
                q.setSubid(rs.getInt("sub_id"));
                q.setSubname(rs.getString("sub_name"));
                q.setCname(rs.getString("course_name"));
                q.setSname(rs.getString("sname"));
                q.setCstatus(rs.getBoolean("course_status"));
                q.setQuesnum(rs.getInt("quesnum"));
                String pass = String.format("%.2f", rs.getDouble("passrate"));
                q.setPasrat(Double.parseDouble(pass));
                list.add(q);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertQuiz(Quiz q) {
        String sql = "insert into [Quiz] VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, q.getQname());
            st.setString(2, q.getQdesc());
            st.setString(3, q.getCid());
            st.setInt(4, q.getTid());
            st.setInt(5, q.getSid());
            st.setInt(6, 1);
            st.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void updateQuiz(Quiz q) {
        String sql = "update Quiz set [quiz_name]=?, [quiz_desc]=?, [course_id]=?, [section_id]=? where quiz_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, q.getQname());
            st.setString(2, q.getQdesc());
            st.setString(3, q.getCid());
            st.setInt(4, q.getSid());
            st.setInt(5, q.getQid());
            st.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void delQuiz(int qid) {
        String sql = "delete from Quiz where quiz_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, qid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    // Dao iter 4 ---------------admin/expert role ------------------------------------------------------------------------------------------------------
    public List<Quiz> getListQuizByCidAR(String cid, int sid, int status) {
        List<Quiz> list = new ArrayList<>();
        String sql = "select * from Quiz where course_id = ?";
        if (sid != 0) {
            sql += " and section_id = " + sid;
        }
        if (status != 2) {
            sql += " and quiz_status = " + status;
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Quiz q = new Quiz();
                q.setQid(rs.getInt(1));
                q.setQname(rs.getString(2));
                q.setQdesc(rs.getString(3));
                q.setCid(rs.getString(4));
                q.setTid(rs.getInt(5));
                q.setSid(rs.getInt(6));
                q.setQstatus(rs.getBoolean(7));
                list.add(q);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public Quiz getQuizByIdAR(int qid) {
        String sql = "select * from Quiz where quiz_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, qid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Quiz q = new Quiz();
                q.setQid(rs.getInt(1));
                q.setQname(rs.getString(2));
                q.setQdesc(rs.getString(3));
                q.setCid(rs.getString(4));
                q.setTid(rs.getInt(5));
                q.setSid(rs.getInt(6));
                return q;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public void updateQuizAR(Quiz q) {
        String sql = "update Quiz set [quiz_name]=?, [quiz_desc]=?, [course_id]=?, [section_id]=?, quiz_status= ? where quiz_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, q.getQname());
            st.setString(2, q.getQdesc());
            st.setString(3, q.getCid());
            st.setInt(4, q.getSid());
            st.setBoolean(5, q.isQstatus());
            st.setInt(6, q.getQid());
            st.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    // Dao iter 4 ---------------admin/expert role ------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
        DateFormat obj = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(obj.format(new Date().getTime()));
//        new QuizDAO().insertQuizResult(new QuizResult(0, 1, 1, true, 8, , '1'));
    }

}
