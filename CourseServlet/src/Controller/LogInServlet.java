package Controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.Cookie;
import Model.*;


public class LogInServlet extends HttpServlet {

    private AdminList admin;

    public void init(ServletConfig config) {
        try {
            admin = new AdminList();
            admin.readFile();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        if (admin.CheckUser(name, password)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            session = request.getSession(true);
            session.setAttribute("name", "Admin");
            session.setMaxInactiveInterval(30*60);
            Cookie loginCookie = new Cookie("name", "Admin");
            loginCookie.setMaxAge(30*60);
            response.addCookie(loginCookie);
            response.sendRedirect("Items");

        } else {
            request.getRequestDispatcher("/src/View/LoginLink.html").include(request, response);
            out.print("Sorry, username or password is wrong !");

        }
        out.println("</html></body>");
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        try{
            
            RequestDispatcher rd = request.getRequestDispatcher("/src/View/LoginLink.html");
            rd.forward(request, response);
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
