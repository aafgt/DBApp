package random_6;

import java.io.Serializable;

public class Ref implements Serializable{
	
	/**
	 * This class represents a pointer to the record. It is used at the leaves of the B+ tree 
	 */
	private static final long serialVersionUID = 1L;
	private int indexInPage;
	private String pageNo;
	
	public Ref(String pageNo) {
		this.pageNo = pageNo;
	}
	
	public Ref(String pageNo, int indexInPage)
	{
		this.pageNo = pageNo;
		this.indexInPage = indexInPage;
	}
	
	/**
	 * @return the page at which the record is saved on the hard disk
	 */
	public String getPage()
	{
		return pageNo;
	}
	
	/**
	 * @return the index at which the record is saved in the page
	 */
	public int getIndexInPage()
	{
		return indexInPage;
	}
}