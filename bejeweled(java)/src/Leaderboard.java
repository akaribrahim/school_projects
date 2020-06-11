import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;


public class Leaderboard {
	
	
	public Leaderboard() {

	}
	
	public void leaderBoard(String name,int totalPoint) throws IOException{
		FileWriter fw = new FileWriter("leaderboard.txt",true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(name+" "+totalPoint);
		bw.newLine();
		
		HashMap<String, Integer> leaderBoard = new HashMap<String, Integer>();
		BufferedReader br = new BufferedReader(new FileReader("leaderboard.txt"));
		String str = br.readLine();
		while(str!=null){
			String[] component = str.split(" ");
			leaderBoard.put(component[0], Integer.parseInt(component[1]));
			str=br.readLine();
		}
		leaderBoard.put(name, totalPoint);
		ArrayList<Integer> sortByPoint = new ArrayList<Integer>(leaderBoard.values());

		Collections.sort(sortByPoint, new Comparator<Integer>() {
			@Override
			public int compare(Integer point1, Integer point2) {
				if(point1>point2)
					return 1;
				else if(point1<point2)
					return -1;
				else
					return 0;
				
			}
		});
		int rank=sortByPoint.size()+1;
		for(Integer i:sortByPoint){
			rank -= 1;
			if(i==totalPoint) break; 
		}
		System.out.println("Your rank is "+rank+"/"+sortByPoint.size());
		System.out.println("\nGood bye!");
		br.close();
		bw.close();
	}
}
