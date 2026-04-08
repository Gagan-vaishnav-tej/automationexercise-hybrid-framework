package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	private static final Properties prop = new Properties();;
	
	static 
	{
        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }

            prop.load(input);

        } 
        catch (IOException e) 
        {
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
