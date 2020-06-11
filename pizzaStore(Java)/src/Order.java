
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Order {
	
	String orderID,customerID;
	boolean drink = false; 
	ArrayList<String> pizzaType = new ArrayList<String>();
	static ArrayList<Order> orderList = new ArrayList<Order>();

	public Order(String orderID, String customerID) {
		this.orderID = orderID;
		this.customerID = customerID;
	}
	public static void readFile(String fileName) throws IOException{
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		String str = br.readLine();
		Order o = null;
		while(str!=null){
			String[] component = str.split(" ");
			if(component[0].compareTo("Order:")==0){
				o = new Order(component[1],component[2]);
				orderList.add(o);
				str=br.readLine();
			}
			else{
				
				while(component[0].compareTo("Order:")!=0){
					for(int j=0 ; j<component.length ; j++){
						o.pizzaType.add(component[j]);
					}
					str = br.readLine();
					if(str==null) break;
					component = str.split(" ");
				}
			}
			
			
		}
		br.close();
	}
}
