import java.util.ArrayList;


public class Diamond implements GameInterface {
	private int coordinate,dRow,dColumn,gridRow,gridColumn;
	
	public Diamond(int coordinate, int dRow, int dColumn, int gridRow,int gridColumn) {
		this.coordinate = coordinate;
		this.dRow = dRow;
		this.dColumn = dColumn;
		this.gridRow = gridRow;
		this.gridColumn = gridColumn;
	}
	public Diamond(){
		
	}
	public int getCoordinate() {
		return coordinate;
	}

	public int getdRow() {
		return dRow;
	}

	public int getdColumn() {
		return dColumn;
	}

	public int getGridRow() {
		return gridRow;
	}

	public int getGridColumn() {
		return gridColumn;
	}
	
	@Override
	public int lookFor(int coordinate, int row, int column) {
		if(coordinate>=gridColumn*2 && !Square.checkLeft.contains(coordinate) &&
			((isSame(coordinate, "D") || isSame(coordinate,"W")) && isSame(coordinate-gridColumn-1,"D") && isSame(coordinate-2*gridColumn-2, "D")
			||isMatch(coordinate,"W" , coordinate-gridColumn-1,"W" , coordinate-2*gridColumn-2,"D")
			||isMatch(coordinate,"W" , coordinate-gridColumn-1,"W" , coordinate-2*gridColumn-2,"W")
			||isSame(coordinate, "W") && isSame(coordinate-gridColumn-1, "W") && Play.symbols.contains(whichJewel(coordinate-2*gridColumn-2))
			||isSame(coordinate, "W") && isSame(coordinate-gridColumn-1,"\\") && Play.symbols.contains(whichJewel(coordinate-2*gridColumn-2))
			||isSame(coordinate, "\\") && isSame(coordinate-gridColumn-1, "\\") && Play.symbols.contains(whichJewel(coordinate-2*gridColumn-2)))){
			return 1;
			//newGrid(GameGrid.getgameGridList(), coordinate, "left_top", dRow, dColumn);
		}
	
		else if((coordinate<= (gridRow*gridColumn)-(gridColumn*2)-1) && !Square.checkLeft.contains(coordinate) &&
			((isSame(coordinate, "D") || isSame(coordinate,"W")) && isSame(coordinate+gridColumn-1,"D") && isSame(coordinate+2*gridColumn-2, "D")
			||isMatch(coordinate,"W" , coordinate+gridColumn-1,"W" , coordinate+2*gridColumn-2,"D")
			||isMatch(coordinate,"W" , coordinate+gridColumn-1,"W" , coordinate+2*gridColumn-2,"W")
			||isSame(coordinate, "W") && isSame(coordinate+gridColumn-1, "W") && Play.symbols.contains(whichJewel(coordinate+2*gridColumn-2))
			||isSame(coordinate, "W") && isSame(coordinate+gridColumn-1, "/") && Play.symbols.contains(whichJewel(coordinate+2*gridColumn-2))
			||isSame(coordinate, "/") && isSame(coordinate+gridColumn-1, "/") && Play.symbols.contains(whichJewel(coordinate+2*gridColumn-2)))){
			return 2;
			//newGrid(GameGrid.getgameGridList(), coordinate, "left_bot", dRow, dColumn);
		}
		
		else if(coordinate>=gridColumn*2 && !Square.checkRight.contains(coordinate) && 
			((isSame(coordinate, "D") || isSame(coordinate,"W")) && isSame(coordinate-gridColumn+1,"D") && isSame(coordinate-2*gridColumn+2, "D")
			||isMatch(coordinate,"W" , coordinate-gridColumn+1,"W" , coordinate-2*gridColumn+2,"D")
			||isMatch(coordinate,"W" , coordinate-gridColumn+1,"W" , coordinate-2*gridColumn+2,"W")
			||isSame(coordinate, "W") && isSame(coordinate-gridColumn+1, "W") && Play.symbols.contains(whichJewel(coordinate-2*gridColumn+2))
			||isSame(coordinate, "W") && isSame(coordinate-gridColumn+1, "/") && Play.symbols.contains(whichJewel(coordinate-2*gridColumn+2))
			||isSame(coordinate, "/") && isSame(coordinate, "/") && Play.symbols.contains(whichJewel(coordinate-2*gridColumn+2)))){
			return 3;
			//newGrid(GameGrid.getgameGridList(), coordinate, "right_top", dRow, dColumn);
		}
		
		else if((coordinate<= (gridRow*gridColumn)-(gridColumn*2)-1) && !Square.checkRight.contains(coordinate) &&
			((isSame(coordinate, "D") || isSame(coordinate,"W")) && isSame(coordinate+gridColumn+1,"D") && isSame(coordinate+2*gridColumn+2, "D")
			||isMatch(coordinate,"W" , coordinate+gridColumn+1,"W" , coordinate+2*gridColumn+2,"D")
			||isMatch(coordinate,"W" , coordinate+gridColumn+1,"W" , coordinate+2*gridColumn+2,"W")
			||isSame(coordinate, "W") && isSame(coordinate+gridColumn+1, "W") && Play.symbols.contains(whichJewel(coordinate+2*gridColumn+2))
			||isSame(coordinate, "W") && isSame(coordinate+gridColumn+1, "/") && Play.symbols.contains(whichJewel(coordinate+2*gridColumn+2))
			||isSame(coordinate, "\\") && isSame(coordinate, "\\") && Play.symbols.contains(whichJewel(coordinate+2*gridColumn+2)))){
			return 4;
			//newGrid(GameGrid.getgameGridList(), coordinate, "right_bot", dRow, dColumn);
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
		if(where.compareTo("left_top")==0) support(Grid,"left" ,coordinate,(coordinate-gridColumn-1), (coordinate-2*gridColumn-2));
		else if(where.compareTo("left_bot")==0) support(Grid,"left" ,coordinate,(coordinate+gridColumn-1), (coordinate+2*gridColumn-2));
		else if(where.compareTo("right_top")==0) support(Grid,"right" ,coordinate,(coordinate-gridColumn+1), (coordinate-2*gridColumn+2));
		else if(where.compareTo("right_bot")==0) support(Grid,"right", coordinate,(coordinate+gridColumn+1), (coordinate+2*gridColumn+2));
	}

	public void support(ArrayList<String> Grid,String lr,int c1,int c2,int c3){
		int counter=0,c=c1;
		int count = c1/10;
		for(int j=0;j<3;j++){
			int a=0,b=-1;
			for(int i=0;i<count;i++){
				Grid.set(c+a*gridColumn,Grid.get(c+b*gridColumn));
				a--;
				b--;
			}
			counter++;
			if(counter==1){
				c=c2;
				count=c2/10;
			}
			else if(counter==2){
				c=c3;
				count=c3/10;
			}
		}
		for(int k=0;k<3;k++){
			if(lr.equals("left")) Grid.set(dColumn-k, " ");
			if(lr.equals("right")) Grid.set(dColumn+k, " ");
		}
	}
	
}

