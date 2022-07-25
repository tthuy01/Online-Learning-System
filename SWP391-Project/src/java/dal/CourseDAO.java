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
import model.Course;

/**
 *
 * @author Admin
 */
public class CourseDAO extends DBContext {

    public List<Course> getAll() {
        String sql = "select * from Course";
        List<Course> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Course(rs.getString("course_id"), rs.getString("course_name"),
                        rs.getString("course_title"), rs.getString("course_img"),
                        rs.getDouble("course_price"), rs.getString("course_desc"),
                        rs.getString("course_start"), rs.getString("course_stop"),
                        rs.getString("course_public"), rs.getInt("sub_id"),
                        rs.getInt("lecturer_id"), rs.getInt("level_id"), rs.getBoolean("course_status")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Course getCourseById(String cid) {
        String sql = "select * from Course where course_id like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Course(rs.getString("course_id"), rs.getString("course_name"),
                        rs.getString("course_title"), rs.getString("course_img"),
                        rs.getDouble("course_price"), rs.getString("course_desc"),
                        rs.getString("course_start"), rs.getString("course_stop"),
                        rs.getString("course_public"), rs.getInt("sub_id"),
                        rs.getInt("lecturer_id"), rs.getInt("level_id"), rs.getBoolean("course_status"));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Course> getCourseBySid(int sid, String cid) {
        List<Course> list = new ArrayList<>();
        String sql = "select * from Course where sub_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, sid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Course c = new Course(rs.getString("course_id"), rs.getString("course_name"),
                        rs.getString("course_title"), rs.getString("course_img"),
                        rs.getDouble("course_price"), rs.getString("course_desc"),
                        rs.getString("course_start"), rs.getString("course_stop"),
                        rs.getString("course_public"), rs.getInt("sub_id"),
                        rs.getInt("lecturer_id"), rs.getInt("level_id"), rs.getBoolean("course_status"));
                if (!c.getCid().equals(cid)) {
                    list.add(c);
                }

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Course> getCourseBySid(int sid) {
        List<Course> list = new ArrayList<>();
        String sql = "select * from Course where sub_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, sid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Course c = new Course(rs.getString("course_id"), rs.getString("course_name"),
                        rs.getString("course_title"), rs.getString("course_img"),
                        rs.getDouble("course_price"), rs.getString("course_desc"),
                        rs.getString("course_start"), rs.getString("course_stop"),
                        rs.getString("course_public"), rs.getInt("sub_id"),
                        rs.getInt("lecturer_id"), rs.getInt("level_id"), rs.getBoolean("course_status"));
                list.add(c);

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public double getMaxPrice() {
        String sql = "select MAX(course_price) from Course where course_status=1";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Course> getCourseByFilter(String subject, String level, String lecturer, String price, String cpublic, String search, int index, int pageSize) {
        String sql = "select * from Course where course_status=1";
        if (search != null) {
            sql += " and (course_name like '%" + search + "%' or course_id like '%" + search + "%')";
        }
        if (subject != null && !subject.equals("0")) {
            sql += " and sub_id = " + subject;
        }
        if (level != null && !level.equals("0")) {
            sql += " and level_id = " + level;
        }
        if (lecturer != null && !lecturer.equals("0")) {
            sql += " and lecturer_id = " + lecturer;
        }
        if (price != null && price.equals("free")) {
            sql += " and course_price = 0";
        } else {
            if (price != null && !price.equals("all")) {
                sql += " and course_price between " + price;
            }
        }
        if (cpublic != null && !cpublic.equals("0")) {
            sql += " order by course_public " + cpublic;
        } else {
            sql += " order by course_id";
        }
        sql += " offset " + (index - 1) * pageSize + " rows fetch next " + pageSize + " rows only";
        List<Course> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Course(rs.getString("course_id"), rs.getString("course_name"),
                        rs.getString("course_title"), rs.getString("course_img"),
                        rs.getDouble("course_price"), rs.getString("course_desc"),
                        rs.getString("course_start"), rs.getString("course_stop"),
                        rs.getString("course_public"), rs.getInt("sub_id"),
                        rs.getInt("lecturer_id"), rs.getInt("level_id"), rs.getBoolean("course_status")));
            }
        } catch (Exception e) {
        }
        System.out.println(list.size() + "hello");
        return list;
    }

    public int getCourseNumber(String subject, String level, String lecturer, String price, String search) {
        String sql = "select COUNT(*) from Course where course_status=1";
        if (search != null) {
            sql += " and (course_name like '%" + search + "%' or course_id like '%" + search + "%')";
        }
        if (subject != null && !subject.equals("0")) {
            sql += " and sub_id = " + subject;
        }
        if (level != null && !level.equals("0")) {
            sql += " and level_id = " + level;
        }
        if (lecturer != null && !lecturer.equals("0")) {
            sql += " and lecturer_id = " + lecturer;
        }
        if (price != null && price.equals("free")) {
            sql += " and course_price = 0";
        } else {
            if (price != null && !price.equals("all")) {
                sql += " and course_price between " + price;
            }
        }
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

    public List<Course> getTop4FreeCourse() {
        String sql = "select top 4* from Course where course_price = 0 order by course_name";
        List<Course> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
//                list.add(new Course(rs.getString("0"), rs.getString("1"),
//                        rs.getString("2"), rs.getString("3"),
//                        rs.getDouble("4"), rs.getString("5"),
//                        rs.getString("6"), rs.getString("7"),
//                        rs.getString("8"), rs.getInt("9"),
//                        rs.getInt("10"), rs.getInt("11")));
                list.add(new Course(rs.getString("course_id"), rs.getString("course_name"),
                        rs.getString("course_title"), rs.getString("course_img"),
                        rs.getDouble("course_price"), rs.getString("course_desc"),
                        rs.getString("course_start"), rs.getString("course_stop"),
                        rs.getString("course_public"), rs.getInt("sub_id"),
                        rs.getInt("lecturer_id"), rs.getInt("level_id"), rs.getBoolean("course_status")));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<Course> getTop4Courses(String cType) {
        String type = cType.equals("free") ? "and course_price = 0" : "and course_price > 0";
        String sql = "select * from Course where course_status = 1 and course_id in\n"
                + "(select a.course_id from\n"
                + "(select top 4 c.course_id, COUNT(*) as 'reg_num' from Course c, Registration reg where c.course_id=reg.course_id " + type + " group by c.course_id order by reg_num desc) a)";
//        if (cType.equals("free")) sql += " order by course_id";
//        else sql += " order by course_price desc";
        List<Course> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
//                list.add(new Course(rs.getString("0"), rs.getString("1"),
//                        rs.getString("2"), rs.getString("3"),
//                        rs.getDouble("4"), rs.getString("5"),
//                        rs.getString("6"), rs.getString("7"),
//                        rs.getString("8"), rs.getInt("9"),
//                        rs.getInt("10"), rs.getInt("11")));
                list.add(new Course(rs.getString("course_id"), rs.getString("course_name"),
                        rs.getString("course_title"), rs.getString("course_img"),
                        rs.getDouble("course_price"), rs.getString("course_desc"),
                        rs.getString("course_start"), rs.getString("course_stop"),
                        rs.getString("course_public"), rs.getInt("sub_id"),
                        rs.getInt("lecturer_id"), rs.getInt("level_id"), rs.getBoolean("course_status")));
            }
        } catch (SQLException e) {
        }
        return list;
    }
// thuy--------------------------------------------------------------------------------------

    public List<Course> getMyCourse(int uid) {
        List<Course> list = new ArrayList<>();
        String sql = "select c.course_id, c.course_name, c.course_title, c.course_img,\n"
                + "c.course_price, c.course_desc, c.course_start, c.course_stop,\n"
                + "c.course_public, c.sub_id, c.lecturer_id, c.level_id, c.course_status\n"
                + "from Course c\n"
                + "join Registration r on c.course_id = r.course_id \n"
                + "join Price_Package p on r.package_id = p.package_id\n"
                + "where r.user_id = ? and GETDATE() >= r.reg_time and GETDATE() <= DATEADD(MONTH,p.duration,r.reg_time)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, uid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Course(rs.getString("course_id"), rs.getString("course_name"),
                        rs.getString("course_title"), rs.getString("course_img"),
                        rs.getDouble("course_price"), rs.getString("course_desc"),
                        rs.getString("course_start"), rs.getString("course_stop"),
                        rs.getString("course_public"), rs.getInt("sub_id"),
                        rs.getInt("lecturer_id"), rs.getInt("level_id"), rs.getBoolean("course_status")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Course> searchMyCourse(int uid, String search) {
        List<Course> list = new ArrayList<>();
        String sql = "select c.course_id, c.course_name, c.course_title, c.course_img,\n"
                + "c.course_price, c.course_desc, c.course_start, c.course_stop,\n"
                + "c.course_public, c.sub_id, c.lecturer_id, c.level_id, c.course_status\n"
                + "from Course c\n"
                + "join Registration r on c.course_id = r.course_id \n"
                + "join Price_Package p on r.package_id = p.package_id\n"
                + "where r.user_id = ? and GETDATE() >= r.reg_time and GETDATE() <= DATEADD(MONTH,p.duration,r.reg_time) ";
        if (search != null && !search.trim().equals("")) {
            sql += " and c.course_name like ?";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, uid);
            if (search != null && !search.trim().equals("")) {
                st.setString(2, "%" + search + "%");
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Course(rs.getString("course_id"), rs.getString("course_name"),
                        rs.getString("course_title"), rs.getString("course_img"),
                        rs.getDouble("course_price"), rs.getString("course_desc"),
                        rs.getString("course_start"), rs.getString("course_stop"),
                        rs.getString("course_public"), rs.getInt("sub_id"),
                        rs.getInt("lecturer_id"), rs.getInt("level_id"), rs.getBoolean("course_status")));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<Course> getListCourseByPage(List<Course> list, int start, int end) {
        List<Course> listCourseByPage = new ArrayList<>();
        for (int i = start; i < end; i++) {
            listCourseByPage.add(list.get(i));
        }
        return listCourseByPage;
    }

    public Course getCourseByQrID(int qrid) {
        String sql = "select c.* from Quiz_Result qr\n"
                + "join Quiz q on q.quiz_id= qr.quiz_id\n"
                + "join Course c on q.course_id=c.course_id\n"
                + "where qr.quiz_result_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, qrid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Course c = new Course(rs.getString("course_id"), rs.getString("course_name"),
                        rs.getString("course_title"), rs.getString("course_img"),
                        rs.getDouble("course_price"), rs.getString("course_desc"),
                        rs.getString("course_start"), rs.getString("course_stop"),
                        rs.getString("course_public"), rs.getInt("sub_id"),
                        rs.getInt("lecturer_id"), rs.getInt("level_id"), rs.getBoolean("course_status"));
                return c;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public int getNumOfCourse() {
        String sql = "select count(*) from Course";
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

    //Iteration 3: Thuy
    public Course getCourseByQid(int qid) {
        Course c = new Course();
        String sql = "select c.course_id, c.course_name, c.course_title, c.course_img, \n"
                + "c.course_price, c.course_desc, c.course_start, c.course_stop, \n"
                + "c.course_public, c.sub_id, c.lecturer_id, c.level_id, c.course_status \n"
                + "from Course c \n"
                + "join Quiz q on c.course_id = q.course_id\n"
                + "where q.quiz_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, qid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                c.setCid(rs.getString("course_id"));
                c.setCname(rs.getString("course_name"));
                c.setCtitle(rs.getString("course_title"));
                c.setCimg(rs.getString("course_img"));
                c.setCprice(rs.getDouble("course_price"));
                c.setCdesc(rs.getString("course_desc"));
                c.setCstart(rs.getString("course_start"));
                c.setCstop(rs.getString("course_stop"));
                c.setCpublic(rs.getString("course_public"));
                c.setSid(rs.getInt("sub_id"));
                c.setLecid(rs.getInt("lecturer_id"));
                c.setLevid(rs.getInt("level_id"));
                c.setCstatus(rs.getBoolean("course_status"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return c;
    }

    //CheckBox + Paging
    public List<Course> checkBoxCourseBySid(int[] sid, int[] cstatusArr, String search, int uid) {
        List<Course> list = new ArrayList<>();
        String sql = "select	c.course_id, course_name, course_title, \n"
                + "		course_img, course_price,course_desc,\n"
                + "		course_start, course_stop, course_public,\n"
                + "		c.sub_id, lecturer_id, level_id, \n"
                + "		course_status, s.sub_name, mc.user_id, mc.feature\n"
                + "from Course c inner join Subject s on c.sub_id = s.sub_id\n"
                + "              inner join Manage_Course mc on c.course_id = mc.course_id\n"
                + "where 1=1 ";
        if (sid != null) {
            sql += " and s.sub_id in (";
            for (int i = 0; i < sid.length; i++) {
                sql += sid[i] + ",";
            }
            if (sql.endsWith(",")) {
                sql = sql.substring(0, sql.length() - 1);
            }
            sql += ")";
        }
        if (cstatusArr != null) {
            sql += " and course_status in (";
            for (int i = 0; i < cstatusArr.length; i++) {
                sql += cstatusArr[i] + ",";
            }
            if (sql.endsWith(",")) {
                sql = sql.substring(0, sql.length() - 1);
            }
            sql += ")";
        }
        if (cstatusArr != null) {
            sql += " and course_name like '%" + search + "%'";
        }
        if (uid != 0) {
            sql += " and mc.user_id = " + uid;
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Course(rs.getString("course_id"), rs.getString("course_name"),
                        rs.getString("course_title"), rs.getString("course_img"),
                        rs.getDouble("course_price"), rs.getString("course_desc"),
                        rs.getString("course_start"), rs.getString("course_stop"),
                        rs.getString("course_public"), rs.getInt("sub_id"),
                        rs.getInt("lecturer_id"), rs.getInt("level_id"), rs.getBoolean("course_status")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Course> checkBoxCourseBySidAndPaging(int[] sid, int[] cstatusArr, String search, int uid, int page) {
        List<Course> list = new ArrayList<>();
        String sql = "select	c.course_id, course_name, course_title, \n"
                + "		course_img, course_price,course_desc,\n"
                + "		course_start, course_stop, course_public,\n"
                + "		c.sub_id, lecturer_id, level_id, \n"
                + "		course_status, s.sub_name, mc.user_id, mc.feature\n"
                + "from Course c inner join Subject s on c.sub_id = s.sub_id\n"
                + "              inner join Manage_Course mc on c.course_id = mc.course_id\n"
                + "where 1=1 ";
        if (sid != null) {
            sql += " and s.sub_id in (";
            for (int i = 0; i < sid.length; i++) {
                sql += sid[i] + ",";
            }
            if (sql.endsWith(",")) {
                sql = sql.substring(0, sql.length() - 1);
            }
            sql += ")";
        }
        if (cstatusArr != null) {
            sql += " and course_status in (";
            for (int i = 0; i < cstatusArr.length; i++) {
                sql += cstatusArr[i] + ",";
            }
            if (sql.endsWith(",")) {
                sql = sql.substring(0, sql.length() - 1);
            }
            sql += ")";
        }
        if (cstatusArr != null) {
            sql += " and course_name like '%" + search + "%'";
        }
        if (uid != 0) {
            sql += " and mc.user_id = " + uid;
        }
        sql += "\n order by course_id\n"
                + "offset ? rows fetch next 5 rows only";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (page - 1) * 5);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Course(rs.getString("course_id"), rs.getString("course_name"),
                        rs.getString("course_title"), rs.getString("course_img"),
                        rs.getDouble("course_price"), rs.getString("course_desc"),
                        rs.getString("course_start"), rs.getString("course_stop"),
                        rs.getString("course_public"), rs.getInt("sub_id"),
                        rs.getInt("lecturer_id"), rs.getInt("level_id"), rs.getBoolean("course_status")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertByAdmin(Course s) {
        //insert into Course values (cid, cname, null, cimg, null ,cdes, null,null,null,sid,null,null ,cstatus)
        String sql = "insert into Course values (?, ?, null, ?, null ,?, null,null,null,?,null,null ,'false')";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, s.getCid());
            st.setString(2, s.getCname());
            st.setString(3, s.getCimg());
            st.setString(4, s.getCdesc());
            st.setInt(5, s.getSid());
            st.executeUpdate();//1 or 0
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

//    Fix Iteration 1-----------------------------------------------------------------------------------------------------------------------
    public List<Course> getFeaturedCourse() {
        String sql = "select * from Course where course_status=1 and course_id in (select a.course_id from\n"
                + "(select top 4 c.course_id, COUNT(*) as 'reg_num' from Course c, Registration reg where reg.course_id=c.course_id group by c.course_id order by reg_num desc) a)";
        List<Course> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Course(rs.getString("course_id"), rs.getString("course_name"),
                        rs.getString("course_title"), rs.getString("course_img"),
                        rs.getDouble("course_price"), rs.getString("course_desc"),
                        rs.getString("course_start"), rs.getString("course_stop"),
                        rs.getString("course_public"), rs.getInt("sub_id"),
                        rs.getInt("lecturer_id"), rs.getInt("level_id"), rs.getBoolean("course_status")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    // chieu fix for iteration 3
    public List<Course> getAllDescPublicDate(String from, String to) {
        String sql = "select * from Course where course_public between '" + from + "' and '" + to + "' order by course_public desc";
        List<Course> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Course(rs.getString("course_id"), rs.getString("course_name"),
                        rs.getString("course_title"), rs.getString("course_img"),
                        rs.getDouble("course_price"), rs.getString("course_desc"),
                        rs.getString("course_start"), rs.getString("course_stop"),
                        rs.getString("course_public"), rs.getInt("sub_id"),
                        rs.getInt("lecturer_id"), rs.getInt("level_id"), rs.getBoolean("course_status")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    //Update Course
    public void updateByAdmin(Course s) {
        //Update Course
        //set course_name = ?, course_title = ?, course_img = ?,
        //course_price = ?, course_desc = ?, sub_id = ? , lecturer_id = ?, 
        //level_id = ?, course_status = ? where course_id = ?
        String sql = "Update Course\n"
                + "set course_name = ?, course_title = ?, course_img = ?,\n"
                + "course_price = ?, course_desc = ?, sub_id = ? , lecturer_id = ?, "
                + "level_id = ?, course_status = ? where course_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, s.getCname());
            st.setString(2, s.getCtitle());
            st.setString(3, s.getCimg());
            st.setDouble(4, s.getCprice());
            st.setString(5, s.getCdesc());
            st.setInt(6, s.getSid());
            st.setInt(7, s.getLecid());
            st.setInt(8, s.getLevid());
            st.setBoolean(9, s.isCstatus());
            st.setString(10, s.getCid());
            st.executeUpdate();//1 or 0
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //Update Course
    public void updateByExpert(Course s) {
        //Update Course
        //set course_name = ?, course_title = ?, course_img = ?,
        // course_desc = ?, sub_id = ? , lecturer_id = ?, 
        //level_id = ? where course_id = ?
        String sql = "Update Course\n"
                + "set course_name = ?, course_title = ?, course_img = ?,\n"
                + "course_desc = ?, sub_id = ? , lecturer_id = ?, "
                + " level_id = ? where course_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, s.getCname());
            st.setString(2, s.getCtitle());
            st.setString(3, s.getCimg());
            st.setString(4, s.getCdesc());
            st.setInt(5, s.getSid());
            st.setInt(6, s.getLecid());
            st.setInt(7, s.getLevid());
            st.setString(8, s.getCid());
            st.executeUpdate();//1 or 0
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        CourseDAO cd = new CourseDAO();
        System.out.println(cd.getCourseByQrID(1).getCname());
    }
}
