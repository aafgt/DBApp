package random_6;
import java.awt.Polygon;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

public class DBAppTest {

	public static void main(String[] args) {
		DBApp dbApp = new DBApp();
		
//		String strTableName = "DEL";
		
//		Hashtable<String,String> htblColNameType = new Hashtable<String,String>();
//		htblColNameType.put("id", "java.lang.Integer");
//		htblColNameType.put("name", "java.lang.String"); 
//		htblColNameType.put("gpa", "java.lang.Double");
//		dbApp.createTable(strTableName, "id", htblColNameType);
		
//		Hashtable<String,Object> htblColNameValue = new Hashtable<String,Object>();
//		for(int i=1; i <= 2; i++) {
//			htblColNameValue.clear();
//			htblColNameValue.put("id", new Integer(1)); 
//			htblColNameValue.put("name", "Buddy"); 
//			htblColNameValue.put("gpa", new Double(2)); 
//			try {
//				dbApp.insertIntoTable(strTableName, htblColNameValue);
//			}catch(DBAppException e) {
//				System.out.println(e.getMessage());
//			}
//		}
		
//		int[] x = {0};
//		int[] y = {0};
//		htblColNameValue.clear();
//		htblColNameValue.put("id", new Integer(9));
//		htblColNameValue.put("name", "Buddy2"); 
//		htblColNameValue.put("gpa", new Double(2.23));
//		try {
//			dbApp.updateTable("DEL", "1", htblColNameValue);
//			//dbApp.insertIntoTable(strTableName, htblColNameValue);
//		}catch(DBAppException e) {
//			System.out.println(e.getMessage());
//		}
		
//		htblColNameValue.clear();
//		htblColNameValue.put("pts", new DBAppPolygon(x,y,1));
//		htblColNameValue.put("id", new Integer(1));
//		try {
//			dbApp.deleteFromTable(strTableName, htblColNameValue);
//		}catch(DBAppException e) {
//			System.out.println(e.getMessage());
//		}
//		htblColNameValue.clear();
//		htblColNameValue.put("name", "f");
//		try {
//			dbApp.deleteFromTable("Pets", htblColNameValue);
//		}catch(DBAppException e) {
//			System.out.println(e.getMessage());
//		}
//		htblColNameValue.clear();
//		htblColNameValue.put("id", new Integer(3));
//		dbApp.deleteFromTable(strTableName, htblColNameValue);
//		htblColNameValue.clear();
//		htblColNameValue.put("id", new Integer(4));
//		dbApp.deleteFromTable(strTableName, htblColNameValue);
//		htblColNameValue.clear();
//		htblColNameValue.put("id", new Integer(5));
//		dbApp.deleteFromTable(strTableName, htblColNameValue);
		
//		dbApp.deserializeAll();
//		dbApp.printPages();
//		System.out.println("------------------page Data----------------");
//		for(int i=0; i < dbApp.pages.size(); i++) {
//			System.out.println(dbApp.pages.get(i).fileName);
//			System.out.println(dbApp.pages.get(i).tableName);
//		}
		
//		Hashtable<String,String> htblColNameType = new Hashtable<String,String>();
//		htblColNameType.put("date", "java.util.Date");
//		htblColNameType.put("name", "java.lang.String"); 
//		dbApp.createTable("members", "date", htblColNameType);
	
//		Hashtable<String,Object> htblColNameValue = new Hashtable<String,Object>();
//		//for(int i=1; i <= 5; i++) {
//			htblColNameValue.clear();
//			htblColNameValue.put("date", new Date(99,11,15)); 
//			htblColNameValue.put("name", new String("a")); 
//			try {
//				dbApp.insertIntoTable("members", htblColNameValue);
//			}catch(DBAppException e) {
//				System.out.println(e.getMessage());
//			}
//		//}
		
//		SQLTerm[] arrSQLTerms;
//		arrSQLTerms = new SQLTerm[2];
//		arrSQLTerms[0] = new SQLTerm();
//		arrSQLTerms[1] = new SQLTerm();
//		arrSQLTerms[2] = new SQLTerm();
//		arrSQLTerms[3] = new SQLTerm();
//		
//		arrSQLTerms[0]._strTableName = "Student";
//		arrSQLTerms[0]._strColumnName= "name";
//		arrSQLTerms[0]._strOperator = "=";
//		arrSQLTerms[0]._objValue = "Buddy1";
//		arrSQLTerms[1]._strTableName = "Student";
//		arrSQLTerms[1]._strColumnName= "gpa";
//		arrSQLTerms[1]._strOperator = ">=";
//		arrSQLTerms[1]._objValue = "";
//		String[]strarrOperators = new String[1];
//		strarrOperators[0] = "OR";
//		strarrOperators[1] = "AND";
//		strarrOperators[2] = "OR";
//		// select * from Student where name = “John Noor” or gpa = 1.5;
//		try {
//			Iterator resultSet = dbApp.selectFromTable(arrSQLTerms , strarrOperators);
//			while(resultSet.hasNext()) {
//				Tuple t = (Tuple)resultSet.next();
//				System.out.println(t.htblColNameValue);
//			}
//		}catch(DBAppException e) {
//			System.out.println(e.getMessage());
//		}
		
		
//		BPlusTree t = new BPlusTree();
//		t.insert("1", "a");
//		t.insert("2", "b");
//		t.insert("0", "value");
//		t.insert("3", "a");
//		t.insert("3", "no");
//		System.out.println(t.search("3"));
//		System.out.println(t.searchRange("0", RangePolicy.INCLUSIVE, "3", RangePolicy.INCLUSIVE));
		
//		try {
//			dbApp.createBTreeIndex("DEL", "id");
//		}catch(DBAppException e) {
//			System.out.println(e.getMessage());
//		}
		
//		dbApp.deserializeAllForTree();
//		for(int i=0; i < dbApp.index.size(); i++) {
//			try {
//				System.out.println(dbApp.index.get(i));
//				System.out.println(dbApp.index.get(i).tableName);
//				System.out.println(dbApp.index.get(i).colName);
//				System.out.println(dbApp.index.get(i).fileName);
//				//System.out.println(dbApp.index.get(i).search("2").getPage());
//				//System.out.println(dbApp.index.get(i).search("10").getPage());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
//		try {
//			BPlusTree tree = new BPlusTree();
//			tree.insertKey(1, "a", false);
//			tree.insertKey(1, "a", false);
//			tree.insertKey(1, "a", false);
//			tree.deleteKey(1, true);
//			System.out.println(tree.searchKey(1, false).getValues());
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (InvalidBTreeStateException e) {
//			e.printStackTrace();
//		}
		
		//BPTree t = new BPTree(3,"","");
		//Page p = new Page("");
		//dbApp.serializeForTree(t);
		//Vector<String> v = dbApp.getSerializedFilesForTree();	
		//for(int w=0; w < v.size(); w++) {
			//System.out.println(v.get(w));
		//}
		//Vector<String> v = dbApp.getSerializedFiles();	
		//for(int w=0; w < v.size(); w++) {
			//System.out.println(v.get(w));
		//}
		//dbApp.deserializeForTree("index9.class");
		//dbApp.serialize(p);
		//dbApp.updateSerialize(p, "page34.class");
		
		//dbApp.deserialize("page34.class");
		//dbApp.deleteFile("page34.class");
		
		//String s = dbApp.readData();
		//System.out.println(s);
		
//		String s = "Student, gpa, java.lang.Double, false, true\n" + 
//				"Student, name, java.lang.String, false, true\n" + 
//				"Student, id, java.lang.Integer, true, false\n" + 
//				"Student, TouchDate, java.util.Date, false, false\n" + 
//				"Owners, isOwner, java.lang.Boolean, true, true\n" + 
//				"Owners, name, java.lang.String, false, true\n" + 
//				"Owners, TouchDate, java.util.Date, false, false\n" + 
//				"members, name, java.lang.String, false, false\n" + 
//				"members, date, java.util.Date, true, false\n" + 
//				"members, TouchDate, java.util.Date, false, false\n" + 
//				"Poly, pts, java.awt.Polygon, true, false\n" + 
//				"Poly, id, java.lang.Integer, false, false\n" + 
//				"Poly, TouchDate, java.util.Date, false, false\n" + 
//				"DBPoly, pts, java.awt.Polygon, true, false\n" + 
//				"DBPoly, id, java.lang.Integer, false, false\n" + 
//				"DBPoly, TouchDate, java.util.Date, false, false\n" + 
//				"Pets, name, java.lang.String, false, true\n" + 
//				"Pets, id, java.lang.Integer, true, true\n" + 
//				"Pets, TouchDate, java.util.Date, false, false";
//		dbApp.writeData(s);
		
		//int i = dbApp.getTfile();
		//System.out.println(i);
		
		//dbApp.updateTfile();
		
		//int i = dbApp.getNfile();
		//System.out.println(i);
		
		//dbApp.updateNfile();
		
		//int i = dbApp.getT();
		//System.out.println(i);
		
		//int i = p.getN();
		//System.out.println(i);
		
//		t.insert("a", new Ref("page0.class",3));
//		t.insert("a", new Ref("page1.class",4));
//		System.out.println(t.search("b"));
//		System.out.println(t);
		
		
//		Hashtable<String,String> htblColNameType = new Hashtable<String,String>();
//		htblColNameType.put("id", "java.lang.Integer");
//		htblColNameType.put("name", "java.lang.String"); 
//		htblColNameType.put("gpa", "java.lang.Double");
//		try {
//			dbApp.createTable("Student", "id", htblColNameType);
//		}catch(DBAppException e) {
//			System.out.println(e.getMessage());
//		}
		
//		Hashtable<String,Object> htblColNameValue = new Hashtable<String,Object>();
//		for(int i=1; i <= 1000; i++) {
//			htblColNameValue.clear();
//			htblColNameValue.put("id", new Integer(i)); 
//			htblColNameValue.put("name", "Buddy" + i); 
//			htblColNameValue.put("gpa", new Double(i)); 
//			try {
//				dbApp.insertIntoTable("Student", htblColNameValue);
//			}catch(DBAppException e) {
//				System.out.println(e.getMessage());
//			}
//		}
		
//		htblColNameValue.clear();
//		htblColNameValue.put("id", new Integer(1));
//		htblColNameValue.put("name", "UPDATED[10->1]"); 
//		htblColNameValue.put("gpa", new Double(99.9));
//		try {
//			dbApp.updateTable("Student", "10", htblColNameValue);
//		}catch(DBAppException e) {
//			System.out.println(e.getMessage());
//		}
		
//		htblColNameValue.clear();
//		htblColNameValue.put("id", new Integer(1));
//		htblColNameValue.put("gpa", new Double(1));
//		htblColNameValue.put("name", "Buddy1");
//		htblColNameValue.put("TouchDate", new Date());
//		try {
//			dbApp.deleteFromTable("Student", htblColNameValue);
//		}catch(DBAppException e) {
//			System.out.println(e.getMessage());
//		}
		
//		try {
//			dbApp.createBTreeIndex("Student", "id");
//		}catch(DBAppException e) {
//			System.out.println(e.getMessage());
//		}
		
//		dbApp.deserializeAll();
//		dbApp.printPages();
		
	}

}

