package random_6;
import java.io.Serializable;
import java.util.Hashtable;

public class Tuple implements Serializable{
	
	private static final long serialVersionUID = 2;

	String strTableName; 
	Hashtable<String,Object> htblColNameValue;
	
	public Tuple(String strTableName, Hashtable<String,Object> htblColNameValue) {
		this.strTableName = strTableName; 
		this.htblColNameValue = new Hashtable<String,Object>();
		this.htblColNameValue = (Hashtable<String, Object>)htblColNameValue.clone();
	}
	
	public boolean checkIfExists() {
		if(htblColNameValue == null) {
			return false;
		}else {
			return true;
		}
	}
	
//	public ArrayList<String> getValues() {
//		ArrayList<String> values = new ArrayList<String>();
//		Enumeration value = htblColNameValue.elements();
//		while(value.hasMoreElements()) {
//			values.add(value.nextElement()+"");
//		}
//		return values;
//	}
	
//	public void getKeyValue() {
//		Enumeration key = htblColNameValue.keys();
//		Enumeration value = htblColNameValue.elements();
//		while(key.hasMoreElements()) {
//			System.out.println(key.nextElement());
//			System.out.println(value.nextElement());
//		}
//	}
	
}
