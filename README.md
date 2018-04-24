# Datumize-Server

To run the executable jar use the below command after building the jar using Maven.

java -jar datumize-server-0.0.1-SNAPSHOT-jar-with-dependencies.jar

The below lines should be printed.

Properties loaded successfully.
server started at 9700

The server startup can be tested by accessing the below urls:

http://localhost:9700/

Below are the functionalities implemented:

- List all products from ‘X’ department


http://localhost:9700/getProductsByDeptCategory?department=Grocery

The products are : 
Apple
Guava
Onion
Tomato

- List all products from ‘X’ department and ‘Y’ category


http://localhost:9700/getProductsByDeptCategory?department=Grocery&Category=Fruit

The products are : 
Apple
Guava


- Be able to filter above operations by product name

http://localhost:9700/getProductsByDeptCategory?department=Grocery&Category=Fruit&prodName=Guava

The products are : 
Guava

- Add product to the cart

http://localhost:9700/addRemoveListProducts?addProdName=Guava

The product Guava has been added to the cart.

- List cart products after adding:

http://localhost:9700/addRemoveListProducts?listCartProds=true

The products in cart are : 
Guava

- Remove product from the cart

http://localhost:9700/addRemoveListProducts?removeProdName=Guava

The product Guava has been removed from the cart.

- List cart products after removing

http://localhost:9700/addRemoveListProducts?listCartProds=true

The products in cart are : 
Empty

