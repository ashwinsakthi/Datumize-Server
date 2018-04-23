package com.datumize.handlers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
	//public static int port = 9700;

	public static void main(String[] args) {

		String filePath = "config.properties";
		Properties prop = new Properties();

		try {
			InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(filePath);
			// Loading the properties.
			prop.load(inputStream);
			System.out.println("Properties loaded successfully.");
			SimpleHttpServer httpServer = new SimpleHttpServer();			
			httpServer.Start(Integer.parseInt(prop.getProperty("server.port")));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Start http server

	}
}
