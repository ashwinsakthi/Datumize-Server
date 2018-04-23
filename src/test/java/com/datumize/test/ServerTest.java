package com.datumize.test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.Scanner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.datumize.handlers.Main;
import com.datumize.handlers.SimpleHttpServer;

public class ServerTest {

	static Properties prop = null;
	static SimpleHttpServer httpServer = null;
	// Loading the properties.

	@BeforeClass
	public static void loadProperties() {
		InputStream inputStream = null;
		try {
			String fileName = "config.properties";
			prop = new Properties();
			inputStream = Main.class.getClassLoader().getResourceAsStream(fileName);
			prop.load(inputStream);
			System.out.println("Properties loaded successfully.");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	@BeforeClass
	public static void  startMockServer() {

		if (httpServer == null) {
			httpServer = new SimpleHttpServer();
		}

		httpServer.Start(Integer.parseInt(prop.getProperty("server.port")));
	}

	@AfterClass
	public static void stopMockServer() {
		if (httpServer != null) {
			httpServer.Stop();
		}
	}

	@Test
	public void getProductsByDeptCategoryTest() throws IOException, URISyntaxException {

		String url = prop.getProperty("test.urlwithport");
		System.out.println(url);
		String handlers = "getProductsByDeptCategory";
		String charset = "UTF-8";
		String department = "Grocery";
		String category = "Fruit";
		StringBuffer responseBody = new StringBuffer();
		String query = String.format("department=%s&category=%s", URLEncoder.encode(department, charset),
				URLEncoder.encode(category, charset));

		URLConnection connection = new URL(url + handlers + "?" + query).openConnection();
		connection.setRequestProperty("Accept-Charset", charset);
		InputStream response = connection.getInputStream();

		try (Scanner scanner = new Scanner(response)) {
			responseBody.append(scanner.useDelimiter("\\A").next());
			System.out.println(responseBody);
		}

		assertTrue(responseBody.toString().contains("Apple"));

		assertTrue(responseBody.toString().contains("Guava"));

	}

	@Test
	public void getProductsByDeptTest() throws IOException, URISyntaxException {

		String url = prop.getProperty("test.urlwithport");
		String handlers = "getProductsByDeptCategory";
		String charset = "UTF-8";
		String department = "Grocery";

		StringBuffer responseBody = new StringBuffer();
		String query = String.format("department=%s", URLEncoder.encode(department, charset));

		URLConnection connection = new URL(url + handlers + "?" + query).openConnection();
		connection.setRequestProperty("Accept-Charset", charset);
		InputStream response = connection.getInputStream();

		try (Scanner scanner = new Scanner(response)) {
			responseBody.append(scanner.useDelimiter("\\A").next());
			System.out.println(responseBody);
		}

		assertTrue(responseBody.toString().contains("Apple"));
		assertTrue(responseBody.toString().contains("Guava"));
		assertTrue(responseBody.toString().contains("Onion"));
		assertTrue(responseBody.toString().contains("Tomato"));

	}

	@Test
	public void getProductsByFruitCategoryTest() throws IOException, URISyntaxException {

		String url = prop.getProperty("test.urlwithport");
		String handlers = "getProductsByDeptCategory";
		String charset = "UTF-8";

		String category = "Fruit";
		StringBuffer responseBody = new StringBuffer();
		String query = String.format("category=%s", URLEncoder.encode(category, charset));

		URLConnection connection = new URL(url + handlers + "?" + query).openConnection();
		connection.setRequestProperty("Accept-Charset", charset);
		InputStream response = connection.getInputStream();

		try (Scanner scanner = new Scanner(response)) {
			responseBody.append(scanner.useDelimiter("\\A").next());
			System.out.println(responseBody);
		}

		assertTrue(responseBody.toString().contains("Apple"));
		assertTrue(responseBody.toString().contains("Guava"));

	}

	@Test
	public void getProductsByVegCategoryTest() throws IOException, URISyntaxException {

		String url = prop.getProperty("test.urlwithport");
		String handlers = "getProductsByDeptCategory";
		String charset = "UTF-8";

		String category = "Vegetables";
		StringBuffer responseBody = new StringBuffer();
		String query = String.format("category=%s", URLEncoder.encode(category, charset));

		URLConnection connection = new URL(url + handlers + "?" + query).openConnection();
		connection.setRequestProperty("Accept-Charset", charset);
		InputStream response = connection.getInputStream();

		try (Scanner scanner = new Scanner(response)) {
			responseBody.append(scanner.useDelimiter("\\A").next());
			System.out.println(responseBody);
		}

		assertTrue(responseBody.toString().contains("Onion"));
		assertTrue(responseBody.toString().contains("Tomato"));

	}

}
