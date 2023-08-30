package model;

import java.io.Serializable;
//javabeans
public class User implements Serializable{
	//プロパティ
	private int id;
	private String name;
	private String pass;
	public User() {}
	//コンストラクタ
	public User(String name,String pass) {
		this.name = name;
		this.pass = pass;
	}
	public User(String name) {
		this.name = name;
	}
	//getterだけsetterも入れてみる
	public String getName() {
		return name;
	}
	public String getPass() {
		return pass;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
}
