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
public class SubCateProfit {

    int subjectCateId;
    String subjectCateName;
    double subjectCateProfit;

    public SubCateProfit() {
    }

    public SubCateProfit(int subjectCateId, String subjectCateName, double subjectCateProfit) {
        this.subjectCateId = subjectCateId;
        this.subjectCateName = subjectCateName;
        this.subjectCateProfit = subjectCateProfit;
    }

    public int getSubjectCateId() {
        return subjectCateId;
    }

    public void setSubjectCateId(int subjectCateId) {
        this.subjectCateId = subjectCateId;
    }

    public String getSubjectCateName() {
        return subjectCateName;
    }

    public void setSubjectCateName(String subjectCateName) {
        this.subjectCateName = subjectCateName;
    }

    public double getSubjectCateProfit() {
        return subjectCateProfit;
    }

    public void setSubjectCateProfit(double subjectCateProfit) {
        this.subjectCateProfit = subjectCateProfit;
    }
    
}
