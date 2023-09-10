package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;
import model.User;

public class MutterDAO {
	//データベース接続に使用する情報
	private final String JDBC_URL ="jdbc:mysql://localhost/tsubuyaki";
	private final String DB_USER = "root";
	private final String DB_PASS = "hogehoge";

	public List<Mutter> findAll(){
		List<Mutter> mutterList = new ArrayList<>();
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
			//SELECT文の準備
			String sql = "SELECT MUTTER_ID,NAME,TEXT FROM MUTTER ORDER BY MUTTER_ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//SELECT文の実行
			ResultSet rs = pStmt.executeQuery();
			//SELECT文の結果をArrayListに格納
			System.out.println("findall");
			while(rs.next()) {
				int id = rs.getInt("MUTTER_ID");
				String userName = rs.getString("NAME");
				String text = rs.getString("TEXT");
				Mutter mutter = new Mutter(id,userName,text);
				mutterList.add(mutter);
//				System.out.println("mutterList" + mutterList);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("mutterDAO:findallでエラー");
			e.printStackTrace();
			return null;
		}
		return mutterList;
	}
	public boolean create(Mutter mutter) {
		//データベースに接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			//INSERT文の準備（IDは自動連番なので指定しなくてよい）
			System.out.println("create");
			String sql = "INSERT INTO MUTTER(NAME,TEXT) VALUES(?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//INSERT文中の？に使用する値を設定しSQLを完成
			pStmt.setString(1, mutter.getUserName());
			pStmt.setString(2, mutter.getText());
			//INSERT文を実行（resultには追加された行数が代入される）
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public List<Mutter> searchMutter(Mutter mutter) {
		List<Mutter> sortMutterList = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			//部分一致検索機能
			String sql = "SELECT MUTTER_ID,NAME,TEXT FROM MUTTER WHERE TEXT LIKE ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//selectにフロントから入る値をセット
			pStmt.setString(1, "%" + mutter.getText() + "%");
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				int sortId = rs.getInt("MUTTER_ID");
				String sortName = rs.getString("NAME");
				String sortText = rs.getString("TEXT");
				Mutter sortmutter = new Mutter(sortId,sortName, sortText);
				sortMutterList.add(sortmutter);
			}
//			return sortMutterList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sortMutterList;

	}
	public boolean deleteMutter(int id) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			//ID一致で削除
			String sql = "DELETE FROM MUTTER WHERE MUTTER_ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, id);
			int rs = pStmt.executeUpdate();
			System.out.println("rs"+rs);
			if(rs == 1) {
//				System.out.println("rsが1件削除成功,DBの状況で成功かわかる");
				return true;
			}else {
//				System.out.println("MUtterDAO:else:削除に失敗");
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	public List<Mutter> sortMutter(User user){
		System.out.println("sortMutterメソッドに入る");
		List<Mutter> myMutterList = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "SELECT TEXT FROM MUTTER WHERE NAME = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			String mutterName = user.getName();
			System.out.println(mutterName);
			pStmt.setString(1, mutterName);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				System.out.println("whileに入っている");
				String text = rs.getString("TEXT");
				Mutter myMutter = new Mutter(text);
				myMutterList.add(myMutter);
			}
//			if(!(rs.next())) {
//				System.out.println("rsが空");
//				myMutterList = new ArrayList<>();
//				return myMutterList;
//			}
			} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return myMutterList;

	}
}
