import java.util.Comparator;


public class CompareByID implements Comparator<Customer> {

	@Override
	public int compare(Customer c1, Customer c2) {
		int customerID_1 = Integer.parseInt(c1.customerID);
		int customerID_2 = Integer.parseInt(c2.customerID);
		if(customerID_1>customerID_2)
			return 1;
		else if(customerID_1<customerID_2)
			return -1;
		else
			return 0;
	}

}
