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
public class QuizList {
    
    int qid, tid, sid, subid, quesnum, uid, qexist;
    String qname, qdesc, cid, cname, ctitle, cdesc, qstart, qend, subname, sname;
    boolean cstatus, qstatus;
    double pasrat;

    public QuizList() {
    }

    public QuizList(int qid, int tid, int sid, int subid, int quesnum, int uid, int qexist, String qname, String qdesc, String cid, String cname, String ctitle, String cdesc, String qstart, String qend, String subname, String sname, boolean cstatus, boolean qstatus, double pasrat) {
        this.qid = qid;
        this.tid = tid;
        this.sid = sid;
        this.subid = subid;
        this.quesnum = quesnum;
        this.uid = uid;
        this.qexist = qexist;
        this.qname = qname;
        this.qdesc = qdesc;
        this.cid = cid;
        this.cname = cname;
        this.ctitle = ctitle;
        this.cdesc = cdesc;
        this.qstart = qstart;
        this.qend = qend;
        this.subname = subname;
        this.sname = sname;
        this.cstatus = cstatus;
        this.qstatus = qstatus;
        this.pasrat = pasrat;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getSubid() {
        return subid;
    }

    public void setSubid(int subid) {
        this.subid = subid;
    }

    public int getQuesnum() {
        return quesnum;
    }

    public void setQuesnum(int quesnum) {
        this.quesnum = quesnum;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getQexist() {
        return qexist;
    }

    public void setQexist(int qexist) {
        this.qexist = qexist;
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

    public String getCdesc() {
        return cdesc;
    }

    public void setCdesc(String cdesc) {
        this.cdesc = cdesc;
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

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public boolean isCstatus() {
        return cstatus;
    }

    public void setCstatus(boolean cstatus) {
        this.cstatus = cstatus;
    }

    public boolean isQstatus() {
        return qstatus;
    }

    public void setQstatus(boolean qstatus) {
        this.qstatus = qstatus;
    }

    public double getPasrat() {
        return pasrat;
    }

    public void setPasrat(double pasrat) {
        this.pasrat = pasrat;
    }
    
}
