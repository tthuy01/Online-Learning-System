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
public class Lecturer {
    int lid;
    String lname;

    public Lecturer() {
    }

    public Lecturer(int lid, String lname) {
        this.lid = lid;
        this.lname = lname;
    }

    
    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

   
    
}
