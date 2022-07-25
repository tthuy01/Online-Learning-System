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
import model.SubCateProfit;
import model.Subject;
import model.TotalLesson;

/**
 *
 * @author Admin
 */
public class SubjectDAO extends DBContext {

    public List<Subject> getAll() {
        String sql = "select * from Subject";
        List<Subject> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Subject(rs.getInt("sub_id"), rs.getString("sub_name"),
                        rs.getString("sub_img"), rs.getString("sub_desc"), rs.getInt("subject_cate_id")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Subject> getAllDesc() {
        String sql = "select * from Subject order by sub_id desc";
        List<Subject> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Subject(rs.getInt("sub_id"), rs.getString("sub_name"),
                        rs.getString("sub_img"), rs.getString("sub_desc"), rs.getInt("subject_cate_id")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int getNumOfSub() {
        String sql = "select count(*) from Subject";
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

    public List<SubCateProfit> getProfitBySubCate(String from, String to, String viewby) {
        String sql = "";
        if (viewby.equals("0")) {
            sql = "select sc.subject_cate_id, sc.subject_cate_name, SUM(c.course_price*pp.multiple) as profit\n"
                    + "from Subject_Category sc, Subject s, Course c, Registration r, Price_Package pp\n"
                    + "where sc.subject_cate_id=s.subject_cate_id and s.sub_id=c.sub_id and c.course_id=r.course_id\n"
                    + "and r.package_id=pp.package_id and r.reg_time between '" + from + "' and '" + to + "'\n"
                    + "group by sc.subject_cate_id, sc.subject_cate_name";
        } else {
            sql = "select s.sub_id, s.sub_name, SUM(c.course_price*pp.multiple) as profit\n"
                    + "from Subject s, Course c, Registration r, Price_Package pp\n"
                    + "where s.sub_id=c.sub_id and c.course_id=r.course_id\n"
                    + "and r.package_id=pp.package_id and r.reg_time between '" + from + "' and '" + to + "'\n"
                    + "group by s.sub_id, s.sub_name";
        }

        List<SubCateProfit> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new SubCateProfit(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    //Iteration 3: Thuy
    public Subject getSubBySubId(int id) {
        String sql = "select * from Subject where sub_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, 1);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Subject(rs.getInt("sub_id"), rs.getString("sub_name"),
                        rs.getString("sub_img"), rs.getString("sub_desc"), rs.getInt("subject_cate_id"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    //------------------Thanh----------------------
    // Tong so khoa hoc theo cid
    public List<TotalLesson> totalLesson() {
        String sql = "select c.course_id, COUNT(lesson_id) as total_lesson from Lesson l right join Course c on l.course_id = c.course_id\n"
                + "group by c.course_id";
        List<TotalLesson> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new TotalLesson(rs.getString("course_id"), rs.getInt("total_lesson")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Subject getSubjectBySname(String sname) {
        String sql = "select * from Subject where sub_name like '%" + sname + "%'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Subject(rs.getInt("sub_id"), rs.getString("sub_name"),
                        rs.getString("sub_img"), rs.getString("sub_desc"), rs.getInt("subject_cate_id"));
            }
        } catch (Exception e) {
        }
        return null;
    }

    //Iteration 3: Thuy
    public Subject getSubByCourseId(String cid) {
        String sql = "select s.sub_id, s.sub_name, s.sub_img, s.sub_desc, s.subject_cate_id from Subject s\n"
                + "join Course c on s.sub_id = c.sub_id\n"
                + "where c.course_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Subject(rs.getInt("sub_id"), rs.getString("sub_name"),
                        rs.getString("sub_img"), rs.getString("sub_desc"), rs.getInt("subject_cate_id"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        SubjectDAO sd = new SubjectDAO();
//        System.out.println(sd.getProfitBySubCate("2022-06-13", "2022-07-07").size());
    }
}
