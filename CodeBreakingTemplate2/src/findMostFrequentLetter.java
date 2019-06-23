import java.util.ArrayList;

public class findMostFrequentLetter {
	public static void main(String[] args) {
		findMostFrequentLetters(true);
	}

	public static void findMostFrequentLetters(boolean willPrint, String fileName) {
		int numOfFrequent = 5;
		Bag bag = new Bag();
		String fullText = Cipher.getFileAsString(fileName);

		for (int i = 0; i < fullText.length(); i++) {
			bag.add(fullText.substring(i, i + 1));
		}

		ArrayList<String> letters = new ArrayList<String>();
		letters.addAll(bag.getNMostFrequentStrings(numOfFrequent));
		if (willPrint) {
			System.out.println("Most frequent letters: " + bag.getNMostFrequentStrings(numOfFrequent));
			for (int i = 0; i < numOfFrequent; i++) {
				double percent = 100 * (double) bag.getNumOccurances(letters.get(i)) / bag.getTotalWords();
				System.out.println("The chance of getting an \"" + letters.get(i) + "" + "\" is " + percent + "%");
			}
		}
	}

}
