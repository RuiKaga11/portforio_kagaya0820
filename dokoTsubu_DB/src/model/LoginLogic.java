package model;

import dao.UserDAO;

public class LoginLogic {
	public boolean execute(User user) {

		UserDAO dao = new UserDAO();
		boolean judge = dao.findPass(user);
		if(judge == true) {
			return true;
		}else {
			return false;
		}
	}
}
