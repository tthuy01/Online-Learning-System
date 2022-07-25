package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Subject;
import model.SubjectCategory;

public class SubjectCategoryDAO extends DBContext {

    public List<SubjectCategory> getAll() {
        String sql = "Select * from Subject_Category";
        List<SubjectCategory> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new SubjectCategory(rs.getInt("subject_cate_id"), rs.getString("subject_cate_name")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<SubjectCategory> getAllSubjectCategory() {
        List<SubjectCategory> list = new ArrayList<>();
        String sql = "select * from Subject_Category";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SubjectCategory sc = new SubjectCategory();
                sc.setSubjectCateId(rs.getInt(1));
                sc.setSubjectCateName(rs.getString(2));
                list.add(sc);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public void insert(Subject s) {
        //insert into Subject values (sname, simg, sdesc, subcatID)
        String sql = "insert into Subject values (?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, s.getSname());
            st.setString(2, s.getSimg());
            st.setString(3, s.getSdesc());
            st.setInt(4, s.getScate());
            st.executeUpdate();//1 or 0
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void insertSubjectCategory(String s) {
        String sql = "insert into Subject_Category values (?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, s);
            st.executeUpdate();//1 or 0
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
        public void update(String scname, int scid) {
        String sql = "update Subject_Category set subject_cate_name = ? where subject_cate_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, scname);
            st.setInt(2, scid);
            st.executeUpdate();//1 or 0
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void delete(int scid) {
        String sql = "delete Subject_Category where subject_cate_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, scid);
            st.executeUpdate();//1 or 0
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        SubjectCategoryDAO scd = new SubjectCategoryDAO();
        for (SubjectCategory sc : scd.getAll()) {
            System.out.println(sc.getSubjectCateName());
        }
    }
}
