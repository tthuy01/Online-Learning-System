/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class TimeCourse {
    int timecid;
    String cid;
    int uid;
    String course_start, course_stop;

    public TimeCourse() {
    }

    public TimeCourse(int timecid, String cid, int uid, String course_start, String course_stop) {
        this.timecid = timecid;
        this.cid = cid;
        this.uid = uid;
        this.course_start = course_start;
        this.course_stop = course_stop;
    }

    public int getTimecid() {
        return timecid;
    }

    public void setTimecid(int timecid) {
        this.timecid = timecid;
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

    public String getCourse_start() {
        return course_start;
    }

    public void setCourse_start(String course_start) {
        this.course_start = course_start;
    }

    public String getCourse_stop() {
        return course_stop;
    }

    public void setCourse_stop(String course_stop) {
        this.course_stop = course_stop;
    }
    
}
