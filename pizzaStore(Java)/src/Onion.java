
public class Onion implements Decorater {
	int cost = 2;
	public Onion(){
		
	}
	public Onion(Decorater topping){
		cost += topping.cost();
	}
	
	@Override
	public int cost() {
		return cost;
	}
	public Decorater addTopping(String topping) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Decorater x = ((Decorater)Class.forName(topping.toString()).newInstance());
		cost += x.cost();
		return x;
		
	}
	
}
