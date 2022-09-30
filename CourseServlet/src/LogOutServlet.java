import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class LogOutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        session.invalidate();
        Cookie cookie = new Cookie("username", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);



        PrintWriter out=response.getWriter();
        out.println("<html><body>");
        //request.getRequestDispatcher("StyleLink.html").include(request, response);

        request.getRequestDispatcher("LoggedOutPanel.html").include(request, response);
        out.println("You are successfully logged out!");

        out.println("</html></body>");

        out.close();
    }

}
