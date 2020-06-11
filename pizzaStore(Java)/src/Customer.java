      
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Customer implements IDataAccessObject {
	
	String customerID,customerName,customerSurname,phoneNum,address="";
	static ArrayList<Customer> customerList = new ArrayList<Customer>();
	
	public Customer(String customerID, String customerName,
			String customerSurname, String phoneNum) {
		this.customerID = customerID;
		this.customerName = customerName;
		this.customerSurname = customerSurname;
		this.phoneNum = phoneNum;
	}
	
	public Customer() {
		
	}
	
	/**Before reading input , 
	 * reading pre-registered customer data
	 */
	public static void readFile(String fileName) throws IOException{
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		String str = br.readLine();	
		while(str!=null){
			String[] component = str.split(" ");
			Customer c = new Customer(component[0],component[1],component[2],component[3]);
			for(int i=4;i<component.length;i++){
				c.address += " "+component[i]; 
			}
			Customer.customerList.add(c);
			str=br.readLine();
		}
		br.close();
	}
	
	/**
     *  Read a single entry from file
     * @param ID id of the customer
     * @return  Object customer with given ID
     */
	@Override
	public Customer getByID(String ID) {
		for(Customer i:Customer.customerList){
			if(i.customerID.compareTo(ID)==0) return i;
		}
		return null;	
	}
	
	/**
     *  Add a new object
     * @param object  object to be added
     */
	@Override
	public void add(Customer c) {
		Customer.customerList.add(c);
	}
	
	/**
     *   Delete a single entry from file
     * @param ID  id of customer
     * @return result of operation
     */
	@Override
	public boolean deleteByID(String ID) {
		for(int i=0;i<Customer.customerList.size();i++){
			for(int j=0;j<Order.orderList.size();j++){
				if(Customer.customerList.get(i).customerID.compareTo(ID)==0){
					if(Order.orderList.get(j).customerID.compareTo(ID)==0){
						Customer.customerList.remove(i);
						Order.orderList.remove(j);
						return true;
					}
					else{
						Customer.customerList.remove(i);
						return true;
					}				
				}
			}
		}
		return false;
	}
	
	@Override
	public void update(Customer c) {
		
		
	}
	
	/**
     * Returns all entries  in a list
     * @return   List list of all entries
     */
	@Override
	public ArrayList<Customer> getALL() {
		return Customer.customerList;
	}
	
}
