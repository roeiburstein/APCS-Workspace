import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class StudentCipher {
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.() '\"![]/%-_;?-=:" + '\n' + '\r';
	public static Dictionary dictionary = Dictionary.buildDictionary("c:\\data\\words.txt");
	public static String validPasswordChars = "abcdefghijklmnopqrstuvwx ";
	public static List<String> engFreq = new ArrayList<String>( Arrays.asList(" ", "e", "t", "a", "o", "i", "n", "s", "h", "r","d","l","c","u","m","w","f","g","y","p","b","v","k","j","x","q","z"));
	
	public static void main(String[] args) {
		String mystery = getFileAsString("c:\\data\\ciphers\\cipher1.txt");

		String plain = "\"THE\n\rQUICK\nBROWN FOX. JUMPED OVER THE LAZY DOGS!\"";
		String cipher = StudentCipher.rotationCipherEncrypt(plain, 100);
		
		System.out.println(cipher);
		
		//crackRotation(mystery);
		
//		String decoded = vigenereCipherDecrypt(mystery, "APlus");
//		System.out.println(decoded);
//		
//		String[] groups = splitString(mystery, 5);
//		
//		for (int shift = 0; shift < ALPHABET.length(); shift++) {
//			String decoded = rotationCipherDecrypt(groups[0], shift);
//			System.out.print("With shift " + shift + " " + orderDistanceFromEnglish(decoded));
//			
//		}
		
	}
	
	private static void crackRotation(String mystery, String ALPHABET) {
		for (int i = 0; i < ALPHABET.length(); i++) {
			String solved = rotationCipherDecrypt(mystery, i);
			if (isDecoded(solved)) {
				System.out.println(i);
			}
		}
	}

	public static int orderDistanceFromEnglish(String text) {
		Bag b = new Bag();
		for (int i = 0; i < text.length(); i++) {
			String letter = (""+text.charAt(i)).toLowerCase();
			if (validPasswordChars.contains(letter)) 
				b.add(letter);
		}
		
		List<String> freq = b.getSortedList();
		int dist = 0;
		for (int i = 0; i < freq.size(); i++) {
			dist += Math.abs(i - engFreq.indexOf(freq.get(i)));
		}
		System.out.println("Total distance: " + dist);
		return dist;
	}
	
	// Check if a decoded letter group's top k most frequent letters
	// are the same as english.
	public static boolean couldBeDecoded(String text, int k) {
		Bag b = new Bag();
		for (int i = 0; i < text.length(); i++) {
			String letter = (""+text.charAt(i)).toLowerCase();
			//if (valid.contains(letter)) 
				b.add(letter);
		}
		
		List<String> freq = b.getNMostFrequentStrings(k);
		int count = 0;
		for (int i = 0; i < freq.size(); i++) {
			if (freq.get(i).equals(engFreq.get(i))) count++;
		}
		System.out.println("Most frequent: " + freq);
		 return (count >= k);
	}

	public static String getShiftLetter(String codeLetter,
			String plainTextLetter) {
		int index1 = ALPHABET.indexOf(codeLetter);
		int index2 = ALPHABET.indexOf(plainTextLetter);
		if (index1 == -1 || index2 == -1)
			return null;

		if (index1 > index2)
			return "" + ALPHABET.charAt(index2 - index1);
		return "" + ALPHABET.charAt((index1 + ALPHABET.length()) - index2);
	}

	public static List<String> combine(List<String> aList, List<String> bList,
			List<String> cList) {
		List<String> ret = new ArrayList<String>();

		for (String a : aList) {
			for (String b : bList) {
				for (String c : cList) {
					ret.add(a + b + c);
				}
			}
		}

		return ret;
	}

	// splits the input string in into n groups
	public static String[] splitString(String in, int n) {
		String[] groups = new String[n];
		
		StringBuilder[] builders = new StringBuilder[n];
		
		for (int i = 0; i < n; i++)
			builders[i] = new StringBuilder();

		for (int i = 0; i < in.length(); i++) {
			builders[i % n].append(in.charAt(i));
		}
		
		for (int i = 0; i < n; i++)
			groups[i] = builders[i].toString();
		
		return groups;
	}

	private static boolean isDecoded(String plain) {
		int count = 0;

		for (String word : plain.split(" ")) {
			if (dictionary.isWord(word))
				count++;
		}
		return ((double) count / plain.split(" ").length) > 0.5;
	}

	@SuppressWarnings("resource")
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
	private static String vigenereCipher(String plain, String code,
			int direction) {
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
				System.out.println("Stripping letter: \"" + plain.charAt(i)
						+ "\"");
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
		String cipherText = "";
		for (int i = 0; i < plainText.length(); i++) {
			char c = plainText.charAt(i);
			int shiftedIndex = ALPHABET.indexOf(c) + shiftValue;
			while (shiftedIndex < 0)
				shiftedIndex += ALPHABET.length();
			shiftedIndex %= ALPHABET.length();
			cipherText += ALPHABET.charAt(shiftedIndex);
		}
		return cipherText;
	}

	public static String rotationCipherEncrypt(String plain, int movement) {
		return rotationCipher(plain, movement);
	}

	public static String rotationCipherDecrypt(String plain, int movement) {
		return rotationCipher(plain, -movement);
	}
}