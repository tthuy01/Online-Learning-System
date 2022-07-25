package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Setting;
import model.SettingStatus;
import model.SettingType;

public class SettingDAO extends DBContext {

    public int countTotalOfSetting(String search, String[] typeId, String[] statusId) {
        int count = 0;
        String sql = "select COUNT(*) from Setting\n"
                + "where 1=1 ";
        if (search != null && !search.trim().equals("")) {
            sql += " and (SettingOrder like '%" + search + "%' or SettingValue like '%" + search + "%') ";
        }
        if (typeId != null) {
            sql += " and SettingTypeId in (";
            for (int i = 0; i < typeId.length; i++) {
                sql += "" + typeId[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += ")";
        }
        if (statusId != null) {
            sql += " and SettingStatusId in (";
            for (int i = 0; i < statusId.length; i++) {
                sql += "" + statusId[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += ")";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return count;
    }

    public List<Setting> getSetting(String search, String sortBy, String[] typeId, String[] statusId, int page) {
        List<Setting> list = new ArrayList<>();
        String sql = "select s.SettingId, s.SettingTypeId, s.SettingValue, s.SettingOrder, s.SettingStatusId, s.SettingDescription from Setting s\n"
                + "join SettingType st on s.SettingTypeId = st.SettingTypeId\n"
                + "join SettingStatus ss on s.SettingStatusId = ss.SettingStatusId\n"
                + "where 1=1 ";
        if (search != null && !search.trim().equals("")) {
            sql += " and (SettingOrder like '%" + search + "%' or SettingValue like '%" + search + "%') ";
        }
        if (typeId != null) {
            sql += " and s.SettingTypeId in (";
            for (int i = 0; i < typeId.length; i++) {
                sql += "" + typeId[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += ")";
        }
        if (statusId != null) {
            sql += " and s.SettingStatusId in (";
            for (int i = 0; i < statusId.length; i++) {
                sql += "" + statusId[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += ")";
        }
        if (sortBy != null && !sortBy.trim().equals("")) {
            if (sortBy.toLowerCase().equals("id")) {
                sql += " order by s.SettingId desc";
            }
            if (sortBy.toLowerCase().equals("type".toLowerCase())) {
                sql += " order by st.SettingTypeName desc";
            }
            if (sortBy.toLowerCase().equals("value")) {
                sql += " order by s.SettingValue desc";
            }
            if (sortBy.toLowerCase().equals("order")) {
                sql += " order by s.SettingOrder desc";
            }
            if (sortBy.toLowerCase().equals("status")) {
                sql += " order by ss.SettingStatusName desc";
            }
        }
        if (sortBy != null && sortBy.trim().equals("")) {
            sql += " order by s.SettingId";
        }
        sql += " offset " + ((page - 1) * 4) + " rows\n"
                + "fetch next 4 rows only;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Setting(rs.getInt("SettingId"), rs.getInt("SettingTypeId"), rs.getString("SettingValue"),
                        rs.getString("SettingOrder"), rs.getInt("SettingStatusId"), rs.getString("SettingDescription")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public SettingType getSettingTypeBySettingId(int sid) {
        SettingType stype = new SettingType();
        String sql = "select st.SettingTypeId, st.SettingTypeName from SettingType st \n"
                + "join Setting s on st.SettingTypeId = s.SettingTypeId\n"
                + "where s.SettingId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, sid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                stype.setSettingTypeId(rs.getInt("SettingTypeId"));
                stype.setSettingTypeName(rs.getString("SettingTypeName"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return stype;
    }

    public List<SettingType> getListSettingTypeBySettingList(List<Setting> list) {
        List<SettingType> listSettingType = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            listSettingType.add(getSettingTypeBySettingId(list.get(i).getSettingId()));
        }
        return listSettingType;
    }
    
    public SettingStatus getSettingStatusBySettingId(int sid) {
        SettingStatus sstatus = new SettingStatus();
        String sql = "select ss.SettingStatusId, ss.SettingStatusName from SettingStatus ss\n" +
                        "join Setting s on ss.SettingStatusId = s.SettingStatusId\n" +
                        "where s.SettingId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, sid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                sstatus.setSettingStatusId(rs.getInt("SettingStatusId"));
                sstatus.setSettingStatusName(rs.getString("SettingStatusName"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return sstatus;
    }
    
    public List<SettingStatus> getListSettingStatusBySettingList(List<Setting> list) {
        List<SettingStatus> listSettingStatus = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            listSettingStatus.add(getSettingStatusBySettingId(list.get(i).getSettingId()));
        }
        return listSettingStatus;
    }

    public List<SettingType> getAllSettingType() {
        List<SettingType> list = new ArrayList<>();
        String sql = "select * from SettingType";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new SettingType(rs.getInt("SettingTypeId"), rs.getString("SettingTypeName")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<SettingStatus> getAllSettingStatus() {
        List<SettingStatus> list = new ArrayList<>();
        String sql = "select * from SettingStatus";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new SettingStatus(rs.getInt("SettingStatusId"), rs.getString("SettingStatusName")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void changeStatus(int settingId, int settingStatusId)
    {
        String sql = "update Setting set SettingStatusId = ? where SettingId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, settingStatusId);
            st.setInt(2, settingId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void addNewSetting(Setting s)
    {
        String sql = "insert into Setting values (?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, s.getSettingTypeId());
            st.setString(2, s.getSettingValue());
            st.setString(3, s.getSettingOrder());
            st.setInt(4, s.getSettingStatusId());
            st.setString(5, s.getSettingDescription());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void updateSetting(Setting s)
    {
        String sql = "update Setting set SettingTypeId = ?, SettingValue = ?, SettingOrder = ?, "
                + "SettingStatusId = ?, SettingDescription = ? where SettingId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, s.getSettingTypeId());
            st.setString(2, s.getSettingValue());
            st.setString(3, s.getSettingOrder());
            st.setInt(4, s.getSettingStatusId());
            st.setString(5, s.getSettingDescription());
            st.setInt(6, s.getSettingId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public Setting getSettingById(int id)
    {
        String sql = "select * from Setting where SettingId = ?" ;
        Setting s = new Setting();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                s.setSettingId(id);
                s.setSettingTypeId(rs.getInt("SettingTypeId"));
                s.setSettingValue(rs.getString("SettingValue"));
                s.setSettingOrder(rs.getString("SettingOrder"));
                s.setSettingStatusId(rs.getInt("SettingStatusId"));
                s.setSettingDescription(rs.getString("SettingDescription"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return s;
    }

    public static void main(String[] args) {
        SettingDAO sdao = new SettingDAO();
//        String[] ar = {"2"};
////        System.out.println(sdao.countTotalOfSetting("", ar, null));
//        List<Setting> list = sdao.getSetting("", "", null, null, 1);
//        System.out.println(list.size());
//        System.out.println(sdao.getAllSettingStatus().get(0).getSettingStatusName());
//        System.out.println(sdao.getAllSettingType().get(0).getSettingTypeName());
//        System.out.println(sdao.getListSettingTypeBySettingList(list).size());
//        sdao.changeStatus(1, 1);
        Setting s = new Setting(8, 2, "Customer", "Customer role", 2, "Update");
        sdao.updateSetting(s);
    }
}
