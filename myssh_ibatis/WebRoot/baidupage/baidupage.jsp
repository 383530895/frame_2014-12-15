<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'baidupage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 <style>
        <!--
            body{
                .p{
                    PADDING-LEFT: 18px; FONT-SIZE: 14px; WORD-SPACING: 4px
                }
            }
        -->
        </style>

  </head>
  
  <body>
    <div class="p">
            <%
                int pagesize = 10;//ÿҳ��ʾ��¼��
                int liststep = 20;//�����ʾ��ҳҳ��
                int pages = 1;//Ĭ����ʾ��һҳ
                if (request.getParameter("pages") != null) {
                    pages = Integer.parseInt(request.getParameter("pages"));//��ҳҳ�����
                }
                //sql="select count(*) from table";//ȡ����¼����,��*���ɼ�¼�����ܸ���
                int count = 305;//����ȡ����¼����
                int pagescount = (int) Math.ceil((double) count / pagesize);//����ҳ����ceil��num��ȡ����С��num
                if (pagescount < pages) {
                    pages = pagescount;//�����ҳ��������ҳ�����򽫷�ҳ�������Ϊ��ҳ��
                }
                if (pages < 1) {
                    pages = 1;//�����ҳ����С�ڣ�,�򽫷�ҳ������Ϊ��
                }
                int listbegin = (pages - (int) Math.ceil((double) liststep / 2));//�ӵڼ�ҳ��ʼ��ʾ��ҳ��Ϣ
                if (listbegin < 1) {
                    listbegin = 1;
                }
                int listend = pages + liststep/2;//��ҳ��Ϣ��ʾ���ڼ�ҳ
                if (listend > pagescount) {
                    listend = pagescount + 1;
                }

                //��ʾ���ݲ���
                int recordbegin = (pages - 1) * pagesize;//��ʼ��¼
                int recordend = 0;
                recordend = recordbegin + pagesize;
                //���һҳ��¼��ʾ����
                // if (pages == pagescount) {
                  //  recordend = (int) (recordbegin + pagesize * (count % pagesize) * 0.1);
               //  }
                  if (pages == pagescount && count % pagesize != 0) {//��лshixiangkun�ڲ������������
                       recordend = (int) (recordbegin + (count % pagesize) );
                  }
                //Connection conn = null;//Ҫ������ʾ�������Լ��������ݿ�
                //PreparedStatement stmt = null;
                //String strSql = "select * from usertable limit recordbegin,pagesize";//ȡ����ҳ��¼,SQL�����ݿ���죡
                //stmt = conn.prepareStatement(strSql);
                //ResultSet rs = stmt.executeQuery();
                //while(rs.next())){
                //    response.getWriter().println(""+rs.getString("userId")+"<br>");//��ʾȡ��������
                //}
                for (int i = recordbegin; i < recordend; i++) {
                    response.getWriter().println("record " + i + "<br>");
                }

                //��ʾ���ݲ���

                //<��ʾ��ҳ��Ϣ
                //<��ʾ��һҳ
                if (pages > 1) {
                    response.getWriter().println(
                    "<a href=?pages=" + (pages - 1) + ">��һҳ</a>");
                }//>��ʾ��һҳ
                //<��ʾ��ҳ��
                for (int i = listbegin; i < listend; i++) {
                    if (i != pages) {//���i�����ڵ�ǰҳ
                        response.getWriter().println(
                        "<a href=?pages=" + i + ">[" + i + "]</a>");
                    } else {
                        response.getWriter().println("[" + i + "]");
                    }
                }//��ʾ��ҳ��>
                //<��ʾ��һҳ
                if (pages != pagescount) {
                    response.getWriter().println(
                    "<a href=?pages=" + (pages + 1) + ">��һҳ</a>");
                }//>��ʾ��һҳ
                //>��ʾ��ҳ��Ϣ
            %>
        </div>

  </body>
</html>
