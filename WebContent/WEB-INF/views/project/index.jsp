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
                        <li class="breadcrumb-item active" aria-current="page">
                            Project
                        </li>
                    </ol>
                </nav>
                <h1 class="m-0">Project Dashboard</h1>
            </div>
            <div class="ml-auto">
            	<c:choose>
            		<c:when test="${roleId.id == 'manager'}">
            			<a href="<c:url value="<%=UrlConst.PROJECT_ADD %>" />" class="btn btn-light"><i class="material-icons icon-16pt text-muted mr-1">add</i>
    						Add</a>
            		</c:when>
            		<c:otherwise>
            			<a href="#" class="btn btn-light"><i class="material-icons icon-16pt text-muted mr-1">add</i>
    						Add</a>
            		</c:otherwise>
            	</c:choose>
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
					<th class="col-1">
						ID
					</th>
					<th class="col-2">
						Name
					</th>
					<th class="col-2">
						Description
					</th>
					<th class="col-2">
						Start date
					</th>
					<th class="col-2">
						End date
					</th>
					<th class="col-3">
						Action
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${projects}" var="project">
					<tr class="row">
						<td class="col-1">
							${project.id}
						</td>
						<td class="col-2">
							${project.name}
						</td>
						<td class="col-2">
							${project.description}
						</td>
						<td class="col-2">
							${project.start_date}
						</td>
						<td class="col-2">
							${project.end_date}
						</td>
						<td class="col-3 button-list d-flex flex-wrap">
	                        <c:choose>
	                        	<c:when test="${roleId.name=='manager'}">
	                        		<a href="<c:url value="<%=UrlConst.PROJECT_UPDATE %>" />?id=${project.id}" class="btn btn-primary">
	                           			<i class="material-icons">edit</i>
	                        		</a>
	                        		<a href="<c:url value="<%=UrlConst.PROJECT_DELETE %>" />?id=${project.id}" class="btn btn-danger">
	                            		<i class="material-icons">delete_forever</i>
	                        		</a>
	                        	</c:when>
	                        	<c:otherwise>
	                        		<a class="btn btn-primary">
	                           			<i class="material-icons">edit</i>
	                        		</a>
	                        		<a class="btn btn-danger">
	                            		<i class="material-icons">delete_forever</i>
	                        		</a>
	                        	</c:otherwise>
	                        </c:choose>
	                        	<a href="<c:url value="<%=UrlConst.TASK_DASHBOARD %>"/>?id=${project.id}" class="btn btn-success">
	                        		<i class="material-icons material-icons-outlined">assignment</i>
	                        	</a>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${projects == null}">
					<tr>
						<td>There is no data.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
</div>