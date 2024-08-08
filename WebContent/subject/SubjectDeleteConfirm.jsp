<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>削除確認</title>
	 <link rel="stylesheet" type="text/css" href="../css/subject.css">
</head>
<body>
<c:import url="../common/base.jsp">
	<c:param name="title"></c:param>
	<c:param name="scripts"></c:param>
	<c:param name="content">
	<section class="me-4">
	<h2>科目削除確認</h2>



						<p>「${subject.name}( ${subject.cd})」を削除してもよろしいですか</p>


						<a class="delete-button" href="#" onclick="deleteSubject('${subject.cd}')">削除</a><br><br><br><br>



<a href="../subject/SubjectList.action">戻る</a>	</section>
	</c:param>
</c:import>
</body>
<style>
    .delete-button {
        display: inline-block;
        padding: 5px 10px;
        margin: 5px 0;
        font-size: 14px;
        font-weight: bold;
        color: #fff;
        background-color: #d9534f;
        border: none;
        border-radius: 4px;
        text-decoration: none;
        text-align: center;
    }

    .delete-button:hover {
        background-color: #c9302c;
    }
</style>
<script type="text/javascript">
function deleteSubject(subjectCd) {
    // XMLHttpRequestを使用して削除リクエストを送信
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "SubjectDeleteExecute.action?cd=" + subjectCd, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            // リクエストが成功した場合、削除完了画面にリダイレクト
            window.location.href = "deleteSuccess.jsp";
        }
    };
    xhr.send();
}
</script>



</html>
