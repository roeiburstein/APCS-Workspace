import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Cipher {
	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.() '\"![]/%-_;?-=:"
			+ '\n' + '\r';
	// private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz ";

	public static Dictionary dictionary = Dictionary.buildDictionary("words.txt");

	public static boolean isDecoded(String plain) {
		int counter = 0;
		plain = plain.toLowerCase();
		String[] words = plain.split(" ");
		for (int i = 0; i < words.length; i++) {
			if (dictionary.isWord(words[i])) {
				counter++;
			}
		}
		
		if((double)counter / words.length >= 0.5)
		return true;
		return false;
	}

	public static String getFileAsString(String filename) {
		Scanner s;
		StringBuilder sb = new StringBuilder(500000);
		try {
			s = new Scanner(new FileReader(filename));
			s.useDelimiter("");

			while (s.hasNext()) {
				String n = s.next();
				sb.append(n);
			}
		} catch (Exception e) {

		}
		return sb.toString();
	}

	public static void writeStringToFile(String filename, String text) {
		try {
			PrintWriter out = new PrintWriter(filename);
			for (int i = 0; i < text.length(); i++) {
				char n = text.charAt(i);
				out.print(n);
			}
			out.close();
		} catch (Exception e) {
		}
	}

	public static String vigenereCipherDecrypt(String cipher, String code) {
		return vigenereCipher(cipher, code, -1);
	}

	public static String vigenereCipherEncrypt(String cipher, String code) {
		return vigenereCipher(cipher, code, 1);
	}

	// direction must be +1 or -1 depending on direction to shift.
	private static String vigenereCipher(String plain, String code, int direction) {
		String cipher = "";
		int originalIndex, shiftAmount, newIndex;
		int length = code.length();

		if (!isValidCodeWord(code))
			return "INVALID CODEWORD!";
		plain = stripInvalid(plain);

		for (int i = 0; i < plain.length(); i++) {
			char c = plain.charAt(i);
			originalIndex = ALPHABET.indexOf(c);

			if (originalIndex >= 0) {
				shiftAmount = ALPHABET.indexOf(code.charAt(i % length));

				newIndex = originalIndex + direction * shiftAmount;

				while (newIndex < 0)
					newIndex += ALPHABET.length();
				newIndex %= ALPHABET.length();

				cipher += ALPHABET.charAt(newIndex);
			} else {
				// System.out.println("Skipping letter: \"" + c + "\"");
			}
		}
		return cipher;
	}

	// Strips out all characters not in our alphabet
	private static String stripInvalid(String plain) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < plain.length(); i++) {
			if (ALPHABET.indexOf(plain.charAt(i)) >= 0)
				b.append(plain.charAt(i));
			else
				System.out.println("Stripping letter: \"" + plain.charAt(i) + "\"");
		}
		return b.toString();
	}

	private static boolean isValidCodeWord(String code) {
		for (int i = 0; i < code.length(); i++) {
			if (!ALPHABET.contains(code.substring(i, i + 1)))
				return false;
		}

		return true;
	}

	private static String rotationCipher(String plainText, int shiftValue) {
		while (shiftValue < 0)
			shiftValue += ALPHABET.length();
		shiftValue = shiftValue % ALPHABET.length();
		String cipherText = "";

		for (int i = 0; i < plainText.length(); i++) {
			String letter = plainText.substring(i, i + 1);

			int shiftedIndex = ALPHABET.indexOf(letter) + shiftValue;

			while (shiftedIndex >= ALPHABET.length())
				shiftedIndex -= ALPHABET.length();
			cipherText += ALPHABET.substring(shiftedIndex, shiftedIndex + 1);
		}

		return cipherText;
	}
	
	public static String newRotationCipher(String fileName, int shiftValue) {
		while (shiftValue < 0)
			shiftValue += ALPHABET.length();
		shiftValue = shiftValue % ALPHABET.length();
		String cipherText = "";
		String plainText = Cipher.getFileAsString(fileName);

		findMostFrequentLetter.findMostFrequentLetters(false, "ciphertext1.txt");
		
		

		return cipherText;
	}

	public static String rotationCipherEncrypt(String plain, int movement) {
		return rotationCipher(plain, movement);
	}

	public static String rotationCipherDecrypt(String plain, int movement) {
		return rotationCipher(plain, -movement);
	}
}