package com.datumize.handlers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.datumize.dtos.AppConstants;

public class Main {
	//public static int port = 9700;

	public static void main(String[] args) {

		String fileName = AppConstants.CONFIG_FILE_NAME;

		Properties prop = new Properties();

		try {
			InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(fileName);
			// Loading the properties.
			prop.load(inputStream);
			System.out.println("Properties loaded successfully.");
			SimpleHttpServer httpServer = new SimpleHttpServer();			
			httpServer.Start(Integer
					.parseInt(prop.getProperty(AppConstants.CONFIG_PORT_NAME)));
			
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
