// ----------------------------------------------------------
// Written by: Jingnan Xiao 
// ----------------------------------------------------------
import java.util.Scanner;
/**
* The BookDemo class is used to manage the books in a bookstore
* @author Jingnan Xiao 
*/
public class BookDemo {

	public static void main(String[] args) {

		
		final String PASSWORD = "password";
		String userPassword;	
		int exit = 0; // to count every 3rd wrong attempt entry of password
		int option;
		int option2;
		
		System.out.println("-----Welcome To WeLoveReading Bookstore-----"); // welcome message
		System.out.println("Please enter the maximum number of books"); // Prompt the user for the maximum number of books
		Scanner myKeyboard = new Scanner(System.in);		
		
		/** if user does not input a integer
		 */
		while (!myKeyboard.hasNextInt()) 
		{        
			myKeyboard.next(); // Read and discard the non-integer input
		    System.out.println("Please enter a number: "); 
		}
		int maxBooks = myKeyboard.nextInt();
		Book[] inventory = new Book[maxBooks]; // an empty array that has the potential of keeping track of the created Book objects 
		
		
		String mainMenu = ("What do you want to do?\n"
				+ "   1. Enter new books (password required)\n"
				+ "   2. Change information of a book (password required)\n"
				+ "   3. Display all books by a specific author\n"
				+ "   4. Display all books under a certain a price.\n" 
				+ "   5. Quit\n" 
				+ "Please enter your choice >"); // main menu
		
		/** the following do-while loop will help user to return to main menu after finishing an option from 1 to 4,
		 *  and will end after option 5
		 */
		do {
		System.out.println(mainMenu);		
		
		/** if user does not input a integer
		 */
		while (!myKeyboard.hasNextInt()) 
		{        
			myKeyboard.next(); // Read and discard the non-integer input
		    System.out.println("Please enter a number: "); 
		}
		option = myKeyboard.nextInt(); // get the choice	
		String junk = myKeyboard.nextLine(); // to get rid of '\n'
		
		/** if the integer is not between 1 to 5 inclusive 
		 */
		while (option <1 || option >5) {
			System.out.println("Invalid input. Please enter a number 1-5.");
			System.out.println(mainMenu);
			option = myKeyboard.nextInt();
			junk = myKeyboard.nextLine();
		}
		/** the option 1 - 5
		 */
		switch (option)
		{
		/** option 1
		 */
		case 1:
			System.out.println("Please enter the password>"); // Prompt the user for his/her password
			int inputLimit = 0; // to count attempts to enter the password 
			
			do {
				userPassword = myKeyboard.nextLine(); // to get the password
				if (userPassword.equals(PASSWORD))
					break; // if the password is correct, the loop ends
				else{
					System.out.println("Invalid password!");
					inputLimit++; // if the password was incorrect for 3 times, the loop ends, and the count will be 3					
				}
			} while (inputLimit < 3); 		
			
			if (inputLimit == 3 && exit < 3){				
				exit++; // for every 3rd wrong attempts entry, exit will increase by 1
				break;  // to end the switch, and return to main menu
			}
			if (exit == 3){ 
				System.out.println("Program detected suspicous activities and will terminate immediately!"); 
				System.exit(0); // after the variable exit has increased by 1 for 3 times, 
								// for the 4th time the program will display the message above and quit
			}	
			
			/** if the password is correct 	
			 */
			for(;;){
				System.out.println("There are " + Book.findNumberOfCreatedBooks() + " books in the store.\n" +
									"Enter the number of new books you want to add: ");
									// ask the user how many books he/she wants to enter
				// if user does not input a integer
				while (!myKeyboard.hasNextInt()) 
				{        
					myKeyboard.next(); // Read and discard the non-integer input
				    System.out.println("Please enter a number: "); 
				}				
				int newBooks = myKeyboard.nextInt(); // get the number of books
				
				/** if there is enough space 	
				 */ 
				if (newBooks <= (maxBooks - Book.findNumberOfCreatedBooks()))
					{		
					int currentBooks = Book.findNumberOfCreatedBooks();
					for (int i = Book.findNumberOfCreatedBooks(); i < (currentBooks + newBooks); i++)
						inventory[i] = new Book("title", "name", 0, 0.0); //to create book objects (add books)
					
					System.out.println(newBooks +" books have been added.\n" +
							"Now there are " + Book.findNumberOfCreatedBooks() + " books in the store.");
					break; 
					}
				/** if there is no enough space 	
				 */
				else 
					System.out.println("You can only add " + (maxBooks - Book.findNumberOfCreatedBooks()) + " more books");
					// inform the user he/she can only add the number of remaining places in the array
			}	
			break;
		
		/** option 2
		 */
		case 2:
			System.out.println("Please enter the password>");  // Prompt the user for his/her password
			inputLimit = 0; // to count attempts to enter the password 
			
			/** ask for the password 	
			 */
			do {
				userPassword = myKeyboard.nextLine(); // to get the password
				if (userPassword.equals(PASSWORD)) // if the password is correct, the loop ends
					break;
				else{
					System.out.println("Invalid password!");
					inputLimit++;					
				}
			} while (inputLimit < 3); 	// if the password was incorrect for 3 times, the loop ends, and the count will be 3		 
			
			if (inputLimit == 3)			
				break;  // to end the switch, and return to main menu, if there are 3 wrong attempts
						
			/** if the password is correct 	
			 */	
			for(;;) {
				System.out.println("Enter the index number of book you want to update: "); //the user is asked which book to update
				
				/** if user does not input a integer 	
				 */
				while (!myKeyboard.hasNextInt()) 
				{        
					myKeyboard.next(); // to read and discard the non-integer input
				    System.out.println("Please enter a number: "); 
				}				
				int bookIndex = myKeyboard.nextInt(); // to get the book number
				junk = myKeyboard.nextLine(); // to get rid of '\n'
				
				/** If there is no Book object at the specified index location
				 */
				if (bookIndex  < 1 || bookIndex > Book.findNumberOfCreatedBooks()) { // bookIndex <1 because for the user, there is no book No.0
					System.out.println("No book is found.");
					
					/** asking the user if he/she wishes to re-enter another book, 
					 * or quit this operation and go back to the main menu
					 */					 
					System.out.println("press R to re-enter an index or press Q to return to main menu");
					String answer = myKeyboard.nextLine();
					if (answer.equals("R") || answer.equals("r"))
						continue; // re-enter another book
					else if  (answer.equals("Q") || answer.equals("q"))
						break; // go back to the main menu					
				}
				
				/** If the entered index has a valid book
				 */
				if ( bookIndex >= 1 && bookIndex <= Book.findNumberOfCreatedBooks() )	{
					System.out.println("Book: " + bookIndex); //display the current information of the chosen book
					System.out.println(inventory[bookIndex - 1]); // bookIndex - 1 because when user means book No.1, it is the index[0] in the array
					
					
					String menu2 = ("What information would you like to change?\n"
							+ "   1. author\n"
							+ "   2. title\n"
							+ "   3. IBSN\n"
							+ "   4. price\n" 
							+ "   5. Quit\n" 
							+ "Enter your choice >");// the update menu
					
					/** to update books
					 */
					do {
						System.out.println(menu2); // to ask the user which attribute they wish to change
						
						/** if user does not input a integer
						 */
						while (!myKeyboard.hasNextInt()) 
						{        
							myKeyboard.next(); // to read and discard the non-integer input
						    System.out.println("Please enter a number: "); 
						}
						option2 = myKeyboard.nextInt(); // to get the choice
						junk = myKeyboard.nextLine(); // to get rid of '\n'
						
						/** if the input number is not between 1 to 5 inclusive 
						*/
						while (option2 <1 || option2 >5) {
							System.out.println("Invalid input. Please enter a number 1-5.");
							System.out.println(mainMenu);
							option = myKeyboard.nextInt();
							junk = myKeyboard.nextLine();
						}
						/** the option  1-5
						 */
						switch (option2) {
						case 1:
							System.out.println("The author of the book is: "); // to ask for what change the user wants to make
							String name = myKeyboard.nextLine();  // to get the update information
							inventory[bookIndex - 1].setName(name); // to make the change to the attribute
							break; //return to the update menu for addtional changes
						case 2:
							System.out.println("The title of the book is: ");
							String title = myKeyboard.nextLine();
							inventory[bookIndex - 1].setTitle(title);
							break;
						case 3:
							System.out.println("The IBSN of the book is: ");
							// if user does not input a number
							while (!myKeyboard.hasNextLong()) 
							{        
								myKeyboard.next(); // Read and discard the non-number input
							    System.out.println("Please enter a number: "); 
							}
							long IBSN = myKeyboard.nextLong();
							inventory[bookIndex - 1].setIBSN(IBSN);
							break;
						case 4:
							System.out.println("The price of the book is: ");
							// if user does not input a number
							while (!myKeyboard.hasNextDouble()) 
							{        
								myKeyboard.next(); // Read and discard the non-number input
							    System.out.println("Please enter a number: "); 
							}
							double price = myKeyboard.nextDouble();
							inventory[bookIndex - 1].setPrice(price);
							break;
						case 5:
							break; // return to main menu
						default:
							System.out.println("Enter a number 1-5");
							break;
						}
						System.out.println("The information of the book #" + bookIndex + " has been updated to: ");
						System.out.println(inventory[bookIndex - 1]);
					}while (option2 != 5);
					
					break;
				}
			}	
			break;
		/** option 3
		*/
		case 3:			
			System.out.println("Enter the author's name of book you want: "); // prompt the user to enter an author name
			String name = myKeyboard.nextLine();	 // to get the name		
			for (int i = 0; i < Book.findNumberOfCreatedBooks(); i++)
				inventory[i].findBookBy(name); // to display the information of all books by that requested	author
			break;
		/** option 4
		*/
		case 4:	
			System.out.println("Enter a price than which you want the books cheaper: "); // prompt the user to enter a value
			
			/** if user does not input a number
			 */
			while (!myKeyboard.hasNextDouble()) 
			{        
				myKeyboard.next(); // Read and discard the non-number input
			    System.out.println("Please enter a number: "); 
			}
			double price = myKeyboard.nextDouble(); // to get the price
			for (int i = 0; i < Book.findNumberOfCreatedBooks(); i++)
				inventory[i].findCheaperThan(price); //to display all books that have a value smaller than that entered value
			break;
		/** option 5
		*/
		case 5:
			System.out.println("Thank you for using the book management system!"); //display a closing message 
			System.exit(0); // to and end the drive
			break;
		default:
			System.out.println("Enter a number 1-5");
			break;		
		}	
	} while (option != 5); // the loop will end after the user chooses option 5	
		myKeyboard.close();
	}

}
