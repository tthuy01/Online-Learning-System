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
public class QuestionMix {
    
    int quesid;
    String quescontext;
    int quesresult;
    String quesnote;
    boolean quesstatus;
    boolean quesflag;
    int quesanswer;
    int qid;
    int quesrid;
    int qrid;

    public QuestionMix() {
    }

    public QuestionMix(int quesid, String quescontext, int quesresult, String quesnote, boolean quesstatus, boolean quesflag, int quesanswer, int qid, int quesrid, int qrid) {
        this.quesid = quesid;
        this.quescontext = quescontext;
        this.quesresult = quesresult;
        this.quesnote = quesnote;
        this.quesstatus = quesstatus;
        this.quesflag = quesflag;
        this.quesanswer = quesanswer;
        this.qid = qid;
        this.quesrid = quesrid;
        this.qrid = qrid;
    }

    public int getQuesid() {
        return quesid;
    }

    public void setQuesid(int quesid) {
        this.quesid = quesid;
    }

    public String getQuescontext() {
        return quescontext;
    }

    public void setQuescontext(String quescontext) {
        this.quescontext = quescontext;
    }

    public int getQuesresult() {
        return quesresult;
    }

    public void setQuesresult(int quesresult) {
        this.quesresult = quesresult;
    }

    public String getQuesnote() {
        return quesnote;
    }

    public void setQuesnote(String quesnote) {
        this.quesnote = quesnote;
    }

    public boolean isQuesstatus() {
        return quesstatus;
    }

    public void setQuesstatus(boolean quesstatus) {
        this.quesstatus = quesstatus;
    }

    public boolean isQuesflag() {
        return quesflag;
    }

    public void setQuesflag(boolean quesflag) {
        this.quesflag = quesflag;
    }

    public int getQuesanswer() {
        return quesanswer;
    }

    public void setQuesanswer(int quesanswer) {
        this.quesanswer = quesanswer;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public int getQuesrid() {
        return quesrid;
    }

    public void setQuesrid(int quesrid) {
        this.quesrid = quesrid;
    }

    public int getQrid() {
        return qrid;
    }

    public void setQrid(int qrid) {
        this.qrid = qrid;
    }
    
}
