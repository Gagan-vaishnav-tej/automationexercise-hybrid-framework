package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	private static final Properties prop = new Properties();;
	
	static {
		try(FileInputStream fin = new FileInputStream("src/test/resources/config.properties")){
			prop.load(fin);			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		String value = prop.getProperty(key);
		if (value == null) {
            throw new RuntimeException("Property not found: " + key);
        }
		return value;
	}

}
