<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset = "UTF-8">
	<title>科目管理</title>
	<link rel="stylesheet" type="text/css" href="../css/subject.css">
</head>
<body>
	<c:import url="../common/base.jsp">
	<c:param name="title"></c:param>
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class="me-4">
			<h2>科目情報登録</h2>
			<div class = "comp">
				<p>登録が完了しました</p>
			</div>
			<div class = "comp2">
				<a href="../subject/SubjectCreate.action">戻る</a>
				&ensp;&ensp;&ensp;&ensp;&ensp;
				<a href="../subject/SubjectList.action">科目一覧</a>
			</div>
		</section>
	</c:param>
	</c:import>
</body>
</html>