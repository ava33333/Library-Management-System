
public class DVD extends MediaItem implements Loanable{
	// attributes of dvd
	String duration;
	String rating;
	String title;
	String itemID;
	String director;
	int publicationYr;
	
	@Override
	String getItemDetails() {
		// availability yes or no for user interface
		String availability;
		if(isAvailable()) {
			availability = "Yes!";
		} else {
			availability = "No.";
		}
		
		// returns info by using getter methods
		return "\n(DVD)\nTitle: " + getTitle() + 
				"\nDirector: " + getDirector() + 
				"\nRating: " + getRating() + 
				"\nPublication year: " + getYr() + 
				"\nDuration: " + getDuration() + 
				"\nID: " + getID() + 
				"\nAvailable? " + availability;
	}
	
	// below are all getter and setter methods
	public void setID(String itemID) {
		this.itemID = itemID;
	}
	
	public String getID() {
		return itemID;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setDirector(String director) {
		this.director = director;
	}
	
	public String getDirector() {
		return director;
	}

	public void setYr(int publicationYr) {
		this.publicationYr = publicationYr;
	}
	
	public int getYr() {
		return publicationYr;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public String getDuration() {
		return duration;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	
	public String getRating() {
		return rating;
	}
	
}
