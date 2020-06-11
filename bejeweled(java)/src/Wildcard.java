import java.util.ArrayList;


public class Wildcard implements GameInterface {
	private int coordinate,wRow,wColumn,gridRow,gridColumn;
	public Wildcard(int coordinate, int wRow, int wColumn, int gridRow,int gridColumn) {
		this.coordinate = coordinate;
		this.wRow = wRow;
		this.wColumn = wColumn;
		this.gridRow = gridRow;
		this.gridColumn = gridColumn;
	}
	public Wildcard(){
		
	}
	public int getCoordinate() {
		return coordinate;
	}

	public int getwRow() {
		return wRow;
	}

	public int getwColumn() {
		return wColumn;
	}

	public int getGridRow() {
		return gridRow;
	}

	public int getGridColumn() {
		return gridColumn;
	}
	
	@Override
	public int lookFor(int coordinate, int row, int column) {
		String point = null;
		Triangle t = new Triangle(coordinate,wRow,wColumn,gridRow,gridColumn);
		int counter = t.lookFor(coordinate, wRow, wColumn);
		if(counter!=0){
			if(counter==1){
				point = printPoints(coordinate, coordinate-t.getGridColumn(), coordinate-2*t.getGridColumn());
				t.newGrid(GameGrid.getgameGridList(), coordinate, "up", wRow, wColumn);
				Play.printGrid();
				System.out.println(point);
			}
			else{
				point = printPoints(coordinate, coordinate+t.getGridColumn(), coordinate+2*t.getGridColumn());
				t.newGrid(GameGrid.getgameGridList(), coordinate, "down", wRow, wColumn);
				Play.printGrid();
				System.out.println(point);
			}
			return 1;
		}
		
		Square s = new Square(coordinate,wRow,wColumn,gridRow,gridColumn);
		counter = s.lookFor(coordinate, wRow, wColumn);
		if(counter!=0){
			if(counter==1){
				point = printPoints(coordinate, coordinate-1, coordinate-2);	
				s.newGrid(GameGrid.getgameGridList(), coordinate, "left", wRow, wColumn);
				Play.printGrid();
				System.out.println(point);
			}
			else{
				point = printPoints(coordinate, coordinate+1, coordinate+2);
				s.newGrid(GameGrid.getgameGridList(), coordinate, "right", wRow, wColumn);
				Play.printGrid();
				System.out.println(point);
			}
			return 2;
		}
		
		Diamond d = new Diamond(coordinate,wRow,wColumn,gridRow,gridColumn);
		counter = d.lookFor(coordinate, wRow, wColumn);
		if(counter!=0){
			if(counter==1){
				point = printPoints(coordinate, coordinate-getGridColumn()-1, coordinate-2*getGridColumn()-2);
				d.newGrid(GameGrid.getgameGridList(), coordinate, "left_top", wRow, wColumn);
				Play.printGrid();
				System.out.println(point);
			}
			else if(counter==2){
				point = printPoints(coordinate, coordinate+getGridColumn()-1, coordinate+2*getGridColumn()-2);
				d.newGrid(GameGrid.getgameGridList(), coordinate, "left_bot", wRow, wColumn);
				Play.printGrid();
				System.out.println(point);
			}
			else if(counter==3){
				point = printPoints(coordinate, coordinate-getGridColumn()+1, coordinate-2*getGridColumn()+2);
				d.newGrid(GameGrid.getgameGridList(), coordinate, "right_top", wRow, wColumn);
				Play.printGrid();
				System.out.println(point);
			}
			else if(counter==4){
				point = printPoints(coordinate, coordinate+getGridColumn()+1, coordinate+2*getGridColumn()+2);
				d.newGrid(GameGrid.getgameGridList(), coordinate, "right_bot", wRow, wColumn);
				Play.printGrid();
				System.out.println(point);
			}
			return 3;
		}
		return 0;
		
	}
	
	@Override
	public String whichJewel(int coordinate) {
		return GameGrid.getgameGridList().get(coordinate);
	}
	
	@Override
	public void newGrid(ArrayList<String> Grid, int coordinate, String where,
			int row, int column) {
		
	}


	public String printPoints(int c1,int c2,int c3){
		return ("\nScore: "+calculatePoint(whichJewel(c1), 
				whichJewel(c2), 
				whichJewel(c3))+" points.");
	}
	public int calculatePoint(String j1,String j2,String j3){
		Play.totalPoint +=GameGrid.points.get(j1)+GameGrid.points.get(j2)+GameGrid.points.get(j3);
		return GameGrid.points.get(j1)+GameGrid.points.get(j2)+GameGrid.points.get(j3);
	}
	
}
