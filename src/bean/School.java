package bean;

public class School implements java.io.Serializable {
    // 学校のコードを保持するフィールド
    private String cd;
    // 学校の名前を保持するフィールド
    private String name;

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
