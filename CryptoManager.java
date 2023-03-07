

public class CryptoManager {
	
	private static final char LOWER_BOUND = ' ';
	private static final char UPPER_BOUND = '_';
	private static final int RANGE = UPPER_BOUND - LOWER_BOUND + 1;

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_BOUND and UPPER_BOUND characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean stringInBounds (String plainText) {
		boolean testResult = true;
		/**
		 * This for loop will go through the entire length of the string and check the bounds of each character
		 * It will return false if any of the characters are out of bounds
		 * */
		for (int i = 0; i < plainText.length(); i++) 
		{
			int char1 = plainText.charAt(i);
			if (char1 < LOWER_BOUND || char1 > UPPER_BOUND)
			{
				testResult = false;
			}
		}
		
		return testResult;
		
		//throw new RuntimeException("method not implemented");
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String encryptCaesar(String plainText, int key) {
		
		// Create a new StringBuffer object that will contain the new encrypted string
		StringBuffer newString = new StringBuffer();
		
		/**
		 * This for loop will go through all the character of plainText and assign it to a
		 * variable , while offsetting the character by the key. Then those
		 * characters will be added to the StringBuffer object
		 * */
		for (int i = 0; i < plainText.length(); i++) 
		{
			int char1 = plainText.charAt(i);
			char1 += key;
			while (char1 < LOWER_BOUND || char1 > UPPER_BOUND)
			{
			if (char1 < LOWER_BOUND)
				char1 += RANGE;
			else if (char1 > UPPER_BOUND)
				char1 -= RANGE;
			}	
			
			newString.append((char)char1);
		}
		return newString.toString();
		
		/** 
		 * Error appeared stating "Unreachable code"
		 * For the fix the IDE suggested the line to be removed
		 */
		//throw new RuntimeException("method not implemented");
	}
	
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String encryptBellaso(String plainText, String bellasoStr) {
		
		// Create a new StringBuffer object that will contain the new encrypted string
		StringBuffer newString = new StringBuffer();
		
		int char1;				// The ASCII code of plainText
		int char2;				// The ASCII code of the bellasoStr
		int keyLocation = 0;	// The location in the bellasoStr string
		int combination;		// the new ASCII code for the encrypted string
		
		/**
		 * This for loop will go through the plainText string and get the ASCII of the current char
		 * it is on, then it will get the ASCII code of bellasoStr, making sure that is not out of bounds.
		 * Then it will save the new offset chars into the newString object
		 */
		for (int i = 0; i < plainText.length(); i++, keyLocation++) {
			
			// Reset keyLocation to 0, if it has exceeded the length of the bellasoStr string
			if (keyLocation >= bellasoStr.length())
			{
				keyLocation = 0;
			}
			
			// Assign the int variables their corresponding ASCII code
			char1 = plainText.charAt(i);
			char2 = bellasoStr.charAt(keyLocation);
			
			// Calculate the combined ASCII code
			combination = (char1 + char2) - RANGE;
			
			// Check combination exceeds the bounds of the range, if it is reduce it again by RANGE
			if (combination > (int)UPPER_BOUND)
			{
				combination -= RANGE;
			}
			else if (combination < (int)LOWER_BOUND)
			{
				combination += RANGE;
			}
			
			// Add characters to newString
			newString.append((char)combination);
		}
		
		return newString.toString();
		
		/** 
		 * Error appeared stating "Unreachable code"
		 * For the fix the IDE suggested the line to be removed
		 */
		//throw new RuntimeException("method not implemented");
	}
	
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String decryptCaesar(String encryptedText, int key) {
		// New StringBuffer object to hold decrypted text
		StringBuffer newString = new StringBuffer();
		
		int char1;			// ASCII code of the a specific character in encryptedText
		int newChar;		// ASCII code of the decrypted character
		
		/**
		 * This for loop will go through the encrypted text and change take the characters and offset them
		 * back by subtracting the key from the current ASCII code, then place them in the new decrypted string
		 */
		for (int i = 0; i < encryptedText.length(); i++) {
			char1 = encryptedText.charAt(i);
			newChar = char1 - key;
			while (newChar < LOWER_BOUND || newChar > UPPER_BOUND)
			{
			if (newChar < LOWER_BOUND)
				newChar += RANGE;
			else if (char1 > UPPER_BOUND)
				newChar -= RANGE;
			}	
			newString.append((char)newChar);
		}
		
		return newString.toString();
		
		/** 
		 * Error appeared stating "Unreachable code"
		 * For the fix the IDE suggested the line to be removed
		 */
		//throw new RuntimeException("method not implemented");
	}
	
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String decryptBellaso(String encryptedText, String bellasoStr) {
		// StringBuffer object that will hold the decrypted Bellaso
		StringBuffer newString = new StringBuffer();
		
		int char1;			// ASCII code of the specific character of the encrypedText
		int char2;			// ASCII code of the specific character of the bellasoStr
		int keyLocation = 0;// The location in the string bellasoStr
		int combination;	// ASCII code of the decrypted character
		
		for (int i = 0; i < encryptedText.length(); i++, keyLocation++) {
			// Reset keyLocation to 0, if it has exceeded the length of the bellasoStr string
			if (keyLocation >= bellasoStr.length())
			{
				keyLocation = 0;
			}
			
			// Assign the int variables their corresponding ASCII code
			char1 = encryptedText.charAt(i);
			char2 = bellasoStr.charAt(keyLocation);
			
			// Calculate the combined ASCII code
			combination = (char1 + (int) RANGE) - char2;
			
			// Check combination exceeds the bounds of the range, if it is add it again by RANGE
			if (combination < (int)LOWER_BOUND)
			{
				combination += RANGE;
			}
			else if (combination > (int) UPPER_BOUND)
			{
				combination -= RANGE;
			}
			
			// Add characters to newString
			newString.append((char)combination);
		}
		
		return newString.toString();
		
		/** 
		 * Error appeared stating "Unreachable code"
		 * For the fix the IDE suggested the line to be removed
		 */
		//throw new RuntimeException("method not implemented");
	}
}
