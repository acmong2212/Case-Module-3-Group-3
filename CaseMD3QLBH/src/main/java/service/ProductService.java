package service;

import dao.ProductDAO;
import model.Product;

import java.util.List;

public class ProductService {
    ProductDAO productDAO = new ProductDAO();
    List<Product> productList = productDAO.getAll();

    public List<Product> getAll() {
        return productList;
    }

    public List<Product> getAllByCategory(int id){
        return productDAO.getAllByCategory(id);
    }

    public void save(Product product) {
        productDAO.save(product);
        productList = productDAO.getAll();
    }

    public void delete(int id) {
        productDAO.delete(id);
        productList = productDAO.getAll();
    }

    public void edit(Product product) {
        productDAO.edit(product);
        productList = productDAO.getAll();
    }

    public Product findById(int id) {
        productList = productDAO.getAll();
        for (int i = 0; i < productList.size(); i++) {
            if (id == productList.get(i).getId()) {
                return productList.get(i);
            }
        }
        return null;
    }

    public List<Product> findByName(String name){
       return productList = productDAO.findByName(name);
    }
}