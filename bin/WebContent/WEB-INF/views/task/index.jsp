<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="cybersoft.java11.crm.utils.UrlConst"%>
<!-- Breadcrumb -->
<title>Role Dashboard</title>
<div class="container page__heading-container">
	<div class="page__heading">
		<div class="d-flex align-items-center">
			<div>
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb mb-0">
						<li class="breadcrumb-item"><a
							href="<c:url value="<%=UrlConst.HOME%>" />">Home</a></li>
						<li class="breadcrumb-item active" aria-current="page">Task</li>
					</ol>
				</nav>
				<h1 class="m-0">Task Dashboard</h1>
			</div>
			<div class="ml-auto">
				<a href="<c:url value="<%=UrlConst.TASK_ADD%>" />"
					class="btn btn-light"><i
					class="material-icons icon-16pt text-muted mr-1">add</i> Add</a>
			</div>
		</div>
	</div>
</div>
<!-- End Breadcrumb -->
<div class="container page__container">
	<div class="table">
		<table class="table mb-0 thead-border-top-0">
			<thead>
				<tr class="row">
					<th class="col-1">Id</th> 
					<th class="col-2">Name</th>
					<th class="col-2">Start Date</th>
					<th class="col-2">End Date</th>
					<th class="col-1">Assignee</th>
					<th class="col-1">Project ID</th>
					<th class="col-1">Status</th>
					<th class="col-2">Function</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listTask}" var="task">
					<tr class="row">
						<td class="col-1">${task.id }</td> 
						<td class="col-2">${task.name }</td>
						<td class="col-2">${task.start_date }</td>
						<td class="col-2">${task.end_date }</td>
						<td class="col-1">${task.assignee.id }</td>
						<td class="col-1">${task.project_id.id }</td>
						<td class="col-1">${task.status_id.name}</td>
						<td class="col-2 button-list d-flex flex-wrap"><a
							href="<c:url value="<%=UrlConst.TASK_UPDATE %>" />?id=${task.id}"
							class="btn btn-primary"> <i class="material-icons">edit</i>
						</a> <a
							href="<c:url value="<%=UrlConst.TASK_DELETE %>" />?id=${task.id}"
							class="btn btn-danger"> <i class="material-icons">delete_forever</i>
						</a></td>
					</tr>
				</c:forEach>
				<c:if test="${listTask == null}">
					<tr>
						<td>There is no data.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
</div>