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


public class CartServlet extends HttpServlet {
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
            LinkedHashMap<Integer, Integer> tmp2 = items.getItemsNum();
            ArrayList<Integer> CartItems = new ArrayList<>();
            String tmp = request.getParameter("num");
            String state = request.getParameter("state");
            int num = Integer.parseInt(tmp);
            //session = request.getSession(false);
            Cookie[] cookies = request.getCookies();

            if(state.equals("delete")) {

                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().startsWith("Cart"))
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

                CartItems.remove(Integer.valueOf(num));
                items.edit(num, tmp2.get(num) + 1);

                if(CartItems.size()!=0) {
                    for (int i = 0; i < CartItems.size(); i++) {
                        Cookie CartCookie = new Cookie("Cart" + i, "" + CartItems.get(i));
                        CartCookie.setMaxAge(30 * 60);
                        response.addCookie(CartCookie);
                    }
                }

            }

            if(state.equals("buy")) {

                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().startsWith("Cart"))
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

                if(tmp2.get(num) == 0)
                    items.delete(num);

                CartItems.remove(Integer.valueOf(num));

                for (int i = 0; i < CartItems.size(); i++) {
                    Cookie CartCookie = new Cookie("Cart" + i, "" + CartItems.get(i));
                    CartCookie.setMaxAge(30 * 60);
                    response.addCookie(CartCookie);
                }
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
            LinkedHashMap<Integer, String> tmp2 = items.getFilePaths();
            LinkedHashMap<Integer, Integer> tmp3 = items.getItemsPrices();
            HttpSession session = request.getSession(false);
            request.setAttribute("session", session);
            request.setAttribute("items", tmp1);
            request.setAttribute("FilePaths", tmp2);
            request.setAttribute("Prices", tmp3);
            RequestDispatcher rd = request.getRequestDispatcher("/src/View/Cart.jsp");
            rd.forward(request, response);
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
