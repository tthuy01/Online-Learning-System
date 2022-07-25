/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Dell
 */
public class User {

    int uid;
    String uemail;
    String upassword;
    String ufullname;
    String uimg;
    int gid;
    String udob;
    String uphone;
    String uaddress;
    String uwallet;
    int rid;
    String utime;
    boolean ustatus;

    public User() {
    }

    public User(String uemail, String upassword, String ufullname, int gid, int rid) {
        this.uemail = uemail;
        this.upassword = upassword;
        this.ufullname = ufullname;
        this.gid = gid;
        this.rid = rid;

    }

    public User(int uid, String uemail, String upassword, String ufullname, String uimg, int gid, String udob, String uphone, String uaddress, String uwallet) {
        this.uid = uid;
        this.uemail = uemail;
        this.upassword = upassword;
        this.ufullname = ufullname;
        this.uimg = uimg;
        this.udob = udob;
        this.uphone = uphone;
        this.uaddress = uaddress;
        this.uwallet = uwallet;
        this.gid = gid;
    }

    public User(int uid, String uemail, String ufullname, String uimg, int gid, String udob, String uphone, String uaddress, String uwallet, int rid) {
        this.uid = uid;
        this.uemail = uemail;
        this.ufullname = ufullname;
        this.uimg = uimg;
        this.gid = gid;
        this.udob = udob;
        this.uphone = uphone;
        this.uaddress = uaddress;
        this.uwallet = uwallet;
        this.rid = rid;
    }

    public User(int uid, String uemail, String upassword, String ufullname, String uimg, int gid, String udob, String uphone, String uaddress, String uwallet, int rid) {
        this.uid = uid;
        this.uemail = uemail;
        this.upassword = upassword;
        this.ufullname = ufullname;
        this.uimg = uimg;
        this.gid = gid;
        this.udob = udob;
        this.uphone = uphone;
        this.uaddress = uaddress;
        this.uwallet = uwallet;
        this.rid = rid;
    }

    public User(int uid, String uemail, String upassword, String ufullname, String uimg, int gid, String udob, String uphone, String uaddress, String uwallet, int rid, String utime) {
        this.uid = uid;
        this.uemail = uemail;
        this.upassword = upassword;
        this.ufullname = ufullname;
        this.uimg = uimg;
        this.gid = gid;
        this.udob = udob;
        this.uphone = uphone;
        this.uaddress = uaddress;
        this.uwallet = uwallet;
        this.rid = rid;
        this.utime = utime;
    }
    
    public User(int uid, String uemail, String upassword, String ufullname, String uimg, int gid, String udob, String uphone, String uaddress, String uwallet, int rid, boolean ustatus) {
        this.uid = uid;
        this.uemail = uemail;
        this.upassword = upassword;
        this.ufullname = ufullname;
        this.uimg = uimg;
        this.gid = gid;
        this.udob = udob;
        this.uphone = uphone;
        this.uaddress = uaddress;
        this.uwallet = uwallet;
        this.rid = rid;
        this.ustatus = ustatus;
    }

    public User(int uid, String uemail, String upassword, String ufullname, String uimg, int gid, String udob, String uphone, String uaddress, String uwallet, int rid, String utime, boolean ustatus) {
        this.uid = uid;
        this.uemail = uemail;
        this.upassword = upassword;
        this.ufullname = ufullname;
        this.uimg = uimg;
        this.gid = gid;
        this.udob = udob;
        this.uphone = uphone;
        this.uaddress = uaddress;
        this.uwallet = uwallet;
        this.rid = rid;
        this.utime = utime;
        this.ustatus = ustatus;
    }
    

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUfullname() {
        return ufullname;
    }

    public void setUfullname(String ufullname) {
        this.ufullname = ufullname;
    }

    public String getUimg() {
        return uimg;
    }

    public void setUimg(String uimg) {
        this.uimg = uimg;
    }

    public String getUdob() {
        return udob;
    }

    public void setUdob(String udob) {
        this.udob = udob;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUaddress() {
        return uaddress;
    }

    public void setUaddress(String uaddress) {
        this.uaddress = uaddress;
    }

    public String getUwallet() {
        return uwallet;
    }

    public void setUwallet(String uwallet) {
        this.uwallet = uwallet;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getUtime() {
        return utime;
    }

    public void setUtime(String utime) {
        this.utime = utime;
    }

    public boolean isUstatus() {
        return ustatus;
    }

    public void setUstatus(boolean ustatus) {
        this.ustatus = ustatus;
    }

}
