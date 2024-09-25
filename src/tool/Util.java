package tool;

import java.time.LocalDate; // 日付を扱うためのクラス
import java.util.ArrayList; // 可変長の配列を提供するクラス
import java.util.List; // リストを扱うためのインターフェース

import javax.servlet.http.HttpServletRequest; // HTTPリクエストを表すクラス
import javax.servlet.http.HttpSession; // セッションを扱うためのクラス

import bean.Subject; // 科目を表すクラス
import bean.Teacher; // 教師を表すクラス
import dao.ClassNumDao; // クラス番号に関するデータ操作を行うDAOクラス
import dao.SubjectDao; // 科目に関するデータ操作を行うDAOクラス

public class Util {

    // セッションからユーザー情報を取得するメソッド
    public Teacher getUser(HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession(); // 現在のセッションを取得
        Teacher user = (Teacher) session.getAttribute("user"); // セッションからユーザー情報を取得
        return user; // ユーザー情報を返す
    }

    // クラス番号のリストをリクエストに設定するメソッド
    public void setClassNum(HttpServletRequest req) throws Exception {
        Teacher teacher = getUser(req); // ユーザー情報を取得
        ClassNumDao cNumDao = new ClassNumDao(); // クラス番号DAOのインスタンスを作成
        List<String> list = cNumDao.filter(teacher.getSchool()); // 学校に基づいてクラス番号を取得
        req.setAttribute("class_num_set", list); // リクエスト属性にクラス番号のリストを設定
    }

    // 年度のリストをリクエストに設定するメソッド
    public void setEntYearSet(HttpServletRequest req) throws Exception {
        LocalDate todaysDate = LocalDate.now(); // 今日の日付を取得
        int year = todaysDate.getYear(); // 現在の年を取得
        List<Integer> entYearSet = new ArrayList<>(); // 年度のリストを初期化
        // 現在の年から10年前までの年度をリストに追加
        for (int i = year - 10; i < year + 1; i++) {
            entYearSet.add(i);
        }
        req.setAttribute("ent_year_set", entYearSet); // リクエスト属性に年度のリストを設定
    }

    // 科目のリストをリクエストに設定するメソッド
    public void setSubject(HttpServletRequest req) throws Exception {
        Teacher user = getUser(req); // ユーザー情報を取得
        SubjectDao subjectDao = new SubjectDao(); // 科目DAOのインスタンスを作成
        List<Subject> subjects = subjectDao.filter(user.getSchool()); // 学校に基づいて科目を取得
        req.setAttribute("subjects", subjects); // リクエスト属性に科目のリストを設定
    }

    // 数値のリストをリクエストに設定するメソッド
    public void setNumSet(HttpServletRequest req) throws Exception {
        List<Integer> number = new ArrayList<>(); // 数値のリストを初期化
        // 1から10までの整数をリストに追加
        for (int i = 1; i <= 10; i++) {
            number.add(i);
        }
        req.setAttribute("number", number); // リクエスト属性に数値のリストを設定
    }
}
