<%-- 
    Document   : manageUsers
    Created on : 07-Sep-2011, 10:55:13
    Author     : jurijspe
--%>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<div id="content">

    <h1>Group Cards</h1>

    <c:choose>
        <c:when test="${empty model.cardList}">
            <section id="warning">No Cards? You can create them here ...</section>
            <a id="pageButton" href="#">Create</a></td>
        </c:when>
        <c:otherwise>
        <table id="aTable">
            <thead> 
                <tr> 
                    <th>Question</th> 
                    <th>Answer</th>
                </tr> 
            </thead> 
            <tbody>
                <c:forEach items="${model.cardList}" var="card">
                    <tr>
                        <td>${card.question}<p>${card.questionCode}</p></td>
                        <td>${card.answer}<p>${card.answerCode}</p></td>
                        <td>
                            <a href="#">Edit</a>&nbsp;
                            <a href="#">Delete</a>&nbsp;
                            <a href="#">Create</a>&nbsp;
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>

</div>

<section id="pageButton"><a href="login.do"><-- Go to Home Page</a></section>
</body>
</html>
