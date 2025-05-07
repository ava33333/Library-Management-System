
public class Book extends MediaItem implements Loanable{
	String genre;
	String ISBN; 
	String title;
	String author;
	int publicationYr;
	String ID;
	
	@Override
	public String getItemDetails() {
		// yes or no for user readability
		String availability;
		if(isAvailable) {
			availability = "Yes!";
		} else {
			availability = "No.";
		}
		// returns book info by using getter methods
		return "\n(Book)\nTitle: " + getTitle() + 
				"\nAuthor: " + getAuthor() + 
				"\nPublication year: " + getYr() + 
				"\nGenre: " + getGenre() + 
				"\nISBN: " + getISBN() + 
				"\nID: " + getID() + 
				"\nAvailable? " + availability;
	}

	// below are all of the getter and setter methods
	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String getID() {
		return ID;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setYr(int publicationYr) {
		this.publicationYr = publicationYr;
	}
	
	public int getYr() {
		return publicationYr;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getGenre() {
		return genre;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public String getISBN() {
		return ISBN;
	}
	
}
