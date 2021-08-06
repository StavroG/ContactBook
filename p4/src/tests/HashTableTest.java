package tests;


import data_structures.Hashtable;
import phone.PhoneNumber;



public class HashTableTest implements Tester {
	Hashtable<PhoneNumber, String> table;
	
	public HashTableTest() {
		table = new Hashtable<PhoneNumber, String>(10);
		System.out.println("======= Testing HashTable =======");
		addTest();
		deleteTest();
		getValueTest();
		getKeyTest();
		sizeClearTest();
		System.out.println();
	}
	
	public void addTest() {
		PhoneNumber num1 = new PhoneNumber("619-444-2222");
		PhoneNumber num2 = new PhoneNumber("619-999-9999");
		PhoneNumber num3 = new PhoneNumber("619-111-1111");
		
		table.add(num1, "Jackson, Steve");
		table.add(num2, "Evens, Cameron");
		table.add(num3, "Miller, Susan");
		
		if (table.contains(num1) && table.contains(num2) && table.contains(num3)) {
			System.out.println("- Add Test Passed!");
		}
		else {
			System.out.println("- Add Test Failed");
			System.out.println("-- Checking contains(num1) - " + table.contains(num1));
			System.out.println("-- Checking contains(num2) - " + table.contains(num2));
			System.out.println("-- Checking contains(num3) - " + table.contains(num3));
		}
		table.clear();
	}
	
	public void deleteTest() {
		PhoneNumber num = new PhoneNumber("619-555-5555");
		table.add(num, "Brown, Jack");
		table.delete(num);
		if (table.contains(num)) {
			System.out.println("- Delete Test Failed");
		}
		else {
			System.out.println("- Delete Test Passed!");
		}
		table.clear();
	}
	
	public void getValueTest() {
		PhoneNumber num = new PhoneNumber("619-123-4567");
		table.add(num, "Barber, Jake");
		
		if (table.getValue(num) == "Barber, Jake") {
			System.out.println("- Get Value Test Passed!");
		} 
		else {
			System.out.println("- Get Value Test Failed");
			System.out.println("-- " + table.getValue(num) + " is the value returned. Should be Barber, Jake.");
		}
		table.clear();
	}
	
	public void getKeyTest() {
		PhoneNumber num = new PhoneNumber("619-123-4567");
		table.add(num, "Anderson, Brian");
		
		if (table.getKey("Anderson, Brian") == num) {
			System.out.println("- Get Key Test Passed!");
		} 
		else {
			System.out.println("- Get Key Test Failed");
			System.out.println("-- " + table.getKey("Anderson, Brian") + " is the key returned. Should be 619-123-4567.");
		}
		table.clear();
	}
	
	private void sizeClearTest() {
		PhoneNumber num = new PhoneNumber("619-123-4567");
		table.add(num, "Anderson, Brian");
		
		if (table.size() == 1) {
			table.clear();
			if (table.size() == 0) {
				System.out.println("- Size and Clear Test Passed!");
			}
			else {
				System.out.println("- Clear Test Failed");
				System.out.println("-- Size: " + table.size() + ". Should be 0." );
			}
		}
		else {
			System.out.println("- Size Test Failed");
			System.out.println("-- Size: " + table.size() + ". Should be 1." );
		}
		table.clear();
	}
}





