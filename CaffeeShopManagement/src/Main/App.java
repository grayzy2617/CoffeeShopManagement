package Main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Controller.ProductController;
import Db.DBConnection;
import Model.Product;

public class App {
	public static void main(String[] args) throws SQLException {
		try (Connection conn = DBConnection.getConnection()) {
			System.out.println("Connected to database successfully!");
		} catch (SQLException e) {
			System.out.println("Failed to connect to database: " + e.getMessage());
		}
		ProductController productController = new ProductController();
		List<Product> result = productController.getProducts();
		for (Product product : result) {
			System.out.println(product.getName() + "  " + product.getPrice());
			System.out.println("------------------");
		}
	}

}
