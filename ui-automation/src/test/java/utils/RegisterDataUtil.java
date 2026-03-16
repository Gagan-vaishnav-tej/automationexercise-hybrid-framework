package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import config.ConfigReader;

public class RegisterDataUtil {

	private static Properties prop;

	public static Map<String, String> getRegisterData() {
		prop = ConfigReader.getConfig();
		Map<String, String> data = new HashMap<>();
		String[] keys = {
			    "name",
			    "title",
			    "dob.day",
			    "dob.month",
			    "dob.year",
			    "firstname",
			    "lastname",
			    "company",
			    "address",
			    "country",
			    "state",
			    "city",
			    "zipcode",
			    "mobile"
			};

			for (String key : keys) {
			    data.put(key, prop.getProperty("register." + key));
			}
		
			data.put("password", RandomUtil.generatePassword());
		return data;
	}
}
