package util;

import pojo.User;

import config.ConfigReader;

public class UserDataBuilder {

    public static User getRegisterUser() {
        User user = new User();

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

    private static String generateDynamicEmail() {
        return ConfigReader.getProperty("register.email") 
                + System.currentTimeMillis() + "@mail.com";
    }
}
