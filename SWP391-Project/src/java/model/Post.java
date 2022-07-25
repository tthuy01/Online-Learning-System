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
public class Post {

    int poid;
    String poimg;
    String potitle;
    String podesc;
    String podate;
    Boolean postatus;
    int bid;

    public Post() {
    }

    public Post(int poid, String poimg, String potitle, String podesc, String podate, Boolean postatus, int bid) {
        this.poid = poid;
        this.poimg = poimg;
        this.potitle = potitle;
        this.podesc = podesc;
        this.podate = podate;
        this.postatus = postatus;
        this.bid = bid;
    }

    public int getPoid() {
        return poid;
    }

    public void setPoid(int poid) {
        this.poid = poid;
    }

    public String getPoimg() {
        return poimg;
    }

    public void setPoimg(String poimg) {
        this.poimg = poimg;
    }

    public String getPotitle() {
        return potitle;
    }

    public void setPotitle(String potitle) {
        this.potitle = potitle;
    }

    public String getPodesc() {
        return podesc;
    }

    public void setPodesc(String podesc) {
        this.podesc = podesc;
    }

    public String getPodate() {
        return podate;
    }

    public void setPodate(String podate) {
        this.podate = podate;
    }

    public Boolean getPostatus() {
        return postatus;
    }

    public void setPostatus(Boolean postatus) {
        this.postatus = postatus;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

}
