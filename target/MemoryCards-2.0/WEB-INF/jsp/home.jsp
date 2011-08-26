<%-- 
    Document   : home
    Created on : 05-Aug-2011, 15:34:00
    Author     : jurijspe
--%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>


<div id="content">
    <div id="mainContent">
        <section id="dt_example" class="example_alt_pagination">
            <div id="container">
                <table cellpadding="0" cellspacing="0" border="1" class="display" id="example">
                    <thead> 
                        <tr> 
                            <th>Question</th> 
                            <th>Answer</th> 
                        </tr> 
                    </thead>
                    <tbody>
                        <c:forEach items="${user.cardGroups}" var="group">
                            <c:forEach items="${group.cardList}" var="card">
                                <tr class="gradeA">
                                    <td>${card.question}<br />${card.questionCode}</td>
                                    <td>${card.answer}<br />${card.answerCode}</td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
    </div>
    <aside>
        <section id="listCardGroups">
            <c:forEach items="${user.cardGroups}" var="group">
                <table>
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
                </table>
            </c:forEach>
        </section>
    </aside>
</div>

<footer>
    <p>
        <fmt:message key="AllRightsReserved" />
    </p>
</footer>
</body>
</html>
