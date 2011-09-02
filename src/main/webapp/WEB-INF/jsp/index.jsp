<%@ page session="false"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/login.css" />
    </head>

    <body>
        
        <div id="content">
            <div id="table">
                <div id="mainContent">
                    <span id="msg"><fmt:message key="welcomeMsg"/></span>
                </div>
                <aside>
                    <form action="login.do" method="POST">
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
                                <td><input class="button" type="submit" name="submit" id="submit" value="<fmt:message key="loginButtonLogin"/>"/></td>
                            </tr>
                        </table>
                    </form>
                </aside>
            </div>
        </div>

        <footer>
            <p><fmt:message key="AllRightsReserved"/></p>
        </footer>
    </body>
</html>