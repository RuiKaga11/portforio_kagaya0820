package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginLogic;
import model.User;
import model.UserRegisterLogic;

/**
 * Servlet implementation class Account
 */
@WebServlet("/Account")
public class Account extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Account() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String pass2 = request.getParameter("pass2");
		User user = new User(name,pass);
		user.setName(name);
		user.setPass(pass);

		//パスワードの再入力の値チェック
		if(pass.equals(pass2)){
			//パスワードの再入力が適切(True)
			request.setAttribute("trueMsg", "アカウント登録に成功しました。");
			//DBの操作、daoの呼び出し
			//重複の確認
			UserRegisterLogic userRLogic = new UserRegisterLogic();
			boolean overRap = userRLogic.overRapCheckExecute(user);
			//trueで登録処理
			UserRegisterLogic userRLogic2 = new UserRegisterLogic();
			boolean register = userRLogic2.executeRegister(user,overRap);
			//登録成功したらついでにログイン処理
			LoginLogic loginLogic = new LoginLogic();
			boolean isLogin = loginLogic.execute(user);

			//登録失敗時のエラーメッセージとリダイレクト
			if(register == false) {
				request.setAttribute("errorMsg3", "このユーザIDは既に使われています。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
				dispatcher.forward(request, response);
				return;
			}else if(register == true){
				request.setAttribute("trueMsg2", "登録に成功しました");
			}else {
				request.setAttribute("errorMsg4", "登録に関する、予期せぬエラーです。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
				dispatcher.forward(request, response);
			}
			//ログイン成功時の処理
			if(isLogin == true) {
				//ユーザー情報をセッションスコープに保存
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", user);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}
		else if(pass != pass2) {
			request.setAttribute("errorMsg", "再入力されたパスワードが最初のパスワードと異なります。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
			dispatcher.forward(request, response);
			return;
		}
		else{
			System.out.println("予期せぬエラー");
			request.setAttribute("errorMsg2", "予期せぬエラーです。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
			dispatcher.forward(request, response);
			return;

		}
	}

}
