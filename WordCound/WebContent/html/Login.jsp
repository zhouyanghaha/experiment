<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Login</title>
<link href="css/register.css" rel="stylesheet" type="text/css">
</head>
<script type="text/javascript">
  /* 获取文件名的函数 */
  function one() {
    var fileName = document.getElementById("file").value;
    location.href = "../WordCountServlet?fileName=" + fileName + "&id=0";
  }
  
  function two() {
		var word = document.getElementById("word").value;
		location.href = "../WordCountServlet?word=" + word + "&id=1";
		
	}
  function three() {
		var wordnum = document.getElementById("wordnum").value;
		location.href = "../WordCountServlet?wordnum=" + wordnum + "&id=2";
	}
  function four() {
		var wordlines = document.getElementById("wordlines").value;
		location.href = "../WordCountServlet?wordlines=" + wordlines + "&id=3";	
	}
  function five() {
		var result = document.getElementById("result").value;
		location.href = "../WordCountServlet?result=" + result + "&id=4";
	}
  
</script>
<%
    TreeMap<String,Integer> map1=(TreeMap)request.getAttribute("map1");//强制转换
    
    ArrayList gaopin =(ArrayList)request.getAttribute("gaopin");
    List list=(List)request.getAttribute("list");
 	
%>
<body>
  <div class="one">
    <h2 align="center">高频单词统计页面</h2>
    <hr width="55%" align="center" color="#ccc"/>
    <form action="#" method="post" enctype="multipart/form-data">
      <table width="60%" border="0" cellspacing="0" cellpadding="0" 
             class="reg" align="center" style="margin-left: 300px; margin: center;">
              <tr>
                <td>选择你要上传的文件“.txt”</td>
                <td>
                  <input type="file" name="file" id="file" />
                  <input type="button" id="butt1" value="确认文件" onclick="one()" /><br />
                  <label >程序统计所需要的时间</label>
                  <input type="text" class="form-control" placeholder="">
                </td>
              </tr>
              <tr>
                <td>请输入指定单词，用逗号“，”隔开</td>
                <td>
                  <input name="word" type="text" id="word" />
                  <input type="submit" name="button" value="提交"  onclick="two()"/>
                  <label style="margin-left: 119px;">程序统计所需要的时间</label>
                  <input type="text" class="form-control" placeholder="" value="${excTime }"></td>
              </tr>
              <tr>
                  <td>高频词统计</td>
                  <td><input name="wordcount" type="text" id="wordcount" value="显示高频词" />
                  <input type="submit" id="butt2" value="统计" onclick="two()" /> 
                  <label style="margin-left: 119px;">程序统计所需要的时间</label>
                  <input type="text" class="form-control" placeholder="" value="${excTime2 }"></td>
              </tr>
              <tr>
                  <td>所有行数以及字符数统计</td>
                  <td><input name="wordcount" type="text" id="wordcount" value="所有单词数量及词频统计" />
                  <input type="submit" name="button" value="提交" />
                  <label style="margin-left: 119px;">所有行数以及字符数统计</label>
                  <input type="text" class="form-control" placeholder="" value="${excTime3 }"></td>
              </tr>
              <tr>
                  <td>存放到result.txt中</td>
                  <td><input name="wordcount" type="text" id="wordcount" value="所有单词数量及词频统计" />
                  <input type="submit" name="button" value="提交" />
                  <label style="margin-left: 119px;">存放文件</label>
                  <input type="text" class="form-control" placeholder="" value="${excTime1 }"></td>
              </tr>
      </table>
    </form>
  </div>
  <table border = 1px align = "center" >
	<tr>
	<td>单词</td>
	<td>数量</td>
	</tr>
	<c:forEach items="${map1}" var="map1" varStatus="st">
	
	<tr  >
	<td>${map1.key}</td>
	<td>${map1.value}</td>
	</tr>
	</c:forEach>
	</table>
	
	<!-- 高频词显示 -->
	<table border = 1px align = "center" id="tb1" >
	<tr>
	<td>单词</td>
	<td>数量</td>
	</tr>
	<c:forEach items="${gaopin}" var="gaopin" varStatus="st">
	<tr  >
	<td>${gaopin.key}</td>
	<td>${gaopin.value}</td>
	</tr>
	</c:forEach>
	</table>
	
	<table>
	<!--行数以及字符数的统计  -->
	  <tr>
	     <td>行数</td><td>字符数</td><td>单词数</td>
	  </tr>
	  <c:forEach items="${list}" var="list" varStatus="st">
	 <tr>
	   <td>${list}</td>
	 </tr>
	 </c:forEach>
	</table>
</body>
</html>
