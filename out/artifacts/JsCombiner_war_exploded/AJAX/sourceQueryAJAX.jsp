<%@ page import="VO.SourceVO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: neal
  Date: 2017/11/9
  Time: 下午6:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <%
        String keyword = request.getParameter("keyword");
        List<SourceVO> sourceList = null;
        if ("".equals(keyword)){
            sourceList = DAO.sourceDAO.getSourceList();
        } else {
            sourceList = DAO.sourceDAO.querySourceList(keyword);
        }
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

<script>
    $(document).ready(function () {
        $(".delete").click(function () {
            if (confirm("sure ?")){
                var filename = this.getAttribute("data-filename");
                $.get("delSource.do?filename="+filename,function (data) {
                    $("#query").click();
                });
            }
        });
    });
</script>
