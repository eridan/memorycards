<%-- 
    Document   : manageUsers
    Created on : 07-Sep-2011, 10:55:13
    Author     : jurijspe
--%>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<div id="content">

    <h1>Group Cards</h1>

    <c:choose>
        <c:when test="${model.cardToEdit != null}">
            <br />
            <form action="manageCards.do?action=update&id=${model.cardToEdit.id}" method="POST">
                <section id="warning">Please manage Cards with extreme care, your changes can not be undone ...</section>
                <br />
                <table id="aTable">
                    <tr>
                        <td>Question</td>
                        <td><input type="text" name="question" size="25" required value="${model.cardToEdit.question}" placeholder="${model.cardToEdit.question}"/></td>
                    </tr>
                    <tr>
                        <td>Question Code</td>
                        <td>
                            <textarea wrap="hard" rows="2" cols="26" placeholder="${model.cardToEdit.questionCode}">${model.cardToEdit.questionCode}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>Answer</td>
                        <td><input type="text" name="answer" size="25" required value="${model.cardToEdit.answer}" placeholder="${model.cardToEdit.answer}"/></td>
                    </tr>
                    <tr>
                        <td>Answer Code</td>
                        <td>
                            <textarea wrap="hard" rows="2" cols="26" placeholder="${model.cardToEdit.answerCode}">${model.cardToEdit.answerCode}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input class="button" type="submit" id="submit" value="<fmt:message key="buttonUpdate"/>"/>
                            <input class="button" type="reset" name="reset" id="reset" value="<fmt:message key="buttonReset"/>"/>
                        </td>
                    </tr>
                </table>
            </form>
        </c:when>
        <c:when test="${model.cardToDelete != null}">
            <form action="manageCards.do?action=delete&id=${model.cardToDelete.id}" method="POST">
                <section id="warning">Please manage Cards with extreme care, your changes can not be undone ...</section>
                <br />
                <table id="aTable">
                    <tr>
                        <td>Card Question</td>
                        <td>${model.cardToDelete.question}</td>
                    </tr>
                    <tr>
                        <td>Answer</td>
                        <td>${model.cardToDelete.answer}</td>
                    </tr>
                    <tr>
                        <td>
                            <input class="button" type="submit" id="submit" value="<fmt:message key="buttonDelete"/>"/>
                        </td>
                    </tr>
                </table>
            </form>
        </c:when>
        <c:when test="${model.cardToCreate != null}">
            <form action="manageCards.do?action=create" method="POST">
                <br />
                <table id="aTable">
                    <tr>
                        <td>Question</td>
                        <td><input type="text" name="question" size="25" required placeholder="Question"/></td>
                    </tr>
                    <tr>
                        <td>Question Code</td>
                        <td><input type="" name="questionCode" size="25" required placeholder="Question Code"/></td>
                    </tr>
                    <tr>
                        <td>Answer</td>
                        <td><input type="text" name="answer" size="25" required placeholder="Answer"/></td>
                    </tr>
                    <tr>
                        <td>Answer Code</td>
                        <td><input type="textbox" name="answerCode" size="25" required placeholder="Answer Code"/></td>
                    </tr>
                    <tr>
                        <td>
                            <input class="button" type="submit" id="submit" value="<fmt:message key="buttonUpdate"/>"/>
                            <input class="button" type="reset" name="reset" id="reset" value="<fmt:message key="buttonReset"/>"/>
                        </td>
                    </tr>
                </table>
            </form>
        </c:when>
    </c:choose>

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
                            <a href="manageCards.do?form=edit&id=${card.id}">Edit</a>&nbsp;
                            <a href="manageCards.do?form=delete&id=${card.id}">Delete</a>&nbsp;
                            <a href="manageCards.do?form=create">Create</a>&nbsp;
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
