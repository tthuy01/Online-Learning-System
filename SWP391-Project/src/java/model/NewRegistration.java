/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class NewRegistration {
    
    String uimg;
    String ufullname;
    String cname;
    String rtime;
    String regStatus;
    String packname;

    public NewRegistration() {
    }

    public NewRegistration(String uimg, String ufullname, String cname, String rtime, String regStatus, String packname) {
        this.uimg = uimg;
        this.ufullname = ufullname;
        this.cname = cname;
        this.rtime = rtime;
        this.regStatus = regStatus;
        this.packname = packname;
    }

    public String getUimg() {
        return uimg;
    }

    public void setUimg(String uimg) {
        this.uimg = uimg;
    }

    public String getUfullname() {
        return ufullname;
    }

    public void setUfullname(String ufullname) {
        this.ufullname = ufullname;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getRtime() {
        return rtime;
    }

    public void setRtime(String rtime) {
        this.rtime = rtime;
    }

    public String getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(String regStatus) {
        this.regStatus = regStatus;
    }

    public String getPackname() {
        return packname;
    }

    public void setPackname(String packname) {
        this.packname = packname;
    }

    

}
