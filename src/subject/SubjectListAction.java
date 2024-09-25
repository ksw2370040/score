package subject;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject; // 科目データを格納するためのクラス
import bean.Teacher; // 教師データを格納するためのクラス
import dao.SubjectDao; // 科目に関するデータアクセスオブジェクト
import tool.Action; // 基本アクションクラス
import tool.Util; // ユーティリティクラス

public class SubjectListAction extends Action {

    // HTTPリクエストに基づくアクションの実行
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        List<Subject> list = null; // 科目のリストを初期化

        // ユーザー情報を取得
        Util util = new Util(); // ユーティリティクラスのインスタンスを生成
        Teacher teacher = util.getUser(req); // 現在のリクエストから教師情報を取得

        // SubjectDaoのインスタンスを作成
        SubjectDao subjectDao = new SubjectDao(); // 科目データを操作するためのDAOインスタンスを生成

        // リクエストパラメータから科目名を取得
        String Name = req.getParameter("f1"); // フィルタリング用の科目名をリクエストから取得

        // 科目名が指定されている場合は、その名前でフィルタリング
        if (Name != null) {
            list = subjectDao.filter(teacher.getSchool(), Name); // 指定された科目名でフィルタリング
        } else {
            // 科目名が指定されていない場合は、全ての科目を取得
            list = subjectDao.filter(teacher.getSchool()); // 学校に基づいて全ての科目を取得
        }

        // 取得した科目のリストをリクエスト属性に設定
        req.setAttribute("subjects", list); // 科目リストをリクエストに設定

        // 科目リストの表示ページにフォワード
        req.getRequestDispatcher("../subject/subject_list.jsp").forward(req, res); // JSPページにリダイレクト
    }
}
