import java.io.IOException;


public class Main {

	public static void main(String[] args) {
		
		try {
			GameGrid.readGrid("gameGrid.txt");
		} catch (IOException e) {
			System.out.println("gameGrid.txt can not found.");
			return;
		}
		
		Play user = new Play(GameGrid.rowCount,GameGrid.columnCount);
		try {
			user.letsPlay();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
