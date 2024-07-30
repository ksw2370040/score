package bean;

public class Test implements java.io.Serializable {

    // 学生情報を保持するフィールド
    private Student student;
    // 学校情報を保持するフィールド
    private School school;
    // 科目情報を保持するフィールド
    private Subject subject;
    // クラス番号を保持するフィールド
    private String classNum;
    // テスト番号を保持するフィールド
    private int no;
    // テストの得点を保持するフィールド
    private int point;

    // コンストラクタ、ゲッター、セッターは省略

    // studentフィールドのゲッターメソッド
    public Student getStudent() {
        return student;
    }

    // studentフィールドのセッターメソッド
    public void setStudent(Student student) {
        this.student = student;
    }

    // subjectフィールドのゲッターメソッド
    public Subject getSubject() {
        return subject;
    }

    // subjectフィールドのセッターメソッド
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    // schoolフィールドのゲッターメソッド
    public School getSchool() {
        return school;
    }

    // schoolフィールドのセッターメソッド
    public void setSchool(School school) {
        this.school = school;
    }

    // noフィールドのゲッターメソッド
    public int getNo() {
        return no;
    }

    // noフィールドのセッターメソッド
    public void setNo(int no) {
        this.no = no;
    }

    // pointフィールドのゲッターメソッド
    public int getPoint() {
        return point;
    }

    // pointフィールドのセッターメソッド
    public void setPoint(int point) {
        this.point = point;
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
