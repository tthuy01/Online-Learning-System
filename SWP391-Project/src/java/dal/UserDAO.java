/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.xml.bind.DatatypeConverter;
import model.User;
import model.Registration;

/**
 *
 * @author Dell
 */
public class UserDAO extends DBContext {

    public List<User> getAll() {
        String sql = "select * from [User]";
        List<User> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt("user_id"), rs.getString("user_email"),
                        rs.getString("password"), rs.getString("full_name"),
                        rs.getString("user_img"), rs.getInt("gender_id"), rs.getString("user_dob"),
                        rs.getString("user_phone"), rs.getString("user_address"),
                        rs.getString("user_wallet"), rs.getInt("role_id")));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    //validate email
    public boolean isValid(String email) {
        String emailRegex = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }
    public User checkUser(String email) {
        String sql = "select * from [User] where user_email=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("user_id"), rs.getString("user_email"),
                        rs.getString("password"), rs.getString("full_name"),
                        rs.getString("user_img"), rs.getInt("gender_id"), rs.getString("user_dob"),
                        rs.getString("user_phone"), rs.getString("user_address"),
                        rs.getString("user_wallet"));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }
    
    public User getUser(String email) {
        String sql = "select * from [User] where user_email=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("user_id"), rs.getString("user_email"),
                        rs.getString("password"), rs.getString("full_name"),
                        rs.getString("user_img"), rs.getInt("gender_id"), rs.getString("user_dob"),
                        rs.getString("user_phone"), rs.getString("user_address"),
                        rs.getString("user_wallet"), rs.getInt("role_id"), rs.getString("user_time"), rs.getBoolean("user_status"));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    public User checkUser(String email, String pass) throws NoSuchAlgorithmException {
        String sql = "select * from [User] where user_email=? and password=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, Encryption((pass)));
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("user_id"), rs.getString("user_email"),
                        rs.getString("password"), rs.getString("full_name"),
                        rs.getString("user_img"), rs.getInt("gender_id"), rs.getString("user_dob"),
                        rs.getString("user_phone"), rs.getString("user_address"),
                        rs.getString("user_wallet"), rs.getInt("role_id"));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }
    public User checkULogin(String email, String pass) throws NoSuchAlgorithmException {
        String sql = "select * from [User] where user_email=? and password=? and user_status = 1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, Encryption((pass)));
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("user_id"), rs.getString("user_email"),
                        rs.getString("password"), rs.getString("full_name"),
                        rs.getString("user_img"), rs.getInt("gender_id"), rs.getString("user_dob"),
                        rs.getString("user_phone"), rs.getString("user_address"),
                        rs.getString("user_wallet"), rs.getInt("role_id"));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    public String getRole(User u) {
        String sql = "select r.role_name from [User] u\n"
                + "join [Role] r on u.role_id = r.role_id\n"
                + "where u.user_id = " + u.getUid();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString("role_name");
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public User getUserByUid(int uId) {
        //select * from [User] where user_id=1;
        String sql = "select * from [User] where user_id=" + uId;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("user_id"), rs.getString("user_email"),
                        rs.getString("password"), rs.getString("full_name"),
                        rs.getString("user_img"), rs.getInt("gender_id"),
                        rs.getString("user_dob"), rs.getString("user_phone"),
                        rs.getString("user_address"), rs.getString("user_wallet"),
                        rs.getInt("role_id"),rs.getString("user_time"), rs.getBoolean("user_status"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void updateUser(User u, int uid) {
        String sql = "update [User] \n"
                + "set user_email = ?, full_name = ?, user_img = ?, gender_id = ? , user_dob = ?, \n"
                + "user_phone = ?, user_address = ?, user_wallet= ?, role_id = ?\n"
                + "where user_id = " + uid;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, u.getUemail());
            st.setString(2, u.getUfullname());
            st.setString(3, u.getUimg());
            st.setInt(4, u.getGid());
            st.setString(5, u.getUdob());
            st.setString(6, u.getUphone());
            st.setString(7, u.getUaddress());
            st.setString(8, u.getUwallet());
            st.setInt(9, u.getRid());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateNewPass(String email, String newpass) {
        String sql = "update [User] "
                + " set password = ?"
                + " where user_email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newpass);
            st.setString(2, email);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateUserPassword(User u) {
        String sql = "Update [User] set [password] = ? where user_email like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, u.getUpassword());
            st.setString(2, u.getUemail());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public String Encryption(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest);
        return myHash;
    }

    public void insertUser(User ca) throws NoSuchAlgorithmException {
        String sql = "insert into [User](user_email, password, full_name, gender_id, role_id,user_time,user_status) values(?,?,?,?,?,GETDATE(),?)";
        try {
            String encrypt = Encryption(ca.getUpassword());
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, ca.getUemail());
            st.setString(2, encrypt);
            st.setString(3, ca.getUfullname());
            st.setInt(4, ca.getGid());
            st.setInt(5, ca.getRid());
            st.setInt(6, 1);
            st.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public List<User> getTopNewUser(String from, String to) {
        String sql = "select * from [User] where user_time between '" + from + "' and '" + to + "' order by user_time desc";
        List<User> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt("user_id"), rs.getString("user_email"),
                        rs.getString("password"), rs.getString("full_name"),
                        rs.getString("user_img"), rs.getInt("gender_id"), rs.getString("user_dob"),
                        rs.getString("user_phone"), rs.getString("user_address"),
                        rs.getString("user_wallet"), rs.getInt("role_id"), rs.getString("user_time")));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    //Iteration 3: Thuy
    public List<User> getListUserByListReg(List<Registration> list) {
        List<User> listUser = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            listUser.add(getUserByUid(list.get(i).getUid()));
        }
        return listUser;
    }

    //Thanh
    public List<User> getUserByRoleExpert(int rId) {
        String sql = "select * from [User] where role_id=5";
        List<User> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt("user_id"), rs.getString("user_email"),
                        rs.getString("password"), rs.getString("full_name"),
                        rs.getString("user_img"), rs.getInt("gender_id"), rs.getString("user_dob"),
                        rs.getString("user_phone"), rs.getString("user_address"),
                        rs.getString("user_wallet"), rs.getInt("role_id")));
            }
        } catch (SQLException e) {
        }
        return list;
    }
    
    //==========================================Dao iter4 Admin role======================================================
    public List<User> getListUserAR(int gid, int rid, int ustatus, String ufullname, String uemail, String uphone, int sort, int page) {
        List<User> list = new ArrayList<>();
        String sql = "select * from [User] where user_id != 0";
        if (gid != 0) {
            sql += " and gender_id = " + gid;
        }
        if (rid != 0) {
            sql += " and role_id = " + rid;
        }
        if (ustatus != 2) {
            sql += " and user_status = " + ustatus;
        }
        if (ufullname != null) {
            sql += " and full_name like '%" + ufullname + "%'";
        }
        if (uemail != null) {
            sql += " and user_email like '%" + uemail + "%'";
        }
        if (uphone != null) {
            sql += " and user_phone like '%" + uphone + "%'";
        }
        switch (sort) {
            case 1:
                sql += " order by user_id";
                break;
            case 2:
                sql += " order by full_name";
                break;
            case 3:
                sql += " order by gender_id";
                break;
            case 4:
                sql += " order by user_email";
                break;
            case 5:
                sql += " order by user_phone";
                break;
            case 6:
                sql += " order by role_id desc";
                break;
            case 7:
                sql += " order by user_status";
                break;
            default:
                break;
        }
        if (page != 0) {
            sql += " offset " + (page - 1) * 5 + " rows FETCH NEXT 5 ROWS ONLY";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setUid(rs.getInt(1));
                u.setUemail(rs.getString(2));
                u.setUpassword(rs.getString(3));
                u.setUfullname(rs.getString(4));
                u.setUimg(rs.getString(5));
                u.setGid(rs.getInt(6));
                u.setUdob(rs.getString(7));
                u.setUphone(rs.getString(8));
                u.setUaddress(rs.getString(9));
                u.setUwallet(rs.getString(10));
                u.setRid(rs.getInt(11));
                u.setUtime(rs.getString(12));
                u.setUstatus(rs.getBoolean(13));
                list.add(u);

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void updateUserAR(User u, int uid) {
        String sql = "update [User] \n"
                + "set user_email = ?, full_name = ?, user_img = ?, gender_id = ? , user_dob = ?, \n"
                + "user_phone = ?, user_address = ?, user_wallet= ?, role_id = ?, user_status = ?\n"
                + "where user_id = " + uid;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, u.getUemail());
            st.setString(2, u.getUfullname());
            st.setString(3, u.getUimg());
            st.setInt(4, u.getGid());
            st.setString(5, u.getUdob());
            st.setString(6, u.getUphone());
            st.setString(7, u.getUaddress());
            st.setString(8, u.getUwallet());
            st.setInt(9, u.getRid());
            st.setBoolean(10, u.isUstatus());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void insertUserAR(User u) {
        String sql = "insert [User] (user_email,password,full_name,user_img,gender_id,user_dob,user_phone,user_address,user_wallet,role_id,user_status)\n"
                + "values (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, u.getUemail());
            st.setString(2, u.getUpassword());
            st.setString(3, u.getUfullname());
            st.setString(4, u.getUimg());
            st.setInt(5, u.getGid());
            st.setString(6, u.getUdob());
            st.setString(7, u.getUphone());
            st.setString(8, u.getUaddress());
            st.setString(9, u.getUwallet());
            st.setInt(10, u.getRid());
            st.setBoolean(11, u.isUstatus());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //==========================================Dao iter4 Admin role======================================================

    public static void main(String[] args) {
        UserDAO cd = new UserDAO();
        List<User> list = cd.getListUserAR(0, 0, 2, null, null, null, 1, 0);
        System.out.println(list.size());
//        for (User c : cd.getAll()) {
//            System.out.println(c);
//        }
    }
}
