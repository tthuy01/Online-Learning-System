/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class ManageCourse {
    private String cid;
    private int uid;
    private String feature;

    public ManageCourse() {
    }

    public ManageCourse(String cid, int uid, String feature) {
        this.cid = cid;
        this.uid = uid;
        this.feature = feature;
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

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
    
    
    
}
