/*
 * @author: Stavro Gorou
 * GitHub: https://github.com/StavroG
 * Date: 08/05/21
 * Description: This is a contact book project that saves contacts with their name and phone numbers.
 */

package driver;

import tests.BalancedTreeTest;
import tests.BinarySearchTreeTest;
import tests.HashTableTest;
import tests.PhoneBookTest;

public class Driver {
	public static void main(String[] args) {
		new BinarySearchTreeTest();
		new BalancedTreeTest();
		new HashTableTest();
		new PhoneBookTest();
	}
}
