package Db;

import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public void addUser(User user) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO user (username, password, role_id, name, salary) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            if (user.getRoleId() == null) {
                stmt.setNull(3, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(3, user.getRoleId());
            }
            stmt.setString(4, user.getName());
            if (user.getSalary() == null) {
                stmt.setNull(5, java.sql.Types.DOUBLE);
            } else {
                stmt.setDouble(5, user.getSalary());
            }
            stmt.executeUpdate();
        }
    }
    
    public User getUserByUsername(String username) throws SQLException {
    	try(Connection conn = DBConnection.getConnection()) {
    		String sql = "SELECT * FROM user WHERE username = ? AND status = 'active'";
    		PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Integer userId = rs.getInt("id");
                if (rs.wasNull()) {
                    userId = null;
                }
                Integer roleId = rs.getInt("role_id");
                if (rs.wasNull()) {
                    roleId = null;
                }
                Double salary = rs.getDouble("salary");
                if (rs.wasNull()) {
                    salary = null;
                }
                return new User(
                        userId,
                        rs.getString("username"),
                        rs.getString("password"),
                        roleId,
                        rs.getString("name"),
                        salary
                );
            }
            return null;
    	}
    }

    public User getUserById(int id) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM user WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Integer userId = rs.getInt("id");
                if (rs.wasNull()) {
                    userId = null;
                }
                Integer roleId = rs.getInt("role_id");
                if (rs.wasNull()) {
                    roleId = null;
                }
                Double salary = rs.getDouble("salary");
                if (rs.wasNull()) {
                    salary = null;
                }
                return new User(
                        userId,
                        rs.getString("username"),
                        rs.getString("password"),
                        roleId,
                        rs.getString("name"),
                        salary
                );
            }
            return null;
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM user WHERE status = 'active'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer userId = rs.getInt("id");
                if (rs.wasNull()) {
                    userId = null;
                }
                Integer roleId = rs.getInt("role_id");
                if (rs.wasNull()) {
                    roleId = null;
                }
                Double salary = rs.getDouble("salary");
                if (rs.wasNull()) {
                    salary = null;
                }
                users.add(new User(
                        userId,
                        rs.getString("username"),
                        rs.getString("password"),
                        roleId,
                        rs.getString("name"),
                        salary
                ));
            }
        }
        return users;
    }

    public void updateUser(User user) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE user SET username = ?, password = ?, role_id = ?, name = ?, salary = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            if (user.getRoleId() == null) {
                stmt.setNull(3, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(3, user.getRoleId());
            }
            stmt.setString(4, user.getName());
            if (user.getSalary() == null) {
                stmt.setNull(5, java.sql.Types.DOUBLE);
            } else {
                stmt.setDouble(5, user.getSalary());
            }
            if (user.getId() == null) {
                stmt.setNull(6, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(6, user.getId());
            }
            stmt.executeUpdate();
        }
    }

    public void deleteUser(int id) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE user SET status = 'inactive' WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<User> searchUsersByName(String name) throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM user WHERE name LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer userId = rs.getInt("id");
                if (rs.wasNull()) {
                    userId = null;
                }
                Integer roleId = rs.getInt("role_id");
                if (rs.wasNull()) {
                    roleId = null;
                }
                Double salary = rs.getDouble("salary");
                if (rs.wasNull()) {
                    salary = null;
                }
                users.add(new User(
                        userId,
                        rs.getString("username"),
                        rs.getString("password"),
                        roleId,
                        rs.getString("name"),
                        salary
                ));
            }
        }
        return users;
    }
}
