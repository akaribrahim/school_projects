import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class GameGrid {
	static int rowCount=0;
	static int columnCount;
	private static ArrayList<String> gameGridList = new ArrayList<String>();
	static HashMap<String,Integer> points = new HashMap<String,Integer>();

	public HashMap<String, Integer> getPoints() {
		return points;
	}


	public static ArrayList<String> getgameGridList() {
		return gameGridList;
	}

	
	public static void readGrid(String fileName) throws IOException{
		GameGrid.points.put("D", 30);
		GameGrid.points.put("S", 15);
		GameGrid.points.put("T", 15);
		GameGrid.points.put("W", 10);
		GameGrid.points.put("/", 20);
		GameGrid.points.put("\\", 20);
		GameGrid.points.put("+", 20);
		GameGrid.points.put("-", 20);
		GameGrid.points.put("|", 20);
		
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		String str = br.readLine();

		while(str!=null){
			String[] component = str.split(" ");
			columnCount = component.length;
			
			for(int i=0;i<component.length;i++){
				getgameGridList().add(component[i]);
			}	
			rowCount +=1;
			str = br.readLine();			
		}
		
		int j = 0;
		for(int i=0;i<GameGrid.getgameGridList().size();i++){
			System.out.print(GameGrid.getgameGridList().get(i)+" ");
			j++;
			if(j%10==0) System.out.println("");
		}
		System.out.println("");
		br.close();
		fr.close();
	}
}
