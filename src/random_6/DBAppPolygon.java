package random_6;
import java.awt.Dimension;
import java.awt.Polygon;

public class DBAppPolygon extends Polygon implements Comparable{

//	String point1;
//	String point2;
//	String point3;
//	String point4;
	
	public DBAppPolygon() {
		super();
	}
	
	public DBAppPolygon(int xpoints[], int ypoints[], int npoints) {
		super(xpoints,ypoints,npoints);
	}
	
	public int compareTo(Object o) {
		Dimension dim1 = this.getBounds().getSize(); 
		int nThisArea1 = dim1.width * dim1.height;
		
		Dimension dim2 = ((Polygon) o).getBounds().getSize(); 
		int nThisArea2 = dim2.width * dim2.height;
		
		if(nThisArea1 > nThisArea2)
			return 1;
		else
			return 0;
	}
	
}

