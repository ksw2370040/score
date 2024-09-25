package subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SubjectDao; // 科目に関するデータアクセスオブジェクト
import tool.Action; // 基本アクションクラス

public class SubjectDeleteExecuteAction extends Action {

    // HTTPリクエストに基づくアクションの実行
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // リクエストパラメータから科目コードを取得
        String cd = req.getParameter("cd"); // 削除対象の科目コードをリクエストから取得

        // SubjectDaoのインスタンスを作成し、指定された科目コードを削除
        SubjectDao dao = new SubjectDao(); // 科目データを操作するためのDAOインスタンスを生成
        dao.delete(cd); // 指定された科目コードに基づいて科目を削除

        // 削除後、科目リストページにフォワード
        req.getRequestDispatcher("SubjectList.action").forward(req, res); // 科目一覧ページにリダイレクト
    }
}
