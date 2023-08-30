package model;

import java.util.List;

import dao.MutterDAO;

public class MypageSortLogic {
	public List<Mutter> sortMyMutter(User user){
		MutterDAO dao = new MutterDAO();
		List<Mutter> myMutterList = dao.sortMutter(user);
		return myMutterList;
	}
}
