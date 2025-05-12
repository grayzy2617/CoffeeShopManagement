package Controller;

import Db.CategoryDAO;
import Model.Category;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class CategoryController {
    private CategoryDAO cateDAO;

    public CategoryController(){
        cateDAO = new CategoryDAO();
    }

    public Category getCategoryById(Integer id) {
        if (id == null) {
            return null;
        }
        try {
            return cateDAO.getCategoryById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Category> getAllCategories() throws SQLException {
        try {
            List<Category> categories = cateDAO.getAllCategories();
            return categories != null ? categories : Collections.emptyList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public boolean addCategory(Category category) {
        if (category == null || category.getName() == null || category.getName().isEmpty()) {
            return false;
        }
        try {
            cateDAO.addCategory(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCategory(Category category) {
        if (category == null || category.getId() == null || category.getName() == null || category.getName().isEmpty()) {
            return false;
        }
        try {
            cateDAO.updateCategory(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCategory(Integer id) {
        if (id == null) {
            return false;
        }
        try {
            cateDAO.deleteCategory(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Category> searchCategoriesByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return Collections.emptyList();
        }
        try {
            List<Category> categoryList = cateDAO.searchCategoriesByName(name);
            return categoryList != null ? categoryList : Collections.emptyList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


}
