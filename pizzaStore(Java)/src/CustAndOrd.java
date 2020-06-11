import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;


public class CustAndOrd {
	String fileName = null;
	public CustAndOrd(String fileName){
		this.fileName = fileName;
	}
	public static void writeCust_Ord(String fileName) throws IOException{
		FileWriter fw = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(fw);
		if(fileName.compareTo("order(After_Reading).txt")==0){
			for(Order i:Order.orderList){
				bw.write("Order: "+i.orderID+" "+i.customerID);
				for(int j=0;j<i.pizzaType.size();j++){
					if(i.pizzaType.get(j).compareTo("AmericanPan")==0 || 
							i.pizzaType.get(j).compareTo("Neapolitan")==0){
						bw.write("\n");
					}
					bw.write(i.pizzaType.get(j)+" ");
				}
				bw.write("\n");
				if(i.drink == true) bw.write("Softdrink\n");
			}
		}
		else if(fileName.compareTo("customer(After_Reading).txt")==0){
			Collections.sort(Customer.customerList,new CompareByID());
			for(Customer i:Customer.customerList){
				bw.write(i.customerID+" "+i.customerName
						+" "+i.customerSurname+" "+i.phoneNum
						+" "+i.address+"\n");
			}
		}			
		bw.close();
		fw.close();
	}
}

