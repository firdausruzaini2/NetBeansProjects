package course;

public class Detail {

    private final String iD, name, credit;
    private String synopsis, school, course;

    Detail(String iD, String name, String synopsis, String course, String credit, String school) {
        this.iD = iD;
        this.name = name;
        this.synopsis = synopsis;
        this.course = course;
        this.credit = credit;
        this.school = school;
    }

    String getID() {
        return iD;
    }

    String getName() {
        return name;
    }

    String getSynopsis() {
        return synopsis;
    }

    String getCourse() {
        return course;
    }
    
    String getCredit() {
        return credit;
    }
    
    String getSchool(){
        return school;
    }

    void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
    
    void setCourse(String course) {
        this.course = course;
    } 
    
    void setSchool(String school) {
        this.school = school;
    }

}
