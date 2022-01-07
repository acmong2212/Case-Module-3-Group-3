package service;

import dao.CategoryDAO;
import model.Category;

import java.util.List;

public class CategoryService {
    CategoryDAO categoryDAO = new CategoryDAO();
    List<Category> categoryList = categoryDAO.getAll();

    public List<Category> fillAll(){
        return categoryList;
    }
}
