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
				<form action="SubjectUpdateExecute.action" method="get"> <!-- フォームの開始 -->
					<div id="filter"> <!-- フィルタコンテナ -->
						<h2>科目情報変更</h2> <!-- 見出し -->
						<div>
							<label>科目コード</label> <!-- 科目コードのラベル -->
							<p>&ensp;&ensp;${subject.cd}</p> <!-- 科目コードの表示 -->
							<input id="radius" type="hidden" name="cd" value="${subject.cd}" required> <!-- 隠しフィールドで科目コードを保持 -->
						</div>
						<div>
							<label>科目名</label> <!-- 科目名のラベル -->
							<input id="subject-name" type="text" name="name" placeholder="科目名を入力してください" maxlength="30" required> <!-- 科目名入力フィールド -->
						</div>
						<div>
							<button id="button">変更</button> <!-- 変更ボタン -->
						</div>
						<p><a href="../subject/SubjectList.action">戻る</a></p> <!-- 科目一覧へのリンク -->
					</div>
				</form>
			</section>
		</c:param>
	</c:import>
</body>
</html>
