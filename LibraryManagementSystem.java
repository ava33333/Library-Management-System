import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystem {
	// main method for program
	public static void main(String[] args) {
		// scanner
		Scanner scan = new Scanner(System.in);

		// collection that stores items
		List<MediaItem> mediaItems = new ArrayList<>();

		// read media items from our specified text file
		MediaItem loader = new Book(); // using subclass to access superclass

		// for me ** please ignore **
		//String file = "C:\\Users\\avalm\\OneDrive\\Documents\\eclipse-workspace\\Project 2\\src\\inventory.txt";

		String file = "inventory.txt";

		loader.readMediaFromFile(file, mediaItems);

		// manage library operations (adding, removing, searching, sorting,
		// checking out, and returning media items)

		// provide a simple text based user interface for interaction
		int choice;

		// do while loop which presents the menu to our user until -1 is entered
		do {
			try {
				// menu
				System.out.println("\n --- Library Menu ---");
				System.out.println("1. Search media by title");
				System.out.println("2. Check out an item");
				System.out.println("3. Return an item");
				System.out.println("4. Remove an item");
				System.out.println("5. Sort items");
				System.out.println("6. Add an item");
				System.out.println("-1. Exit");
				System.out.print("Enter your choice: ");

				// user inputs choice number
				choice = scan.nextInt();
				scan.nextLine(); // consumes leftover newline

				// switch case goes through each number
				switch (choice) {
				case 1:
					System.out.println("Enter title keyword: ");
					String keyword = scan.nextLine();

					// counter to see if we went through the whole list without finding the title
					int countLoop = 0;
					// for each, goes through mediaItems list
					for (MediaItem item : mediaItems) {
						// calls keywordSearch with our items title and the keyword as parameters
						// if it is found the item will be printed out with all of its information
						if (keywordSearch(item.getTitle(), keyword)) {
							System.out.println(item.getItemDetails());
							// subtracts to show that we found it at least once
							// doesn't break so that we can keep searching for other titles with similar
							// keywords
							countLoop--;
						}
						countLoop++;
					}

					// if we went through all of the items in the list
					// prints out message accordingly
					if (countLoop == mediaItems.size()) {
						System.out.println("Media is not in directory or keyword is not sufficient.");
					}
					break;
				case 2:
					// ask for item id
					System.out.println("Enter item ID to check out: ");
					String checkOutID = scan.next();
					String xtra = scan.nextLine();
					// becomes true if the item is found
					boolean found_COUT = false;

					// for each loop goes through media items
					for (MediaItem item : mediaItems) {
						// if the checkout id is in the system and the item is available
						if (checkOutID.equalsIgnoreCase(item.getID()) && item.isAvailable) {
							// check out item and tells user
							item.checkOut();
							System.out.println("Checked out " + item.getTitle() + " successfully.");
							// update file
							MediaItem.writeMediaToFile(file, mediaItems);
							found_COUT = true;
							break;
						}
					}
					// if item wasn't found
					if (!found_COUT) {
						System.out.println("Item not found or is already checked out.");
					}

					// if the wrong format was entered (eg: M 009)
					if (!(xtra.equalsIgnoreCase(""))) {
						System.out.println(
								"Incorrect format entered. Enter item letter and 3 numbers with no spaces (ex: M009 for magazine).");
					}
					break;
				case 3:
					// asks user for id
					System.out.print("Enter item ID to return: ");
					String returnID = scan.next();
					String xtra2 = scan.nextLine();
					// if our item is found this will be set to true
					boolean found_RTN = false;

					// for each loop to go through media items
					for (MediaItem item : mediaItems) {
						// if the return id exists in directory and is not available
						if (returnID.equalsIgnoreCase(item.getID()) && !item.isAvailable) {
							// call return item and tell user that it worked
							item.returnItem();
							System.out.println("Returned " + item.getTitle() + " successfully.");
							// writes info to file
							MediaItem.writeMediaToFile(file, mediaItems);
							found_RTN = true;
							break;
						}
					}
					// if the item wasn't found, tell user
					if (!found_RTN) {
						System.out.println("Item not found or is ID was incorrectly entered.");
					}
					if (!(xtra2.equalsIgnoreCase(""))) {
						System.out.println(
								"Incorrect format entered. Enter item letter and 3 numbers with no spaces (eg: M009 for magazine).");
					}

					break;
				case 4:
					// remove item id
					System.out.println("Enter item ID to remove: ");
					String removeID = scan.next();
					String xtra3 = scan.nextLine();
					// count to know what item in our list to remove
					int count = 0;
					int lstSize = mediaItems.size();
					// for each loop goes through items
					for (MediaItem item : mediaItems) {
						count++;
						// if the remove id exists
						if (removeID.equalsIgnoreCase(item.getID())) {
							count--; // in case last item in list
							// call removeItem with the element index and our array list as parameters
							item.removeItem(count - 1, mediaItems);
							// tell user it worked
							System.out.println("Item removed successfully");
							// update file
							MediaItem.writeMediaToFile(file, mediaItems);
							break;
						}
					}
					// if item wasn't found
					if (count == lstSize) {
						System.out.println("Item ID not found. Item not in directory or ID is incorrect.");
					}
					if (!(xtra3.equalsIgnoreCase(""))) {
						System.out.println(
								"Incorrect format entered. Enter item letter and 3 numbers with no spaces (eg: M009 for magazine).");
					}
					break;
				case 5:
					// sort items (alphabetically by title)
					mediaItems.sort((item1, item2) -> item1.getTitle().compareToIgnoreCase(item2.getTitle()));
					// update file
					MediaItem.writeMediaToFile(file, mediaItems);
					// tell user
					System.out.println("Sorted!");
					break;
				case 6:
					// call add item with scanner and mediaItems array list
					addItem(scan, mediaItems);
					// update file
					MediaItem.writeMediaToFile(file, mediaItems);
					break;
				case -1:
					// leaving message
					System.out.println("Thank you for using the media library. Goodbye!");
					break;
				default:
					// if user enters incorrect choice
					System.out.println("Invalid choice");
				}
			} catch (InputMismatchException e) {
				System.out.println("Please enter a number.");
				scan.nextLine();
				choice = 0;
			}
		} while (choice != -1); // loop runs until user enters -1

		scan.close();
	}

	private static void addItem(Scanner scan, List<MediaItem> mediaItems) {
		int choice;
		// menu for user
		do {
			System.out.println(" ");
			System.out.println("Enter a media type: ");
			System.out.println("1. Book");
			System.out.println("2. DVD");
			System.out.println("3. Magazine");
			System.out.println("-1. Exit back to main menu");

			choice = scan.nextInt();
			scan.nextLine(); // consumes xtra characters
			
			if(choice == -1) {
				System.out.println("Returning to main menu...");
				break;
			}
			
			if(1 > choice || choice > 3) {
				System.out.println("Invalid input. Please enter a number from the list.");
				continue;
			}
			
			// asks user for below information for item
			System.out.println("Enter an ID (use the first letter of the item and 3 numbers, eg; M009 for magazine): ");
			String ID = scan.nextLine();
			
			if(!(ID.matches("[BMDbmd]\\d{3}"))) {
				System.out.println("Please enter correct format.");
				continue;
			}
			
			char firstLetter = ID.charAt(0);
			
			if (choice == 1 && (firstLetter == 'B' || firstLetter == 'b')) {
				System.out.println("You're adding a book");
			} else if (choice == 2 && (firstLetter == 'D' || firstLetter == 'd')) {
				System.out.println("You're adding a DVD");
			} else if (choice == 3 && (firstLetter == 'M' || firstLetter == 'm')) {
				System.out.println("You're adding a magazine");
			} else {
				System.out.println("ID does not match media type selected. Try again.");
				continue;
			}
			
			System.out.println("Enter a title: ");
			String title = scan.nextLine();
			System.out.println("Enter a author/editor/director: ");
			String person = scan.nextLine();
			System.out.println("Enter a year: ");
			int year = scan.nextInt();
			scan.nextLine(); // consume xtra characters

			String item5; // genre/duration/issue number
			String item6; // ISBN/rating/month

			// switch case for item type
			switch (choice) {
			case 1:
				// creates book
				Book newBook = new Book();

				// sets the following attributes according to what user entered
				newBook.setID(ID);
				newBook.setTitle(title);
				newBook.setAuthor(person);
				newBook.setYr(year);

				// asks for genre
				// sets genre to book
				System.out.println("Enter genre: ");
				item5 = scan.nextLine();
				newBook.setGenre(item5);

				// asks for isbn
				// sets isbn to book
				System.out.println("Enter ISBN: ");
				item6 = scan.nextLine();
				newBook.setISBN(item6);

				// adds to array list
				mediaItems.add(newBook);
				break;
			case 2:
				// create dvd
				DVD newDvd = new DVD();

				// sets attributes for dvd that user entered
				newDvd.setID(ID);
				newDvd.setTitle(title);
				newDvd.setDirector(person);
				newDvd.setYr(year);

				// asks for duration
				// sets duration for dvd
				System.out.println("Enter duration (minutes): ");
				item5 = scan.nextLine();
				newDvd.setDuration(item5);

				// asks for rating
				// sets rating for dvd
				System.out.println("Enter rating (PG-13, R, etc.): ");
				item6 = scan.nextLine();
				newDvd.setRating(item6);

				// adds to array list
				mediaItems.add(newDvd);
				break;
			case 3:
				// creates magazine
				Magazine newMag = new Magazine();

				// sets magazine attributes to what user entered above
				newMag.setID(ID);
				newMag.setTitle(title);
				newMag.setAuthOrEd(person);
				newMag.setYr(year);

				// asks for issue number
				// sets issue number for magazine
				System.out.println("Enter issue number: ");
				item5 = scan.nextLine();
				newMag.setIssueNum(item5);

				// asks for month
				// sets month for magazine
				System.out.println("Enter month: ");
				item6 = scan.nextLine();
				newMag.setMonth(item6);

				// adds to array list
				mediaItems.add(newMag);
				break;
			default:
				// if invalid number is entered
				System.out.println("Invalid number entered.");
			}
			System.out.println("Item added successfully.");
		} while (choice != -1);
	}

	public static boolean keywordSearch(String s, String keyword) {
		// checks to see if title (s) has the keyword entered
		return s.toLowerCase().contains(keyword.toLowerCase());
	}
}
