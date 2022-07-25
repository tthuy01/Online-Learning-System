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
public class Subject {
    int sid;
    String sname;
    String simg;
    String sdesc;
    int scate;

    public Subject() {
    }

    public Subject(int sid, String sname, String simg, String sdesc, int scate) {
        this.sid = sid;
        this.sname = sname;
        this.simg = simg;
        this.sdesc = sdesc;
        this.scate = scate;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSimg() {
        return simg;
    }

    public void setSimg(String simg) {
        this.simg = simg;
    }

    public String getSdesc() {
        return sdesc;
    }

    public void setSdesc(String sdesc) {
        this.sdesc = sdesc;
    }

    public int getScate() {
        return scate;
    }

    public void setScate(int scate) {
        this.scate = scate;
    }

    


}
