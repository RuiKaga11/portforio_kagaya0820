package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Mutter;
import model.SortMutterLogic;
import model.User;

/**
 * Servlet implementation class Sort
 */
@WebServlet("/Sort")
public class Sort extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sort() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String sortText = request.getParameter("sortText");
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		Mutter sortMutter = new Mutter(loginUser.getName(), sortText);
		SortMutterLogic sortM = new SortMutterLogic();
		List<Mutter> sort = sortM.sortExe(sortMutter);

		request.setAttribute("sortResult", sort);
		String space = "";
			if(sortText == space) {
				//より詳細な空文字対策は正規表現で
				request.setAttribute("errorMsg3", "検索内容が入力されていません。（スペースを含む）");
				System.out.println("Sort.java:空文字判定");
			}
			if(sortText == null) {
				//エラーメッセージをリクエストスコープに保存
				request.setAttribute("errorMsg2", "検索内容が入力されていません。");
			}else {
				System.out.println("Sort.java:正常な動作");
			}

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);

	}

}
