package Controller;

import Model.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Db.ProductDAO;

public class ProductController {
	public Product getProduct(int id) {
		ProductDAO productDAO = new ProductDAO();
		Product product = null;
		try {
			product = productDAO.getProductById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}
	
	public List<Product> getProducts() {
		ProductDAO productDAO = new ProductDAO();
		List<Product> productList = new ArrayList<Product>();
		try {
			productList = productDAO.getAllProducts();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return productList != null ? productList : Collections.emptyList();
	}
	
	public Boolean createProduct(Product product) {
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
	
	public Boolean updateProduct(Product product) {
		if(product.getId() == null) {
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
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	public Boolean deleteProduct(Integer id) {
		if(id == null) {
			return false;
		}
		ProductDAO productDAO = new ProductDAO();
		try {
			productDAO.deleteProduct(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Product> getProductByCategory(Integer categoryId) {
		if(categoryId == null)
			return Collections.emptyList();
		ProductDAO productDAO = new ProductDAO();
		try {
			List<Product> productList = productDAO.searchProductsByCategory(categoryId);
			return productList != null ? productList : Collections.emptyList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	public List<Product> searchProduct(String keyword) {
		ProductDAO productDAO = new ProductDAO();
		List<Product> productList = new ArrayList<Product>();
		if(keyword == null || keyword.isEmpty()) {			
			try {
				productList = productDAO.getAllProducts();
				return productList != null ? productList : Collections.emptyList();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return Collections.emptyList();
			}
			
		}
		
		try {
			productList = productDAO.searchProductsByName(keyword);
			return productList != null ? productList : Collections.emptyList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Collections.emptyList();
		}
		
	}


}
