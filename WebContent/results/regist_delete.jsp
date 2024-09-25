<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- JSTLコアタブライブラリのインポート -->

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>成績登録</title> <!-- ページのタイトル -->
    <link rel="stylesheet" type="text/css" href="../css/results.css"> <!-- スタイルシートのリンク -->
</head>
<body>
    <!-- base.jspのインポート -->
    <c:import url="../common/base.jsp"> <!-- 共通のベースJSPをインポート -->
        <c:param name="title"></c:param> <!-- タイトルパラメータ -->
        <c:param name="scripts"></c:param> <!-- スクリプトパラメータ -->
        <c:param name="content"> <!-- コンテンツパラメータの開始 -->
            <section class="me-4"> <!-- 成績登録セクション -->
                <c:choose> <!-- 条件分岐 -->
                    <c:when test="${tests.size() >= 0}"> <!-- テストのリストが空でない場合 -->
                        <form action="TestDeleteExecute.action" method="get"> <!-- 削除フォーム -->
                            <table>
                                <c:forEach var="test" items="${tests}"> <!-- テストリストをループ -->
                                    <tr>
                                        <td><input type="hidden" name="student_no" value="${test.student.no}" required></td> <!-- 学生番号を隠しフィールドとして追加 -->
                                        <td><input type="hidden" name="subject_cd" value="${test.subject.cd}" required></td> <!-- 科目コードを隠しフィールドとして追加 -->
                                        <td><input type="hidden" name="class_num" value="${test.classNum}" required></td> <!-- クラス番号を隠しフィールドとして追加 -->
                                        <td><input type="hidden" name="test_no" value="${test.no}" required></td> <!-- テスト番号を隠しフィールドとして追加 -->
                                        <td><input type="hidden" name="count" value="${tests.size()}" required></td> <!-- テストの総数を隠しフィールドとして追加 -->
                                    </tr>
                                </c:forEach>
                            </table>
                            <p>本当に削除しますか</p> <!-- 確認メッセージ -->
                            <button>削除する</button> <!-- 削除ボタン -->
                        </form>
                    </c:when>
                </c:choose>
            </section>
        </c:param> <!-- コンテンツパラメータの終了 -->
    </c:import> <!-- base.jspのインポート終了 -->
</body>
</html>
