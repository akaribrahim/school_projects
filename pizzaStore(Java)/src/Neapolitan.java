

public class Neapolitan extends Pizza{
	int cost = 10;
	String name;
	public Neapolitan(Decorater topping){
		cost += topping.cost();
	}
	
	public Neapolitan() {
	
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
