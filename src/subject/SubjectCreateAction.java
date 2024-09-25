package subject;

// 必要なクラスをインポート
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject; // 科目データを格納するためのクラス
import bean.Teacher; // 教師データを格納するためのクラス
import dao.SubjectDao; // 科目に関するデータアクセスオブジェクト
import tool.Action; // 基本アクションクラス
import tool.Util; // ユーティリティクラス

public class SubjectCreateAction extends Action {

    // HTTPリクエストに基づくアクションの実行
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ユーティリティクラスを使用して教師情報を取得
        Util util = new Util(); // ユーティリティインスタンスを生成
        Teacher teacher = util.getUser(req); // リクエストから教師情報を取得

        // リクエストパラメータから科目コードを取得
        String cd = req.getParameter("cd"); // 科目コードをリクエストから取得

        // SubjectDaoのインスタンスを作成
        SubjectDao subjectDao = new SubjectDao(); // 科目データを操作するためのDAOインスタンスを生成

        // 教師の学校と科目コードに基づいて科目リストを取得
        List<Subject> list = subjectDao.filter(teacher.getSchool(), cd); // 指定された学校と科目コードでフィルタリング

        // 取得した科目リストをリクエスト属性に設定
        req.setAttribute("subjects", list); // 科目リストをリクエスト属性に設定

        // subject_add.jspにフォワード
        req.getRequestDispatcher("../subject/subject_add.jsp").forward(req, res); // JSPページにリダイレクト
    }
}
