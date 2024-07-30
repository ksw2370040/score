package bean;

public class Student implements java.io.Serializable {
    // 学生番号を保持するフィールド
    private String no;
    // 学生の名前を保持するフィールド
    private String name;
    // 入学年度を保持するフィールド
    private int entYear;
    // クラス番号を保持するフィールド
    private String classNum;
    // 出席状況を保持するフィールド
    private boolean isAttend;
    // 学校情報を保持するフィールド
    private School school;

    // noフィールドのゲッターメソッド
    public String getNo() {
        return no;
    }

    // noフィールドのセッターメソッド
    public void setNo(String no) {
        this.no = no;
    }

    // nameフィールドのゲッターメソッド
    public String getName() {
        return name;
    }

    // nameフィールドのセッターメソッド
    public void setName(String name) {
        this.name = name;
    }

    // entYearフィールドのゲッターメソッド
    public int getEntYear() {
        return entYear;
    }

    // entYearフィールドのセッターメソッド
    public void setEntYear(int entYear) {
        this.entYear = entYear;
    }

    // classNumフィールドのゲッターメソッド
    public String getClassNum() {
        return classNum;
    }

    // classNumフィールドのセッターメソッド
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    // isAttendフィールドのゲッターメソッド（出席状況を返す）
    public boolean isAttend() {
        return isAttend;
    }

    // isAttendフィールドのセッターメソッド（出席状況を設定）
    public void setIsAttend(boolean isAttend) {
        this.isAttend = isAttend;
    }

    // schoolフィールドのゲッターメソッド
    public School getSchool() {
        return school;
    }

    // schoolフィールドのセッターメソッド
    public void setSchool(School school) {
        this.school = school;
    }
}
