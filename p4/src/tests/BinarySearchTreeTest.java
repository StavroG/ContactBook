package tests;

import data_structures.BinarySearchTree;
import phone.PhoneNumber;


public class BinarySearchTreeTest implements Tester{
	BinarySearchTree<PhoneNumber, String> tree;
	
	public BinarySearchTreeTest() {
		tree = new BinarySearchTree<>();
		System.out.println("======= Testing BinarySearchTree =======");
		addTest();
		deleteTest();
		getValueTest();
		getKeyTest();
		System.out.println();
	}
	
	@Override
	public void addTest() {
		PhoneNumber num1 = new PhoneNumber("619-444-2222");
		PhoneNumber num2 = new PhoneNumber("619-999-9999");
		PhoneNumber num3 = new PhoneNumber("619-111-1111");
	
		tree.add(num1, "Jackson, Steve");
		tree.add(num2, "Evens, Cameron");
		tree.add(num3, "Miller, Susan");
		
		if (tree.contains(num1) && tree.contains(num2) && tree.contains(num3)) {
			tree.clear();
			tree.add(num1, "Jackson, Steve");
			if (tree.contains(num1)) {
				System.out.println("- Add Test Passed!");
			}
		}
		else {
			System.out.println("- Add Test Failed");
			System.out.println("-- Checking contains(num1) - " + tree.contains(num1));
			System.out.println("-- Checking contains(num2) - " + tree.contains(num2));
			System.out.println("-- Checking contains(num3) - " + tree.contains(num3));
		}
		tree.clear();
	}

	@Override
	public void deleteTest() {
		PhoneNumber num = new PhoneNumber("619-555-5555");
		tree.add(num, "Brown, Jack");
		tree.delete(num);
		if (tree.contains(num) && tree.delete(num) == false) {
			System.out.println("- Delete Test Failed");
			System.out.println("-- Size: " + tree.size() + ", should be 0");
		}
		else {
			System.out.println("- Delete Test Passed!");
		}
		tree.clear();
	}

	@Override
	public void getValueTest() {
		PhoneNumber num = new PhoneNumber("619-123-4567");
		tree.add(num, "Barber, Jake");
		
		if (tree.getValue(num) == "Barber, Jake") {
			System.out.println("- Get Value Test Passed!");
		} 
		else {
			System.out.println("- Get Value Test Failed");
			System.out.println("-- " + tree.getValue(num) + " is the value returned. Should be Barber, Jake.");
		}
		tree.clear();
	}

	@Override
	public void getKeyTest() {
		PhoneNumber num = new PhoneNumber("619-123-4567");
		tree.add(num, "Anderson, Brian");
		
		if (tree.getKey("Anderson, Brian") == num) {
			System.out.println("- Get Key Test Passed!");
		} 
		else {
			System.out.println("- Get Key Test Failed");
			System.out.println("-- " + tree.getKey("Anderson, Brian") + " is the key returned. Should be 619-123-4567.");
		}
		tree.clear();
	}

	
}
