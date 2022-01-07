package controller;

import model.Product;
import service.CategoryService;
import service.ProductService;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    UserService userService = new UserService();
    ProductService productService = new ProductService();
    CategoryService categoryService = new CategoryService();
    RequestDispatcher requestDispatcher;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String act = req.getParameter("act");
        if (act == null) {
            act = "";
        }
        switch (act) {
            case "create":
                creatProduct(req, resp);
                break;
        }
    }

    private void creatProduct(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        int price = Integer.parseInt(req.getParameter("price"));
        String description = req.getParameter("description");
        String img = req.getParameter("img");
        int idCategory = Integer.parseInt(req.getParameter("idCategory"));

        productService.save(new Product(name, price, description, img, idCategory));
        req.setAttribute("productList",productService.getAll());
        requestDispatcher = req.getRequestDispatcher("/index.jsp");
        try {
            resp.sendRedirect("/admin");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String act = req.getParameter("act");
        if (act == null) {
            act = "";
        }
        switch (act) {
            case "delete":
                deleteProduct(req, resp);
                break;
            case "create":
                showCreateForm(req, resp);
                break;
            case "home":
                showHome(req, resp);
                break;
            default:
                showList(req, resp);
        }
    }

    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("categoryList", categoryService.fillAll());
        requestDispatcher = req.getRequestDispatcher("/createForm.jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        productService.delete(id);
        try {
            resp.sendRedirect("/admin");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("productList", productService.getAll());
        requestDispatcher = req.getRequestDispatcher("/viewAdmin.jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }


    private void showHome(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("productList", productService.getAll());
        requestDispatcher = req.getRequestDispatcher("/index.jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
