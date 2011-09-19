<%request.getSession(true).invalidate();%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<html>
    <head>
        <c:if test="${fn:containsIgnoreCase(header['User-Agent'],'Windows') || 
                      fn:containsIgnoreCase(header['User-Agent'],'Unix') ||
                      fn:containsIgnoreCase(header['User-Agent'],'Linux')}">
            <link rel="stylesheet" type="text/css" href="css/login.css" />
        </c:if>
        <c:if test="${
              fn:containsIgnoreCase(header['User-Agent'],'iPod') || 
                  fn:containsIgnoreCase(header['User-Agent'],'iPhone') ||
                  fn:containsIgnoreCase(header['User-Agent'],'Android')
              }">
            <link rel="stylesheet" type="text/css" href="css/mob_login.css" />
        </c:if>
        <link rel="stylesheet" media="handheld" type="text/css" href="css/mob_login.css" />
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