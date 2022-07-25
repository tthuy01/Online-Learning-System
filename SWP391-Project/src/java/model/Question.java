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
public class Question {
    int quesid;
    String qcontent;
    int qresult;
    String qnote;
    int qid;

    public Question() {
    }

    public Question(int quesid, String qcontent, int qresult, String qnote, int qid) {
        this.quesid = quesid;
        this.qcontent = qcontent;
        this.qresult = qresult;
        this.qnote = qnote;
        this.qid = qid;
    }

    public int getQuesid() {
        return quesid;
    }

    public void setQuesid(int quesid) {
        this.quesid = quesid;
    }

    public String getQcontent() {
        return qcontent;
    }

    public void setQcontent(String qcontent) {
        this.qcontent = qcontent;
    }

    public int getQresult() {
        return qresult;
    }

    public void setQresult(int qresult) {
        this.qresult = qresult;
    }

    public String getQnote() {
        return qnote;
    }

    public void setQnote(String qnote) {
        this.qnote = qnote;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    
}
