package subject;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School; // 学校データを格納するためのクラス
import bean.Subject; // 科目データを格納するためのクラス
import bean.Teacher; // 教師データを格納するためのクラス
import dao.SubjectDao; // 科目に関するデータアクセスオブジェクト
import tool.Action; // 基本アクションクラス
import tool.Util; // ユーティリティクラス

public class SubjectCreateExecuteAction extends Action {
    private SubjectDao subjectDao = new SubjectDao(); // 科目データを操作するためのDAOインスタンス
    private Util util = new Util(); // ユーティリティインスタンス

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        boolean exists = true; // 科目コードの存在チェックフラグ
        List<Subject> subjects = new ArrayList<>(); // 科目リストの初期化

        // ユーザー情報を取得
        Teacher teacher = util.getUser(req); // リクエストから教師情報を取得
        String cd = req.getParameter("cd"); // リクエストから科目コードを取得
        String name = req.getParameter("name"); // リクエストから科目名を取得
        School school = teacher.getSchool(); // 教師の学校情報を取得

        // 学校に基づいて既存の科目リストを取得
        subjects = subjectDao.filter(teacher.getSchool());

        // 既存の科目コードの確認
        exists = subjects.stream().anyMatch(subject -> subject.getCd().equals(cd)); // 科目コードが既に存在するか確認

        if (exists) {
            // エラーメッセージをセッションに保存
            HttpSession session = req.getSession(); // 現在のセッションを取得
            session.setAttribute("error", "この科目コードは既に存在します。"); // エラーメッセージを設定
            // エラーメッセージを表示するためにリダイレクト
            req.getRequestDispatcher("../subject/SubjectCreate.action").forward(req, res);

        } else {
            // 新しい科目の保存処理
            Subject subject = new Subject(); // 新しい科目オブジェクトを生成
            subject.setCd(cd); // 科目コードを設定
            subject.setName(name); // 科目名を設定
            subject.setSchool(school); // 学校情報を設定

            subjectDao.save(subject); // 科目をデータベースに保存

            // 成功メッセージを表示するためにリダイレクト
            req.getRequestDispatcher("../subject/subject_add_complete.jsp").forward(req, res);
        }
    }
}
