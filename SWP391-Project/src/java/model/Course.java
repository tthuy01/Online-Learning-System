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
public class Course {

    String cid, cname, ctitle, cimg;
    double cprice;
    String cdesc, cstart, cstop, cpublic;
    int sid, lecid, levid;
    boolean cstatus;

    public Course() {
    }

    public Course(String cid, String cname, String ctitle, String cimg, double cprice, String cdesc, String cstart, String cstop, String cpublic, int sid, int lecid, int levid, boolean cstatus) {
        this.cid = cid;
        this.cname = cname;
        this.ctitle = ctitle;
        this.cimg = cimg;
        this.cprice = cprice;
        this.cdesc = cdesc;
        this.cstart = cstart;
        this.cstop = cstop;
        this.cpublic = cpublic;
        this.sid = sid;
        this.lecid = lecid;
        this.levid = levid;
        this.cstatus = cstatus;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCtitle() {
        return ctitle;
    }

    public void setCtitle(String ctitle) {
        this.ctitle = ctitle;
    }

    public String getCimg() {
        return cimg;
    }

    public void setCimg(String cimg) {
        this.cimg = cimg;
    }

    public double getCprice() {
        return cprice;
    }

    public void setCprice(double cprice) {
        this.cprice = cprice;
    }

    public String getCdesc() {
        return cdesc;
    }

    public void setCdesc(String cdesc) {
        this.cdesc = cdesc;
    }

    public String getCstart() {
        return cstart;
    }

    public void setCstart(String cstart) {
        this.cstart = cstart;
    }

    public String getCstop() {
        return cstop;
    }

    public void setCstop(String cstop) {
        this.cstop = cstop;
    }

    public String getCpublic() {
        return cpublic;
    }

    public void setCpublic(String cpublic) {
        this.cpublic = cpublic;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getLecid() {
        return lecid;
    }

    public void setLecid(int lecid) {
        this.lecid = lecid;
    }

    public int getLevid() {
        return levid;
    }

    public void setLevid(int levid) {
        this.levid = levid;
    }

    public boolean isCstatus() {
        return cstatus;
    }

    public void setCstatus(boolean cstatus) {
        this.cstatus = cstatus;
    }

   

   
}
