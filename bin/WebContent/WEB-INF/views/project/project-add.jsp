
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="cybersoft.java11.crm.utils.UrlConst" %>
<!-- Breadcrumb -->
<title>Project Dashboard</title>
<div class="container page__heading-container">
    <div class="page__heading">
        <div class="d-flex align-items-center">
            <div>
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb mb-0">
                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.HOME %>" />">Home</a></li>
                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.PROJECT_DASHBOARD %>" />">Project</a></li>
                        <li class="breadcrumb-item active" aria-current="page">
                            Add new project
                        </li>
                    </ol>
                </nav>
                <h1 class="m-0">Add new project</h1>
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
                <p><strong class="headings-color">Date format</strong></p>
                <p class="text-muted">Date format should be in: YYYY-MM-DD</p>
            </div>
            <div class="col-lg-8 card-form__body card-body">
                <form action='<c:url value="<%=UrlConst.PROJECT_ADD%>" />' method="post">
                	<!-- <div class="form-group">
                		<label for="id">ID:</label>
                		<input type="text" class="form-control" id="id" name="project-id">
                	</div> -->
                    <div class="form-group">
                        <label for="description">Name:</label>
                        <input type="text" class="form-control" id="description" name="project-name">
                    </div>
                    <div class="form-group">
                        <label for="name">Description</label>
                        <input type="text" class="form-control" id="name" name="project-description">
                    </div>
                    <div class="form-group">
                        <label for="name">Start date:</label>
                        <input type="text" class="form-control" id="name" name="project-start-date">
                    </div>
                    <div class="form-group">
                        <label for="name">End date:</label>
                        <input type="text" class="form-control" id="name" name="project-end-date">
                    </div>
                    <div class="form-group">
                        <label for="name">User create project: </label>
                        <input type="text" value=<%=request.getAttribute("userId") %> class="form-control" readonly id="name" name="project-user-create">
                    </div>
                    <button type="submit" class="btn btn-lg btn-primary">Add</button> 
                </form>
            </div>
        </div>
    </div>
</div>