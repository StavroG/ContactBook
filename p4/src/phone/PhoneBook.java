package phone;

import java.util.Iterator;

import data_structures.DictionaryADT;
import data_structures.Hashtable;

import java.io.*;

public class PhoneBook {
	private DictionaryADT<PhoneNumber, String> table;
	final int MAX_SIZE;
	
    // Constructor.  There is no argument-less constructor, or default size
    public PhoneBook(int maxSize) {
    	table = new Hashtable<PhoneNumber, String>(maxSize);
    	MAX_SIZE = maxSize;
    }
       
    // Reads PhoneBook data from a text file and loads the data into
    // the PhoneBook.  Data is in the form "key=value" where a phoneNumber
    // is the key and a name in the format "Last, First" is the value.
    @SuppressWarnings("resource")
	public void load(String filename) {
    	try {
    		FileReader file = new FileReader(filename);
    		BufferedReader reader = new BufferedReader(file);
    		String line;
    		while((line = reader.readLine()) != null) {	//Loops through every line in the file.
    			table.add(new PhoneNumber(line.substring(0, 12)), line.substring(13));	//Adds every line from the file to the hashtable.
    		}
    	}	
    	catch (FileNotFoundException e ) {
    		System.out.println("Could not find file.");
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
           
    // Returns the name associated with the given PhoneNumber, if it is
    // in the PhoneBook, null if it is not.
    public String numberLookup(PhoneNumber number) {
		return table.getValue(number);
    }
       
    // Returns the PhoneNumber associated with the given name value.
    // There may be duplicate values, return the first one found.
    // Return null if the name is not in the PhoneBook.
    public PhoneNumber nameLookup(String name) {
		return table.getKey(name);
    }
       
    // Adds a new PhoneNumber = name pair to the PhoneBook.  All
    // names should be in the form "Last, First".
    // Duplicate entries are *not* allowed.  Return true if the
    // insertion succeeds otherwise false (PhoneBook is full or
    // the new record is a duplicate).  Does not change the datafile on disk.
    public boolean addEntry(PhoneNumber number, String name) {
		return table.add(number, name);
    }
       
    // Deletes the record associated with the PhoneNumber if it is
    // in the PhoneBook.  Returns true if the number was found and
    // its record deleted, otherwise false.  Does not change the datafile on disk.
    public boolean deleteEntry(PhoneNumber number) {
		return table.delete(number);
    }
       
    // Prints a directory of all PhoneNumbers with their associated
    // names, in sorted order (ordered by PhoneNumber).
    @SuppressWarnings("unchecked")
	public void printAll() {
    	Iterator<PhoneNumber> number = table.keys();
    	PhoneNumber[] sortedNums = new PhoneNumber[table.size()];
    	
    	int count = 0;
    	while(number.hasNext()) {	//Adds all the numbers from the iterator to an array that will be sorted.
    		sortedNums[count++] = number.next();
    	}
    	sortByNumber(sortedNums);	//Sorts the numbers.
    	
    	for (int i = 0; i < sortedNums.length; i++) {	//Prints the sorted numbers.
    		System.out.println(table.getValue(sortedNums[i]) + "=" + sortedNums[i]);
    	}
    }
       
    // Prints all records with the given Area Code in ordered
    // sorted by PhoneNumber.
    @SuppressWarnings("unchecked")
	public void printByAreaCode(String code) {
    	Iterator<PhoneNumber> numbers = table.keys();
    	PhoneNumber[] sortedNums = new PhoneNumber[table.size()];
    	
    	int count = 0;
    	while (numbers.hasNext()) {	//Adds all the numbers from the iterator to an array that will be sorted.
    		sortedNums[count++] = numbers.next();
    	}
    	sortByNumber(sortedNums);	//Sorts the numbers.

    	for (int i = 0; i < sortedNums.length; i++) {
    		if (sortedNums[i].areaCode.compareTo(code) == 0) {	//Checks the sorted number at i and checks if the area code matches code.
    			System.out.println(table.getValue(sortedNums[i]) + "=" + sortedNums[i]);	//If area codes match, then print.
    		}
    	}
    }
   
    // Prints all of the names in the directory, in sorted order (by name,
    // not by number).  There may be duplicates as these are the values.       
    @SuppressWarnings("unchecked")
	public void printNames() {
    	Iterator<String> names = table.values();
    	String[] sortedNames = new String[table.size()];
    	
    	int count = 0;
    	while (names.hasNext()) {	//Adds all the names from the iterator to an array that will be sorted.
    		sortedNames[count++] = names.next();
    	}
    	sortByName(sortedNames);	//Sorts the array.
    	for (int i = 0; i < sortedNames.length; i++) {	//Prints all the sorted names.
    		System.out.println(sortedNames[i]);	
    	}
    }
    
    private void sortByNumber(PhoneNumber[] numbers) {
    	for (int i = 0; i < numbers.length; i++) {
    		for (int j = 0; j < numbers.length; j++) {
    			PhoneNumber tmp; 
    			if (numbers[i].areaCode.compareTo(numbers[j].areaCode) != 0) {	//If area codes are not the same.
    				if (numbers[i].areaCode.compareTo(numbers[j].areaCode) < 0) {//Check if areacode at i is < prefix at j, if so swap.
    					//Swapping
    					tmp = numbers[i];
    					numbers[i] = numbers[j];
    					numbers[j] = tmp;
    				}
    			}
    			else if (numbers[i].prefix.compareTo(numbers[j].prefix) != 0) {	//If area code is same but not prefix.
    				if (numbers[i].prefix.compareTo(numbers[j].prefix) < 0) {	//Check if prefix at i is < prefix at j, if so swap.
    					//Swapping
    					tmp = numbers[i];
    					numbers[i] = numbers[j];
    					numbers[j] = tmp;
    				}
    			}
    			else {	//If area code and prefix are same but not number.
    				if (numbers[i].number.compareTo(numbers[j].number) < 0) {//Check if numer at i is < prefix at j, if so swap.
    					//Swapping
    					tmp = numbers[i];
    					numbers[i] = numbers[j];
    					numbers[j] = tmp;
    				}
    			}
    		}
    	}
    }
    
    private void sortByName(String[] names) {
    	for (int i = 0; i < names.length; i++) {
    		for (int j = 0; j < names.length; j++) {
    			//Sorts by last name
    			if (names[i].toLowerCase().substring(0, names[i].indexOf(',')).compareTo(names[j].toLowerCase().substring(0, names[j].indexOf(','))) == 0) {	//If last names match
    				if (names[i].toLowerCase().substring(names[i].indexOf(' ') + 1).compareTo(names[j].toLowerCase().substring(names[j].indexOf(' ') + 1)) < 0) {	//Checks first names.
    					//Swapping
        				String tmp = names[i];
        				names[i] = names[j];
        				names[j] = tmp;
    				}
    			}
    			else if (names[i].toLowerCase().substring(0, names[i].indexOf(',')).compareTo(names[j].toLowerCase().substring(0, names[j].indexOf(','))) < 0) {	// Checking last names
    				//Swapping
    				String tmp = names[i];
    				names[i] = names[j];
    				names[j] = tmp;
    			}
    		}
    	}
    }
}













