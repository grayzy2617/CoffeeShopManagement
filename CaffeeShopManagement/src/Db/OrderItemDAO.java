package Db;

import Model.OrderItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO {
    public void createOrderItem(OrderItem item) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO orderItem (invoice_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            if (item.getInvoiceId() == null) {
                stmt.setNull(1, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(1, item.getInvoiceId());
            }
            if (item.getProductId() == null) {
                stmt.setNull(2, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(2, item.getProductId());
            }
            if (item.getQuantity() == null) {
                stmt.setNull(3, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(3, item.getQuantity());
            }
            if (item.getPrice() == null) {
                stmt.setNull(4, java.sql.Types.DOUBLE);
            } else {
                stmt.setDouble(4, item.getPrice());
            }
            stmt.executeUpdate();
        }
    }

    public OrderItem getOrderItemById(int id) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM orderItem WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Integer itemId = rs.getInt("id");
                if (rs.wasNull()) {
                    itemId = null;
                }
                Integer invoiceId = rs.getInt("invoice_id");
                if (rs.wasNull()) {
                    invoiceId = null;
                }
                Integer productId = rs.getInt("product_id");
                if (rs.wasNull()) {
                    productId = null;
                }
                Integer quantity = rs.getInt("quantity");
                if (rs.wasNull()) {
                    quantity = null;
                }
                Double price = rs.getDouble("price");
                if (rs.wasNull()) {
                    price = null;
                }
                return new OrderItem(
                        itemId,
                        invoiceId,
                        productId,
                        quantity,
                        price
                );
            }
            return null;
        }
    }

    public List<OrderItem> getAllOrderItems() throws SQLException {
        List<OrderItem> items = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM orderItem";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer itemId = rs.getInt("id");
                if (rs.wasNull()) {
                    itemId = null;
                }
                Integer invoiceId = rs.getInt("invoice_id");
                if (rs.wasNull()) {
                    invoiceId = null;
                }
                Integer productId = rs.getInt("product_id");
                if (rs.wasNull()) {
                    productId = null;
                }
                Integer quantity = rs.getInt("quantity");
                if (rs.wasNull()) {
                    quantity = null;
                }
                Double price = rs.getDouble("price");
                if (rs.wasNull()) {
                    price = null;
                }
                items.add(new OrderItem(
                        itemId,
                        invoiceId,
                        productId,
                        quantity,
                        price
                ));
            }
        }
        return items;
    }

    public void updateOrderItem(OrderItem item) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE orderItem SET invoice_id = ?, product_id = ?, quantity = ?, price = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            if (item.getInvoiceId() == null) {
                stmt.setNull(1, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(1, item.getInvoiceId());
            }
            if (item.getProductId() == null) {
                stmt.setNull(2, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(2, item.getProductId());
            }
            if (item.getQuantity() == null) {
                stmt.setNull(3, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(3, item.getQuantity());
            }
            if (item.getPrice() == null) {
                stmt.setNull(4, java.sql.Types.DOUBLE);
            } else {
                stmt.setDouble(4, item.getPrice());
            }
            if (item.getId() == null) {
                stmt.setNull(5, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(5, item.getId());
            }
            stmt.executeUpdate();
        }
    }

    public void deleteOrderItem(int id) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM orderItem WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<OrderItem> getOrderItemsByBillId(int billId) throws SQLException {
        List<OrderItem> items = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM orderItem WHERE invoice_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, billId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer itemId = rs.getInt("id");
                if (rs.wasNull()) {
                    itemId = null;
                }
                Integer invoiceId = rs.getInt("invoice_id");
                if (rs.wasNull()) {
                    invoiceId = null;
                }
                Integer productId = rs.getInt("product_id");
                if (rs.wasNull()) {
                    productId = null;
                }
                Integer quantity = rs.getInt("quantity");
                if (rs.wasNull()) {
                    quantity = null;
                }
                Double price = rs.getDouble("price");
                if (rs.wasNull()) {
                    price = null;
                }
                items.add(new OrderItem(
                        itemId,
                        invoiceId,
                        productId,
                        quantity,
                        price
                ));
            }
        }
        return items;
    }
}