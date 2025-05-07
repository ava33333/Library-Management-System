
public class Magazine extends MediaItem implements Loanable{
	// attributes of magazine
	String issueNum; 
	String month;
	String itemID;
	String title;
	String authorOrEditor;
	int publicationYr;
	
	
	@Override
	String getItemDetails() {
		// availability set to yes or no so it is more user friendly
		String availability;
		if(isAvailable()) {
			availability = "Yes!";
		} else {
			availability = "No.";
		}
		
		// returns information by using getter methods
		return "\n(Magazine)\nTitle: " + getTitle() +
				"\nAuthor/editor: " + getAuthorEd() + 
				"\nMonth: " + getMonth() + 
				"\nPublication year: " + getYr() + 
				"\nID: " + getID() + 
				"\nIssue number: " + getIssueNum() +
				"\nAvailable? " + availability;
	}
	
	// below is all of the getter/setter methods for magazine

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

	public void setAuthOrEd(String authorOrEditor) {
		this.authorOrEditor = authorOrEditor;
	}
	
	public String getAuthorEd() {
		return authorOrEditor;
	}

	public void setYr(int publicationYr) {
		this.publicationYr = publicationYr;
	}
	
	public int getYr() {
		return publicationYr;
	}

	public void setIssueNum(String issueNum) {
		this.issueNum = issueNum;
	}
	
	public String getIssueNum() {
		return issueNum;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	public String getMonth() {
		return month;
	}
	
}
