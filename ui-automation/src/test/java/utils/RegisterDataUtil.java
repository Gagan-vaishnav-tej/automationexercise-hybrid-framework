package utils;

import java.util.HashMap;
import java.util.Map;

import config.ConfigReader;

public class RegisterDataUtil {
	
	public static Map<String, String> getRegisterData() {
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
			    data.put(key, ConfigReader.getProperty("register." + key));
			}
		
			data.put("password", RandomUtil.generatePassword());
		return data;
	}
}
