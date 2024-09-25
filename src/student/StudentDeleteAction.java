package student;

// 必要なクラスをインポート
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao; // 学生データにアクセスするためのDAO
import tool.Action; // 基本アクションクラス

public class StudentDeleteAction extends Action {

    /*
     * HTTPリクエストに基づくアクションの実行
     *
     * @param req HTTPリクエスト (HttpServletRequest)
     * @param res HTTPレスポンス (HttpServletResponse)
     * @throws Exception リクエスト処理中に発生した例外をスローします。
     */
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // リクエストパラメータから学生番号を取得
        String no = req.getParameter("no"); // 削除対象の学生番号を取得

        // StudentDaoのインスタンスを作成
        StudentDao dao = new StudentDao(); // 学生データを操作するためのDAOインスタンスを生成

        // 指定された学生番号に基づいて学生を削除
        dao.delete(no); // 学生番号を使用して学生を削除するメソッドを呼び出す

        // 学生一覧ページにフォワード
        req.getRequestDispatcher("StudentList.action").forward(req, res); // 削除後、学生一覧ページにリダイレクト
    }
}
