<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
      
  <div id="ht-preloader">
    <div class="loader clear-loader"> <img class="img-fluid" src="${pageContext.request.contextPath}/assets/images/loader.gif" alt=""> </div>
  </div>
  
  <!--header start-->
  <header class="site-header header-5">
    <div class="header-top bg-blue pt-1">
      <div class="container">
        <div class="col-md-12">
          <div class="row justify-content-end">
            <div class="language-selection font-w-3">
              <div class="d-flex align-items-center justify-content-center justify-content-md-end">
                
                <div class="lng_dropdown">
                  <select name="countries" class="custome_select" onchange="location.href=this.value;">
                    <option value='${pageContext.request.contextPath}/faq/index?locale=zh_CN' data-image="${pageContext.request.contextPath}/assets/images/cn.png" data-title="China"><spring:message code='zh' /></option>
                    <option value='${pageContext.request.contextPath}/faq/index?locale=en_US' data-image="${pageContext.request.contextPath}/assets/images/eng.png" data-title="English"><spring:message code='en' /></option>
                  </select>
                </div>
              </div>
            </div>
            <ul class="header_list">
              <c:choose> 
				   <c:when test="${userName == null}">
				      <li><a href="${pageContext.request.contextPath}/user/login"><i class="icon fa fa-user"></i><span><spring:message code='login' /></span></a></li>
				      <li><a href="${pageContext.request.contextPath}/user/signup"><i class="icon fa fa-user-plus"></i><span><spring:message code='signup' /></span></a></li>
				   </c:when>
				   <c:otherwise>
				   		<li><a href="${pageContext.request.contextPath}/account"><i class="icon fa fa-user"></i><span>${userName}</span></a></li>
              			<li><a href="${pageContext.request.contextPath}/cart/view"><i class="icon fa fa-shopping-cart"></i><span><spring:message code='shoppingcart' /></span></a></li>
				        <li><a href="${pageContext.request.contextPath}/user/logout"><i class="icon fa fa-power-off"></i><span><spring:message code='logout' /></span></a></li>
				   </c:otherwise>
		        </c:choose>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="py-md-4 py-4">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-md-12 col-lg-3 d-none d-md-flex align-items-center"> <a class="navbar-brand logo d-none d-lg-block" href="index.html"> <img class="img-fluid" src="assets/images/logo3.png" alt=""> </a> </div>
          <div class="col-md-12 col-lg-9 col-sm-12 col-12">
            <div class="right-nav align-items-center d-flex justify-content-end">
              <form class="form-inline w-100">
                <select class="custom-select rounded-left form-control d-none d-lg-inline d-md-inline">
                  <option selected value="0"><spring:message code='allcategory' /></option>
                  <c:forEach var="category" items="${topCategoryList}">
                  <option value="${category.id}">${category.name}</option>
                  </c:forEach>
                </select>
                <input class="form-control border-0 rounded-0 border-left col" type="search" placeholder="<spring:message code='keyword' />" aria-label="Search">
                <button class="btn btn-yellow text-white col-auto rounded-right" type="submit"><i class="fa fa-search" aria-hidden="true"></i></i> </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div id="header-wrap" class="shadow-sm">
      <div class="container">
        <div class="row m-0"> 
          <!--menu start-->
          <div class="col p-0">
            <nav class="navbar navbar-expand-lg navbar-light position-static"> <a class="navbar-brand logo d-lg-none" href="index.html"> <img class="img-fluid" src="assets/images/logo3.png" alt=""> </a>
              <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-expanded="false" aria-label="Toggle navigation"> <span class="navbar-toggler-icon"></span> </button>
              <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                  <li class="nav-item"> <a class="nav-link active" href="${pageContext.request.contextPath}/index"><spring:message code='index' /></a></li>
                  <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/category/all"><spring:message code='category' /></a></li>
                  <li class="nav-item"> <a class="nav-link" href="miaosha.jsp"><spring:message code='miaosha' /></a></li>
                  <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/blog/list"><spring:message code='tuijian' /></a></li>
                  <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/faq"><spring:message code='faq' /></a></li>
                  <li class="nav-item"> <a class="nav-link" href="#"> </a></li>
                </ul>
              </div>
            </nav>
          </div>
          <!--menu end--> 
        </div>
      </div>
    </div>
  </header>  
  <!--header end--> 