
public class Pizza {
	int cost = 0;
	
	
	public Pizza() {
	
	}

	public int cost() {
		return cost;
	}
	public Decorater addTopping(String topping) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Decorater x = ((Decorater)Class.forName(topping.toString()).newInstance());
		cost += x.cost();
		return x;
		
	}
}
