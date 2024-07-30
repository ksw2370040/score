<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    if (session.getAttribute("user") == null) {
    	request.getRequestDispatcher("../user/LoginExecute.action").forward(request, response);
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>得点管理システム</title>
    <link href="../css/stylesheet.css" rel="stylesheet" />
</head>
<body>
    <header>
        <h1>得点管理システム</h1>
        <div class="header">
                <span>${sessionScope.user.name}様</span>
                <a href="../user/logout.jsp">ログアウト</a>
        </div>
    </header>
    <div class="sidebar">
        <nav>
            <ul>
                <li><a href="../menu/menu.jsp">メニュー</a></li>
                <li><a href="../student/StudentList.action">学生管理</a></li>
                <li><label for="score">成績管理</label></li>
                <li><a href="../results/TestRegist2.action" id="score">成績登録</a></li>
                <li><a href="../results/TestList.action" id="score">成績参照</a></li>
				<li><a href="../subject/SubjectList.action">科目管理</a></li>
			</ul>
        </nav>
    </div>
    <div class="content">
        ${param.content}
    </div>
    <footer>
        <p>© 2023 TIC</p>
        <p>大原学園</p>
    </footer>
</body>

</html>
