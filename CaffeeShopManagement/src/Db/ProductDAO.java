package Db;

import Model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public void addProduct(Product product) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO product (name, price, category_id, unit) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, product.getName());
            if (product.getPrice() == null) {
                stmt.setNull(2, java.sql.Types.DOUBLE);
            } else {
                stmt.setDouble(2, product.getPrice());
            }
            if (product.getCategoryId() == null) {
                stmt.setNull(3, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(3, product.getCategoryId());
            }
            stmt.setString(4, product.getUnit());
            stmt.executeUpdate();
        }
    }

    public Product getProductById(int id) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM product WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Integer productId = rs.getInt("id");
                if (rs.wasNull()) {
                    productId = null;
                }
                Double price = rs.getDouble("price");
                if (rs.wasNull()) {
                    price = null;
                }
                Integer categoryId = rs.getInt("category_id");
                if (rs.wasNull()) {
                    categoryId = null;
                }
                return new Product(
                        productId,
                        rs.getString("name"),
                        price,
                        categoryId,
                        rs.getString("unit")
                );
            }
            return null;
        }
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM product";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer productId = rs.getInt("id");
                if (rs.wasNull()) {
                    productId = null;
                }
                Double price = rs.getDouble("price");
                if (rs.wasNull()) {
                    price = null;
                }
                Integer categoryId = rs.getInt("category_id");
                if (rs.wasNull()) {
                    categoryId = null;
                }
                products.add(new Product(
                        productId,
                        rs.getString("name"),
                        price,
                        categoryId,
                        rs.getString("unit")
                ));
            }
        }
        return products;
    }

    public void updateProduct(Product product) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE product SET name = ?, price = ?, category_id = ?, unit = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, product.getName());
            if (product.getPrice() == null) {
                stmt.setNull(2, java.sql.Types.DOUBLE);
            } else {
                stmt.setDouble(2, product.getPrice());
            }
            if (product.getCategoryId() == null) {
                stmt.setNull(3, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(3, product.getCategoryId());
            }
            stmt.setString(4, product.getUnit());
            if (product.getId() == null) {
                stmt.setNull(5, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(5, product.getId());
            }
            stmt.executeUpdate();
        }
    }

    public void deleteProduct(int id) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM product WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Product> searchProductsByCategory(int categoryId) throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM product WHERE category_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer productId = rs.getInt("id");
                if (rs.wasNull()) {
                    productId = null;
                }
                Double price = rs.getDouble("price");
                if (rs.wasNull()) {
                    price = null;
                }
                Integer catId = rs.getInt("category_id");
                if (rs.wasNull()) {
                    catId = null;
                }
                products.add(new Product(
                        productId,
                        rs.getString("name"),
                        price,
                        catId,
                        rs.getString("unit")
                ));
            }
        }
        return products;
    }

    public List<Product> searchProductsByName(String name) throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM product WHERE name LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer productId = rs.getInt("id");
                if (rs.wasNull()) {
                    productId = null;
                }
                Double price = rs.getDouble("price");
                if (rs.wasNull()) {
                    price = null;
                }
                Integer categoryId = rs.getInt("category_id");
                if (rs.wasNull()) {
                    categoryId = null;
                }
                products.add(new Product(
                        productId,
                        rs.getString("name"),
                        price,
                        categoryId,
                        rs.getString("unit")
                ));
            }
        }
        return products;
    }
}