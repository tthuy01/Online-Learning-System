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
import model.QuesResult;
import model.Question;
import model.QuestionList;
import model.QuestionMix;

/**
 *
 * @author Admin
 */
public class QuestionDAO extends DBContext {

    public Question getQuestionById(String qid) {
        String sql = "select * from Question where ques_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(qid));
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Question q = new Question();
                q.setQuesid(rs.getInt(1));
                q.setQcontent(rs.getString(2));
                q.setQresult(rs.getInt(3));
                q.setQnote(rs.getString(4));
                q.setQid(rs.getInt(5));
                return q;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public int getNumQuestionByQid(String qid) {
        String sql = "select count(*) from Question where quiz_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(qid));
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return 0;
    }

    public void insertQuesAnswer(QuesResult quesr) {
        String sql = "INSERT INTO [dbo].[Ques_Result]([ques_id],[user_id],[ques_status],[ques_flag],[ques_answer],[quiz_result_id]) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, quesr.getQuesid());
            st.setInt(2, quesr.getUid());
            st.setBoolean(3, quesr.isQstatus());
            st.setBoolean(4, quesr.isQflag());
            st.setInt(5, quesr.getQanswer());
            st.setInt(6, quesr.getQrid());
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Integer> getQuesResultByQuizId(String qid) {
        List<Integer> list = new ArrayList<>();
        String sql = "select ques_result from Question where quiz_id=" + qid;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<QuestionMix> getAllInfoBy2Id(String qid, String qrid) {
        List<QuestionMix> list = new ArrayList<>();
        String sql = "select ques.*, \n"
                + "quer.ques_result_id, quer.quiz_result_id, quer.ques_status, quer.ques_flag, quer.ques_answer\n"
                + "from Question ques right join Quiz q on ques.quiz_id = q.quiz_id left join Ques_Result quer on quer.ques_id = ques.ques_id\n"
                + "where q.quiz_id = ? and quer.quiz_result_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(qid));
            st.setInt(2, Integer.parseInt(qrid));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                QuestionMix quesm = new QuestionMix();
                quesm.setQuesid(rs.getInt("ques_id"));
                quesm.setQuescontext(rs.getString("ques_content"));
                quesm.setQuesresult(rs.getInt("ques_result"));
                quesm.setQuesnote(rs.getString("ques_note"));
                quesm.setQid(rs.getInt("quiz_id"));
                quesm.setQuesrid(rs.getInt("ques_result_id"));
                quesm.setQuesstatus(rs.getBoolean("ques_status"));
                quesm.setQuesflag(rs.getBoolean("ques_flag"));
                quesm.setQuesanswer(rs.getInt("ques_answer"));
                quesm.setQrid(rs.getInt("quiz_result_id"));
                list.add(quesm);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public QuestionMix getInfoBy3Id(String qid, String qrid, String quesid) {
        String sql = "select ques.*, \n"
                + "quer.ques_result_id, quer.quiz_result_id, quer.ques_status, quer.ques_flag, quer.ques_answer\n"
                + "from Question ques right join Quiz q on ques.quiz_id = q.quiz_id left join Ques_Result quer on quer.ques_id = ques.ques_id\n"
                + "where q.quiz_id = ? and quer.quiz_result_id = ? and ques.ques_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(qid));
            st.setInt(2, Integer.parseInt(qrid));
            st.setInt(3, Integer.parseInt(quesid));
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                QuestionMix quesm = new QuestionMix();
                quesm.setQuesid(rs.getInt("ques_id"));
                quesm.setQuescontext(rs.getString("ques_content"));
                quesm.setQuesresult(rs.getInt("ques_result"));
                quesm.setQuesnote(rs.getString("ques_note"));
                quesm.setQid(rs.getInt("quiz_id"));
                quesm.setQuesrid(rs.getInt("ques_result_id"));
                quesm.setQuesstatus(rs.getBoolean("ques_status"));
                quesm.setQuesflag(rs.getBoolean("ques_flag"));
                quesm.setQuesanswer(rs.getInt("ques_answer"));
                quesm.setQrid(rs.getInt("quiz_result_id"));
                return quesm;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public int getAllAnsweredBy2Id(String qid, String qrid) {
        String sql = "select count(*) as Mark\n"
                + "from Question ques right join Quiz q on ques.quiz_id = q.quiz_id left join Ques_Result quer on quer.ques_id = ques.ques_id\n"
                + "where q.quiz_id= ? and quer.[quiz_result_id] = ? and ques.ques_result = quer.ques_answer";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(qid));
            st.setInt(2, Integer.parseInt(qrid));
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("Mark");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return 0;
    }

    public int getTop1QuesIdByQuizId(String qid) {
        String sql = "select top 1 ques_id from Question where quiz_id=" + qid;
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

    public List<QuestionList> getQuesList(int queid, String que_content, String cId, String[] sId, String[] queStatus, int page) {

        List<QuestionList> list = new ArrayList<>();
        String sql = "select distinct que.ques_id, cast(que.ques_content as varchar(max)) as ques_content, que.ques_result, cast(que.ques_note as varchar(max)) as ques_note, que.quiz_id,\n"
                + "q.course_id,\n"
                + "(select c.course_name from Course c where q.course_id = c.course_id) as cname,\n"
                + "q.section_id,\n"
                + "(select s.section_name from Section s where q.section_id = s.section_id) as sname,\n"
                + "(select q.quiz_name from Quiz q where que.quiz_id = q.quiz_id) as qname,\n"
                + "qr.ques_id as status\n"
                + "from Question que \n"
                + "join Quiz q on q.quiz_id = que.quiz_id\n"
                + "left join Ques_Result qr on qr.ques_id = que.ques_id\n"
                + "where 1=1 ";

        if (queid != 0) {
            sql += " and que.ques_id = " + queid;
        }
        if (que_content != null) {
            sql += " and que.ques_content like '%" + que_content + "%'";
        }
        if (cId != null) {
            sql += " and q.course_id in ('" + cId + "')";
        }
        if (sId != null) {
            sql += " and q.section_id in (";
            for (int i = 0; i < sId.length; i++) {
                sql += "'" + sId[i] + "'";
                if (i != sId.length - 1) {
                    sql += ", ";
                }
            }
            sql += ")";
        }
        if (queStatus != null) {
            if (queStatus.length == 1) {
                sql += queStatus[0].equals("0") ? " and qr.ques_id is null " : " and qr.ques_id is not null ";
            } else {
                sql += " and (qr.ques_id is null or qr.ques_id is not null) ";
            }
        }
        if (page > 0) {
            sql += " order by que.ques_id offset ? rows fetch next 5 rows only";
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            if (page > 0) {
                st.setInt(1, (page - 1) * 5);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                QuestionList q = new QuestionList();
                q.setQueid(rs.getInt("ques_id"));
                q.setQue_content(rs.getString("ques_content"));
                q.setQue_result(rs.getInt("ques_result"));
                q.setQue_note(rs.getString("ques_note"));
                q.setQid(rs.getInt("quiz_id"));
                q.setCid(rs.getString("course_id"));
                q.setCname(rs.getString("cname"));
                q.setSid(rs.getInt("section_id"));
                q.setSname(rs.getString("sname"));
                q.setQname(rs.getString("qname"));
                q.setQue_status(rs.getBoolean("status"));
                list.add(q);
//                for (QuestionList ques : list) {
//                    if (ques.getQueid() == q.getQueid()) {
//                        if (ques.isQue_status() == false && q.isQue_status() == true) {
//                            list.remove(ques);
//                        }
//                    }
//                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void updateQues(Question q) {
        String sql = "update Question\n"
                + "  set ques_content = ?, ques_result = ?, ques_note = ?, quiz_id = ?\n"
                + "  where ques_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, q.getQcontent());
            st.setInt(2, q.getQresult());
            st.setString(3, q.getQnote());
            st.setInt(4, q.getQid());
            st.setInt(5, q.getQuesid());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    public void importQuestions(String content, int result, String note, int qid) {
        String sql = "INSERT INTO [dbo].[Question]([ques_content],[ques_result],[ques_note],[quiz_id]) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, content);
            st.setInt(2, result);
            st.setString(3, note);
            st.setInt(4, qid);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        System.out.println(new QuestionDAO().getTop1QuesIdByQuizId("2"));
        System.out.println(new QuestionDAO().getInfoBy3Id("2", "1", "11"));

    }
}
