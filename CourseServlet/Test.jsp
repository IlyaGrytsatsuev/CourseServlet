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
    <link href="Style1.css" rel="stylesheet" type="text/css">

</head>
<body>
    <script src="PagesSwitcher.js"></script>
  <%
    int page_count = 1;
    HttpSession s = (HttpSession)request.getAttribute("session");
    String name = (String)session.getAttribute("name");
  
    if(name=="Admin"){
    %>
       <a href="LogOut">Logout</a> |
       <a href="NewItem.html">Add New</a>
      <hr>

    <%} else {
    %>
      <a href="LoginLink.html">Login</a> |
      <a href="Cart">Cart</a>

      <hr>
    <%}
    %>
  

  <% LinkedHashMap<Integer, String> items = (LinkedHashMap<Integer, String>)request.getAttribute("items");
     LinkedHashMap<Integer, Integer> ItemNum = (LinkedHashMap<Integer, Integer>)request.getAttribute("itemNum");
     LinkedHashMap<Integer, Integer> ItemPrice = (LinkedHashMap<Integer, Integer>)request.getAttribute("itemPrice");
     LinkedHashMap<Integer, String> FilePaths = (LinkedHashMap<Integer, String>)request.getAttribute("FilePaths");
     
     Set<Integer> keySet = items.keySet();
     List<Integer> listKeys = new ArrayList<Integer>(keySet);
     

     int count = 0;
     int pages_num = 0;
     int size = 0;
     if((items.keySet().size())%4 == 0 )
       pages_num = (items.keySet().size())/4 ;
     else 
       pages_num = (items.keySet().size())/4 + 1;

      
      if(pages_num == 1)
         size = items.keySet().size();
       
      else
         size = 4;
        
     
      if(items.size()!=0){
      %>
        <body onload="ShowFirst(<%=pages_num%>); ShowNavButtons();">
     <% 
      int i = 0; 
      int j = 0;
      //int size = 0;
      %>
      <%for(; i<pages_num; i++){
      %>
          <div id="<%=i+1%>">
          <%
          for(; j < size; j++){
          %>
            <dl class="product-wrapper">

              <div id="<%=listKeys.get(j)%>Image" class="image" >
                <img src= "<%=FilePaths.get(listKeys.get(j))%>" alt="Item">
              </div>

              <dt id="<%=listKeys.get(j)%>Title">  <%=items.get(listKeys.get(j))%></dt>
              <dd id="<%=listKeys.get(j)%>Info">  Price: <%=ItemPrice.get(listKeys.get(j))%>     Available: <%=ItemNum.get(listKeys.get(j))%></dd>
        
        <div id="<%=listKeys.get(j)%>Buttons">
         <% if(name=="Admin"){
         %>
            <input type="button" class="button" id="<%=listKeys.get(j)%>Button1" value="Delete" onclick="Delete(<%=listKeys.get(j)%>)"/>
          <%}else{
          %>
            <input type="button" class="button" id="<%=listKeys.get(j)%>Button2" value="Add to cart" onclick="AddToCart(<%=listKeys.get(j)%>, <%=ItemPrice.get(listKeys.get(j))%>, <%=ItemNum.get(listKeys.get(j))%>)" />
        <%}
        %>
        </div>
           </dl>
         <% }
          %>
       </div>
          <%
          if(i == pages_num-2)
            size+= items.keySet().size() - (pages_num-1)*4;
          else
            size+=4;
          %>
          <%}
      }else{
      %>
        <p>The Store is empty!</p>
      <%}
      %>
    
<%if(pages_num > 1){%>
<div class="Pbutton">
          <input type="button" class="Pbutton1" id="PButton" value="Previous" onclick="Prev()"/>    <input type="button" class="Pbutton2" id="NButton" value="Next" onclick="Next()"/>

</div>
<%}%>
</body>
</html>

