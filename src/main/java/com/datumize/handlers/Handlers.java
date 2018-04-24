package com.datumize.handlers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.datumize.dtos.AppConstants;
import com.datumize.dtos.Product;
import com.datumize.dtos.ProductLoader;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class Handlers {
	
	
	public static class RootHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange he) throws IOException {
			//String response = "<h1>Server start success if you see this message</h1>" + "<h1>Port: " + Main.port + "</h1>";
			String response = "<h1>Server start success if you see this message</h1>";
			he.sendResponseHeaders(200, response.length());
			OutputStream os = he.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}
	
	
	public static class GetProductsByDeptCategoryProd implements HttpHandler {
		
		String department;
		String category;
		String prodName;
		
		@Override
		public void handle(HttpExchange he) throws IOException {

			department=null;
			category=null;
			prodName=null;
			
			List<Product> productList =  new ProductLoader().loadStock();
			List<Product> filteredProductList = null;
			StringBuffer sbf =new StringBuffer();
			Map<String, Object> parameters = new HashMap<String, Object>();
			URI requestedUri = he.getRequestURI();
			String query = requestedUri.getRawQuery();
			parseQuery(query, parameters);
			
			for (String key : parameters.keySet()) {
				if(key.equalsIgnoreCase("department")) {
					department=parameters.get(key).toString().trim();
				}else if(key.equalsIgnoreCase("category")) {
					category=parameters.get(key).toString().trim();
				}else if(key.equalsIgnoreCase("prodName")) {
					prodName=parameters.get(key).toString().trim();
				}					
			}

			if (department != null && category != null) {
				filteredProductList = productList.stream()
						.filter(product -> product.getCategory().getDepartment().getName().trim().equalsIgnoreCase(department)
								&& product.getCategory().getName().trim().equalsIgnoreCase(category))
						.collect(Collectors.toList());
			} else if (department != null) {
				filteredProductList = productList.stream()
						.filter(product -> product.getCategory().getDepartment().getName().trim().equalsIgnoreCase(department))
						.collect(Collectors.toList());
			} else if (category != null) {
				filteredProductList = productList.stream()
						.filter(product -> product.getCategory().getName().trim().equalsIgnoreCase(category))
						.collect(Collectors.toList());
			}
			
			if (prodName != null) {
				filteredProductList = productList.stream()
						.filter(product -> product.getName().equalsIgnoreCase(prodName))
						.collect(Collectors.toList());
			}
					
			sbf.append("The products are : " + "\n");
			if(filteredProductList.size()>=1) {
				filteredProductList.forEach(product->sbf.append(product.getName()+"\n"));	
			}else {
				sbf.append("Empty");
			}
			
			
			he.sendResponseHeaders(200, sbf.toString().length());
			OutputStream os = he.getResponseBody();
			os.write(sbf.toString().getBytes());
			os.close();
		}

	}
	
	
	public static class AddRemoveListProducts implements HttpHandler {
		
		String addProdName;
		String removeProdName;
		String listCartProds;
		List<Product> cartList = new ArrayList<Product>();
		
		
		@Override
		public void handle(HttpExchange he) throws IOException {

			addProdName=null;
			removeProdName=null;
			listCartProds=null;
			
			List<Product> productList =  new ProductLoader().loadStock();
			
			StringBuffer sbf =new StringBuffer();
			Map<String, Object> parameters = new HashMap<String, Object>();
			URI requestedUri = he.getRequestURI();
			String query = requestedUri.getRawQuery();
			parseQuery(query, parameters);
			
			if(parameters.size()==1) {
				
				for (String key : parameters.keySet()) {
					if(key.equalsIgnoreCase("addProdName")) {
						addProdName=parameters.get(key).toString().trim();
					}else if(key.equalsIgnoreCase("removeProdName")) {
						removeProdName=parameters.get(key).toString().trim();
					}else if(key.equalsIgnoreCase("listCartProds")) {
						listCartProds=parameters.get(key).toString();
					}					
				}
				
				List<Product> prodList = null;

				if (addProdName != null) {
					prodList = productList.stream()
							.filter(product -> product.getName().trim()
									.equalsIgnoreCase(addProdName))
							.collect(Collectors.toList());
					if (prodList != null && prodList.size() >= 1) {
						cartList.addAll(prodList);
						sbf.append("The product " + addProdName
								+ " has been added to the cart.");
					} else {
						sbf.append("The product " + addProdName
								+ " cannot be found in our inventory.");
					}

				} else if (removeProdName != null) {

					Boolean isRemoved = cartList.removeIf(product -> product
							.getName()
							.trim().equalsIgnoreCase(removeProdName));

					if (isRemoved) {
						sbf.append("The product " + removeProdName
								+ " has been removed from the cart.");
					} else {
						sbf.append("The product is not present in the cart.");
					}

				}				
				if (listCartProds != null) {
					if (listCartProds
							.equalsIgnoreCase(AppConstants.TRUE_VALUE)) {
						sbf.append("The products in cart are : " + "\n");
						if (cartList.size() >= 1) {
							cartList.forEach(product -> sbf
									.append(product.getName() + "\n"));
						} else {
							sbf.append("Empty");
						}
					} else {
						sbf.append(
								"Please specify corrrect parameters for Listing Products -- listCartProds=true");
					}

				}
			}			
			else {
				sbf.append("Add, Remove or List can be done only one at a time");
			}
			
			he.sendResponseHeaders(200, sbf.toString().length());
			OutputStream os = he.getResponseBody();
			os.write(sbf.toString().getBytes());
			os.close();
		}

	}
	

	@SuppressWarnings("unchecked")
	public static void parseQuery(String query, Map<String, Object> parameters) throws UnsupportedEncodingException {

		if (query != null) {
			String pairs[] = query.split("[&]");

			for (String pair : pairs) {
				String param[] = pair.split("[=]");

				String key = null;
				String value = null;
				if (param.length > 0) {
					key = URLDecoder.decode(param[0], System.getProperty("file.encoding"));
				}

				if (param.length > 1) {
					value = URLDecoder.decode(param[1], System.getProperty("file.encoding"));
				}

				if (parameters.containsKey(key)) {
					Object obj = parameters.get(key);
					if (obj instanceof List<?>) {
						List<String> values = (List<String>) obj;
						values.add(value);
					} else if (obj instanceof String) {
						List<String> values = new ArrayList<String>();
						values.add((String) obj);
						values.add(value);
						parameters.put(key, values);
					}
				} else {
					parameters.put(key, value);
				}
			}
		}
	}
}
