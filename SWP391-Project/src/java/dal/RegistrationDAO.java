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
import model.NewRegistration;
import model.Registration;

public class RegistrationDAO extends DBContext {

    public List<Registration> getAll() {
        String sql = "Select * from Registration";
        List<Registration> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Registration(rs.getInt("reg_id"), rs.getString("reg_time"),
                        rs.getString("reg_note"), rs.getString("course_id"), rs.getInt("user_id"),
                        rs.getByte("reg_status_id"), rs.getInt("package_id")));
            }
        } catch (Exception e) {
        }
        return list;
    }

//    public Registration getRegByCourseId(String cid) {
//        String sql = "select * from Registration where course_id = ?";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setString(1, cid);
//            ResultSet rs = st.executeQuery();
//            if (rs.next()) {
//                Registration r = new Registration(rs.getInt("reg_id"), rs.getString("reg_time"),
//                        rs.getString("reg_note"), rs.getString("course_id"), rs.getInt("user_id"),
//                        rs.getInt("reg_status_id"), rs.getInt("package_id"));
//                return r;
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return null;
//    }
//===============================Thanh=================================
    // Course Registered
    public Registration checkEnoll(int uid, String cid) {
        String sql = "select * from Registration where user_id = ? and course_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, uid);
            st.setString(2, cid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Registration r = new Registration(rs.getInt("reg_id"), rs.getString("reg_time"),
                        rs.getString("reg_note"), rs.getString("course_id"), rs.getInt("user_id"),
                        rs.getInt("reg_status_id"), rs.getInt("package_id"));
                return r;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    // Course Registered
    public void insert(Registration r) {
//        insert into Registration values(time,note,cid, uid, 0, packid)
        String sql = "insert into Registration values(?,?,?, ?, ?, ?, null)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, r.getRtime());
            st.setString(2, r.getRnote());
            st.setString(3, r.getCid());
            st.setInt(4, r.getUid());
            st.setInt(5, r.getRstatusId());
            st.setInt(6, r.getPid());
            st.executeUpdate();//1 or 0
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updatePackagePriceByCidAndUid(String cid, int uid, int pid) {
        String sql = "update Registration\n"
                + "set package_id = " + pid + " where course_id = ? and user_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);
            st.setInt(2, uid);
            st.executeUpdate();//1 or 0
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
//====================het phan Thanh===================================

//==============================THUY====================================
    public Registration getRegByRegId(int rid) {
        String sql = "select * from Registration where reg_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, rid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Registration r = new Registration();
                r.setRid(rs.getInt("reg_id"));
                r.setRtime(rs.getString("reg_time"));
                r.setRnote(rs.getString("reg_note"));
                r.setCid(rs.getString("course_id"));
                r.setUid(rs.getInt("user_id"));
                r.setRstatusId(rs.getInt("reg_status_id"));
                r.setPid(rs.getInt("package_id"));
                return r;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Registration> getRegistrationByUserID(int uid) {
        List<Registration> list = new ArrayList<>();
        String sql = "select * from Registration\n"
                + "where user_id = " + uid;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Registration r = new Registration();
                r.setRid(rs.getInt("reg_id"));
                r.setRtime(rs.getString("reg_time"));
                r.setRnote(rs.getString("reg_note"));
                r.setCid(rs.getString("course_id"));
                r.setUid(rs.getInt("user_id"));
                r.setRstatusId(rs.getInt("reg_status_id"));
                r.setPid(rs.getInt("package_id"));
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public String getCourseNameByCourseID(String cid) {
        String sql = "select c.course_name from Course c\n"
                + "join Registration r on r.course_id = c.course_id\n"
                + "where c.course_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<String> getListCourseNameByListRegistration(List<Registration> list) {
        List<String> rs = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            rs.add(getCourseNameByCourseID(list.get(i).getCid()));
        }
        return rs;
    }

    public String getSubjectByCourseID(String cid) {
        String sql = "select s.sub_name from [Subject] s\n"
                + "join Course c on s.sub_id = c.sub_id\n"
                + "join Registration r on r.course_id = c.course_id\n"
                + "where c.course_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<String> getListSubjectByListRegistration(List<Registration> list) {
        List<String> rs = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            rs.add(getSubjectByCourseID(list.get(i).getCid()));
        }
        return rs;
    }

    public String getPackageNameByRegId(int rid) {
        String pname = "";
        String sql = "select p.package_name from Registration r\n"
                + "join Price_Package p on r.package_id = p.package_id\n"
                + "where r.reg_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, rid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                pname = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return pname;
    }

    public List<String> getListPackageName(List<Registration> list) {
        List<String> listPacName = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            listPacName.add(getPackageNameByRegId(list.get(i).getRid()));
        }
        return listPacName;
    }

    public double getTotalPrice(Registration r) {
        double totalPrice = 0;
        String sql = "select c.course_price from Registration r\n"
                + "join Course c on r.course_id = c.course_id\n"
                + "where r.reg_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, r.getRid());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                double price = rs.getInt("course_price");
                if (getPackageNameByRegId(r.getRid()).toLowerCase().equals("Silver".toLowerCase())) //Silver
                {
                    totalPrice = price * 0.8;
                }
                if (getPackageNameByRegId(r.getRid()).toLowerCase().equals("Gold".toLowerCase())) //Gold
                {
                    totalPrice = price * 1.5 * 0.8;
                }
                if (getPackageNameByRegId(r.getRid()).toLowerCase().equals("Diamond".toLowerCase())) //Diamond
                {
                    totalPrice = price * 2 * 0.8;
                }
                return totalPrice;
            }
        } catch (SQLException e) {
        }
        return totalPrice;
    }

    public List<Double> getListTotalPrice(List<Registration> list) {
        List<Double> rs = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            rs.add(getTotalPrice(list.get(i)));
        }
        return rs;
    }

    public int getDurationByRid(int rid) {
        String sql = "select p.duration from Registration r \n"
                + "join Price_Package p on r.package_id = p.package_id\n"
                + "where r.reg_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, rid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public String getValidToByRid(Registration r) {
        String validTo = "";
        String sql = "select DATEADD(MONTH,?,?) from Registration r\n"
                + "where r.reg_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, getDurationByRid(r.getRid()));
            st.setString(2, r.getRtime());
            st.setInt(3, r.getRid());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                validTo = rs.getString(1).substring(0, 10);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return validTo;
    }

    public List<String> getListValidTo(List<Registration> list) {
        List<String> rs = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            rs.add(getValidToByRid(list.get(i)));
        }
        return rs;
    }

    public List<Registration> bothSearchAndFilter(String search, String[] subCateId) {
        List<Registration> list = new ArrayList<>();
        String sql = "select r.reg_id, r.reg_time, r.reg_note, r.course_id, r.user_id, r.reg_status_id, r.package_id from Registration r\n"
                + "  join Course c on r.course_id = c.course_id\n"
                + "  join Subject s on c.sub_id = s.sub_id\n"
                + "  join Subject_Category sc on s.subject_cate_id = sc.subject_cate_id\n"
                + "  where 1=1 ";
        if (search != null && !search.trim().equals("")) {
            sql += "and s.sub_name like ?";
        }
        if (subCateId != null) {
            sql += " and sc.subject_cate_id in (";
            for (int i = 0; i < subCateId.length; i++) {
                sql += "" + subCateId[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += ")";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            if (search != null && !search.trim().equals("")) {
                st.setString(1, "%" + search + "%");
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Registration r = new Registration();
                r.setRid(rs.getInt("reg_id"));
                r.setRtime(rs.getString("reg_time"));
                r.setRnote(rs.getString("reg_note"));
                r.setCid(rs.getString("course_id"));
                r.setUid(rs.getInt("user_id"));
                r.setRstatusId(rs.getInt("reg_status_id"));
                r.setPid(rs.getInt("package_id"));
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void deleteRegistration(int rid) {
        String sql = "delete from Registration where reg_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, rid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
//==========================het phan cua Thuy===============================

    public double getTotalProfit() {
        String sql = "select SUM(c.course_price*pp.multiple) from Registration reg, Course c, Price_Package pp "
                + "where reg.course_id=c.course_id and reg.package_id=pp.package_id";
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

    public List<NewRegistration> getTopRegistrations(String from, String to) {
        String sql = "select u.user_img, u.full_name, c.course_name, r.reg_time, rs.reg_status_name, pp.package_name \n"
                + "from [User] u, Course c, Registration r, Registration_Status rs, Price_Package pp\n"
                + "where u.user_id=r.user_id and c.course_id=r.course_id and r.reg_status_id=rs.reg_status_id and r.package_id=pp.package_id\n"
                + "and r.reg_time between '" + from + "' and '" + to + "'\n"
                + "order by r.reg_id desc";
        List<NewRegistration> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new NewRegistration(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Integer> getNumOfRegStatus(List<String> listdate, String status, String statby) {
        List<Integer> list = new ArrayList<>();
        String sql = "";
        if (statby.equals("day")) {
            sql = "select count(*) from Registration where reg_time = ? and reg_status_id = " + status;
        }
        if (statby.equals("month")) {
            sql = "select count(*) from Registration where " + statby + "(reg_time) = ? and year(reg_time) = ? and reg_status_id = " + status;
        }
        if (statby.equals("year")) {
            sql = "select count(*) from Registration where " + statby + "(reg_time) = ? and reg_status_id = " + status;
        }
        for (String s : listdate) {
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                if (statby.equals("month")) {
                    st.setString(1, s.split("-")[0]);
                    st.setString(2, s.split("-")[1]);
                } else {
                    st.setString(1, s);
                }
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    list.add(rs.getInt(1));
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    //Interation 3: Thuy----------------------------------------------------------------------
    public int countTotalOfRes(String search, String[] subCateId, String[] subId, String[] packId, String[] costFrom, String[] costTo, String[] statusId, String regTimeFrom, String regTimeTo, String sortBy) {
        String sql = "select count(*) \n"
                + "from Registration r\n"
                + "join Course c on r.course_id = c.course_id\n"
                + "join Subject s on c.sub_id = s.sub_id\n"
                + "join Subject_Category sc on s.subject_cate_id = sc.subject_cate_id\n"
                + "join Price_Package pp on r.package_id = pp.package_id join [User] u on u.user_id = r.user_id \n"
                + "where 1=1 ";
        if (search != null && !search.trim().equals("")) {
            sql += " and (c.course_name like '%" + search + "%' or s.sub_name like '%" + search + "%' or u.user_email like '%" + search + "%')";
        }

        if (subCateId != null) {
            sql += " and sc.subject_cate_id in (";
            for (int i = 0; i < subCateId.length; i++) {
                sql += "" + subCateId[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += ")";
        }

        if (subId != null) {
            sql += " and s.sub_id in (";
            for (int i = 0; i < subId.length; i++) {
                sql += "" + subId[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += ")";
        }

        if (packId != null) {
            sql += " and pp.package_id in (";
            for (int i = 0; i < packId.length; i++) {
                sql += "" + packId[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += ")";
        }

        if (costFrom != null && costTo != null) {
            sql += " and ";
            for (int i = 0; i < costFrom.length; i++) {
                sql += " ((c.course_price*pp.multiple) >= " + costFrom[i] + " and (c.course_price*pp.multiple) <= " + costTo[i] + ") or";
            }
            sql = sql.substring(0, sql.length() - 2);
        }

        if (statusId != null) {
            sql += " and r.reg_status_id in (";
            for (int i = 0; i < statusId.length; i++) {
                sql += "" + statusId[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += ")";
        }

        if (regTimeFrom != null && !regTimeFrom.trim().equals("")) {
            sql += " and r.reg_time >= '" + regTimeFrom + "'";
        }

        if (regTimeTo != null && !regTimeTo.trim().equals("")) {
            sql += " and r.reg_time <= '" + regTimeTo + "'";
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<Registration> getListResPaging(int offset, int fetchNext) {
        List<Registration> list = new ArrayList<>();
        String sql = "select * from Registration\n"
                + "order by reg_id\n"
                + "offset ? rows\n"
                + "fetch next ? rows only;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, offset);
            st.setInt(2, fetchNext);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Registration(rs.getInt("reg_id"), rs.getString("reg_time"),
                        rs.getString("reg_note"), rs.getString("course_id"), rs.getInt("user_id"),
                        rs.getByte("reg_status_id"), rs.getInt("package_id"), rs.getString("last_updated_by")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Registration> getAllAndFilterListReg(String search, String[] subCateId, String[] subId, String[] packId, String[] costFrom, String[] costTo, String[] statusId, String regTimeFrom, String regTimeTo, String sortBy, int page) {
        List<Registration> list = new ArrayList<>();
        String sql = "select r.reg_id, r.reg_time, r.reg_note, r. course_id, r.user_id, r.reg_status_id, r.package_id,r.last_updated_by \n"
                + "from Registration r\n"
                + "join Course c on r.course_id = c.course_id\n"
                + "join Subject s on c.sub_id = s.sub_id\n"
                + "join Subject_Category sc on s.subject_cate_id = sc.subject_cate_id\n"
                + "join Price_Package pp on r.package_id = pp.package_id join [User] u on u.user_id = r.user_id\n"
                + "where 1=1 ";
        if (search != null && !search.trim().equals("")) {
            sql += " and (c.course_name like '%" + search + "%' or s.sub_name like '%" + search + "%' or u.user_email like '%" + search + "%')";
        }

        if (subCateId != null) {
            sql += " and sc.subject_cate_id in (";
            for (int i = 0; i < subCateId.length; i++) {
                sql += "" + subCateId[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += ")";
        }

        if (subId != null) {
            sql += " and s.sub_id in (";
            for (int i = 0; i < subId.length; i++) {
                sql += "" + subId[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += ")";
        }

        if (packId != null) {
            sql += " and pp.package_id in (";
            for (int i = 0; i < packId.length; i++) {
                sql += "" + packId[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += ")";
        }

        if (costFrom != null && costTo != null) {
            sql += " and ";
            for (int i = 0; i < costFrom.length; i++) {
                sql += " ((c.course_price*pp.multiple) >= " + costFrom[i] + " and (c.course_price*pp.multiple) <= " + costTo[i] + ") or";
            }
            sql = sql.substring(0, sql.length() - 2);
        }

        if (statusId != null) {
            sql += " and r.reg_status_id in (";
            for (int i = 0; i < statusId.length; i++) {
                sql += "" + statusId[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += ")";
        }

        if (regTimeFrom != null && !regTimeFrom.trim().equals("")) {
            sql += " and r.reg_time >= '" + regTimeFrom + "'";
        }

        if (regTimeTo != null && !regTimeTo.trim().equals("")) {
            sql += " and r.reg_time <= '" + regTimeTo + "'";
        }

        if (sortBy != null && !sortBy.trim().equals("")) {
            if (sortBy.toLowerCase().equals("id")) {
                sql += " order by r.reg_id desc";
            }
            if (sortBy.toLowerCase().equals("registrationTime".toLowerCase())) {
                sql += " order by r.reg_time desc";
            }
            if (sortBy.toLowerCase().equals("course")) {
                sql += " order by c.course_name desc";
            }
            if (sortBy.toLowerCase().equals("subject")) {
                sql += " order by s.sub_name desc";
            }
            if (sortBy.toLowerCase().equals("package")) {
                sql += " order by pp.package_name desc";
            }
            if (sortBy.toLowerCase().equals("totalCost".toLowerCase())) {
                sql += " order by c.course_price*pp.multiple desc";
            }
        }

        if (sortBy.equals("")) {
            sql += " order by r.reg_id";
        }

        sql += " offset " + ((page - 1) * 10) + " rows\n"
                + "fetch next 10 rows only;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Registration(rs.getInt("reg_id"), rs.getString("reg_time"),
                        rs.getString("reg_note"), rs.getString("course_id"), rs.getInt("user_id"),
                        rs.getByte("reg_status_id"), rs.getInt("package_id"), rs.getString("last_updated_by")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public double getMaxTotalCost() {
        String sql = "select MAX(c.course_price*p.multiple) from Registration r\n"
                + "join Course c on r.course_id = c.course_id\n"
                + "join Price_Package p on r.package_id = p.package_id";
        double max = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                max = rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return max; //Lam tron len 1 chu so
    }

    public void updateByCidAndUid(Registration r) {
        String sql = "update Registration\n"
                + "set reg_status_id = 1 where course_id = ? and user_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, r.getCid());
            st.setInt(2, r.getUid());
            st.executeUpdate();//1 or 0
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateRegisStatus(int rid, String note, int regStatus) {
        String sql = "update Registration set reg_status_id = ?, reg_note = ? where reg_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, regStatus);
            st.setString(2, note);
            st.setInt(3, rid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean checkCourseExistInMyCourse(int uid, String cid) {
        String sql = "select c.course_id, c.course_name, c.course_title, c.course_img,\n"
                + "c.course_price, c.course_desc, c.course_start, c.course_stop,\n"
                + "c.course_public, c.sub_id, c.lecturer_id, c.level_id, c.course_status\n"
                + "from Course c\n"
                + "join Registration r on c.course_id = r.course_id\n"
                + "join Price_Package p on r.package_id = p.package_id\n"
                + "where r.user_id = ? and r.course_id = ? and GETDATE() >= r.reg_time and GETDATE() <= DATEADD(MONTH,p.duration,r.reg_time)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, uid);
            st.setString(2, cid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public void updateRegisInfo(int rid, String cid, int pid, String lastUB, String note, String rtime) {
        String sql = "update Registration set course_id = ?, package_id = ?, reg_time = ?, last_updated_by = ?, reg_note = ? where reg_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);
            st.setInt(2, pid);
            st.setString(3, rtime);
            st.setString(4, lastUB);
            st.setString(5, note);
            st.setInt(6, rid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    //Thuy: fix + them iteration 4
    public int countMyRegistration(int uid, String search, String[] subCateId, String[] subId, String[] packId, String[] costFrom, String[] costTo, String[] statusId, String regTimeFrom, String regTimeTo, String sortBy, int page) {
        String sql = "select count(*) \n"
                + "from Registration r\n"
                + "join Course c on r.course_id = c.course_id\n"
                + "join Subject s on c.sub_id = s.sub_id\n"
                + "join Subject_Category sc on s.subject_cate_id = sc.subject_cate_id\n"
                + "join Price_Package pp on r.package_id = pp.package_id join [User] u on u.user_id = r.user_id \n"
                + "where r.user_id = ? ";
        if (search != null && !search.trim().equals("")) {
            sql += " and (c.course_name like '%" + search + "%' or s.sub_name like '%" + search + "%')";
        }

        if (subCateId != null) {
            sql += " and sc.subject_cate_id in (";
            for (int i = 0; i < subCateId.length; i++) {
                sql += "" + subCateId[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += ")";
        }

        if (subId != null) {
            sql += " and s.sub_id in (";
            for (int i = 0; i < subId.length; i++) {
                sql += "" + subId[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += ")";
        }

        if (packId != null) {
            sql += " and pp.package_id in (";
            for (int i = 0; i < packId.length; i++) {
                sql += "" + packId[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += ")";
        }

        if (costFrom != null && costTo != null) {
            sql += " and ";
            for (int i = 0; i < costFrom.length; i++) {
                sql += " ((c.course_price*pp.multiple) >= " + costFrom[i] + " and (c.course_price*pp.multiple) <= " + costTo[i] + ") or";
            }
            sql = sql.substring(0, sql.length() - 2);
        }

        if (statusId != null) {
            sql += " and r.reg_status_id in (";
            for (int i = 0; i < statusId.length; i++) {
                sql += "" + statusId[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += ")";
        }

        if (regTimeFrom != null && !regTimeFrom.trim().equals("")) {
            sql += " and r.reg_time >= '" + regTimeFrom + "'";
        }

        if (regTimeTo != null && !regTimeTo.trim().equals("")) {
            sql += " and r.reg_time <= '" + regTimeTo + "'";
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, uid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public List<Registration> bothSearchAndFilter(int uid, String search, String[] subCateId, String[] subId, String[] packId, String[] costFrom, String[] costTo, String[] statusId, String regTimeFrom, String regTimeTo, String sortBy, int page) {
        List<Registration> list = new ArrayList<>();
        String sql = "select r.reg_id, r.reg_time, r.reg_note, r. course_id, r.user_id, r.reg_status_id, r.package_id,r.last_updated_by \n"
                + "from Registration r\n"
                + "join Course c on r.course_id = c.course_id\n"
                + "join Subject s on c.sub_id = s.sub_id\n"
                + "join Subject_Category sc on s.subject_cate_id = sc.subject_cate_id\n"
                + "join Price_Package pp on r.package_id = pp.package_id join [User] u on u.user_id = r.user_id\n"
                + "where r.user_id = ? ";
        if (search != null && !search.trim().equals("")) {
            sql += " and (c.course_name like '%" + search + "%' or s.sub_name like '%" + search + "%')";
        }

        if (subCateId != null) {
            sql += " and sc.subject_cate_id in (";
            for (int i = 0; i < subCateId.length; i++) {
                sql += "" + subCateId[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += ")";
        }

        if (subId != null) {
            sql += " and s.sub_id in (";
            for (int i = 0; i < subId.length; i++) {
                sql += "" + subId[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += ")";
        }

        if (packId != null) {
            sql += " and pp.package_id in (";
            for (int i = 0; i < packId.length; i++) {
                sql += "" + packId[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += ")";
        }

        if (costFrom != null && costTo != null) {
            sql += " and ";
            for (int i = 0; i < costFrom.length; i++) {
                sql += " ((c.course_price*pp.multiple) >= " + costFrom[i] + " and (c.course_price*pp.multiple) <= " + costTo[i] + ") or";
            }
            sql = sql.substring(0, sql.length() - 2);
        }

        if (statusId != null) {
            sql += " and r.reg_status_id in (";
            for (int i = 0; i < statusId.length; i++) {
                sql += "" + statusId[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += ")";
        }

        if (regTimeFrom != null && !regTimeFrom.trim().equals("")) {
            sql += " and r.reg_time >= '" + regTimeFrom + "'";
        }

        if (regTimeTo != null && !regTimeTo.trim().equals("")) {
            sql += " and r.reg_time <= '" + regTimeTo + "'";
        }

        if (sortBy != null && !sortBy.trim().equals("")) {
            if (sortBy.toLowerCase().equals("id")) {
                sql += " order by r.reg_id desc";
            }
            if (sortBy.toLowerCase().equals("registrationTime".toLowerCase())) {
                sql += " order by r.reg_time desc";
            }
            if (sortBy.toLowerCase().equals("course")) {
                sql += " order by c.course_name desc";
            }
            if (sortBy.toLowerCase().equals("subject")) {
                sql += " order by s.sub_name desc";
            }
            if (sortBy.toLowerCase().equals("package")) {
                sql += " order by pp.package_name desc";
            }
            if (sortBy.toLowerCase().equals("totalCost".toLowerCase())) {
                sql += " order by c.course_price*pp.multiple desc";
            }
        }

        if (sortBy.equals("")) {
            sql += " order by r.reg_id";
        }

        sql += " offset " + ((page - 1) * 10) + " rows\n"
                + "fetch next 10 rows only;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, uid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Registration(rs.getInt("reg_id"), rs.getString("reg_time"),
                        rs.getString("reg_note"), rs.getString("course_id"), rs.getInt("user_id"),
                        rs.getByte("reg_status_id"), rs.getInt("package_id"), rs.getString("last_updated_by")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void UpdateRegistration(int uid, int rid, String name, int genderId, String phone, String address, String lastUpBy)
    {
        String sql = "update [User] set full_name=?, gender_id=?, user_phone=?, user_address=? where user_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setInt(2, genderId);
            st.setString(3, phone);
            st.setString(4, address);
            st.setInt(5, uid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
//        String sql1 = "update Registration set last_updated_by=? where reg_id = ?";
        String sql1 = "update Registration set last_updated_by=? where user_id = ?";
        try {
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setString(1, lastUpBy);
            st1.setInt(2, uid);
            st1.executeUpdate();
        } catch (SQLException e1) {
            System.out.println(e1);
        }
    }

    public static void main(String[] args) {
        RegistrationDAO rd = new RegistrationDAO();
//        rd.insert(new Registration("2022-05-05", null, "GUI", 8, 1, 4));
//        System.out.println(rd.checkEnoll(8, "CP"));
        List<String> listdate = new ArrayList<>();
        listdate.add("2022-06-20");
        listdate.add("2022-06-21");
        listdate.add("2022-06-27");
        listdate.add("2022-06-28");
        System.out.println(rd.getNumOfRegStatus(listdate, "1", "month").size());

    }
}
