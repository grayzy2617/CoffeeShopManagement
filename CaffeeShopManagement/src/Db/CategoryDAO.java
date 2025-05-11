package Db;

import Model.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    public void addCategory(Category category) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO category (name) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, category.getName());
            stmt.executeUpdate();
        }
    }

    public Category getCategoryById(int id) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM category WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Integer catId = rs.getInt("id");
                if (rs.wasNull()) {
                    catId = null;
                }
                return new Category(
                        catId,
                        rs.getString("name")
                );
            }
            return null;
        }
    }

    public List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM category";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer catId = rs.getInt("id");
                if (rs.wasNull()) {
                    catId = null;
                }
                categories.add(new Category(
                        catId,
                        rs.getString("name")
                ));
            }
        }
        return categories;
    }

    public void updateCategory(Category category) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE category SET name = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, category.getName());
            if (category.getId() == null) {
                stmt.setNull(2, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(2, category.getId());
            }
            stmt.executeUpdate();
        }
    }

    public void deleteCategory(int id) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM category WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Category> searchCategoriesByName(String name) throws SQLException {
        List<Category> categories = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM category WHERE name LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer catId = rs.getInt("id");
                if (rs.wasNull()) {
                    catId = null;
                }
                categories.add(new Category(
                        catId,
                        rs.getString("name")
                ));
            }
        }
        return categories;
    }
}