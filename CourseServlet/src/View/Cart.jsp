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
    <link href="src/View/Style1.css" rel="stylesheet" type="text/css">

</head>
<body>
   <script src="src/View/PagesSwitcher.js"></script>
  <%
    List<Integer> CartList  = new ArrayList<>();
    int page_count = 1;
    HttpSession s = (HttpSession)request.getAttribute("session");
    //@SuppressWarnings("unchecked")
    Cookie[] cookies = request.getCookies();
                if(cookies !=null){
                    for(Cookie cookie : cookies){
                        if(cookie.getName().startsWith("Cart"))
                            CartList.add(Integer.parseInt(cookie.getValue()));
                    }
                }
    //List<Integer> CartList  = (ArrayList<Integer>)s.getAttribute("CartItems");
  %>
      <a href="Items">Home</a> |
      <a href="LoginLink.html">Login</a>
      <hr>

  <% LinkedHashMap<Integer, String> items = (LinkedHashMap<Integer, String>)request.getAttribute("items");
     LinkedHashMap<Integer, Integer> ItemPrice = (LinkedHashMap<Integer, Integer>)request.getAttribute("Prices");
     LinkedHashMap<Integer, String> FilePaths = (LinkedHashMap<Integer, String>)request.getAttribute("FilePaths");
     
     Set<Integer> keySet = items.keySet();
     List<Integer> listKeys = new ArrayList<Integer>(keySet);
     
     int count = 0;
     int pages_num = 0;
     int size = 0;
     if(CartList.size()%4 == 0 )
       pages_num = CartList.size()/4 ;
     else 
       pages_num = CartList.size()/4 + 1;

      
      if(pages_num == 1)
         size = CartList.size();
       
      else
         size = 4;
        
     
      if(CartList.size()!=0){
      %>
      <body onload="ShowFirst(<%=pages_num%>); ShowNavButtons();">
     <% 
      int i = 0; 
      int j = 0;
      %>
      <%for(; i<pages_num; i++){
      %>
          <div id="<%=i+1%>">
      <%
          for(; j < size; j++){
      %>
        <dl class="product-wrapper">

              <div id="<%=CartList.get(j)%>Image" class="image" >
                <img src= "<%=FilePaths.get(CartList.get(j))%>" alt="Item">
              </div>
              <dt id="<%=CartList.get(j)%>Title">  <%=items.get(CartList.get(j))%></dt>
              <dd id="<%=CartList.get(j)%>Info">  Price: <%=ItemPrice.get(CartList.get(j))%></dd>
              <input type="button" class="button" id="<%=CartList.get(j)%>Button1" value="Delete" onclick="DeleteFromCart(<%=CartList.get(j)%>)"/>
              <input type="button" class="button" id="<%=CartList.get(j)%>Button2" value="Buy" onclick="Buy(<%=CartList.get(j)%>)" />

        </dl>
         <% }
          %>
       </div>
          <%
          if(i == pages_num-2)
            size+= CartList.size() - (pages_num-1)*4;
          else
            size+=4;
          %>
          <%}
      }else{
      %>
        <p>The Cart is empty!</p>
      <%}
      %>

<%if(pages_num > 1){%>
      <div class="Pbutton">
          <input type="button" class="Pbutton1" id="PButton" value="Previous" onclick="Prev()"/>    <input type="button" class="Pbutton2" id="NButton" value="Next" onclick="Next()"/>

     </div>
<%}%>
</body>
</html>
