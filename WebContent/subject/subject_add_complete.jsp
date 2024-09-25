<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8"> <!-- 文字エンコーディングをUTF-8に設定 -->
	<title>科目管理</title> <!-- ページタイトル -->
	<link rel="stylesheet" type="text/css" href="../css/subject.css"> <!-- スタイルシートのリンク -->
</head>
<body>
	<c:import url="../common/base.jsp"> <!-- 共通のベースページをインポート -->
		<c:param name="title"></c:param> <!-- ページタイトルを設定 -->
		<c:param name="scripts"></c:param> <!-- スクリプトを設定 -->
		<c:param name="content"> <!-- コンテンツ部分を定義 -->
			<section class="me-4">
				<h2>科目情報登録</h2> <!-- 見出し -->
				<div class="comp">
					<p>登録が完了しました</p> <!-- 登録完了メッセージ -->
				</div>
				<div class="comp2">
					<a href="../subject/SubjectCreate.action">戻る</a> <!-- 戻るリンク -->
					&ensp;&ensp;&ensp;&ensp;&ensp; <!-- スペースの追加 -->
					<a href="../subject/SubjectList.action">科目一覧</a> <!-- 科目一覧へのリンク -->
				</div>
			</section>
		</c:param>
	</c:import>
</body>
</html>
