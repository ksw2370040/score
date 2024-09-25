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
		<c:param name="title">科目管理</c:param> <!-- ページタイトルを設定 -->
		<c:param name="scripts"></c:param> <!-- スクリプトを設定 -->
		<c:param name="content"> <!-- コンテンツ部分を定義 -->
			<section class="me-4">
				<h2>科目管理</h2> <!-- 見出し -->
				<form method="get"> <!-- GETメソッドで検索フォームを作成 -->
					<input id="subject-name" type="text" name="f1" placeholder="科目名を入力してください" > <!-- 科目名の入力フィールド -->
					<button id="filter-button">検索</button> <!-- 検索ボタン -->
				</form>
				<div class="new">
					<a href="SubjectCreate.action">新規登録</a> <!-- 新規登録ページへのリンク -->
				</div>
				<table>
					<tr>
						<th>科目コード</th> <!-- 科目コードの列見出し -->
						<th>科目名</th> <!-- 科目名の列見出し -->
						<th></th> <!-- 空の列（変更用） -->
						<th></th> <!-- 空の列（削除用） -->
					</tr>
					<c:forEach var="subject" items="${subjects}"> <!-- 科目のリストをループで取得 -->
						<tr>
							<td>${subject.cd}</td> <!-- 科目コードを表示 -->
							<td>${subject.name}</td> <!-- 科目名を表示 -->
							<td><a href="SubjectUpdate.action?cd=${subject.cd}">変更</a></td> <!-- 変更リンク -->
							<td><a href="SubjectDelete.action?cd=${subject.cd}">削除</a></td> <!-- 削除リンク -->
						</tr>
					</c:forEach>
				</table>
			</div>
		</section>
	</c:param>
	</c:import>
</body>
</html>
