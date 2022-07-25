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
public class QuizMix {
    
    String cid;
    String cname;
    int qid;
    String qname;
    String qdesc;
    int qrid;
    double qgrade;
    String qstart;
    String qend;
    boolean qstatus;

    public QuizMix() {
    }

    public QuizMix(String cid, String cname, int qid, String qname, String qdesc, int qrid, double qgrade, String qstart, String qend, boolean qstatus) {
        this.cid = cid;
        this.cname = cname;
        this.qid = qid;
        this.qname = qname;
        this.qdesc = qdesc;
        this.qrid = qrid;
        this.qgrade = qgrade;
        this.qstart = qstart;
        this.qend = qend;
        this.qstatus = qstatus;
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

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public String getQdesc() {
        return qdesc;
    }

    public void setQdesc(String qdesc) {
        this.qdesc = qdesc;
    }

    public int getQrid() {
        return qrid;
    }

    public void setQrid(int qrid) {
        this.qrid = qrid;
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

    public boolean isQstatus() {
        return qstatus;
    }

    public void setQstatus(boolean qstatus) {
        this.qstatus = qstatus;
    }

    
}
