package com.automationexercise.api.util;

import config.ConfigReader;
import com.automationexercise.api.payloads.User;

public class UserDataBuilder {

    private static User user;

    public static User getRegisterUser() {
        user = new User();

        user.setName(ConfigReader.getProperty("register.name"));
        user.setEmail(generateDynamicEmail());
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

    public static User updateUser() {
        if (user == null) {
            user = new User();
        }

        user.setName(ConfigReader.getProperty("update.name"));
        user.setEmail(ConfigReader.getProperty("login.email"));
        user.setPassword(ConfigReader.getProperty("login.password"));
        user.setTitle(ConfigReader.getProperty("update.title"));
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

        return user;
    }

    public static void setUser(User createdUser) {
        user = createdUser;
    }

    public static User getUser() {
    	
        if (user == null) {
            throw new RuntimeException("User not created!");
        }
        return user;
    }

    private static String generateDynamicEmail() {
        return ConfigReader.getProperty("register.email") + System.currentTimeMillis() + "@mail.com";
    }
}