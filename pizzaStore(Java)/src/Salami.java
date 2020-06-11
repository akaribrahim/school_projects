

public class Salami implements Decorater{
	int cost = 3;
	String name = "Salami";
	public Salami(){
		
	}
	public Salami(Decorater topping){
		cost += topping.cost();
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
