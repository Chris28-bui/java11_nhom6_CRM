
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="cybersoft.java11.crm.utils.UrlConst" %>
<!-- Breadcrumb -->
<title>Task Dashboard</title>
<div class="container page__heading-container">
    <div class="page__heading">
        <div class="d-flex align-items-center">
            <div>
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb mb-0">
                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.HOME %>" />">Home</a></li>
                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.TASK_DASHBOARD %>" />">Task</a></li>
                        <li class="breadcrumb-item active" aria-current="page">
                            Add new task
                        </li>
                    </ol>
                </nav>
                <h1 class="m-0">Add new task</h1>
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
                <p><strong class="headings-color">Task naming convention</strong></p>
                <p class="text-muted">Task name should be capitalized.</p>
                <p class="text-muted">Date should be inputted in the format (YYYY-MM-DD)</p>
                <p class="text-muted">Assignee, Project_id and Status should be input as number</p>
            </div>
            <div class="col-lg-8 card-form__body card-body">
                <form action='<c:url value="<%=UrlConst.TASK_ADD%>" />' method="post">
                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input type="text" class="form-control" id="name" name="task-name">
                    </div>
                    <div class="form-group">
                        <label for="description">Description:</label>
                        <input type="text" class="form-control" id="description" name="task-description">
                    </div>
                    <div class="form-group">
                        <label for="description">Start Date:</label>
                        <input type="text" class="form-control" id="startDate" name="task-start-date">
                    </div>
                    <div class="form-group">
                        <label for="description">End Date:</label>
                        <input type="text" class="form-control" id="endDate" name="task-end-date">
                    </div>
                    <div class="form-group">
                        <label for="description">Assignee:</label>
                        <input type="text" class="form-control" id="assignee" name="task-assignee">
                    </div>
                    <div class="form-group">
                        <label for="description">Project ID:</label>
                        <input type="text" class="form-control" id="projectId" name="task-project-id">
                    </div>
                    <div class="form-group">
                        <label for="description">Status:</label>
                        <input type="text" class="form-control" id="status" name="task-status">
                    </div>
                    <button type="submit" class="btn btn-lg btn-primary">Add</button>
                </form>
            </div>
        </div>
    </div>
</div>