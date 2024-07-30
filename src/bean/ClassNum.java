package bean;

public class ClassNum implements java.io.Serializable {
    // Schoolクラスのインスタンスを保持するフィールド
    private School school;
    // クラス番号を保持するフィールド
    private String classNum;

    // schoolフィールドのゲッターメソッド
    public School getSchool() {
        return school;
    }

    // schoolフィールドのセッターメソッド
    public void setSchool(School school) {
        this.school = school;
    }

    // classNumフィールドのゲッターメソッド
    public String getClassNum() {
        return classNum;
    }

    // classNumフィールドのセッターメソッド
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }
}
