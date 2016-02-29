package edu.saintjoe.cmullis;
	/*
	 * FriendList:  Use the UnsortedArray to store a list of friends, driven
	 * by a menu that allows us to interact with the UnsortedArray.
	 * 
	 * Original Code by Jennifer Coy and Rob Deadman
	 * Added to by Collin Mullis
	 * Spring 2016
	 */

	import java.util.*;	// for Scanner

	public class FriendList {

		//////////main/////////
		public static void main(String[] args) {

			UnsortedArray friendList = new UnsortedArray(); // a list of friends
			Scanner keyboard = new Scanner(System.in);		// to read from the keyboard
			int option = 0;									// which option the user chooses
			String userInput;								// the user's string input
			String userInput2;								// the user's string input to second prompt 
			DirectoryEntry temp;							// temporary directory entry

			// Loop, presenting a menu to the user each time
			while (true) {
				// print menu
				System.out.println("Select option:");
				System.out.println("1. Add a new entry");
				System.out.println("2. Delete an entry");
				System.out.println("3. Retrieve an entry");
				System.out.println("4. Update an entry");
				System.out.println("5. List all entries");
				System.out.println("6. Exit");

				// prompt user for a selection, read in their result
				System.out.println("Enter option:");
				userInput = keyboard.nextLine();  // read as a string
				// use exception handling, in case the user doesn't enter an integer...
				// otherwise, it will crash!!
				try {
					option = Integer.parseInt(userInput); // extract the first integer
				} catch (Exception e) {
					// if they didn't enter an integer, use the default of 0, which will
					// print an error message below.
				option = 0;
				}
				
				// perform the requested action
				if (option == 1) { 
					
					// add a new entry
					// first, we need to ask the user the name
					System.out.println("Enter new name: ");
					userInput = keyboard.nextLine();
					// second, create a temporary directory entry with this information
					temp = new DirectoryEntry();
					temp.setName(userInput);

					// third, add it to the list
					friendList.insert(temp);
					

				} else if (option == 2) { 
					
					// delete an entry
					// first, ask them who to delete
					System.out.println("Enter which name to Delete: ");
					userInput = keyboard.nextLine();
					temp = friendList.delete(userInput);
					// second, try to delete it
					if (temp == null) {
						System.out.println("User not found.");
					}
				} else if (option == 3) {
					// retrieve an entry
					// first, ask them who to look for
					System.out.println("Enter the name to Retrieve: ");
					userInput = keyboard.nextLine();
					temp = friendList.fetch(userInput);
					// second, try to fetch it
					if (temp != null) {
						System.out.println(temp);
					}
					else if (temp == null) {
						System.out.println("'" + temp + "'" + " not found.");
					}
					// third, report if it was found, or not found

				} else if (option == 4) {
					// update an entry
					// first, ask which entry to update
					System.out.println("What User would you like to update?");
					userInput = keyboard.nextLine();
					System.out.println("What would you like to change it to?");
					userInput2 = keyboard.nextLine();
					friendList.update(userInput, userInput2, "undefined", new int[] {0,0,0,0,0,0,0,0,0,0});
					// second, ask what to change the name to

					// third, update the entry

				} else if (option == 5) {
					// list all entries
					System.out.println(friendList);
				} else if (option == 6) {
					// exit the loop
					break;
				} else {
					// they entered something else
					System.out.println("Error, please choose an option between 1 and 6.");
				}
			}
			
			// close the keyboard resource before exiting
			keyboard.close();
		}

	}

