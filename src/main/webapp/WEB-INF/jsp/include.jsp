<%-- 
    Document   : include
    Created on : 11-Aug-2011, 13:13:07
    Author     : jurijspe
--%>
<!DOCTYPE HTML>
<%@ include file="/WEB-INF/jsp/include_libs.jsp"%>

<html>
    <head>
        <c:if test="${fn:containsIgnoreCase(header['User-Agent'],'Windows') || 
                      fn:containsIgnoreCase(header['User-Agent'],'Unix') ||
                      fn:containsIgnoreCase(header['User-Agent'],'Linux')}">
              <link rel="stylesheet" type="text/css" href="css/fc_basic.css" />
        </c:if>
        <c:if test="${
              fn:containsIgnoreCase(header['User-Agent'],'iPod') || 
                  fn:containsIgnoreCase(header['User-Agent'],'iPhone') ||
                  fn:containsIgnoreCase(header['User-Agent'],'Android')
              }">
            <link rel="stylesheet" type="text/css" href="css/mob_fc_basic.css" />
        </c:if>
        <title><fmt:message key="title"/></title>
        <link rel="stylesheet" MEDIA="handheld" type="text/css" href="css/mob_fc_basic.css" />
    </head>

    <body>
        <header>
            <!--            <span style="float: right">
                                <a href="?lang=en">en</a> 
                                | 
                                <a href="?lang=ru"><fmt:message key="changeLangRu"/></a>
                        </span>-->
            <h1><fmt:message key="title"/></h1>
            <section>${user.fName} ${user.lName}</section>
        </header>

        <nav>
            <ul>
                <li><a href="#"><fmt:message key="navHelp"/></a></li>
                <li><a href="#"><fmt:message key="navAbout"/></a></li>
                <li><a href="#"><fmt:message key="navContact"/></a></li>
                <li id="Extras"><a href="#"><fmt:message key="navExtras"/></a>
                    <ul>
                        <li><a href="#"><fmt:message key="navManUsers"/></a></li>
                        <li><a href="manageGroups.do"><fmt:message key="navManGroups"/></a></li>
                        <li><a href="#"><fmt:message key="navManCards"/></a></li>
                        <li><a href="#"><fmt:message key="navRunTest"/></a></li>
                    </ul>
                </li>
                <span>
                    <li id="Logout"><a href="index.do">Logout</a></li>
                </span>
            </ul>
        </nav>