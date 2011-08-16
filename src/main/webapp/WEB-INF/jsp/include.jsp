<%-- 
    Document   : include
    Created on : 11-Aug-2011, 13:13:07
    Author     : jurijspe
--%>
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
            <h1><fmt:message key="title"/></h1>
        </header>
        <nav>
            <ul>
                <li><a href="#"><fmt:message key="navHelp"/></a></li>
                <li><a href="#"><fmt:message key="navAbout"/></a></li>
                <li><a href="#"><fmt:message key="navContact"/></a></li>
                <li><a href="index.do"><fmt:message key="navLogout"/></a></li>
            </ul>
        </nav>
