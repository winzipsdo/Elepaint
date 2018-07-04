<%@ page import="VO.SourceVO" %>
<%@ page import="java.util.List" %>
<%@ page import="VO.ElephantVO" %><%--
  Created by IntelliJ IDEA.
  User: neal
  Date: 2017/11/12
  Time: 下午10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String keyword = request.getParameter("keyword");
    List<ElephantVO> elephantList = null;
    if ("".equals(keyword)){
        elephantList = DAO.elephantDAO.getElephantList();
    } else {
        elephantList = DAO.elephantDAO.queryElephantList(keyword);
    }
    String root = request.getContextPath()+"/";
%>
<table>
    <tr>
        <th>File Name</th>
        <th>Process Date</th>
        <th>Country</th>
        <th>Size</th>
        <th>Action</th>
        <th>Alter</th>
    <tr/>
    <%
        for (ElephantVO elephantVO : elephantList) {
    %>

    <tr>
        <td><%=elephantVO.getFilename()%>
        </td>
        <td><%=elephantVO.getElephantdate()%>
        </td>
        <td><%=elephantVO.getCountry()%>
        </td>
        <td><%=elephantVO.getSize()%>
        </td>
        <td><a href="<%=root+"storage/"+elephantVO.getFilename()+".png"%>" download="<%=elephantVO.getFilename()%>.png">Download</a>
        </td>
        <td class="delete" data-filename = "<%=elephantVO.getFilename()%>">Delete
        </td>
    </tr>

    <%
        }
    %>
</table>

<script>
    $(document).ready(function () {
        $(".delete").click(function () {
            if (confirm("sure ?")){
                var filename = this.getAttribute("data-filename");
                $.get("delElephant.do?filename="+filename,function (data) {
                    $("#query").click();
                });
            }
        });
    });
</script>
