/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Tran Thi Thanh Thuy
 */
public class RegistrationStatus {
    int rstatusId;
    String rstatusName;

    public RegistrationStatus() {
    }

    public RegistrationStatus(int rstatusId, String rstatusName) {
        this.rstatusId = rstatusId;
        this.rstatusName = rstatusName;
    }

    public int getRstatusId() {
        return rstatusId;
    }

    public void setRstatusId(int rstatusId) {
        this.rstatusId = rstatusId;
    }

    public String getRstatusName() {
        return rstatusName;
    }

    public void setRstatusName(String rstatusName) {
        this.rstatusName = rstatusName;
    }
    
}
