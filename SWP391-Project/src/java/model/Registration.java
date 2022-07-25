/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Registration {

    int rid;
    String rtime;
    String rnote;
    String cid;
    int uid;
    int rstatusId;
    int pid;
    String lastUpBy;

    public Registration() {
    }

    public Registration(String rtime, String rnote, String cid, int uid, int rstatusId, int pid, String lastUpBy) {
        this.rtime = rtime;
        this.rnote = rnote;
        this.cid = cid;
        this.uid = uid;
        this.rstatusId = rstatusId;
        this.pid = pid;
        this.lastUpBy = lastUpBy;
    }

    public Registration(int rid, String rtime, String rnote, String cid, int uid, int rstatusId, int pid) {
        this.rid = rid;
        this.rtime = rtime;
        this.rnote = rnote;
        this.cid = cid;
        this.uid = uid;
        this.rstatusId = rstatusId;
        this.pid = pid;
    }

    public Registration(int rid, String rtime, String rnote, String cid, int uid, int rstatusId, int pid, String lastUpBy) {
        this.rid = rid;
        this.rtime = rtime;
        this.rnote = rnote;
        this.cid = cid;
        this.uid = uid;
        this.rstatusId = rstatusId;
        this.pid = pid;
        this.lastUpBy = lastUpBy;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRtime() {
        return rtime;
    }

    public void setRtime(String rtime) {
        this.rtime = rtime;
    }

    public String getRnote() {
        return rnote;
    }

    public void setRnote(String rnote) {
        this.rnote = rnote;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getRstatusId() {
        return rstatusId;
    }

    public void setRstatusId(int rstatusId) {
        this.rstatusId = rstatusId;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getLastUpBy() {
        return lastUpBy;
    }

    public void setLastUpBy(String lastUpBy) {
        this.lastUpBy = lastUpBy;
    }

}
