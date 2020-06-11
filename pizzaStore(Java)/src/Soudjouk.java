

public class Soudjouk implements Decorater {
	int cost = 3;
	String name = "Soudjouk";
	public Soudjouk(){
		
	}
	public Soudjouk(Decorater topping){
		cost += topping.cost();
	}
	
	public int cost() {
		return cost;
	}
	@Override
	public Decorater addTopping(String topping) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Decorater x = ((Decorater)Class.forName(topping.toString()).newInstance());
		cost += x.cost();
		return x;
		
	}

	
	
}
