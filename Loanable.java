
public interface Loanable {
	void checkOut(); // checks out book
	void returnItem(); //marks item as loaned
	boolean isAvailable(); //check availability
}
 