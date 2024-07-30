package bean;

public class ClassNum implements java.io.Serializable {
    private School school;
    private String classNum;

    public School getSchool() {
        return school;
    }
    public void setSchool(School school){
    	this.school=school;
    }
    public String getClassNum(){
    	return classNum;
    }
    public void setClassNum(String classNum){
    	this.classNum=classNum;
    }
}