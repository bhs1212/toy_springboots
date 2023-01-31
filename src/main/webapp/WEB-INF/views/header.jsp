<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<header class="mb-5 fs-5">
      <%-- user_id = (String)session.getAttribute("user_id"); --%>
  <nav class="navbar fixed-top navbar-expand-sm bg-light p-3">
    <div class="container-fluid">
      <a href="/main" class="navbar-brand">
        <img src="../img/logo.png" width="100" alt="" />
      </a>
      <a href="#collapseId" class="navbar-toggler" data-bs-toggle="collapse"
        ><span class="navbar-toggler-icon"></span
      ></a>
      <div
        class="collapse navbar-collapse d-flex-md justify-content-between"
        id="collapseId"
      >
        <div class="navbar-nav">
          <a href="/main" class="nav-item nav-link">Home</a>
          <a href="/jsp/survey_start.jsp" class="nav-item nav-link">설문</a>
          <div class="dropdown"> 
            <a
              href=""
              class="nav-link dropdown-toggle"
              data-bs-toggle="dropdown"
              aria-expanded="false"
              >통계</a
            >
            <ul class="dropdown-menu mb-3">
              <a href="/result/statistics1Servlets" class="dropdown-item text-secondary"
                >설문자별 답변 결과</a
              >
              <a href="/result/statistics2Servlets" class="dropdown-item text-secondary"
                >질문별 총 답변수</a
              >
            </ul>
          </div>
        </div>
        <div class="d-flex justify-content-center">
        <%-- 저장된 세션값이 없으면 로그인, 회원가입이 상단바에 보이도록 --%>
          <%-- <% if(session.getAttribute("user_id") == null){ %> --%>
          <a href="/jsp/login.jsp" class="btn btn-secondary me-2">로그인</a>
          <a href="/jsp/signup_form.jsp" class="btn btn-success opacity-75 me-2"
            >회원가입</a
          >
          <a href="/userlist" class="btn btn-secondary me-2">회원목록</a>
          <%-- 관리자 로그인 회원관리 상단바에 보이도록 --%>
          <%-- <% } else if(session.getAttribute("user_id").equals("admin")) { %> --%>
          <%-- <a href="/admin/userList" class="btn btn-secondary me-2">회원관리</a> --%>
          <%-- <a href="/login/logoutServlets" class="btn btn-success opacity-75" --%>
            <%-- >로그아웃</a --%>
          <%-- > --%>
          <%-- 저장된 세션값이 있으면 로그아웃만 상단바에 보이도록 --%>
          <%-- <% } else { %> --%>
          <%-- <a href="/login/logoutServlets" class="btn btn-secondary me-2">로그아웃</a> --%>
          <%-- <% } %> --%>
        </div>
      </div>
    </div>
  </nav>
</header>
