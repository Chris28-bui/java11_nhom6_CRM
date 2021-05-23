<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="cybersoft.java11.crm.utils.UrlConst" %>
<!-- Breadcrumb -->
<title>User Dashboard</title>
<div class="container page__heading-container">
    <div class="page__heading">
        <div class="d-flex align-items-center">
            <div>
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb mb-0">
                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.HOME %>" />">Home</a></li>
                        <li class="breadcrumb-item active" aria-current="page">
                            User
                        </li>
                    </ol>
                </nav>
                <h1 class="m-0">User Dashboard</h1>
            </div>
            <div class="ml-auto">
                <a href="<c:url value="<%=UrlConst.USER_ADD %>" />" class="btn btn-light"><i class="material-icons icon-16pt text-muted mr-1">add</i>
    Add</a>
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
					<th class="col-2">
						Fullname
					</th>
					<th class="col-2">
						Email
					</th>
					<th class="col-2">
						Password
					</th>
					<th class="col-2">
						Address
					</th>
					<th class="col-2">
						Phone
					</th>
					<th class="col-1">
						Role
					</th>
					<th class="col-1">
						Action
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr class="row">
						<td class="col-2">
							${user.fullname}
						</td>
						<td class="col-2">
							${user.email}
						</td>
						<td class="col-2">
							${user.password}
						</td>
						<td class="col-2">
							${user.address}
						</td>
						<td class="col-2">
							${user.phone}
						</td>
						<td class="col-1">
							${user.role.name}
						</td>
						<td class="col-1 button-list d-flex flex-wrap">
							<a href="<c:url value="<%=UrlConst.USER_UPDATE %>" />?id=${user.id}" class="btn btn-primary">
	                            <i class="material-icons">edit</i>
	                        </a>
	                        <a href="<c:url value="<%=UrlConst.USER_DELETE %>" />?id=${user.id}" class="btn btn-danger">
	                            <i class="material-icons">delete_forever</i>
	                        </a>
	                        <%-- <c:choose>
	                        	<c:when test="${roleId.id == 1 }">
	                        		<a href="<c:url value="<%=UrlConst.ROLE_UPDATE %>" />?id=${role.id}" class="btn btn-primary">
	                           			<i class="material-icons">edit</i>
	                        		</a>
	                        		<a href="<c:url value="<%=UrlConst.ROLE_DELETE %>" />?id=${role.id}" class="btn btn-danger">
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
	                        </c:choose> --%>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${users == null}">
					<tr>
						<td>There is no data.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
</div>