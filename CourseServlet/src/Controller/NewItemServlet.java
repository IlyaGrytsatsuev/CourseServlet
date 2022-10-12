package Controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import Model.*;

@WebServlet
@MultipartConfig
public class NewItemServlet extends HttpServlet{

    private ItemsManager items;

    public void init(ServletConfig config) {
        try {
            items = new ItemsManager();
            items.readFile();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            String path = "/Users/gratchuvalsky/Desktop/apache-tomcat-8.5.82/webapps/CourseServlet/ServletImages";
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            items.readFile();
            String name = request.getParameter("name");
            int price = Integer.parseInt(request.getParameter("price"));
            int amount = Integer.parseInt(request.getParameter("amount"));
            items.Add(name, price, amount);
            items.writeFile();
            Part filePart = request.getPart("file");
            InputStream fileContent = filePart.getInputStream();
            items.SaveImage(fileContent, name);

            request.getRequestDispatcher("/src/View/NewItem.html").include(request, response);
            out.print("A new item has been added successfully !");
            out.println("</html></body>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        try{

            RequestDispatcher rd = request.getRequestDispatcher("/src/View/NewItem.html");
            rd.forward(request, response);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
