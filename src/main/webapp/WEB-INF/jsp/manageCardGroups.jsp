<%-- 
    Document   : manageCardGroups
    Created on : 06-Sep-2011, 14:01:50
    Author     : jurijspe
--%>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<div id="content">
    
    <section id="warning">Please manage groups with extreme care, your changes can not be undone ...</section>
    
    <table id="aTable">
        <th>Group Name</th>
        <c:forEach items="${cardGroups}" var="group">
            <tr>
                <td class="groupName">${group.groupName}</td>
                <td width="1" bgcolor="#008000"><BR></td>
                <td><a href="#">Edit</a>&nbsp;<a href="#">Delete</a>&nbsp;<a href="#">Create</a></td>
            </tr>
        </c:forEach>
    </table>
</div>

<section id="pageButton"><a href="login.do"><-- Go to Home Page</a></section>
</body>
</html>