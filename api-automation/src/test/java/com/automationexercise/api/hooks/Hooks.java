package com.automationexercise.api.hooks;

import com.automationexercise.api.util.UserFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

	
	@Before("@DeleteUser or @UpdateUser")
	public void setUp() {
		UserFactory.initUser();
	}
	
	@After("@CreateUser or @UpdateUser")
	public void tearDown() {
		UserFactory.removeUser();
	}
}
