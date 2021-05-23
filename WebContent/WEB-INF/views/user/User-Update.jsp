
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="cybersoft.java11.crm.utils.UrlConst" %>
<!-- Breadcrumb -->
<title>User Insert</title>
<div class="container page__heading-container">
    <div class="page__heading">
        <div class="d-flex align-items-center">
            <div>
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb mb-0">
                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.HOME %>" />">Home</a></li>
                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.USER_DASHBOARD %>" />">User</a></li>
                        <li class="breadcrumb-item active" aria-current="page">
                            Add new user
                        </li>
                    </ol>
                </nav>
                <h1 class="m-0">Add new user</h1>
            </div>
            
        </div>
    </div>
</div>
<!-- End Breadcrumb -->
<div class="container page__container">
	<div class="card card-form">
		<c:if test="${msg != null }">
			<div class="alert alert-dismissible bg-danger text-white border-0 fade show" role="alert">
	            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
	                <span aria-hidden="true">&times;</span>
	            </button>
	            <strong>Danger - </strong> A simple danger alert - check it out!
	        </div>
		</c:if>
        <div class="row no-gutters">
            <div class="col-lg-4 card-body">
                <p><strong class="headings-color">Role naming convention</strong></p>
                <p class="text-muted">Role name should be capitalized.</p>
            </div>
            <div class="col-lg-8 card-form__body card-body">
                <form action='<c:url value="<%=UrlConst.USER_UPDATE%>" />' method="post">
                    <div class="form-group">
                       
                        <input type="text" class="form-control" id="fullname" name="id" value="${user.id}" hidden>
                    </div>
                    <div class="form-group">
                        <label for="name">Full Name:</label>
                        <input type="text" class="form-control" id="fullname" name="fullname" value="${user.fullname}">
                    </div>
                    <div class="form-group">
                        <label for="description">Email:</label>
                        <input type="text" class="form-control" id="email" name="email" value="${user.email}">
                    </div>
                    <div class="form-group">
                        <label for="description">Password:</label>
                        <input type="text" class="form-control" id="password" name="password" value="${user.password}">
                    </div>
                    <div class="form-group">
                        <label for="description">Address:</label>
                        <input type="text" class="form-control" id="address" name="address" value="${user.address}">
                    </div>
                    <div class="form-group">
                        <label for="description">Phone:</label>
                        <input type="text" class="form-control" id="phone" name="phone" value="${user.phone}">
                    </div>
                    <div class="form-group">
                        <label for="description">Role:</label>
                        <select id="select01" value="${user.role.id }" name="role_id" data-toggle="select" class="form-control select2-hidden-accessible" data-select2-id="select01" tabindex="-1" aria-hidden="true">
                                    <option value="${user.role.id }">${user.role.name }</option>
                                    <c:forEach items="${roles}" var="role">
                                    
                                    <option value="${role.id }">${role.name}</option>
                                    
                                  </c:forEach>
                                  
                                </select>
                          <%-- <input type="text" class="form-control" id="role_id" name="role_id" value="${user.role.id}"> --%>   
                    </div>
                    <button type="submit" class="btn btn-lg btn-primary">Add</button>
                </form>
            </div>
        </div>
    </div>
</div>