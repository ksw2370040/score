package bean;

import java.util.Map;

public class TestListSubject implements java.io.Serializable {
    // 入学年度を保持するフィールド
    private int entYear;
    // 学生番号を保持するフィールド
    private String studentNo;
    // 学生の名前を保持するフィールド
    private String studentName;
    // クラス番号を保持するフィールド
    private String classNum;
    // テストの得点を保持するマップ
    private Map<Integer, Integer> points;

    // デフォルトコンストラクタ
    public TestListSubject() {}

    // entYearフィールドのゲッターメソッド
    public int getEntYear() {
        return entYear;
    }

    // entYearフィールドのセッターメソッド
    public void setEntYear(int entYear) {
        this.entYear = entYear;
    }

    // studentNoフィールドのゲッターメソッド
    public String getStudentNo() {
        return studentNo;
    }

    // studentNoフィールドのセッターメソッド
    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    // studentNameフィールドのゲッターメソッド
    public String getStudentName() {
        return studentName;
    }

    // studentNameフィールドのセッターメソッド
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    // classNumフィールドのゲッターメソッド
    public String getClassNum() {
        return classNum;
    }

    // classNumフィールドのセッターメソッド
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    // pointsフィールドのゲッターメソッド
    public Map<Integer, Integer> getPoints() {
        return points;
    }

    // pointsフィールドのセッターメソッド
    public void setPoints(Map<Integer, Integer> points) {
        this.points = points;
    }

    // 指定されたキーの得点を取得するメソッド
    public String getPoint(int key) {
        String point = String.valueOf(this.points.get(key));
        return point;
    }

    // 指定されたキーと値をマップに追加するメソッド
    public void putPoint(int key, int value) {
        points.put(key, value);
    }
}
