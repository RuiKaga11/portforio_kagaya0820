package model;

import dao.UserDAO;

public class UserRegisterLogic {
	public boolean overRapCheckExecute(User user) {
		UserDAO dao = new UserDAO();
		boolean judge = dao.findUser(user);
		return judge;

	}

	public boolean executeRegister(User user, boolean judge) {
		UserDAO dao = new UserDAO();
		boolean result = dao.registerUser(user, judge);
		return result;

	}
}
