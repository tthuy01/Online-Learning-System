package model;

public class SubjectCategory {
    int subjectCateId;
    String subjectCateName;

    public SubjectCategory() {
    }

    public SubjectCategory(int subjectCateId, String subjectCateName) {
        this.subjectCateId = subjectCateId;
        this.subjectCateName = subjectCateName;
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
    
}
