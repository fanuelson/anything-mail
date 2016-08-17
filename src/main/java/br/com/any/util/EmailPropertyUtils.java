package br.com.any.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class EmailPropertyUtils {

	static final String EMAIL_PROPERTIES_FILE_NAME = "email.properties";
	private static Properties prop;

	private EmailPropertyUtils() {
	}

	public static Properties getProperties() {
		try {
			if (prop == null) {
				InputStream is = EmailPropertyUtils.class.getClassLoader().getResourceAsStream(EMAIL_PROPERTIES_FILE_NAME);
				prop = new Properties();
				prop.load(is);
			}
		} catch (IOException e) {
			throw new RuntimeException("ERROR READING RESOURCE: "+EMAIL_PROPERTIES_FILE_NAME, e);
		}
		return prop;
	}
	
	public static String getValue(String key){
		return getProperties().getProperty(key);
	}

}
