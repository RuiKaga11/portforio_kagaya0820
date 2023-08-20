package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetMutterListLogic;
import model.Mutter;
import model.PostMutterLogic;
import model.User;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//つぶやきリストを取得して、リクエストスコープに保存
		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
		List<Mutter> mutterList = getMutterListLogic.execute();
		ServletContext application = this.getServletContext();
		application.setAttribute("mutterList", mutterList);
//		request.setAttribute("mutterList", mutterList);

		//取得できなかった場合は、つぶやきリストを新規作成して
		//アプリケーションスコープに保存
		if(mutterList == null) {
			System.out.println("test:2muterListがnullでここに入る");
			mutterList = new ArrayList<>();
//			ServletContext application = this.getServletContext();
			application.setAttribute("mutterList", mutterList);
		}

		//ログインしているか確認するため
		//セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if(loginUser == null) {
			//リダイレクト
			System.out.println("test:3 loginUserが空");
			response.sendRedirect("/dokoTsubu_DB/");
		}else {
			//ログイン済みの場合
			RequestDispatcher dispstcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispstcher.forward(request, response);
			return;
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");

		//入力値チェック
		if(text != null && text.length() != 0) {

			//セッションスコープに保存されたユーザー情報を取得
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");

			//つぶやきをつぶやきリストに追加
			Mutter mutter = new Mutter(loginUser.getName(),text);
			System.out.println(mutter.getUserName());
			System.out.println(mutter.getText());
			PostMutterLogic postMutterLogic = new PostMutterLogic();
			postMutterLogic.execute(mutter);//executeはユーザー自作クラス

			}else {
				//エラーメッセージをリクエストスコープに保存
				request.setAttribute("errorMsg", "つぶやきが入力されていません。");
			}

		//つぶやきリストを取得して、リクエストスコープに保存
			GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
			List<Mutter> mutterList =getMutterListLogic.execute();
			request.setAttribute("mutterList", mutterList);

			//メイン画面にフォワード
//			RequestDispatcher dispatcher = request.getRequestDispatcher("mainjspのパス");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);

			}
}
