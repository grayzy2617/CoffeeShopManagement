package Db;

import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public User getUserById(int id) throws SQLException {
        try (Connection conn =DBConnection.getConnection()){
            String sql = "Select * from user Where id= ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("role_id"),
                        rs.getString("name"),
                        rs.getDouble("salary"));
            }
        }
        return null;
    }

    public List<User> getAllUser() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()){
            String sql ="Select * from user";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("role_id"),
                        rs.getString("name"),
                        rs.getDouble("salary"))
                );
            }
        }
        return users;
    }

    public void updateUser ( User user) throws SQLException {
        try (Connection conn= DBConnection.getConnection()){
            String sql = "update user Set name =?, password =?, role_id =?, salary =?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,user.getName());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3,user.getRoleId());
            stmt.setDouble(4,user.getSalary());
            stmt.executeUpdate();
        }
    }

    public void addUser (User user) throws SQLException {
        try (Connection conn = DBConnection.getConnection()){
            String sql = "Insert into user (username, password, role_id, name, salary) Values" +
                    "(?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,user.getUsername());
            stmt.setString(2,user.getPassword());
            stmt.setInt(3,user.getRoleId());
            stmt.setString(4,user.getName());
            stmt.setDouble(5,user.getSalary());
            stmt.executeUpdate();
        }
    }

    public void deleteUser(int id) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM user WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<User> searchUserByName(String name) throws SQLException {
        List<User> users = new ArrayList<>();
        try ( Connection conn = DBConnection.getConnection()){
            String sql = "SELECT * from user WHERE name LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,"%" + name +"%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("role_id"),
                        rs.getString("name"),
                        rs.getDouble("salary"))
                );
            }
        }
        return users;
    }
}
