<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>user manager</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="${pageContext.request.contextPath}/jqueryminiui/scripts/boot.js" type="text/javascript"></script>
  </head>
  
  <body>

    <div style="width:800px;">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:100%;">
                        <a class="mini-button" iconCls="icon-add" onclick="add()">����</a>
                        <a class="mini-button" iconCls="icon-add" onclick="edit()">�༭</a>
                        <a class="mini-button" iconCls="icon-remove" onclick="remove()">ɾ��</a>       
                    </td>
                    <td style="white-space:nowrap;">
                        <input id="name" class="mini-textbox" emptyText="����������" style="width:150px;" onenter="onKeyEnter"/>   
                        <a class="mini-button" onclick="search()">��ѯ</a>
                    </td>
                </tr>
            </table>           
        </div>
    </div>
    <div id="datagrid1" class="mini-datagrid" style="width:800px;height:280px;" allowResize="true"
        url="${pageContext.request.contextPath}/user/getPageList.action"  idField="id" multiSelect="true" 
    >
        <div property="columns">
            <!--<div type="indexcolumn"></div>        -->
            <div type="checkcolumn" ></div>       
            <div field="ID" width="120" headerAlign="center" allowSort="true">id</div>   
            <div field="NAME" width="120" headerAlign="center" allowSort="true">����</div>    
            <div field="PASSWORD" width="120" headerAlign="center" allowSort="true">����</div>    
        </div>
    </div>
    

    <script type="text/javascript">
        mini.parse();

        var grid = mini.get("datagrid1");
        grid.load();
        grid.sortBy("id", "desc");

        
        function add() {
            
            mini.open({
                url: bootPATH + "../demo/CommonLibs/EmployeeWindow.html",
                title: "����Ա��", width: 600, height: 360,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = { action: "new"};
                    iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {

                    grid.reload();
                }
            });
        }
        function edit() {
         
            var row = grid.getSelected();
            if (row) {
                mini.open({
                    url: bootPATH + "../demo/CommonLibs/EmployeeWindow.html",
                    title: "�༭Ա��", width: 600, height: 360,
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        var data = { action: "edit", id: row.id };
                        iframe.contentWindow.SetData(data);
                        
                    },
                    ondestroy: function (action) {
                        grid.reload();
                        
                    }
                });
                
            } else {
                alert("��ѡ��һ����¼");
            }
            
        }
        function remove() {
            
            var rows = grid.getSelecteds();
            if (rows.length > 0) {
                if (confirm("ȷ��ɾ��ѡ�м�¼��")) {
                    var ids = [];
                    for (var i = 0, l = rows.length; i < l; i++) {
                        var r = rows[i];
                        ids.push(r.id);
                    }
                    var id = ids.join(',');
                    grid.loading("�����У����Ժ�......");
                    $.ajax({
                        url: "../data/AjaxService.jsp?method=RemoveEmployees&id=" +id,
                        success: function (text) {
                            grid.reload();
                        },
                        error: function () {
                        }
                    });
                }
            } else {
                alert("��ѡ��һ����¼");
            }
        }
        function search() {
            var name = mini.get("name").getValue();
            grid.load({ name: name });
        }
        function onKeyEnter(e) {
            search();
        }
        /////////////////////////////////////////////////
        function onBirthdayRenderer(e) {
            var value = e.value;
            if (value) return mini.formatDate(value, 'yyyy-MM-dd');
            return "";
        }
        function onMarriedRenderer(e) {
            if (e.value == 1) return "��";
            else return "��";
        }
        var Genders = [{ id: 1, text: '��' }, { id: 2, text: 'Ů'}];        
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }





    </script>
  </body>
</html>
