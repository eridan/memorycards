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
        <c:forEach items="${model.cardGroups}" var="group">
            <tr>
                <td class="groupName">${group.groupName}</td>
                <td width="1" bgcolor="#008000"><BR></td>
                <td><a href="manageGroups.do?edit=${group.id}">Edit</a>&nbsp;<a href="manageGroups.do?delete=${group.id}">Delete</a>&nbsp;<a href="manageGroups.do?create=true">Create</a></td>
            </tr>
        </c:forEach>
    </table>
    
    <c:if test="${model.cardGroupToEdit != null}">
        <br />
        <form action="manageGroups.do?actionupdate=${model.cardGroupToEdit.id}" method="POST">
            <table id="aTable">
                <tr>
                    <td>Name</td>
                    <td><input type="email" name="email" size="25" placeholder="${model.cardGroupToEdit.groupName}" /></td>
                </tr>
                <tr>
                    <td>Description</td>
                    <td><input type="password" name="password" size="25" placeholder="${model.cardGroupToEdit.description}" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input class="button" type="submit" id="submit" value="<fmt:message key="buttonUpdate"/>"/>
                        <input class="button" type="reset" name="reset" id="reset" value="<fmt:message key="buttonReset"/>"/>
                    </td>
                </tr>
            </table>
        </form>
    </c:if>

    <c:if test="${model.cardGroupToDelete != null}">
        <br />
        <form action="manageGroups.do?actiondelete=${model.cardGroupToDelete.id}" method="POST">
            <table id="aTable">
                <tr>
                    <td><fmt:message key="loginEmail"/></td>
                    <td>${model.cardGroupToDelete.email}</td>
                </tr>
                <tr>
                    <td><fmt:message key="cardGroupFName"/></td>
                    <td>${model.cardGroupToDelete.fName}</td>
                </tr>
                <tr>
                    <td><fmt:message key="cardGroupLName"/></td>
                    <td>${model.cardGroupToDelete.lName}</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input class="button" type="submit" name="actiondelete=${model.cardGroupToEdit.id}" id="submit" value="<fmt:message key="buttonDelete"/>"/>
                    </td>
                </tr>
            </table>
        </form>
    </c:if>

    <c:if test="${model.cardGroupToCreate != null}">
        <br />
        <form action="manageGroups.do?actioncreate" method="POST">
            <table id="aTable">
                <tr>
                    <td><fmt:message key="loginEmail"/></td>
                    <td><input type="email" name="email" size="25" required placeholder="<fmt:message key="loginEmailPlaceholder"/>" /></td>
                </tr>
                <tr>
                    <td><fmt:message key="loginPassword"/></td>
                    <td><input type="password" name="password" size="25" required placeholder="<fmt:message key="loginPasswordPlaceholder"/>"/></td>
                </tr>
                <tr>
                    <td><fmt:message key="cardGroupFName"/></td>
                    <td><input type="text" name="cardGroupFName" size="25" required placeholder="<fmt:message key="cardGroupFNamePlaceholder"/>"/></td>
                </tr>
                <tr>
                    <td><fmt:message key="cardGroupLName"/></td>
                    <td><input type="text" name="cardGroupLName" size="25" required placeholder="<fmt:message key="cardGroupLNamePlaceholder"/>"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input class="button" type="submit" name="actioncreate" id="submit" value="<fmt:message key="buttonCreate"/>"/>
                        <input class="button" type="reset" name="reset" id="reset" value="<fmt:message key="buttonReset"/>"/>
                    </td>
                </tr>
            </table>
        </form>
    </c:if>
</div>

<section id="pageButton"><a href="login.do"><-- Go to Home Page</a></section>
</body>
</html>