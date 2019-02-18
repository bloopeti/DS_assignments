package business.servlets;

import business.commands.LoginCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }

        request.getRequestDispatcher("index.html").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        LoginCommand command = new LoginCommand(username, password);

        Integer status = (Integer) command.execute();

        HttpSession session = request.getSession(true);

        switch (status) {
            case 1:
                response.addCookie(new Cookie("usertype", "admin"));
                session.setAttribute("usertype", "admin");
                response.sendRedirect("/admin");
                break;
            case 0:
                response.addCookie(new Cookie("usertype", "user"));
                session.setAttribute("usertype", "user");
                response.sendRedirect("/user");
                break;
            default:
                response.sendRedirect("/loginerror");
//                request.getRequestDispatcher("loginError.html").forward(request, response);
        }
    }
}
