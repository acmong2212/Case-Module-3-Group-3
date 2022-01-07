package controller;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class UserServlet extends HttpServlet {
    UserService userService = new UserService();
    RequestDispatcher requestDispatcher;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String act = req.getParameter("act");
        if (act == null) {
            act = "";
        }
        switch (act) {
            case "register":
                createUser(req, resp);
                break;
            case "login":
                checkLogin(req,resp);
                break;
        }
    }

    private void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String pass = req.getParameter("pass");
        User user = userService.checkLogin(userName,pass);
        if (user == null) {
            req.setAttribute("mess","Thông tin đăng nhập không đúng");
            requestDispatcher = req.getRequestDispatcher("/loginForm.jsp");
            requestDispatcher.forward(req,resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            try {
                resp.sendRedirect("/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createUser(HttpServletRequest req, HttpServletResponse resp) {
        String userName = req.getParameter("userName");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");
        String address = req.getParameter("address");
        String pass = req.getParameter("pass");

        if (userService.checkUserName(userName) == null) {
            userService.create(new User(userName, pass, email, address, phoneNumber));
            req.setAttribute("mess", "Successful Registration");
            requestDispatcher = req.getRequestDispatcher("/SignUp.jsp");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                req.setAttribute("mess", "Username available");
                requestDispatcher = req.getRequestDispatcher("/SignUp.jsp");
                requestDispatcher.forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

