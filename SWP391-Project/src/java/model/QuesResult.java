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
public class QuesResult {

    int quesResultId;
    int quesid;
    int uid;
    boolean qstatus;
    boolean qflag;
    int qanswer;
    int qrid;

    public QuesResult() {
    }

    public QuesResult(int quesResultId, int quesid, int uid, boolean qstatus, boolean qflag, int qanswer, int qrid) {
        this.quesResultId = quesResultId;
        this.quesid = quesid;
        this.uid = uid;
        this.qstatus = qstatus;
        this.qflag = qflag;
        this.qanswer = qanswer;
        this.qrid = qrid;
    }

    public int getQuesResultId() {
        return quesResultId;
    }

    public void setQuesResultId(int quesResultId) {
        this.quesResultId = quesResultId;
    }

    public int getQuesid() {
        return quesid;
    }

    public void setQuesid(int quesid) {
        this.quesid = quesid;
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

    public boolean isQflag() {
        return qflag;
    }

    public void setQflag(boolean qflag) {
        this.qflag = qflag;
    }

    public int getQanswer() {
        return qanswer;
    }

    public void setQanswer(int qanswer) {
        this.qanswer = qanswer;
    }

    public int getQrid() {
        return qrid;
    }

    public void setQrid(int qrid) {
        this.qrid = qrid;
    }

}
