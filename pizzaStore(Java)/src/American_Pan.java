

public class American_Pan extends Pizza {
	int cost = 5;
	
	String name="American_Pan";
	
	public American_Pan(Decorater topping){
		cost += topping.cost();
	}
	
	
	public American_Pan(){
		cost = 5;
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
