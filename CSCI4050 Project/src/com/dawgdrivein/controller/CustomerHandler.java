package com.dawgdrivein.controller;

import java.util.Date;

import com.dawgdrivein.entity.Customer;
import com.dawgdrivein.view.CustomerHomepageWindow;
import com.dawgdrivein.view.RegistrationWindow;

public class CustomerHandler {
	
	private CustomerHomepageWindow custHomeWindow;
	private RegistrationWindow regWindow;
	private Customer cust;
	
	public Customer getCust(String email)
	{
		
		return null;
	}
	
	public void subscribePromotions(String email)
	{
		
	}
	
	public boolean verifyLogin(String email, String password)
	{
		return true;
	}
	
	public Customer createNewCust(String fN, String lN, String email, Date birthday, String pw)
	{
		return null;
	}
}
