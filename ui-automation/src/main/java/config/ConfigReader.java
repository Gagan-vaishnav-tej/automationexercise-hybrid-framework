package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigReader {
	private static Properties prop;
	
	public static void loadConfig() {
		try(FileInputStream fin = new FileInputStream("src/test/resources/config.properties")){
			prop = new Properties();
			prop.load(fin);			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		return prop.getProperty(key);
	}
	
	public static Map<String, String> getRegisterData() {
		Map<String, String> data = new HashMap<>();
		String[] keys = {
			    "name",
			    "email",
			    "title",
			    "dob.day",
			    "dob.month",
			    "dob.year",
			    "firstname",
			    "lastname",
			    "address1",
			    "country",
			    "state",
			    "city",
			    "zipcode",
			    "mobile"
			};

			for (String key : keys) {
			    data.put(key, prop.getProperty("register." + key));
			}
			String pass = prop.getProperty("register.password") + System.currentTimeMillis();
			data.put("password", pass);
		return data;
	}
}
