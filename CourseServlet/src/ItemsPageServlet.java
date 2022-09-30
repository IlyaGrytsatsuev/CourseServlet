import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.LinkedHashMap;


public class ItemsPageServlet extends HttpServlet {
    private ItemsManager items;

    public void init(ServletConfig config) {
        try {
            items = new ItemsManager();
            //items.readFile();
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
            Set<Integer> keySet = tmp2.keySet();
            List<Integer> listKeys = new ArrayList<Integer>(keySet);

            if(state.equals("add")) {

                if (tmp2.get(num) - 1 == 0)
                    items.delete(num);

                else
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
                HttpSession session = request.getSession(false);
                request.setAttribute("session", session);
                request.setAttribute("items", tmp1);
                request.setAttribute("itemNum", tmp2);
                request.setAttribute("itemPrice", tmp3);
                request.setAttribute("FilePaths", tmp4);
                RequestDispatcher rd = request.getRequestDispatcher("Test.jsp");
                rd.forward(request, response);


        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
