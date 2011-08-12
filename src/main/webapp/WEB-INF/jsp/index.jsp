<%@ include file="/WEB-INF/jsp/include.jsp" %>

        <div id="content">
            <div id="mainContent">
                <section><fmt:message key="welcomeMsg"/></section>
            </div>
            <aside>
                <section id="loginForm">
                    <form action="login.do" method="get">
                        <table>
                            <tr>
                                <td><fmt:message key="loginEmail"/></td>
                                <td><input type="email" name="email" size="25" required placeholder="<fmt:message key="loginEmailPlaceholder"/>" /></td>
                            </tr>
                            <tr>
                                <td><fmt:message key="loginPassword"/></td>
                                <td><input type="password" name="password" size="25" required placeholder="<fmt:message key="loginPasswordPlaceholder"/>"/></td>
                            </tr>
                        </table>

                        <p><input type="submit" name="submit" id="submit" value="<fmt:message key="loginButtonLogin"/>"/>
                            <input type="reset" name="reset" id="reset" value="<fmt:message key="loginButtonReset"/>"/>
                        </p>
                    </form>
                </section>
            </aside>
        </div>

        <footer>
            <p><fmt:message key="AllRightsReserved"/></p>
        </footer>
    </body>
</html>