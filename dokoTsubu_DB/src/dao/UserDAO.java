package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDAO {
	//データベース接続に使用する情報
	private final String JDBC_URL ="jdbc:mysql://localhost/tsubuyaki";
	private final String DB_USER = "root";
	private final String DB_PASS = "hogehoge";


	public boolean findUser(User user) {
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
		//SQL文の準備
		String sql = "SELECT NAME,PASS FROM USER WHERE NAME = ?";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		//入力されたname,passを？に追加してsql文を完成、ソートする
		pStmt.setString(1, user.getName());
		//SELECT文の実行
		ResultSet rs = pStmt.executeQuery();
		if(rs.next()) {

			return false;
			}else {
				return true;
			}

		}catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			System.out.println("findUser: false");
			e.printStackTrace();
			return false;
		}
	}


	public boolean registerUser(User user,boolean judge) {

		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			//INSERT文の準備（idは自動連番なので指定しなくてよい）
			String sql = "INSERT INTO USER(NAME, PASS) VALUES(?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//INSERT文中の？に使用する値を設定しSQLを完成
			if(judge == true) {
				pStmt.setString(1, user.getName());
				pStmt.setString(2, user.getPass());
				pStmt.executeUpdate();
				return true;
			}else {
				System.out.println("userDAO:false");
				return false;
			}

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return false;

	}
	public boolean findPass(User user) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS) ) {
			String sql = "SELECT NAME,PASS FROM USER WHERE NAME = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user.getName());

			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				String name = rs.getString("NAME");
				String pass = rs.getString("PASS");
				User user2 = new User(name,pass);
				name = rs.getString("NAME");
				pass = rs.getString("PASS");
				user2.setName(name);
				user2.setPass(pass);


				if(user.getPass().equals(user2.getPass())) {
					return true;
				}else {
					return false;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

}
