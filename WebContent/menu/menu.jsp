<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
    <title>メニュー</title>
    <link rel="stylesheet" type="text/css" href="../css/subject.css">
</head>
<body>
	<c:import url="../common/base.jsp">
	<c:param name="title"></c:param>
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class="me-4">


	    <h2>メニュー</h2>

	<div class  = "sub">
            <a href = "../student/StudentList.action">学生管理</a>
        </div>

        <div class  = "sub2">
            <p>成績管理</p>
            <a href = "../results/TestRegist2.action">成績登録</a><br><br>
            <a href = "../test/TestList.action">成績参照</a>
        </div>

        <div class  = "sub3">
            <a href = "../subject/SubjectList.action">科目管理</a>
        </div>




	</section>
	</c:param>
</c:import>
</body>
</html>