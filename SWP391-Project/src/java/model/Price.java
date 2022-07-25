/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class Price {
    int packid;
    String packname;
    int duration;
    boolean packstatus;
    float multiple;
    String description;

    public Price() {
    }

    public Price(int packid, String packname, int duration, boolean packstatus) {
        this.packid = packid;
        this.packname = packname;
        this.duration = duration;
        this.packstatus = packstatus;
    }
    
    public Price(int packid, String packname, int duration, boolean packstatus, float multiple, String description) {
        this.packid = packid;
        this.packname = packname;
        this.duration = duration;
        this.packstatus = packstatus;
        this.multiple = multiple;
        this.description = description;
    }

    public int getPackid() {
        return packid;
    }

    public void setPackid(int packid) {
        this.packid = packid;
    }

    public String getPackname() {
        return packname;
    }

    public void setPackname(String packname) {
        this.packname = packname;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isPackstatus() {
        return packstatus;
    }

    public void setPackstatus(boolean packstatus) {
        this.packstatus = packstatus;
    }
    
    public float getMultiple() {
        return multiple;
    }

    public void setMultiple(float multiple) {
        this.multiple = multiple;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static void main(String[] args) {
        Calendar calendar = new Calendar.Builder()
                .setDate(2022, Calendar.JUNE, 1)
                .setTimeOfDay(0, 0, 0)
                .build();

System.out.println(new Date().getTime());
    }
}
