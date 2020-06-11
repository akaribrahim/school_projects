import java.util.ArrayList;


public class Square implements GameInterface{
	private int coordinate,sRow,sColumn,gridRow,gridColumn;
	static ArrayList<Integer> checkLeft = new ArrayList<Integer>();
	static ArrayList<Integer> checkRight = new ArrayList<Integer>();

	public Square(int coordinate, int sRow, int sColumn, int gridRow,int gridColumn) {
		this.coordinate = coordinate;
		this.sRow = sRow;
		this.sColumn = sColumn;
		this.gridRow = gridRow;
		this.gridColumn = gridColumn;
	}
	public Square(){
		
	}

	public int getCoordinate() {
		return coordinate;
	}

	public int getsRow() {
		return sRow;
	}

	public int getsColumn() {
		return sColumn;
	}

	public int getGridRow() {
		return gridRow;
	}

	public int getGridColumn() {
		return gridColumn;
	}

	
	@Override
	public int lookFor(int coordinate, int row, int column) {
		for(int i = 0; i<gridRow;i++){
			checkLeft.add(i*gridColumn);
			checkLeft.add(i*gridColumn+1);
		}
		for(int i = 0; i<gridRow;i++){
			checkRight.add((gridColumn-2)+(i*gridColumn));
			checkRight.add((gridColumn-2)+(i*gridColumn)+1);
		}
		
		if(!checkLeft.contains(coordinate) &&  	
			((isSame(coordinate, "S") || isSame(coordinate,"W")) && isSame(coordinate-1,"S") && isSame(coordinate-2, "S")
			||isMatch(coordinate,"W" , coordinate-1,"W" , coordinate-2,"S")
			||isMatch(coordinate,"W" , coordinate-1,"W" , coordinate-2,"W")
			||isSame(coordinate, "W") && isSame(coordinate-1, "W") && Play.symbols.contains(whichJewel(coordinate-2))
			||isSame(coordinate, "W") && (isSame(coordinate-1, "-") || isSame(coordinate-1,"+")) && Play.symbols.contains(whichJewel(coordinate-2))
			||(isSame(coordinate, "-") || isSame(coordinate,"+")) && (isSame(coordinate-1, "-") || isSame(coordinate-1,"+")) && Play.symbols.contains(whichJewel(coordinate-2)))){
			return 1;
			//newGrid(GameGrid.getgameGridList(),coordinate,"left",sRow,sColumn);	
		}
		else if(!checkRight.contains(coordinate) && 
			((isSame(coordinate, "S") || isSame(coordinate,"W")) && isSame(coordinate+1,"S") && isSame(coordinate+2, "S")
			||isMatch(coordinate,"W" , coordinate+1,"W" , coordinate+2,"S")
			||isMatch(coordinate,"W" , coordinate+1,"W" , coordinate+2,"W")
			||isSame(coordinate, "W") && isSame(coordinate+1, "W") && Play.symbols.contains(whichJewel(coordinate+2))
			||isSame(coordinate, "W") && (isSame(coordinate+1, "-") || isSame(coordinate+1,"+")) && Play.symbols.contains(whichJewel(coordinate+2))
			||(isSame(coordinate, "-") || isSame(coordinate,"+")) && (isSame(coordinate+1, "-") || isSame(coordinate+1,"+")) && Play.symbols.contains(whichJewel(coordinate+2)))){
			return 2;
			//newGrid(GameGrid.getgameGridList(),coordinate,"right",sRow,sColumn);
		}
		else return 0;
		
	}
	public boolean isMatch(int c1,String j1, int c2,String j2, int c3,String j3){
		return isSame(c1, j1) && isSame(c2,j2) && isSame(c3, j3);
	}
	public boolean isSame(int coordinate,String jewel){
		return whichJewel(coordinate).equals(jewel);
	}

	
	@Override
	public String whichJewel(int coordinate) {
		return GameGrid.getgameGridList().get(coordinate);
	}

	
	@Override
	public void newGrid(ArrayList<String> Grid, int coordinate, String where,int row, int column) {
		int a=0,b=-1;
		if(where.compareTo("left")==0){
			for(int i=0;i<coordinate/10;i++){
				Grid.set(coordinate+a*gridColumn, Grid.get(coordinate+b*gridColumn));
				Grid.set((coordinate-1)+a*gridColumn, Grid.get((coordinate-1)+b*gridColumn));
				Grid.set((coordinate-2)+a*gridColumn, Grid.get((coordinate-2)+b*gridColumn));
				a--;
				b--;
			}
			Grid.set(sColumn, " ");
			Grid.set(sColumn-1, " ");
			Grid.set(sColumn-2, " ");
		}
		else if(where.compareTo("right")==0){
			for(int i=0;i<coordinate/10;i++){
				Grid.set(coordinate+a*gridColumn, Grid.get(coordinate+b*gridColumn));
				Grid.set((coordinate+1)+a*gridColumn, Grid.get((coordinate+1)+b*gridColumn));
				Grid.set((coordinate+2)+a*gridColumn, Grid.get((coordinate+2)+b*gridColumn));
				a--;
				b--;
			}
			Grid.set(sColumn, " ");
			Grid.set(sColumn+1, " ");
			Grid.set(sColumn+2, " ");
		}
	}





}
