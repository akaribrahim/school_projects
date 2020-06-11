import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Play {
	private int row,column;
	static int totalPoint = 0;

	String[] symbol ={"/","-","+","\\","|"};
	static ArrayList<String> symbols = new ArrayList<String>();

	public Play(int row, int column) {
		this.row = row;
		this.column = column;
	}
	public Play(){
		
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public void letsPlay() throws IOException{
		for(int i=0;i<symbol.length;i++){
			Play.symbols.add(symbol[i]);
		}
		

		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.print("Select coordinate or enter E to end the game: ");
			if(sc.hasNextInt()){
				int selectedRow = sc.nextInt();
				int selectedColumn = sc.nextInt();
				int coordinate = (selectedRow)*10+selectedColumn;
				int counter = 0;
				
				String point=null;
				if(whichJewel(coordinate).compareTo("T")==0){
					Triangle t = new Triangle(coordinate,selectedRow,selectedColumn,getRow(),getColumn());
					counter = t.lookFor(coordinate, selectedRow, selectedColumn);
					if(counter!=0){	
						if(counter==1){
							point = printPoints(coordinate, coordinate-t.getGridColumn(), coordinate-2*t.getGridColumn());	
							t.newGrid(GameGrid.getgameGridList(), coordinate, "up", selectedRow, selectedColumn);
							printGrid();
							System.out.println(point);
						}
						else {
							point = printPoints(coordinate, coordinate+t.getGridColumn(), coordinate+2*t.getGridColumn());
							t.newGrid(GameGrid.getgameGridList(), coordinate, "down", selectedRow, selectedColumn);
							printGrid();
							System.out.println(point);
						}	
					}
					
				}
				
				else if(whichJewel(coordinate).compareTo("S")==0){
					Square s = new Square(coordinate,selectedRow,selectedColumn,getRow(),getColumn());
					counter = s.lookFor(coordinate, selectedRow, selectedColumn);
					if(counter!=0){
						if(counter==1){
							point = printPoints(coordinate, coordinate-1, coordinate-2);
							s.newGrid(GameGrid.getgameGridList(), coordinate, "left", selectedRow, selectedColumn);
							printGrid();
							System.out.println(point);
						}
						else{
							point = printPoints(coordinate, coordinate+1, coordinate+2);
							s.newGrid(GameGrid.getgameGridList(), coordinate, "right", selectedRow, selectedColumn);
							printGrid();
							System.out.println(point);
						}
					}
				}
				
				else if(whichJewel(coordinate).compareTo("D")==0){
					Diamond d = new Diamond(coordinate,selectedRow,selectedColumn,getRow(),getColumn());
					counter = d.lookFor(coordinate, selectedRow, selectedColumn);
					if(counter!=0){
						if(counter==1){
							point = printPoints(coordinate, coordinate-getColumn()-1, coordinate-2*getColumn()-2);
							d.newGrid(GameGrid.getgameGridList(), coordinate, "left_top", selectedRow, selectedColumn);
							printGrid();
							System.out.println(point);
						}
						else if(counter==2){
							point = printPoints(coordinate, coordinate+getColumn()-1, coordinate+2*getColumn()-2);
							d.newGrid(GameGrid.getgameGridList(), coordinate, "left_bot", selectedRow, selectedColumn);
							printGrid();
							System.out.println(point);
						}
						else if(counter==3){
							point = printPoints(coordinate, coordinate-getColumn()+1, coordinate-2*getColumn()+2);
							d.newGrid(GameGrid.getgameGridList(), coordinate, "right_top", selectedRow, selectedColumn);
							printGrid();
							System.out.println(point);
						}
						else if(counter==4){
							point = printPoints(coordinate, coordinate+getColumn()+1, coordinate+2*getColumn()+2);
							d.newGrid(GameGrid.getgameGridList(), coordinate, "right_bot", selectedRow, selectedColumn);
							printGrid();
							System.out.println(point);
						}
					}
				}
				
				else if(whichJewel(coordinate).compareTo("W")==0){
					Wildcard w = new Wildcard(coordinate,selectedRow,selectedColumn,getRow(),getColumn());
					counter = w.lookFor(coordinate, selectedRow, selectedColumn);					
				}
				
				else if(whichJewel(coordinate).compareTo("/")==0){
					MathSymbol m = new MathSymbol(coordinate,selectedRow,selectedColumn,getRow(),getColumn(),"/");
					counter = m.lookFor(coordinate, selectedRow, selectedColumn);
					
				}
				else if(whichJewel(coordinate).compareTo("-")==0){
					MathSymbol m = new MathSymbol(coordinate,selectedRow,selectedColumn,getRow(),getColumn(),"-");
					counter = m.lookFor(coordinate, selectedRow, selectedColumn);
				}
				else if(whichJewel(coordinate).compareTo("+")==0){
					MathSymbol m = new MathSymbol(coordinate,selectedRow,selectedColumn,getRow(),getColumn(),"+");
					counter = m.lookFor(coordinate, selectedRow, selectedColumn);
				}
				else if(whichJewel(coordinate).compareTo("\\")==0){
					MathSymbol m = new MathSymbol(coordinate,selectedRow,selectedColumn,getRow(),getColumn(),"\\");
					counter = m.lookFor(coordinate, selectedRow, selectedColumn);
				}
				else if(whichJewel(coordinate).compareTo("|")==0){
					MathSymbol m = new MathSymbol(coordinate,selectedRow,selectedColumn,getRow(),getColumn(),"|");
					counter = m.lookFor(coordinate, selectedRow, selectedColumn);
				}
			if(counter==0) errorMessage(selectedRow, selectedColumn);
		
			}
			
			else{
				sc.next();
				Leaderboard user = new Leaderboard();
				System.out.println("\nTotal score: "+totalPoint+" points.");
				System.out.print("\nEnter name: ");
				String name = sc.next();
				user.leaderBoard(name, totalPoint);
				
				break;
			}
		}
		
		sc.close();
	}
	public String printPoints(int c1,int c2,int c3){
		int p = calculatePoint(whichJewel(c1), 
				whichJewel(c2), 
				whichJewel(c3));
		return ("\nScore: "+p+" points.");
	}
	public int calculatePoint(String j1,String j2,String j3){	
		totalPoint +=GameGrid.points.get(j1)+GameGrid.points.get(j2)+GameGrid.points.get(j3);
		return GameGrid.points.get(j1)+GameGrid.points.get(j2)+GameGrid.points.get(j3);
	}
	public static void printGrid(){
		System.out.println("");
		int j = 0;
		for(int i=0;i<GameGrid.getgameGridList().size();i++){
			System.out.print(GameGrid.getgameGridList().get(i)+" ");
			j++;
			if(j%10==0) System.out.println("");
		}
		
	}
	public void errorMessage(int selectedRow,int selectedColumn){
		System.out.println("No match found at "+selectedRow+" "+selectedColumn+". Enter a new coordinate.");
	}
	public String whichJewel(int coordinate){
		return GameGrid.getgameGridList().get(coordinate);
	}
}
