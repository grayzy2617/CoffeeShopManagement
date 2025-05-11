package Db;

import Model.Bill;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    public int createBill(Bill bill) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);// dữ liệu ko được tự động mà cần commit thì mới đc lưu ở db
            String sql = "INSERT INTO bill (phoneNumberCus, totalPrice, created_at, createdByEmployID) VALUES (?, ?, NOW(), ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, bill.getPhoneNumberCus());
            stmt.setDouble(2, bill.getTotalPrice());
            stmt.setInt(3, bill.getCreatedByEmployID());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();//ko auto commit để lấy được id của newBill dễ dàng
            if (rs.next()) {
                int billId = rs.getInt(1);
                conn.commit();
                return billId;
            }
            throw new SQLException("Failed to create bill");
        }
    }

    public Bill getBillById(int id) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM bill WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("created_at");
                LocalDateTime createdAt = timestamp != null ? timestamp.toLocalDateTime() : null;
                return new Bill(
                        rs.getInt("id"),
                        rs.getString("phoneNumberCus"),
                        rs.getDouble("totalPrice"),
                        createdAt,
                        rs.getInt("createdByEmployID")
                );
            }
            return null;
        }
    }

    public List<Bill> getAllBills() throws SQLException {
        List<Bill> bills = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM bill";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("created_at");
                LocalDateTime createdAt = timestamp != null ? timestamp.toLocalDateTime() : null;
                bills.add(new Bill(
                        rs.getInt("id"),
                        rs.getString("phoneNumberCus"),
                        rs.getDouble("totalPrice"),
                        createdAt,
                        rs.getInt("createdByEmployID")
                ));
            }
        }
        return bills;
    }

    public void updateBill(Bill bill) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE bill SET phoneNumberCus = ?, totalPrice = ?, createdByEmployID = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, bill.getPhoneNumberCus());
            stmt.setDouble(2, bill.getTotalPrice());
            stmt.setInt(3, bill.getCreatedByEmployID());
            stmt.setInt(4, bill.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteBill(int id) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM bill WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Bill> getBillsByEmployeeId(int employeeId) throws SQLException {
        List<Bill> bills = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM bill WHERE createdByEmployID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("created_at");
                LocalDateTime createdAt = timestamp != null ? timestamp.toLocalDateTime() : null;
                bills.add(new Bill(
                        rs.getInt("id"),
                        rs.getString("phoneNumberCus"),
                        rs.getDouble("totalPrice"),
                        createdAt,
                        rs.getInt("createdByEmployID")
                ));
            }
        }
        return bills;
    }

    public List<Bill> searchBillsByDateRange(LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
        List<Bill> bills = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM bill WHERE created_at BETWEEN ? AND ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setTimestamp(1, java.sql.Timestamp.valueOf(startDate));
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("created_at");
                LocalDateTime createdAt = timestamp != null ? timestamp.toLocalDateTime() : null;
                bills.add(new Bill(
                        rs.getInt("id"),
                        rs.getString("phoneNumberCus"),
                        rs.getDouble("totalPrice"),
                        createdAt,
                        rs.getInt("createdByEmployID")
                ));
            }
        }
        return bills;
    }
}