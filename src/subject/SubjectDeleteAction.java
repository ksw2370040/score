package subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject; // 科目データを格納するためのクラス
import bean.Teacher; // 教師データを格納するためのクラス
import dao.SubjectDao; // 科目に関するデータアクセスオブジェクト
import tool.Action; // 基本アクションクラス
import tool.Util; // ユーティリティクラス

public class SubjectDeleteAction extends Action {

    // HTTPリクエストに基づくアクションの実行
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ユーザー情報を取得
        Util util = new Util(); // ユーティリティクラスのインスタンスを生成
        Teacher user = util.getUser(req); // リクエストから教師情報を取得

        // リクエストパラメータから科目コードを取得
        String subject_cd = req.getParameter("cd"); // 科目コードをリクエストから取得

        // SubjectDaoのインスタンスを作成し、科目情報を取得
        SubjectDao subjectDao = new SubjectDao(); // 科目データを操作するためのDAOインスタンスを生成
        Subject subject = subjectDao.get(subject_cd, user.getSchool()); // 科目コードと学校情報を基に科目を取得

        // 取得した科目情報をリクエスト属性に設定
        req.setAttribute("subject", subject); // 科目情報をリクエスト属性に設定

        // 確認ページにフォワード
        req.getRequestDispatcher("../subject/SubjectDeleteConfirm.jsp").forward(req, res); // 削除確認ページにリダイレクト
    }
}
