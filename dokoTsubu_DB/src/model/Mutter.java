package model;

import java.io.Serializable;
//javabeans
public class Mutter implements Serializable{
	//プロパティ
	int id;
	private String userName;
	private String text;
	//コンストラクタ
	public Mutter() {}
	public Mutter(String text) {
		this.text = text;
	}
	public Mutter(String userName, String text) {
		this.userName = userName;
		this.text = text;
	}
	public Mutter(int id,String userName,String text) {
		this.id = id;
		this.userName = userName;
		this.text = text;
	}
	//getterのみ
	public int getId() {
		return id;
	}
	public String getUserName() {
		return userName;
	}
	public String getText() {
		return text;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setId(int id) {
		this.id = id;
	}
}
