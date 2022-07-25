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
public class QuizResult {
    int qrid;
    int qid;
    int uid;
    boolean qstatus;
    double qgrade;
    String qstart;
    String qend;

    public QuizResult() {
    }

    public QuizResult(int qrid, int qid, int uid, boolean qstatus, double qgrade, String qstart, String qend) {
        this.qrid = qrid;
        this.qid = qid;
        this.uid = uid;
        this.qstatus = qstatus;
        this.qgrade = qgrade;
        this.qstart = qstart;
        this.qend = qend;
    }

    public int getQrid() {
        return qrid;
    }

    public void setQrid(int qrid) {
        this.qrid = qrid;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public boolean isQstatus() {
        return qstatus;
    }

    public void setQstatus(boolean qstatus) {
        this.qstatus = qstatus;
    }

    public double getQgrade() {
        return qgrade;
    }

    public void setQgrade(double qgrade) {
        this.qgrade = qgrade;
    }

    public String getQstart() {
        return qstart;
    }

    public void setQstart(String qstart) {
        this.qstart = qstart;
    }

    public String getQend() {
        return qend;
    }

    public void setQend(String qend) {
        this.qend = qend;
    }
    
    
}
