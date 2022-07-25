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
import model.Slider;

/**
 *
 * @author Admin
 */
public class SliderDAO extends DBContext {

    public List<Slider> getAll() {
        List<Slider> list = new ArrayList<>();
        String sql = "select * from Slider where slider_status=1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Slider(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5), rs.getString(6)));
            }
        } catch (Exception e) {
        }

        return list;
    }

    public Slider getSliderById(String id) {
        String sql = "select * from Slider where slider_id=" + id;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Slider(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5), rs.getString(6));
            }
        } catch (Exception e) {
        }

        return null;
    }

    public List<Slider> getSlidersByFilter(String search, String status, int index, int pageSize) {
        List<Slider> list = new ArrayList<>();
        String sql = "select * from Slider where (slider_title like '%" + search + "%' or slider_link like '%" + search + "%')";
        switch (status) {
            case "1":
                sql += " and slider_status=1";
                break;
            case "0":
                sql += " and slider_status=0";
                break;
            default:
        }
        sql += " order by slider_id offset " + (index - 1) * pageSize + " rows fetch next " + pageSize + " rows only";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Slider(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5), rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int getNumSlidersByFilter(String search, String status) {
        String sql = "select count(*) from Slider where (slider_title like '%" + search + "%' or slider_link like '%" + search + "%')";
        switch (status) {
            case "1":
                sql += " and slider_status=1";
                break;
            case "0":
                sql += " and slider_status=0";
                break;
            default:
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

    public void updateStatusById(String id) {
        Slider s = getSliderById(id);
        String status = s.isStatus() ? "0" : "1";
        String sql = "update Slider set slider_status = " + status + " where slider_id = " + id;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateSliderById(Slider s) {
        String status = s.isStatus() ? "1" : "0";
        String sql = "update Slider set slider_title = '" + s.getTitle() + "'"
                + ", slider_img = '" + s.getImage() + "'"
                + ", slider_link = '" + s.getLink() + "'"
                + ", slider_status = " + status
                + ", slider_note = '" + s.getNote() + "'"
                + " where slider_id = " + s.getId();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        SliderDAO sd = new SliderDAO();
        Slider s = sd.getSliderById("1");
        sd.updateSliderById(new Slider(1, s.getTitle(), s.getImage(), s.getLink(), true, s.getNote()));
    }
}
