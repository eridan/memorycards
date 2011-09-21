<%-- 
    Document   : manageCardGroups
    Created on : 06-Sep-2011, 14:01:50
    Author     : jurijspe
--%>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<head>
    <style type="text/css" title="currentStyle">
        @import "css/tabs.css";
        @import "css/demo_table.css";
    </style>

    <script type="text/javascript" src="JavaScript/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="JavaScript/jquery-ui-1.8.16.custom.min.js"></script>
    <script type="text/javascript" language="javascript" src="JavaScript/jquery.dataTables.js"></script> 

    <script type="text/javascript" charset="utf-8"> 
        $(document).ready(function() {
            $('.display').dataTable( {
                "sPaginationType": "full_numbers",
                "aLengthMenu": [[2, 5, 10, 25, 50, -1], [2, 5, 10, 25, 50, "All"]],
                "bLengthChange": true,
                "bFilter": false,
                "bSort": false,
                "bInfo": false,
                "bAutoWidth": false
            } );
        });
    </script>
</head>

<div id="content">

    <h1>Card Groups</h1>

    <c:choose>
        <c:when test="${!empty model.cardGroups}">

            <table id="aTable">
                <th>Name</th>
                <th>Description</th>
                <th>Creation Date</th>
                <th>Update Date</th>
                <c:forEach items="${model.cardGroups}" var="group">
                    <tr>
                        <td class="groupName">${group.groupName}</td>
                        <td>${group.description}</td>
                        <td class="date">${group.creationDate}</td>
                        <td class="date">${group.updateDate}</td>
                        <td>
                            <a href="manageGroups.do?form=edit&id=${group.id}">Edit</a>&nbsp;
                            <a href="manageGroups.do?form=delete&id=${group.id}">Delete</a>&nbsp;
                            <a href="manageGroups.do?form=create">Create</a>&nbsp;
                            <a href="manageCards.do?form=manageCards&id=${group.id}">Manage Cards</a></td>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <section id="warning">No Card Groups? You can create them here ...</section>
            <a id="pageButton" href="manageGroups.do?action=create">Create</a></td>
        </c:otherwise>
    </c:choose>

<c:if test="${model.cardGroupToEdit != null}">
    <br />
    <form action="manageGroups.do?action=update&id=${model.cardGroupToEdit.id}" method="POST">
        <section id="warning">Please manage groups with extreme care, your changes can not be undone ...</section>
        <br />
        <table id="aTable">
            <tr>
                <td><fmt:message key="groupName"/></td>
                <td><input type="text" name="groupName" size="25" required value="${model.cardGroupToEdit.groupName}" placeholder="${model.cardGroupToEdit.groupName}"/></td>
            </tr>
            <tr>
                <td><fmt:message key="groupDesc"/></td>
                <td><input type="text" name="description" size="25" required value="${model.cardGroupToEdit.description}" placeholder="${model.cardGroupToEdit.description}"/></td>
            </tr>
            <tr>
                <td>
                    <input class="button" type="submit" id="submit" value="<fmt:message key="buttonUpdate"/>"/>
                    <input class="button" type="reset" name="reset" id="reset" value="<fmt:message key="buttonReset"/>"/>
                </td>
            </tr>
        </table>
    </form>
    <h1 style="text-align: center"><b><em>"${model.cardGroupToEdit.groupName}"</em> Cards</b></h1>
    <table class="display" id="cardsTable">
        <thead> 
            <tr> 
                <th>Question</th> 
                <th>Answer</th>
            </tr> 
        </thead> 
        <tbody>
            <c:forEach items="${model.cardGroupToEdit.cardList}" var="card">
                <tr class="gradeA">
                    <td>${card.question}<p>${card.questionCode}</p></td>
                    <td>${card.answer}<p>${card.answerCode}</p></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>

<c:if test="${model.cardGroupToDelete != null}">
    <br />
    <section id="warning">Please manage groups with extreme care, your changes can not be undone ...</section>
    <form action="manageGroups.do?action=delete&id=${model.cardGroupToDelete.id}" method="POST">
        <table id="aTable">
            <tr>
                <td><fmt:message key="groupName"/></td>
                <td>${model.cardGroupToDelete.groupName}</td>
            </tr>
            <tr>
                <td><fmt:message key="groupDesc"/></td>
                <td>${model.cardGroupToDelete.description}</td>
            </tr>
            <tr>
                <td><fmt:message key="groupCrDate"/></td>
                <td>${model.cardGroupToDelete.creationDate}</td>
            </tr>
            <tr>
                <td><fmt:message key="groupUpdDate"/></td>
                <td>${model.cardGroupToDelete.updateDate}</td>
            </tr>
            <td>
                <input class="button" type="submit" name="submit" id="submit" value="<fmt:message key="buttonDelete"/>"/>
            </td>
            </tr>
        </table>
    </form>
    <br />
    <h1 style="text-align: center"><b><em>"${model.cardGroupToDelete.groupName}"</em> Cards</b></h1>
    <table class="display" id="cardsTable">
        <thead> 
            <tr> 
                <th>Question</th> 
                <th>Answer</th>
            </tr> 
        </thead> 
        <tbody>
            <c:forEach items="${model.cardGroupToDelete.cardList}" var="card">
                <tr class="gradeA">
                    <td>${card.question}<p>${card.questionCode}</p></td>
                    <td>${card.answer}<p>${card.answerCode}</p></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>

<c:if test="${model.cardGroupToCreate != null}">
    <br />
    <form action="manageGroups.do?action=create" method="POST">
        <table id="aTable">
            <tr>
                <td><fmt:message key="groupName"/></td>
                <td><input type="text" name="groupName" size="25" placeholder="Group Name" /></td>
            </tr>
            <tr>
                <td><fmt:message key="groupDesc"/></td>
                <td><input type="text" name="description" size="25" placeholder="Description" /></td>
            </tr>
            <td>
                <input class="button" type="submit" name="submit" id="submit" value="<fmt:message key="buttonCreate"/>"/>
                <input class="button" type="reset" name="reset" id="reset" value="<fmt:message key="buttonReset"/>"/>
            </td>
            </tr>
        </table>
    </form>
</c:if>

<c:if test="${displayCards != null}">
    <br />

    <section id="pageButton"><a href="login.do"><-- Go to Home Page</a></section>
</c:if>


</div>

<section id="pageButton"><a href="login.do"><-- Go to Home Page</a></section>
</body>
</html>