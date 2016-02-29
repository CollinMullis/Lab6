package edu.saintjoe.cmullis;
	/** 
	 * DirectoryEntry class:  store name, address, and phone number for a directory entry
	 * 
	 * For this lab:  Develop the getPhoneNumber() function to print nicely, create several
	 *                Directory Entry objects for friends, print out just their phone numbers area codes.
	 * 
	 * Original @author Jennifer Coy and Rob Deadman
	 * Spring 2016
	 *
	 */
	public class DirectoryEntry {
		//////////constants///////////
		public static final int DIGITS = 10;	// store 10 digit phone numbers
		
		//////////class data members///////////
		private String name;		// full name of this person:  Last, First
		private String address;		// address:  street, state zip
		private int[] phoneNumber;	// 10 single digits of the phone number
		
		//////////////////
		// Constructors //
		//////////////////
		/* create a default (empty) directory entry */
		public DirectoryEntry() {
			name = new String("undefined");
			address = new String("undefined");
			
			// create and initialize
			phoneNumber = new int[DIGITS];
			for (int i=0; i<DIGITS; i++) {
				phoneNumber[i] = 0;
			}
		}
		/* create a pre-filled directory entry */
		public DirectoryEntry(String newName, String newAddress, int[] newPhoneNumber) {
			name = newName;
			address = newAddress;
			phoneNumber = newPhoneNumber;
		}
		
		///////////////
		// Accessors //
		///////////////
		public String getName() {
			return name;
		}
		public String getAddress() {
			return address;
		}
		/* return phone number as a string */
		public String getPhoneNumber() {
			String stringNumber = new String();	// the number as a String
			
			/* assemble area code */
			stringNumber = "(" + phoneNumber[0] + phoneNumber[1] + phoneNumber[2] + ") ";
			/* assemble next 3 numbers */
			for (int i=3; i<=5; i++) {
				stringNumber = stringNumber + phoneNumber[i];
			}
			/* add the -, then the rest of the numbers */
			stringNumber = stringNumber + "-";
			for (int i=6; i<DIGITS; i++) {
				stringNumber = stringNumber + phoneNumber[i];
			}
			
			/* return the result */
			return stringNumber;
		}
		
		/* return just the area code */
		public String getAreaCode() {
			return "(" + phoneNumber[0] + phoneNumber[1] + phoneNumber[2] + ")";
		}
		
		//////////////
		// Mutators //
		//////////////
		public void setName(String newName) {
			name = new String(newName);  // create a copy of the String object
		}
		public void setAddress(String newAddress) {
			address = new String(newAddress);	// create a copy of the String object
		}
		public void setPhoneNumber(int[] newPhoneNumber) {
			phoneNumber = newPhoneNumber;
		}
		
		//////////////////////
		// Standard Methods //
		//////////////////////
		public String toString() {
			return name + ", " + address + ", " + getPhoneNumber() + "\n";
		}
		
		/* deepCopy:  create a new node, copy all of the contents of this node, and return the copy */
		public DirectoryEntry deepCopy() {
			/* allocate memory for a new Person */
			DirectoryEntry clone = new DirectoryEntry();
			
			/* copy contents */
			clone.setName(name);
			clone.setAddress(address);
			clone.setPhoneNumber(phoneNumber);
			
			/* return new object */
			return clone;
		}
		
		///////////////////////
		// main, for testing //
		///////////////////////
		public static void main (String[] args) {
			DirectoryEntry aPerson = new DirectoryEntry();		// create a test person 1
			DirectoryEntry bPerson = new DirectoryEntry();		// create a test person 1
			DirectoryEntry cPerson = new DirectoryEntry();		// create a test person 1
			int [] number;						// an array of the phone number
			
			/* set up the number array */
			number = new int[] {2,1,9,8,6,6,6,1,9,7};  // initialize contents
			
			/* set up the contents */
			aPerson.setName("Jane Doe");
			aPerson.setAddress("123 My Street, IN, 47978");
			aPerson.setPhoneNumber(number); // an array of ints as the number
			
			/* print it out to test */
			System.out.println(aPerson);
			
			/* set up b and c people */
			bPerson.setName("John Doe");
			bPerson.setAddress("456 Somewhere Street");
			number = new int[] {7,6,5,5,5,5,5,5,5,5};
			bPerson.setPhoneNumber(number);
			cPerson.setName("Jack Reacher");
			cPerson.setAddress("here or there");
			number = new int[] {4,1,9,5,5,5,5,5,5,5};
			cPerson.setPhoneNumber(number);
			
			/* print them out */
			System.out.println(bPerson);
			System.out.println(cPerson);
			
			/* print out just the area codes */
			System.out.println("The area codes are: ");
			System.out.println(aPerson.getAreaCode());
			System.out.println(bPerson.getAreaCode());
			System.out.println(cPerson.getAreaCode());
		}
	}
