

import java.io.IOException;


public class Main {

	public static void main(String[] args) throws IOException, InstantiationException, 
										IllegalAccessException, ClassNotFoundException  {

		
		//First get these two file("customer.txt","order.txt")
		try {
			Customer.readFile("customer.txt");
		} catch (IOException e1) {
			System.out.println("-----------------------"+"\n"
					+ "customer.txt not found."+"\n"
					+ "-----------------------");
			return;
			
		}
		try {
			Order.readFile("order.txt");
		} catch (IOException e) {
			System.out.println("--------------------"+"\n"
					+ "Order.txt not found."+"\n"
					+ "--------------------");
			return;
		}
		//Take "input.txt" as command line argument
		OpenFile.openAndread(args[0]);

		CustAndOrd.writeCust_Ord("order(After_Reading).txt");
		CustAndOrd.writeCust_Ord("customer(After_Reading).txt");
		
	
	}

}
