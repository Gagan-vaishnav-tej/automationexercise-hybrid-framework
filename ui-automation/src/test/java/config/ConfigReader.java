package config;

import java.io.FileInputStream;
import java.io.IOException;
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
	
	public static Properties getConfig() {
		return prop;
	}
}
