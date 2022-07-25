/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Lesson {
    int lid;
    String lname;
    String lvideo;
    String cid;
    int sid;
    int tid;
    String ldesc;
    boolean lstatus;

    public Lesson() {
    }

    public Lesson(int lid, String lname, String lvideo, String cid, int sid, int tid, String ldesc) {
        this.lid = lid;
        this.lname = lname;
        this.lvideo = lvideo;
        this.cid = cid;
        this.sid = sid;
        this.tid = tid;
        this.ldesc = ldesc;
    }

    public Lesson(int lid, String lname, String lvideo, String cid, int sid, int tid, String ldesc, boolean lstatus) {
        this.lid = lid;
        this.lname = lname;
        this.lvideo = lvideo;
        this.cid = cid;
        this.sid = sid;
        this.tid = tid;
        this.ldesc = ldesc;
        this.lstatus = lstatus;
    }
    

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLvideo() {
        return lvideo;
    }

    public void setLvideo(String lvideo) {
        this.lvideo = lvideo;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getLdesc() {
        return ldesc;
    }

    public void setLdesc(String ldesc) {
        this.ldesc = ldesc;
    }

    public boolean isLstatus() {
        return lstatus;
    }

    public void setLstatus(boolean lstatus) {
        this.lstatus = lstatus;
    }

    
    
}
