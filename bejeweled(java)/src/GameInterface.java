import java.util.ArrayList;


public interface GameInterface {
	int lookFor(int coordinate,int row,int column);
	String whichJewel(int coordinate);
	void newGrid(ArrayList<String> Grid,int coordinate,String where,int row,int column);
}
