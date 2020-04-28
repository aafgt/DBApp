package random_6;
import java.awt.Polygon;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

public class Page implements Serializable{

	private static final long serialVersionUID = 1;
	
	static int n = getN();
	String tableName;
	//String strClustringKeyColumn;
	Vector<Tuple> page;
	String fileName;
	
//	public Page(Table t) {
//		this.tableName = t.strTableName;
//		this.strClustringKeyColumn = t.strClustringKeyColumn;
//		this.page = new Vector<Tuple>();
//		//pages.add(this.page);
//	}
	
	public Page(String tableName) {
		this.tableName = tableName;
		this.page = new Vector<Tuple>();
		//this.fileName = fileName;
	}
	
	public void addTuple(Tuple t) {
		this.page.add(t);
	}
	
	public void printPage() {
		for(int i=0; i < page.size(); i++) {
			if(!(page.get(i).htblColNameValue == null)) {
				System.out.println(page.get(i).htblColNameValue);
			}
		}
	}
	
	public boolean isPageFull() {
		int c = 0;
		
		if(this.page == null) {
			return false;
		}
		
		for(int i=0; i < this.page.size(); i++) {
			Tuple current = this.page.get(i);
			if(current.htblColNameValue == null) {
				if(c > 0) {
					c--;
				}
			}else {
				c++;
			}
		}
		if(c == this.n) {
			//serialize(this);
			return true;
		}else {
			return false;
		}
	}
	
	public void insertForSort(Vector<Hashtable<String, Object>> v) {
		for(int i=0; i < v.size(); i++) {
			this.page.get(i).htblColNameValue = v.get(i);
		}
	}
	
	public void sortPage(final String clstrKey) {
	    Collections.sort(this.page, new Comparator<Tuple>() {
	        public int compare(Tuple o1, Tuple o2) {
	        	if(o1.htblColNameValue == null || o2.htblColNameValue == null) {
	        		return -1;
	        	}
	        	
	        	Object o = o1.htblColNameValue.get(clstrKey);
        		if(o instanceof Integer) {
	        		Integer o1clstrKey = (Integer)o1.htblColNameValue.get(clstrKey);
		        	Integer o2clstrKey = (Integer)o2.htblColNameValue.get(clstrKey);
		        	return o1clstrKey.compareTo(o2clstrKey);
	        	}else if(o instanceof String) {
	        		String o1clstrKey = (String)o1.htblColNameValue.get(clstrKey);
	        		String o2clstrKey = (String)o2.htblColNameValue.get(clstrKey);
	        		return o1clstrKey.compareTo(o2clstrKey);
	        	}else if(o instanceof Double) {
	        		Double o1clstrKey = (Double)o1.htblColNameValue.get(clstrKey);
	        		Double o2clstrKey = (Double)o2.htblColNameValue.get(clstrKey);
	        		return o1clstrKey.compareTo(o2clstrKey);
	        	}else if(o instanceof Boolean) {
	        		Boolean o1clstrKey = (Boolean)o1.htblColNameValue.get(clstrKey);
	        		Boolean o2clstrKey = (Boolean)o2.htblColNameValue.get(clstrKey);
	        		return o1clstrKey.compareTo(o2clstrKey);
	        	}else if(o instanceof Date) {
	        		Date o1clstrKey = (Date)o1.htblColNameValue.get(clstrKey);
	        		Date o2clstrKey = (Date)o2.htblColNameValue.get(clstrKey);
	        		return o1clstrKey.compareTo(o2clstrKey);
	        	}/*else if(o instanceof Polygon) {
	        		Polygon o1clstrKey = (Polygon)o1.htblColNameValue.get(clstrKey);
	        		Polygon o2clstrKey = (Polygon)o2.htblColNameValue.get(clstrKey);
	        		return o1clstrKey.compareTo(o2clstrKey);
	        	}*/
	        	return -1;
	        }           
	    });
	}
	
//	public void sortPage(final String clstrKey) {
//	    Collections.sort(this.page, new Comparator<Tuple>() {
//	        public int compare(Tuple o1, Tuple o2) {
//	        	if(o1.htblColNameValue == null || o2.htblColNameValue == null) {
//		        	return -1;
//	        	}else {
//	        		Object o = o1.htblColNameValue.get(clstrKey);
//	        		if(o instanceof Integer) {
//		        		Integer o1clstrKey = (Integer)o1.htblColNameValue.get(clstrKey);
//			        	Integer o2clstrKey = (Integer)o2.htblColNameValue.get(clstrKey);
//			        	return o1clstrKey.compareTo(o2clstrKey);
//		        	}else if(o instanceof String) {
//		        		String o1clstrKey = (String)o1.htblColNameValue.get(clstrKey);
//		        		String o2clstrKey = (String)o2.htblColNameValue.get(clstrKey);
//		        		return o1clstrKey.compareTo(o2clstrKey);
//		        	}else if(o instanceof Double) {
//		        		Double o1clstrKey = (Double)o1.htblColNameValue.get(clstrKey);
//		        		Double o2clstrKey = (Double)o2.htblColNameValue.get(clstrKey);
//		        		return o1clstrKey.compareTo(o2clstrKey);
//		        	}else if(o instanceof Boolean) {
//		        		Boolean o1clstrKey = (Boolean)o1.htblColNameValue.get(clstrKey);
//		        		Boolean o2clstrKey = (Boolean)o2.htblColNameValue.get(clstrKey);
//		        		return o1clstrKey.compareTo(o2clstrKey);
//		        	}else if(o instanceof Date) {
//		        		Boolean o1clstrKey = (Boolean)o1.htblColNameValue.get(clstrKey);
//		        		Boolean o2clstrKey = (Boolean)o2.htblColNameValue.get(clstrKey);
//		        		return o1clstrKey.compareTo(o2clstrKey);
//		        	}/*else if(o instanceof Polygon) {
//		        		Polygon o1clstrKey = (Polygon)o1.htblColNameValue.get(clstrKey);
//		        		Polygon o2clstrKey = (Polygon)o2.htblColNameValue.get(clstrKey);
//		        		return o1clstrKey.compareTo(o2clstrKey);
//		        	}*/
//		        	return -1;
//	        	}
//	        	
//	        }           
//	    });
//	}
	
	public static int getN() {
		try {
//			FileReader reader=new FileReader("DBApp.properties");
			FileReader reader=new FileReader("config\\DBApp.properties");
		    Properties p = new Properties();  
		    p.load(reader);
		    String s = p.getProperty("MaximumRowsCountinPage");
		    return Integer.parseInt(s);
		}catch(Exception e) {
			System.out.println("Error");
			return -1;
		}
	}
	
}
