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
public class Section {
    int sid;
    String cid;
    String sname;
    int tid;
    boolean sstatus;
    
    public Section() {
    }

    public Section(int sid, String cid, String sname, int tid) {
        this.sid = sid;
        this.cid = cid;
        this.sname = sname;
        this.tid = tid;
    }

    public Section(int sid, String cid, String sname, int tid, boolean sstatus) {
        this.sid = sid;
        this.cid = cid;
        this.sname = sname;
        this.tid = tid;
        this.sstatus = sstatus;
    }
    

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public boolean isSstatus() {
        return sstatus;
    }

    public void setSstatus(boolean sstatus) {
        this.sstatus = sstatus;
    }
    

    
    
    
    
}
