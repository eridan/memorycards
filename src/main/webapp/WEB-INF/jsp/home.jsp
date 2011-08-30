<%-- 
    Document   : home
    Created on : 05-Aug-2011, 15:34:00
    Author     : jurijspe
--%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>


<div id="content">
    <table id="cardsTable">
        <thead> 
            <tr> 
                <th></th> 
                <th></th> 
            </tr> 
        </thead> 
        <tbody>
            <c:forEach items="${user.cardGroups}" var="group">
                <c:forEach items="${group.cardList}" var="card">
                    <tr class="gradeA">
                        <td>${card.question}<p>${card.questionCode}</p></td>
                        <td>${card.answer}<p>${card.answerCode}</p></td>
                    </tr>
                </c:forEach>
            </c:forEach>
        </tbody>
    </table>
</div>

<footer>
    <p>
        <fmt:message key="AllRightsReserved" />
    </p>
</footer>
</body>
</html>
