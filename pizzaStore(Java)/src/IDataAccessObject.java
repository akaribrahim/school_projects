

import java.util.ArrayList;

public interface IDataAccessObject {
		
	
	Customer getByID(String ID);

    
	boolean deleteByID(String ID);

    
    void add(Customer c);

 
    void update(Customer c);

    
    ArrayList<Customer> getALL(); 
	

}
