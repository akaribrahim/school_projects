
public interface Decorater {
	public int cost();
	public Decorater addTopping(String topping) throws InstantiationException, IllegalAccessException, ClassNotFoundException;
}
