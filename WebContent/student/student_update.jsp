<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>学生管理</title>
    <link rel="stylesheet" type="text/css" href="../css/student-add.css">
    <script>
    // "変更"ボタンがクリックされたときのイベントリスナーを追加
    document.getElementById("changeButton").addEventListener("click", function() {
        // 他のページに移動するURLを指定
        window.location.href = "student_update_complete.jsp";
    });
</script>

</head>
<body>
<c:import url="../common/base.jsp">
	<c:param name="title"></c:param>
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class= "me-4">
			<form action="StudentUpdateExecute.action" method="get">
				<div id="filter">
				<h2>学生情報変更</h2>
					<div>
						<label>入学年度</label>
						<p>${student.entYear}</p>
						<!-- 入学年度を隠しフィールドで送信 -->
						<input id="radius" type="hidden" name="ent_year" value="${student.entYear}" required>
					</div>
					<div>
						<label>学生番号</label>
						<p>${student.no}</p>
						<!-- 学生番号を隠しフィールドで送信 -->
						<input id="radius" type="hidden" name="no" value="${student.no}" required>
					</div>
					<div>
						<label>氏名</label>
						<!-- 学生の名前を入力 -->
						<input id="radius" type="text" name="name" value="${student.name}" required><br>
					</div>
					<div>
						<label>クラス</label>
						<!-- クラスを選択するドロップダウンリスト -->
						<select name="class_num">
							<option value="0">${student.classNum}</option>
							<c:forEach var="num" items="${class_num_set}">
								<option value="${num}" <c:if test="${num==class_num}">selected</c:if>>${num}</option>
							</c:forEach>
						</select>
					</div>
					<div>
						<label>在学中
							<!-- 在学中かどうかを示すチェックボックス -->
							<input type="checkbox" name="isAttend" value="t"<c:if test="${!empty isAttend}">checked</c:if>>
						</label>
					</div>
					<div>
						<!-- 変更ボタン -->
						<button id="changeButton">変更</button>
					</div>
					<div>${errors.get("f1")}</div>
				</div>
			</form>
			 <p></p>
            <div>
                <!-- 学生一覧に戻るリンク -->
                <a href="../student/StudentList.action">戻る</a>
            </div>
    </section>
	</c:param>
</c:import>
</body>
</html>
