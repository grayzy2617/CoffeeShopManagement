package Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Db.ProductDAO;
import Model.Product;

public class ProductController {
	private ProductDAO productDAO;

	public ProductController() {
		productDAO = new ProductDAO();
	}

	// get a product by id
	public Product getProduct(int id) {
		Product product = null;
		try {
			product = productDAO.getProductById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	// get products
	public List<Product> getProducts() {
		List<Product> productList = new ArrayList<Product>();
		try {
			productList = productDAO.getAllProducts();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList != null ? productList : Collections.emptyList();
	}

	// create a product
	public boolean createProduct(Product product) {
		if (product.getName() == null || product.getName().isEmpty()) {
			return false;
		}
		if (product.getPrice() == null || product.getPrice() <= 0) {
			return false;
		}
		if (product.getCategoryId() == null || product.getCategoryId() <= 0) {
			return false;
		}
		if (product.getUnit() == null || product.getUnit().isEmpty()) {
			return false;
		}

		ProductDAO productDAO = new ProductDAO();
		try {
			productDAO.addProduct(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// update the product information
	public boolean updateProduct(Product product) {
		if (product.getId() == null) {
			return false;
		}
		if (product.getName() == null || product.getName().isEmpty()) {
			return false;
		}
		if (product.getPrice() == null || product.getPrice() <= 0) {
			return false;
		}
		if (product.getCategoryId() == null || product.getCategoryId() <= 0) {
			return false;
		}
		if (product.getUnit() == null || product.getUnit().isEmpty()) {
			return false;
		}

		ProductDAO productDAO = new ProductDAO();
		try {
			productDAO.updateProduct(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// delete a product
	public boolean deleteProduct(Integer id) {
		if (id == null) {
			return false;
		}
		ProductDAO productDAO = new ProductDAO();
		try {
			productDAO.deleteProduct(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// get products by category_id
	public List<Product> getProductByCategory(Integer categoryId) {
		if (categoryId == null)
			return Collections.emptyList();
		ProductDAO productDAO = new ProductDAO();
		try {
			List<Product> productList = productDAO.searchProductsByCategory(categoryId);
			return productList != null ? productList : Collections.emptyList();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	// search products (search following name)
	public List<Product> searchProduct(String keyword) {
		ProductDAO productDAO = new ProductDAO();
		List<Product> productList = new ArrayList<Product>();
		if (keyword == null || keyword.isEmpty()) {
			try {
				productList = productDAO.getAllProducts();
				return productList != null ? productList : Collections.emptyList();
			} catch (Exception e) {
				e.printStackTrace();
				return Collections.emptyList();
			}

		}

		try {
			productList = productDAO.searchProductsByName(keyword);
			return productList != null ? productList : Collections.emptyList();
		} catch (SQLException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}

	}
}
