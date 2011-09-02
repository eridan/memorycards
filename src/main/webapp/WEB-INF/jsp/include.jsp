<%-- 
    Document   : include
    Created on : 11-Aug-2011, 13:13:07
    Author     : jurijspe
--%>
<!DOCTYPE HTML>
<%@ include file="/WEB-INF/jsp/include_libs.jsp"%>

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
            <h1><fmt:message key="title"/></h1>
            <section><h1>${user.fName} ${user.lName}</h1></section>
        </header>
        
        <nav>
            <ul>
                <li><a href="#"><fmt:message key="navHelp"/></a></li>
                <li><a href="#"><fmt:message key="navAbout"/></a></li>
                <li><a href="#"><fmt:message key="navContact"/></a></li>
                <li><a href="#"><fmt:message key="navExtras"/></a>
                    <ul id="Extras">
                        <li><a href="#"><fmt:message key="navManUsers"/></a></li>
                        <li><a href="#"><fmt:message key="navManGroups"/></a></li>
                        <li><a href="#"><fmt:message key="navManCards"/></a></li>
                        <li><a href="#"><fmt:message key="navRunTest"/></a></li>
                    </ul>
                </li>
                <span>
                    <li><a href="index.do">Logout</a></li>
                </span>
            </ul>
        </nav>

