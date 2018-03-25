package com.dawgdrivein.controller;

import java.util.Date;

import com.dawgdrivein.entity.Administrator;
import com.dawgdrivein.view.AdminHomepageWindow;

public class AdminHandler {

	private AdminHomepageWindow adminHomeWindow;
	private Administrator admin;
	
	public Administrator getAdmin(String email)
	{
	
		return null;
	}
	
	public boolean verifyLogin(String email, String password)
	{
		return true;
	}
	
	public Administrator createNewAdmin(String fN, String lN, String email, Date birthday, String pw, String phoneNum)
	{
		return null;
	}
}
