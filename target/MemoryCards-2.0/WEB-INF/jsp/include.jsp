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
        <style type="text/css" title="currentStyle"> 
            @import "css/demo_page.css";
            @import "css/demo_table.css";
        </style>
        <script type="text/javascript"src="JavaScript/jquery-1.6.1.min.js"></script>
        <script type="text/javascript" language="javascript" src="JavaScript/jquery.dataTables.js"></script> 
        <script type="text/javascript" charset="utf-8"> 
            $(document).ready(function() {
                $('#example').dataTable( {
                    "sPaginationType": "full_numbers",
                    "aLengthMenu": [[2, 5, 10, 25, 50, -1], [2, 5, 10, 25, 50, "All"]],
                    "bLengthChange": true,
                    "bFilter": false,
                    "bSort": false,
                    "bInfo": false,
                    "bAutoWidth": false
                } );
            } );
        </script> 
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
                <section>
                    <li><a href="index.do">Logout</a></li>
                </section>
            </ul>
        </nav>

