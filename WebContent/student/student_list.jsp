<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- JSTLコアタブライブラリをインポート -->
<!DOCTYPE html>
<html lang="ja"> <!-- 言語を日本語に設定 -->
<head>
    <meta charset="UTF-8"> <!-- 文字エンコーディングをUTF-8に設定 -->
    <title>学生管理</title> <!-- ページのタイトル -->
    <link rel="stylesheet" type="text/css" href="../css/student.css"> <!-- スタイルシートのリンク -->
</head>
<body>
    <c:import url="../common/base.jsp"> <!-- 共通のベースJSPをインポート -->
        <c:param name="title"></c:param> <!-- タイトルパラメータ -->
        <c:param name="scripts"></c:param> <!-- スクリプトパラメータ -->
        <c:param name="content"> <!-- コンテンツパラメータの開始 -->
            <section class="me-4"> <!-- 学生管理セクション -->
                <h2>学生管理</h2> <!-- セクションタイトル -->
                <div class="new">
                    <a href="StudentCreate.action">新規登録</a> <!-- 新規登録へのリンク -->
                </div>
                <form method="get"> <!-- 検索フォーム -->
                    <div id="filter"> <!-- フィルタDIV -->
                        <div class="form-group">
                            <label for="student-f1-select">入学年度</label> <!-- 入学年度ラベル -->
                            <select id="student-f1-select" name="f1"> <!-- 入学年度セレクトボックス -->
                                <option value="0">--------</option> <!-- デフォルトオプション -->
                                <c:forEach var="year" items="${ent_year_set}"> <!-- 入学年度のオプションをループ -->
                                    <option value="${year}" <c:if test="${year==f1 }">selected</c:if>>${year}</option> <!-- 選択された年度にチェック -->
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <label for="student-f2-select">クラス</label> <!-- クラスラベル -->
                            <select id="student-f2-select" name="f2"> <!-- クラスセレクトボックス -->
                                <option value="0">--------</option> <!-- デフォルトオプション -->
                                <c:forEach var="num" items="${class_num_set}"> <!-- クラスのオプションをループ -->
                                    <option value="${num}" <c:if test="${num==f2 }">selected</c:if>>${num}</option> <!-- 選択されたクラスにチェック -->
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <label for="student-f3-check">在学中 <!-- 在学中チェックボックス -->
                                <input type="checkbox" id="student-f3-check" name="f3" value="t" <c:if test="${!empty f3}">checked</c:if>> <!-- チェックされている場合はチェックを入れる -->
                            </label>
                        </div>
                        <div>
                            <button id="filter-button">絞込み</button> <!-- 絞込みボタン -->
                        </div>
                        <div style="color:red;">${errors.get("f1")}</div> <!-- エラーメッセージを表示 -->
                    </div>
                </form>
                <c:choose> <!-- 検索結果の表示分岐 -->
                    <c:when test="${students.size()>0}"> <!-- 学生情報が存在する場合 -->
                        <div>検索結果:${students.size()}件</div> <!-- 検索結果の件数を表示 -->
                        <table>
                            <tr>
                                <th>入学年度</th> <!-- テーブルのヘッダー -->
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>クラス</th>
                                <th class="center">在学中</th>
                                <th></th> <!-- 変更リンク用の空のヘッダー -->
                            </tr>
                            <c:forEach var="student" items="${students}"> <!-- 検索結果の学生リストをループ -->
                                <tr>
                                    <td>${student.entYear}</td> <!-- 入学年度を表示 -->
                                    <td>${student.no}</td> <!-- 学生番号を表示 -->
                                    <td>${student.name}</td> <!-- 氏名を表示 -->
                                    <td>${student.classNum}</td> <!-- クラスを表示 -->
                                    <td class="center"> <!-- 在学中の表示 -->
                                        <c:choose>
                                            <c:when test="${student.isAttend()}"> <!-- 在学中かどうかの判定 -->
                                                〇 <!-- 在学中 -->
                                            </c:when>
                                            <c:otherwise>
                                                × <!-- 非在学 -->
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td><a href="StudentUpdate.action?no=${student.no}">変更</a></td> <!-- 学生情報変更へのリンク -->
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise> <!-- 学生情報が存在しない場合 -->
                        <div>学生情報が存在しませんでした</div> <!-- メッセージを表示 -->
                    </c:otherwise>
                </c:choose>
            </section>
        </c:param> <!-- コンテンツパラメータの終了 -->
    </c:import>
</body>
</html>
