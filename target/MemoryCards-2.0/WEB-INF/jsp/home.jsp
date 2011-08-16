<%-- 
    Document   : home
    Created on : 05-Aug-2011, 15:34:00
    Author     : jurijspe
--%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>


        <div id="content">
            <div id="mainContent">
                <section><h1>Hello ${user.userName}!</h1></section>
            </div>
            
        </div>

        <footer>
            <p><fmt:message key="AllRightsReserved"/></p>
        </footer>
    </body>
</html>
