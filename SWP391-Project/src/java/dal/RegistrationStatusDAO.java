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
import model.RegistrationStatus;

public class RegistrationStatusDAO extends DBContext{
    public List<RegistrationStatus> getAll() {
        List<RegistrationStatus> list = new ArrayList<>();
        String sql = "select * from Registration_Status";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                list.add(new RegistrationStatus(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
     public RegistrationStatus getRegStatusByRsid(int rsid)
    {
        String sql = "select * from Registration_Status where reg_status_id = "+rsid;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                RegistrationStatus r = new RegistrationStatus(rs.getInt(1), rs.getString(2));
                return r;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public static void main(String[] args) {
//        RegistrationStatusDAO rsdao = new RegistrationStatusDAO();
//        System.out.println(rsdao.getAll().size());
    }
}
