

public class HotPepper implements Decorater {
	int cost = 1;
	public HotPepper(){
		
	}
	public HotPepper(Decorater topping){
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
