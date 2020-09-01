package random_6;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

public class Table implements Serializable{
	
	private static final long serialVersionUID = 3;
	
	static String[] dataTypes = {"java.lang.Integer", "java.lang.String", "java.lang.Double", "java.lang.Boolean", "java.util.Date", "java.awt.Polygon"};
	String strTableName;
	String strClustringKeyColumn;
	Hashtable<String,String> htblColNameType;
	//Vector<Page> tablePages;
	
	public Table(String strTableName, String strClustringKeyColumn, Hashtable<String,String> htblColNameType) throws DBAppException{
		if(checkTableName(strTableName)) {	
			//Error
			//System.out.println("Table name already taken.");
			throw new DBAppException("Table name already taken.");
		}else {
			this.strTableName = strTableName;
			this.strClustringKeyColumn = strClustringKeyColumn;
			this.htblColNameType = htblColNameType;
			
			String data = readData();
			Enumeration<String> s = htblColNameType.keys();
			boolean cFlag = false;
			boolean iFlag = false;
			while(s.hasMoreElements()) {
				String key = s.nextElement();
				String type = htblColNameType.get(key);
				if(isValidDataType(type)) {
					if(key.equals(strClustringKeyColumn)) {
						cFlag = true;
					}
					data += strTableName + ", " + key + ", " + type + ", " + cFlag + ", " + iFlag + "\n";
					cFlag = false;
					iFlag = false;
				}else {
					//error DBAppException
					//System.out.println("Table contains invalid data type.");
					throw new DBAppException("Table contains invalid data type.");
					//break;
				}
			}
			data += strTableName + ", " + "TouchDate" + ", " + "java.util.Date" + ", " + false + ", " + false + "\n";
			writeData(data);
		}
	}
	
//	public void addPage(Page p) {
//		this.tablePages.add(p);
//	}
	
	public static boolean checkTableName(String tableName) {
		String data = readData();
		StringTokenizer st = new StringTokenizer(data, "\n");
		while(st.hasMoreTokens()) {
			String current = st.nextToken();
			StringTokenizer st2 = new StringTokenizer(current, ",");
			while(st2.hasMoreTokens()) {
				String currentTableName = st2.nextToken();
				if(currentTableName.equals(tableName)) {
					return true;
				}
				st2.nextToken(); st2.nextToken(); st2.nextToken(); st2.nextToken();	
			}
		}
		return false;
	}
	
	public static boolean isValidDataType(String dataType) {
		for(int i=0; i < dataTypes.length; i++) {
			if(dataTypes[i].equals(dataType)) {
				return true;
			}
		}
		return false;
	}
	
	public static void writeData(String data) {
		try {
//			FileWriter fw = new FileWriter("metadata.csv");
			FileWriter fw = new FileWriter("data\\metadata.csv");
			fw.write(data);
			fw.close();
		}catch(Exception e) {
			System.out.println("Error.");
		}
	}
	
	public static String readData() {
		String data = "";
		//File file = new File("metadata.csv");
		File file = new File("data\\metadata.csv");
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;
			while((st = br.readLine()) != null) {
				data += st + "\n";
			}
			br.close();
		}catch(FileNotFoundException e) {
			//System.out.println("File not found.");
		}catch(Exception e) {
			System.out.println("Error.");
		}
		
		return data;
	}
	
}
