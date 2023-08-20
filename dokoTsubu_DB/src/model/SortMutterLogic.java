package model;

import java.util.List;

import dao.MutterDAO;

public class SortMutterLogic {
	public List<Mutter> sortExe(Mutter sortM) {
		MutterDAO dao = new MutterDAO();
		List<Mutter> sortMutter = dao.searchMutter(sortM);

		return sortMutter;
	}

}
