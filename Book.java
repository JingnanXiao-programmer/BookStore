// ----------------------------------------------------------
// Written by: Jingnan Xiao
// ----------------------------------------------------------

/**
* The Book class is used for the book management system.
* @author Jingnan Xiao 40058110
*/
public class Book {
	private String title;	
	private String name;
	private long IBSN;
	private double price;
	private static int bookNumber = 0;
	
	
	/**
	* constructor
	*/
	public Book(String title, String name, long IBSN, double price) {
		this.title = title;
		this.name = name;
		this.IBSN = IBSN;
		this.price = price;
		bookNumber++;
	}
	/**
	* default constructor
	*/
	public Book() {
		this.title = null;
		this.name = null;
		this.IBSN = 0;
		this.price = 0.0;		
	}
	/**
	* copy constructor
	*/
	public Book(Book another) {
		this.title = another.title;
		this.name = another.name;
		this.IBSN = another.IBSN;
		this.price = another.price;		
	}
	/**
	* accessors
	* @return a String which is the title of the book	
	*/
	public String getTitle(){
		return title;
	}
	/**
	* accessors
	* @return a String which is the name of the book	
	*/
	public String getName(){
		return name;
	}
	/**
	* accessors
	* @return a double number which is the IBSN of the book	
	*/
	public long getIBSN(){
		return IBSN;
	}
	/**
	* accessors
	* @return a long number which is the IBSN of the book	
	*/
	public double getPrice(){
		return price;
	}
		
	/**
	* mutators
	* @param title a String value 	
	*/	
	public void setTitle(String title){
		this.title =  title;
	}
	/**
	* mutators
	* @param name a String value 	
	*/
	public void setName(String name){
		this.name = name;
	}
	/**
	* mutators
	* @param IBSN a long value 	
	*/
	public void setIBSN(long IBSN){
		this.IBSN = IBSN;
	}
	/**
	* mutators
	* @param price a double value 	
	*/
	public void setPrice(double price){
		this.price = price;
	}	 

	/**
	* toString method
	* @return a String that describes the book's information
	*/
	public String toString() {
		return ("Author: " + name + "\nTitle: " + title + "\nIBSN: " + IBSN + "\nPrice: $" + price);
	}
	/**
	* to find how many books are created	
	* @return an integer which is the number of created Book objects
	*/
	public static int findNumberOfCreatedBooks(){
		return bookNumber;
	}
	/**
	* to find books by giving author names	
	* @param name a String value
	*/	
	public void findBookBy(String name) {		
		if(this.getName().equals(name)) 
			System.out.println(this + "\n-----");
	}
	/**
	* to find books cheaper than a given price	
	* @param price a double value
	*/		
	public void findCheaperThan(double price) {
		if (this.price != 0.0 && this.price < price)
			System.out.println(this + "\n-----");
	}
	/**
	* to compare if two book objects are same	
	* @param Book an object
	*/
	public boolean equals(Book anotherBook) {
		return (this.IBSN == anotherBook.IBSN && this.price == anotherBook.price);
	}
	
}
