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
public class Blog {

    int bid;
    String bname;
    String btitle;
    String bupdateDate;

    public Blog() {
    }

    public Blog(int bid, String bname, String btitle, String bupdateDate) {
        this.bid = bid;
        this.bname = bname;
        this.btitle = btitle;
        this.bupdateDate = bupdateDate;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBtitle() {
        return btitle;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }

    public String getBupdateDate() {
        return bupdateDate;
    }

    public void setBupdateDate(String bupdateDate) {
        this.bupdateDate = bupdateDate;
    }
    public static void main(String[] args) {
        String s="0|0|0|1|";
        String a[]=s.split("[|]");
        System.out.println(a.length);
    }

}
