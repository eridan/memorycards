<%@ include file="/WEB-INF/jsp/include.jsp" %>

<div id="content">
    <div id="mainContent">
        <span id="msg"><fmt:message key="welcomeMsg"/></span>
    </div>
    <aside>
        <form id="loginForm" action="login.do" method="POST">
            <table>
                <tr>
                    <td><fmt:message key="loginEmail"/></td>
                    <td><input type="email" name="email" size="25" required placeholder="<fmt:message key="loginEmailPlaceholder"/>" /></td>
                </tr>
                <tr>
                    <td><fmt:message key="loginPassword"/></td>
                    <td><input type="password" name="password" size="25" required placeholder="<fmt:message key="loginPasswordPlaceholder"/>"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input class="loginButton" type="submit" name="submit" id="submit" value="<fmt:message key="loginButtonLogin"/>"/></td>
                </tr>
            </table>
        </form>
    </aside>
</div>

<footer>
    <p><fmt:message key="AllRightsReserved"/></p>
</footer>
</body>
</html>