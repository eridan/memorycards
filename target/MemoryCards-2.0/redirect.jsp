<%--
Views should be stored under the WEB-INF folder so that
they are not accessible except through controller process.

This JSP is here to provide a redirect to the dispatcher
servlet but should be the only JSP outside of WEB-INF.

Optional:

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% response.sendRedirect("index.do"); %>
--%>

<%@ include file="/WEB-INF/jsp/include.jsp" %> 
<c:redirect url="/index.do"/>
