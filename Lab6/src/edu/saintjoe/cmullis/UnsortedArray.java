package edu.saintjoe.cmullis;
/*  
 * UnsortedArray:  one implementation of an unsorted array
 * Original Code by Jennifer Coy and Rob Deadman
 * Spring 2016
 */
public class UnsortedArray {
	
	//// data members ///
	public final static int MAX_SIZE = 20;
	private int next;				 /* the next array index to store an entry */
	private DirectoryEntry data[];   /* array of directory entries */

	//////Constructor////////
	public UnsortedArray() {
		next = 0;	/* start at the beginning */
		
		/* two steps to initialize a primitive array */
		/* first, get the memory using new */
		data = new DirectoryEntry[MAX_SIZE];
		/* second, zero out the array */
		for(int i=0; i < MAX_SIZE; i++) {
			data[i] = null;
		}
	}
	
	/// insert //
	/* add a DirectoryEntry to the array using two steps:
	 * make a deep copy of the Directory entry into the next array element
	 * increment next
	 */
	public void insert(DirectoryEntry newDirectoryEntry) {
		/* make sure there is room */
		if (next == MAX_SIZE) {
			/* array is full! */
			/* handle this in a NON OBJECT ORIENTED way, and quit the whole program */
			System.out.println("Error!  UnsortedArray is full.");
			System.exit(404);
		}
		
		/* make a deep copy of the newDirectoryEntry and store into array */
		data[next] = newDirectoryEntry.deepCopy();
		
		/* increment next */
		next++;    /* next = next + 1 */
	}
	
	/// fetch ///
	/* search for a person's name in the array
	 * return a deep copy of that node
	 */
	public DirectoryEntry fetch(String targetKey) {
		int i = 0;   /* loop counter, and index of the found item */
		
		/* perform a sequential search looking for the name targetKey */
		/* find the index of this name */
		for (i=0; i<next; i++) {
			if (data[i].getName().equals(targetKey)) {
				/* found it! */
				break;  /* exit for loop, stop looking! */				
			}
		}  /* if I found it, i will be the item */
		
		/* if I find it, return a deep copy of the node */
		if (i<next) {
			return data[i].deepCopy();
		}
		/* if I don't find it.... return null */
		else {
			return null;
		}
	}
	
	/* delete: add a DirectoryEntry to the array using three basic steps:
	 *     1. sequentially search for the desired node (with name of targetKey)
	 *     2. delete the node
	 *     3. garbage collect to recover the storage for that node
	 * The deleted node will be returned; if not found, null will be returned.
	 */
	public DirectoryEntry delete(String targetKey) {
		int i = 0;			/* current node */
		DirectoryEntry deletedNode;		// the node that we deleted
		
		// find the node we want to delete
		/* perform a SEQUENTIAL SEARCH through the array to locate a node with
		 * the name targetKey
		 */
		i = 0;  // start at the beginning
		// loop until we reach the end
		while (i < data.length) {
			if ((data[i] != null) && (data[i].getName().equals(targetKey))) {
				/* is this the one we are looking for? */
				break;  // exit the while loop with i equal to the one we want! 
			} else { 
				/* this is not the one we are looking for */
				i++;  // move to the next one
			}
		}
		/* error check:  did we hit the end without finding it? */
		if (i==data.length) {
			// oops, didn't find it!
			return null;  
		} else {
			// we found it:  data[i]
			// delete it
			deletedNode = data[i];  // save the node in case we need, we'll return it later
			data[i] = null;  //delete the node
			
			// reclaim the empty spot
			// start where this node is [i], and scoot everything up one spot in the array,
			// until we get to the end
			for (int j=i; j < data.length-1; j++) { // could do j < next-1
				// move, for example, data[1] into data[2]
				data[j] = data[j+1];
			}
			
			// clear out the very last entry
			data[next] = null;
			
			// one fewer items in the array now
			next--;   // or next = next - 1
		}
		
		// return the deleted node, in case the user wants to do something it
		return deletedNode;
		
	}
	
	/* update:  change the contents of a node with the targetKey
	 * to match the arguments presented.
	 */
	public void update(String targetKey, String newName, String newAddress, int[] newPhoneNumber) {
		DirectoryEntry newNode = null;		/* the node to update */
		
		/* delete this node */
		delete(targetKey);
		
		/* insert the updated node */
		newNode = new DirectoryEntry(newName, newAddress, newPhoneNumber);
		insert(newNode);		
	}

	
	//// toString ///
	public String toString() {
		String output = new String();		/* output to return */
		
		output = "This UnsortedArray contains " + next + " elements.\n";
		
		/* print out the contents of the array */
		//for (int i=0; i < MAX_SIZE; i++) {
		for (int i=0; i < next; i++) {
			output = output + i + ": " + data[i];
		}
		
		/* return final output string */
		return output;
	}
	
	//////////main/////////
	// test out this class's code
	public static void main(String[] args) {

		/* variables used in this method */
		UnsortedArray testArray = new UnsortedArray();  /* an unsorted array */
		/* five directory entries to use with tests */
		DirectoryEntry phil = new DirectoryEntry("Phil", "Avenue P", new int[] {5,5,5,5,7,2,8,3,9,1});	
		DirectoryEntry bill = new DirectoryEntry("Bill", "Avenue B", new int[] {5,5,5,9,7,8,5,6,3,4});	
		DirectoryEntry carol = new DirectoryEntry("Carol", "Avenue C", new int[] {5,5,5,2,3,4,8,9,8,9});	
		DirectoryEntry vick = new DirectoryEntry("Vick", "Avenue V", new int[] {5,5,5,9,3,6,7,2,8,1});	
		DirectoryEntry mike = new DirectoryEntry("Mike", "Avenue M", new int[] {5,5,5,5,4,6,7,3,7,3});	
		DirectoryEntry temp = new DirectoryEntry();		/* blank, used for fetch */
		
		/* print out the contents of the (empty) array */
		System.out.println(testArray);
		
		/* add an element */
		System.out.println("Adding Phil.");
		testArray.insert(phil);
		System.out.println(testArray);
		
		/* add another element */
		System.out.println("Adding Bill.");
		testArray.insert(bill);
		System.out.println(testArray);
		
		/* add the remaining elements */
		System.out.println("Adding Carol, Vick, and Mike.");
		testArray.insert(carol);
		testArray.insert(vick);
		testArray.insert(mike);
		System.out.println(testArray);
		
		/* fetch an entry:  mike */
		temp = testArray.fetch("Mike");
		System.out.print("Fetched record: " + temp);
		if (temp == mike) {
			System.out.println(" temp and mike are the same object.\n");
		} else {
			System.out.println(" temp and mike are NOT the same object, temp is a copy.\n");
		}
		
		/* delete carol */
		System.out.println("Deleting Carol.");
		testArray.delete("Carol");
		System.out.println(testArray);
		
		/* update phil */
		System.out.println("Updating Phil.");
		testArray.update("Phil", "Phillip", phil.getAddress(), new int[] {5,5,5,5,5,5,5,5,5,5});
		System.out.println(testArray);	


	}
}

