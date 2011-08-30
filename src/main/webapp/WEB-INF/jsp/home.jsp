<%-- 
    Document   : home
    Created on : 05-Aug-2011, 15:34:00
    Author     : jurijspe
--%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>


<div id="content">
    <div id="mainContent">
        <table>
            <c:forEach items="${user.cardGroups}" var="group">
                <c:forEach items="${group.cardList}" var="card">
                    <tr>
                        <td>${card.question}<p>${card.questionCode}</p></td>
                        <td>${card.answer}<p>${card.answerCode}</p></td>
                    </tr>
                </c:forEach>
            </c:forEach>
        </table>
    </div>
    <aside>
        <table>
            <c:forEach items="${user.cardGroups}" var="group">
                <tr>
                    <td>
                        <a href="#">${group.groupName}</a>
                    </td>
                    <td><a href="#">Delete</a>&nbsp;<a href="#">Update</a>&nbsp;<a href="#">Create</a>
                    </td>
                </tr>
                <tr>
                    <td>${group.groupName}</td>
                </tr>
            </c:forEach>
        </table>
    </aside>
</div>

<footer>
    <p>
        <fmt:message key="AllRightsReserved" />
    </p>
</footer>
</body>
</html>
