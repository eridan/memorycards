<!DOCTYPE HTML>

<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/fc_basic.css" />
        <title><fmt:message key="title"/></title>
    </head>

    <body>
        <header>
            <!--            <span style="float: right">
                                <a href="?lang=en">en</a> 
                                | 
                                <a href="?lang=ru"><fmt:message key="changeLangRu"/></a>
                        </span>-->
            <section id="loginTitle"><span>ME</span>morize<span>ME</span></section>
        </header>
        <nav>
            <ul>
                <li><a href="#"><fmt:message key="navHelp"/></a></li>
                <li><a href="#"><fmt:message key="navAbout"/></a></li>
                <li><a href="#"><fmt:message key="navContact"/></a></li>
                <section>
                    <li><a href="index.do">Logout</a></li>
                </section>
            </ul>
        </nav>
        <div id="content">
            <div id="mainContent">
                <span><fmt:message key="welcomeMsg"/></span>
            </div>
            <aside>
                    <form action="login.do" method="POST">
                        <table>
                            <tr>
                                <td><fmt:message key="loginEmail"/></td>
                                <td><input type="email" name="email" size="23" required placeholder="<fmt:message key="loginEmailPlaceholder"/>" /></td>
                            </tr>
                            <tr>
                                <td><fmt:message key="loginPassword"/></td>
                                <td><input type="password" name="password" size="23" required placeholder="<fmt:message key="loginPasswordPlaceholder"/>"/></td>
                            </tr>
                        </table>

                        <p><input type="submit" name="submit" id="submit" value="<fmt:message key="loginButtonLogin"/>"/>
                            <input type="reset" name="reset" id="reset" value="<fmt:message key="loginButtonReset"/>"/>
                        </p>
                    </form>
            </aside>
        </div>

        <footer>
            <p><fmt:message key="AllRightsReserved"/></p>
        </footer>
    </body>
</html>