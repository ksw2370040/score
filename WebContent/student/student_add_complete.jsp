<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>得点管理システム</title>
    <link href="../css/student-add.css" rel="stylesheet" />
</head>
<body>
<c:import url="../common/base.jsp">
    <c:param name="title"></c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
            <div id="addcomplete">
                <h2>学生情報登録</h2>
                <p>登録が完了しました</p>
                <div class="link-container">
                    <a href="../student/StudentCreate.action">戻る</a>
                    <a href="../student/StudentList.action">学生一覧</a>
                </div>
            </div>
        </section>
    </c:param>
</c:import>
</body>
</html>
