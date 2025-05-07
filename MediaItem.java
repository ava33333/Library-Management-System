import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public abstract class MediaItem implements Loanable {
	String itemID;
	String title;
	String authorOrDirector;
	int publicationYear;
	boolean isAvailable = true;
	int mediaCount = 0;
	List<String> lst;

	abstract String getItemDetails();

	abstract String getID();

	abstract String getTitle();

	public void readMediaFromFile(String filename, List<MediaItem> mediaList) {
		// try catch for reading file
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			// while line isn't empty (null)
			while ((line = br.readLine()) != null) {
				// call parse record to parse each line
				// set array to the call
				String[] arr = parseRecord(line);
				// set array to array list (for easier searching/sorting and such
				lst = Arrays.asList(arr);

				// 7 attributes for each media item
				if (arr.length < 7) {
					System.out.println("Skipping invalid record format: " + line);
					continue;
				}

				// set each element of the array to an appropriate variable
				String type = lst.get(0).trim();
				String ID = lst.get(1).trim();
				String title = lst.get(2).trim();
				// the author/editor/director
				String person = lst.get(3).trim();
				int yr = Integer.parseInt(lst.get(4).trim());

				String item6 = lst.get(5).trim();
				String item5 = lst.get(6).trim();

				try {
					// book item
					if (type.equalsIgnoreCase("Book")) {
						// create
						Book book = new Book();
						// attributes
						book.setID(ID);
						book.setTitle(title);
						book.setAuthor(person);
						book.setYr(yr);
						book.setGenre(item6);
						book.setISBN(item5);
						// add to array list
						mediaList.add(book);
					} else if (type.equalsIgnoreCase("Magazine")) {
						// magazine
						// create
						Magazine magazine = new Magazine();
						// attributes
						magazine.setID(ID);
						magazine.setTitle(title);
						magazine.setAuthOrEd(person);
						magazine.setYr(yr);
						magazine.setIssueNum(item5);
						magazine.setMonth(item6);
						// add to array list
						mediaList.add(magazine);
					} else if (type.equalsIgnoreCase("DVD")) {
						// dvd
						// create
						DVD dvd = new DVD();
						// attributes
						dvd.setID(ID);
						dvd.setTitle(title);
						dvd.setDirector(person);
						dvd.setYr(yr);
						dvd.setRating(item5);
						dvd.setDuration(item6);
						// add to array list
						mediaList.add(dvd);
					}
				} catch (NumberFormatException e) /* catches wrong number format */{
					System.out.println("Invalid number format in record: " + line);
				} catch (ArrayIndexOutOfBoundsException e) /* if array index too big */{
					System.out.println("Array index is out of bounds: " + e.getMessage());
				}
			}
		} catch (FileNotFoundException e) /* if file not found */ {
			System.out.println("File not found: " + e.getMessage());
		} catch (IOException e) /* if file can't be read */{
			System.out.println("Error reading file: " + e.getMessage());
		}
	}
	
	// checkout item from library
	public void checkOut() {
		isAvailable = false;
	}
	
	// return item to library
	public void returnItem() {
		isAvailable = true;
	}
	
	// remove item from list
	public void removeItem(int num, List<MediaItem> mediaList) {
		mediaList.remove(num);
	}
	
	// returns availability of item
	public boolean isAvailable() {
		return isAvailable;
	}
	
	// goes through file
	private String[] parseRecord(String line) {
		// splits on commas
		if (line.contains(",")) {
			return line.split(",");
		} else {
			return new String[] {};
		}
	}
	
	// writes our file (for updating files after doing remove/add/... operations)
	public static void writeMediaToFile(String filename, List<MediaItem> mediaList) {
		// try catch for writing file
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
			// for each item in media list
			for (MediaItem item : mediaList) {
				String line = "";
				// if our item is a book
				if (item instanceof Book) {
					// create book
					Book b = (Book) item;
					// info for book in one line
					line = String.format("Book, %s, %s, %s, %d, %s, %s", b.getID(), b.getTitle(), b.getAuthor(),
							b.getYr(), b.getISBN(), b.getGenre());
				} else if (item instanceof Magazine) {
					// if magazine
					// create magazine
					Magazine m = (Magazine) item;
					// info for magazine in one line
					line = String.format("Magazine, %s, %s, %s, %d, %s, %s", m.getID(), m.getTitle(), m.getAuthorEd(),
							m.getYr(), m.getMonth(), m.getIssueNum());
				} else if (item instanceof DVD) {
					// if dvd
					// create dvd
					DVD d = (DVD) item;
					// info for dvd in one line
					line = String.format("DVD, %s, %s, %s, %d, %s, %s", d.getID(), d.getTitle(), d.getDirector(),
							d.getYr(), d.getRating(), d.getDuration());
				}
				
				// writes line before going to next item
				writer.write(line);
				writer.newLine();
			}
		} catch (IOException e) /* catches error when writing file */{
			System.out.println("Error writing to file: " + e.getMessage());
		}
	}

}
