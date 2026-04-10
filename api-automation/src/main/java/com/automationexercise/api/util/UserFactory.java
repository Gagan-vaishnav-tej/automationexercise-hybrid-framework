package com.automationexercise.api.util;

import com.automationexercise.api.clients.UserApiClient;
import com.automationexercise.api.payloads.User;

public class UserFactory {

	private static ThreadLocal<User> userThread = new ThreadLocal<User>();
	private static UserApiClient client;
	
	public static User initUser() {
		User user;
		user = UserDataBuilder.getcreateUserData();
		client = new UserApiClient();
		client.resgisterUser(user);
		userThread.set(user);
		return userThread.get();
	}
	
	public static User getUser() {
	    if (userThread.get() == null) {
	        throw new RuntimeException("User not created. Check Hooks.");
	    }
	    return userThread.get();
	}
	
	public static void removeUser() {
		if(userThread.get() != null) {
			client.deleteUserAccount(getUser().getEmail(), getUser().getPassword());
			userThread.remove();
		}
	}
}