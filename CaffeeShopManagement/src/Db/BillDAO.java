package Db;

import Model.Bill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    public int createBill(Bill bill) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO bill (phoneNumberCus, totalPrice, created_at, createdByEmployID) VALUES (?, ?, NOW(), ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, bill.getPhoneNumberCus());
            if (bill.getTotalPrice() == null) {
                stmt.setNull(2, java.sql.Types.DOUBLE);
            } else {
                stmt.setDouble(2, bill.getTotalPrice());
            }
            if (bill.getCreatedByEmployID() == null) {
                stmt.setNull(3, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(3, bill.getCreatedByEmployID());
            }
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
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
                Integer billId = rs.getInt("id");
                if (rs.wasNull()) {
                    billId = null;
                }
                Double totalPrice = rs.getDouble("totalPrice");
                if (rs.wasNull()) {
                    totalPrice = null;
                }
                Timestamp timestamp = rs.getTimestamp("created_at");
                LocalDateTime createdAt = timestamp != null ? timestamp.toLocalDateTime() : null;
                Integer createdBy = rs.getInt("createdByEmployID");
                if (rs.wasNull()) {
                    createdBy = null;
                }
                return new Bill(
                        billId,
                        rs.getString("phoneNumberCus"),
                        totalPrice,
                        createdAt,
                        createdBy
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
                Integer billId = rs.getInt("id");
                if (rs.wasNull()) {
                    billId = null;
                }
                Double totalPrice = rs.getDouble("totalPrice");
                if (rs.wasNull()) {
                    totalPrice = null;
                }
                Timestamp timestamp = rs.getTimestamp("created_at");
                LocalDateTime createdAt = timestamp != null ? timestamp.toLocalDateTime() : null;
                Integer createdBy = rs.getInt("createdByEmployID");
                if (rs.wasNull()) {
                    createdBy = null;
                }
                bills.add(new Bill(
                        billId,
                        rs.getString("phoneNumberCus"),
                        totalPrice,
                        createdAt,
                        createdBy
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
            if (bill.getTotalPrice() == null) {
                stmt.setNull(2, java.sql.Types.DOUBLE);
            } else {
                stmt.setDouble(2, bill.getTotalPrice());
            }
            if (bill.getCreatedByEmployID() == null) {
                stmt.setNull(3, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(3, bill.getCreatedByEmployID());
            }
            if (bill.getId() == null) {
                stmt.setNull(4, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(4, bill.getId());
            }
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
                Integer billId = rs.getInt("id");
                if (rs.wasNull()) {
                    billId = null;
                }
                Double totalPrice = rs.getDouble("totalPrice");
                if (rs.wasNull()) {
                    totalPrice = null;
                }
                Timestamp timestamp = rs.getTimestamp("created_at");
                LocalDateTime createdAt = timestamp != null ? timestamp.toLocalDateTime() : null;
                Integer createdBy = rs.getInt("createdByEmployID");
                if (rs.wasNull()) {
                    createdBy = null;
                }
                bills.add(new Bill(
                        billId,
                        rs.getString("phoneNumberCus"),
                        totalPrice,
                        createdAt,
                        createdBy
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
            stmt.setTimestamp(1, Timestamp.valueOf(startDate));
            stmt.setTimestamp(2, Timestamp.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer billId = rs.getInt("id");
                if (rs.wasNull()) {
                    billId = null;
                }
                Double totalPrice = rs.getDouble("totalPrice");
                if (rs.wasNull()) {
                    totalPrice = null;
                }
                Timestamp timestamp = rs.getTimestamp("created_at");
                LocalDateTime createdAt = timestamp != null ? timestamp.toLocalDateTime() : null;
                Integer createdBy = rs.getInt("createdByEmployID");
                if (rs.wasNull()) {
                    createdBy = null;
                }
                bills.add(new Bill(
                        billId,
                        rs.getString("phoneNumberCus"),
                        totalPrice,
                        createdAt,
                        createdBy
                ));
            }
        }
        return bills;
    }
}