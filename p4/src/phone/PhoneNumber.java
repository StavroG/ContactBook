package phone;

public class PhoneNumber implements Comparable<PhoneNumber> {
    String areaCode, prefix, number;
    String phoneNumber;
   
    // Constructor.  Creates a new PhoneNumber instance.  The parameter
    // is a phone number in the form xxx-xxx-xxxx, which is area code -
    // prefix - number.  The phone number must be validated, and an
    // IllegalArgumentException thrown if it is invalid.
    public PhoneNumber(String n) {
    	verify(n);
    	phoneNumber = n;
    	areaCode = n.substring(0, 3);
    	prefix = n.substring(4, 7);
    	number = n.substring(8, 12);
    }
    
    // Follows the specifications of the Comparable Interface.  
    public int compareTo(PhoneNumber n) {
		if (n == null) {
			throw new NullPointerException();
		}
		int hashCode = hashCode();
		int compareHashCode = n.hashCode();
		if (hashCode < compareHashCode){	//If phone number is less than n.
			return -1;
		}
		if (hashCode > compareHashCode) {	//If phone number is greater than n.
			return 1;
		}
    	return 0;	//If phone number is equal to n
    }
       
    // Returns an int representing the hashCode of the PhoneNumber.
    public int hashCode() {
		int hashMultiplier = 6;
    	return (Integer.parseInt(areaCode + prefix) * hashMultiplier) + (Integer.parseInt(number) * hashMultiplier);  
    }
   
    // Private method to validate the Phone Number.  Should be called
    // from the constructor.   
    private void verify(String n) {
    	if (n.length() != 12 || n.charAt(3) != '-' || n.charAt(7) != '-') {
    		//Checks the area code, prefix and number to make sure that they are digits.
    		if ((verifyDigits(n.substring(0, 2)) && verifyDigits(n.substring(4, 6)) && verifyDigits(n.substring(8, 11))) == false) {
    			throw new IllegalArgumentException();
    		}
    	}
    }
       
    private boolean verifyDigits(String n) {	//Helper method to check if a string contains all digits.
    	for (int i = 0; i < n.length(); i++) {
    		if (!Character.isDigit(n.charAt(i))) {	//Loops through the string and checks every character to check if they are a digit.
    			return false;	//If not a digit return false;
    		}
    	}
    	return true;	//If every character in the string is a digit, return true.
    }
    
    // Returns the area code of the Phone Number.
    public String getAreaCode() {
		return areaCode;
    }
       
    // Returns the prefix of the Phone Number.
    public String getPrefix() {
		return prefix;
    }
       
    // Returns the last four digits of the number.
    public String getNumber() {
		return number;
    }

    // Returns the Phone Number.       
    public String toString() {
		return phoneNumber;
    }
}