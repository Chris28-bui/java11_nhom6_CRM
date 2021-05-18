<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="cybersoft.java11.crm.utils.UrlConst" %>
<!-- Breadcrumb -->
<title>Role Dashboard</title>
<div class="container page__heading-container">
    <div class="page__heading">
        <div class="d-flex align-items-center">
            <div>
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb mb-0">
                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.HOME %>" />">Home</a></li>
                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.PROJECT_DASHBOARD %>" />">Project</a></li>
                        <li class="breadcrumb-item active" aria-current="page">
                            Edit project
                        </li>
                    </ol>
                </nav>
                <h1 class="m-0">Edit project</h1>
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
                <p><strong class="headings-color">Project naming convention</strong></p>
                <p class="text-muted">Date format should be in: YYYY-MM-DD</p>
            </div>
            <div class="col-lg-8 card-form__body card-body">
                <form action='<c:url value="<%=UrlConst.PROJECT_UPDATE%>" />' method="post">
                	<div class="form-group">
                		<label for="id">Project ID:</label>
                        <input type="text" class="form-control" id="id" name="project-id" value="${project.id}" readonly>
                    </div>
                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input type="text" class="form-control" id="name" name="project-name" value="${project.name }">
                    </div>
                    <div class="form-group">
                        <label for="description">Description:</label>
                        <input type="text" class="form-control" id="description" name="project-description"  value="${project.description }">
                    </div>
                     <div class="form-group">
                        <label for="description">End date:</label>
                        <input type="text" class="form-control" id="end_date" name="project-end-date" value="${project.end_date }">
                    </div>
                    <button type="submit" class="btn btn-lg btn-primary">Update</button>
                </form>
            </div>
        </div>
    </div>
</div>