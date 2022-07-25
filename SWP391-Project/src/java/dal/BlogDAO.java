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
import model.Blog;
import model.Post;

/**
 *
 * @author ADMIN
 */
public class BlogDAO extends DBContext {

    public Blog getBlogByID(int bid) {
        String sql = "select * from Blog where blog_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, bid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Blog(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Blog> getBlog() {
        String sql = "select * from Blog";
        List<Blog> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Blog b = new Blog();
                b.setBid(rs.getInt(1));
                b.setBname(rs.getString(2));
                b.setBtitle(rs.getString(3));
                b.setBupdateDate(rs.getString(4));
                list.add(b);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Post> getListByPage(List<Post> list,
            int start, int end) {
        ArrayList<Post> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

}
