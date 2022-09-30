<%@ page import="java.util.*" %><%--
  Created by IntelliJ IDEA.
  User: gratchuvalsky
  Date: 09.09.2022
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Items</title>
  </head>
  <body>


  <%
    int page_count = 1;
    HttpSession s = (HttpSession)request.getAttribute("session");
    if(s != null){%>
       <a href="LogOut">Logout</a>
       <a href="NewItem.html">Add New</a>
      <hr>

    <%} else {%>
      <a href="LoginLink.html">Login</a>
      <hr>
    <%}%>

  <% LinkedHashMap<Integer, String> items = (LinkedHashMap<Integer, String>)request.getAttribute("items");
     LinkedHashMap<Integer, Integer> ItemNum = (LinkedHashMap<Integer, Integer>)request.getAttribute("itemNum");
     LinkedHashMap<Integer, Integer> ItemPrice = (LinkedHashMap<Integer, Integer>)request.getAttribute("itemPrice");
     LinkedHashMap<Integer, String> FilePaths = (LinkedHashMap<Integer, String>)request.getAttribute("FilePaths");
     Set<Integer> keySet = items.keySet();
     List<Integer> listKeys = new ArrayList<Integer>(keySet);


     int count = 0;
     int pages_num = 0;
     if((items.keySet().size()+1)%5 == 0)
       pages_num = (items.keySet().size()+1)/5 ;
     else
       pages_num = (items.keySet().size()+1)/5 + 1;%>

      <body onload="ShowFirst(<%=pages_num%>);">

     <% Integer j = 0;
      int size = 5;
        for(int i = 0 ; i < pages_num; i++){
          %>
        <div id="<%=i+1%>">
          <%for(; j < size; j++){%>
          <dl>
            <dt id="<%=j%>Title"> <%=items.get(listKeys.get(j))%></dt>
            <dd id="<%=j%>Info"> Price: <%=ItemPrice.get(listKeys.get(j))%> Available: <%=ItemNum.get(listKeys.get(j))%></dd>
          </dl>
          <% if(s != null){%>
            <input type="button"  value="Delete"/>
        <% }else {%>
          <input type="button"  value="Add to cart" onclick="AddToCart(<%=j%>, <%=ItemPrice.get(listKeys.get(j))%>, <%=ItemNum.get(listKeys.get(j))%>)"/>
        <%}
        }%>
        </div>
        <%size+=5;
        }%>



  <input type="button" id="PButton" value="Previos" onclick="Prev()"/>    <input type="button" id="NButton" value="Next" onclick="Next()"/>
  
    <script src="PagesSwitcher.js"></script>
  </body>
</html>
