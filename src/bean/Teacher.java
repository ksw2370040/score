package bean;

public class Teacher implements java.io.Serializable {
    // 教師のIDを保持するフィールド
    private String id;
    // 教師のパスワードを保持するフィールド
    private String password;
    // 教師の名前を保持するフィールド
    private String name;
    // 学校情報を保持するフィールド
    private School school;

    // idフィールドのゲッターメソッド
    public String getId() {
        return id;
    }

    // idフィールドのセッターメソッド
    public void setId(String id) {
        this.id = id;
    }

    // nameフィールドのゲッターメソッド
    public String getName() {
        return name;
    }

    // nameフィールドのセッターメソッド
    public void setName(String name) {
        this.name = name;
    }

    // passwordフィールドのゲッターメソッド
    public String getPassword() {
        return password;
    }

    // passwordフィールドのセッターメソッド
    public void setPassword(String password) {
        this.password = password;
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
