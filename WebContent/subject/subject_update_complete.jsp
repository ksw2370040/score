<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8"> <!-- 文字エンコーディングをUTF-8に設定 -->
	<title>科目管理</title> <!-- ページタイトル -->
	<link rel="stylesheet" type="text/css" href="../css/subject.css"> <!-- スタイルシートをリンク -->
</head>
<body>
	<c:import url="../common/base.jsp"> <!-- 共通のベースページをインポート -->
		<c:param name="title"></c:param> <!-- ページタイトルを設定（空） -->
		<c:param name="scripts"></c:param> <!-- スクリプトを設定（空） -->
		<c:param name="content"> <!-- コンテンツ部分を定義 -->
			<section class="me-4"> <!-- セクションを作成 -->
				<h2>科目情報変更</h2> <!-- 見出し -->
				<div class="comp"> <!-- 完了メッセージを表示するコンテナ -->
					<p>変更が完了しました</p> <!-- 変更完了のメッセージ -->
				</div>
				<div class="comp2"> <!-- 戻るリンクを表示するコンテナ -->
					<a href="../subject/SubjectList.action">科目一覧</a> <!-- 科目一覧へのリンク -->
				</div>
			</section>
		</c:param>
	</c:import>
</body>
</html>
