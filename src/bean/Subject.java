package bean;

public class Subject implements java.io.Serializable {
    // 学校情報を保持するフィールド
    private School school;
    // 科目コードを保持するフィールド
    private String cd;
    // 科目名を保持するフィールド
    private String name;

    // schoolフィールドのゲッターメソッド
    public School getSchool() {
        return school;
    }

    // schoolフィールドのセッターメソッド
    public void setSchool(School school) {
        this.school = school;
    }

    // cdフィールドのゲッターメソッド
    public String getCd() {
        return cd;
    }

    // cdフィールドのセッターメソッド
    public void setCd(String cd) {
        this.cd = cd;
    }

    // nameフィールドのゲッターメソッド
    public String getName() {
        return name;
    }

    // nameフィールドのセッターメソッド
    public void setName(String name) {
        this.name = name;
    }
}
