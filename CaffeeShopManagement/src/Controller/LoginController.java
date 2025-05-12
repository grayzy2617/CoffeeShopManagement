package Controller;

import Db.UserDAO;
import Model.User;

public class LoginController {
	private UserDAO userDAO;
	public LoginController() {
		userDAO = new UserDAO();
	}
	public boolean login(String username, String password) {
		if(username == null) return false;
		if(password == null) return false;
		
		try {
			User u = userDAO.getUserByUsername(username);
			if(u == null) return false;
			if(u.getPassword().equals(password)) return true;
			else return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
