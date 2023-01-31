<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>UserList</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="/css/font.css" />
  </head>
  <body class="bg-light">
    <div class="container pb-5">
      <%@ include file="header.jsp" %>
      <table class="table table-striped table-hover table-bordered">
	      <thead>
		      <tr class="text-center">
			      <th><input type="checkbox" id="selectall" /></th>
			      <th>USER ID</th>
			      <th>이름</th>
			      <th>생년월일</th>
			      <th>번호</th>
			      <th>Delete</th>
		      </tr>
	      </thead>
	      <tbody>
		      <c:forEach items="${resultMap}" var="resultData" varStatus="loop">
			      <tr>
				      <td class="text-center"><input type="checkbox" class="checkbox"
					      name="USER_ID"  /></td>
				      <td>
					      <form action="/commonCodeOur/edit/${resultData.USER_ID}" method="get">
						      <button class="btn btn-link viewPopup"
							      >${resultData.USER_ID}</button>
					      </form>
				      </td>
				      <td>${resultData.NAME}</td>
				      <td class="text-center">
					      <div class="btn-group">
					      <button class="btn btn-outline-info"
							      name="PARENT_USER_ID" >
							      ${resultData.PARENT_USER_ID}
						      </button>
					      </div>
				      </td>
				      <td>
					      <form action="/commonCodeOur/delete/${resultData.USER_ID}" method="post">
						      <button class="btn btn-outline-info"
							      >Delete</button>
					      </form>
				      </td>
			      </tr>
		      </c:forEach>
	      </tbody>
      </table>
    </div>
    <hr />
    <%@ include file="footer.jsp" %>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
      crossorigin="anonymous"
    ></script>
  </body>
</html>