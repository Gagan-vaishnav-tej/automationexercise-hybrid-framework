package com.automationexercise.api.util;

import com.automationexercise.api.payloads.User;

import config.ConfigReader;

public class UserDataBuilder {

    public static User getcreateUserData() {
        User user = new User();

        String email = generateDynamicEmail();
        user.setName(ConfigReader.getProperty("register.name"));
        user.setEmail(email);
        user.setPassword(ConfigReader.getProperty("register.password"));
        user.setTitle(ConfigReader.getProperty("register.title"));
        user.setBirthDate(ConfigReader.getProperty("register.dob.day"));
        user.setBirthMonth(ConfigReader.getProperty("register.dob.month"));
        user.setBirthYear(ConfigReader.getProperty("register.dob.year"));
        user.setFirstName(ConfigReader.getProperty("register.firstname"));
        user.setLastName(ConfigReader.getProperty("register.lastname"));
        user.setCompany(ConfigReader.getProperty("register.company"));
        user.setAddress1(ConfigReader.getProperty("register.address"));
        user.setCountry(ConfigReader.getProperty("register.country"));
        user.setState(ConfigReader.getProperty("register.state"));
        user.setCity(ConfigReader.getProperty("register.city"));
        user.setZipcode(ConfigReader.getProperty("register.zipcode"));
        user.setMobileNumber(ConfigReader.getProperty("register.mobile"));

        return user;
    }
    
    public static User updateUser(User user)
    {
    	user.setTitle(ConfigReader.getProperty("register.title"));
        user.setBirthDate(ConfigReader.getProperty("update.dob.day"));
        user.setBirthMonth(ConfigReader.getProperty("update.dob.month"));
        user.setBirthYear(ConfigReader.getProperty("update.dob.year"));
        user.setFirstName(ConfigReader.getProperty("update.firstname"));
        user.setLastName(ConfigReader.getProperty("update.lastname"));
        user.setCompany(ConfigReader.getProperty("update.company"));
        user.setAddress1(ConfigReader.getProperty("update.address"));
        user.setCountry(ConfigReader.getProperty("update.country"));
        user.setState(ConfigReader.getProperty("update.state"));
        user.setCity(ConfigReader.getProperty("update.city"));
        user.setZipcode(ConfigReader.getProperty("update.zipcode"));
        user.setMobileNumber(ConfigReader.getProperty("update.mobile"));
    	user.setName(ConfigReader.getProperty("update.name"));
    	return user;
    }

	private static String generateDynamicEmail() {
        return ConfigReader.getProperty("register.email") + java.util.UUID.randomUUID() + System.currentTimeMillis()+ System.currentTimeMillis() + "@mail.com";
    }
}