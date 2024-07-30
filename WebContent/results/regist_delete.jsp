<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>成績登録</title>
    <!-- 外部スタイルシートのリンク -->
    <link rel="stylesheet" type="text/css" href="../css/results.css">
</head>
<body>
    <!-- 共通のベースページをインポート -->
    <c:import url="../common/base.jsp">
        <!-- ベースページにパラメータを渡す -->
        <c:param name="title"></c:param> <!-- ページタイトル -->
        <c:param name="scripts"></c:param> <!-- ページに必要なスクリプト -->
        <c:param name="content">
            <!-- 成績削除のセクション -->
            <section class="me-4">
                <!-- テストが1つ以上存在する場合の条件分岐 -->
                <c:choose>
                    <c:when test="${tests.size() >= 0}">
                        <!-- テスト削除フォーム -->
                        <form action="TestDeleteExecute.action" method="get">
                            <table>
                                <!-- 各テストアイテムをループして非表示の入力フィールドを生成 -->
                                <c:forEach var="test" items="${tests}">
                                    <tr>
                                        <!-- テストに関連する情報を隠しフィールドとして送信 -->
                                        <td><input type="hidden" name="student_no" value="${test.student.no}" required></td>
                                        <td><input type="hidden" name="subject_cd" value="${test.subject.cd}" required></td>
                                        <td><input type="hidden" name="class_num" value="${test.classNum}" required></td>
                                        <td><input type="hidden" name="test_no" value="${test.no}" required></td>
                                        <td><input type="hidden" name="count" value="${tests.size()}" required></td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <p>本当に削除しますか</p> <!-- 削除確認のメッセージ -->
                            <button>削除する</button> <!-- 削除ボタン -->
                        </form>
                    </c:when>
                </c:choose>
            </section>
        </c:param>
    </c:import>
</body>
</html>
