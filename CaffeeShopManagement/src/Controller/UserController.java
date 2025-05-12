package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Db.UserDAO;
import Model.User;

public class UserController {
	private UserDAO userDAO;
	public UserController() {
		userDAO = new UserDAO();
	}
	
	public User getUserById(Integer id) {
		if(id == null)
			return null;
		try {
			return userDAO.getUserById(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public List<User> getUsers() {
		try {
			List<User> userList = userDAO.getAllUser();
			return userList != null ? userList : Collections.emptyList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	public boolean addUser(User user) {
		if(user.getUsername() == null || user.getUsername().isEmpty()) return false;
		if(user.getPassword() == null || user.getPassword().isEmpty()) return false;
		if(user.getRoleId() == null || user.getRoleId() <= 0) return false;
		if(user.getName() == null || user.getName().isEmpty()) return false;
		if(user.getSalary() == null || user.getSalary() <= 0) return false;
		
		try {
			userDAO.addUser(user);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}	
	}
	
	public boolean updateUser(User user) {
		if(user.getId() == null) return false;
		if(user.getUsername() == null || user.getUsername().isEmpty()) return false;
		if(user.getPassword() == null || user.getPassword().isEmpty()) return false;
		if(user.getRoleId() == null || user.getRoleId() <= 0) return false;
		if(user.getName() == null || user.getName().isEmpty()) return false;
		if(user.getSalary() == null || user.getSalary() <= 0) return false;
		
		try {
			userDAO.updateUser(user);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteUser(Integer id) {
		if(id == null) return false;
		
		try {
			userDAO.deleteUser(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	public List<User> searchUserByName(String keyword) {
		List<User> userList = new ArrayList<User>();
		if(keyword == null || keyword.isEmpty()) {
			try {
				userList = userDAO.getAllUser();
				return userList != null ? userList : Collections.emptyList();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return Collections.emptyList();
			}
		}
		
		try {
			userList = userDAO.searchUserByName(keyword);
			return userList != null ? userList : Collections.emptyList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
}
