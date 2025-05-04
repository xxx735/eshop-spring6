<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title><spring:message code='signup_title' /></title>
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.png" />
	<link href="${pageContext.request.contextPath}/assets/css/theme-plugin.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/assets/css/theme.min.css" rel="stylesheet" />
  <link href="${pageContext.request.contextPath}/assets/css/bootstrapValidator.min.css" rel="stylesheet" />
</head>

<body class="bg-light-4">

<!-- page wrapper start -->

<div class="page-wrapper"> 
  
    <%@ include file="header.jsp" %>
	<!--body content start-->

<div class="page-content">

<!--login start-->

<section class="bg-light register">
  <div class="container">
<div class="row justify-content-center text-center">
  <div class="col-10 col-lg-7 col-md-9 shadow p-6 bg-white">
   <div class="col-lg-12 col-md-12 p-0">
        <div class="mb-6">
           <h2 class="font-w-6"><spring:message code='signup' /></h2>
          <p><spring:message code='welcome' /> </p>
        </div>
        </div>
      <div class="col-lg-12 col-md-12 ml-auto mr-auto p-0">
        <div class="register-form text-center">
          <form id="register-form" method="post" action="${pageContext.request.contextPath}/user/signup">
            <div class="messages"></div>
            <div class="row">
              <div class="col-md-12">
                <div class="form-group">
                  <input id="form_name" type="text" name="username" class="form-control" placeholder="<spring:message code='username' />" required data-error="<spring:message code='must' />" >
                  <div class="help-block with-errors"></div>
                  <div class="col-sm-6 text-danger"><form:errors path="user.username"/></div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <input id="form_email" type="email" name="email" class="form-control" placeholder="<spring:message code='email' />" required data-error="<spring:message code='must' />">
                  <div class="help-block with-errors"></div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <input id="form_phone" type="tel" name="phone" class="form-control" placeholder="<spring:message code='phone' />" required data-error="<spring:message code='must' />">
                  <div class="help-block with-errors"></div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <input id="form_password" type="password" name="password" class="form-control" placeholder="<spring:message code='password' />" required data-error="password是必填字段">
                  <div class="help-block with-errors"></div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <input id="form_password1" type="password" name="repassword" class="form-control" placeholder="<spring:message code='repassword' />" required data-error="重输password是必填字段">
                  <div class="help-block with-errors"></div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-12">
                <input type="submit" class="btn btn-primary" value="<spring:message code='signup' />"/><br>
                <!--  <a href="SignupServlet" class="btn btn-primary" onclick="document.getElementById('register-form').submit();">注册</a>  -->
                <span class="mt-4 d-block">已有账号? <a href="${pageContext.request.contextPath}/user/login"><spring:message code='tologin' /></a></span>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
    </div>
  </div>
</section>

	<!--login end-->
	</div>
	<!--body content end--> 
  <%@ include file="footer.jsp" %>
  <script src="${pageContext.request.contextPath}/assets/js/signup-validator.js" ></script>
  </div>
</body>
</html>
