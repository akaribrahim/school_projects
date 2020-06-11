import java.util.ArrayList;


public class MathSymbol implements GameInterface{
	private int coordinate,mRow,mColumn,gridRow,gridColumn;
	private String whichSymbol;
	public MathSymbol(int coordinate, int mRow, int mColumn, int gridRow,int gridColumn,String whichSymbol) {
		this.coordinate = coordinate;
		this.mRow = mRow;
		this.mColumn = mColumn;
		this.gridRow = gridRow;
		this.gridColumn = gridColumn;
		this.whichSymbol= whichSymbol;
	}
	public MathSymbol(){
		
	}
	public int getCoordinate() {
		return coordinate;
	}
	public int getmRow() {
		return mRow;
	}
	public int getmColumn() {
		return mColumn;
	}
	public int getGridRow() {
		return gridRow;
	}
	public int getGridColumn() {
		return gridColumn;
	}
	public String getWhichSymbol() {
		return whichSymbol;
	}

	@Override
	public int lookFor(int coordinate, int row, int column) {
		int counter = 0;
		String point=null;
		if(whichSymbol.equals("/")){
			Diamond d = new Diamond(coordinate,mRow,mColumn,gridRow,gridColumn);
			counter = d.lookFor(coordinate, mRow, mColumn);
			if(counter == 3){
				point = printPoints(coordinate, coordinate-getGridColumn()+1, coordinate-2*getGridColumn()+2);
				d.newGrid(GameGrid.getgameGridList(), coordinate, "right_top", mRow, mColumn);
				Play.printGrid();
				System.out.println(point);
			}
			else if(counter == 2){
				point = printPoints(coordinate, coordinate+getGridColumn()-1, coordinate+2*getGridColumn()-2);
				d.newGrid(GameGrid.getgameGridList(), coordinate, "left_bot", mRow, mColumn);
				Play.printGrid();
				System.out.println(point);
			}
			return 1;
		}
		else if(whichSymbol.equals("-")){
			Square s = new Square(coordinate, mRow, mColumn, gridRow, gridColumn);
			counter = s.lookFor(coordinate, mRow, mColumn);
			if(counter==1){
				point = printPoints(coordinate, coordinate-1, coordinate-2);
				s.newGrid(GameGrid.getgameGridList(), coordinate, "left", mRow, mColumn);
				Play.printGrid();
				System.out.println(point);
			}
			else if(counter==2){
				point = printPoints(coordinate, coordinate+1, coordinate+2);
				s.newGrid(GameGrid.getgameGridList(), coordinate, "right", mRow, mColumn);
				Play.printGrid();
				System.out.println(point);
			}
			return 2;
		}
		else if(whichSymbol.equals("+")){
			Square s = new Square(coordinate, mRow, mColumn, gridRow, gridColumn);
			counter = s.lookFor(coordinate, mRow, mColumn);
			if(counter==1){
				point = printPoints(coordinate, coordinate-1, coordinate-2);
				s.newGrid(GameGrid.getgameGridList(), coordinate, "left", mRow, mColumn);
				Play.printGrid();
				System.out.println(point);
			}
			else if(counter==2){
				point = printPoints(coordinate, coordinate+1, coordinate+2);
				s.newGrid(GameGrid.getgameGridList(), coordinate, "right", mRow, mColumn);
				Play.printGrid();
				System.out.println(point);
			}
			
			Triangle t = new Triangle(coordinate, mRow, mColumn, gridRow, gridColumn);
			counter = t.lookFor(coordinate, mRow, mColumn);
			if(counter==1){
				point = printPoints(coordinate, coordinate-t.getGridColumn(), coordinate-2*t.getGridColumn());
				t.newGrid(GameGrid.getgameGridList(), coordinate, "up", mRow, mColumn);
				Play.printGrid();
				System.out.println(point);
			}
			else if(counter==2){
				point = printPoints(coordinate, coordinate+t.getGridColumn(), coordinate+2*t.getGridColumn());
				t.newGrid(GameGrid.getgameGridList(), coordinate, "down", mRow, mColumn);
				Play.printGrid();
				System.out.println(point);
			}
			return 3;
		}
		else if(whichSymbol.equals("\\")){
			Diamond d = new Diamond(coordinate,mRow,mColumn,gridRow,gridColumn);
			counter = d.lookFor(coordinate, mRow, mColumn);
			if(counter == 1){
				point = printPoints(coordinate, coordinate-getGridColumn()-1, coordinate-2*getGridColumn()-2);
				d.newGrid(GameGrid.getgameGridList(), coordinate, "left_top", mRow, mColumn);
				Play.printGrid();
				System.out.println(point);
			}
			else if(counter == 4){
				point = printPoints(coordinate, coordinate+getGridColumn()+1, coordinate+2*getGridColumn()+2);
				d.newGrid(GameGrid.getgameGridList(), coordinate, "right_bot", mRow, mColumn);
				Play.printGrid();
				System.out.println(point);
			}
			return 4;
		}
		else if(whichSymbol.equals("|")){
			Triangle t = new Triangle(coordinate, mRow, mColumn, gridRow, gridColumn);
			counter = t.lookFor(coordinate, mRow, mColumn);
			if(counter==1){
				point = printPoints(coordinate, coordinate-t.getGridColumn(), coordinate-2*t.getGridColumn());
				t.newGrid(GameGrid.getgameGridList(), coordinate, "up", mRow, mColumn);
				Play.printGrid();
				System.out.println(point);
			}
			else if(counter==2){
				point = printPoints(coordinate, coordinate+t.getGridColumn(), coordinate+2*t.getGridColumn());
				t.newGrid(GameGrid.getgameGridList(), coordinate, "down", mRow, mColumn);
				Play.printGrid();
				System.out.println(point);
			}
			return 5;
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
		// TODO Auto-generated method stub
		
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
