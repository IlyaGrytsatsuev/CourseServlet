
package Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.LinkedHashMap;
import javax.servlet.http.Cookie;
import Model.*;

public class ItemsPageServlet extends HttpServlet {
    private ItemsManager items;

    public void init(ServletConfig config) {
        try {
            items = new ItemsManager();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            items.readFile();
            String tmp = request.getParameter("num");
            String state = request.getParameter("state");
            int num = Integer.parseInt(tmp);
            LinkedHashMap<Integer, Integer> tmp2 = items.getItemsNum();
            
            if(state.equals("add")) {
                HttpSession session = request.getSession(false);
                ArrayList<Integer> CartItems = new ArrayList<>();
                
                Cookie[] cookies = request.getCookies();
                if(cookies !=null){
                    for(Cookie cookie : cookies){
                        if(cookie.getName().startsWith("Cart"))
                            CartItems.add(Integer.parseInt(cookie.getValue()));
                    }
                }
                if(cookies !=null){
                    for(Cookie cookie : cookies){
                        if(cookie.getName().startsWith("Cart"))
                            cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
                if(CartItems.size() == 0) {
                    Cookie CartCookie = new Cookie("Cart0", "" + num);
                    CartCookie.setMaxAge(30 * 60);
                    response.addCookie(CartCookie);
                }
                else{
                    CartItems.add(num);
                    for(int i = 0; i < CartItems.size(); i++){
                        Cookie CartCookie = new Cookie("Cart" + i, "" + CartItems.get(i));
                        CartCookie.setMaxAge(30 * 60);
                        response.addCookie(CartCookie);
                    }
                }
                
                    items.edit(num, tmp2.get(num) - 1);
            }
            else if(state.equals("delete")){
                items.delete(num);
            }
            
            items.writeFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        try{

                items.readFile();
                LinkedHashMap<Integer, String> tmp1 = items.getItems();
                LinkedHashMap<Integer, Integer> tmp2 = items.getItemsNum();
                LinkedHashMap<Integer, Integer> tmp3 = items.getItemsPrices();
                LinkedHashMap<Integer, String> tmp4 = items.getFilePaths();
                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(30*60);
                
                request.setAttribute("session", session);
                request.setAttribute("items", tmp1);
                request.setAttribute("itemNum", tmp2);
                request.setAttribute("itemPrice", tmp3);
                request.setAttribute("FilePaths", tmp4);
                RequestDispatcher rd = request.getRequestDispatcher("/src/View/Items.jsp");
                rd.forward(request, response);


        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
