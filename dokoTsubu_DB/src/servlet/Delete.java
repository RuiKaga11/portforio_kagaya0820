package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MutterDAO;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("delete.javaに入ってきているか確認");
		request.setCharacterEncoding("UTF-8");
		String deleteId = request.getParameter("deleteId");
	    int id = Integer.parseInt(deleteId);

	    MutterDAO dao = new MutterDAO();
	    boolean judge = dao.deleteMutter(id);
	    if(judge == true) {
//	    	System.out.println("Delete.java:true");
			request.setAttribute("deleteTrueMsg", "指定のつぶやきを削除しました。");
	    }else if(judge == false) {
//	    	System.out.println("Delete.java:false");
			request.setAttribute("deleteFalseMsg", "削除に失敗しました。");
	    }else {
			request.setAttribute("deleteFalseMsg2", "意図しない失敗です。");
//	    	System.out.println("意図しない挙動");
	    }

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}

}
