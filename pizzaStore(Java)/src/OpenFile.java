

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class OpenFile {
	String fileName;
	
	public OpenFile(String fileName){
		this.fileName = fileName;
	}
	public static void openAndread(String fileName) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException{	
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		
		FileWriter fw = new FileWriter("output.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		
		ArrayList<String> toppingKinds = new ArrayList<String>();
		toppingKinds.add("Salami");
		toppingKinds.add("Soudjouk");
		toppingKinds.add("HotPepper");
		toppingKinds.add("Onion");
		toppingKinds.add("AmericanPan");
		toppingKinds.add("Neapolitan");
		
		String str = br.readLine();		
		Customer c = new Customer();
		while(str!=null){
			String[] component = str.split(" ");
			if(component[0].compareTo("AddCustomer")==0){
				Customer Adding = new Customer(component[1],component[2],component[3],component[4]);
				for(int i=5;i<component.length;i++){
					Adding.address += " "+component[i]; 
				}
				c.add(Adding);
				bw.write("Customer "+component[1]+" "+component[2]+" added.\n");
			}
			
			else if(component[0].compareTo("RemoveCustomer")==0){
				Customer a = c.getByID(component[1]);
				if(c.deleteByID(component[1])) 
					bw.write("Customer "+component[1]+" "+a.customerName+" removed\n");
				else{
					bw.write("Error Message --> Customer "+component[1]+" not found.");
				}
			}
			
			else if(component[0].compareTo("List")==0){
				ArrayList<Customer> cList = c.getALL();
				bw.write("Customer List:\n");
				Collections.sort(cList,new CompareByName());
				for(int i=0;i<cList.size();i++){
					bw.write(cList.get(i).customerID+" "+
							cList.get(i).customerName+" "+
							cList.get(i).customerSurname+" "+
							cList.get(i).phoneNum+" "+
							cList.get(i).address+"\n");
				}
				
			}
			else if(component[0].compareTo("CreateOrder")==0){
				Order.orderList.add(new Order(component[1],component[2]));
				bw.write("Order "+component[1]+" created.\n");
			}
			else if(component[0].compareTo("RemoveOrder")==0){
				for(int i=0;i<Order.orderList.size();i++){
					if(Order.orderList.get(i).orderID.compareTo(component[1])==0){
						Order.orderList.remove(i);
						bw.write("Order "+component[1]+" removed.\n");
						break;
					}
				}
			}
			else if(component[0].compareTo("AddPizza")==0){
				int counter = 0;
				
				if(component.length>5){
					bw.write("Error Message --> A pizza can include 3 toppings at most.\nPizza order couldn't be added.\n");
					str = br.readLine();
					continue;
				}
				for(int i=2;i<component.length;i++){
					if(!toppingKinds.contains(component[i])){
						bw.write("Error Message --> There is no "+component[i]+".");
						counter = 1;
						break;
					}
				}
				if(counter==1) {
					str=br.readLine();
					continue;
				}
				
				for(Order i:Order.orderList){
					if(i.orderID.compareTo(component[1])==0){
						if(i.pizzaType.isEmpty()){
							i.pizzaType.add(component[2]);
							for(int j=3 ; j<component.length ; j++){
								i.pizzaType.add(component[j]);
							}
						}
						else{
							i.pizzaType.add(component[2]);
							for(int j=3 ; j<component.length ; j++){
								i.pizzaType.add(component[j]);
							}
						}
					}
					
				}
				bw.write(component[2]+" pizza added to order "+component[1]+"\n");
			}
			else if(component[0].compareTo("AddDrink")==0){
				for(Order i:Order.orderList){
					if(i.orderID.compareTo(component[1])==0){
						i.drink = true;
						bw.write("Drink added to order "+component[1]+"\n");
					}
				}
			}
			else if(component[0].compareTo("PayCheck")==0){
				for(Order i:Order.orderList){
					if(i.orderID.compareTo(component[1])==0){
						bw.write("PayCheck for order "+component[1]+"\n");
						whichPizza(i.pizzaType,i.drink,bw);
						
					}
				}
			}
			
			str = br.readLine();	
		}
		br.close();
		bw.close();
		fr.close();
		br.close();
	}
	public static void whichPizza(ArrayList<String> pizzaType,boolean drink,BufferedWriter bw) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		int totalCost=0;
		for(int j=0;j<pizzaType.size();j++){			
			if(pizzaType.size()==1){
				if(pizzaType.get(j).compareTo("AmericanPan")==0){
					American_Pan pizza = new American_Pan();
					totalCost = pizza.cost();
					bw.write("\tAmericanPan "+pizza.cost()+"$\n");
				}
				else{
					Neapolitan pizza = new Neapolitan();
					totalCost = pizza.cost();
					bw.write("\tNeapolitan "+pizza.cost()+"$\n");
				}
					
			}
			else if(pizzaType.get(j).compareTo("AmericanPan")==0){
				American_Pan pizza = new American_Pan();
				int[] j_total = OpenFile.makePizza(pizza,pizzaType,bw,j,totalCost);
				j=j_total[0];
				totalCost=j_total[1];
				
				
			}
			else{
				Neapolitan pizza = new Neapolitan();
				int[] j_total = OpenFile.makePizza(pizza,pizzaType,bw,j,totalCost);
				j=j_total[0];
				totalCost=j_total[1];
			}
		}
		if(drink == true){
			bw.write("\tSoftDrink 1$\n");
			totalCost +=1;
		}
		bw.write("\tTotal: "+totalCost+"$\n");
		
	}
	public static int[] makePizza(Pizza pizza,ArrayList<String> pizzaType,BufferedWriter bw,int j,int totalCost) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		bw.write("\t"+pizzaType.get(j));
		j++;

		while(pizzaType.get(j).compareTo("AmericanPan")!=0 &&
				pizzaType.get(j).compareTo("Neapolitan")!=0){
			pizza.addTopping(pizzaType.get(j));
			bw.write(" "+pizzaType.get(j));
			j++;
			if(j==pizzaType.size()) break;
			
		}
		j--;
		bw.write(" "+pizza.cost()+"$\n");
		totalCost += pizza.cost();
		int[] j_total={j,totalCost};
		return j_total;
	}
}
	
