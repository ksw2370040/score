<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- JSTLコアタブライブラリをインポート -->
<head>
    <title>得点管理システム</title> <!-- ページタイトル -->
    <link href="../css/student-add.css" rel="stylesheet" /> <!-- スタイルシートのリンク -->
</head>
<body>
<c:import url="../common/base.jsp"> <!-- 共通のベースJSPをインポート -->

	<c:param name="title"></c:param> <!-- タイトルパラメータ -->
	<c:param name="scripts"></c:param> <!-- スクリプトパラメータ -->
	<c:param name="content"> <!-- コンテンツパラメータの開始 -->
	<section class="me-4"> <!-- 学生情報変更セクション -->
		<div id="addcomplete"> <!-- 変更完了メッセージを表示するDIV -->
			<h2>学生情報変更</h2> <!-- セクションタイトル -->
			<p>変更が完了しました</p> <!-- 変更完了のメッセージ -->
			<a href="../student/StudentList.action">学生一覧</a> <!-- 学生一覧へのリンク -->
		</div>
	</section>
	</c:param> <!-- コンテンツパラメータの終了 -->
</c:import>
</body>
</html>
