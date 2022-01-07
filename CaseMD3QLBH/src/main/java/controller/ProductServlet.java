package controller;

import model.Product;
import service.CartService;
import service.CategoryService;
import service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "")
public class ProductServlet extends HttpServlet {
    ProductService productService = new ProductService();
    CartService cartService = new CartService();
    CategoryService categoryService = new CategoryService();

    RequestDispatcher requestDispatcher;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String act = req.getParameter("act");
        if (act == null){
            act = "";
        }
        switch (act){
            case "detail":
                detailProduct(req,resp);
                break;
            case "addToCart":
                addToCart(req,resp);
                break;
            case "showCart":
                showCart(req,resp);
                break;
            case "showListByCategory":
                showListByCategory(req,resp);
                break;
            default:
                showList(req,resp);
        }
    }

    private void searchProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("search");

        req.setAttribute("productList",productService.findByName(name));

        requestDispatcher = req.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void showListByCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("productList",productService.getAllByCategory(id));
        req.setAttribute("categoryList",categoryService.fillAll());
        req.setAttribute("count",cartService.getCart().size());
        requestDispatcher = req.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void showCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("sum",cartService.sumCart());
        req.setAttribute("listCart",cartService.getCart());

        requestDispatcher = req.getRequestDispatcher("/viewCart.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void addToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Product product = productService.findById(id);

        cartService.saveCart(product);
        resp.sendRedirect("/");

    }

    private void detailProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));

        Product product = productService.findById(id);
        req.setAttribute("product",product);
        requestDispatcher = req.getRequestDispatcher("/detail.jsp");
        try {
            requestDispatcher.forward(req,resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("categoryList",categoryService.fillAll());
        req.setAttribute("count",cartService.getCart().size());
        req.setAttribute("productList",productService.getAll());
        requestDispatcher = req.getRequestDispatcher("/index.jsp");
        try {
            requestDispatcher.forward(req,resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String act = req.getParameter("act");
        if (act == null){
            act = "";

        }
        switch (act){
            case "search":
                searchProduct(req,resp);
                break;
        }

    }
}
