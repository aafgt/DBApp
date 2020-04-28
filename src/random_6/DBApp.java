package random_6;
import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

public class DBApp {

	static Vector<Page> pages = new Vector<Page>();
	
	static Vector<BPTree> index = new Vector<BPTree>();	
	static Vector<RTree> Rindex = new Vector<RTree>();
	static int Nfile = getNfile();
	static int Tfile = getTfile();
	
	static int t = getT();	
	

		
	//	public DBApp() {
	//		
	//	}
		
		public void init() {
			
			//deserializeAll();	
			
			//Vector<String> v = getSerializedFiles();
			//deserializeAll(v);
			//read n in init() from properties file
	//		int n = 10;
	//		try {
	//			FileWriter fw = new FileWriter("metadata.csv");
	//			fw.write("n=" + n);
	//			fw.close();
	//		}catch(Exception e) {
	//			System.out.println("Error.");
	//		}
		}

	public void createTable(String strTableName, String strClustringKeyColumn, Hashtable<String,String> htblColNameType) throws DBAppException {
//		try {
//			new Table(strTableName, strClustringKeyColumn, htblColNameType);
//		}catch(DBAppException e) {
//			System.out.println(e.getMessage());
//		}
		new Table(strTableName, strClustringKeyColumn, htblColNameType);
		//Table e = new Table(strTableName, strClustringKeyColumn, htblColNameType);
		//new Page(e);
		//Page p = new Page(e);
		//serialize(p);
	}
	
	//	public static void insertTuplesInTree(BPlusTree t, Vector<Page> pages, String colName) {
	//		for(int i=0; i < pages.size(); i++) {
	//			Page p = pages.get(i);
	//			for(int j=0; j < p.page.size(); j++) {
	//				Tuple tup = p.page.get(j);
	//				Object o = tup.htblColNameValue.get(colName);
	//				if(o instanceof Integer) {
	//					t.insert((Integer)o, p.fileName);
	//				}else if(o instanceof String) {
	//					t.insert(o+"", p.fileName);
	//				}else if(o instanceof Double) {
	//					t.insert((Double)o, p.fileName);
	//				}else if(o instanceof Boolean) {
	//					t.insert((Boolean)o, p.fileName);
	//				}else if(o instanceof Date) {
	//					t.insert((Date)o, p.fileName);
	//				}
	//				
	//			}
	//		}
	//	}
		
	//	public static boolean isIndexed(String strTableName, String strColName) {
	//		String data = readData();
	//		StringTokenizer t1 = new StringTokenizer(data,"\n");
	//		while(t1.hasMoreTokens()) {
	//			String current = t1.nextToken();
	//			StringTokenizer t2 = new StringTokenizer(current,",");
	//			String tableName = t2.nextToken();
	//			String colName = removeSpaceInFront(t2.nextToken());
	//			String colType = removeSpaceInFront(t2.nextToken());
	//			String clusKey = removeSpaceInFront(t2.nextToken());
	//			String indexed = removeSpaceInFront(t2.nextToken());
	//			if(tableName.equals(strTableName) && colName.equals(strColName)) {
	//				if(indexed.equals("true")) {
	//					return true;
	//				}
	//			}
	//		}
	//		return false;
	//	}
		
		public void createBTreeIndex(String strTableName, String strColName) throws DBAppException {
			//check if col is already indexed;	DONE
			if(!updateMetaDataIndexed(strTableName, strColName)) {
				throw new DBAppException("Column already indexed or does not exist in table or table does not exist.");
			}else {
				deserializeAll();
				
				Vector<Page> tablePages = getTablePages(strTableName);
				//BPlusTree t = new BPlusTree(strTableName,strColName);
				try {
					BPTree tree = new BPTree(t,strTableName,strColName);
					
					insertTuplesInTree(tree,tablePages,strColName);
					index.add(tree);
					//System.out.println(t);
					//serializeForTree(t);
					serializeAllForTree();
					deleteAllIndexFromMem();
					deleteAllPagesFromMem();
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}

	//	public static void insertTuplesInTree(BPlusTree t, Vector<Page> pages, String colName) {
		//		for(int i=0; i < pages.size(); i++) {
		//			Page p = pages.get(i);
		//			for(int j=0; j < p.page.size(); j++) {
		//				Tuple tup = p.page.get(j);
		//				Object o = tup.htblColNameValue.get(colName);
		//				if(o instanceof Integer) {
		//					t.insert((Integer)o, p.fileName);
		//				}else if(o instanceof String) {
		//					t.insert(o+"", p.fileName);
		//				}else if(o instanceof Double) {
		//					t.insert((Double)o, p.fileName);
		//				}else if(o instanceof Boolean) {
		//					t.insert((Boolean)o, p.fileName);
		//				}else if(o instanceof Date) {
		//					t.insert((Date)o, p.fileName);
		//				}
		//				
		//			}
		//		}
		//	}
			
		//	public static boolean isIndexed(String strTableName, String strColName) {
		//		String data = readData();
		//		StringTokenizer t1 = new StringTokenizer(data,"\n");
		//		while(t1.hasMoreTokens()) {
		//			String current = t1.nextToken();
		//			StringTokenizer t2 = new StringTokenizer(current,",");
		//			String tableName = t2.nextToken();
		//			String colName = removeSpaceInFront(t2.nextToken());
		//			String colType = removeSpaceInFront(t2.nextToken());
		//			String clusKey = removeSpaceInFront(t2.nextToken());
		//			String indexed = removeSpaceInFront(t2.nextToken());
		//			if(tableName.equals(strTableName) && colName.equals(strColName)) {
		//				if(indexed.equals("true")) {
		//					return true;
		//				}
		//			}
		//		}
		//		return false;
		//	}
			
			public void createRTreeIndex(String strTableName, String strColName) throws DBAppException {
				if(!updateMetaDataIndexed(strTableName, strColName)) {
					throw new DBAppException("Column already indexed or does not exist in table or table does not exist.");
				}else {
					deserializeAll();
					
					Vector<Page> tablePages = getTablePages(strTableName);
					//BPlusTree t = new BPlusTree(strTableName,strColName);
					try {
						RTree tree = new RTree(t,strTableName,strColName);
						//need to handle duplicates
						insertTuplesInRTree(tree,tablePages,strColName);
						Rindex.add(tree);
						//System.out.println(t);
						//serializeForTree(t);
						serializeAllForTree();
						deleteAllIndexFromMem();
						deleteAllPagesFromMem();
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}

	
	
//	public static void insertTuplesInTree(BPlusTree t, Vector<Page> pages, String colName) {
//		for(int i=0; i < pages.size(); i++) {
//			Page p = pages.get(i);
//			for(int j=0; j < p.page.size(); j++) {
//				Tuple tup = p.page.get(j);
//				Object o = tup.htblColNameValue.get(colName);
//				if(o instanceof Integer) {
//					t.insert((Integer)o, p.fileName);
//				}else if(o instanceof String) {
//					t.insert(o+"", p.fileName);
//				}else if(o instanceof Double) {
//					t.insert((Double)o, p.fileName);
//				}else if(o instanceof Boolean) {
//					t.insert((Boolean)o, p.fileName);
//				}else if(o instanceof Date) {
//					t.insert((Date)o, p.fileName);
//				}
//				
//			}
//		}
//	}
	
//	public static boolean isIndexed(String strTableName, String strColName) {
//		String data = readData();
//		StringTokenizer t1 = new StringTokenizer(data,"\n");
//		while(t1.hasMoreTokens()) {
//			String current = t1.nextToken();
//			StringTokenizer t2 = new StringTokenizer(current,",");
//			String tableName = t2.nextToken();
//			String colName = removeSpaceInFront(t2.nextToken());
//			String colType = removeSpaceInFront(t2.nextToken());
//			String clusKey = removeSpaceInFront(t2.nextToken());
//			String indexed = removeSpaceInFront(t2.nextToken());
//			if(tableName.equals(strTableName) && colName.equals(strColName)) {
//				if(indexed.equals("true")) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
	
	public void insertIntoTable(String strTableName, Hashtable<String,Object> htblColNameValue) throws DBAppException  {
		//try {
			if(!checkDataType(strTableName, htblColNameValue)) {
				throw new DBAppException("Invalid data types or table does not exist.");
				//System.out.println("Invalid data types.");
			}else {
				if(false/*isTableClusteringKeyIndexed(strTableName)*/) {
					//TODO if clstrKey is indexed USE INDEX
					String tableClstrKey = getTableClusteringKey(strTableName);
					Object currentTupleClstKeyValue = htblColNameValue.get(tableClstrKey);
					Vector<BPTree> tableIndex = getTableTrees(strTableName);
					for(int i=0; i < tableIndex.size(); i++) {
						BPTree tree = tableIndex.get(i);
						if(tree.colName.equals(tableClstrKey)) {
							//get max value < than currentTupleCLstKeyValue
							//	HOW?? bruteforce??
							Ref r = tree.search(currentTupleClstKeyValue+"");/////////////XstringX
							if(!(r == null)) {
								String page = r.getPage();
								deserialize(page);
								Page thePage = pages.get(0);
								//cases:
								//1- there is space in page, insert and sort
								//2- page is full, go to next page and check if there is
								//	 	space, insert and sort, else if all pages are full
								//		create a new page
								
							}
						}
					}
				}else {
					deserializeAll();
					//deserializeAllForTree();
					//find table name findTable(String strTableName)
					Date date = new Date();
					htblColNameValue.put("TouchDate", date);
					Tuple e = new Tuple(strTableName, htblColNameValue);
					//check where to serialize the tuple, in which table
					//serialize(e);
					
					Vector<Page> tablePages = getTablePages(strTableName);
					
					boolean flag = true;
					boolean done = false;
					
					if(!pageFound(strTableName) || areAllPagesFull(tablePages)) {					//or page full in one condition
						Page p = new Page(strTableName);
						//Nfile++;//////////////
						p.addTuple(e);
						//p.sortPage(getTableClusteringKey(strTableName));///////////////
						//sortMultiplePages(strTableName);
						pages.add(p);
						sortMultiplePages(strTableName);
						serializeAll();//////////////////////
						deleteAllPagesFromMem();//////////////////////////////
					}else {
						for(int i=0; i < tablePages.size(); i++) {
							if(!done) {
								Page p = tablePages.get(i);
								if(!p.isPageFull()) {
									for(int j=0; j < p.page.size(); j++) {
										Tuple current = p.page.get(j);
										if(current.htblColNameValue == null) {
											current.htblColNameValue = e.htblColNameValue;
											current.strTableName = e.strTableName;
											//p.sortPage(getTableClusteringKey(strTableName));///////////////
											sortMultiplePages(strTableName);
											flag = false;
											done = true;
											serializeAll();//////////////////////
											deleteAllPagesFromMem();///////////////////////////
											break;
										}
									}
									if(flag) {
										p.addTuple(e);
										//p.sortPage(getTableClusteringKey(strTableName));///////////////
										sortMultiplePages(strTableName);
										done = true;
										
										//if(p.isPageFull()) {
										//	serialize(p);
										//}
										serializeAll();/////////////////////////
										deleteAllPagesFromMem();////////////////////////////
										break;
									}
								}
							}
							else {
								break;
							}
						} 
					}
				}
			}
		//}catch(DBAppException e) {
		//	System.out.println(e.getMessage());
		//}
	}
	
	public void updateTable(String strTableName, String strClusteringKey, Hashtable<String,Object> htblColNameValue) throws DBAppException{
		//try {
			//Vector<String> indexedColNames = isIndexed(strTableName);
			if(isTableClusteringKeyIndexed(strTableName)) {
				
				//deserializeAll();
				String tableClstrKey = getTableClusteringKey(strTableName);
				Vector<BPTree> tableIndex = getTableTrees(strTableName);
				for(int i=0; i < tableIndex.size(); i++) {
					BPTree tree = tableIndex.get(i);
					if(tree.colName.equals(tableClstrKey)) {
						Ref r = tree.search(strClusteringKey);/////////////XstringX
						if(!(r == null)) {
							String page = r.getPage();
							deserialize(page);
							Page thePage = pages.get(0);
							for(int j=0; j < thePage.page.size(); j++) {
								Tuple t = thePage.page.get(j);
								if(!(t.htblColNameValue == null)) {
									if(isDeleted(strClusteringKey, tableClstrKey, t.htblColNameValue)) {
										//Hashtable<String, Object> h = new Hashtable<String, Object>();
										//h = (Hashtable<String, Object>) htblColNameValue.clone();
										//Date date = new Date();
										//h.put("TouchDate", date);
										//t.htblColNameValue = h;
										
										Vector<String> keysToBeInserted = getConditionsKey(htblColNameValue);
										for(int k=0; k < keysToBeInserted.size(); k++) {
											t.htblColNameValue.replace(keysToBeInserted.get(k), htblColNameValue.get(keysToBeInserted.get(k)));
										}
										Date date = new Date();
										t.htblColNameValue.replace("TouchDate", date);
										
										Hashtable<String, Object> h = new Hashtable<String, Object>();
										h = (Hashtable<String, Object>) t.htblColNameValue.clone();
										
										//deserializeAll();
										//sortMultiplePages(strTableName);
										
										
										tree.delete(strClusteringKey);
										//tree.insert(t.htblColNameValue.get(tableClstrKey)+"", new Ref(thePage.fileName));
										tree.insert(h.get(tableClstrKey)+"", new Ref(thePage.fileName));
										
										//serializeAll();
										//deleteAllPagesFromMem();
										//serializeAllForTree();
										//deleteAllIndexFromMem();
									}
								}
							}
							
							serialize(thePage);
							deleteAllPagesFromMem();
							
						}
					}
				}
				
				deserializeAll();
				sortMultiplePages(strTableName);
				serializeAll();
				deleteAllPagesFromMem();
				
				
				serializeAllForTree();
				deleteAllIndexFromMem();
				
			}else {
				deserializeAll();
				//deserializeAllForTree();
				
				//Date(int year, int month, int date)
				for(int i=0; i < pages.size(); i++) {
					Page p = pages.get(i); 
					if(p.tableName.equals(strTableName)) {
						if(!checkDataType(strTableName, htblColNameValue)) {
							throw new DBAppException("Invalid data types.");
							//Error invalid data types
							//System.out.println("Invalid data types.");
						}else {
							//search clusteringkey==strkey
							//remove current tuple
							//insert htblcolnamevalue
							String tableClstrKey = getTableClusteringKey(strTableName);
							
							for(int j=0; j < p.page.size(); j++) {
								Tuple t = p.page.get(j);
								
								if(!(t.htblColNameValue == null)) {
									if(isDeleted(strClusteringKey, tableClstrKey, t.htblColNameValue)) {
										//Hashtable<String, Object> h = new Hashtable<String, Object>();
										//h = (Hashtable<String, Object>) htblColNameValue.clone();
										//Date date = new Date();
										//h.put("TouchDate", date);
										//t.htblColNameValue = htblColNameValue;
										//t.htblColNameValue = h;
										
										Vector<String> keysToBeInserted = getConditionsKey(htblColNameValue);
										for(int k=0; k < keysToBeInserted.size(); k++) {
											t.htblColNameValue.replace(keysToBeInserted.get(k), htblColNameValue.get(keysToBeInserted.get(k)));
										}
										Date date = new Date();
										t.htblColNameValue.replace("TouchDate", date);
										
										//p.sortPage(tableClstrKey);///////////////
										sortMultiplePages(strTableName);
										
										//if(p.isPageFull()) {
										//	updateSerialize(p, p.fileName);
										//}
										serializeAll();//////////////////////
										deleteAllPagesFromMem();///////////////////////////
									}
								}
								
								//String value = t.htblColNameValue.get(strClusteringKey) + "";
								//check strKey == t.thatKey
							}
						}
					}
				}
			}
		//}catch(DBAppException e) {
		//	System.out.println(e.getMessage());
		//}
	}
	
	public void deleteFromTable(String strTableName, Hashtable<String,Object> htblColNameValue) throws DBAppException{
		//try {
			boolean aTupleWasDeleted = false;
			if(!checkDataType(strTableName, htblColNameValue)) {
				throw new DBAppException("Invalid data types or table does not exist.");
				//System.out.println("invalid data types.");
			}else {
				Vector<String> indexedColNames = isIndexed(strTableName);
				
				Vector<String> TheconditionsKey = getConditionsKey(htblColNameValue);
				Vector<String> TheconditionsValue = getConditionsValue(htblColNameValue);
				String col = getIndexedCol(htblColNameValue, indexedColNames);
				
				if(!indexedColNames.isEmpty() && goToIndexDel(col,indexedColNames)) {
					
					//get index if any of the htblColNameValue....
					
					Vector<BPTree> tableIndex = getTableTrees(strTableName);
					for(int i=0; i < tableIndex.size(); i++) {
						BPTree tree = tableIndex.get(i);
						if(tree.colName.equals(col)) {
							Ref r = tree.search(htblColNameValue.get(col)+"");
							if(!(r == null)) {
								String page = r.getPage();
								deserialize(page);
								Page thePage = pages.get(0);
								if(thePage.tableName.equals(strTableName)) {
									for(int j=0; j < thePage.page.size(); j++) {
										Tuple t = thePage.page.get(j);
										Vector<String> values = new Vector<String>();
										//////////////////////////////////////////////////
										for(int k=0; k < TheconditionsKey.size(); k++) {
											String key = TheconditionsKey.get(k);
											if(t.checkIfExists()) {
												String newValue = t.htblColNameValue.get(key)+"";
												values.add(newValue);
											}
										}
										//////////////////////////////////////////////////
										if(!values.isEmpty()) {
											if(equalValues(TheconditionsValue, values)) {
												//del
												//t = null
												
												tree.delete(t.htblColNameValue.get(col)+"");
												
												t.htblColNameValue = null;
												aTupleWasDeleted = true;
												//p.sortPage(getTableClusteringKey(strTableName));///////////////
												//
												updateSerialize(thePage, thePage.fileName);
												updateSerialize(tree, tree.fileName);
											}
										}
									}
								}
								if(checkEmptyPage(thePage)) {
									thePage.page = null;
									//p = null;
									pages.remove(thePage);///////////////////////
									//delete the empty page file
									deleteFile(thePage.fileName);
								}
							}
						}
					}
					deleteAllPagesFromMem();
					deleteAllIndexFromMem();
				}else {
					deserializeAll();
					//deserializeAllForTree();
					
					Vector<String> conditionsKey = getConditionsKey(htblColNameValue);
					Vector<String> conditionsValue = getConditionsValue(htblColNameValue);
					for(int i=0; i < pages.size(); i++) {
						Page p = pages.get(i); 
						if(p.tableName.equals(strTableName)) {
							for(int j=0; j < p.page.size(); j++) {
								Tuple t = p.page.get(j);
								Vector<String> values = new Vector<String>();
								//////////////////////////////////////////////////
								for(int k=0; k < conditionsKey.size(); k++) {
									String key = conditionsKey.get(k);
									if(t.checkIfExists()) {
										String newValue = t.htblColNameValue.get(key)+"";
										values.add(newValue);
									}
								}
								//////////////////////////////////////////////////
								if(!values.isEmpty()) {
									if(equalValues(conditionsValue, values)) {
										//del
										//t = null
										
										Vector<BPTree> tableIndex = getTableTrees(strTableName);
										BPTree tree = tableIndex.get(0);
										tree.delete(t.htblColNameValue.get(indexedColNames.get(0))+"");
										
										t.htblColNameValue = null;
										aTupleWasDeleted = true;
										//p.sortPage(getTableClusteringKey(strTableName));///////////////
										
										updateSerialize(p, p.fileName);
										updateSerialize(tree, tree.fileName);
									}
								}
							}
						}
						if(checkEmptyPage(p)) {
							p.page = null;
							//p = null;
							pages.remove(p);///////////////////////
							//delete the empty page file
							deleteFile(p.fileName);
						}
					}
					deleteAllPagesFromMem();/////////////////////////
					deleteAllIndexFromMem();
				}
				
				if(!aTupleWasDeleted) {
					throw new DBAppException("Tuple does not exist.");
				}
				
			}
		//}catch(DBAppException e) {
		//	System.out.println(e.getMessage());
		//}
	}

	public Iterator selectFromTable(SQLTerm[] arrSQLTerms, String[] strarrOperators) throws DBAppException {
		//deserializeAll();
		//deserializeAllForTree();
		//checkStatement,checkForSelection
		//try {
			if(!checkForSelection(arrSQLTerms) || !checkStatement(arrSQLTerms, strarrOperators)) {
				throw new DBAppException("Invalid Statement or table is empty or invalid data types.");
			}else {
				Vector<String> indexedColNames = isIndexed(arrSQLTerms[0]._strTableName);
				if(false/*!indexedColNames.isEmpty()*/) {
					
					Vector<Tuple> result = new Vector<Tuple>();
					
					Vector<BPTree> tableIndex = getTableTrees(arrSQLTerms[0]._strTableName);
					for(int i=0; i < tableIndex.size(); i++) {
						BPTree tree = tableIndex.get(i);
						if(tree.colName.equals(arrSQLTerms[0]._strColumnName)) {
							//TODO need to loop on sqlterms to search or WHAT!!??
							Ref r = tree.search(arrSQLTerms[0]._objValue+"");
							if(!(r == null)) {
								String page = r.getPage();
								deserialize(page);
								Page thePage = pages.get(0);
							}
						}
					}
					
					result = removeDuplicates(result);
					Iterator r = result.iterator();
					serializeAll();
					serializeAllForTree();
					return r;
				}else {
					deserializeAll();
					
					Vector<Tuple> result = new Vector<Tuple>();
					//checkDataType();getDataType();getTableDataTypes()
					String tableName = arrSQLTerms[0]._strTableName;
					Vector<Page> pages = getTablePages(tableName);
					
					//Hashtable<String,Object> conditions = getConditions(arrSQLTerms);
					//Vector<Tuple> partialResult = new Vector<Tuple>();
					
					if(arrSQLTerms.length == 1) {
						result = getResultsOfCondition(arrSQLTerms[0], pages);
					}else {
						for(int i=0; i < arrSQLTerms.length-1; i++) {
							SQLTerm s1 = arrSQLTerms[i];
							SQLTerm s2 = arrSQLTerms[i+1];
							
							if(i == 0) {
								Vector<Tuple> partialResult = getResultsOfConditions(strarrOperators[i], s1, s2, pages);
								result = merge(result,partialResult);
							}else {
								if(strarrOperators[i].equals("OR")) {
									Vector<Tuple> partialResult = getResultsOfConditions(strarrOperators[i], s1, s2, pages);
									result = merge(result,partialResult);
									
								}else {
									Vector<Tuple> partialResult = getResultsOfConditions2(strarrOperators[i], s1, s2, result);
									//result = merge(result,partialResult);
									result = (Vector<Tuple>) partialResult.clone();
								}
							}
							
						}
					}
					result = removeDuplicates(result);
					//result = partialResult;
					Iterator r = result.iterator();
					serializeAll();
					serializeAllForTree();
					return r;
				}
			}
		//}catch(DBAppException e) {
		//	e.getMessage();
		//}
	}
	
	public static boolean goToIndexDel(String col, Vector<String> cols) {
		for(int i=0; i < cols.size(); i++) {
			if(cols.get(i).equals(col)) {
				return true;
			}
		}
		return false;
	}

	public static void insertTuplesInTree(BPTree t, Vector<Page> pages, String colName) {
		for(int i=0; i < pages.size(); i++) {
			Page p = pages.get(i);
			for(int j=0; j < p.page.size(); j++) {
				Tuple tup = p.page.get(j);
				if(!(tup.htblColNameValue == null)) {
					Object o = tup.htblColNameValue.get(colName);
					String s = o+"";
					t.insert(s, new Ref(p.fileName));
					
				}
			}
		}
	}
	
	public static void insertTuplesInRTree(RTree t, Vector<Page> pages, String colName) {
		for(int i=0; i < pages.size(); i++) {
			Page p = pages.get(i);
			for(int j=0; j < p.page.size(); j++) {
				Tuple tup = p.page.get(j);
				if(!(tup.htblColNameValue == null)) {
					Object o = tup.htblColNameValue.get(colName);
					String s = o+"";
					t.insert(s, new Ref(p.fileName));
					
				}
			}
		}
	}

	public static Vector<String> isIndexed(String strTableName) {
		
		Vector<String> colNames = new Vector<String>();
		
		String data = readData();
		StringTokenizer t1 = new StringTokenizer(data,"\n");
		while(t1.hasMoreTokens()) {
			String current = t1.nextToken();
			StringTokenizer t2 = new StringTokenizer(current,",");
			String tableName = t2.nextToken();
			String colName = removeSpaceInFront(t2.nextToken());
			String colType = removeSpaceInFront(t2.nextToken());
			String clusKey = removeSpaceInFront(t2.nextToken());
			String indexed = removeSpaceInFront(t2.nextToken());
			if(tableName.equals(strTableName) && indexed.equals("true")) {
				colNames.add(colName);
			}
		}
		return colNames;
	}

	public static boolean updateMetaDataIndexed(String strTableName, String strColName) {
		boolean isIndexed = true;
		String newData = "";
		String data = readData();
		StringTokenizer t1 = new StringTokenizer(data,"\n");
		while(t1.hasMoreTokens()) {
			String current = t1.nextToken();
			StringTokenizer t2 = new StringTokenizer(current,",");
			String tableName = t2.nextToken();
			String colName = removeSpaceInFront(t2.nextToken());
			String colType = removeSpaceInFront(t2.nextToken());
			String clusKey = removeSpaceInFront(t2.nextToken());
			String indexed = removeSpaceInFront(t2.nextToken());
			if(tableName.equals(strTableName) && colName.equals(strColName)) {
				if(indexed.equals("false")) {
					indexed = "true";
					isIndexed = false;
				}
			}
			newData += tableName + ", " + colName + ", " + colType + ", " + clusKey + ", " + indexed + "\n";
		}
		writeData(newData);
		if(isIndexed) {
			return false;
		}else {
			return true;
		}
	}

	public static Polygon makePolygon(String p) {
		//"(10,20),(30,30),(40,40),(50,60)" in update	DONE
		boolean flag = true;
		Vector<Integer> xx = new Vector<Integer>();
		Vector<Integer> yy = new Vector<Integer>();
		StringTokenizer st = new StringTokenizer(p,")");
		while(st.hasMoreTokens()) {
			String current = st.nextToken();
			if(flag) {
				current = current.substring(1);
				flag = false;
			}else {
				current = current.substring(2);
			}
			StringTokenizer st2 = new StringTokenizer(current,",");
			xx.add(Integer.parseInt(st2.nextToken()));
			yy.add(Integer.parseInt(st2.nextToken()));
		}
		
		int[] x = new int[xx.size()];
		int[] y = new int[yy.size()];
		for(int i=0; i < xx.size(); i++) {
			x[i] = xx.get(i);
			y[i] = yy.get(i);
		}
		return new Polygon(x,y,xx.size());
	}
	
	public static Vector<BPTree> getTableTrees(String tableName){
		deserializeAllForTree();
		Vector<BPTree> r = new Vector<BPTree>();
		for(int i=0; i < index.size(); i++) {
			BPTree tree = index.get(i);
			if(tree.tableName.equals(tableName)) {
				r.add(tree);
			}
		}
		return r;
	}
	
	public static boolean isTableClusteringKeyIndexed(String strtableName) {
		String data = readData();
		StringTokenizer t1 = new StringTokenizer(data,"\n");
		while(t1.hasMoreTokens()) {
			String current = t1.nextToken();
			StringTokenizer t2 = new StringTokenizer(current,",");
			String tableName = t2.nextToken();
			String colName = removeSpaceInFront(t2.nextToken());
			String colType = removeSpaceInFront(t2.nextToken());
			String clusKey = removeSpaceInFront(t2.nextToken());
			String indexed = removeSpaceInFront(t2.nextToken());
			if(tableName.equals(strtableName) && indexed.equals("true") && clusKey.equals("true")) {
				return true;
			}
		}
		return false;
	}
	
	public static String getIndexedCol(Hashtable<String,Object> htblColNameValue, Vector<String> indexedColNames) {
		Vector<String> cols = getConditionsKey(htblColNameValue);
		for(int i=0; i < cols.size(); i++) {
			String col = cols.get(i);
			for(int j=0; j < indexedColNames.size(); j++) {
				String col2 = indexedColNames.get(j);
				if(col.equals(col2)) {
					return col;
				}
			}
		}
		return "";
	}
	
	public static Vector<Tuple> removeDuplicates(Vector<Tuple> a) {
		Vector<Tuple> result = new Vector<Tuple>();
		for(int i=0; i < a.size(); i++) {
			Tuple t1 = a.get(i);
			for(int j=0; j < a.size(); j++) {
				Tuple t2 = a.get(j);
				if(t1 == t2 && !(i==j)) {
					if(!result.contains(t1)) {
						result.add(t1);
					}
				}else {
					if(!result.contains(t1)) {
						result.add(t1);
					}
					if(!result.contains(t2)) {
						result.add(t2);
					}
				}
			}
		}
		return result;
	}
	
//	public static boolean checkForSelection(SQLTerm[] arrSQLTerms) {
//		for(int i=0; i < arrSQLTerms.length; i++) {
//			String tableName = arrSQLTerms[i]._strTableName;
//			String colName = arrSQLTerms[i]._strColumnName;
//			String op = arrSQLTerms[i]._strOperator;
//			
//			Vector<Page> thePages;
//			if(checkTableExists(tableName)) {
//				thePages = getTablePages(tableName);
//			}else {
//				thePages = null;
//			}
//			
//			if(thePages == null) {
//				return false;
//			}else {
//				if(checkColExists(colName, thePages) && checkOp(op)) {
//					continue;
//				}else {
//					return false;
//				}
//			}
//			
//		}
//		return true;
//	}
	
	public static boolean checkForSelection(SQLTerm[] arrSQLTerms) {
		for(int i=0; i < arrSQLTerms.length; i++) {
			String tableName = arrSQLTerms[i]._strTableName;
			String colName = arrSQLTerms[i]._strColumnName;
			String op = arrSQLTerms[i]._strOperator;
			
			Object obj = arrSQLTerms[i]._objValue;
			String objType = getTypeForSelect(obj);
			
			Hashtable<String,String> dataTypes = getTableDataTypes(tableName);
			String validDataType = dataTypes.get(colName);
			
			if(!objType.equals(validDataType)) {
				return false;
			}
			
			Vector<Page> thePages;
			if(checkTableExists(tableName)) {
				thePages = getTablePages(tableName);
			}else {
				thePages = null;
			}
			
			if(thePages == null) {
				return false;
			}else {
				if(checkColExists(colName, thePages) && checkOp(op)) {
					continue;
				}else {
					return false;
				}
			}
			
		}
		return true;
	}
	
	public static boolean checkTableExists(String tableName) {
		for(int i=0; i < pages.size(); i++) {
			Page p = pages.get(i);
			if(p.tableName.equals(tableName)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkColExists(String colName, Vector<Page> pages) {
		Hashtable<String,Object> t = pages.get(0).page.get(0).htblColNameValue;
		return t.get(colName) == null ? false : true;	
	}
	
	public static boolean checkOp(String op) {
		switch(op) {
			case ">":	return true;  
			case ">=":	return true;
			case "<":	return true;
			case "<=":	return true;
			case "!=":	return true;
			case "=":	return true;
			default:	return false;
		}
	}
	
	public static boolean checkStatement(SQLTerm[] arrSQLTerms, String[] strarrOperators) {
		if(strarrOperators.length == 1 && strarrOperators[0] == null) {
			return true;
		}
		int l = arrSQLTerms.length;
		int l2 = strarrOperators.length;
		
		return (l == (l2+1)) ? true : false;
	}
	
	public static Vector<Tuple> getResultsOfConditions2(String operator, SQLTerm s1, SQLTerm s2, Vector<Tuple> tuples) {
		
		Vector<Tuple> result = new Vector<Tuple>();
		String columnName1 = s1._strColumnName;
		String operator1 = s1._strOperator;
		Object value1 = s1._objValue;
		
		String columnName2 = s2._strColumnName;
		String operator2 = s2._strOperator;
		Object value2 = s2._objValue;
		
		String type1 = getType(value1);
		String type2 = getType(value2);
		
		for(int i=0; i < tuples.size(); i++) {
			Tuple t = tuples.get(i);
			String tColumn1 = t.htblColNameValue.get(columnName1)+"";
			String tColumn2 = t.htblColNameValue.get(columnName2)+"";
			
			if(operator.equals("AND")) {
				if(isResult(type1,operator1,tColumn1,value1) && isResult(type2,operator2,tColumn2,value2)) {
					result.add(t);
				}
			}else if(operator.equals("OR")) {
				if(isResult(type1,operator1,tColumn1,value1) || isResult(type2,operator2,tColumn2,value2)) {
					result.add(t);
				}
			}else if(operator.equals("XOR")) {
				if(isResult(type1,operator1,tColumn1,value1) ^ isResult(type2,operator2,tColumn2,value2)) {
					result.add(t);
				}
			}
		}
		return result;
	}
		
	public static Vector<Tuple> getResultsOfConditions(String operator, SQLTerm s1, SQLTerm s2, Vector<Page> pages) {
		
		Vector<Tuple> result = new Vector<Tuple>();
		String columnName1 = s1._strColumnName;
		String operator1 = s1._strOperator;
		Object value1 = s1._objValue;
		
		String columnName2 = s2._strColumnName;
		String operator2 = s2._strOperator;
		Object value2 = s2._objValue;
		
		String type1 = getType(value1);
		String type2 = getType(value2);
		
		for(int i=0; i < pages.size(); i++) {
			Page p = pages.get(i);
			for(int j=0; j < p.page.size(); j++) {
				Tuple t = p.page.get(j);
				String tColumn1 = t.htblColNameValue.get(columnName1)+"";
				String tColumn2 = t.htblColNameValue.get(columnName2)+"";
				
				if(operator.equals("AND")) {
					if(isResult(type1,operator1,tColumn1,value1) && isResult(type2,operator2,tColumn2,value2)) {
						result.add(t);
					}
				}else if(operator.equals("OR")) {
					if(isResult(type1,operator1,tColumn1,value1) || isResult(type2,operator2,tColumn2,value2)) {
						result.add(t);
					}
				}else if(operator.equals("XOR")) {
					if(isResult(type1,operator1,tColumn1,value1) ^ isResult(type2,operator2,tColumn2,value2)) {
						result.add(t);
					}
				}
				
			}
		}
		return result;
	}
	
	public static Vector<Tuple> getResultsOfCondition(SQLTerm s, Vector<Page> pages) {
		Vector<Tuple> result = new Vector<Tuple>();
		String columnName = s._strColumnName;
		String operator = s._strOperator;
		Object value = s._objValue;
		
		String type = getType(value);
		
		for(int i=0; i < pages.size(); i++) {
			Page p = pages.get(i);
			for(int j=0; j < p.page.size(); j++) {
				Tuple t = p.page.get(j);
				String tColumn = t.htblColNameValue.get(columnName)+""; 
				if(isResult(type,operator,tColumn,value)) {
					result.add(t);
				}
			}
		}
		return result;
	}
	
	public static boolean isResult(String type, String operator, String tColumn, Object value) {
		if(type.equals("Integer")) {
			Integer v = (Integer)value;
			Integer tC = Integer.parseInt(tColumn);
			switch(operator) {
			case ">":	return tC > v ? true:false; 
			case ">=":	return tC >= v ? true:false;
			case "<":	return tC < v ? true:false; 
			case "<=":	return tC <= v ? true:false; 
			case "!=":	return !tC.equals(v) ? true:false; 
			case "=":	return tC.equals(v) ? true:false; 
		}
		}else if(type.equals("String")) {
			String v = value+"";
			switch(operator) {
			//case ">":	tColumn > value ? true:false; break;
			//case ">=":	tColumn >= value ? true:false; break;
			//case "<":	tColumn < value ? true:false; break;	
			//case "<=":	tColumn <= value ? true:false; break;
			case "!=":	return !tColumn.equals(v) ? true:false; 
			case "=":	return tColumn.equals(v) ? true:false; 
		}
		}else if(type.equals("Double")) {
			Double v = (Double)value;
			Double tC = Double.parseDouble(tColumn);
			switch(operator) {
			case ">":	return tC > v ? true:false; 
			case ">=":	return tC >= v ? true:false; 
			case "<":	return tC < v ? true:false;
			case "<=":	return tC <= v ? true:false; 
			case "!=":	return !tC.equals(v) ? true:false; 
			case "=":	return tC.equals(v) ? true:false; 
		}
		}else if(value instanceof Boolean) {
			Boolean v = (Boolean)value;
			Boolean tC = Boolean.parseBoolean(tColumn);
			switch(operator) {
			//case ">":	return tC > v ? true:false; break;
			//case ">=":	return tC >= v ? true:false; break;
			//case "<":	return tC < v ? true:false; break;	
			//case "<=":	return tC <= v ? true:false; break;
			case "!=":	return !tC.equals(v) ? true:false; 
			case "=":	return tC.equals(v) ? true:false;
		}
		}else if(value instanceof Date) {
			try {
				Date v = (Date)value;
				String z3 = v+"";
				String z2 = z3.substring(0,19) + z3.substring(23);
				Date vv = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy").parse(z2);
				v = vv;
				//Wed Dec 15 00:00:00 EET 1999
				//"dd/MM/yyyy"
				String z = tColumn.substring(0,19) + tColumn.substring(23);
				tColumn = z;
				Date tC = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy").parse(tColumn);
				switch(operator) {
				case ">":	return tC.compareTo(v) > 0 ? true:false;
				case ">=":	return tC.compareTo(v) > 0 || tC.compareTo(v) == 0 ? true:false; 
				case "<":	return tC.compareTo(v) < 0 ? true:false;
				case "<=":	return tC.compareTo(v) < 0 || tC.compareTo(v) == 0 ? true:false; 
//				case ">":	return tC > v ? true:false; break;
//				case ">=":	return tC >= v ? true:false; break;
//				case "<":	return tC < v ? true:false; break;	
//				case "<=":	return tC <= v ? true:false; break;
				case "!=":	return !(tC.compareTo(v) == 0) ? true:false; 
				case "=":	return tC.compareTo(v) == 0 ? true:false; 
//				case "!=":	return !tC.equals(v) ? true:false; 
//				case "=":	return tC.equals(v) ? true:false; 
			}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//Date tC = (Date)tColumn;
		}//else if(value instanceof Polygon) {
//			DBAppPolygon v = (DBAppPolygon)value;
//			DBAppPolygon tC = (DBAppPolygon)tColumn;
//			switch(operator) {
//			case ">":	tColumn > value ? true:false; break;
//			case ">=":	tColumn >= value ? true:false; break;
//			case "<":	tColumn < value ? true:false; break;	
//			case "<=":	tColumn <= value ? true:false; break;
//			case "!=":	tColumn != value ? true:false; break;
//			case "=":	tColumn == value ? true:false; break;
//		}
//		}
		return false;
		
	}
	
	public static String getType(Object value) {
		if(value instanceof Integer) {
			return "Integer";
		}else if(value instanceof String) {
			return "String";
		}else if(value instanceof Double) {
			return "Double";
		}else if(value instanceof Boolean) {
			return "Boolean";
		}else if(value instanceof Date) {
			return "Date";
		}else if(value instanceof Polygon) {
			return "Polygon";
		}else {
			return "";
		}
	}
	
	public static String getTypeForSelect(Object value) {
		if(value instanceof Integer) {
			return "java.lang.Integer";
		}else if(value instanceof String) {
			return "java.lang.String";
		}else if(value instanceof Double) {
			return "java.lang.Double";
		}else if(value instanceof Boolean) {
			return "java.lang.Boolean";
		}else if(value instanceof Date) {
			return "java.util.Date";
		}else if(value instanceof Polygon) {
			return "java.awt.Polygon";
		}else {
			return "";
		}
	}
	
	public static Vector<Tuple> merge(Vector<Tuple> a, Vector<Tuple> b) {
		Vector<Tuple> r = new Vector<Tuple>();
		r.addAll(a);
		r.addAll(b);
		return r;
	}
	
	public static void serializeForTree(Object t) {
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("data\\" + "index" + Tfile + ".class");	
	         //new FileOutputStream(fileName + ".ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         
	         //BPlusTree p = (BPlusTree)t;
	         BPTree p = (BPTree)t;
	         p.fileName = "index" + Tfile + ".class";
	         // keep track,index of which table and on what col	DONE
	         
	         out.writeObject(t);
	         out.close();
	         fileOut.close();
	         Tfile++;
	         updateTfile();
	         //System.out.println("Serialized data is saved.");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	
	public static void serializeForRTree(Object t) {
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("data\\" + "index" + Tfile + ".class");	
	         //new FileOutputStream(fileName + ".ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         
	         //BPlusTree p = (BPlusTree)t;
	         RTree p = (RTree)t;
	         p.fileName = "index" + Tfile + ".class";
	         // keep track,index of which table and on what col	DONE
	         
	         out.writeObject(t);
	         out.close();
	         fileOut.close();
	         Tfile++;
	         updateTfile();
	         //System.out.println("Serialized data is saved.");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	
	public static void serializeAllForTree() {
		for(int i=0; i < index.size(); i++) {
			if(index.get(i).fileName == null) {
				serializeForTree(index.get(i));
			}else {
				updateSerialize(index.get(i), index.get(i).fileName);
			}
		}
	}
	
	public static void serializeAllForRTree() {
		for(int i=0; i < index.size(); i++) {
			if(Rindex.get(i).fileName == null) {
				serializeForRTree(Rindex.get(i));
			}else {
				updateSerialize(Rindex.get(i), Rindex.get(i).fileName);
			}
		}
	}
	
	public static void deserializeAllForTree() {
		Vector<String> v = getSerializedFilesForTree();
		for(int i=0; i < v.size(); i++) {
			try {
				deserializeForTree(v.get(i));
			}catch(Exception e) {
				deserializeForRTree(v.get(i));
			}
		}
	}
	
	public static Vector<String> getSerializedFilesForTree() {
		//File folder = new File("C:\\Users\\amirw\\eclipse-workspace\\DBApp\\data");
		//File folder = new File("\\data");
		File folder2 = new File("data");
		String ss = folder2.getAbsolutePath();
		File folder = new File(ss);
		
		 FileFilter filter = new FileFilter() { 
			  
             public boolean accept(File f) 
             { 
                 return f.getName().endsWith("class") && f.getName().startsWith("index");
             } 
         }; 
		
		File[] listOfFiles = folder.listFiles(filter);
		
		Vector<String> serializedFiles = new Vector<String>(); 
		for (int i = 0; i < listOfFiles.length; i++) {
			String s = listOfFiles[i].getName()+"";
			serializedFiles.add(s);
			//System.out.println("File " + listOfFiles[i].getName());
		}
		return serializedFiles;
	}
	
	public static void deserializeForTree(String fileName) {
		//Table t = null;
	    try {
	       FileInputStream fileIn = new FileInputStream("data\\" + fileName);
	       ObjectInputStream in = new ObjectInputStream(fileIn);
	       //BPlusTree p = (BPlusTree) in.readObject();
	       BPTree p = (BPTree) in.readObject();
	       in.close();
	       fileIn.close();
	       //return p;
	       
	       index.add(p);
	       //System.out.println("deserialized.");
	       //p.printPage();////
	       //System.out.println(p.page);
	       //System.out.println(p.page.get(0).htblColNameValue);
	       //System.out.println(t.strTableName);
	       //System.out.println(t.strClustringKeyColumn);
	       //System.out.println(t.htblColNameType);
	    } catch (IOException i) {
	       i.printStackTrace();
	       //return;
	    } catch (ClassNotFoundException c) {
	       System.out.println("class not found");
	       c.printStackTrace();
	       //return;
	    }
	}
	
	public static void deserializeForRTree(String fileName) {
		//Table t = null;
	    try {
	       FileInputStream fileIn = new FileInputStream("data\\" + fileName);
	       ObjectInputStream in = new ObjectInputStream(fileIn);
	       //BPlusTree p = (BPlusTree) in.readObject();
	       RTree p = (RTree) in.readObject();
	       in.close();
	       fileIn.close();
	       //return p;
	       
	       Rindex.add(p);
	       //System.out.println("deserialized.");
	       //p.printPage();////
	       //System.out.println(p.page);
	       //System.out.println(p.page.get(0).htblColNameValue);
	       //System.out.println(t.strTableName);
	       //System.out.println(t.strClustringKeyColumn);
	       //System.out.println(t.htblColNameType);
	    } catch (IOException i) {
	       i.printStackTrace();
	       //return;
	    } catch (ClassNotFoundException c) {
	       System.out.println("class not found");
	       c.printStackTrace();
	       //return;
	    }
	}
	
	public static void deleteAllIndexFromMem() {
		int s = index.size();
		for(int i=0; i < s; i++) {
			index.remove(0);
		}
	}
	
	public static void deleteAllRIndexFromMem() {
		int s = Rindex.size();
		for(int i=0; i < s; i++) {
			Rindex.remove(0);
		}
	}
	
	public static void serializeAll() {
		for(int i=0; i < pages.size(); i++) {
			if(pages.get(i).fileName == null) {
				serialize(pages.get(i));
			}else {
				updateSerialize(pages.get(i), pages.get(i).fileName);
			}
		}
	}
	
	public static void serialize(Object t) {
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("data\\" + "page" + Nfile + ".class");	
	         //new FileOutputStream(fileName + ".ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         
	         Page p = (Page)t;
	         p.fileName = "page" + Nfile + ".class";
	         
	         out.writeObject(t);
	         out.close();
	         fileOut.close();
	         Nfile++;
	         updateNfile();
	         //System.out.println("Serialized data is saved.");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	
	public static void updateSerialize(Object t, String fileName) {
		try {
//	         FileOutputStream fileOut =
//	         new FileOutputStream("C:\\Users\\amirw\\eclipse-workspace\\DBApp\\data\\" + fileName);
			 FileOutputStream fileOut =
			 new FileOutputStream("data\\" + fileName);
	         //new FileOutputStream(fileName + ".ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(t);
	         out.close();
	         fileOut.close();
	         //System.out.println("Serialized data is saved.");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}

	public static void deserializeAll() {
		Vector<String> v = getSerializedFiles();
		for(int i=0; i < v.size(); i++) {
			deserialize(v.get(i));
		}
	}
	
	public static Vector<String> getSerializedFiles() {
//		File folder = new File("C:\\Users\\amirw\\eclipse-workspace\\DBApp\\data");
		File folder2 = new File("data");
		String ss = folder2.getAbsolutePath();
		File folder = new File(ss);
		
		 FileFilter filter = new FileFilter() { 
			  
             public boolean accept(File f) 
             { 
                 return f.getName().endsWith("class") && f.getName().startsWith("page"); 
             } 
         }; 
		
		File[] listOfFiles = folder.listFiles(filter);
		
		Vector<String> serializedFiles = new Vector<String>(); 
		for (int i = 0; i < listOfFiles.length; i++) {
			String s = listOfFiles[i].getName()+"";
			serializedFiles.add(s);
			//System.out.println("File " + listOfFiles[i].getName());
		}
		return serializedFiles;
	}
	
	public static void deserialize(String fileName) {
		//Table t = null;
	    try {
	       FileInputStream fileIn = new FileInputStream("data\\" + fileName);
	       ObjectInputStream in = new ObjectInputStream(fileIn);
	       Page p = (Page) in.readObject();
	       in.close();
	       fileIn.close();
	       //return p;
	       
	       pages.add(p);
	       //System.out.println("deserialized.");
	       //p.printPage();////
	       //System.out.println(p.page);
	       //System.out.println(p.page.get(0).htblColNameValue);
	       //System.out.println(t.strTableName);
	       //System.out.println(t.strClustringKeyColumn);
	       //System.out.println(t.htblColNameType);
	    } catch (IOException i) {
	       i.printStackTrace();
	       //return;
	    } catch (ClassNotFoundException c) {
	       System.out.println("class not found");
	       c.printStackTrace();
	       //return;
	    }
	}
	
	public static void deleteFile(String fileName) {
//		File f = new File("C:\\Users\\amirw\\eclipse-workspace\\DBApp\\data\\" + fileName);
		File f = new File("data\\" + fileName);
		f.delete();
	}
	
	public static void deleteAllPagesFromMem() {
		int s = pages.size();
		for(int i=0; i < s; i++) {
			pages.remove(0);
		}
	}
	
	public static Vector<Hashtable<String, Object>> getSimpleTuples(Vector<Tuple> v) {
		Vector<Hashtable<String, Object>> vv = new Vector<Hashtable<String, Object>>();
		for(int i=0; i < v.size(); i++) {
			vv.add(v.get(i).htblColNameValue);
		}
		return vv;
	}
	
	public static void sortMultiplePages(String tableName) {
		//put all tuples of pages in one page and sort then fill pages
		//and delete if page got empty
		//and it should not be required to create
		Vector<Page> tablePages = getTablePages(tableName);
		Vector<Tuple> v = getAllTuplesOfTableSorted(tableName);
		Vector<Hashtable<String, Object>> vvv = getSimpleTuples(v);
		for(int i=0; !vvv.isEmpty(); i++) {
			Page p = tablePages.get(i);
			Vector<Hashtable<String, Object>> vv = new Vector<Hashtable<String, Object>>();
			for(int j=0; j < p.n && !vvv.isEmpty(); j++) {
				vv.add(vvv.remove(0));
			}
			p.insertForSort(vv);
		}
	}
	
//	public static void sortMultiplePages(String tableName) {
//		//put all tuples of pages in one page and sort then fill pages
//		//and delete if page got empty
//		//and it should not be required to create
//		int j = 0;
//		Vector<Page> tablePages = getTablePages(tableName);
//		Vector<Tuple> v = getAllTuplesOfTableSorted(tableName);
//		//int ss = v.size();	//i < ss forloop condition
//		for(int i=0; !v.isEmpty(); i++) {
//			Page p = tablePages.get(i);
//			Vector<Tuple> vv = toInsertForSortInPage(p.n, v);
//			p.insertForSort(vv);
//			int s = vv.size();
//			for(int k=0; k < s; k++) {
//				v.remove(0);
//				//v.remove(vv.get(k));
//			}
//		}
//	}

//for(int i=0; i < tablePages.size(); i++) {
//	Page p = tablePages.get(i);
//	Vector<Tuple> vv = new Vector<Tuple>();
//	for(int k=0; k < p.n && !v.isEmpty(); k++) {
//		vv.add(v.get(j));
//		v.remove(j);
//		j++;
//	}
//	p.insertForSort(vv);
	
	public static Vector<Tuple> toInsertForSortInPage(int n, Vector<Tuple> v){
		Vector<Tuple> r = new Vector<Tuple>(); 
		for(int i=0; i < v.size() && i < n; i++) {
			r.add(v.get(i));
		}
		return r;
	}
	
	public static Vector<Tuple> getAllTuplesOfTableSorted(String tableName){
		Vector<Tuple> r = new Vector<Tuple>();
		Page thePage = new Page(tableName);
		Vector<Page> tablePages = getTablePages(tableName);
		for(int i=0; i < tablePages.size(); i++) {
			Page p = tablePages.get(i);
			for(int j=0; j < p.page.size(); j++) {
				Tuple t = p.page.get(j);
				r.add(t);
				//t.htblColNameValue = null;////////////////////
			}
		}
		thePage.page = r;
		thePage.sortPage(getTableClusteringKey(tableName));
		r = thePage.page;
		//return thePage;
		return r;
	}
	
	public static boolean pageFound(String tableName) {
		for(int i=0; i < pages.size(); i++) {
			if(tableName.equals(pages.get(i).tableName)) {
				return true;
			}
		}
		return false;
	}
	
/////////////////////////////////////////////////
	public static Vector<Page> getTablePages(String tableName){
		Vector<Page> r = new Vector<Page>();
		for(int i=0; i < pages.size(); i++) {
			Page p = pages.get(i);
			if(p.tableName.equals(tableName)) {
				r.add(p);
			}
		}
		return r;
	}
/////////////////////////////////////////////////
	
	public static boolean areAllPagesFull(Vector<Page> v) {
		for(int i=0;i < v.size(); i++) {
			if(!v.get(i).isPageFull()) {
				return false;
			}
		}
		return true;
	}
	
	
	
//	public void insertIntoTable(String strTableName, Hashtable<String,Object> htblColNameValue) {
//		//find table name findTable(String strTableName)
//		Tuple e = new Tuple(strTableName, htblColNameValue);
//		//check where to serialize the tuple, in which table
//		//serialize(e);
//		
//		boolean flag = true;
//		
//		if(!pageFound(strTableName)) {					//or page full in one condition
//			Page p = new Page(strTableName);
//			p.addTuple(e);
//			pages.add(p);
//		}else {
//			for(int i=0; i < pages.size(); i++) {
//				Page p = pages.get(i);
//				if((p.tableName).equals(strTableName)) {
//					if(p.page.size() == p.n) {
//						Page p1 = new Page(strTableName);
//						p1.addTuple(e);
//						pages.add(p1);
//						break;
//					}else {
//						for(int j=0; j < p.page.size(); j++) {
//							Tuple current = p.page.get(j);
//							if(current.htblColNameValue == null) {
//								current.htblColNameValue = e.htblColNameValue;
//								current.strTableName = e.strTableName;
//								flag = false;
//								break;
//							}
//						}
//						if(flag) {
//							p.addTuple(e);
//							break;
//						}
//					}
//				}
//			}
//		}
//	}
		
	public static int getYearOfDate(String date) {
		//YYYY-MM-DD
		String r = date.substring(0,5);
		return Integer.parseInt(r);
	}
	
	public static int getMonthOfDate(String date) {
		//YYYY-MM-DD
		String r = date.substring(5,7);
		return Integer.parseInt(r);
	}
	
	public static int getDayOfDate(String date) {
		//YYYY-MM-DD
		String r = date.substring(8);
		return Integer.parseInt(r);
	}
	
	public static boolean isDeleted(String clstrKey, String tableClstrKey, Hashtable<String,Object> htblColNameValue) {
		String currentClstrKey = getClusteringKeyOfTuple(tableClstrKey, htblColNameValue);
		if(currentClstrKey.contains("java.awt.Polygon")) {
			Polygon pol = makePolygon(clstrKey);
			Polygon pol2 = (Polygon)htblColNameValue.get(tableClstrKey);
			if(isPolysEqual(pol, pol2)) {//////////////
				return true;
			}else {
				return false;
			}
		}
		
		if(clstrKey.equals(currentClstrKey)) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isPolysEqual(Polygon p1, Polygon p2) {
		if(p1.npoints == p2.npoints) {
			for(int i=0; i < p1.npoints; i++) {
				int p1x = p1.xpoints[i];
				int p1y = p1.ypoints[i];
				int p2x = p2.xpoints[i];
				int p2y = p2.ypoints[i];
				if( !(p1x == p2x && p1y == p2y) ) {
					return false;
				}
			}
			return true;
		}else {
			return false;
		}
	}
	
	public static String getClusteringKeyOfTuple(String tableClstrKey, Hashtable<String,Object> htblColNameValue) {
		Enumeration key = htblColNameValue.keys();
		Enumeration value = htblColNameValue.elements();
		while(key.hasMoreElements()) {
			String currentKey = key.nextElement()+"";
			String currentValue = value.nextElement()+"";
			if(currentKey.equals(tableClstrKey)){
				return currentValue;
			}
		}
		return "";
	}
	
	
	
//	public void deleteFromTable(String strTableName, Hashtable<String,Object> htblColNameValue) {
//		ArrayList<String> conditionsKey = getConditionsKey(htblColNameValue);
//		ArrayList<String> conditionsValue = getConditionsValue(htblColNameValue);
//		for(int i=0; i < pages.size(); i++) {
//			Page p = pages.get(i); 
//			if(p.tableName.equals(strTableName)) {
//				for(int j=0; j < p.page.size(); j++) {
//					Tuple t = p.page.get(j);
//					ArrayList<String> values = new ArrayList<String>();
//					//////////////////////////////////////////////////
//					for(int k=0; k < conditionsKey.size(); k++) {
//						String key = conditionsKey.get(k);
//						if(t.checkIfExists()) {
//							String newValue = t.htblColNameValue.get(key)+"";
//							values.add(newValue);
//						}
//					}
//					//////////////////////////////////////////////////
//					if(!values.isEmpty()) {
//						if(equalValues(conditionsValue, values)) {
//							//del
//							//t = null
//							t.htblColNameValue = null;
//						}
//					}
//				}
//			}
//			if(checkEmptyPage(p)) {
//				p.page = null;
//				p = null;
//			}
//		}
//	}
	
//	public void sortPage(String clstrKey, Page p) {
//		Vector<Tuple> v = p.page;
//		//v.sort(c);	insertionSort
//	}
	
	public static boolean checkEmptyPage(Page p) {
		if(p.page.isEmpty()) {
			return true;
		}else {
			for(int i=0; i < p.page.size(); i++) {
				Tuple t = p.page.get(i);
				if(!(t.htblColNameValue == null)) {
					return false;
				}
			}
			return true;
		}
	}
	
	public static boolean equalValues(Vector<String> conditionsValue, Vector<String> values) {
		for(int i=0; i < conditionsValue.size(); i++) {
			String currentCondition = conditionsValue.get(i);
			String currentValue = values.get(i);
			
//			if(currentCondition.contains("Polygon")) {
//				if(currentValue.contains("Polygon")) {
//					
//				}else {
//					return false;
//				}
//			}
			
			if(!currentCondition.equals(currentValue)) {
				return false;
			}
		}
		return true;
	}

	public static Vector<String> getConditionsKey(Hashtable<String,Object> htblColNameValue) {
		Vector<String> conditionsKey = new Vector<String>();
		Enumeration key = htblColNameValue.keys();
		while(key.hasMoreElements()) {
			conditionsKey.add(key.nextElement()+"");
		}
		return conditionsKey;
	}
	
	public static Vector<String> getConditionsValue(Hashtable<String,Object> htblColNameValue) {
		Vector<String> conditionsValue = new Vector<String>();
		Enumeration value = htblColNameValue.elements();
		while(value.hasMoreElements()) {
			conditionsValue.add(value.nextElement()+"");
		}
		return conditionsValue;
	}
	
	public static String getTableClusteringKey(String tableName) {
		String data = readData();
		StringTokenizer st = new StringTokenizer(data, "\n");
		while(st.hasMoreTokens()) {
			String current = st.nextToken();
			StringTokenizer st2 = new StringTokenizer(current, ",");
			while(st2.hasMoreTokens()) {
				String currentTableName = st2.nextToken();
				String colName = removeSpaceInFront(st2.nextToken());
				st2.nextToken();
				String clstKey = removeSpaceInFront(st2.nextToken());
				if(currentTableName.equals(tableName) && clstKey.equals("true")) {
					return colName;
				}
				st2.nextToken();
			}
			
		}
		return "";
	}
	
	public static boolean checkDataType(String tableName, Hashtable<String,Object> htblColNameValue) /*throws DBAppException*/{
		//get valid data types from metadata.csv
		Hashtable<String,String> h = getTableDataTypes(tableName);
		Enumeration<String> e = htblColNameValue.keys();
		Enumeration<String> e2 = h.keys();
		while(e.hasMoreElements()) {
			String current = e.nextElement();	//returns gpa
			Object currentValue = htblColNameValue.get(current);
			//String dataType = e2.nextElement();
			
			String currentDataType = (currentValue.getClass()+"").substring(6);//////////
			
			if(currentDataType.equals("random_6.DBAppPolygon")) {
				currentDataType = "java.awt.Polygon";
			}
			
			String dataType = getDataType(current, h);///////////////////////////
			if(!currentDataType.equals(dataType)) {
				//throw new DBAppException("Invalid data types.");
				return false;
			}
		}
		return true;
	}
	
	public static String getDataType(String key, Hashtable<String,String> tableDataTypes) {
		Enumeration<String> e = tableDataTypes.keys();
		while(e.hasMoreElements()) {
			String current = e.nextElement();
			if(current.equals(key)) {
				return tableDataTypes.get(current);
			}
		}
		return "";
	}
	
	public static Hashtable<String,String> getTableDataTypes(String tableName) {
		Hashtable<String,String> h = new Hashtable<String,String>();
		String data = readData();
		StringTokenizer st = new StringTokenizer(data, "\n");
		while(st.hasMoreTokens()) {
			String current = st.nextToken();
			StringTokenizer st2 = new StringTokenizer(current, ",");
			while(st2.hasMoreTokens()) {
				String currentTableName = st2.nextToken();
				String colName = removeSpaceInFront(st2.nextToken());
				String colType = removeSpaceInFront(st2.nextToken());
				if(currentTableName.equals(tableName)) {
					h.put(colName, colType);
				}
				st2.nextToken(); st2.nextToken();
			}
		}
		return h;
	}
	
	public static void printPages() {
		for(int i=0; i < pages.size(); i++) {
			System.out.println(pages.get(i).tableName);
			if(pages.get(i) == null || pages.get(i).page == null) {
				System.out.println("Empty Page.");
			}else {
				System.out.println("-----------------------------------");
				pages.get(i).printPage();
				System.out.println("-----------------------------------");
			}
		}
	}

	public static String removeSpaceInFront(String s) {
		return s.substring(1);
	}
	
	public static String readData() {
		String data = "";
//		File file = new File("metadata.csv");
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
	
	public static int getTfile() {
		try {
			FileReader reader=new FileReader("config\\DBApp.properties");  
		    Properties p = new Properties();  
		    p.load(reader);
		    String s = p.getProperty("Tfile");
		    return Integer.parseInt(s);
		}catch(Exception e) {
			System.out.println("Error");
			return -1;
		}
	}
	
	public static void updateTfile() {
		try {
//			FileInputStream in = new FileInputStream("DBApp.properties");
			FileInputStream in = new FileInputStream("config\\DBApp.properties");
			Properties props = new Properties();
			props.load(in);
			in.close();

//			FileOutputStream out = new FileOutputStream("DBApp.properties");
			FileOutputStream out = new FileOutputStream("config\\DBApp.properties");
			props.setProperty("Tfile", Tfile+"");
			props.store(out, null);
			out.close();
		}catch(Exception e) {
			System.out.println("Error");
		}
	}
	
	public static int getNfile() {
		try {
//			FileReader reader=new FileReader("DBApp.properties");  
			FileReader reader=new FileReader("config\\DBApp.properties");
		    Properties p = new Properties();  
		    p.load(reader);
		    String s = p.getProperty("Nfile");
		    return Integer.parseInt(s);
		}catch(Exception e) {
			System.out.println("Error");
			return -1;
		}
	}
	
	public static void updateNfile() {
		try {
//			FileInputStream in = new FileInputStream("DBApp.properties");
			FileInputStream in = new FileInputStream("config\\DBApp.properties");
			Properties props = new Properties();
			props.load(in);
			in.close();

//			FileOutputStream out = new FileOutputStream("DBApp.properties");
			FileOutputStream out = new FileOutputStream("config\\DBApp.properties");
			props.setProperty("Nfile", Nfile+"");
			props.store(out, null);
			out.close();
		}catch(Exception e) {
			System.out.println("Error");
		}
	}
	
	public static void main(String[] args) {
//		DBApp dbApp = new DBApp();
//		
//		String strTableName = "Student";
//		
//		Hashtable<String,String> htblColNameType = new Hashtable<String,String>();
//		htblColNameType.put("id", "java.lang.Integer");
//		htblColNameType.put("name", "java.lang.String"); 
//		htblColNameType.put("gpa", "java.lang.Double");
//		dbApp.createTable(strTableName, "id", htblColNameType);
//		dbApp.createTable("CityShop2", "name", h);
//		deserialize();
//		
//		printPages();
//		System.out.println("-----------------------------------");
//		
//		Hashtable<String,Object> htblColNameValue = new Hashtable<String,Object>(); 
//		htblColNameValue.put("id", new Integer(2343432)); 
//		htblColNameValue.put("name", new String("Ahmed Noor")); 
//		htblColNameValue.put("gpa", new Double(0.95)); 
//		dbApp.insertIntoTable(strTableName, htblColNameValue);
//		
//		printPages();
//		System.out.println("-----------------------------------");
		
		//serialize(pages.get(0));
		//deserialize("SD.class");
		//serializeAll();
		//dbApp.init();
		
//		
//		htblColNameValue.clear();
//		htblColNameValue.put("id", new Integer(453455)); 
//		htblColNameValue.put("name", new String("Ahmed Noor"));
//		htblColNameValue.put("gpa", new Double(0.95)); 
//		dbApp.insertIntoTable(strTableName, htblColNameValue);
//		
//		printPages();
//		System.out.println("-----------------------------------");
//		
//		htblColNameValue.clear();
//		htblColNameValue.put("id", new Integer(5674567)); 
//		htblColNameValue.put("name", new String("Dalia Noor")); 
//		htblColNameValue.put("gpa", new Double(1.25)); 
//		dbApp.insertIntoTable(strTableName, htblColNameValue);
//		
//		printPages();
//		System.out.println("-----------------------------------");
//		
//		htblColNameValue.clear( );
//		htblColNameValue.put("id", new Integer(23498));
//		htblColNameValue.put("name", new String("John Noor")); 
//		htblColNameValue.put("gpa", new Double(1.5)); 
//		dbApp.insertIntoTable(strTableName, htblColNameValue);
//		
//		printPages();
//		System.out.println("-----------------------------------");
//		
//		htblColNameValue.clear( );
//		htblColNameValue.put("id", new Integer(78452));
//		htblColNameValue.put("name", new String("Zaky Noor")); 
//		htblColNameValue.put("gpa", new Double(0.88));
//		dbApp.insertIntoTable(strTableName, htblColNameValue);
//		
//		printPages();
//		System.out.println("-----------------------------------");
//	
//		
//		Hashtable<String,Object> h = new Hashtable<String,Object>(); 
//		//h.put("gpa", new Double(1.5));
//		h.put("gpa", new Double(0.95));
//		h.put("id", new Integer(453455));
//		dbApp.deleteFromTable(strTableName, h);
//		
//		printPages();
//		System.out.println("-----------------------------------");
//		
//		
//		
//		
//		h.clear();
//		h.put("gpa", new Double(0.88));
//		dbApp.deleteFromTable(strTableName, h);
//		
//		printPages();
//		System.out.println("-----------------------------------");
//
//		htblColNameValue.clear( );
//		htblColNameValue.put("id", new Integer(1));
//		htblColNameValue.put("name", new String("A")); 
//		htblColNameValue.put("gpa", new Double(2.3));
//		dbApp.insertIntoTable(strTableName, htblColNameValue);
//		
//		printPages();
//		System.out.println("-----------------------------------");
//		
//		htblColNameValue.clear( );
//		htblColNameValue.put("id", new Integer(1));
//		htblColNameValue.put("name", new String("A")); 
//		htblColNameValue.put("gpa", new Double(2.23));
//		dbApp.updateTable(strTableName, "1", htblColNameValue);
//		
//		printPages();
//		System.out.println("-----------------------------------");
//		
//		htblColNameValue.clear();
//		htblColNameValue.put("id", new Integer(2));
//		htblColNameValue.put("name", new String("B")); 
//		htblColNameValue.put("gpa", new Double(3.0));
//		dbApp.insertIntoTable(strTableName, htblColNameValue);
//		
//		printPages();
//		System.out.println("-----------------------------------");
//		
//		htblColNameValue.clear();
//		htblColNameValue.put("id", new Integer(3));
//		htblColNameValue.put("name", new String("C")); 
//		htblColNameValue.put("gpa", new Double(3));
//		dbApp.insertIntoTable(strTableName, htblColNameValue);
//		
//		printPages();
//		System.out.println("-----------------------------------");
//		
//		htblColNameValue.clear();
//		htblColNameValue.put("id", new Integer(1));
//		htblColNameValue.put("name", new String("changed")); 
//		htblColNameValue.put("gpa", new Double(2.23));
//		dbApp.updateTable(strTableName, "3", htblColNameValue);
//		
//		printPages();
//		System.out.println("-----------------------------------");
//		
//		h.clear();
//		h.put("id", new Integer(1));
//		dbApp.deleteFromTable(strTableName, h);
//		
//		printPages();
//		System.out.println("-----------------------------------");
//		
//		htblColNameValue.clear();
//		htblColNameValue.put("id", new Integer(3));
//		htblColNameValue.put("name", new String("C")); 
//		htblColNameValue.put("gpa", new Double(3));
//		dbApp.insertIntoTable(strTableName, htblColNameValue);
//		
//		printPages();
//		System.out.println("-----------------------------------");
//		
//		htblColNameValue.clear();
//		htblColNameValue.put("id", new Integer(3));
//		htblColNameValue.put("name", new String("C")); 
//		htblColNameValue.put("gpa", new Double(3));
//		dbApp.insertIntoTable(strTableName, htblColNameValue);
//		
//		printPages();
//		System.out.println("-----------------------------------");
//		
//		htblColNameType.clear();
//		htblColNameType.put("available", "java.lang.Boolean");
//		htblColNameType.put("name", "java.lang.String"); 
//		htblColNameType.put("price", "java.lang.Double");
//		dbApp.createTable("CityShop", "name", htblColNameType);
//		
//		printPages();
//		System.out.println("-----------------------------------");
//		
//		htblColNameValue.clear();
//		htblColNameValue.put("available", new Boolean(false));
//		htblColNameValue.put("name", new String("A")); 
//		htblColNameValue.put("price", new Double(1.1));
//		dbApp.insertIntoTable("CityShop", htblColNameValue);
//		
//		printPages();
//		System.out.println("-----------------------------------");
//		
//		htblColNameValue.clear();
//		htblColNameValue.put("available", new Integer(1));
//		htblColNameValue.put("name", new String("A")); 
//		htblColNameValue.put("price", new Double(1.1));
//		dbApp.insertIntoTable("CityShop", htblColNameValue);
//		
//		//printPages();
//		//System.out.println("-----------------------------------");
////		
//		htblColNameValue.clear();
//		htblColNameValue.put("id", new Integer(1));
//		htblColNameValue.put("name", new String("changed")); 
//		htblColNameValue.put("gpa", new Integer(3));
//		dbApp.updateTable(strTableName, "3", htblColNameValue);
//		
//		printPages();
//		System.out.println("-----------------------------------");
//		
//		h.clear();
//		h.put("id", new Double(1));
//		dbApp.deleteFromTable(strTableName, h);
//		
//		printPages();
//		System.out.println("-----------------------------------");
//		
//		htblColNameValue.clear();
//		htblColNameValue.put("available", new Boolean(true));
//		htblColNameValue.put("name", new String("B")); 
//		htblColNameValue.put("price", new Double(1.1));
//		dbApp.insertIntoTable("CityShop", htblColNameValue);
//		
//		printPages();
//		System.out.println("-----------------------------------");
//		
//		htblColNameValue.clear();
//		htblColNameValue.put("available", new Boolean(true));
//		htblColNameValue.put("name", new String("d")); 
//		htblColNameValue.put("price", new Double(1.1));
//		dbApp.updateTable("CityShop", "A", htblColNameValue);
//		
//		printPages();
//		System.out.println("-----------------------------------");
//		
//		htblColNameValue.clear();
//		htblColNameValue.put("available", new Boolean(true));
//		htblColNameValue.put("name", new String("A")); 
//		htblColNameValue.put("price", new Double(1.1));
//		dbApp.insertIntoTable("CityShop", htblColNameValue);
//		
//		printPages();
//		System.out.println("-----------------------------------");
//		
//		Hashtable<String,String> htblColNameType = new Hashtable<String,String>();
//		htblColNameType.put("date", "java.lang.Date");
//		htblColNameType.put("name", "java.lang.String"); 
//		dbApp.createTable("TestDate", "date", htblColNameType);
//		
//		Hashtable<String,Object> htblColNameValue = new Hashtable<String,Object>();
//		htblColNameValue.put("date", new Date());
//		htblColNameValue.put("name", new String("A")); 
//		dbApp.insertIntoTable("TestDate", htblColNameValue);
//		
//		printPages();
//		System.out.println("-----------------------------------");
//		
//		htblColNameValue.clear();
//		htblColNameValue.put("date", new Date());
//		htblColNameValue.put("name", new String("Z")); 
//		dbApp.insertIntoTable("TestDate", htblColNameValue);
//		
//		printPages();
//		System.out.println("-----------------------------------");
		
	}
	
	public static int getT() {
		try {
//			FileReader reader=new FileReader("DBApp.properties"); 
			FileReader reader=new FileReader("config\\DBApp.properties");
		    Properties p = new Properties();  
		    p.load(reader);
		    String s = p.getProperty("NodeSize");
		    return Integer.parseInt(s);
		}catch(Exception e) {
			System.out.println("Error");
			return -1;
		}
	}
	

}
