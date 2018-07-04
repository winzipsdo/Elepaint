<%@ page import="VO.SourceVO" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: neal
  Date: 2017/11/6
  Time: 下午3:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>source</title>
</head>
<body>

<div>
    <input type="radio" name="fileType" value="source" checked="checked">Source
    <input type="radio" name="fileType" value="elephant">Elephant
</div>

<div id="querybar">
    <input id="querytext" type="text" style="width: 800px;">&nbsp;<input id="query" type="button" value="Query">
</div>
<div id="result">
    <%
        List<SourceVO> sourceList = DAO.sourceDAO.getSourceList();
        String root = request.getContextPath()+"/";
    %>
    <table>
        <tr>
            <th>File Name</th>
            <th>Upload Date</th>
            <th>Action</th>
            <th>Alter</th>
        <tr/>
        <%
            for (SourceVO sourceVO : sourceList) {
        %>

        <tr>
            <td><%=sourceVO.getFileName()%>
            </td>
            <td><%=sourceVO.getDate()%>
            </td>
            <td><a href="<%=root+"upload/"+sourceVO.getFileName()+".png"%>" download="<%=sourceVO.getFileName()%>.png">Download</a>
            </td>
            <td class="delete" data-filename = "<%=sourceVO.getFileName()%>">Delete
            </td>
        </tr>

        <%
            }
        %>
    </table>
</div>

<script src="js/jquery.min.js"></script>
<script>
    $(document).ready(function () {
        var queryText = document.getElementById("querytext");

        $("#query").click(function () {

            var fileType = document.getElementsByName("fileType");
            var type = "source";
            for (var i = 0; i < fileType.length; i++){
                if (fileType[i].checked === true){
                    type = fileType[i].value;
                }
            }

            if (type === "source"){
                $("#result").load("AJAX/sourceQueryAJAX.jsp?keyword="+queryText.value);
            } else if (type === "elephant"){
                $("#result").load("AJAX/elephantQueryAJAX.jsp?keyword="+queryText.value);
            }

        });

        queryText.addEventListener("keydown",function (e) {
            if (e.keyCode == 13){
                $("#query").click();
            }
        });

        $(".delete").click(function () {
            if (confirm("Sure?")){
                var filename = this.getAttribute("data-filename");
                $.get("delSource.do?filename="+filename,function (data) {
                    $("#query").click();
                });
            }
        });
    });
</script>
</body>
</html>
