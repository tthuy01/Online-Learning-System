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
public class LessonResult {
    
    int lid;
    int uid;
    boolean lstatus;

    public LessonResult() {
    }

    public LessonResult(int lid, int uid, boolean lstatus) {
        this.lid = lid;
        this.uid = uid;
        this.lstatus = lstatus;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public boolean isLstatus() {
        return lstatus;
    }

    public void setLstatus(boolean lstatus) {
        this.lstatus = lstatus;
    }
    
    
}
