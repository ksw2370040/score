<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>学生管理</title>
    <link rel="stylesheet" type="text/css" href="../css/subject.css">
    <script>
        const existingCodes = [
            <c:forEach var="code" items="${existingSubjectCodes}">
                "${code}"<c:if test="${!status.last}">,</c:if>
            </c:forEach>
        ];

        console.log(existingCodes);

        function checkDuplicate() {
            const inputCode = document.getElementById("radius").value;

            if (existingCodes.includes(inputCode)) {
                alert("この科目コードは既に存在します。");
                return false;
            }

            return true;
        }
    </script>
</head>
<body>
    <c:import url="../common/base.jsp">
        <c:param name="title"></c:param>
        <c:param name="scripts"></c:param>
        <c:param name="content">
            <section class="me-4">
                <h2>科目情報登録</h2>
			    <c:if test="${not empty error}">
			        <p style="color: red;">${error}</p>
			        <c:set var="error" value="${null}" scope="session" />
			    </c:if>
                <form action="SubjectCreateExecute.action" method="post" onsubmit="return checkDuplicate()">
                    <div id="filter">
                        <div>
                            <label>科目コード</label>
                            <input id="radius" type="text" name="cd" placeholder="科目コードを入力してください" maxlength="3" pattern=".{3}" required title="3文字を入力してください"><br>
                        </div>
                        <div>
                            <label>科目名</label>
                            <input id="radius" type="text" name="name" placeholder="科目名を入力してください" required><br>
                        </div>
                        <div>
                            <button id="button">登録</button>
                        </div>
                    </div>
                </form>
                <div>
                    <a href="../subject/SubjectList.action">戻る</a>
                </div>
            </section>
        </c:param>
    </c:import>
</body>
</html>
