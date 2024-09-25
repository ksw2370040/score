package subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School; // 学校データを格納するためのクラス
import bean.Subject; // 科目データを格納するためのクラス
import bean.Teacher; // 教師データを格納するためのクラス
import dao.SubjectDao; // 科目に関するデータアクセスオブジェクト
import tool.Action; // 基本アクションクラス
import tool.Util; // ユーティリティクラス

public class SubjectUpdateExecuteAction extends Action {

    // HTTPリクエストに基づくアクションの実行
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ユーザー情報を取得
        Util util = new Util(); // ユーティリティクラスのインスタンスを生成
        Teacher teacher = util.getUser(req); // 現在のリクエストから教師情報を取得

        // リクエストパラメータから科目コードと科目名を取得
        String cd = req.getParameter("cd"); // 更新対象の科目コードをリクエストから取得
        String Name = req.getParameter("name"); // 更新対象の科目名をリクエストから取得
        School school = teacher.getSchool(); // 教師が所属する学校情報を取得

        // 更新する科目の情報を設定
        Subject subject = new Subject(); // 新しいSubjectオブジェクトを作成
        subject.setCd(cd); // 科目コードを設定
        subject.setName(Name); // 科目名を設定
        subject.setSchool(school); // 学校情報を設定

        // SubjectDaoのインスタンスを作成し、科目情報を保存
        SubjectDao dao = new SubjectDao(); // 科目データを操作するためのDAOインスタンスを生成
        dao.save(subject); // 科目情報をデータベースに保存

        // 更新完了ページにフォワード
        req.getRequestDispatcher("../subject/subject_update_complete.jsp").forward(req, res); // JSPページにリダイレクト
    }
}
