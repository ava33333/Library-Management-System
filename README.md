# Overview
This program manages library records from a `.txt` file, allowing users to interact with a text-based menu to:
* Add
* Remove
* Search
* Check out
* Return
* Sort media items (Books, DVDs, and Magazines)
It ensures all data is validated, persistent, and easily extendable.

## Code Overview
### Purpose
This program reads library records from a `.txt` file and stores them as objects in memory. It uses expressions to validate data and handle incorrectly formatted data. An interactive menu allows the user to manage the collection. After any change (add, delete, check out, return), the program updates the file automatically.

### Key Classes/Interface
`LibraryManagementSystem`
* Contains the `main` method and user interaction loop.
* Handles core logic for adding, deleting, searching, checking out, and returning media.
* Saves changes to the file after each modifying operation.
`Mediaitem`(Abstract Class)
* Superclass for `Book`, `DVD`, and `Magazine`.
* Implements `Loanable` interface.
* Contains:
	* Shared fields (like `title`, `itemID, ``isAvailable`)
	* File I/O methods: `readMediaFromFile` and `writeMediaToFile`
	* Core logic: `checkOut`, `returnItem`, `isAvailable`
`Book`, `DVD`, `Magazine`
* Subclasses of `MediaItem`.
* Each implements `getItemDetails()` to display type-specific info.
* Constructors used when reading from the file.
`Loanable` (Interface)
* Declares:
	* `checkOut()`
	* `returnItem()`
	* `isAvailable()`

## How To Run
1. Ensure `library_data.txt` is in the same directory as your `.java` files.
2. Run the program.
3. Follow the interactive menu to manage your library collection.

## Challenges Faced
* Designing robust file I/O that could handle various malformed records.
* Implementing inheritance and interfaces cleanly across multiple item types.
* Ensuring data integrity during runtime and consistent file saving after every modification.
* Managing user input gracefully and ensuring all operations were intuitive in a text-based UI.

# Author
Ava McDonald
	`mcdonaldal@g.cofc.edu`
