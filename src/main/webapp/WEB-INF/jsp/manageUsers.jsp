<%-- 
    Document   : manageUsers
    Created on : 07-Sep-2011, 10:55:13
    Author     : jurijspe
--%>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<div id="content">

    <h1>Application Users</h1>

    <table id="aTable">
        <th>User Name</th>
        <c:forEach items="${model.userList}" var="user">
            <tr>
                <td class="groupName">${user.email}</td>
                <td>${user.fName}&nbsp;${user.lName}</td>
                <td>
                    <a href="manageUsers.do?edit=${user.id}">Edit</a>&nbsp;
                    <a href="manageUsers.do?delete=${user.id}">Delete</a>&nbsp;
                    <a href="manageUsers.do?create=true">Create</a>&nbsp;
                    <a href="manageGroups.do?addGroup=${user.id}">Add Group</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <c:if test="${model.userToEdit != null}">
        <section id="warning">Please manage Users with extreme care, your changes can not be undone ...</section>
        <br />
        <form action="manageUsers.do?actionupdate=${model.userToEdit.id}" method="POST">
            <table id="aTable">
                <tr>
                    <td><fmt:message key="loginEmail"/></td>
                    <td><input type="email" name="email" size="25" value="${model.userToEdit.email}" placeholder="${model.userToEdit.email}" /></td>
                </tr>
                <tr>
                    <td><fmt:message key="loginPassword"/></td>
                    <td><input type="password" name="password" size="25" value="${model.userToEdit.password}" placeholder="password? blank - unchanged"/></td>
                </tr>
                <tr>
                    <td><fmt:message key="userFName"/></td>
                    <td><input type="text" name="userFName" size="25" value="${model.userToEdit.fName}" placeholder="${model.userToEdit.fName}"/></td>
                </tr>
                <tr>
                    <td><fmt:message key="userLName"/></td>
                    <td><input type="text" name="userLName" size="25" value="${model.userToEdit.lName}" placeholder="${model.userToEdit.lName}"/></td>
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

    <c:if test="${model.userToDelete != null}">
        <section id="warning">Please manage Users with extreme care, your changes can not be undone ...</section>
        <br />
        <form action="manageUsers.do?actiondelete=${model.userToDelete.id}" method="POST">
            <table id="aTable">
                <tr>
                    <td><fmt:message key="loginEmail"/></td>
                    <td>${model.userToDelete.email}</td>
                </tr>
                <tr>
                    <td><fmt:message key="userFName"/></td>
                    <td>${model.userToDelete.fName}</td>
                </tr>
                <tr>
                    <td><fmt:message key="userLName"/></td>
                    <td>${model.userToDelete.lName}</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input class="button" type="submit" name="actiondelete=${model.userToEdit.id}" id="submit" value="<fmt:message key="buttonDelete"/>"/>
                    </td>
                </tr>
            </table>
        </form>
    </c:if>

    <c:if test="${model.userToCreate != null}">
        <br />
        <form action="manageUsers.do?actioncreate" method="POST">
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
                    <td><fmt:message key="userFName"/></td>
                    <td><input type="text" name="userFName" size="25" required placeholder="<fmt:message key="userFNamePlaceholder"/>"/></td>
                </tr>
                <tr>
                    <td><fmt:message key="userLName"/></td>
                    <td><input type="text" name="userLName" size="25" required placeholder="<fmt:message key="userLNamePlaceholder"/>"/></td>
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
