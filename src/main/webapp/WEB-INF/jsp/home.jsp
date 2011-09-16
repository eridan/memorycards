<%@ include file="/WEB-INF/jsp/include.jsp"%>

<html>
    <head>

        <c:if test="${fn:containsIgnoreCase(header['User-Agent'],'Windows') || 
                      fn:containsIgnoreCase(header['User-Agent'],'Unix') ||
                      fn:containsIgnoreCase(header['User-Agent'],'Linux')}">
              <style type="text/css" title="currentStyle">
                  @import "css/tabs.css";
                  @import "css/demo_table.css";
              </style>
        </c:if>
        <c:if test="${
              fn:containsIgnoreCase(header['User-Agent'],'iPod') || 
                  fn:containsIgnoreCase(header['User-Agent'],'iPhone') ||
                  fn:containsIgnoreCase(header['User-Agent'],'Android')
              }">
            <link rel="stylesheet" type="text/css" href="css/mob_home.css" />
        </c:if>


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
            <c:choose>
                <c:when test="${empty user.cardGroups}">
                    <section id="warning">No Card Groups yet created. Please go to Manage Groups (Extras -> Manage Groups) to Create them ...</section>
                </c:when>
                <c:otherwise>

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
                                    <caption>${group.description}</caption>
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
                </c:otherwise>
            </c:choose>

        </div>

        <div id="mobileView">
            <c:forEach items="${user.cardGroups}" var="group">
                <table>
                    <thead>
                    <th colspan="2">${group.groupName}</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${group.cardList}" var="card">
                            <tr class="question">
                                <td><b>Question: </b>${card.question}<p>${card.questionCode}</p></td>
                            </tr>
                            <tr class="answer">
                                <td><b>Answer: </b>${card.answer}<p>${card.answerCode}</p></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:forEach>
        </div>

        <footer>
            <p>
                <fmt:message key="AllRightsReserved" />
            </p>
        </footer>
    </body>
</html>
