/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Post;

/**
 *
 * @author ADMIN
 */
public class PostDAO extends DBContext {

    public List<Post> getPost() {
        String sql = "select p.* from Post p \n"
                + "inner join Blog b on p.blog_id=b.blog_id where p.post_status = 1\n"
                + "order by post_date desc ";
        List<Post> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Post p = new Post();
                p.setPoid(rs.getInt(1));
                p.setPoimg(rs.getString(2));
                p.setPotitle(rs.getString(3));
                p.setPodesc(rs.getString(4));
                p.setPodate(rs.getString(5));
                p.setPostatus(rs.getBoolean(6));
                p.setBid(rs.getInt(7));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Post> getTop3Post() {
        String sql = "select top 3 p.* from Post p \n"
                + "inner join Blog b on p.blog_id=b.blog_id where p.post_status = 1\n"
                + "order by post_date desc";
        List<Post> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Post p = new Post();
                p.setPoid(rs.getInt(1));
                p.setPoimg(rs.getString(2));
                p.setPotitle(rs.getString(3));
                p.setPodesc(rs.getString(4));
                p.setPodate(rs.getString(5));
                p.setPostatus(rs.getBoolean(6));
                p.setBid(rs.getInt(7));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Post> getTop4Posts(String status) {
        String sql = "select top 4 post_id\n"
                    + ",post_img\n"
                    + ",post_title\n"
                    + ",post_desc\n"
                    + ",post_date\n"
                    + ",post_status\n"
                    + ",blog_id\n"
                    + "from Post where post_status = 1";
        if (status.equals("early")) sql += " order by post_date";
        else sql += " order by post_date desc";
        List<Post> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Post p = new Post();
                p.setPoid(rs.getInt(1));
                p.setPoimg(rs.getString(2));
                p.setPotitle(rs.getString(3));
                p.setPodesc(rs.getString(4));
                p.setPodate(rs.getString(5));
                p.setPostatus(rs.getBoolean(6));
                p.setBid(rs.getInt(7));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Post> getTop3PostRelate(int id, int bid) {
        String sql = "select top 3 p.* from Post p \n"
                + "inner join Blog b on p.blog_id=b.blog_id\n"
                + "where p.blog_id=? and post_id!=? and p.post_status = 1\n"
                + "order by post_date desc";
        List<Post> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, bid);
            st.setInt(2, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Post p = new Post();
                p.setPoid(rs.getInt(1));
                p.setPoimg(rs.getString(2));
                p.setPotitle(rs.getString(3));
                p.setPodesc(rs.getString(4));
                p.setPodate(rs.getString(5));
                p.setPostatus(rs.getBoolean(6));
                p.setBid(rs.getInt(7));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Post> getPostByBlogID(int bid) {
        String sql = "select p.* from Post p \n"
                + "inner join Blog b on p.blog_id=b.blog_id\n"
                + "where p.blog_id=? and p.post_status = 1\n"
                + "order by post_date desc";
        List<Post> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, bid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Post p = new Post();
                p.setPoid(rs.getInt(1));
                p.setPoimg(rs.getString(2));
                p.setPotitle(rs.getString(3));
                p.setPodesc(rs.getString(4));
                p.setPodate(rs.getString(5));
                p.setPostatus(rs.getBoolean(6));
                p.setBid(rs.getInt(7));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Post getPostByID(int id) {
        String sql = "select * from Post where post_id= ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Post(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getInt(7));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Post> getListByPage(List<Post> list,
            int start, int end) {
        ArrayList<Post> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public List<Post> searchPost(String key) {
        List<Post> list = new ArrayList<>();
        String sql = "select * from Post where post_title like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + key + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Post p = new Post();
                p.setPoid(rs.getInt(1));
                p.setPoimg(rs.getString(2));
                p.setPotitle(rs.getString(3));
                p.setPodesc(rs.getString(4));
                p.setPodate(rs.getString(5));
                p.setPostatus(rs.getBoolean(6));
                p.setBid(rs.getInt(7));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<Post> getListPostByPage(int page, int bid, String key) {
        List<Post> list = new ArrayList<>();
        String sql = "select * from Post where post_title like '%" + key + "%'";
        try {
            if (bid != 0) {
                sql += " and blog_id = " + bid;
            }
            sql += " order by post_date desc offset " + (page - 1) * 10 + " rows FETCH NEXT 10 ROWS ONLY";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Post p = new Post();
                p.setPoid(rs.getInt(1));
                p.setPoimg(rs.getString(2));
                p.setPotitle(rs.getString(3));
                p.setPodesc(rs.getString(4));
                p.setPodate(rs.getString(5));
                p.setPostatus(rs.getBoolean(6));
                p.setBid(rs.getInt(7));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public int getCountNumber(int bid, String key) {
        String sql = "select count(*) from Post where post_title like '%" + key + "%'";
        try {
            if (bid != 0) {
                sql += " and blog_id = " + bid;
            }
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

    public void updateStatusPost(int hide, int pid) {
        String sql = "update Post set post_status = ? where post_id= ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, hide);
            st.setInt(2, pid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updatePost(int pid, String img, String title, String desc, int status, int bid) {
        LocalDate curDate = LocalDate.now();
        String date = curDate.toString();
        String sql = "update Post set post_img= ? , post_title= ?,post_desc= ?, post_date = ?, post_status = ?, blog_id = ?\n"
                + "where post_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, img);
            st.setString(2, title);
            st.setString(3, desc);
            st.setString(4, date);
            st.setInt(5, status);
            st.setInt(6, bid);
            st.setInt(7, pid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        
    }
    
    public void insertPost(String img, String title, String desc, int status, int bid){
        LocalDate curDate = LocalDate.now();
        String date = curDate.toString();
        String sql = "insert into Post(post_img,post_title,post_desc,post_date,post_status,blog_id) values (?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, img);
            st.setString(2, title);
            st.setString(3, desc);
            st.setString(4, date);
            st.setInt(5, status);
            st.setInt(6, bid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        PostDAO pd = new PostDAO();
        pd.insertPost("https://149357281.v2.pressablecdn.com/wp-content/uploads/2022/06/Northeastern-University-MS-in-Management-Digital-Transformation-in-Healthcare-with-Mayo-Clinic-social-blog-3.png", "test", "ok", 0, 3);
    }
}
