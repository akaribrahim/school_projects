
import java.util.Comparator;


public class CompareByName implements Comparator<Customer>{

	@Override
	public int compare(Customer customer1, Customer customer2){
		if(customer1.customerName.compareTo(customer2.customerName)<0)
			return -1;		
		else if(customer1.customerName.compareTo(customer2.customerName)<0)
			return 1;
		else return 0;
			
	}
	public int compare1(Customer customer1,Customer customer2){
		return customer1.customerID.compareTo(customer2.customerID);
		
	}
}
