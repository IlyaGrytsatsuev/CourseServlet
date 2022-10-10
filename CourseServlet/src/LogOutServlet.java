import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class LogOutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }

        HttpSession session = request.getSession(false);
        if(session!= null) {
            session.invalidate();
        }
        
        
        PrintWriter out=response.getWriter();
        out.println("<html><body>");
        //request.getRequestDispatcher("StyleLink.html").include(request, response);

        request.getRequestDispatcher("LoggedOutPanel.html").include(request, response);
        out.println("You are successfully logged out!");

        out.println("</html></body>");

        out.close();
    }

}
