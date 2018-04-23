package com.datumize.handlers;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class SimpleHttpServer {
	
	private HttpServer server;

	public void Start(int port) {
		try {			
			server = HttpServer.create(new InetSocketAddress(port), 0);
			System.out.println("server started at " + port);
			server.createContext("/", new Handlers.RootHandler());			
			server.createContext("/addRemoveListProducts", new Handlers.AddRemoveListProducts());
			server.createContext("/getProductsByDeptCategory", new Handlers.GetProductsByDeptCategoryProd());			
			server.setExecutor(null);
			server.start();			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Stop() {
		server.stop(0);
		System.out.println("server stopped");
	}
}
