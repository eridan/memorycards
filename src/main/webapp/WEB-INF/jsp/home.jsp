<%-- 
    Document   : home
    Created on : 05-Aug-2011, 15:34:00
    Author     : jurijspe
--%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<head>

    <style type="text/css" title="currentStyle"> 
        @import "css/demo_table.css";
        @import "css/tabs.css";
    </style> 

    <script type="text/javascript" src="JavaScript/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="JavaScript/jquery-ui-1.8.16.custom.min.js"></script>

    <!--    <script type="text/javascript"src="JavaScript/jquery-1.6.1.min.js"></script>-->
    <script type="text/javascript" language="javascript" src="JavaScript/jquery.dataTables.js"></script> 
    <script type="text/javascript" charset="utf-8"> 
        $(function () {
            $('#cardsTable').dataTable( {
                "sPaginationType": "full_numbers",
                "aLengthMenu": [[2, 5, 10, 25, 50, -1], [2, 5, 10, 25, 50, "All"]],
                "bLengthChange": true,
                "bFilter": false,
                "bSort": false,
                "bInfo": false,
                "bAutoWidth": false
            } );
        } );
        
        $(document).ready(function() {
	
            // Tabs
            $('#tabs').tabs();
				
            //hover states on the static widgets
            $('#dialog_link, ul#icons li').hover(
            function() { $(this).addClass('ui-state-hover'); }, 
            function() { $(this).removeClass('ui-state-hover'); }
        );
        });

    </script> 

</head>


<div id="content">

    <!-- Tabs -->
    <div id="tabs">
        <ul>
            <c:forEach items="${user.cardGroups}" var="group">
                <li><a href="#${group.id}">${group.groupName}</a></li>
            </c:forEach>
        </ul>


        <c:forEach items="${user.cardGroups}" var="group" varStatus="status">
            <div id="${group.id}">
                <table class="display" id="cardsTable">
                    <thead> 
                        <tr> 
                            <th>Question</th> 
                            <th>Answer</th>
                        </tr> 
                    </thead> 
                    <tbody>
                        <c:forEach items="${group.cardList}" var="card">
                            <tr class="gradeA">
                                <td>${card.question}<p>${card.questionCode}</p></td>
                                <td>${card.answer}<p>${card.answerCode}</p></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:forEach>
    </div>
</div>

<footer>
    <p>
        <fmt:message key="AllRightsReserved" />
    </p>
</footer>
</body>
</html>
