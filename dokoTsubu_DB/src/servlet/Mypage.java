package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Mutter;
import model.MypageSortLogic;
import model.User;

/**
 * Servlet implementation class Mypage
 */
@WebServlet("/Mypage")
public class Mypage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Mypage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		//セッションスコープのnameだけ使う。
		String userName = loginUser.getName();
		User user = new User(userName);
		MypageSortLogic MypageSL = new MypageSortLogic();
		List<Mutter> myMutterList = MypageSL.sortMyMutter(user);
//		System.out.println("mymutterlist:"+myMutterList.get(0).getText());
		request.setAttribute("mymutterList", myMutterList);

		if(myMutterList.isEmpty()) {
			System.out.println("ifに入っている=リストが空");
			myMutterList = new ArrayList<>();
			request.setAttribute("mymutterList", myMutterList);
			request.setAttribute("recomMsg", "つぶやきがありません。つぶやいてみよう！");
//			request.setAttribute("mymutterList", myMutterList);
		}else {
			System.out.println("elseに入っている");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
