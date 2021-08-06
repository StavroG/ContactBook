package tests;

import phone.PhoneBook;
import phone.PhoneNumber;

public class PhoneBookTest implements Tester{
	PhoneBook book;
	PhoneNumber num1;
	PhoneNumber num2;
	
	public PhoneBookTest() {
		book = new PhoneBook(10);
		num1 = new PhoneNumber("619-123-4567");
		num2 = new PhoneNumber("619-111-0000");
		
		System.out.println("======= Testing PhoneBook =======");
		addTest();
		deleteTest();
		getValueTest();
		getKeyTest();
		loadTest();
		printAreaCodeTest();
		printNames();
		System.out.println();
	}
	
	private void loadTest() {
		System.out.println("- Loading Contacts from SampleContacts.txt");
		book.load("C:\\Users\\stavr\\eclipse-workspace\\p4\\src\\tests\\SampleContacts.txt");
		book.printAll();
		System.out.println();
	}

	@Override
	public void addTest() {
		boolean result1 = book.addEntry(num1, "Last, Name");
		boolean result2 = book.addEntry(num2, "Last2, Name");
	
		if (result1 && result2) {
			System.out.println("- Add Test Passed!");
		}
		else {
			System.out.println("- Add Test Failed");
			System.out.println("-- First Result:" + result1 + " should be true");
			System.out.println("-- Second Result:" + result2 + " should be true");
		}
	}

	@Override
	public void deleteTest() {		
		boolean result1 = book.deleteEntry(num1);
		boolean result2 = book.deleteEntry(num2);
		
		if (result1 && result2) {
			System.out.println("- Delete Test Passed!");
		}
		else {
			System.out.println("- Delete Test Failed");
			System.out.println("-- First Result:" + result1 + " should be true");
			System.out.println("-- Second Result:" + result2 + " should be true");
		}
	}

	@Override
	public void getValueTest() {
		book.addEntry(num1, "Last, Name");
		
		if (book.numberLookup(num1) == "Last, Name") {
			System.out.println("- Get Value Test Passed!");
		}
		else {
			System.out.println("- Get Value Test Failed");
		}
	}

	@Override
	public void getKeyTest() {
		System.out.println("- Should print every contact in order by phone number");
		book.addEntry(num1, "Last, Name");
		
		if (book.nameLookup("Last, Name") == num1) {
			System.out.println("- Get Key Test Passed!");
		}
		else {
			System.out.println("- Get Key Test Failed");
		}
	}
	
	public void printTest() {
		book.printAll();
	}
	
	public void printAreaCodeTest() {
		System.out.println("- Should print all contacts with area code 619, in order by phone number");
		book.addEntry(new PhoneNumber("111-111-1111"), "test, name");
		book.printByAreaCode("619");
		System.out.println();
	}
	
	public void printNames() {
		System.out.println("- Should now print all the names in order by name");
		
		book.addEntry(new PhoneNumber("111-111-1111"), "Carlson, Jake");
		book.addEntry(new PhoneNumber("222-111-1111"), "Carlson, Debbie");
		book.addEntry(new PhoneNumber("333-111-1111"), "Carlson, Alex");
		book.addEntry(new PhoneNumber("444-111-1111"), "Allen, Alex");
		book.addEntry(new PhoneNumber("555-111-1111"), "Allen, Mary");
		book.addEntry(new PhoneNumber("666-111-1111"), "Cannon, Aiden");
		book.addEntry(new PhoneNumber("777-111-1111"), "Stephen, Andre");
		
		book.printNames();
		System.out.println();
	}
	
}
