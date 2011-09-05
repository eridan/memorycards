<%@ include file="/WEB-INF/jsp/include.jsp"%>

<html>
    <head>

        <style type="text/css" title="currentStyle"> 
            @import "css/tabs.css";
            @import "css/demo_table.css";
        </style>

        <script type="text/javascript" src="JavaScript/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="JavaScript/jquery-ui-1.8.16.custom.min.js"></script>
        <script type="text/javascript" language="javascript" src="JavaScript/jquery.dataTables.js"></script> 
        <script type="text/javascript">
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

        <script type="text/javascript" charset="utf-8"> 
            $(document).ready(function() {
                $('.display').dataTable( {
                    "sPaginationType": "full_numbers",
                    "aLengthMenu": [[2, 5, 10, 25, 50, -1], [2, 5, 10, 25, 50, "All"]],
                    "bLengthChange": true,
                    "bFilter": false,
                    "bSort": false,
                    "bInfo": false,
                    "bAutoWidth": false
                } );
            });
        </script>
    </head>

    <body>
        <div id="content">

            <!-- Tabs -->
            <div id="tabs">
                <ul>
                    <c:forEach items="${user.cardGroups}" var="group">
                        <li><a href="#${group.id}" onClick="javascript:dataTable.reload(true)">${group.groupName}</a></li>
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
