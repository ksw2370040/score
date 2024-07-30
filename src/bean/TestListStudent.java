package bean;

public class TestListStudent implements java.io.Serializable {
    // 科目名を保持するフィールド
    private String subjectName;
    // 科目コードを保持するフィールド
    private String subjectCd;
    // テストの番号を保持するフィールド
    private int num;
    // テストの得点を保持するフィールド
    private int point;

    // subjectNameフィールドのゲッターメソッド
    public String getSubjectName() {
        return subjectName;
    }

    // subjectNameフィールドのセッターメソッド
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    // subjectCdフィールドのゲッターメソッド
    public String getSubjectCd() {
        return subjectCd;
    }

    // subjectCdフィールドのセッターメソッド
    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }

    // numフィールドのゲッターメソッド
    public int getNum() {
        return num;
    }

    // numフィールドのセッターメソッド
    public void setNum(int num) {
        this.num = num;
    }

    // pointフィールドのゲッターメソッド
    public int getPoint() {
        return point;
    }

    // pointフィールドのセッターメソッド
    public void setPoint(int point) {
        this.point = point;
    }
}
