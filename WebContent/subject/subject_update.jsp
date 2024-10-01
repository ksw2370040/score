<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8"> <!-- 文字エンコーディングを指定 -->
    <title>科目管理</title> <!-- ページタイトル -->
    <link rel="stylesheet" type="text/css" href="../css/subject.css"> <!-- CSSファイルをリンク -->
</head>
<body>
<!-- 共通の基盤となるJSPをインポート -->
    <c:import url="../common/base.jsp">
        <c:param name="title"></c:param>
        <c:param name="scripts"></c:param>
        <c:param name="content">
            <section class="me-4"> <!-- メインコンテンツセクション -->
                <form action="SubjectUpdateExecute.action" method="get"> <!-- フォームの開始 -->
                    <div id="filter"> <!-- フィルタ用のコンテナ -->
                        <h2>科目情報変更</h2> <!-- セクション見出し -->
                        <div> <!-- 科目コードの入力欄 -->
                            <label>科目コード</label> <!-- ラベル -->
                            <p>&ensp;&ensp;${subject.cd}</p> <!-- 現在の科目コードを表示 -->
                            <input id="radius" type="hidden" name="cd" value="${subject.cd}" required> <!-- 科目コードを隠しフィールドで送信 -->
                        </div>
                        <div> <!-- 科目名の入力欄 -->
                            <label>科目名</label> <!-- ラベル -->
                            <input id="subject-name" type="text" name="name" placeholder="科目名を入力してください" maxlength="30" required> <!-- 科目名の入力 -->
                        </div>
                        <div> <!-- ボタン用のコンテナ -->
                            <button id="button">変更</button> <!-- 変更ボタン -->
                        </div>
                        <p><a href="../subject/SubjectList.action">戻る</a> <!-- 戻るリンク -->
                    </div> <!-- フィルタコンテナの終了 -->
                </form> <!-- フォームの終了 -->
            </section>
        </c:param>
    </c:import>
</body>
</html>
