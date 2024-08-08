<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
    <title>科目管理</title>
    <link rel="stylesheet" type="text/css" href="../css/subject.css">
</head>
<body>
	<c:import url="../common/base.jsp">
	<c:param name="title"></c:param>
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class= "me-4">
			<form action="SubjectUpdateExecute.action" method="get">
				<div id="filter">
					<h2>科目情報変更</h2>
					<div>
						<label>科目コード</label>
						<p>&ensp;&ensp;${subject.cd}</p>
						<input id="radius" type="hidden" name="cd" value="${subject.cd}" required>
					</div>
					<div>
						<label>科目名</label>
						<input id="subject-name" type="text" name="name" placeholder="科目名を入力してください" maxlength="30" required>
					</div>
					<div>
						<button id = "button">変更</button>
					</div>
					<p><a href="../subject/SubjectList.action">戻る</a>
				</div>
			</form>
		</section>
	</c:param>
</c:import>
</body>
</html>
