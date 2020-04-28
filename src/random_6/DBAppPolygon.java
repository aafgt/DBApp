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


//	one issue that arises from using java.awt.Polygon is that it does not implement 
//	the comparable interface, which will hinder your effort to sort a column of type Polygon.
//	To solve this problem, define your own class that will internally retrieve the points from 
//	the passed Polygon. In that class, you can implement the interface comparable
//	and consequently, you will need to add the method compareTo. To compare polygons, you can use 
//	the bounding box to get the enclosing rectangle and use the area of the rectangle, 
//	as follows: Dimension dim = poly1.getBounds( ).getSize( ); nThisArea = dim.width * dim.height;
//	
//	When calling the update method, if the clustering key is a polygon, 
//	the second parameter will pass it as: "(10,20),(30,30),(40,40),(50,60)" 
//	You can use Java's StringTokenizer to process such a string.
