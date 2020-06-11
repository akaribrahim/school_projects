import java.util.ArrayList;


public class Triangle implements GameInterface {
	private int coordinate,tRow,tColumn,gridRow,gridColumn;
	
	public Triangle(int coordinate, int tRow, int tColumn,int gridRow,int gridColumn) {
		this.coordinate = coordinate;
		this.tRow = tRow;
		this.tColumn = tColumn;
		this.gridRow = gridRow;
		this.gridColumn = gridColumn;
	}
	public Triangle(){
		
	}
	public int getGridRow() {
		return gridRow;
	}
	public int getGridColumn() {
		return gridColumn;
	}
	public int getTRow() {
		return tRow;
	}

	public int getTColumn() {
		return tColumn;
	}

	public int getCoordinate() {
		return coordinate;
	}
	
	@Override
	public int lookFor(int coordinate, int tRow, int tColumn) {
		
		if(coordinate>=gridColumn*2 && 
			((isSame(coordinate, "T") || isSame(coordinate,"W")) && isSame(coordinate-gridColumn,"T") && isSame(coordinate-2*gridColumn, "T")
			||isMatch(coordinate,"W" , coordinate-gridColumn,"W" , coordinate-2*gridColumn,"T")
			||isMatch(coordinate,"W" , coordinate-gridColumn,"W" , coordinate-2*gridColumn,"W")
			||isSame(coordinate, "W") && (isSame(coordinate-gridColumn, "+") || isSame(coordinate-gridColumn,"|")) && Play.symbols.contains(whichJewel(coordinate-2*gridColumn))
			||isSame(coordinate, "W") && isSame(coordinate-gridColumn, "W") && Play.symbols.contains(whichJewel(coordinate-2*gridColumn))
			||(isSame(coordinate, "+") || isSame(coordinate,"|")) && (isSame(coordinate-gridColumn, "+") || isSame(coordinate-gridColumn,"|")) && Play.symbols.contains(whichJewel(coordinate-2*gridColumn)))){
			return 1;
			//newGrid(GameGrid.getgameGridList(),coordinate,"up",tRow,tColumn);
		}
		
		else if((coordinate<= (gridRow * gridColumn) - (gridColumn*2) -1) && 
			((isSame(coordinate, "T") || isSame(coordinate,"W")) &&isSame(coordinate+gridColumn,"T") && isSame(coordinate+2*gridColumn, "T")
			||isMatch(coordinate,"W" , coordinate+gridColumn,"W" , coordinate+2*gridColumn,"T")
			||isMatch(coordinate,"W" , coordinate+gridColumn,"W" , coordinate+2*gridColumn,"W")
			||isSame(coordinate, "W") && (isSame(coordinate+gridColumn, "+") || isSame(coordinate+gridColumn,"|")) && Play.symbols.contains(whichJewel(coordinate+2*gridColumn))
			||isSame(coordinate, "W") && isSame(coordinate+gridColumn, "W") && Play.symbols.contains(whichJewel(coordinate+2*gridColumn))
			||(isSame(coordinate, "+") || isSame(coordinate,"|")) && (isSame(coordinate+gridColumn, "+") || isSame(coordinate+gridColumn,"|")) && Play.symbols.contains(whichJewel(coordinate+2*gridColumn)))){
			return 2;
			//newGrid(GameGrid.getgameGridList(),coordinate,"down",tRow,tColumn);
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
	public void newGrid(ArrayList<String> Grid, int coordinate, String where,int tRow, int tColumn) {
		if(where.compareTo("down")==0){
			int a = 2,b = -1;
			
			for(int i=0;i<coordinate/10;i++){
				Grid.set(coordinate+a*getGridRow() , Grid.get(coordinate+b*getGridColumn()));
				a--; 
				b--;
			}
			Grid.set(tColumn, " ");
			Grid.set(tColumn+getGridRow(), " ");
			Grid.set(tColumn+2*getGridRow(), " ");
		}
		else if(where.compareTo("up")==0){
			int a = 0,b = -3;
			for(int i=0;i<(coordinate-2*gridColumn)/10;i++){
				Grid.set(coordinate + a*getGridRow() , Grid.get(coordinate + b*getGridColumn()));
				a--;
				b--;
			}
			Grid.set(tColumn, " ");
			Grid.set(tColumn+getGridRow(), " ");
			Grid.set(tColumn+2*getGridRow(), " ");
		}
		
	}

}
